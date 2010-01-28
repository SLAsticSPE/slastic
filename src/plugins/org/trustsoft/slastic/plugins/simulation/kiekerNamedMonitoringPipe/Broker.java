package org.trustsoft.slastic.plugins.simulation.kiekerNamedMonitoringPipe;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class Broker {
    private static final Broker INSTANCE = new Broker();
    private static final Log log = LogFactory.getLog(Broker.class);

    /* Not synchronized! */
    private HashMap<String, Pipe> pipeMap = new HashMap<String,Pipe>();

    public static Broker getInstance(){
        return Broker.INSTANCE;
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
