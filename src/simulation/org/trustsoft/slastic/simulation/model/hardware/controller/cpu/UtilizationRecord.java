package org.trustsoft.slastic.simulation.model.hardware.controller.cpu;

import kieker.common.record.AbstractMonitoringRecord;

@SuppressWarnings({ "serial", "unchecked" })
public class UtilizationRecord extends AbstractMonitoringRecord {

	private long time;
	private double utilization;
	@SuppressWarnings("rawtypes")
	private static final Class types[] = { Long.class, Double.class,
			String.class };
	private String server;

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
