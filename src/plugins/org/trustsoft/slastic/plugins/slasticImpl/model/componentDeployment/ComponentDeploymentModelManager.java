package org.trustsoft.slastic.plugins.slasticImpl.model.componentDeployment;

import de.cau.se.slastic.metamodel.componentDeployment.ComponentDeploymentModel;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;

/**
 *
 * @author Andre van Hoorn
 */
public class ComponentDeploymentModelManager extends AbstractModelManager<ComponentDeploymentModel> implements IDeploymentComponentsManager {

    private final DeploymentComponentsManager DeploymentComponentsManager;

    private ComponentDeploymentModelManager() {
        super(null);
        this.DeploymentComponentsManager = null;
    }

    public ComponentDeploymentModelManager(final ComponentDeploymentModel ComponentDeploymentModel){
        super(ComponentDeploymentModel);
        this.DeploymentComponentsManager = new DeploymentComponentsManager(ComponentDeploymentModel.getDeploymentComponents());
    }

    @Override
    public DeploymentComponent lookupDeploymentComponent(String fullyQualifiedName) {
        return this.DeploymentComponentsManager.lookup(fullyQualifiedName);
    }

    @Override
    public DeploymentComponent lookupDeploymentComponent(long id) {
        return this.DeploymentComponentsManager.lookupDeploymentComponent(id);
    }

    @Override
    public DeploymentComponent createAndRegisterDeploymentComponent(String fullyQualifiedName) {
        return this.DeploymentComponentsManager.createAndRegisterDeploymentComponent(fullyQualifiedName);
    }
}
