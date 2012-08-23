package org.trustsoft.slastic.simulation.model.hardware.controller.hd;

import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.model.hardware.controller.engine.AbstractScheduler;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import desmoj.core.simulator.SimTime;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class HDScheduler extends AbstractScheduler<HardDrive, IOOperation> {

	public HDScheduler(final Model model, final String name) {
		super(model, name, new Queue<IOOperation>(model, name + "Queue", Constants.DEBUG, Constants.DEBUG));
	}

	@Override
	public void schedule(final IOOperation process) {
		throw new UnsupportedOperationException("Needs to be implemented");
	}

	@Override
	public SimTime tick() {
		throw new UnsupportedOperationException("Needs to be implemented");
	}

	@Override
	public Queue<IOOperation> getQueue() {
		return this.queue;
	}

	@Override
	public double getBusiness() {
		throw new UnsupportedOperationException("Needs to be implemented");
	}

	@Override
	public void resumeBusinessMonitoringAt(final SimTime t) {
		throw new UnsupportedOperationException("Needs to be implemented");
	}

	@Override
	public void pauseBusinessMonitoring() {
		throw new UnsupportedOperationException("Needs to be implemented");
	}

}
