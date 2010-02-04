package org.trustsoft.slastic.simulation.user.model;

import java.util.Hashtable;
import java.util.List;

import org.trustsoft.slastic.simulation.software.controller.exceptions.NoBranchProbabilitiesException;
import org.trustsoft.slastic.simulation.util.Interval;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimTime;

public class BranchNode extends Node {

	private final Hashtable<Interval<?>, Node> branches = new Hashtable<Interval<?>, Node>();

	private Node successor = null;

	public BranchNode(final Model owner, final String name,
			final List<Node> successors, final List<Interval<Node>> intervals)
			throws NoBranchProbabilitiesException {
		super(owner, name, successors, null);
		if (successors.size() != intervals.size()) {
			throw new NoBranchProbabilitiesException(
					"# of transitions != # of probabilities");
		}
		for (int i = 0; i < successors.size(); i++) {
			branches.put(intervals.get(i), successors.get(i));
		}
	}

	public BranchNode(final Model owner, final String name,
			final List<Node> successors, final List<Interval<Node>> intervals,
			final Node father) throws NoBranchProbabilitiesException {
		super(owner, name, successors, father);
		if (successors.size() != intervals.size()) {
			throw new NoBranchProbabilitiesException(
					"# of transitions != # of probabilities");
		}
		for (int i = 0; i < successors.size(); i++) {
			branches.put(intervals.get(i), successors.get(i));
		}
	}

	@Override
	public Node getSuccessor(final double val) {
		if (successor != null) {
			return successor;
		}
		for (final Interval<?> i : branches.keySet()) {
			if (i.contains(val)) {
				return successor = branches.get(i);
			}
		}
		return null;
	}

	@Override
	public void eventRoutine() {
		if (successor == null) {
			getSuccessor(Math.random());
		}
		successor.schedule(SimTime.NOW);
	}

	public void reset() {
		if (!isScheduled()) {
			successor = null;
		}
	}

}
