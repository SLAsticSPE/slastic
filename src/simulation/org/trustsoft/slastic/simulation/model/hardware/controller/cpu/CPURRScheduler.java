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

	private boolean idle = true;

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
		if (this.idle) {
			// FIXME some stuff goes terribly wrong
			// final SimTime t = this.tick();
			CPURRScheduler.log.info("Resuming scheduler with tick at "
					+ " with " + this.cyclesPerSlice + " cycles per slice");
			super.getTickEventGenerator().resume();
		}

	}

	@Override
	public synchronized SimTime tick() {
		SimTime ret = null;
		CPURRScheduler.log.info("Got " + this.queue.length() + " processes");
		/**
		 * new algo:<br />
		 * 1. remove active process ap and subtract processing time<br />
		 * 2. if ap.processingtime >0 readd to queue<br />
		 * 3. get next process np from queue<br />
		 * if np!=0 <br />
		 * 4a calc max processing time for np<br />
		 * 4b make np active<br />
		 * 4c schedule event<br />
		 * else<br />
		 * 5a dont schedule event, mark ap finished<br />
		 */
		final CPUSchedulableProcess ap = this.activeProcess.first();
		CPURRScheduler.log.info("Removing active process " + ap);
		if (ap != null) {
			ap.substractFromRemaining(this.cyclesPerSlice);
			CPURRScheduler.log.info(ap + " needs " + ap.getCyclesRemaining()
					+ " cycles to finish");
			this.activeProcess.remove(ap);
			if (ap.getCyclesRemaining() > 0) {
				this.queue.insert(ap);
			}
		}
		final CPUSchedulableProcess np = this.queue.first();
		if (np != null) {
			CPURRScheduler.log.info("Activating process " + np + " with "
					+ np.getCyclesRemaining() + " remaining cycles");
			this.queue.remove(np);
			this.activeProcess.insert(np);
			final long prun = np.getCyclesRemaining();
			if (prun > this.cyclesPerSlice) {
				ret = this.getTickSimTime();
				this.queue.insert(np);
				CPURRScheduler.log.info("Next slice will be started at " + ret);
			} else {
				ret = new SimTime(prun
						/ (double) (this.getOwner().getCapacity() * 1000000)); // MHz
				// =>
				// divide
				// by
				// 1Mega
				CPURRScheduler.log.info("Finishing job in " + ret);
			}
		}
		if (ret == null) {
			this.idle = true;
		} else {
			this.idle = false;
		}
		/*
		 * final CPUSchedulableProcess p = this.queue.first(); if
		 * (this.queue.length() > 0) { this.queue.remove(p);
		 * 
		 * final long prun = p.getCyclesRemaining(); CPURRScheduler.log.info("It
		 * needs " + prun + " cycles to finish, we have " + this.cyclesPerSlice + "
		 * cycles per slice"); // cycles per slice = slice in ms * capacity in
		 * MHz * 1000 // <=> ms = cycles / cap
		 * 
		 * if (prun > this.cyclesPerSlice) { ret = this.getTickSimTime();
		 * this.queue.insert(p); } else { ret = new SimTime(prun / (double)
		 * (this.getOwner().getCapacity() * 1000)); } } else if (p1 != null &&
		 * p1.getCyclesRemaining() > 0) { final long prun =
		 * p1.getCyclesRemaining(); CPURRScheduler.log.info("It needs " + prun + "
		 * cycles to finish, we have " + this.cyclesPerSlice + " cycles per
		 * slice"); // cycles per slice = slice in ms * capacity in MHz * 1000 //
		 * <=> ms = cycles / cap
		 * 
		 * if (prun > this.cyclesPerSlice) { ret = this.getTickSimTime(); } else {
		 * ret = new SimTime(prun / (double) (this.getOwner().getCapacity() *
		 * 1000)); } } if (p1 != null) { this.activeProcess.remove(p1);
		 * p1.substractFromRemaining(this.cyclesPerSlice);
		 * CPURRScheduler.log.info("Substracting " + this.cyclesPerSlice + "
		 * from " + p1 + ", now " + p1.getCyclesRemaining() + " are remaining"); }
		 * if (p != null) { this.activeProcess.insert(p); } else {
		 * this.activeProcess.insert(p); } CPURRScheduler.log.info("Active
		 * processes: " + (this.activeProcess.isEmpty() ? 0 : 1));
		 * CPURRScheduler.log.info("Next slice will be started at " + ret);
		 */
		return ret;
	}

	@Override
	public Queue<CPUSchedulableProcess> getQueue() {
		return this.queue;
	}

	@Override
	public boolean isIdle() {
		return this.queue.isEmpty() && this.activeProcess.isEmpty();
	}
}
