package org.trustsoft.slastic.simulation.software.statistics;

import kieker.common.record.AbstractMonitoringRecord;

public class ComponentUsersRecord extends AbstractMonitoringRecord {

	/**
	 * 
	 */
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

	@SuppressWarnings("rawtypes")
	@Override
	public Class[] getValueTypes() {
		return new Class[] { String.class, String.class, Integer.class,
				Long.class };
	}

}
