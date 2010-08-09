package org.trustsoft.slastic.tests.junit.model.reconfiguration;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.core.SystemModel;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;
import junit.framework.TestCase;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;

/**
 *
 * @author Andre van Hoorn
 */
public class TestComponentMigration extends TestCase {

    /**
     * Creates a deployment component based on newly created component
     * type, assembly component, execution container type, and execution container
     * with the given names.
     *
     * @param modelManager
     * @param fqComponentTypeName
     * @param fqAssemblyComponentName
     * @param fqExecutionContainerTypeName
     * @param fqExecutionContainerName
     * @return
     */
    private DeploymentComponent createDeploymentComponent(
            ModelManager modelManager,
            String fqComponentTypeName,
            String fqAssemblyComponentName,
            String fqExecutionContainerTypeName,
            String fqExecutionContainerName){
        ComponentType componentType =
                modelManager.getTypeRepositoryManager().createAndRegisterComponentType(fqComponentTypeName);
        AssemblyComponent assemblyComponent =
                modelManager.getComponentAssemblyModelManager().createAndRegisterAssemblyComponent(fqAssemblyComponentName, componentType);
        ExecutionContainerType executionContainerType =
                modelManager.getTypeRepositoryManager().createAndRegisterExecutionContainerType(fqExecutionContainerTypeName);
        ExecutionContainer executionContainer =
                modelManager.getExecutionEnvironmentModelManager().createAndRegisterExecutionContainer(fqExecutionContainerName, executionContainerType);
        return modelManager.getComponentDeploymentModelManager().createAndRegisterDeploymentComponent(assemblyComponent, executionContainer);
    }

    private ExecutionContainer createExecutionContainer (
            ModelManager modelManager,
            String fqExecutionContainerTypeName,
            String fqExecutionContainerName){
        ExecutionContainerType executionContainerType =
                modelManager.getTypeRepositoryManager().createAndRegisterExecutionContainerType(fqExecutionContainerTypeName);
        return 
                modelManager.getExecutionEnvironmentModelManager().createAndRegisterExecutionContainer(fqExecutionContainerName, executionContainerType);
    }

    public void testComponentMigration(){
        SystemModel systemModel = ModelManager.createInitializedSystemModel();
        ModelManager mgr = new ModelManager(systemModel);
        DeploymentComponent deploymentComponent =
                this.createDeploymentComponent(
                mgr,
                "ComponentTypeName",
                "AssemblyComponentName",
                "ExecutionContainerTypeName",
                "ExecutionContainernName");
        ExecutionContainer migrationTargetExecutionContainer =
                this.createExecutionContainer(
                mgr,
                "ExecutionContainerTargetTypeName",
                "ExecutionContainerTargetName");
        mgr.migrateComponent(deploymentComponent, migrationTargetExecutionContainer);
        assertSame(deploymentComponent.getExecutionContainer(), migrationTargetExecutionContainer);
    }
}
