package org.trustsoft.slastic.control.components;

import kieker.common.logReader.IKiekerRecordConsumer;
import kieker.common.logReader.RecordConsumerExecutionException;
import org.trustsoft.slastic.control.components.analysis.ISLAsticAnalysis;
import org.trustsoft.slastic.control.components.modelManager.ISLAsticModelManager;
import org.trustsoft.slastic.control.components.modelUpdater.ISLAsticModelUpdater;
import org.trustsoft.slastic.reconfigurationManager.ISLAsticReconfigurationManager;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticControl extends AbstractSLAsticComponent implements ISLAsticControl, IKiekerRecordConsumer {

    private ISLAsticReconfigurationManager reconfigurationManager;
    private ISLAsticModelManager modelManager;
    private ISLAsticModelUpdater modelUpdater;
    private ISLAsticAnalysis analysis;

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

    public boolean execute() throws RecordConsumerExecutionException {
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

    public final ISLAsticAnalysis getAnalysis() {
        return analysis;
    }

    public final ISLAsticModelManager getModelManager() {
        return modelManager;
    }

    public final ISLAsticModelUpdater getModelUpdater() {
        return modelUpdater;
    }

    public final ISLAsticReconfigurationManager getReconfigurationManager() {
        return reconfigurationManager;
    }

   public final void setAnalysis(ISLAsticAnalysis analysis) {
        this.analysis = analysis;
    }

    public final void setModelManager(ISLAsticModelManager modelManager) {
        this.modelManager = modelManager;
    }

    public final void setModelUpdater(ISLAsticModelUpdater modelUpdater) {
        this.modelUpdater = modelUpdater;
    }

    public final void setReconfigurationManager(ISLAsticReconfigurationManager reconfigurationManager) {
        this.reconfigurationManager = reconfigurationManager;
    }
}
