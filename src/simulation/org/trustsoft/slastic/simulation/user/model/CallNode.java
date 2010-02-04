package org.trustsoft.slastic.simulation.user.model;

import java.util.List;

import org.trustsoft.slastic.simulation.model.software.system.SimSystem;
import org.trustsoft.slastic.simulation.user.controller.Controller;


import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimTime;

public class CallNode extends Node {

	private final String signature;

	public CallNode(final Model owner, final String name,
			final List<Node> successors, final String signature) {
		super(owner, name, successors, null);
		this.signature = signature;
	}

	@Override
	public Node getSuccessor(final double val) {
		// TODO Auto-generated method stub
		return super.successors.get(0);
	}

	@Override
	public void eventRoutine() {
		// TODO Auto-generated method stub
		// TODO lookup component and call the appropriate method
		SimSystem.getInstance().call(signature, this);

	}

	public void callReturned() {
		// TODO notify return
		final Node succ = getSuccessor(super.getDist().sample());
		if (succ != null) {
			succ.schedule(SimTime.NOW);
		} else {
			Controller.getInstance().finish(getName());
		}
	}

}
