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

package org.trustsoft.slastic.simulation.software.statistics;

import kieker.common.record.AbstractMonitoringRecord;

/**
 * 
 * @author Robert von Massow
 * 
 */
// TODO: Change to immutable
public class ComponentUsersRecord extends AbstractMonitoringRecord {
	private static final long serialVersionUID = -9016470099398855647L;
	private String component;
	private String host;
	private int users;
	private long monTime;

	public ComponentUsersRecord(final String component, final String host,
			final int users, final long monTime) {
		this.component = component;
		this.host = host;
		this.users = users;
		this.monTime = monTime;
	}

	@Override
	public void initFromArray(final Object[] values) {
		this.component = (String) values[0];
		this.host = (String) values[1];
		this.users = (Integer) values[2];
		this.monTime = (Long) values[3];

	}

	@Override
	public Object[] toArray() {
		return new Object[] { this.component, this.host, this.users,
			this.monTime };
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Class[] getValueTypes() {
		return new Class[] { String.class, String.class, Integer.class,
			Long.class };
	}

}
