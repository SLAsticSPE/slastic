package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker;

import kieker.analysis.AnalysisController;
import kieker.analysis.AnalysisControllerThread;
import kieker.analysis.plugin.reader.namedRecordPipe.PipeReader;
import kieker.common.configuration.Configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.monitoring.AbstractMonitoringManagerComponent;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.arch2implMapping.Arch2ImplNameMappingManager.EntityType;

/**
 * 
 * @author Andre van Hoorn
 */
public abstract class AbstractKiekerMonitoringManager extends
		AbstractMonitoringManagerComponent {
	private static final Log log =
			LogFactory.getLog(AbstractKiekerMonitoringManager.class);

	/**
	 * Send a timer event to the event engine every 100 millis.
	 */
	public final static long TIMER_EVENTS_RESOLUTION_NANOS =
			1000l * 1000l * 100l; // 100 millis: 1000 * 1000 * 100

	private final static String KIEKER_PIPENAME_PROPERTY = "kiekerPipeName";

	private static final String PROPERTY_INITIAL_ARCH2IMPL_NODE_MAPPINGS = "initialArch2ImplContainerNameMappings";

	/** Is initialized in {@link #init()} */
	private volatile PipeReader kiekerNamedRecordPipeReader;

	protected abstract IMonitoringRecordConsumerPlugin getMonitoringRecordConsumer();

	@Override
	public boolean init() {
		final String kiekerRecordPipeName =
				this
						.getInitProperty(AbstractKiekerMonitoringManager.KIEKER_PIPENAME_PROPERTY);
		if ((kiekerRecordPipeName == null)
				|| (kiekerRecordPipeName.length() <= 0)) {
			AbstractKiekerMonitoringManager.log
					.error("Missing configuration property value for '"
							+ AbstractMonitoringManagerComponent.PROP_PREFIX
							+ "."
							+ AbstractKiekerMonitoringManager.KIEKER_PIPENAME_PROPERTY
							+ "'");
			return false;
		}

		final Configuration readerConfiguration = new Configuration();
		readerConfiguration.setProperty(PipeReader.CONFIG_PROPERTY_NAME_PIPENAME, kiekerRecordPipeName);
		this.kiekerNamedRecordPipeReader = new PipeReader(readerConfiguration);
		return this.kiekerNamedRecordPipeReader != null;
	}

	private volatile AnalysisControllerThread analysisControllerThread = null;
	
	private boolean spawnKiekerAnalysis() {
		/** Is initialized in {@link #execute()} */
		final AnalysisController analysisInstance = new AnalysisController();
		analysisInstance.registerReader(this.kiekerNamedRecordPipeReader);

		analysisInstance
				.registerPlugin(new TimeTriggerAndRecordDelegationPlugin(
						this.getController(), this
								.getMonitoringRecordConsumer()));

		/** Spawn analysis instance */
		AbstractKiekerMonitoringManager.log
				.debug("Spawning Kieker analysis instance");
		this.analysisControllerThread = new AnalysisControllerThread(analysisInstance);
		this.analysisControllerThread.start();

		return true;
	}

	private boolean initNodeNameMappings() {
		final String nodeMappingPropVal = this
				.getInitProperty(AbstractKiekerMonitoringManager.PROPERTY_INITIAL_ARCH2IMPL_NODE_MAPPINGS);
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
			modelMgr.getArch2ImplNameMappingManager().registerArch2implNameMapping(EntityType.EXECUTION_CONTAINER,
					archName, implName);
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
				AbstractKiekerMonitoringManager.log.error("Failed to register initial node mappings");
				return false;
			}

			if (!this.concreteExecute()) {
				AbstractKiekerMonitoringManager.log
						.error("concreteExecute returned false. Will terminate.");
			}
			retVal = this.spawnKiekerAnalysis();

		} catch (final Exception e) {
			AbstractKiekerMonitoringManager.log.fatal(
					"AnalysisInstance threw exception: ", e);
			return false;
		}
		AbstractKiekerMonitoringManager.log
				.debug("KiekerMonitoringManager now executing");
		return retVal;
	}

	/**
	 * Terminates the internal Kieker analysis instance spawned in
	 * {@link #execute()} and calls the method
	 * {@link #concreteTerminate(boolean)} of the implementing class.
	 */
	@Override
	public final void terminate(final boolean error) {
		AbstractKiekerMonitoringManager.log
				.debug("Terminating the internal Kieker analysis instance by closing the named record pipe ");
		try {
			if (this.analysisControllerThread != null) {
				this.analysisControllerThread.terminate();
			}
			this.concreteTerminate(error);
		} catch (final Exception e) {
			AbstractKiekerMonitoringManager.log.error(e.getMessage(), e);
		}
	}

	/**
	 * Is called from the {@link #terminate(boolean)} method after having
	 * terminated the internal Kieker analysis instance.
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