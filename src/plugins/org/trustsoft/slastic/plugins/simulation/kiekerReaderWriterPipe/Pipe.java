package org.trustsoft.slastic.plugins.simulation.kiekerReaderWriterPipe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.common.logReader.LogReaderExecutionException;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;


/**
 *
 * @author Andre van Hoorn
 */
public class Pipe {
    private static final Log log = LogFactory.getLog(Pipe.class);

    private final String name;
    private PipeReader pipeReader;
    private PipeWriter pipeWriter;

    public void setPipeReader(PipeReader pipeReader) {
        this.pipeReader = pipeReader;
    }

    public void setPipeWriter(PipeWriter pipeWriter) {
        this.pipeWriter = pipeWriter;
    }

    public String getName() {
        return this.name;
    }

    public Pipe(final String name){
        this.name = name;
    }


    //TODO: do we need execute / terminate?

    public void writeMonitoringRecord(AbstractKiekerMonitoringRecord monitoringRecord) throws PipeException {
        try {
            this.pipeReader.newRecord(monitoringRecord);
        } catch (LogReaderExecutionException ex) {
            // TODO: close pipe?
            log.error("LogReaderExecutionException occured", ex);
            throw new PipeException("LogReaderExecutionException occured", ex);
        }
    }

    public void registerMonitoringRecordType(int id, String className) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isWriteRecordTypeIds() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setWriteRecordTypeIds(boolean writeRecordTypeIds) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
