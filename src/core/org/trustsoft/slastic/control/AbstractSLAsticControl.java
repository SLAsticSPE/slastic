package org.trustsoft.slastic.control;

import java.util.ArrayList;
import kieker.common.logReader.IKiekerRecordConsumer;
import org.trustsoft.slastic.control.components.analysis.AbstractSLAsticAnalysis;
import org.trustsoft.slastic.control.components.modelManager.AbstractSLAsticModelManager;
import org.trustsoft.slastic.control.components.modelUpdater.AbstractSLAsticModelUpdater;
import org.trustsoft.slastic.reconfiguration.AbstractSLAsticReconfigurationManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.components.events.ISLAsticEvent;
import org.trustsoft.slastic.control.components.events.ISimpleSLAsticEventService;
import org.trustsoft.slastic.control.components.events.ISimpleSLAsticEventServiceClient;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticControl extends AbstractSLAsticComponent implements ISLAsticControl, IKiekerRecordConsumer, ISimpleSLAsticEventService {

    private static final Log log = LogFactory.getLog(AbstractSLAsticControl.class);

    public static final String PROP_PREFIX = "slastic.control";
    
    private AbstractSLAsticReconfigurationManager reconfigurationManager;
    private AbstractSLAsticModelManager modelManager;
    private AbstractSLAsticModelUpdater modelUpdater;
    private AbstractSLAsticAnalysis analysis;

    private ArrayList<ISimpleSLAsticEventServiceClient> listeners = new ArrayList<ISimpleSLAsticEventServiceClient>();

    public void terminate() {
        // do not terminate the reconfiguration manager

        if (this.modelManager != null) {
            this.modelManager.terminate();
        }
        if (this.modelUpdater != null) {
            this.modelUpdater.terminate();
        }
        if (this.analysis != null) {
            this.analysis.terminate();
        }
    }

    public boolean execute() {
        boolean success = true;

        // do not execute the reconfiguration manager!

       if (this.modelManager == null || !this.modelManager.execute()) {
            log.error("Failed to execute modelManager ("+this.modelManager+")");
            success = false;
        }
        if (success && (this.modelUpdater == null || !this.modelUpdater.execute())) {
            log.error("Failed to execute modelUpdater ("+this.modelUpdater+")");
            success = false;
        }
        if (success && (this.analysis == null || !this.analysis.execute())) {
            log.error("Failed to execute analysis ("+this.analysis+")");
            success = false;
        }

        if (!success){ // terminate all components
            if (this.modelManager != null) this.modelManager.terminate();
            if (this.modelUpdater != null) this.modelUpdater.terminate();
            if (this.analysis != null) this.analysis.terminate();
        }

       return success;
    }

    public final AbstractSLAsticAnalysis getAnalysis() {
        return analysis;
    }

    public final AbstractSLAsticModelManager getModelManager() {
        return modelManager;
    }

    public final AbstractSLAsticModelUpdater getModelUpdater() {
        return modelUpdater;
    }

    public final AbstractSLAsticReconfigurationManager getReconfigurationManager() {
        return reconfigurationManager;
    }

   public final void setAnalysis(AbstractSLAsticAnalysis analysis) {
        this.analysis = analysis;
    }

    public final void setModelManager(AbstractSLAsticModelManager modelManager) {
        this.modelManager = modelManager;
    }

    public final void setModelUpdater(AbstractSLAsticModelUpdater modelUpdater) {
        this.modelUpdater = modelUpdater;
    }

    public final void setReconfigurationManager(AbstractSLAsticReconfigurationManager reconfigurationManager) {
        this.reconfigurationManager = reconfigurationManager;
    }


    public void sendEvent(ISLAsticEvent ev) {
        for (ISimpleSLAsticEventServiceClient l : this.listeners){
            l.handleSLAsticEvent(ev);
        }
    }

    public void addListener(ISimpleSLAsticEventServiceClient l) {
        this.listeners.add(l);
    }
}
