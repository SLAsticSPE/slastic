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

package org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.monitoringRecord.sla;

import kieker.common.record.AbstractMonitoringRecord;

/**
 * @author Andre van Hoorn
 */
// TODO: change to immutable type
public class SLOMonitoringRecord extends AbstractMonitoringRecord {

	private static final long serialVersionUID = 1113L;
	private static int NUM_RECORD_FIELDS = 6;

	public volatile long timestamp = -1;
	public volatile int serviceId = -1;
	public volatile String componentName = null;
	public volatile String operationName = null;
	public volatile String host = null;
	public volatile long rtNseconds = -1;
	public volatile Object retVal = null;

	public Class<?>[] getValueTypes() {
		return new Class[] {
			long.class, // timestamp
			int.class, // serviceId
			String.class, // componentName
			String.class, // operationName
			String.class, // host
			long.class // rtNseconds
		};
	}

	public void initFromArray(final Object[] values) throws IllegalArgumentException {
		try {
			if (values.length != NUM_RECORD_FIELDS) {
				throw new IllegalArgumentException("Expecting vector with " + NUM_RECORD_FIELDS + " elements but found:" + values.length);
			}
			this.timestamp = (Long) values[0];
			this.serviceId = (Integer) values[1];
			this.componentName = (String) values[2];
			this.operationName = (String) values[3];
			this.host = (String) values[4];
			this.rtNseconds = (Long) values[5];
		} catch (final Exception exc) {
			throw new IllegalArgumentException("Failed to init", exc);
		}
		return;
	}

	public Object[] toArray() {
		return new Object[] {
			this.timestamp,
			this.serviceId,
			this.componentName,
			this.operationName,
			this.host,
			this.rtNseconds
		};
	}

	public SLOMonitoringRecord() {}

	public SLOMonitoringRecord(final String componentName,
			final String operationName, final String host) {
		this.componentName = componentName;
		this.operationName = operationName;
		this.host = host;
	}

	public int compareTo(final SLOMonitoringRecord slo) {
		if (slo == this) {
			return 0;
		} else if (this.rtNseconds > slo.rtNseconds) {
			return 1;
		} else {
			return -1;
		}

	}
}
