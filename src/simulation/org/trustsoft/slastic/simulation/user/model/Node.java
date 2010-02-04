package org.trustsoft.slastic.simulation.user.model;

import java.util.LinkedList;
import java.util.List;

import org.trustsoft.slastic.simulation.config.Constants;

import desmoj.core.dist.RealDist;
import desmoj.core.dist.RealDistUniform;
import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimTime;

public abstract class Node extends ExternalEvent {

	public static final List<Node> EMPTY_LIST = new LinkedList<Node>();

	private RealDist dist;

	private Node father;

	public Node(final Model owner, final String name,
			final List<Node> successors, final Node father) {
		super(owner, name, Constants.DEBUG);
		this.successors = successors;
		dist = new RealDistUniform(owner, name + "rand", .0, 1.0,
				Constants.DEBUG, Constants.DEBUG);
		this.father = father;
	}

	protected List<Node> successors = new LinkedList<Node>();

	public abstract Node getSuccessor(double val);

	public List<Node> genList() {
		final List<Node> ret = new LinkedList<Node>();
		Node cur = this;
		while (cur != null) {
			ret.add(cur);
			cur = cur.getSuccessor(cur.getDist().sample());
		}
		ret.add(new ScenarioFinishNode(getModel(), getName()));
		return ret;
	}

	protected final RealDist getDist() {
		return dist;
	}

	protected final void setDist(final RealDist dist) {
		this.dist = dist;
	}

	public void schedule(final ScenarioSpec spec) {
		schedule(new SimTime(spec.getTime()));
	}

	public final Node getFather() {
		return father;
	}

	public void setFather(final Node node) {
		father = node;
	}

	public void updateFather(final Node node) {
		for (final Node n : genList()) {
			n.setFather(node);
		}
	}

}
