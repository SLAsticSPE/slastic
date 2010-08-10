package org.trustsoft.slastic.tests.junit.model.reconfiguration;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
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
public class TestComponentReplication extends TestCase {

    /**
     * Creates an assembly component as well as a deployment component as a
     * result of a replication of this assembly component on an execution
     * container, and makes sure that the assembly component and execution
     * container are properly set.
     */
    public void testComponentReplication(){
        SystemModel systemModel = ModelManager.createInitializedSystemModel();
        ModelManager mgr = new ModelManager(systemModel);
        AssemblyComponent assemblyComponent =
                ModelEntityCreationUtils.createAssemblyComponent(
                mgr,
                "ComponentTypeName",
                "AssemblyComponentName");
        ExecutionContainer replicationTargetExecutionContainer =
                ModelEntityCreationUtils.createExecutionContainer(
                mgr,
                "ExecutionContainerTargetTypeName",
                "ExecutionContainerTargetName");
        DeploymentComponent deploymentComponent =
                mgr.replicateComponent(assemblyComponent, replicationTargetExecutionContainer);
        assertSame(deploymentComponent.getAssemblyComponent(), assemblyComponent);
        assertSame(deploymentComponent.getExecutionContainer(), replicationTargetExecutionContainer);
    }
}