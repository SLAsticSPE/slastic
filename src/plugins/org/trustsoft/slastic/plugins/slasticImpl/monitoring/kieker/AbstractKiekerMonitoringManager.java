package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker;

import kieker.analysis.AnalysisController;
import kieker.analysis.plugin.IMonitoringRecordConsumerPlugin;
import kieker.analysis.reader.namedRecordPipe.PipeReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.monitoring.AbstractMonitoringManagerComponent;

/**
 * 
 * @author Andre van Hoorn
 */
public abstract class AbstractKiekerMonitoringManager extends
		AbstractMonitoringManagerComponent {
	private static final Log log = LogFactory
			.getLog(AbstractKiekerMonitoringManager.class);

	private final static String KIEKER_PIPENAME_PROPERTY = "kiekerPipeName";

	/** Is initialized in {@link #execute()} */
	private volatile AnalysisController analysisInstance;

	/** Is initialized in {@link #init()} */
	private volatile PipeReader kiekerNamedRecordPipeReader;

	protected abstract IMonitoringRecordConsumerPlugin getMonitoringRecordConsumer();

	@Override
	public boolean init() {
		final String kiekerRecordPipeName = this
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

	/**
	 * Calls the {@link #concreteExecute()} method of the implementing class and
	 * starts the internal Kieker analysis instance.
	 */
	@Override
	public final boolean execute() {
		try {
			if (!this.concreteExecute()) {
				AbstractKiekerMonitoringManager.log
						.error("concreteExecute returned false. Will terminate.");
			}
			this.analysisInstance = new AnalysisController();
			this.analysisInstance.setLogReader(this.kiekerNamedRecordPipeReader);
			this.analysisInstance.registerPlugin(this
					.getMonitoringRecordConsumer());
			this.analysisInstance.run();
		} catch (final Exception e) {
			AbstractKiekerMonitoringManager.log.fatal(
					"AnalysisInstance threw exception: ", e);
			return false;
		}
		AbstractKiekerMonitoringManager.log
				.info("KiekerMonitoringManager now executing");
		return true;
	}

	/**
	 * Is called from {@link #execute()} just before the execution start and can
	 * be used by implementing classes to start internal activities. Note that
	 * the method must be non-blocking.
	 * 
	 * @return
	 */
	protected abstract boolean concreteExecute();
}
