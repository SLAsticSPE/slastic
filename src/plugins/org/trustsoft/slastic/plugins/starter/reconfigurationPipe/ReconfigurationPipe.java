package org.trustsoft.slastic.plugins.starter.reconfigurationPipe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class ReconfigurationPipe {
    private static final Log log = LogFactory.getLog(ReconfigurationPipe.class);

    private final String name;
    private IReconfigurationPipePlanReceiver planReceiver;
    private boolean closed;

    /** No construction employing default constructor */
    private ReconfigurationPipe(){ name = null; }

    public ReconfigurationPipe(final String name){
        this.name = name;
    }

    public void setPlanReceiver(final IReconfigurationPipePlanReceiver planReceiver) {
        this.planReceiver = planReceiver;
        log.info("PipeReader initialized");
    }

    public String getName() {
        return this.name;
    }

    public void close(){
        this.closed = true;
    }
}
