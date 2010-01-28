package org.trustsoft.slastic.plugins.starter.reconfigurationPipe;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class ReconfigurationPipeBroker {
    private static final ReconfigurationPipeBroker INSTANCE = new ReconfigurationPipeBroker();
    private static final Log log = LogFactory.getLog(ReconfigurationPipeBroker.class);

    /* Not synchronized! */
    private HashMap<String, ReconfigurationPipe> pipeMap = new HashMap<String,ReconfigurationPipe>();

    public static ReconfigurationPipeBroker getInstance(){
        return ReconfigurationPipeBroker.INSTANCE;
    }

    /**
     * Returns a connection with name @a pipeName. If a connection with
     * this name does not exist prior to the call, it is created.
     */
    public synchronized ReconfigurationPipe acquirePipe(final String pipeName) throws IllegalArgumentException{
        if (pipeName == null || pipeName.length()==0){
            log.error("Invalid connection name " + pipeName);
            throw new IllegalArgumentException("Invalid connection name "+pipeName);
        }
        ReconfigurationPipe conn = this.pipeMap.get(pipeName);
        if (conn == null){
            conn = new ReconfigurationPipe(pipeName);
            this.pipeMap.put(pipeName, conn);
        }

        return conn;
    }
}
