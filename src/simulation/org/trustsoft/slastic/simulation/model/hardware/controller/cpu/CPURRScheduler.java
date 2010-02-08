package org.trustsoft.slastic.simulation.model.hardware.controller.cpu;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.config.Constants;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import desmoj.core.simulator.SimTime;

public class CPURRScheduler extends CPUScheduler {

	private static Log log = LogFactory.getLog(CPURRScheduler.class);

	public CPURRScheduler(final Model model, final String name) {
		super(model, name, new Queue<CPUSchedulableProcess>(model, name
				+ "JobQueue", Constants.DEBUG, Constants.DEBUG));
	}

	@Override
	public synchronized void schedule(final CPUSchedulableProcess process) {
		this.queue.insert(process);
		CPURRScheduler.log.info("Task arrived: " + process);
		if (this.queue.length() == 1) {
			super.getTickEventGenerator().resume(this.tick());
		}

	}

	@Override
	public synchronized SimTime tick() {
		SimTime ret = null;
		CPURRScheduler.log.info("Got " + this.queue.length() + " processes");
		if (this.queue.length() > 0) {
			final CPUSchedulableProcess p = this.queue.first();
			this.queue.remove(p);
			final long cyclesPerSlice = 50 * 1000 * this.getOwner()
					.getCapacity();
			final long prun = p.getCyclesRemaining();
			CPURRScheduler.log.info("It needs " + prun + " cycles to finish");
			// cycles per slice = slice in ms * capacity in MHz * 1000
			// <=> ms = cycles / cap
			if (prun > cyclesPerSlice) {
				ret = this.getTickSimTime();
				p.substractFromRemaining(cyclesPerSlice);
				this.queue.insert(p);
				CPURRScheduler.log.info("Readding task " + p + ", needs "
						+ prun + " to finish");
			} else {
				ret = new SimTime(prun);
				p.substractFromRemaining(prun);
				CPURRScheduler.log.info("Done Processings task " + p);
			}
		}
		return ret;
	}

	@Override
	public Queue<CPUSchedulableProcess> getQueue() {
		return this.queue;
	}
}
