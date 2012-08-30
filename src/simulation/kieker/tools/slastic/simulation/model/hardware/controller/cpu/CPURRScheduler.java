/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package kieker.tools.slastic.simulation.model.hardware.controller.cpu;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.tools.slastic.simulation.config.Constants;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import desmoj.core.simulator.QueueBased;
import desmoj.core.simulator.SimTime;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class CPURRScheduler extends AbstractCPUScheduler {

	private static final Log LOG = LogFactory.getLog(CPURRScheduler.class);

	private final Queue<CPUSchedulableProcess> activeProcess;

	private volatile double utilization;

	private volatile long cyclesPerSlice;

	private volatile boolean idle = true;

	private final UtilizationProbeEventGenerator utilizationTicker;

	public CPURRScheduler(final Model model, final String name) {
		super(model, name, new Queue<CPUSchedulableProcess>(model, name + "JobQueue", Constants.DEBUG, Constants.DEBUG));
		this.activeProcess = new Queue<CPUSchedulableProcess>(model, name, QueueBased.FIFO, 1, Constants.DEBUG, Constants.DEBUG);
		this.utilizationTicker = new UtilizationProbeEventGenerator(model, name, Constants.DEBUG, this);
		this.activeProcess.reset();
	}

	@Override
	public synchronized void schedule(final CPUSchedulableProcess process) {
		this.queue.insert(process);
		this.cyclesPerSlice = (Constants.PS_SLICE * Constants.CPU_SCALE * this.getOwner().getCapacity()) / 1000;
		LOG.info("Task arrived: " + process);
		if (this.idle) {
			LOG.info("Resuming scheduler with tick at " + " with " + this.cyclesPerSlice + " cycles per slice");
			super.getTickEventGenerator().resume();
		}

	}

	@Override
	public synchronized SimTime tick() {
		SimTime ret = null;
		LOG.info("Got " + this.queue.length() + " processes");
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
		LOG.info("Removing active process " + ap);
		if (ap != null) {
			ap.substractFromRemaining(this.cyclesPerSlice);
			LOG.info(ap + " needs " + ap.getCyclesRemaining() + " cycles to finish");
			this.activeProcess.remove(ap);
			if (ap.getCyclesRemaining() > 0) {
				this.queue.insert(ap);
			}
		}
		final CPUSchedulableProcess np = this.queue.first();
		if (np != null) {
			LOG.info("Activating process " + np + " with " + np.getCyclesRemaining() + " remaining cycles");
			this.queue.remove(np);
			this.activeProcess.insert(np);
			final long cyclesRemaining = np.getCyclesRemaining();
			if (cyclesRemaining > this.cyclesPerSlice) {
				ret = this.getTickSimTime();
				this.queue.insert(np);
				LOG.info("Next slice will be started at " + ret);
			} else {
				ret = new SimTime(cyclesRemaining / (double) (this.getOwner().getCapacity() * Constants.CPU_SCALE)); // MHz
				// => divide by 1Mega
				LOG.info("Finishing job in " + ret);
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

	@Override
	public void resumeBusinessMonitoringAt(final SimTime t) {
		// log.warn("resuming buisiness logging of "
		// + this.getName() + " " + t.getTimeValue());
		this.utilizationTicker.resumeAt(t);
	}

	@Override
	public final void pauseBusinessMonitoring() {
		// log.warn("pausing buisiness logging of "
		// + this.getName() + " "
		// + this.getModel().currentTime().getTimeValue());
		this.utilizationTicker.pause();
	}
}
