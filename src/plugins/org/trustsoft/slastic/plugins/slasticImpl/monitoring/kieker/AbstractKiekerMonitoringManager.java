package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker;

import java.lang.reflect.Method;

import kieker.analysis.AnalysisController;
import kieker.analysis.plugin.IMonitoringRecordConsumerPlugin;
import kieker.analysis.reader.IMonitoringLogReader;

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

	private final static String KIEKER_LOG_READER_CLASSNAME_PROPERTY = "tpmon.reader.classname";
	private final static String KIEKER_LOG_READER_INIT_STRING_PROPERTY = "tpmon.reader.initstring";

	/** Is initialized in {@link #execute()} */
	private volatile AnalysisController analysisInstance;

	/** Is initialized in {@link #init()} */
	private volatile IMonitoringLogReader logReader;

	protected abstract IMonitoringRecordConsumerPlugin getMonitoringRecordConsumer();

	@Override
	public boolean init() {
		final String logReaderClassnameProperty = this
				.getInitProperty(AbstractKiekerMonitoringManager.KIEKER_LOG_READER_CLASSNAME_PROPERTY);
		if ((logReaderClassnameProperty == null)
				|| (logReaderClassnameProperty.length() <= 0)) {
			AbstractKiekerMonitoringManager.log
					.error("Missing configuration property value for '"
							+ AbstractMonitoringManagerComponent.PROP_PREFIX
							+ "."
							+ AbstractKiekerMonitoringManager.KIEKER_LOG_READER_CLASSNAME_PROPERTY
							+ "'");
			return false;
		}
		final String logReaderInitStringProperty = this
				.getInitProperty(AbstractKiekerMonitoringManager.KIEKER_LOG_READER_INIT_STRING_PROPERTY);
		if ((logReaderInitStringProperty == null)
				|| (logReaderInitStringProperty.length() <= 0)) {
			AbstractKiekerMonitoringManager.log
					.warn("Missing configuration property value for '"
							+ AbstractMonitoringManagerComponent.PROP_PREFIX
							+ "."
							+ AbstractKiekerMonitoringManager.KIEKER_LOG_READER_INIT_STRING_PROPERTY
							+ "'");
			return false;
		}
		this.logReader = this.loadAndInitTpmonLogReaderInstanceFromClassname(
				logReaderClassnameProperty, logReaderInitStringProperty);
		return this.logReader != null;
	}

	/**
	 * An object of the class with name @classname is instantiated, its method
	 * setProperties(String initString) is called with parameter @a initString
	 * and the object is returned. This implies, that the class for @a classname
	 * provide the method setProperties(String initString).
	 * 
	 * @return the instance; null in case an error occurred.
	 */
	@SuppressWarnings("unchecked")
	private IMonitoringLogReader loadAndInitTpmonLogReaderInstanceFromClassname(
			final String classname, final String initString) {
		IMonitoringLogReader inst = null;
		try {
			final Class<IMonitoringLogReader> cl = (Class<IMonitoringLogReader>) Class
					.forName(classname);
			inst = cl.newInstance();
			final Method m = cl.getMethod("init", String.class);
			m.invoke(inst, initString);
			AbstractKiekerMonitoringManager.log
					.info("Loaded and instantiated Kieker log reader ('"
							+ classname + "') with init string '" + initString
							+ "'");
		} catch (final Exception ex) {
			inst = null;
			AbstractKiekerMonitoringManager.log.fatal(
					"Failed to create and init reader instance of class '"
							+ classname + "'", ex);
		}
		return inst;
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
			this.analysisInstance.setLogReader(this.logReader);
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
