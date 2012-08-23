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

    private static final Log log = LogFactory.getLog(AverageCalculatorThread.class);
    private final BlockingQueue<SLOMonitoringRecord> q;
    private long averageResponseTime = -1;
    private AtomicBoolean terminateRequestPending = new AtomicBoolean(false);

    public AverageCalculatorThread(BlockingQueue<SLOMonitoringRecord> q) {
        log.info("New AverageCalculatorThread created");
        this.q = q;
    }

    public void run() {
        System.out.println("START vom RecordConsumerThread");
        while (true) {
            try {
                synchronized (this) {
                    this.wait();
                }
                if (!(q.size() > 0)) {
                    this.averageResponseTime = -1;
                    continue;
                }
                System.out.println("updating......................."+this.q.size()+"......");
                long summedTimes = 0;
                synchronized (q) {
                    for (SLOMonitoringRecord slo : q) {
                        summedTimes += (slo.rtNseconds);
                    }
                    this.averageResponseTime = (summedTimes / q.size());
                }
            } catch (InterruptedException ex) {
                if (this.terminateRequestPending.get()) {
                    log.info("Shutting down");
                } else {
                    log.error("Interrupted", ex);
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
