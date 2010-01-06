package org.trustsoft.slastic.control.components;

import org.trustsoft.slastic.control.components.analysis.ISLAsticAnalysis;
import org.trustsoft.slastic.control.components.modelManager.ISLAsticModelManager;
import org.trustsoft.slastic.control.components.modelUpdater.ISLAsticModelUpdater;
import org.trustsoft.slastic.reconfigurationManager.ISLAsticReconfigurationManager;
import org.trustsoft.slastic.reconfigurationManager.ReconfigurationManager;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticControl extends AbstractSLAsticComponent implements ISLAsticControl {
    private final ISLAsticReconfigurationManager reconfigurationManager;
    private final ISLAsticModelManager modelManager;
    private final ISLAsticModelUpdater modelUpdater;
    private final ISLAsticAnalysis analysis;

    public AbstractSLAsticControl(
            ISLAsticReconfigurationManager reconfigurationManager,
            ISLAsticModelManager modelManager,
            ISLAsticModelUpdater modelUpdater,
            ISLAsticAnalysis analysis){
        this.reconfigurationManager = reconfigurationManager;
        this.modelManager = modelManager;
        this.modelUpdater = modelUpdater;
        this.analysis = analysis;
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
