package org.trustsoft.slastic.plugins.simulation.kiekerNamedMonitoringPipe;

import java.util.logging.Level;
import java.util.logging.Logger;
import kieker.common.logReader.AbstractKiekerMonitoringLogReader;
import kieker.common.logReader.LogReaderExecutionException;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public final class PipeReader extends AbstractKiekerMonitoringLogReader implements IPipeReader {
    private static final Log log = LogFactory.getLog(PipeReader.class);

    private Pipe pipe;
    private String pipeName;

    public PipeReader(final String pipeName){
        this.pipeName = pipeName;
        this.pipe = Broker.getInstance().acquirePipe(pipeName);
        if (pipe == null){
            log.error("Failed to get Pipe with name " + this.pipeName);
            throw new IllegalArgumentException("Failed to get Pipe with name " + this.pipeName);
        }
    }

    @Override
    public boolean execute() throws LogReaderExecutionException {
        // No need to initialize since we receive asynchronously
        return true;
    }

    // TODO: allow initialization using init String
    public void init(String initString) throws IllegalArgumentException {
        super.initVarsFromInitString(initString);
    }

    public void newRecord(AbstractKiekerMonitoringRecord rec) throws LogReaderExecutionException {
            super.deliverRecordToConsumers(rec);
    }

    
}
