package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker;

import java.lang.reflect.Method;
import java.util.Collection;
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
public abstract class AbstractKiekerMonitoringManager extends AbstractMonitoringManagerComponent {
    private static final Log log = LogFactory.getLog(AbstractKiekerMonitoringManager.class);

    private AnalysisController analysisInstance = null;
    private final static String KIEKER_LOG_READER_CLASSNAME_PROPERTY = "tpmon.reader.classname";
    private final static String KIEKER_LOG_READER_INIT_STRING_PROPERTY = "tpmon.reader.initstring";

    protected abstract IMonitoringRecordConsumerPlugin getMonitoringRecordConsumer();

    @Override
    public final boolean init() {
        String logReaderClassnameProperty = this.getInitProperty(KIEKER_LOG_READER_CLASSNAME_PROPERTY);
        if (logReaderClassnameProperty == null
                || logReaderClassnameProperty.length() <= 0) {
            log.error("Missing configuration property value for '"
                    + AbstractMonitoringManagerComponent.PROP_PREFIX + "."
                    + KIEKER_LOG_READER_CLASSNAME_PROPERTY + "'");
        }
        String logReaderInitStringProperty = this.getInitProperty(
                KIEKER_LOG_READER_INIT_STRING_PROPERTY);
        if (logReaderInitStringProperty == null
                || logReaderInitStringProperty.length() <= 0) {
            log.warn("Missing configuration property value for '"
                    + AbstractMonitoringManagerComponent.PROP_PREFIX + "."
                    + KIEKER_LOG_READER_INIT_STRING_PROPERTY + "'");
        }
        IMonitoringLogReader logReader = (IMonitoringLogReader) loadAndInitTpmonLogReaderInstanceFromClassname(
                logReaderClassnameProperty, logReaderInitStringProperty);

        analysisInstance = new AnalysisController();
        analysisInstance.setLogReader(logReader);
        analysisInstance.registerPlugin(this.getMonitoringRecordConsumer());
        return true;
    }

    /**
     * An object of the class with name @classname is instantiated, its method
     * setProperties(String initString) is called with parameter @a initString and the
     * object is returned. This implies, that the class for @a classname provide
     * the method setProperties(String initString).
     *
     * @return the instance; null in case an error occured.
     */
    @SuppressWarnings("unchecked")
    private IMonitoringLogReader loadAndInitTpmonLogReaderInstanceFromClassname(
            String classname, String initString) {
        IMonitoringLogReader inst = null;
        try {
            Class<IMonitoringLogReader> cl = (Class<IMonitoringLogReader>) Class.forName(classname);
            inst = cl.newInstance();
            Method m = cl.getMethod("init", String.class);
            m.invoke(inst, initString);
            log.info("Loaded and instantiated Kieker log reader ('" + classname
                    + "') with init string '" + initString + "'");
        } catch (Exception ex) {
            inst = null;
            log.fatal("Failed to create and init reader instance of class '" + classname + "'", ex);
        }
        return inst;
    }

    @Override
    public final boolean execute() {
        final boolean retVal;
        try {
            this.analysisInstance.run();
        } catch (Exception e) {
            log.fatal("AnalysisInstance threw exception: ", e);
            return false;
        }
        retVal = this.concreteExecute();
        log.info("KiekerMonitoringManager now executing");
        return retVal;
    }

    protected abstract boolean concreteExecute();
}
