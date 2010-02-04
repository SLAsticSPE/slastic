package org.trustsoft.slastic.simulation.user.model;

import java.util.List;

import org.trustsoft.slastic.simulation.stoexeval.EvaluationProxy;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimTime;

public class LoopNode extends Node {

	protected String stoex;
	private int iterations;
	private final Node subGraph;

	public LoopNode(final Model owner, final String name,
			final List<Node> successors, final String stoex, final Node subGraph) {
		super(owner, name, successors, null);
		this.stoex = stoex;
		iterations = (Integer) EvaluationProxy.evaluate(stoex, Integer.class,
				null);
		this.subGraph = subGraph;
		this.subGraph.updateFather(this);
	}

	public LoopNode(final Model owner, final String name,
			final List<Node> successors, final String stoex,
			final Node subGraph, final Node father) {
		super(owner, name, successors, father);
		this.stoex = stoex;
		iterations = (Integer) EvaluationProxy.evaluate(stoex, Integer.class,
				null);
		this.subGraph = subGraph;
	}

	@Override
	public Node getSuccessor(final double val) {
		return iterations > 0 ? this : successors.get(0);
	}

	@Override
	public void eventRoutine() {
		// TODO schedule subGraph execution until iterations reached
		if (iterations > 0) {
			subGraph.schedule(SimTime.NOW);
			iterations--;
		} else {
			getSuccessor(getDist().sample());
		}

	}

}
