package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker;

import kieker.analysis.AnalysisController;
import kieker.analysis.plugin.IMonitoringRecordConsumerPlugin;
import kieker.analysis.reader.namedRecordPipe.PipeReader;
import kieker.common.namedRecordPipe.Pipe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.monitoring.AbstractMonitoringManagerComponent;

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

		this.kiekerNamedRecordPipeReader = new PipeReader(kiekerRecordPipeName);
		return this.kiekerNamedRecordPipeReader != null;
	}

	private class KiekerAnalysisTask extends Thread {
		private final AnalysisController analysisInstance;

		@SuppressWarnings("unused")
		/* * Must not be used for construction. */
		private KiekerAnalysisTask() {
			this.analysisInstance = null;
		}

		public KiekerAnalysisTask(final AnalysisController analysisController) {
			this.analysisInstance = analysisController;
		}

		@Override
		public void run() {
			if (!this.analysisInstance.run()) {
				AbstractKiekerMonitoringManager.log
						.error("Analysis returned with error");
			}
		}

	}

	private boolean spawnKiekerAnalysis() {
		/** Is initialized in {@link #execute()} */
		final AnalysisController analysisInstance = new AnalysisController();
		analysisInstance.setLogReader(this.kiekerNamedRecordPipeReader);

		analysisInstance
				.registerPlugin(new TimeTriggerAndRecordDelegationPlugin(
						this.getController(), this
								.getMonitoringRecordConsumer()));

		/** Spawn analysis instance */
		AbstractKiekerMonitoringManager.log
				.info("Spawning Kieker analysis instance");
		(new KiekerAnalysisTask(analysisInstance)).start();

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
				.info("KiekerMonitoringManager now executing");
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
				.info("Terminating the internal Kieker analysis instance by closing the named record pipe ");
		try {
			final Pipe p = this.kiekerNamedRecordPipeReader.getPipe();
			if (p != null) {
				p.close();
			}
			this.concreteTerminate(error);
		} catch (final Exception e) {
			AbstractKiekerMonitoringManager.log.error(e.getMessage(), e);
		}
	}

	/**
	 * Is called from the {@link #terminate(boolean)} method after having
	 * terminated the internal Kieker analyis instance.
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