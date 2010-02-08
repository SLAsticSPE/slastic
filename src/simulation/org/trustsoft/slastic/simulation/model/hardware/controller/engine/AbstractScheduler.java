package org.trustsoft.slastic.simulation.model.hardware.controller.engine;

import org.trustsoft.slastic.simulation.config.Constants;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import desmoj.core.simulator.SimTime;

public abstract class AbstractScheduler<PRType extends ProcessingResource<?>, SPType extends AbstractSchedulableProcess>
		extends Entity {
	private PRType owner;
	private long tickRate;
	private SimTime tickSimTime;
	private boolean timeUnitSet, tickRateSet;
	private TimeUnit unit;
	private TickEventGenerator tickEventGenerator;

	protected final Queue<SPType> queue;

	public AbstractScheduler(final Model model, final String name,
			final Queue<SPType> queue) {
		super(model, name, Constants.DEBUG);
		this.queue = queue;
	}

	public void setOwner(final PRType owner) {
		if (this.owner == null) {
			this.owner = owner;
			this.tickEventGenerator = new TickEventGenerator(this.getModel(),
					this.getName(), Constants.DEBUG, this.owner);
		}
	}

	public PRType getOwner() {
		return this.owner;
	}

	public abstract void schedule(SPType process);

	public long getTickRate() {
		return this.tickRate;
	}

	public void setTickRate(final long l) {
		if (!this.tickRateSet) {
			this.tickRate = l;
			this.tickSimTime = new SimTime(l);
			this.tickRateSet = true;
		}
	}

	public TimeUnit getUnit() {
		return this.unit;
	}

	public void setUnit(final TimeUnit unit) {
		if (!this.timeUnitSet) {
			this.unit = unit;
			this.timeUnitSet = true;
		}
	}

	public boolean isTimeUnitSet() {
		return this.timeUnitSet;
	}

	public boolean isTickRateSet() {
		return this.tickRateSet;
	}

	public abstract SimTime tick();

	public boolean isIdle() {
		return this.queue.isEmpty();
	}

	public TickEventGenerator getTickEventGenerator() {
		return this.tickEventGenerator;
	}

	public abstract Queue<? extends AbstractSchedulableProcess> getQueue();

	public SimTime getTickSimTime() {
		return this.tickSimTime;
	}

}
