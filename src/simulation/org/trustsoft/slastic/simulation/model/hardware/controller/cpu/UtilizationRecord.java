package org.trustsoft.slastic.simulation.model.hardware.controller.cpu;

import kieker.common.record.AbstractMonitoringRecord;

@SuppressWarnings( { "serial", "unchecked" })
public class UtilizationRecord extends AbstractMonitoringRecord {

	private long time;
	private double utilization;
	private final Class types[] = { Long.class, Double.class };

	@Override
	public Class[] getValueTypes() {
		return this.types;
	}

	@Override
	public void initFromArray(final Object[] values) {
		this.time = (Long) values[0];
		this.utilization = (Double) values[1];
	}

	@Override
	public Object[] toArray() {
		return new Object[] { this.time, this.utilization };
	}

}
