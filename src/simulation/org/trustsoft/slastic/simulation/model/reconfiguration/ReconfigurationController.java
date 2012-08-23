/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package org.trustsoft.slastic.simulation.model.reconfiguration;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.events.reconfiguration.AbstractReconfigurationEvent;
import org.trustsoft.slastic.simulation.events.reconfiguration.AllocationEvent;
import org.trustsoft.slastic.simulation.events.reconfiguration.DelComponent;
import org.trustsoft.slastic.simulation.events.reconfiguration.DelocationEvent;
import org.trustsoft.slastic.simulation.events.reconfiguration.ReplicationEvent;
import org.trustsoft.slastic.simulation.listeners.IReconfigurationEventListener;
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
import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimTime;

/**
 * 
 * @author Robert von Massow
 * 
 */
public final class ReconfigurationController {
	private static final Log log = LogFactory.getLog(ReconfigurationController.class);

	private static ReconfigurationController INSTANCE;

	private final List<AbstractReconfigurationEvent> reconfEvents = new LinkedList<AbstractReconfigurationEvent>();

	private final Hashtable<String, ReconfigurableComponent> components = new Hashtable<String, ReconfigurableComponent>();

	private final Hashtable<String, Integer> currentInstances = new Hashtable<String, Integer>();

	private SLAsticReconfigurationPlan plan;

	private final List<IReconfigurationEventListener> listeners = new LinkedList<IReconfigurationEventListener>();

	private final Model model;

	private final ReconfigurationPlanModelFactory reconfOpFact = ReconfigurationPlanModelFactory.eINSTANCE;

	public ReconfigurationController(final ReconfigurationModel reconfModel,
			final Model model) {
		this.model = model;
		if (ReconfigurationController.INSTANCE == null) {
			for (final ReconfigurationSpecification reconfSpec : reconfModel.getComponents()) {
				final ReconfigurableComponent comp =
						new ReconfigurableComponent(reconfSpec.getComponent(), reconfSpec.getMaxInstances(), reconfSpec.isMigratable());
				this.components.put(comp.getComponent().getId(), comp);
			}
			ReconfigurationController.INSTANCE = this;
		}
	}

	/**
	 * Get the reconfiguration specification of the given component
	 * 
	 * @param component
	 * @return the reconfiguration specification of the given component
	 */
	public ReconfigurableComponent getReconfSpecByComponent(final ProvidesComponentType component) {
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
	public ReconfigurableComponent getReconfSpecByASMContext(final AssemblyContext asmContext) {
		return this.components.get(asmContext.getEncapsulatedComponent_ChildComponentContext().getId());
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
		if ((comp != null) && (comp.getMaxInstances() > (instances = this.currentInstances.get(componentId)))) {
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
		if ((comp != null) && ((instances = this.currentInstances.get(componentId)) > 1)) {
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
		return ReconfigurationController.INSTANCE;
	}

	@Deprecated
	public boolean checkValidity(final SLAsticReconfigurationPlan plan) {
		for (final SLAsticReconfigurationOpType operation : plan.getOperations()) {
			if (operation instanceof NodeAllocationOP) {
				final NodeAllocationOP nodeAlloc = (NodeAllocationOP) operation;
				ModelManager.getInstance().getHardwareController().isAllocated(nodeAlloc.getNode().getId());
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
		if (this.reconfEvents.isEmpty() && (this.plan == null)
				&& (plan.getOperations().size() > 0)) {
			this.plan = plan;
			for (final SLAsticReconfigurationOpType op : this.plan
					.getOperations()) {
				if (op instanceof ComponentReplicationOP) {
					final ComponentReplicationOP repOp = (ComponentReplicationOP) op;
					this.reconfEvents.add(this.createEvent(repOp));
				} else if (op instanceof ComponentDeReplicationOP) {
					final ComponentDeReplicationOP repOp = (ComponentDeReplicationOP) op;
					this.reconfEvents.add(this.createEvent(repOp));
				} else if (op instanceof ComponentMigrationOP) {
					final ComponentMigrationOP repOp = (ComponentMigrationOP) op;
					// create replication and dereplication operations
					final ComponentReplicationOP replic = this.reconfOpFact
							.createComponentReplicationOP();
					final ComponentDeReplicationOP dereplic = this.reconfOpFact
							.createComponentDeReplicationOP();
					replic.setComponent(repOp.getComponent()
							.getAssemblyContext_AllocationContext());
					replic.setDestination(repOp.getDestination());
					// System.out
					// .println("-----------------------------------------------------------");
					// System.out.println("Adding Operation " + replic);
					// System.out
					// .println("-----------------------------------------------------------");
					this.reconfEvents.add(this.createEvent(replic));
					dereplic.setClone(repOp.getComponent());
					dereplic.setComponent(repOp.getComponent());
					// System.out
					// .println("-----------------------------------------------------------");
					// System.out.println("Adding operation " + dereplic);
					// System.out
					// .println("-----------------------------------------------------------");
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
		} else {
			for (final IReconfigurationEventListener listener : this.listeners) {
				listener.notifyPlanFailed(plan);
			}
			ReconfigurationController.log.warn("Rejected plan: "
					+ (this.plan != null ? "plan already running"
							: plan.getOperations().size() == 0 ? "no operations provided"
									: "unknown"));
			if (this.plan != null) {
				ReconfigurationController.log.warn("Running plan with ops: "
						+ this.plan.getOperations().size());
				for (final SLAsticReconfigurationOpType op : this.plan
						.getOperations()) {
					ReconfigurationController.log.warn(op);
				}
			}
		}
	}

	private AbstractReconfigurationEvent createEvent(final NodeDeAllocationOP repOp) {
		return new DelocationEvent(this.model, repOp.toString(),
				Constants.DEBUG, repOp);
	}

	private AbstractReconfigurationEvent createEvent(final NodeAllocationOP repOp) {
		return new AllocationEvent(this.model, repOp.toString(),
				Constants.DEBUG, repOp);
	}

	private AbstractReconfigurationEvent createEvent(final ComponentReplicationOP op) {
		return new ReplicationEvent(this.model, op.toString(), Constants.DEBUG,
				op);
	}

	private AbstractReconfigurationEvent createEvent(final ComponentDeReplicationOP op) {
		return new DelComponent(this.model, op.toString(), Constants.DEBUG, op);
	}

	public void operationFinished(final SLAsticReconfigurationOpType reconfOp) {
		if (reconfOp instanceof ComponentDeReplicationOP) {
			// System.out
			// .println("-----------------------------------------------------------");
			// System.out.println("Blocking component " + reconfOp);
			ModelManager
					.getInstance()
					.getAllocationController()
					.blockInstance(
							((ComponentDeReplicationOP) reconfOp).getClone()
									.getAssemblyContext_AllocationContext()
									.getId(),
							((ComponentDeReplicationOP) reconfOp).getClone()
									.getResourceContainer_AllocationContext()
									.getId());
			// System.out
			// .println("-----------------------------------------------------------");
		} else {
			// System.out
			// .println("----------------------------------------------------------");
			// System.out.println("Scheduling "
			// + this.reconfEvents.size() > 0 ? this.reconfEvents.get(0)
			// .getReconfOp() : "done, idling");
			// System.out
			// .println("----------------------------------------------------------");
			this.scheduleNextOp();
		}
	}

	private void scheduleNextOp() {
		if (!this.reconfEvents.isEmpty()) {
			final AbstractReconfigurationEvent event = this.reconfEvents.remove(0);
			event.schedule(SimTime.NOW);
			// System.out
			// .println("----------------------------------------------------------");
			// System.out.println("Scheduling next Operation "
			// + event.getReconfOp());
			// System.out
			// .println("----------------------------------------------------------");
		} else {
			for (final IReconfigurationEventListener listener : this.listeners) {
				listener.notifyPlanDone(this.plan);
			}
			this.plan = null;
		}
	}

	public void addReconfEventListener(final IReconfigurationEventListener listener) {
		this.listeners.add(listener);
	}

	public void removeReconfEventListener(final IReconfigurationEventListener listener) {
		this.listeners.remove(listener);
	}

	public void operationFailed(final SLAsticReconfigurationOpType reconfOp) {
		for (final IReconfigurationEventListener listener : this.listeners) {
			listener.notifyOpFailed(this.plan, reconfOp);
			listener.notifyPlanFailed(this.plan);
		}
		this.plan = null;
		this.reconfEvents.clear();
	}

	public int getComponentInstances(final String componentId) {
		return this.currentInstances.get(componentId);
	}

	public void markUnusedAndBlocked(final String server, final String asmContext) {
		this.scheduleNextOp();
	}

}
