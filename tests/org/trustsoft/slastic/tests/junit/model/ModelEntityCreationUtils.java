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

package org.trustsoft.slastic.tests.junit.model;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector;
import de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;
import de.cau.se.slastic.metamodel.typeRepository.Interface;

/**
 * 
 * @author Andre van Hoorn
 */
public class ModelEntityCreationUtils {

	/**
	 * Creates a new assemblyComponent with the name fqAssemblyComponentName
	 * based on the component type with the name fqComponentTypeName.
	 * 
	 * @param mgr
	 * @param fqComponentTypeName
	 * @param fqAssemblyComponentName
	 * @return
	 */
	public static AssemblyComponent createAssemblyComponent(
			final ModelManager modelManager,
			final String fqComponentTypeName,
			final String fqAssemblyComponentName) {
		final ComponentType componentType =
				modelManager.getTypeRepositoryManager().createAndRegisterComponentType(fqComponentTypeName);
		return modelManager.getComponentAssemblyModelManager().createAndRegisterAssemblyComponent(
				fqAssemblyComponentName, componentType);
	}

	/**
	 * Creates a new assemblyConnector based on the connector type with the name
	 * fqComponentTypeName and supported interface.
	 * 
	 * @param mgr
	 * @param fqComponentTypeName
	 * @param fqAssemblyComponentName
	 * @return
	 */
	public static AssemblyComponentConnector createAssemblyConnector(
			final ModelManager modelManager,
			final String fqConnectorTypeName,
			final Interface iface) {
		final ConnectorType connectorType =
				modelManager.getTypeRepositoryManager().createAndRegisterConnectorType(fqConnectorTypeName, iface);
		return modelManager.getComponentAssemblyModelManager().createAndRegisterAssemblyConnector(connectorType);
	}

	/**
	 * Creates a new {@link SystemProvidedInterfaceDelegationConnector} based on
	 * the connector type with the name fqComponentTypeName and supported
	 * interface.
	 * 
	 * @param mgr
	 * @param fqComponentTypeName
	 * @param fqAssemblyComponentName
	 * @return
	 */
	public static SystemProvidedInterfaceDelegationConnector createSystemProvidedDelegationConnector(
			final ModelManager modelManager,
			final String fqConnectorTypeName,
			final Interface iface) {
		final ConnectorType connectorType =
				modelManager.getTypeRepositoryManager().createAndRegisterConnectorType(fqConnectorTypeName, iface);
		return modelManager.getComponentAssemblyModelManager().createAndRegisterProvidedInterfaceDelegationConnector(
				connectorType);
	}

	/**
	 * Creates a deployment component based on newly created component type,
	 * assembly component, execution container type, and execution container
	 * with the given names.
	 * 
	 * @param modelManager
	 * @param fqComponentTypeName
	 * @param fqAssemblyComponentName
	 * @param fqExecutionContainerTypeName
	 * @param fqExecutionContainerName
	 * @return
	 */
	public static DeploymentComponent createDeploymentComponent(
			final ModelManager modelManager,
			final String fqComponentTypeName,
			final String fqAssemblyComponentName,
			final String fqExecutionContainerTypeName,
			final String fqExecutionContainerName) {
		final AssemblyComponent assemblyComponent =
				ModelEntityCreationUtils.createAssemblyComponent(modelManager, fqComponentTypeName,
						fqAssemblyComponentName);
		final ExecutionContainer executionContainer =
				ModelEntityCreationUtils.createExecutionContainer(modelManager, fqExecutionContainerTypeName,
						fqExecutionContainerName);
		return modelManager.getComponentDeploymentModelManager().createAndRegisterDeploymentComponent(
				assemblyComponent, executionContainer);
	}

	/**
	 * Creates a new execution container with the name fqExecutionContainerName
	 * based on the execution container type with the name
	 * fqExecutionContainerTypeName.
	 * 
	 * @param modelManager
	 * @param fqExecutionContainerTypeName
	 * @param fqExecutionContainerName
	 * @return
	 */
	public static ExecutionContainer createExecutionContainer(
			final ModelManager modelManager,
			final String fqExecutionContainerTypeName,
			final String fqExecutionContainerName) {
		final ExecutionContainerType executionContainerType =
				modelManager.getTypeRepositoryManager().createAndRegisterExecutionContainerType(
						fqExecutionContainerTypeName);
		return modelManager.getExecutionEnvironmentModelManager().createAndRegisterExecutionContainer(
				fqExecutionContainerName, executionContainerType,
				/* mark allocated */true);
	}
}
