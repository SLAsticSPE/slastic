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

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.core.SystemModel;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;

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
	public void testComponentReplication() {
		final SystemModel systemModel = ModelManager.createInitializedSystemModel();
		final ModelManager mgr = new ModelManager(systemModel);
		final AssemblyComponent assemblyComponent = ModelEntityCreationUtils.createAssemblyComponent(mgr, "ComponentTypeName", "AssemblyComponentName");
		final ExecutionContainer replicationTargetExecutionContainer =
				ModelEntityCreationUtils.createExecutionContainer(mgr, "ExecutionContainerTargetTypeName", "ExecutionContainerTargetName");
		final DeploymentComponent deploymentComponent =
				mgr.getReconfigurationManager().replicateComponent(assemblyComponent, replicationTargetExecutionContainer);
		Assert.assertSame(deploymentComponent.getAssemblyComponent(), assemblyComponent);
		Assert.assertSame(deploymentComponent.getExecutionContainer(), replicationTargetExecutionContainer);
		/*
		 * Make sure that the deployment component is contained in the list of
		 * the assembly's deployments
		 */
		Assert.assertTrue("Deployment component not included in assembly component's list of deployments", mgr
				.getComponentDeploymentModelManager().deploymentComponentsForAssemblyComponent(assemblyComponent,
						/*
						 * do not include inactive ones
						 */false).contains(deploymentComponent));
		Assert.assertSame(
				"Lookup of deployment component based on assembly component and execution container failed",
				deploymentComponent,
				mgr.getComponentDeploymentModelManager().deploymentComponentForAssemblyComponent(assemblyComponent,
						deploymentComponent.getExecutionContainer()));

	}
}
