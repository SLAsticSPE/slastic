package org.trustsoft.slastic.simulation.user.model;

import org.trustsoft.slastic.simulation.user.controller.Controller;

import desmoj.core.simulator.Model;

public class ScenarioFinishNode extends Node {

	public ScenarioFinishNode(final Model owner, final String name) {
		super(owner, name, Node.EMPTY_LIST, null);
	}

	@Override
	public Node getSuccessor(final double val) {
		return null;
	}

	@Override
	public void eventRoutine() {
		Controller.getInstance().finish(getName());
	}

}
