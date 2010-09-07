package org.trustsoft.slastic.simulation.software.statistics;

import kieker.common.record.AbstractMonitoringRecord;

public class ActiveUsersRecord extends AbstractMonitoringRecord {

	/**
	 * 
	 */
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

	@SuppressWarnings("rawtypes")
	@Override
	public Class[] getValueTypes() {
		return new Class<?>[] { Integer.class, Long.class };
	}

}
