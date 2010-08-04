package org.trustsoft.slastic.plugins.slasticImpl;

import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import org.trustsoft.slastic.common.event.IObservationEvent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.control.components.modelManager.AbstractModelManagerComponent;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryManager;

/**
 *
 * @author Andre van Hoorn
 */
public class ModelManager extends AbstractModelManagerComponent {

    private static final Log log = LogFactory.getLog(ModelManager.class);
    private static final String PROP_NAME_TYPE_REPOSITORY_FN = "slamodel_fn";

    private volatile TypeRepositoryManager typeRepositoryManager;

    public TypeRepositoryManager getTypeRepositoryManager() {
        return this.typeRepositoryManager;
    }

    @Override
    public void init(Properties properties) {
        super.init(properties);
        this.typeRepositoryManager =
                new TypeRepositoryManager(ModelReader.readTypeRepositoryModel(super.getInitProperty(PROP_NAME_TYPE_REPOSITORY_FN)));
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
}
