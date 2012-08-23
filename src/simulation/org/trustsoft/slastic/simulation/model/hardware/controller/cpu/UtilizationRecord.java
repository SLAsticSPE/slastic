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

package org.trustsoft.slastic.simulation.model.hardware.controller.cpu;

import kieker.common.record.AbstractMonitoringRecord;

/**
 * 
 * @author Robert von Massow
 * 
 */
// TODO: Change to immutable type or use Kieker record directly?
@SuppressWarnings({ "serial", "unchecked" })
public class UtilizationRecord extends AbstractMonitoringRecord {

	private volatile long time;
	private volatile double utilization;
	@SuppressWarnings("rawtypes")
	private static final Class types[] = { Long.class, Double.class, String.class };
	private volatile String server;

	@SuppressWarnings("rawtypes")
	@Override
	public Class[] getValueTypes() {
		return UtilizationRecord.types;
	}

	@Override
	public void initFromArray(final Object[] values) {
		this.time = (Long) values[0];
		this.utilization = (Double) values[1];
		this.server = (String) values[2];
	}

	@Override
	public Object[] toArray() {
		return new Object[] { this.time, this.utilization, this.server };
	}

	/**
	 * @return the time
	 */
	public final long getTime() {
		return this.time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public final void setTime(final long time) {
		this.time = time;
	}

	/**
	 * @return the utilization
	 */
	public final double getUtilization() {
		return this.utilization;
	}

	/**
	 * @param utilization
	 *            the utilization to set
	 */
	public final void setUtilization(final double utilization) {
		this.utilization = utilization;
	}

	public void setServer(final String server) {
		this.server = server;
	}

	public String getServer() {
		return this.server;
	}

}
