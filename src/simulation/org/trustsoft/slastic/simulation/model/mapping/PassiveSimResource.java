package org.trustsoft.slastic.simulation.model.mapping;

import java.util.LinkedList;
import java.util.List;

import org.trustsoft.slastic.simulation.software.controller.controlflow.AbstractControlFlowEvent;

import desmoj.core.simulator.SimTime;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class PassiveSimResource {

	private final String name;
	private final int maxCapacity;
	private volatile int capacity;
	private final List<AbstractControlFlowEvent> queue = new LinkedList<AbstractControlFlowEvent>();

	public PassiveSimResource(final String name, final int maxCapacity) {
		this.name = name;
		this.maxCapacity = this.capacity = maxCapacity;
	}

	public int acquire(final AbstractControlFlowEvent node) {
		return this.capacity > 0 ? --this.capacity + this.sched(node) : this.enqueue(node);
	}

	public int release() {
		return this.queue.size() > 0 ? -this.queue.size()
				+ this.sched(this.queue.remove(0))
				: ++this.capacity <= this.maxCapacity ? this.capacity : null;
	}

	private int sched(final AbstractControlFlowEvent node) {
		node.schedule(SimTime.NOW);
		return 0;
	}

	private int enqueue(final AbstractControlFlowEvent node) {
		this.queue.add(node);
		return -this.queue.size(); // TODO: why '-'?
	}

	public final String getName() {
		return this.name;
	}
}
