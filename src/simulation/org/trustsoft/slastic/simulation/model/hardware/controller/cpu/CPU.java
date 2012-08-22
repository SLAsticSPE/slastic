package org.trustsoft.slastic.simulation.model.hardware.controller.cpu;

import org.trustsoft.slastic.simulation.model.hardware.controller.engine.ProcessingResource;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimTime;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class CPU extends ProcessingResource<CPUSchedulableProcess> {

	private final int capacity;

	public CPU(final Model owner, final String myName, final boolean debugMode, final CPUScheduler scheduler, final int capacity) {
		super(owner, myName, debugMode, scheduler, capacity);
		scheduler.setOwner(this);
		this.capacity = capacity;
	}

	@Override
	public double getBusiness() {
		return this.getScheduler().getBusiness();
	}

	@Override
	public void init() {}

	@Override
	public int getCapacity() {
		return this.capacity;
	}

	public void resumeMonitoringAt(final SimTime t) {
		super.getScheduler().resumeBusinessMonitoringAt(t);
	}

	public void pauseMonitoring() {
		this.getScheduler().pauseBusinessMonitoring();
	}
}
