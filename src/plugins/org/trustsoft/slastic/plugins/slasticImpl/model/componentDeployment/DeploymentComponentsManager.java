package org.trustsoft.slastic.plugins.slasticImpl.model.componentDeployment;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractEntityManager;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.ComponentDeploymentFactory;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;

/**
 *
 * @author Andre van Hoorn
 */
public class DeploymentComponentsManager extends AbstractEntityManager<DeploymentComponent> implements IDeploymentComponentsManager {
    private static final Log log = LogFactory.getLog(DeploymentComponentsManager.class);

    public DeploymentComponentsManager(final List<DeploymentComponent> DeploymentComponents){
        super(DeploymentComponents);
    }

    @Override
    public DeploymentComponent lookupDeploymentComponent(final long id){
        return this.lookupEntityById(id);
    }

    @Override
    public DeploymentComponent createAndRegisterDeploymentComponent(
            final AssemblyComponent assemblyComponent,
            final ExecutionContainer executionContainer) {
        final DeploymentComponent deploymentComponent =
                this.createAndRegisterEntity();
        deploymentComponent.setAssemblyComponent(assemblyComponent);
        deploymentComponent.setExecutionContainer(executionContainer);
        return deploymentComponent;
    }

    @Override
    protected DeploymentComponent createEntity() {
        return ComponentDeploymentFactory.eINSTANCE.createDeploymentComponent();
    }

    @Override
    public boolean deleteDeploymentComponent(final DeploymentComponent deploymentComponent) {
        if (!this.removeEntity(deploymentComponent)){ // throws IllegalStateException
            DeploymentComponentsManager.log.warn("removeEntity(..) returned false for deployment component " + deploymentComponent + ". "
                    + "This means that this component wasn't registered. ");
            return false;
        }
        return true;
    }

    @Override
    public void migrateDeploymentComponent(final DeploymentComponent deploymentComponent, final ExecutionContainer toExecutionContainer) {
        deploymentComponent.setExecutionContainer(toExecutionContainer);
    }
}
