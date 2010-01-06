package org.trustsoft.slastic.control.components;

import kieker.common.logReader.RecordConsumerExecutionException;
import org.trustsoft.slastic.control.components.analysis.ISLAsticAnalysis;
import org.trustsoft.slastic.control.components.modelManager.ISLAsticModelManager;
import org.trustsoft.slastic.control.components.modelUpdater.ISLAsticModelUpdater;
import org.trustsoft.slastic.reconfigurationManager.ISLAsticReconfigurationManager;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticControl extends AbstractSLAsticComponent implements ISLAsticControl {

    private final ISLAsticReconfigurationManager reconfigurationManager;
    private final ISLAsticModelManager modelManager;
    private final ISLAsticModelUpdater modelUpdater;
    private final ISLAsticAnalysis analysis;

    public AbstractSLAsticControl() {
        this(null, null, null, null);
    }

    public AbstractSLAsticControl(
            ISLAsticReconfigurationManager reconfigurationManager,
            ISLAsticModelManager modelManager,
            ISLAsticModelUpdater modelUpdater,
            ISLAsticAnalysis analysis) {
        this.reconfigurationManager = reconfigurationManager;
        this.modelManager = modelManager;
        this.modelUpdater = modelUpdater;
        this.analysis = analysis;
    }

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
}
