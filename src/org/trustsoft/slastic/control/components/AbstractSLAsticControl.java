package org.trustsoft.slastic.control.components;

import kieker.common.logReader.IKiekerRecordConsumer;
import org.trustsoft.slastic.control.components.analysis.AbstractSLAsticAnalysis;
import org.trustsoft.slastic.control.components.modelManager.AbstractSLAsticModelManager;
import org.trustsoft.slastic.control.components.modelUpdater.AbstractSLAsticModelUpdater;
import org.trustsoft.slastic.reconfigurationManager.AbstractSLAsticReconfigurationManager;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticControl extends AbstractSLAsticComponent implements ISLAsticControl, IKiekerRecordConsumer {

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
        // do not execute the reconfiguration manager

       if (this.modelManager != null) {
            this.modelManager.execute();
        }
        if (this.modelUpdater != null) {
            this.modelUpdater.execute();
        }
        if (this.analysis != null) {
            this.analysis.execute();
        }

        // TODO: consider return values of delegated execution calls
        return true;
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
