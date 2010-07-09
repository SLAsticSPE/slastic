package org.trustsoft.slastic.plugins.starter.kiekerNamedMonitoringPipe;

import kieker.common.record.IMonitoringRecord;
import kieker.monitoring.core.MonitoringController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


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

    public void writeMonitoringRecord(IMonitoringRecord monitoringRecord) throws PipeException {
        if (this.closed){
            log.error("trying to write to closed pipe");
            throw new PipeException("trying to write to closed pipe"); 
        }
            this.pipeReader.newMonitoringRecord(monitoringRecord);
    }

    public void registerMonitoringRecordType(int id, String className) {
        // we don't need such a regsitry since we pass the record objects directly
    }

    public void close(){
        this.closed = true;
        try {
            this.writeMonitoringRecord(MonitoringController.END_OF_MONITORING_MARKER);
        } catch (PipeException ex) {
            log.error("Failed to send END_OF_MONITORING_MARKER", ex);
            // we can't do anything more
        }
    }
}
