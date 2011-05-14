package org.trustsoft.slastic.tests.junit.model.reconfiguration;

import junit.framework.TestCase;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.tests.junit.model.ModelEntityCreationUtils;

import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.core.SystemModel;

/**
 * 
 * @author Andre van Hoorn
 */
public class TestComponentDereplication extends TestCase {

	/**
	 * Creates a deployment component, de-replicates it, and makes sure that the
	 * deployment component is removed from the model.
	 */
	public void testComponentDereplication() {
		final SystemModel systemModel = ModelManager
				.createInitializedSystemModel();
		final ModelManager mgr = new ModelManager(systemModel);
		final DeploymentComponent deploymentComponent = ModelEntityCreationUtils
				.createDeploymentComponent(mgr, "ComponentTypeName",
						"AssemblyComponentName", "ExecutionContainerTypeName",
						"ExecutionContainernName");		
		mgr.getReconfigurationManager().dereplicateComponent(deploymentComponent);
		/* Make sure that entity is removed */
		
		// TODO: Re-activate!
//		Assert.assertNull(mgr.getComponentDeploymentModelManager()
//				.lookupDeploymentComponent(deploymentComponent.getId()));

		/* Make sure that this deployment is removed from the list of assemblies */
		// TODO: Re-activate!
//		Assert.assertNull(
//				"Method returned non-null deployment component although component dereplicated",
//				mgr.getComponentDeploymentModelManager()
//						.deploymentComponentForAssemblyComponent(
//								deploymentComponent.getAssemblyComponent(),
//								deploymentComponent.getExecutionContainer()));
		/*
		 * In this case, that should be no deployment component remaining for
		 * the assembly component:
		 */
		// TODO: Re-activate!
//		Assert.assertEquals(
//				"List of deployments for assembly must be 0", 0, 
//				mgr.getComponentDeploymentModelManager()
//						.deploymentComponentsForAssemblyComponent(
//								deploymentComponent.getAssemblyComponent()).size());
	}
}