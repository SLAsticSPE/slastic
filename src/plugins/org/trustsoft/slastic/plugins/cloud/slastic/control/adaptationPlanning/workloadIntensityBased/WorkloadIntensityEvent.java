package org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning.workloadIntensityBased;

public class WorkloadIntensityEvent {
	private final long timestampMillis;
	private final long workloadIntensity;

	/**
	 * @param timestampMillis
	 * @param workloadIntensity
	 */
	public WorkloadIntensityEvent(final long timestampMillis,
			final long workloadIntensity) {
		this.timestampMillis = timestampMillis;
		this.workloadIntensity = workloadIntensity;
	}

	/**
	 * @return the timestampMillis
	 */
	public final long getTimestampMillis() {
		return this.timestampMillis;
	}

	/**
	 * @return the workloadIntensity
	 */
	public final long getWorkloadIntensity() {
		return this.workloadIntensity;
	}

	@Override
	public String toString() {
		final StringBuilder strB = new StringBuilder();
		strB.append("timestampMillis: ").append(this.timestampMillis);
		strB.append("; workloadIntensity: ").append(this.workloadIntensity);
		return strB.toString();
	}
}