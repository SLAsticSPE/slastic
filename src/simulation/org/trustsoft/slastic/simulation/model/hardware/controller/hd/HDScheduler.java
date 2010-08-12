package org.trustsoft.slastic.simulation.model.hardware.controller.hd;

import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.model.hardware.controller.engine.AbstractScheduler;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import desmoj.core.simulator.SimTime;

public class HDScheduler extends AbstractScheduler<HardDrive, IOOperation> {

	public HDScheduler(final Model model, final String name) {
		super(model, name, new Queue<IOOperation>(model, name + "Queue",
				Constants.DEBUG, Constants.DEBUG));
	}

	@Override
	public void schedule(final IOOperation process) {
		// TODO Auto-generated method stub

	}

	@Override
	public SimTime tick() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Queue<IOOperation> getQueue() {
		return this.queue;
	}

	@Override
	public double getBusiness() {
		// TODO Auto-generated method stub
		return 0;
	}

}
