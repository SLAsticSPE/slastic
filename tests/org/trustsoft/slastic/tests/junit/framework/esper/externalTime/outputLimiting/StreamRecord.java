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

package org.trustsoft.slastic.tests.junit.framework.esper.externalTime.outputLimiting;

import java.util.Arrays;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
class StreamRecord {
	/**
	 * The time when the row was observed.
	 */
	final long timestamp;

	/**
	 * Returns the timestamp.
	 * 
	 * @return
	 */
	public long getTimestamp() {
		return this.timestamp;
	}

	/**
	 * Returns the row.
	 * 
	 * @return
	 */
	public Object[] getRow() {
		return this.row;
	}

	/**
	 * The observed row.
	 */
	final Object[] row;

	/**
	 * Must not be used for construction.
	 */
	@SuppressWarnings("unused")
	private StreamRecord() {
		this.timestamp = -1;
		this.row = null;
	}

	public StreamRecord(final long timestamp, final Object[] row) {
		this.timestamp = timestamp;
		this.row = row;
	}

	@Override
	public boolean equals(final Object arg0) {
		if (this == arg0) {
			return true;
		}

		if (!(arg0 instanceof StreamRecord)) {
			return false;
		}

		final StreamRecord other = (StreamRecord) arg0;

		return (this.timestamp == other.timestamp) && Arrays.equals(this.row, other.row);
	}

	@Override
	public String toString() {
		return "{" + this.timestamp + ":" + Arrays.toString(this.row) + "}";
	}
}
