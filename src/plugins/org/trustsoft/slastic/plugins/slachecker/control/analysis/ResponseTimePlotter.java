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

package org.trustsoft.slastic.plugins.slachecker.control.analysis;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.monitoringRecord.sla.SLOMonitoringRecord;

import kieker.common.record.IMonitoringRecord;

/**
 * TODO: Is this guy used at all?
 * 
 * @author Andre van Hoorn
 */
public class ResponseTimePlotter {

	private static final Log LOG = LogFactory.getLog(ResponseTimePlotter.class);

	private final static Collection<Class<? extends IMonitoringRecord>> recordTypeSubscriptionList = new ArrayList<Class<? extends IMonitoringRecord>>();
	static {
		recordTypeSubscriptionList.add(SLOMonitoringRecord.class);
	}

	public Collection<Class<? extends IMonitoringRecord>> getRecordTypeSubscriptionList() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public boolean newMonitoringRecord(final IMonitoringRecord record) {
		if (record instanceof SLOMonitoringRecord) {
			final SLOMonitoringRecord rec = (SLOMonitoringRecord) record;
			LOG.info(rec.componentName + "." + rec.operationName + ":"
					+ rec.rtNseconds + "ns = " + (rec.rtNseconds / (1000 * 1000))
					+ "ms" + " @ timestamp " + rec.timestamp);
		} else {
			LOG.error("Can only consume records of type KiekerExecutionRecord" + " but passed record is of type " + record.getClass().getName());
		}
		return true;
	}

	public boolean execute() {
		/* We don't need to prepare */
		return true;
	}

	public void terminate(final boolean error) {
		/* No actions required */
	}
}
