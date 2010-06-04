package org.trustsoft.slastic.simulation.model.hardware.controller.cpu;

import org.trustsoft.slastic.simulation.model.hardware.controller.engine.ProcessingResource;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;

public class CPU extends ProcessingResource<CPUSchedulableProcess> {

	private final Queue<CPUSchedulableProcess> queue;
	private final int capacity;

	public CPU(final Model owner, final String myName, final boolean debugMode,
			final CPUScheduler scheduler, final int capacity) {
		super(owner, myName, debugMode, scheduler, capacity);
		this.queue = scheduler.getQueue();
		scheduler.setOwner(this);
		this.capacity = capacity;
	}

	@Override
	public float getBusiness() {
		return this.getScheduler().getBusiness();
	}

	@Override
	public void init() {
		// queue = new Queue<T>(super.getModel(), super.getName() + "Queue",
		// super
		// .debugIsOn(), super.traceIsOn());
	}

	@Override
	public int getCapacity() {
		return this.capacity;
	}

}
