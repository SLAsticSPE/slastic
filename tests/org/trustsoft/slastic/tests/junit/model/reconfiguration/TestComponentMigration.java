package org.trustsoft.slastic.tests.junit.model.reconfiguration;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.core.SystemModel;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;

/**
 *
 * @author Andre van Hoorn
 */
public class TestComponentMigration {
    private DeploymentComponent createDeploymentComponent(
            ModelManager modelManager,
            String fqComponentTypeName,
            String fqAssemblyComponentName,
            String fqExecutionEnvironmentTypeName,
            String fqExecutionContainerName){
        ComponentType componentType =
                modelManager.getTypeRepositoryManager().createAndRegisterComponentType(fqComponentTypeName);
        AssemblyComponent assemblyComponent =
                modelManager.getComponentAssemblyModelManager().createAndRegisterAssemblyComponent(fqAssemblyComponentName, componentType);
        ExecutionContainerType executionContainerType =
                modelManager.getTypeRepositoryManager().createAndRegisterExecutionContainerType(fqExecutionEnvironmentTypeName);
        ExecutionContainer executionContainer =
                modelManager.getExecutionEnvironmentModelManager().createAndRegisterExecutionContainer(fqExecutionContainerName, executionContainerType);
        return modelManager.getComponentDeploymentModelManager().createAndRegisterDeploymentComponent(assemblyComponent, executionContainer);
    }

    public void testComponentMigration(){
        SystemModel systemModel = ModelManager.createInitializedSystemModel();
        ModelManager mgr = new ModelManager(systemModel);

    }
}
