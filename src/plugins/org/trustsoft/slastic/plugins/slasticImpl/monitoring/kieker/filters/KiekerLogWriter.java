package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters;

import kieker.common.record.IMonitoringRecord;
import kieker.common.record.IMonitoringRecordReceiver;
import kieker.monitoring.core.configuration.Configuration;
import kieker.monitoring.core.controller.IMonitoringController;
import kieker.monitoring.core.controller.MonitoringController;
import kieker.monitoring.writer.filesystem.AsyncFsWriter;

/**
 * 
 * @author André van Hoorn
 * 
 */
public class KiekerLogWriter implements IMonitoringRecordReceiver {

	private final IMonitoringController monitoringController;

	public KiekerLogWriter() {
		this.monitoringController = MonitoringController.getInstance();
	}

	/**
	 * @param storagePath
	 */
	public KiekerLogWriter(final String storagePath) {
		final Configuration configuration = 
			Configuration.createDefaultConfiguration();
		
		/* Configuring asynchronous file system writer */
		configuration.setProperty(Configuration.WRITER_CLASSNAME, AsyncFsWriter.class.getName());
		// Custom storage path
		configuration.setProperty(AsyncFsWriter.CONFIG_PATH, storagePath);
		configuration.setProperty(AsyncFsWriter.CONFIG_TEMP, Boolean.toString(false));
		// Block on full queue
		configuration.setProperty(AsyncFsWriter.class.getName()+".QueueFullBehavior", Integer.toString(1));
		// Enable "replay mode", i.e., the logging timestamps in the records are kept as-is
		configuration.setProperty(Configuration.AUTO_SET_LOGGINGTSTAMP, Boolean.toString(false));
		// Set controller name
		configuration.setProperty(Configuration.CONTROLLER_NAME, "KiekerLogWriter");
		
		this.monitoringController = MonitoringController.createInstance(configuration);
	}

	@Override
	public boolean newMonitoringRecord(final IMonitoringRecord record) {
		return this.monitoringController.newMonitoringRecord(record);
	}
}
