package org.trustsoft.slastic.tests.junit.model.reconfiguration;

import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.core.SystemModel;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import junit.framework.TestCase;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.tests.junit.model.ModelEntityCreationUtils;

/**
 *
 * @author Andre van Hoorn
 */
public class TestComponentMigration extends TestCase {

    public void testComponentMigration(){
        SystemModel systemModel = ModelManager.createInitializedSystemModel();
        ModelManager mgr = new ModelManager(systemModel);
        DeploymentComponent deploymentComponent =
                ModelEntityCreationUtils.createDeploymentComponent(
                mgr,
                "ComponentTypeName",
                "AssemblyComponentName",
                "ExecutionContainerTypeName",
                "ExecutionContainernName");
        ExecutionContainer migrationTargetExecutionContainer =
                ModelEntityCreationUtils.createExecutionContainer(
                mgr,
                "ExecutionContainerTargetTypeName",
                "ExecutionContainerTargetName");
        mgr.migrateComponent(deploymentComponent, migrationTargetExecutionContainer);
        assertSame(deploymentComponent.getExecutionContainer(), migrationTargetExecutionContainer);
    }
}
