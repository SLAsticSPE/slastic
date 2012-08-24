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

package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.IComponentContext;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.AbstractKiekerMonitoringManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters.KiekerLogWriter;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters.MonitoringRecordConsumerFilterChain;

import de.cau.se.slastic.metamodel.monitoring.OperationExecution;

import kieker.analysis.AnalysisController;
import kieker.analysis.exception.AnalysisConfigurationException;
import kieker.analysis.plugin.reader.AbstractReaderPlugin;
import kieker.common.configuration.Configuration;

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

	private static final String PROP_NAME_COMPONENT_DISCOVERY_HIERARCHY_LEVEL = "component-discovery-level";
	private static final String PROP_VAL_COMPONENT_DISCOVERY_LEVEL_CLASS = "CLASS";
	private static final String PROP_VAL_COMPONENT_DISCOVERY_LEVEL_PACKAGE_STRICT = "PACKAGE_STRICT";

	/** Will be initialized in {@link #init()} */
	private volatile int componentDiscoveryHierarchyLevel;

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

	@Override
	protected boolean refineAnalysisConfiguration(final AnalysisController analysisController, final AbstractReaderPlugin reader, final String readerOutputPortName)
			throws IllegalStateException, AnalysisConfigurationException {
		/* Initialize filter chain for incoming records */

		/* Tee writer */
		final IComponentContext logWriterContext = this.getComponentContext().createSubcontext(KiekerLogWriter.class.getSimpleName());
		final KiekerLogWriter logWriter = new KiekerLogWriter(logWriterContext.getDirectoryLocation());
		this.monitoringRecordConsumerFilterChain.addSynchronousReceiver(logWriter);

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
		analysisController.connect(reader, readerOutputPortName,
				this.monitoringRecordConsumerFilterChain, MonitoringRecordConsumerFilterChain.INPUT_PORT_NAME_RECORDS);
		return true;
	}
}
