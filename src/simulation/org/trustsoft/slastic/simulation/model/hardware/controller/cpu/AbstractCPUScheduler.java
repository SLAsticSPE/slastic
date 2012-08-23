package org.trustsoft.slastic.simulation.model.hardware.controller.cpu;

import org.trustsoft.slastic.simulation.model.hardware.controller.engine.AbstractScheduler;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import desmoj.core.simulator.SimTime;

/**
 * 
 * @author Robert von Massow
 * 
 */
public abstract class AbstractCPUScheduler extends AbstractScheduler<CPU, CPUSchedulableProcess> {

	public AbstractCPUScheduler(final Model model, final String name,
			final Queue<CPUSchedulableProcess> queue) {
		super(model, name, queue);
	}

	@Override
	public final Queue<CPUSchedulableProcess> getQueue() {
		return this.queue;
	}

	@Override
	public abstract void schedule(final CPUSchedulableProcess process);

	@Override
	public abstract SimTime tick();

}
