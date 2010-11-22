package org.trustsoft.slastic.simulation.model.mapping;

import java.util.LinkedList;
import java.util.List;

import org.trustsoft.slastic.simulation.software.controller.controlflow.ControlFlowNode;

import desmoj.core.simulator.SimTime;

public class PassiveSimResource {

	private final String name;
	private final int maxCapacity;
	private int capacity;
	private final List<ControlFlowNode> queue = new LinkedList<ControlFlowNode>();

	public PassiveSimResource(final String name, final int maxCapacity) {
		this.name = name;
		this.maxCapacity = this.capacity = maxCapacity;
	}

	public int acquire(final ControlFlowNode node) {
		return this.capacity > 0 ? --this.capacity + this.sched(node) : this
				.enqueue(node);
	}

	public int release() {
		return this.queue.size() > 0 ? -this.queue.size()
				+ this.sched(this.queue.remove(0))
				: ++this.capacity <= this.maxCapacity ? this.capacity : null;
	}

	private int sched(final ControlFlowNode node) {
		node.schedule(SimTime.NOW);
		return 0;
	}

	private int enqueue(final ControlFlowNode node) {
		this.queue.add(node);
		return -this.queue.size();
	}

	public final String getName() {
		return this.name;
	}
}
