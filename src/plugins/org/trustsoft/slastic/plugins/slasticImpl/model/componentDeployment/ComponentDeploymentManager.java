package org.trustsoft.slastic.plugins.slasticImpl.model.componentDeployment;

import de.cau.se.slastic.metamodel.componentDeployment.ComponentDeployment;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;

/**
 *
 * @author Andre van Hoorn
 */
public class ComponentDeploymentManager implements IDeploymentComponentsManager {

    private final DeploymentComponentsManager DeploymentComponentsManager;

    private ComponentDeploymentManager() {
        this.DeploymentComponentsManager = null;
    }

    public ComponentDeploymentManager(final ComponentDeployment ComponentDeployment){
        this.DeploymentComponentsManager = new DeploymentComponentsManager(ComponentDeployment.getDeploymentComponents());
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
