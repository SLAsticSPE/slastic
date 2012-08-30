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

package org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning.workloadIntensityBased;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class WorkloadIntensityEvent {
	private final long timestampMillis;
	private final long workloadIntensity;

	/**
	 * @param timestampMillis
	 * @param workloadIntensity
	 */
	public WorkloadIntensityEvent(final long timestampMillis, final long workloadIntensity) {
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
