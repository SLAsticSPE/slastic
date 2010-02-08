package org.trustsoft.slastic.simulation.model.hardware.controller.cpu;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.config.Constants;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import desmoj.core.simulator.QueueBased;
import desmoj.core.simulator.SimTime;

public class CPURRScheduler extends CPUScheduler {

	private static Log log = LogFactory.getLog(CPURRScheduler.class);

	private final Queue<CPUSchedulableProcess> activeProcess;

	private long cyclesPerSlice;

	public CPURRScheduler(final Model model, final String name) {
		super(model, name, new Queue<CPUSchedulableProcess>(model, name
				+ "JobQueue", Constants.DEBUG, Constants.DEBUG));
		this.activeProcess = new Queue<CPUSchedulableProcess>(model, name,
				QueueBased.FIFO, 1, Constants.DEBUG, Constants.DEBUG);
	}

	@Override
	public synchronized void schedule(final CPUSchedulableProcess process) {
		this.queue.insert(process);
		this.cyclesPerSlice = 50 * 1000 * this.getOwner().getCapacity();
		CPURRScheduler.log.info("Task arrived: " + process);
		if (this.queue.length() == 1) {
			super.getTickEventGenerator().resume(this.tick());
		}

	}

	@Override
	public synchronized SimTime tick() {
		SimTime ret = null;
		CPURRScheduler.log.info("Got " + this.queue.length() + " processes");
		final CPUSchedulableProcess p1 = this.activeProcess.first();
		final CPUSchedulableProcess p = this.queue.first();
		if (this.queue.length() > 0) {
			this.queue.remove(p);

			final long prun = p.getCyclesRemaining();
			CPURRScheduler.log.info("It needs " + prun
					+ " cycles to finish, we have " + this.cyclesPerSlice
					+ " cycles per slice");
			// cycles per slice = slice in ms * capacity in MHz * 1000
			// <=> ms = cycles / cap

			if (prun > this.cyclesPerSlice) {
				ret = this.getTickSimTime();
				this.queue.insert(p);
			} else {
				ret = new SimTime(prun
						/ (double) (this.getOwner().getCapacity() * 1000));
			}
		}
		if (p1 != null) {
			this.activeProcess.remove(p1);
			p1.substractFromRemaining(this.cyclesPerSlice);
			CPURRScheduler.log.info("Substracting " + this.cyclesPerSlice
					+ " from " + p1 + ", now " + p1.getCyclesRemaining()
					+ " are remaining");
		}
		if (p != null) {
			this.activeProcess.insert(p);
		}
		CPURRScheduler.log.info("Active processes: "
				+ (this.activeProcess.isEmpty() ? 0 : 1));
		CPURRScheduler.log.info("Next slice will be started at " + ret);
		return ret;
	}

	@Override
	public Queue<CPUSchedulableProcess> getQueue() {
		return this.queue;
	}
}
