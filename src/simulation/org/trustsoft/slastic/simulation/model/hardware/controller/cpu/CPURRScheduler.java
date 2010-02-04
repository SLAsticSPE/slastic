package org.trustsoft.slastic.simulation.model.hardware.controller.cpu;

import org.trustsoft.slastic.simulation.config.Constants;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import desmoj.core.simulator.SimTime;

public class CPURRScheduler extends CPUScheduler {

	public CPURRScheduler(final Model model, final String name) {
		super(model, name, new Queue<CPUSchedulableProcess>(model, name
				+ "JobQueue", Constants.DEBUG, Constants.DEBUG));
	}

	@Override
	public synchronized void schedule(final CPUSchedulableProcess process) {
		if (queue.length() == 0) {
			super.getTickEventGenerator().resume(tick());
		}
		queue.insert(process);
	}

	@Override
	public synchronized SimTime tick() {
		SimTime ret = null;
		if (queue.length() > 0) {
			final CPUSchedulableProcess p = queue.first();
			queue.remove(p);
			final long prun = p.getCyclesRemaining()
					/ super.getOwner().getCapacity();
			// cycles per slice = slice in ms * capacity in MHz * 1000
			// <=> ms = cycles / cap
			if (prun > getTickRate()) {
				ret = getTickSimTime();
				p.substractFromRemaining(getTickRate());
				queue.insert(p);
			} else {
				ret = new SimTime(prun);
				p.substractFromRemaining(prun);
				p.getBelongs();
			}
		}
		return ret;
	}

	@Override
	public Queue<CPUSchedulableProcess> getQueue() {
		return queue;
	}
}
