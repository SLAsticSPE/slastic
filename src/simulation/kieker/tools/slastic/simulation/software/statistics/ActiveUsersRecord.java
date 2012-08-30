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

package kieker.tools.slastic.simulation.software.statistics;

import kieker.common.record.AbstractMonitoringRecord;

/**
 * 
 * @author Robert von Massow
 * 
 */
// TODO: Change to immutable
public class ActiveUsersRecord extends AbstractMonitoringRecord {
	private static final long serialVersionUID = -9181771892272376259L;

	private long time;
	private int users;

	public ActiveUsersRecord(final int users, final long time) {
		this.users = users;
		this.time = time;
	}

	@Override
	public void initFromArray(final Object[] values) {
		this.time = (Long) values[0];
		this.users = (Integer) values[1];
	}

	@Override
	public Object[] toArray() {
		return new Object[] { this.time, this.users };
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Class[] getValueTypes() {
		return new Class<?>[] { Integer.class, Long.class };
	}

}
