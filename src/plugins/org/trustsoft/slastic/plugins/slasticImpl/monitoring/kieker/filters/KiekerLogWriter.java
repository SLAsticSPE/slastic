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

package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters;

import kieker.common.configuration.Configuration;
import kieker.common.record.IMonitoringRecord;
import kieker.monitoring.core.IMonitoringRecordReceiver;
import kieker.monitoring.core.configuration.ConfigurationFactory;
import kieker.monitoring.core.controller.IMonitoringController;
import kieker.monitoring.core.controller.MonitoringController;
import kieker.monitoring.writer.filesystem.AbstractAsyncFSWriter;
import kieker.monitoring.writer.filesystem.AsyncFsWriter;
import kieker.tools.logReplayer.MonitoringRecordLoggerFilter;

/**
 * TODO: Replace by {@link MonitoringRecordLoggerFilter}?
 * 
 * @author Andre van Hoorn
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
				ConfigurationFactory.createDefaultConfiguration();

		/* Configuring asynchronous file system writer */
		configuration.setProperty(ConfigurationFactory.WRITER_CLASSNAME, AsyncFsWriter.class.getName());
		// Custom storage path
		configuration.setProperty(AbstractAsyncFSWriter.CONFIG_PATH, storagePath);
		configuration.setProperty(AbstractAsyncFSWriter.CONFIG_TEMP, Boolean.toString(false));
		// Block on full queue
		configuration.setProperty(AsyncFsWriter.class.getName() + ".QueueFullBehavior", Integer.toString(1));
		// Enable "replay mode", i.e., the logging timestamps in the records are kept as-is
		configuration.setProperty(ConfigurationFactory.AUTO_SET_LOGGINGTSTAMP, Boolean.toString(false));
		// Set controller name
		configuration.setProperty(ConfigurationFactory.CONTROLLER_NAME, "KiekerLogWriter");

		this.monitoringController = MonitoringController.createInstance(configuration);
	}

	@Override
	public boolean newMonitoringRecord(final IMonitoringRecord record) {
		return this.monitoringController.newMonitoringRecord(record);
	}
}
