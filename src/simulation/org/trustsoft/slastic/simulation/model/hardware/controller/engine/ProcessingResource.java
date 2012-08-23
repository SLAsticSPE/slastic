package org.trustsoft.slastic.simulation.model.hardware.controller.engine;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;

/**
 * 
 * @author Robert von Massow
 * 
 * @param <T>
 */
public abstract class ProcessingResource<T extends AbstractSchedulableProcess> extends Entity {

	private final AbstractScheduler<? extends ProcessingResource<T>, T> scheduler;
	private final int capacity;
	private volatile boolean allocated = false;

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

	/**
	 * @return the allocated
	 */
	public final boolean isAllocated() {
		return this.allocated;
	}

	/**
	 * @param allocated
	 *            the allocated to set
	 */
	public final void setAllocated(final boolean allocated) {
		this.allocated = allocated;
	}

}
