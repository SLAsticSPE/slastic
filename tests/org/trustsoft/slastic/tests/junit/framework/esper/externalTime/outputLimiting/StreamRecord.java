package org.trustsoft.slastic.tests.junit.framework.esper.externalTime.outputLimiting;

import java.util.Arrays;

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

		return (this.timestamp == other.timestamp)
				&& Arrays.equals(this.row, other.row);
	}

	@Override
	public String toString() {
		return "{" + this.timestamp + ":" + Arrays.toString(this.row) + "}";
	}
}