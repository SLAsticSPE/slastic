package org.trustsoft.slastic.simulation.model.reconfiguration;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.events.reconfiguration.AllocationEvent;
import org.trustsoft.slastic.simulation.events.reconfiguration.DelComponent;
import org.trustsoft.slastic.simulation.events.reconfiguration.DelocationEvent;
import org.trustsoft.slastic.simulation.events.reconfiguration.ReconfigurationEvent;
import org.trustsoft.slastic.simulation.events.reconfiguration.ReplicationEvent;
import org.trustsoft.slastic.simulation.listeners.ReconfEventListener;
import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.model.software.system.ReconfigurableComponent;

import reconfMM.ReconfigurationModel;
import reconfMM.ReconfigurationSpecification;
import ReconfigurationPlanModel.ComponentDeReplicationOP;
import ReconfigurationPlanModel.ComponentMigrationOP;
import ReconfigurationPlanModel.ComponentReplicationOP;
import ReconfigurationPlanModel.NodeAllocationOP;
import ReconfigurationPlanModel.NodeDeAllocationOP;
import ReconfigurationPlanModel.ReconfigurationPlanModelFactory;
import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.repository.ProvidesComponentType;
import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimTime;

public final class ReconfigurationController {

	/**
	 *
	 */
	private static ReconfigurationController instance;

	private final List<ExternalEvent> reconfEvents = new LinkedList<ExternalEvent>();

	private final Hashtable<String, ReconfigurableComponent> components = new Hashtable<String, ReconfigurableComponent>();

	private final Hashtable<String, Integer> currentInstances = new Hashtable<String, Integer>();

	private SLAsticReconfigurationPlan plan;

	private final List<ReconfEventListener> listeners = new LinkedList<ReconfEventListener>();

	private final Model model;

	private final ReconfigurationPlanModelFactory reconfOpFact = ReconfigurationPlanModelFactory.eINSTANCE;

	public ReconfigurationController(final ReconfigurationModel reconfModel,
			final Model model) {
		this.model = model;
		if (ReconfigurationController.instance == null) {
			for (final ReconfigurationSpecification reconfSpec : reconfModel
					.getComponents()) {
				final ReconfigurableComponent comp = new ReconfigurableComponent(
						reconfSpec.getComponent(),
						reconfSpec.getMaxInstances(), reconfSpec.isMigratable());
				this.components.put(comp.getComponent().getId(), comp);
			}
			ReconfigurationController.instance = this;
		}
	}

	/**
	 * Get the reconfiguration specification of the given component
	 *
	 * @param component
	 * @return the reconfiguration specification of the given component
	 */
	public ReconfigurableComponent getReconfSpecByComponent(
			final ProvidesComponentType component) {
		return this.components.get(component.getId());
	}

	/**
	 * Get the reconfiguration specification of the component with the given id
	 *
	 * @param componentId
	 * @return the reconfiguration specification of the given component
	 */
	public ReconfigurableComponent getReconfSpecById(final String componentId) {
		return this.components.get(componentId);
	}

	/**
	 * Get the reconfiguration specification of a component encapsulated in an
	 * assembly context
	 *
	 * @param asmContext
	 * @return the reconfiguration specification of the given component
	 */
	public ReconfigurableComponent getReconfSpecByASMContext(
			final AssemblyContext asmContext) {
		return this.components.get(asmContext
				.getEncapsulatedComponent_ChildComponentContext().getId());
	}

	/**
	 * Adds an instance to the overall existing components of the given type
	 * (via Id) to the system if the maximum instances constraint is not
	 * violated.
	 *
	 * @param componentId
	 * @return true if success
	 */
	public boolean addComponentInstance(final String componentId) {
		final ReconfigurableComponent comp = this.components.get(componentId);
		int instances = 0;
		if (comp != null
				&& comp.getMaxInstances() > (instances = this.currentInstances
						.get(componentId))) {
			this.currentInstances.put(componentId, ++instances);
			return true;
		}
		return false;
	}

	/**
	 * Subtracts an instance from the overall instances of a replicable
	 * component if there is more than one component left in the system.
	 *
	 * @param componentId
	 *            the id of the dereplicated component
	 * @return true if operation succeeded
	 */
	public boolean delComponentInstance(final String componentId) {
		final ReconfigurableComponent comp = this.components.get(componentId);
		int instances = 0;
		if (comp != null
				&& (instances = this.currentInstances.get(componentId)) > 1) {
			this.currentInstances.put(componentId, --instances);
			return true;
		}
		return false;
	}

	/**
	 * Pseudo singleton instance getter
	 *
	 * @return the instance of the reconfiguration controller
	 */
	public static ReconfigurationController getInstrance() {
		return ReconfigurationController.instance;
	}

	public boolean checkValidity(final SLAsticReconfigurationPlan plan) {
		for (final SLAsticReconfigurationOpType operation : plan
				.getOperations()) {
			if (operation instanceof NodeAllocationOP) {
				final NodeAllocationOP nodeAlloc = (NodeAllocationOP) operation;
				ModelManager.getInstance().getHwCont().isAllocated(
						nodeAlloc.getNode().getId());
			}
		}
		return false;
	}

	/**
	 * Take reconfiguration plan and build event list iff no plan is active
	 *
	 * @param plan
	 *            the plan to execute
	 */
	public void schedulePlan(final SLAsticReconfigurationPlan plan) {
		if (this.reconfEvents.isEmpty() && this.plan == null) {
			this.plan = plan;
			for (final SLAsticReconfigurationOpType op : this.plan
					.getOperations()) {
				if (op instanceof ComponentReplicationOP) {
					final ComponentReplicationOP repOp = (ComponentReplicationOP) op;
					this.reconfEvents.add(this.createEvent(repOp));
				} else if (op instanceof ComponentDeReplicationOP) {
					final ComponentDeReplicationOP repOp = (ComponentDeReplicationOP) op;
					this.reconfEvents.add(this.createEvent(repOp));
				} else if (op instanceof ComponentDeReplicationOP) {
					final ComponentMigrationOP repOp = (ComponentMigrationOP) op;
					// create replication and dereplication operations
					final ComponentReplicationOP replic = this.reconfOpFact
							.createComponentReplicationOP();
					final ComponentDeReplicationOP dereplic = this.reconfOpFact
							.createComponentDeReplicationOP();
					replic.setComponent(repOp.getComponent()
							.getAssemblyContext_AllocationContext());
					replic.setDestination(repOp.getDestination());
					this.reconfEvents.add(this.createEvent(replic));
					dereplic.setClone(repOp.getComponent());
					dereplic.setComponent(repOp.getComponent());
					this.reconfEvents.add(this.createEvent(dereplic));
				} else if (op instanceof NodeAllocationOP) {
					final NodeAllocationOP repOp = (NodeAllocationOP) op;
					this.reconfEvents.add(this.createEvent(repOp));
				} else if (op instanceof NodeDeAllocationOP) {
					final NodeDeAllocationOP repOp = (NodeDeAllocationOP) op;
					this.reconfEvents.add(this.createEvent(repOp));
				}

			}
			this.scheduleNextOp();
		}
	}

	private ReconfigurationEvent createEvent(final NodeDeAllocationOP repOp) {
		return new DelocationEvent(this.model, repOp.toString(),
				Constants.DEBUG, repOp);
	}

	private ReconfigurationEvent createEvent(final NodeAllocationOP repOp) {
		return new AllocationEvent(this.model, repOp.toString(),
				Constants.DEBUG, repOp);
	}

	private ReconfigurationEvent createEvent(final ComponentReplicationOP op) {
		return new ReplicationEvent(this.model, op.toString(), Constants.DEBUG,
				op);
	}

	private ReconfigurationEvent createEvent(final ComponentDeReplicationOP op) {
		return new DelComponent(this.model, op.toString(), Constants.DEBUG, op);
	}

	public void operationFinished(final SLAsticReconfigurationOpType reconfOp) {
		if (reconfOp instanceof ComponentDeReplicationOP) {
			ModelManager.getInstance().getAllocCont().blockInstance(
					((ComponentDeReplicationOP) reconfOp).getClone()
							.getAssemblyContext_AllocationContext().getId(),
					((ComponentDeReplicationOP) reconfOp).getClone()
							.getResourceContainer_AllocationContext().getId());

		} else {
			this.scheduleNextOp();
		}
		for (final ReconfEventListener listener : this.listeners) {
			listener.notifyPlanDone(this.plan);
		}

	}

	private void scheduleNextOp() {
		if (!this.reconfEvents.isEmpty()) {
			this.reconfEvents.remove(0).schedule(SimTime.NOW);
		} else {
			for (final ReconfEventListener listener : this.listeners) {
				listener.notifyPlanDone(this.plan);
			}
			this.plan = null;
		}
	}

	public void addReconfEventListener(final ReconfEventListener listener) {
		this.listeners.add(listener);
	}

	public void removeReconfEventListener(final ReconfEventListener listener) {
		this.listeners.remove(listener);
	}

	public void operationFailed(final SLAsticReconfigurationOpType reconfOp) {
		this.scheduleNextOp();
		for (final ReconfEventListener listener : this.listeners) {
			listener.notifyOpFailed(this.plan, reconfOp);
		}
	}

	public int getComponentInstances(final String componentId) {
		return this.currentInstances.get(componentId);
	}

	public void markUnusedAndBlocked(final String server,
			final String asmContext) {
		this.scheduleNextOp();
	}

}
