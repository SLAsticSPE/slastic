package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters;

import kieker.common.record.IMonitoringRecord;
import kieker.common.record.IMonitoringRecordReceiver;
import kieker.monitoring.core.MonitoringController;
import kieker.monitoring.core.configuration.MonitoringConfiguration;

/**
 * 
 * @author André van Hoorn
 * 
 */
public class KiekerLogWriter implements IMonitoringRecordReceiver {

	private final MonitoringController monitoringController;

	public KiekerLogWriter() {
		this.monitoringController = MonitoringController.getInstance();
	}

	/**
	 * @param storagePath
	 */
	public KiekerLogWriter(final String storagePath) {
		final MonitoringConfiguration config =
				MonitoringConfiguration
						.createDefaultConfigurationAsyncFSWriter(
								"KiekerLogWriter",
								storagePath,
								MonitoringConfiguration.DEFAULT_ASYNC_RECORD_QUEUE_SIZE,
								true /* blockOnFullQueue */);
		this.monitoringController = new MonitoringController(config);
		this.monitoringController.enableReplayMode();
	}

	@Override
	public boolean newMonitoringRecord(final IMonitoringRecord record) {
		return this.monitoringController.newMonitoringRecord(record);
	}
}
