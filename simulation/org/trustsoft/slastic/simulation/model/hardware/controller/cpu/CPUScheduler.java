package org.trustsoft.slastic.simulation.model.hardware.controller.cpu;

import org.trustsoft.slastic.simulation.model.hardware.controller.engine.AbstractScheduler;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import desmoj.core.simulator.SimTime;

public abstract class CPUScheduler extends
		AbstractScheduler<CPU, CPUSchedulableProcess> {

	public CPUScheduler(final Model model, final String name,
			final Queue<CPUSchedulableProcess> queue) {
		super(model, name, queue);
	}

	@Override
	public Queue<CPUSchedulableProcess> getQueue() {
		return queue;
	}

	@Override
	public abstract void schedule(final CPUSchedulableProcess process);

	@Override
	public abstract SimTime tick();

}
