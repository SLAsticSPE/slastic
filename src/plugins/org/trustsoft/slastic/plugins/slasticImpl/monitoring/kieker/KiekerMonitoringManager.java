package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker;

import java.lang.reflect.Method;

import kieker.common.logReader.IKiekerMonitoringLogReader;
import kieker.common.logReader.IKiekerRecordConsumer;
import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.tpan.TpanInstance;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.monitoring.AbstractSLAsticMonitoringManager;

/**
 * 
 * @author Andre van Hoorn
 */
public class KiekerMonitoringManager extends AbstractSLAsticMonitoringManager {

    private static final Log log = LogFactory.getLog(KiekerMonitoringManager.class);
    private TpanInstance tpanInstance = null;
    private boolean initialized = false;
    private final static String TPMON_LOG_READER_CLASSNAME_PROPERTY = "tpmon.reader.classname";
    private final static String TPMON_LOG_READER_INIT_STRING_PROPERTY = "tpmon.reader.initstring";

    private void init() throws IllegalArgumentException {
        String logReaderClassnameProperty = this.getInitProperty(TPMON_LOG_READER_CLASSNAME_PROPERTY);
        if (logReaderClassnameProperty == null
                || logReaderClassnameProperty.length() <= 0) {
            log.error("Missing configuration property value for '"
                    + AbstractSLAsticMonitoringManager.PROP_PREFIX + "."
                    + TPMON_LOG_READER_CLASSNAME_PROPERTY + "'");
        }
        String logReaderInitStringProperty = this.getInitProperty(
                TPMON_LOG_READER_INIT_STRING_PROPERTY);
        if (logReaderInitStringProperty == null
                || logReaderInitStringProperty.length() <= 0) {
            log.warn("Missing configuration property value for '"
                    + AbstractSLAsticMonitoringManager.PROP_PREFIX + "."
                    + TPMON_LOG_READER_INIT_STRING_PROPERTY + "'");
        }
        IKiekerMonitoringLogReader logReader = (IKiekerMonitoringLogReader) loadAndInitTpmonLogReaderInstanceFromClassname(
                logReaderClassnameProperty, logReaderInitStringProperty);

        tpanInstance = new TpanInstance();
        tpanInstance.setLogReader(logReader);
        tpanInstance.addRecordConsumer(new IKiekerRecordConsumer() {

            @Override
            public void consumeMonitoringRecord(
                    AbstractKiekerMonitoringRecord monitoringRecord)
                    throws RecordConsumerExecutionException {
                try {
                    // simply forward records to controller
                    getController().consumeMonitoringRecord(monitoringRecord);
                } catch (Exception exc) {
                    log.error("Failed to forward record", exc);
                }
            }

            @Override
            public String[] getRecordTypeSubscriptionList() {
                // TODO: needs to be refined
                return null; // receive events of any type
            }

            @Override
            public boolean execute() throws RecordConsumerExecutionException {
                // TODO: needs to be refined
                return true;
            }

            @Override
            public void terminate() {
                // TODO Auto-generated method stub
            }
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
    private IKiekerMonitoringLogReader loadAndInitTpmonLogReaderInstanceFromClassname(
            String classname, String initString) {
        IKiekerMonitoringLogReader inst = null;
        try {
            Class<IKiekerMonitoringLogReader> cl = (Class<IKiekerMonitoringLogReader>) Class.forName(classname);
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
            this.tpanInstance.run();
        } catch (Exception e) {
            log.fatal("TpanInstance threw exception: ", e);
            return false;
        }

        log.info("KiekerMonitoringManager now executing");
        return true;
    }

    @Override
    public void terminate() {
        log.info("KiekerMonitoringManager now terminating");
    }
}
