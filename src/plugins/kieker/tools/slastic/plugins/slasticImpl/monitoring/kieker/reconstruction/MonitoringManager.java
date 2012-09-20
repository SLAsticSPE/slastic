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

package kieker.tools.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.analysis.AnalysisController;
import kieker.analysis.exception.AnalysisConfigurationException;
import kieker.analysis.plugin.AbstractPlugin;
import kieker.analysis.plugin.reader.AbstractReaderPlugin;
import kieker.common.configuration.Configuration;
import kieker.monitoring.core.configuration.ConfigurationFactory;
import kieker.monitoring.writer.filesystem.AbstractAsyncFSWriter;
import kieker.monitoring.writer.filesystem.AsyncFsWriter;
import kieker.tools.logReplayer.MonitoringRecordLoggerFilter;
import kieker.tools.slastic.metamodel.monitoring.OperationExecution;
import kieker.tools.slastic.plugins.slasticImpl.ModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.NameUtils;
import kieker.tools.slastic.plugins.slasticImpl.monitoring.kieker.AbstractKiekerMonitoringManager;
import kieker.tools.slastic.plugins.slasticImpl.monitoring.kieker.filters.MonitoringRecordConsumerFilterChain;

/**
 * 
 * @author Andre van Hoorn
 */
public class MonitoringManager extends AbstractKiekerMonitoringManager {

	private static final Log LOG = LogFactory.getLog(MonitoringManager.class);

	/**
	 * Delegates all Kieker framework events including records to the {@link #execRecordTransformation} filter and sends the resulting {@link OperationExecution}s to
	 * the {@link #getController()}.
	 */
	private final MonitoringRecordConsumerFilterChain monitoringRecordConsumerFilterChain = new MonitoringRecordConsumerFilterChain(new Configuration());

	private volatile ExecutionRecordTransformationFilter execRecordTransformation;
	private volatile ResourceUtilizationRecordTransformationFilter resourceUtilizationRecordTransformation;
	private volatile CPUUtilizationRecordTransformationFilter cpuUtilizationRecordTransformationFilter;
	private volatile MemSwapUsageRecordTransformationFilter memSwapUsageRecordTransformationFilter;

	private static final String PROP_NAME_KIEKER_TEE_WRITER_ENABLED = "kiekerTeeWriterEnabled";

	private static final String PROP_NAME_COMPONENT_DISCOVERY_HIERARCHY_LEVEL = "component-discovery-level";
	private static final String PROP_VAL_COMPONENT_DISCOVERY_LEVEL_CLASS = "CLASS";
	private static final String PROP_VAL_COMPONENT_DISCOVERY_LEVEL_PACKAGE_STRICT = "PACKAGE_STRICT";

	/** Will be initialized in {@link #init()} */
	private volatile int componentDiscoveryHierarchyLevel;

	/** Will be initialized in {@link #init()} */
	private volatile boolean kiekerTeeWriterEnabled = true;

	/**
	 * Matches the given String representation of the property value for {@value #PROP_NAME_COMPONENT_DISCOVERY_MODE} to the corresponding value
	 * {@link ExecutionRecordTransformationFilter.ComponentDiscoveryMode} and
	 * sets the private {@link #componentDiscoveryMode} field. If the given
	 * value is null, the default mode {@link ExecutionRecordTransformationFilter.ComponentDiscoveryMode#CLASS_NAME} will be used.
	 * 
	 * @return true on success; false otherwise
	 */
	private boolean initDiscoveryMode() {
		final String discoveryLevelPropValStr =
				super.getInitProperty(PROP_NAME_COMPONENT_DISCOVERY_HIERARCHY_LEVEL, PROP_VAL_COMPONENT_DISCOVERY_LEVEL_CLASS);

		boolean success = true;

		if (discoveryLevelPropValStr.equals(PROP_VAL_COMPONENT_DISCOVERY_LEVEL_CLASS)) {
			this.componentDiscoveryHierarchyLevel = NameUtils.ABSTRACTION_MODE_CLASS;
		} else if (discoveryLevelPropValStr.equals(PROP_VAL_COMPONENT_DISCOVERY_LEVEL_PACKAGE_STRICT)) {
			this.componentDiscoveryHierarchyLevel = NameUtils.ABSTRACTION_MODE_PACKAGE_STRICT;
		} else {
			try {
				this.componentDiscoveryHierarchyLevel = Integer.parseInt(discoveryLevelPropValStr);
			} catch (final NumberFormatException exc) {
				LOG.error("Invalid property value for " + PROP_NAME_COMPONENT_DISCOVERY_HIERARCHY_LEVEL + ": " + discoveryLevelPropValStr, exc);
				success = false;
			}
		}

		return success;
	}

	@Override
	public boolean init() {
		final boolean success = super.init() && this.initDiscoveryMode();
		this.kiekerTeeWriterEnabled =
				Boolean.parseBoolean(super.getInitProperty(PROP_NAME_KIEKER_TEE_WRITER_ENABLED, Boolean.toString(this.kiekerTeeWriterEnabled)));

		return success;
	}

	@Override
	protected boolean concreteExecute() {
		return true;
	}

	@Override
	protected void concreteTerminate(final boolean error) {
		LOG.debug(MonitoringManager.class + " now terminating");
	}

	private boolean writeReplayControllerConfiguration(final String configurationFn, final String storagePath) {
		final Configuration configuration = ConfigurationFactory.createDefaultConfiguration();

		/* Configuring asynchronous file system writer */
		configuration.setProperty(ConfigurationFactory.WRITER_CLASSNAME, AsyncFsWriter.class.getName());
		// Custom storage path
		configuration.setProperty(AsyncFsWriter.class.getName() + "." + AbstractAsyncFSWriter.CONFIG_PATH, storagePath);
		configuration.setProperty(AsyncFsWriter.class.getName() + "." + AbstractAsyncFSWriter.CONFIG_TEMP, Boolean.toString(false));
		// Block on full queue
		configuration.setProperty(AsyncFsWriter.class.getName() + ".QueueFullBehavior", Integer.toString(1));

		configuration.setProperty(ConfigurationFactory.AUTO_SET_LOGGINGTSTAMP, Boolean.toString(false));
		// Set controller name
		configuration.setProperty(ConfigurationFactory.CONTROLLER_NAME, "MonitoringManagerTee");

		try {
			final OutputStream os = new FileOutputStream(configurationFn);
			configuration.store(os, "Generated by " + MonitoringManager.class.getName());
		} catch (final Exception e) {
			LOG.error("Failed to create monitoring configuration file " + configurationFn, e);
			return false;
		}
		return true;
	}

	@Override
	protected boolean refineAnalysisConfiguration(final AnalysisController analysisController, final AbstractReaderPlugin reader, final String readerOutputPortName)
			throws IllegalStateException, AnalysisConfigurationException {
		/* Initialize filter chain for incoming records */

		/* Actual filters: */
		this.monitoringRecordConsumerFilterChain.setControlComponent(this.getController());

		final ModelManager modelManager = (ModelManager) this.getController().getModelManager();

		this.execRecordTransformation = new ExecutionRecordTransformationFilter(modelManager, this.componentDiscoveryHierarchyLevel);
		this.resourceUtilizationRecordTransformation = new ResourceUtilizationRecordTransformationFilter(modelManager);
		this.cpuUtilizationRecordTransformationFilter = new CPUUtilizationRecordTransformationFilter(modelManager);
		this.memSwapUsageRecordTransformationFilter = new MemSwapUsageRecordTransformationFilter(modelManager);

		this.monitoringRecordConsumerFilterChain.addSynchronousFilter(this.execRecordTransformation);
		this.monitoringRecordConsumerFilterChain.addSynchronousFilter(this.resourceUtilizationRecordTransformation);
		this.monitoringRecordConsumerFilterChain.addSynchronousFilter(this.cpuUtilizationRecordTransformationFilter);
		this.monitoringRecordConsumerFilterChain.addSynchronousFilter(this.memSwapUsageRecordTransformationFilter);

		analysisController.registerFilter(this.monitoringRecordConsumerFilterChain);

		AbstractPlugin lastPlugin = reader;
		String lastOutputPortName = readerOutputPortName;

		if (!this.kiekerTeeWriterEnabled) {
			LOG.info("Kieker tee writer DISabled");
			// lastPlugin and lastOutputPortName remain unchanged
		} else {
			LOG.info("Kieker tee writer ENabled");

			/* Tee writer */
			final String teeFilterControllerConfigFn = this.getComponentContext().getDirectoryLocation() + "/teemonitoring.properties";
			if (!this.writeReplayControllerConfiguration(teeFilterControllerConfigFn, this.getComponentContext().getDirectoryLocation())) {
				LOG.error("Failed to create monitoring.properties for TeeFilter");
				return false;
			}

			final Configuration monitoringRecordLoggerFilterConfig = new Configuration();
			monitoringRecordLoggerFilterConfig.setProperty(ConfigurationFactory.AUTO_SET_LOGGINGTSTAMP, Boolean.FALSE.toString());
			monitoringRecordLoggerFilterConfig.setProperty(MonitoringRecordLoggerFilter.CONFIG_PROPERTY_NAME_MONITORING_PROPS_FN, teeFilterControllerConfigFn);
			final MonitoringRecordLoggerFilter monitoringRecordLoggerFilter = new MonitoringRecordLoggerFilter(monitoringRecordLoggerFilterConfig);
			analysisController.registerFilter(monitoringRecordLoggerFilter);
			analysisController.connect(reader, readerOutputPortName, monitoringRecordLoggerFilter, MonitoringRecordLoggerFilter.INPUT_PORT_NAME_RECORD);
			lastPlugin = monitoringRecordLoggerFilter;
			lastOutputPortName = MonitoringRecordLoggerFilter.OUTPUT_PORT_NAME_RELAYED_EVENTS;
		}

		analysisController.connect(lastPlugin, lastOutputPortName,
				this.monitoringRecordConsumerFilterChain, MonitoringRecordConsumerFilterChain.INPUT_PORT_NAME_RECORDS);
		return true;
	}
}
