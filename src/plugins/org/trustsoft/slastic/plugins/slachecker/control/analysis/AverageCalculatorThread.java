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

/**
 * 
 */
package org.trustsoft.slastic.plugins.slachecker.control.analysis;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.monitoringRecord.sla.SLOMonitoringRecord;

/**
 * @author Lena
 * 
 */
public class AverageCalculatorThread extends Thread {

	private static final Log LOG = LogFactory.getLog(AverageCalculatorThread.class);

	private final BlockingQueue<SLOMonitoringRecord> q;
	private long averageResponseTime = -1;
	private final AtomicBoolean terminateRequestPending = new AtomicBoolean(false);

	public AverageCalculatorThread(final BlockingQueue<SLOMonitoringRecord> q) {
		LOG.info("New AverageCalculatorThread created");
		this.q = q;
	}

	@Override
	public void run() {
		System.out.println("START vom RecordConsumerThread");
		while (true) {
			try {
				synchronized (this) {
					this.wait();
				}
				if (!(this.q.size() > 0)) {
					this.averageResponseTime = -1;
					continue;
				}
				System.out.println("updating......................." + this.q.size() + "......");
				long summedTimes = 0;
				synchronized (this.q) {
					for (final SLOMonitoringRecord slo : this.q) {
						summedTimes += (slo.rtNseconds);
					}
					this.averageResponseTime = (summedTimes / this.q.size());
				}
			} catch (final InterruptedException ex) {
				if (this.terminateRequestPending.get()) {
					LOG.info("Shutting down");
				} else {
					LOG.error("Interrupted", ex);
				}
			}
		}
	}

	public long getAverage() {
		synchronized (this) {
			this.notify();
		}
		return this.averageResponseTime;
	}

	public void terminate() {
		this.terminateRequestPending.set(true);
		synchronized (this) {
			this.notifyAll();
		}
	}
}
