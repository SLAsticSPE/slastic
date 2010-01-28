package org.trustsoft.slastic.plugins.starter.kiekerNamedMonitoringPipe;

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
    private static final String PROPERTY_PIPE_NAME = "pipeName";
    private static final Log log = LogFactory.getLog(PipeReader.class);

    private Pipe pipe;
    private String pipeName;

    public PipeReader(){ }

    public PipeReader(final String pipeName){
        this.initPipe(pipeName);
    }

    private void initPipe(String pipeName) throws IllegalArgumentException{
        this.pipeName = pipeName;
        this.pipe = Broker.getInstance().acquirePipe(pipeName);
        if (pipe == null){
            log.error("Failed to get Pipe with name " + this.pipeName);
            throw new IllegalArgumentException("Failed to get Pipe with name " + this.pipeName);
        }
        pipe.setPipeReader(this);
    }

    @Override
    public boolean execute() throws LogReaderExecutionException {
        // No need to initialize since we receive asynchronously
        return true;
    }

    public void init(String initString) throws IllegalArgumentException {
        super.initVarsFromInitString(initString);
        this.initPipe(super.getInitProperty(PROPERTY_PIPE_NAME));
        log.info("Connected to pipe '" + this.pipeName + "'"+ " ("+this.pipe+")");
    }

    public void newRecord(AbstractKiekerMonitoringRecord rec) throws LogReaderExecutionException {
            super.deliverRecordToConsumers(rec);
            log.info("Received record: " + rec);
    }
}
