package org.trustsoft.slastic.plugins.starter.kiekerNamedMonitoringPipe;

import java.util.Vector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;
import kieker.tpmon.writer.AbstractKiekerMonitoringLogWriter;
import kieker.tpmon.writer.util.async.AbstractWorkerThread;

/**
 *
 * @author Andre van Hoorn
 */
public final class PipeWriter extends AbstractKiekerMonitoringLogWriter {
    private static final Log log = LogFactory.getLog(PipeWriter.class);

    private static final String PROPERTY_PIPE_NAME = "pipeName";
    private Pipe pipe;
    private String pipeName;

    @Override
    public boolean writeMonitoringRecord(AbstractKiekerMonitoringRecord monitoringRecord) {
        try {
            this.pipe.writeMonitoringRecord(monitoringRecord);
        } catch (PipeException ex) {
            log.error("PipeException occured", ex);
            return false;
        }
        return true;
    }

    @Override
    public void registerMonitoringRecordType(int id, String className) {
        pipe.registerMonitoringRecordType(id, className);
    }

    public boolean init(String initString) throws IllegalArgumentException {
        super.initVarsFromInitString(initString);
        this.pipeName = super.getInitProperty(PROPERTY_PIPE_NAME);
        if (this.pipeName == null || this.pipeName.length() == 0) {
            log.error("Invalid or missing pipeName value for property '" + PROPERTY_PIPE_NAME+"'");
            throw new IllegalArgumentException("Invalid or missing pipeName value:" + this.pipeName);
        }
        this.pipe = Broker.getInstance().acquirePipe(pipeName);
        if (this.pipe == null){
            log.error("Failed to get pipe with name:" + this.pipeName);
            throw new IllegalArgumentException("Failed to get pipe with name:" + this.pipeName);            
        }
        log.info("Connected to pipe '"+this.pipeName+"'"+ " ("+this.pipe+")");
        return true;
    }

    public Vector<AbstractWorkerThread> getWorkers() {
        return new Vector<AbstractWorkerThread>();
    }

    public String getInfoString() {
        StringBuilder strB = new StringBuilder();

        strB.append("pipeName : " + this.pipeName);

        return strB.toString();
    }

}
