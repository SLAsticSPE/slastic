package org.trustsoft.slastic.simulation.model.hardware.controller.engine;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;

public abstract class ProcessingResource<T extends AbstractSchedulableProcess>
		extends Entity {

	private final AbstractScheduler<? extends ProcessingResource<T>, T> scheduler;
	private final int capacity;

	public ProcessingResource(
			final Model owner,
			final String name,
			final boolean showInTrace,
			final AbstractScheduler<? extends ProcessingResource<T>, T> scheduler,
			final int capacity) {
		super(owner, name, showInTrace);
		this.scheduler = scheduler;
		this.capacity = capacity;
	}

	// ProcessingResource( Model
	// ) {
	// this.scheduler = scheduler;
	// }

	public abstract double getBusiness();

	final public void schedule(final T process) {
		this.scheduler.schedule(process);
	}

	public abstract void init();

	public AbstractScheduler<? extends ProcessingResource<T>, T> getScheduler() {
		return this.scheduler;
	}

	public int getCapacity() {
		return this.capacity;
	}

}
