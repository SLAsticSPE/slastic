package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker;

import java.util.Collection;

import kieker.analysis.plugin.IMonitoringRecordConsumerPlugin;
import kieker.analysis.reader.namedRecordPipe.PipeReader;
import kieker.common.record.IMonitoringRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.KiekerMeasurementEvent;

/**
 * Wraps incoming {@link IMonitoringRecord}s as {@link KiekerMeasurementEvent}s
 * and delegates it to the analysis controller.
 * 
 * As it extends {@link AbstractKiekerMonitoringManager}, it uses a
 * {@link PipeReader} internally to connect to a Kieker monitoring instance. See
 * {@link AbstractKiekerMonitoringManager} and
 * {@link AbstractKiekerMonitoringManager#init()} for details on how to set the
 * pipe name.
 * 
 * TODO: Is this class required anywhere?
 * 
 * @author Andre van Hoorn
 */
public class KiekerRecordDelegationMonitoringManager extends
		AbstractKiekerMonitoringManager {

	private static final Log log = LogFactory
			.getLog(KiekerRecordDelegationMonitoringManager.class);

	@Override
	protected IMonitoringRecordConsumerPlugin getMonitoringRecordConsumer() {
		return new IMonitoringRecordConsumerPlugin() {

			@Override
			public boolean newMonitoringRecord(final IMonitoringRecord record) {
				try {
					// simply forward (wrapped) records to controller
					KiekerRecordDelegationMonitoringManager.this
							.getController().getMonitoringClientPort()
							.newObservation(new KiekerMeasurementEvent(record));
				} catch (final Exception exc) {
					KiekerRecordDelegationMonitoringManager.log.error(
							"Failed to forward record", exc);
					return false;
				}
				return true;
			}

			@Override
			public Collection<Class<? extends IMonitoringRecord>> getRecordTypeSubscriptionList() {
				return null; // receive events of any type
			}

			@Override
			public boolean execute() {
				return true;
			}

			@Override
			public void terminate(final boolean error) {
			}
		};
	}

	@Override
	protected boolean concreteExecute() {
		return true; // do nothing here
	}

	@Override
	protected void concreteTerminate(final boolean error) {
		KiekerRecordDelegationMonitoringManager.log
		.info("KiekerMonitoringManager now terminating");
	}
}
