package org.trustsoft.slastic.plugins.slasticImpl.model.componentDeployment;

import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.componentDeployment.ComponentDeploymentFactory;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;
import java.util.List;

/**
 *
 * @author Andre van Hoorn
 */
public class DeploymentComponentsManager extends AbstractFQNamedEntityManager<DeploymentComponent> implements IDeploymentComponentsManager {
    public DeploymentComponentsManager(final List<DeploymentComponent> DeploymentComponents){
        super(DeploymentComponents);
    }

    @Override
    public DeploymentComponent lookupDeploymentComponent(
            final String fullyQualifiedName) {
        return this.lookup(fullyQualifiedName);
    }

    @Override
    public DeploymentComponent lookupDeploymentComponent(final long id){
        return this.lookup(id);
    }

    @Override
    public DeploymentComponent createAndRegisterDeploymentComponent(
            final String fullyQualifiedName) {
        return this.createAndRegister(fullyQualifiedName);
    }

    @Override
    protected DeploymentComponent createEntity() {
        return ComponentDeploymentFactory.eINSTANCE.createDeploymentComponent();
    }
}
