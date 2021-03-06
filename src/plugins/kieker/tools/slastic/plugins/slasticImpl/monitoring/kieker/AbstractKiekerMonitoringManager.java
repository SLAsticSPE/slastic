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
import kieker.analysis.AnalysisControllerThread;
import kieker.analysis.exception.AnalysisConfigurationException;
import kieker.analysis.plugin.filter.AbstractFilterPlugin;
import kieker.analysis.plugin.filter.forward.StringBufferFilter;
import kieker.analysis.plugin.reader.AbstractReaderPlugin;
import kieker.analysis.plugin.reader.namedRecordPipe.PipeReader;
import kieker.common.configuration.Configuration;
import kieker.tools.currentTimeEventGenerator.CurrentTimeEventGenerationFilter;
import kieker.tools.slastic.monitoring.AbstractMonitoringManagerComponent;
import kieker.tools.slastic.plugins.slasticImpl.ModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.arch2implMapping.Arch2ImplNameMappingManager.EntityType;

/**
 * 
 * @author Andre van Hoorn
 */
public abstract class AbstractKiekerMonitoringManager extends AbstractMonitoringManagerComponent {
	private static final Log LOG = LogFactory.getLog(AbstractKiekerMonitoringManager.class);

	/**
	 * Send a timer event to the event engine every 100 millis.
	 */
	public final static long TIMER_EVENTS_RESOLUTION_NANOS = 1000l * 1000l * 100l; // 100 millis: 1000 * 1000 * 100

	private final static String KIEKER_PIPENAME_PROPERTY = "kiekerPipeName";

	private static final String PROPERTY_INITIAL_ARCH2IMPL_NODE_MAPPINGS = "initialArch2ImplContainerNameMappings";

	/** Is initialized in {@link #init()} */
	private volatile PipeReader kiekerNamedRecordPipeReader;
	private volatile String kiekerPipeName;

	@Override
	public boolean init() {
		this.kiekerPipeName = this.getInitProperty(KIEKER_PIPENAME_PROPERTY);
		if ((this.kiekerPipeName == null) || (this.kiekerPipeName.length() <= 0)) {
			LOG.error("Missing configuration property value for '" + AbstractMonitoringManagerComponent.PROP_PREFIX + "." + KIEKER_PIPENAME_PROPERTY + "'");
			return false;
		}
		return true;
	}

	private volatile AnalysisControllerThread analysisControllerThread = null;

	private boolean spawnKiekerAnalysis() throws IllegalStateException, AnalysisConfigurationException {
		final AnalysisController analysisInstance = new AnalysisController();

		/*
		 * Register Named Pipe Reader
		 */
		final Configuration readerConfiguration = new Configuration();
		readerConfiguration.setProperty(PipeReader.CONFIG_PROPERTY_NAME_PIPENAME, this.kiekerPipeName);
		this.kiekerNamedRecordPipeReader = new PipeReader(readerConfiguration);
		analysisInstance.registerReader(this.kiekerNamedRecordPipeReader);

		/*
		 * Unify Strings
		 */
		final StringBufferFilter stringBufferFilter = new StringBufferFilter(new Configuration());
		analysisInstance.registerFilter(stringBufferFilter);
		analysisInstance.connect(this.kiekerNamedRecordPipeReader, PipeReader.OUTPUT_PORT_NAME_RECORDS,
				stringBufferFilter, StringBufferFilter.INPUT_PORT_NAME_EVENTS);

		/*
		 * Register CurrentTimeEventGenerationFilter and connect to Reader
		 */
		final Configuration currentTimeEventGeneratorConfig = new Configuration();
		currentTimeEventGeneratorConfig
				.setProperty(CurrentTimeEventGenerationFilter.CONFIG_PROPERTY_NAME_TIME_RESOLUTION, Long.toString(TIMER_EVENTS_RESOLUTION_NANOS));
		final CurrentTimeEventGenerationFilter currentTimeEventGenerationFilter = new CurrentTimeEventGenerationFilter(currentTimeEventGeneratorConfig);
		analysisInstance.registerFilter(currentTimeEventGenerationFilter);
		analysisInstance.connect(stringBufferFilter, StringBufferFilter.OUTPUT_PORT_NAME_RELAYED_EVENTS,
				currentTimeEventGenerationFilter, CurrentTimeEventGenerationFilter.INPUT_PORT_NAME_NEW_RECORD);

		/*
		 * Register CurrentTimeSetter
		 */
		final CurrentTimeSetter currentTimeSetterFilter = new CurrentTimeSetter(new Configuration(), this.getController());
		analysisInstance.registerFilter(currentTimeSetterFilter);
		analysisInstance.connect(currentTimeEventGenerationFilter, CurrentTimeEventGenerationFilter.OUTPUT_PORT_NAME_CURRENT_TIME_VALUE,
				currentTimeSetterFilter, CurrentTimeSetter.INPUT_PORT_NAME_TIMER_EVENTS_NANOS);

		/*
		 * Allow implementing classes to add additional filters, repos etc.
		 */
		if (!this.refineAnalysisConfiguration(analysisInstance, this.kiekerNamedRecordPipeReader, PipeReader.OUTPUT_PORT_NAME_RECORDS)) {
			LOG.error("refineAnalysisConfiguration returned with error");
			return false;
		}

		/** Spawn analysis instance */
		LOG.debug("Spawning Kieker analysis instance");
		this.analysisControllerThread = new AnalysisControllerThread(analysisInstance);
		this.analysisControllerThread.start();

		return true;
	}

	private boolean initNodeNameMappings() {
		final String nodeMappingPropVal = this.getInitProperty(PROPERTY_INITIAL_ARCH2IMPL_NODE_MAPPINGS);
		if ((nodeMappingPropVal == null) || (nodeMappingPropVal.length() <= 0)) {
			// this is perfectly fine; property is not required
			return true;
		}

		final String[] nodeMappingPropValPairs = nodeMappingPropVal.split(";");
		for (final String pair : nodeMappingPropValPairs) {
			final String[] pairSplit = pair.split(":");
			final String archName = pairSplit[0];
			final String implName = pairSplit[1];

			final ModelManager modelMgr = (ModelManager) super.getController().getModelManager();
			modelMgr.getArch2ImplNameMappingManager().registerArch2implNameMapping(EntityType.EXECUTION_CONTAINER, archName, implName);
		}

		return true;
	}

	/**
	 * Calls the {@link #concreteExecute()} method of the implementing class and
	 * spawns an internal Kieker analysis instance which will be terminated on a
	 * call to {@link #terminate(boolean)}.
	 */
	@Override
	public final boolean execute() {
		boolean retVal = false;
		try {
			if (!this.initNodeNameMappings()) {
				LOG.error("Failed to register initial node mappings");
				return false;
			}

			if (!this.concreteExecute()) {
				LOG.error("concreteExecute returned false. Will terminate.");
			}
			retVal = this.spawnKiekerAnalysis();

		} catch (final Exception e) {
			LOG.fatal("AnalysisInstance threw exception: ", e);
			return false;
		}
		LOG.debug("KiekerMonitoringManager now executing");
		return retVal;
	}

	/**
	 * Terminates the internal Kieker {@link AnalysisController} spawned in {@link #execute()} and calls the method {@link #concreteTerminate(boolean)} of the
	 * implementing class.
	 */
	@Override
	public final void terminate(final boolean error) {
		LOG.debug("Terminating the internal Kieker analysis instance by closing the named record pipe ");
		try {
			if (this.analysisControllerThread != null) {
				this.analysisControllerThread.terminate();
			}
			this.concreteTerminate(error);
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * Allows implementing classes to refine the current {@link AnalysisController}, e.g., by registering and connecting additional {@link AbstractFilterPlugin}s.
	 * 
	 * @param analysisController
	 * @param reader
	 * @param readerOutputPortName
	 * @throws AnalysisConfigurationException
	 * @throws IllegalStateException
	 */
	protected abstract boolean refineAnalysisConfiguration(AnalysisController analysisController, AbstractReaderPlugin reader, final String readerOutputPortName)
			throws IllegalStateException, AnalysisConfigurationException;

	/**
	 * Is called from the {@link #terminate(boolean)} method after having
	 * terminated the internal {@link AnalysisController} instance.
	 * 
	 * @param error
	 */
	protected abstract void concreteTerminate(final boolean error);

	/**
	 * Is called from {@link #execute()} just before the execution start and can
	 * be used by implementing classes to start internal activities. Note that
	 * the method must be non-blocking.
	 * 
	 * @return
	 */
	protected abstract boolean concreteExecute();
}
