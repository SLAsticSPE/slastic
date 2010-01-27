package org.trustsoft.slastic.plugins.simulation.kiekerReaderWriterPipe;

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
public class PipeWriter extends AbstractKiekerMonitoringLogWriter {
    private static final Log log = LogFactory.getLog(PipeWriter.class);
    private Pipe pipe;

    @Override
    public boolean writeMonitoringRecord(AbstractKiekerMonitoringRecord monitoringRecord) {
        try {
            this.pipe.writeMonitoringRecord(monitoringRecord);
        } catch (PipeException ex) {
            log.error("PipeException occured", ex);
            return false;
            // TODO: terminate?
        }
        return true;
    }

    @Override
    public void registerMonitoringRecordType(int id, String className) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isWriteRecordTypeIds() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setWriteRecordTypeIds(boolean writeRecordTypeIds) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean init(String initString) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Vector<AbstractWorkerThread> getWorkers() {
        return new Vector<AbstractWorkerThread>();
    }

    public String getInfoString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
