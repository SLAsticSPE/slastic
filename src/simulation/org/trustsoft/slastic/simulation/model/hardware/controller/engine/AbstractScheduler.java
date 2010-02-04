package org.trustsoft.slastic.simulation.model.hardware.controller.engine;

import org.trustsoft.slastic.simulation.config.Constants;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import desmoj.core.simulator.SimTime;

public abstract class AbstractScheduler<PRType extends ProcessingResource<?>, SPType extends AbstractSchedulableProcess>
		extends Entity {
	private PRType owner;
	private int tickRate;
	private SimTime tickSimTime;
	private boolean timeUnitSet, tickRateSet;
	private TimeUnit unit;
	private final TickEventGenerator tickEventGenerator;

	protected final Queue<SPType> queue;

	public AbstractScheduler(final Model model, final String name,
			final Queue<SPType> queue) {
		super(model, name, Constants.DEBUG);
		this.queue = queue;
		this.tickEventGenerator = new TickEventGenerator(model, name,
				Constants.DEBUG, this.owner);
	}

	public void setOwner(final PRType owner) {
		if (this.owner == null) {
			this.owner = owner;
		}
	}

	public PRType getOwner() {
		return owner;
	}

	public abstract void schedule(SPType process);

	public int getTickRate() {
		return tickRate;
	}

	public void setTickRate(final int tickRate) {
		if (!this.tickRateSet) {
			this.tickRate = tickRate;
			tickSimTime = new SimTime(tickRate);
			tickRateSet = true;
		}
	}

	public TimeUnit getUnit() {
		return unit;
	}

	public void setUnit(final TimeUnit unit) {
		if (!this.timeUnitSet) {
			this.unit = unit;
			this.timeUnitSet = true;
		}
	}

	public boolean isTimeUnitSet() {
		return timeUnitSet;
	}

	public boolean isTickRateSet() {
		return tickRateSet;
	}

	public abstract SimTime tick();

	public boolean isIdle() {
		return queue.isEmpty();
	}

	public TickEventGenerator getTickEventGenerator() {
		return tickEventGenerator;
	}

	public abstract Queue<? extends AbstractSchedulableProcess> getQueue();

	public SimTime getTickSimTime() {
		return tickSimTime;
	}

}
