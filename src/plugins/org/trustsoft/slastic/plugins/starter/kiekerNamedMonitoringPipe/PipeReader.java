package org.trustsoft.slastic.plugins.starter.kiekerNamedMonitoringPipe;

import kieker.analysis.reader.AbstractMonitoringLogReader;
import kieker.analysis.reader.MonitoringLogReaderException;
import kieker.common.record.IMonitoringRecord;
import kieker.common.util.PropertyMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public final class PipeReader extends AbstractMonitoringLogReader implements IPipeReader {
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
    public boolean read() throws MonitoringLogReaderException {
        // No need to initialize since we receive asynchronously
        return true;
    }

    @Override
    public void init(String initString) throws IllegalArgumentException {
        PropertyMap propertyMap = new PropertyMap(initString, "|", "="); // throws IllegalArgumentException
        this.initPipe(propertyMap.getProperty(PROPERTY_PIPE_NAME));
        log.info("Connected to pipe '" + this.pipeName + "'"+ " ("+this.pipe+")");
    }

    @Override
    public boolean newMonitoringRecord(IMonitoringRecord rec) {
            return super.deliverRecord(rec);
    }
}
