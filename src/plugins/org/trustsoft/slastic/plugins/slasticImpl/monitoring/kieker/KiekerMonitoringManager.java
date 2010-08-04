package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker;

import java.lang.reflect.Method;
import java.util.Collection;
import kieker.analysis.AnalysisInstance;
import kieker.analysis.plugin.IMonitoringRecordConsumerPlugin;
import kieker.analysis.reader.IMonitoringLogReader;
import kieker.common.record.IMonitoringRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.monitoring.AbstractMonitoringManagerComponent;
import org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.KiekerMeasurementEvent;

/**
 * 
 * @author Andre van Hoorn
 */
public class KiekerMonitoringManager extends AbstractMonitoringManagerComponent {

    private static final Log log = LogFactory.getLog(KiekerMonitoringManager.class);
    private AnalysisInstance analysisInstance = null;
    private boolean initialized = false;
    private final static String KIEKER_LOG_READER_CLASSNAME_PROPERTY = "tpmon.reader.classname";
    private final static String KIEKER_LOG_READER_INIT_STRING_PROPERTY = "tpmon.reader.initstring";

    private void init() throws IllegalArgumentException {
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

        analysisInstance = new AnalysisInstance();
        analysisInstance.setLogReader(logReader);
        analysisInstance.registerPlugin(new IMonitoringRecordConsumerPlugin() {

            @Override
            public boolean newMonitoringRecord(IMonitoringRecord record) {
                try {
                    // simply forward (wrapped) records to controller
                    getController().getMonitoringClientPort().newObservation(new KiekerMeasurementEvent(record));
                } catch (Exception exc) {
                    log.error("Failed to forward record", exc);
                    return false;
                }
                return true;
            }

            @Override
            public Collection<Class<? extends IMonitoringRecord>> getRecordTypeSubscriptionList() {
                // TODO: needs to be refined
                return null; // receive events of any type
            }

            @Override
            public boolean execute() {
                // TODO: needs to be refined
                return true;
            }

            @Override
            public void terminate(boolean error) { }
        });
        this.initialized = true;
    }

    /**
     * An object of the class with name @classname is instantiated, its method
     * init(String initString) is called with parameter @a initString and the
     * object is returned. This implies, that the class for @a classname provide
     * the method init(String initString).
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
    public boolean execute() {
        if (!this.initialized) {
            this.init();
        }

        try {
            this.analysisInstance.run();
        } catch (Exception e) {
            log.fatal("AnalysisInstance threw exception: ", e);
            return false;
        }

        log.info("KiekerMonitoringManager now executing");
        return true;
    }

    @Override
    public void terminate(final boolean error) {
        log.info("KiekerMonitoringManager now terminating");
    }
}
