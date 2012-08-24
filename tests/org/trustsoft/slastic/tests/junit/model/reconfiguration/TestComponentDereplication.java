/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

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
		final SystemModel systemModel = ModelManager.createInitializedSystemModel();
		final ModelManager mgr = new ModelManager(systemModel);
		final DeploymentComponent deploymentComponent =
				ModelEntityCreationUtils.createDeploymentComponent(mgr, "ComponentTypeName", "AssemblyComponentName", "ExecutionContainerTypeName",
						"ExecutionContainernName");
		mgr.getReconfigurationManager().dereplicateComponent(deploymentComponent);
		/* Make sure that entity is removed/marked inactive */

		final DeploymentComponent deploymentComponentLookup = mgr.getComponentDeploymentModelManager().lookupDeploymentComponent(deploymentComponent.getId());

		if (deploymentComponentLookup != null) {
			Assert.assertSame(deploymentComponent, deploymentComponentLookup);
		}
		Assert.assertTrue((deploymentComponentLookup == null) || !deploymentComponentLookup.isActive());

		/*
		 * Make sure that this deployment is removed from the list of (active)
		 * assemblies
		 */
		final DeploymentComponent deploymentComponentLookup2 =
				mgr.getComponentDeploymentModelManager().deploymentComponentForAssemblyComponent(deploymentComponent.getAssemblyComponent(),
						deploymentComponent.getExecutionContainer());

		Assert.assertTrue((deploymentComponentLookup2 == null) || !deploymentComponentLookup2.isActive());

		/*
		 * In this case, that should be no (active) deployment component
		 * remaining for the assembly component:
		 */
		Assert.assertEquals("List of deployments for assembly must be 0", 0, mgr.getComponentDeploymentModelManager()
				.deploymentComponentsForAssemblyComponent(deploymentComponent.getAssemblyComponent(),
						/* do not include inactive ones */false).size());
	}
}
