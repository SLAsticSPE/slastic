package org.trustsoft.slastic.tests.junit.model.reconfiguration;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.tests.junit.model.ModelEntityCreationUtils;

import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.core.SystemModel;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;

/**
 * 
 * @author Andre van Hoorn
 */
public class TestComponentMigration extends TestCase {

	public void testComponentMigration() {
		final SystemModel systemModel = ModelManager.createInitializedSystemModel();
		final ModelManager mgr = new ModelManager(systemModel);
		final DeploymentComponent deploymentComponent =
				ModelEntityCreationUtils.createDeploymentComponent(mgr, "ComponentTypeName", "AssemblyComponentName",
						"ExecutionContainerTypeName", "ExecutionContainernName");
		final ExecutionContainer migrationTargetExecutionContainer =
				ModelEntityCreationUtils.createExecutionContainer(mgr, "ExecutionContainerTargetTypeName",
						"ExecutionContainerTargetName");
		final DeploymentComponent newDeploymentComponent =
				mgr.getReconfigurationManager()
						.migrateComponent(deploymentComponent, migrationTargetExecutionContainer);

		/* Inspect result: */
		Assert.assertSame(newDeploymentComponent.getExecutionContainer(), migrationTargetExecutionContainer);
		Assert.assertNotSame("Source and resulting deployment components must not be the same", deploymentComponent,
				newDeploymentComponent);
		Assert.assertNull(
				"Old deployment component does still exist!",
				mgr.getComponentDeploymentModelManager().deploymentComponentForAssemblyComponent(
						deploymentComponent.getAssemblyComponent(), deploymentComponent.getExecutionContainer()));
		Assert.assertTrue(
				"New deployment component not in list of assembly component's deployments",
				mgr.getComponentDeploymentModelManager()
						.deploymentComponentsForAssemblyComponent(deploymentComponent.getAssemblyComponent(),
						/* do not include inactive ones */false).contains(newDeploymentComponent));
	}
}
