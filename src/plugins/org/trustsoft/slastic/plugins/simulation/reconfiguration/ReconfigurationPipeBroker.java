package org.trustsoft.slastic.plugins.simulation.reconfiguration;

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
    public ReconfigurationPipe getPipe(final String pipeName) throws IllegalArgumentException{
        if (pipeName == null || pipeName.length()==0){
            log.error("Invalid pipe name " + pipeName);
            throw new IllegalArgumentException("Invalid pipe name "+pipeName);
        }
        ReconfigurationPipe pipe = this.pipeMap.get(pipeName);
        if (pipe == null){
            pipe = new ReconfigurationPipe(pipeName);
        }

        return pipe;
    }
}
