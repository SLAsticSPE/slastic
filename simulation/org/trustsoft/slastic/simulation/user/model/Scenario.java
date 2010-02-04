package org.trustsoft.slastic.simulation.user.model;

import java.util.List;

import desmoj.core.simulator.SimTime;

public class Scenario {

	private final List<Node> graph;

	private final ScenarioSpec spec;

	public Scenario(final List<Node> graph, final ScenarioSpec spec) {
		this.graph = graph;
		this.spec = spec;
	}

	public boolean isOpen() {
		return spec.isOpen();
	}

	public void schedule(final SimTime time) {
		if (time == null && !isOpen()) {
			graph.get(0).schedule(new SimTime(spec.getTime()));
		} else {
			graph.get(0).schedule(time);
		}
	}

}
