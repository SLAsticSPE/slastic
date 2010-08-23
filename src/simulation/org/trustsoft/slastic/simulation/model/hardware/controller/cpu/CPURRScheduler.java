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

	private double utilization;

	private long cyclesPerSlice;

	private boolean idle = true;

	private final UtilizationProbeEventGenerator utilizationTicker;

	public CPURRScheduler(final Model model, final String name) {
		super(model, name, new Queue<CPUSchedulableProcess>(model, name
				+ "JobQueue", Constants.DEBUG, Constants.DEBUG));
		this.activeProcess = new Queue<CPUSchedulableProcess>(model, name,
				QueueBased.FIFO, 1, Constants.DEBUG, Constants.DEBUG);
		this.utilizationTicker = new UtilizationProbeEventGenerator(model,
				name, Constants.DEBUG, this);
	}

	@Override
	public synchronized void schedule(final CPUSchedulableProcess process) {
		this.queue.insert(process);
		this.cyclesPerSlice = Constants.PS_SLICE * Constants.CPU_SCALE
				* this.getOwner().getCapacity() / 1000;
		CPURRScheduler.log.info("Task arrived: " + process);
		if (this.idle) {
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
				ret = new SimTime(
						prun
								/ (double) (this.getOwner().getCapacity() * Constants.CPU_SCALE)); // MHz
				// => divide by 1Mega
				CPURRScheduler.log.info("Finishing job in " + ret);
			}
		}
		if (ret == null) {
			this.idle = true;
		} else {
			this.idle = false;
		}
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

	@Override
	public synchronized double getBusiness() {
		return this.utilization;
	}

	public double recalcUtilization() {
		this.utilization = this.activeProcess.averageLength();
		this.activeProcess.reset();
		return this.utilization;
	}

	@Override
	public int getProcessCount() {
		return this.queue.length() + this.activeProcess.length();
	}
}
