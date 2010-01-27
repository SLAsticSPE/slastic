package org.trustsoft.slastic.plugins.simulation.kiekerReaderWriterPipe;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class MonitoringReaderWriterBroker {
    private static final MonitoringReaderWriterBroker INSTANCE = new MonitoringReaderWriterBroker();
    private static final Log log = LogFactory.getLog(MonitoringReaderWriterBroker.class);

    /* Not synchronized! */
    private HashMap<String, Pipe> pipeMap = new HashMap<String,Pipe>();

    public static MonitoringReaderWriterBroker getInstance(){
        return MonitoringReaderWriterBroker.INSTANCE;
    }

    /**
     * Returns a connection with name @a pipeName. If a connection with
     * this name does not exist prior to the call, it is created.
     */
    public Pipe getPipe(final String pipeName) throws IllegalArgumentException{
        if (pipeName == null || pipeName.length()==0){
            log.error("Invalid connection name " + pipeName);
            throw new IllegalArgumentException("Invalid connection name "+pipeName);
        }
        Pipe conn = this.pipeMap.get(pipeName);
        if (conn == null){
            conn = new Pipe(pipeName);
        }

        return conn;
    }
}
