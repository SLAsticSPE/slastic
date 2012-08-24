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

package org.trustsoft.slastic.plugins.slachecker.monitoring.kieker;

import org.trustsoft.slastic.common.event.IObservationEvent;

import kieker.common.record.IMonitoringRecord;

/**
 * 
 * @author Andre van Hoorn
 */
public class KiekerMeasurementEvent implements IObservationEvent {

	private static final long serialVersionUID = 8766L;

	private final IMonitoringRecord kiekerRecord;

	public final IMonitoringRecord getKiekerRecord() {
		return this.kiekerRecord;
	}

	public KiekerMeasurementEvent(final IMonitoringRecord kiekerRecord) {
		this.kiekerRecord = kiekerRecord;
	}
}
