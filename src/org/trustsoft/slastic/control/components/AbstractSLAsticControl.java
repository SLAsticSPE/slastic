package org.trustsoft.slastic.control.components;

import kieker.common.logReader.IKiekerRecordConsumer;
import org.trustsoft.slastic.control.components.analysis.AbstractSLAsticAnalysis;
import org.trustsoft.slastic.control.components.modelManager.AbstractSLAsticModelManager;
import org.trustsoft.slastic.control.components.modelUpdater.AbstractSLAsticModelUpdater;
import org.trustsoft.slastic.reconfigurationManager.AbstractSLAsticReconfigurationManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticControl extends AbstractSLAsticComponent implements ISLAsticControl, IKiekerRecordConsumer {

    private static final Log log = LogFactory.getLog(AbstractSLAsticControl.class);

    private AbstractSLAsticReconfigurationManager reconfigurationManager;
    private AbstractSLAsticModelManager modelManager;
    private AbstractSLAsticModelUpdater modelUpdater;
    private AbstractSLAsticAnalysis analysis;

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
}
