package org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning.utilizationBased;

public class OverallCPUUtilizationEvent {
	private final long timestampMillis;
	private final double overallCPUUtilization;

	public OverallCPUUtilizationEvent(final long timestampMillis,
			final double overallCPUUtilization) {
		this.timestampMillis = timestampMillis;
		this.overallCPUUtilization = overallCPUUtilization;
	}

	public final long getTimestampMillis() {
		return this.timestampMillis;
	}

	public final double getOverallCPUUtilization() {
		return this.overallCPUUtilization;
	}

	@Override
	public String toString() {
		final StringBuilder strB = new StringBuilder();
		strB.append("timestampMillis: ").append(this.timestampMillis);
		strB.append("; overallCPUUtilization: ").append(this.overallCPUUtilization);
		return strB.toString();
	}
}