package org.trustsoft.slastic.tests.junit.model.reconfiguration;

import junit.framework.Assert;
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
		mgr.dereplicateComponent(deploymentComponent);
		Assert.assertNull(mgr.getComponentDeploymentModelManager()
				.lookupDeploymentComponent(deploymentComponent.getId()));
	}
}