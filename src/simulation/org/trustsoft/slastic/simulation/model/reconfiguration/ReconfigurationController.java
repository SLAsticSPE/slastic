package org.trustsoft.slastic.simulation.model.reconfiguration;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.trustsoft.slastic.simulation.listeners.ReconfEventListener;
import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.model.software.system.ReconfigurableComponent;

import reconfMM.ReconfigurationModel;
import reconfMM.ReconfigurationSpecification;
import ReconfigurationPlanModel.NodeAllocationOP;
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

	public ReconfigurationController(final ReconfigurationModel reconfModel,
			final Model model) {
		if (instance == null) {
			for (final ReconfigurationSpecification reconfSpec : reconfModel
					.getComponents()) {
				final ReconfigurableComponent comp = new ReconfigurableComponent(
						reconfSpec.getComponent(),
						reconfSpec.getMaxInstances(), reconfSpec.isMigratable());
				components.put(comp.getComponent().getId(), comp);
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
		return components.get(component.getId());
	}

	/**
	 * Get the reconfiguration specification of the component with the given id
	 * 
	 * @param componentId
	 * @return the reconfiguration specification of the given component
	 */
	public ReconfigurableComponent getReconfSpecById(final String componentId) {
		return components.get(componentId);
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
		return components.get(asmContext
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
		final ReconfigurableComponent comp = components.get(componentId);
		int instances = 0;
		if (comp != null
				&& comp.getMaxInstances() > (instances = currentInstances
						.get(componentId))) {
			currentInstances.put(componentId, ++instances);
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
		final ReconfigurableComponent comp = components.get(componentId);
		int instances = 0;
		if (comp != null && (instances = currentInstances.get(componentId)) > 1) {
			currentInstances.put(componentId, --instances);
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
		return instance;
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

	public void schedulePlan(final SLAsticReconfigurationPlan plan) {
		this.plan = plan;
		// FIXME: Do it! create events and schedule
	}

	public void operationFinished(final SLAsticReconfigurationOpType reconfOp) {
		scheduleNextOp();
		for (final ReconfEventListener listener : listeners) {
			listener.notifyPlanDone(plan);
		}

	}

	private void scheduleNextOp() {
		if (!reconfEvents.isEmpty()) {
			// TODO: maybe we need more intelligence here!
			// FIXME dereplication can happen when mark unused has been called!
			reconfEvents.remove(0).schedule(SimTime.NOW);
		}
	}

	public void addReconfEventListener(final ReconfEventListener listener) {
		listeners.add(listener);
	}

	public void removeReconfEventListener(final ReconfEventListener listener) {
		listeners.remove(listener);
	}

	public void operationFailed(final SLAsticReconfigurationOpType reconfOp) {
		scheduleNextOp();
		for (final ReconfEventListener listener : listeners) {
			listener.notifyOpFailed(plan, reconfOp);
		}
	}

	public int getComponentInstances(final String componentId) {
		return currentInstances.get(componentId);
	}

	public void markUnusedAndBlocked(final String server,
			final String asmContext) {
		// TODO Auto-generated method stub
		// FIXME add Functionality! e.g. if first event in list is delete
		// component for the empty component we can schedule it now
	}

}
