package org.trustsoft.slastic.plugins.slasticImpl.model.componentDeployment;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.ComponentDeploymentModel;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
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
    public DeploymentComponent lookupDeploymentComponent(final long id) {
        return this.DeploymentComponentsManager.lookupDeploymentComponent(id);
    }

    @Override
    public DeploymentComponent createAndRegisterDeploymentComponent(
            final AssemblyComponent assemblyComponent,
            final ExecutionContainer executionContainer) {
        return this.DeploymentComponentsManager.createAndRegisterDeploymentComponent(assemblyComponent, executionContainer);
    }
}
