package org.trustsoft.slastic.plugins.starter.kiekerNamedMonitoringPipe;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.common.logReader.LogReaderExecutionException;
import kieker.tpmon.core.TpmonController;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;


/**
 *
 * @author Andre van Hoorn
 */
public final class Pipe {
    private static final Log log = LogFactory.getLog(Pipe.class);

    private final String name;
    private PipeReader pipeReader;
    private boolean closed;

    public void setPipeReader(final PipeReader pipeReader) {
        this.pipeReader = pipeReader;
        log.info("PipeReader initialized");
    }

    public String getName() {
        return this.name;
    }

  /** No construction employing default constructor */
    private Pipe(){ name = null; }

    public Pipe(final String name){
        this.name = name;
    }

    public void writeMonitoringRecord(AbstractKiekerMonitoringRecord monitoringRecord) throws PipeException {
        if (this.closed){
            log.error("trying to write to closed pipe");
            throw new PipeException("trying to write to closed pipe"); 
        }
        try {
            this.pipeReader.newRecord(monitoringRecord);
        } catch (LogReaderExecutionException ex) {
            this.close();
            log.error("LogReaderExecutionException occured. Closing pipe.", ex);
            throw new PipeException("LogReaderExecutionException occured. Closing pipe.", ex);
        }
    }

    public void registerMonitoringRecordType(int id, String className) {
        // we don't need such a regsitry since we pass the record objects directly
    }

    public void close(){
        this.closed = true;
        try {
            this.writeMonitoringRecord(TpmonController.END_OF_MONITORING_MARKER);
        } catch (PipeException ex) {
            log.error("Failed to send END_OF_MONITORING_MARKER", ex);
            // we can't do anything more
        }
    }
}
