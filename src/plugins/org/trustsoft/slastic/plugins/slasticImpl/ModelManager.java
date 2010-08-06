package org.trustsoft.slastic.plugins.slasticImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryModel;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;
import java.io.IOException;
import org.trustsoft.slastic.common.event.IObservationEvent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.control.components.modelManager.AbstractModelManagerComponent;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

/**
 *
 * @author Andre van Hoorn
 */
public class ModelManager extends AbstractModelManagerComponent {

    private static final Log log = LogFactory.getLog(ModelManager.class);
    private static final String PROP_NAME_TYPE_REPOSITORY__INPUT_FN = "typeRepository_inputfn";
    private static final String PROP_NAME_TYPE_REPOSITORY__OUTPUT_FN = "typeRepository_outputputfn";
    private volatile String typeRepository_inputFile;
    private volatile String typeRepository_outputFile;
    private volatile TypeRepositoryModelManager typeRepositoryManager;
    private volatile TypeRepositoryModel typeRepositoryModel;

    public TypeRepositoryModelManager getTypeRepositoryManager() {
        return this.typeRepositoryManager;
    }

    @Override
    public boolean init() {
        this.typeRepository_inputFile =
                super.getInitProperty(PROP_NAME_TYPE_REPOSITORY__INPUT_FN, "");
        this.typeRepository_outputFile =
                super.getInitProperty(PROP_NAME_TYPE_REPOSITORY__OUTPUT_FN, "");

        if (this.typeRepository_inputFile.isEmpty()) {
            log.info("No input filename for type repository model given --- creating new model");
            this.typeRepositoryModel = TypeRepositoryFactory.eINSTANCE.createTypeRepositoryModel();
        } else {
            log.info("Loading type repository model from file " + this.typeRepository_inputFile);
            this.typeRepositoryModel = ModelIOUtils.readTypeRepositoryModel(this.typeRepository_inputFile);
        }
        this.typeRepositoryManager =
                new TypeRepositoryModelManager(this.typeRepositoryModel);
        return true;
    }

    @Override
    public void doReconfiguration(SLAsticReconfigurationPlan plan) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void handleEvent(IEvent ev) {
    }

    @Override
    public void newObservation(IObservationEvent ioe) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void terminate(boolean error) {
        this.saveModels();
    }

    private void saveModels() {
        try {
            if (!this.typeRepository_outputFile.isEmpty()) {
                ModelIOUtils.saveTypeRepositoryModel(this.typeRepositoryModel, this.typeRepository_outputFile);
            }
        } catch (IOException exc) {
            log.error("An IOException occured while saving models", exc);
        }
    }

    @Override
    public boolean execute() {
        return true;
    }
}
