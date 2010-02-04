package org.trustsoft.slastic.simulation.software.controller.controlflow;

import java.util.Hashtable;

import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.model.hardware.controller.engine.AbstractSchedulableProcess;
import org.trustsoft.slastic.simulation.model.hardware.controller.engine.Server;
import org.trustsoft.slastic.simulation.software.controller.CallHandler;

import desmoj.core.simulator.SimTime;

public class InternalActionNode extends ControlFlowNode {

	private final Hashtable<String, Demand> demands = new Hashtable<String, Demand>();
	private final String traceId;

	public InternalActionNode(final String name, final String traceId) {
		super(name, traceId);
		this.traceId = traceId;
	}

	@Override
	public void eventRoutine() {
		final String server = CallHandler.getInstance().getCurrentServer(
				traceId);
		final Server s = ModelManager.getInstance().getHwCont().getServer(
				server);
		// create schedulable processes and schedule to the server
	}

	public void add(final String requiredResource, final String demand) {
		final Demand<Integer> d = new Demand<Integer>(demand, Integer.class);
		demands.put(requiredResource, d);
	}

	public void returned(final SimTime t, final AbstractSchedulableProcess p) {
		demands.remove(p.getName());
		if (demands.isEmpty()) {
			CallHandler.getInstance().actionReturn(traceId);
		}

	}

}
