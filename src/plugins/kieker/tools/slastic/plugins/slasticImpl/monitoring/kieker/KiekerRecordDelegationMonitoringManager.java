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

package kieker.tools.slastic.plugins.slasticImpl.monitoring.kieker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.analysis.AnalysisController;
import kieker.analysis.exception.AnalysisConfigurationException;
import kieker.analysis.plugin.annotation.InputPort;
import kieker.analysis.plugin.filter.AbstractFilterPlugin;
import kieker.analysis.plugin.reader.AbstractReaderPlugin;
import kieker.analysis.plugin.reader.namedRecordPipe.PipeReader;
import kieker.common.configuration.Configuration;
import kieker.common.record.IMonitoringRecord;
import kieker.tools.slastic.control.AbstractControlComponent;
import kieker.tools.slastic.plugins.slachecker.monitoring.kieker.KiekerMeasurementEvent;

/**
 * Wraps incoming {@link IMonitoringRecord}s as {@link KiekerMeasurementEvent}s
 * and delegates it to the analysis controller.
 * 
 * As it extends {@link AbstractKiekerMonitoringManager}, it uses a {@link PipeReader} internally to connect to a Kieker monitoring instance. See
 * {@link AbstractKiekerMonitoringManager} and {@link AbstractKiekerMonitoringManager#init()} for details on how to set the
 * pipe name.
 * 
 * TODO: Is this class required anywhere?
 * 
 * @author Andre van Hoorn
 */
@Deprecated
public class KiekerRecordDelegationMonitoringManager extends AbstractKiekerMonitoringManager {

	private static final Log LOG = LogFactory.getLog(KiekerRecordDelegationMonitoringManager.class);

	@Override
	protected boolean concreteExecute() {
		return true; // do nothing here
	}

	@Override
	protected void concreteTerminate(final boolean error) {
		LOG.info("KiekerMonitoringManager now terminating");
	}

	@Override
	protected boolean refineAnalysisConfiguration(final AnalysisController analysisController, final AbstractReaderPlugin reader, final String readerOutputPortName)
			throws IllegalStateException, AnalysisConfigurationException {
		final KiekerRecordDelegationFilter delegationFilter = new KiekerRecordDelegationFilter(new Configuration(), this.getController());
		analysisController.registerFilter(delegationFilter);
		analysisController.connect(reader, readerOutputPortName, delegationFilter, KiekerRecordDelegationFilter.INPUT_PORT_NAME_TIMER_EVENTS_NANOS);
		return true;
	}

	/**
	 * Wraps incoming {@link IMonitoringRecord}s into a {@link KiekerMeasurementEvent} and passes it to the
	 * {@link AbstractControlComponent#getMonitoringClientPort()}.
	 * 
	 * @author Andre van Hoorn
	 * 
	 */
	class KiekerRecordDelegationFilter extends AbstractFilterPlugin {
		public static final String INPUT_PORT_NAME_TIMER_EVENTS_NANOS = "records";

		private final AbstractControlComponent controlComponent;

		public KiekerRecordDelegationFilter(final Configuration configuration, final AbstractControlComponent controlComponent) {
			super(configuration);
			this.controlComponent = controlComponent;
		}

		@InputPort(name = INPUT_PORT_NAME_TIMER_EVENTS_NANOS, eventTypes = { IMonitoringRecord.class })
		public void inputTimerEventNanos(final IMonitoringRecord record) {
			this.controlComponent.getMonitoringClientPort().newObservation(new KiekerMeasurementEvent(record));
		}

		@Override
		public Configuration getCurrentConfiguration() {
			return new Configuration();
		}
	}
}
