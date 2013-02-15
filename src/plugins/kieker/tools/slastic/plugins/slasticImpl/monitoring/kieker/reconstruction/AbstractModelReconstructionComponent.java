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

package kieker.tools.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;
import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentFactory;
import kieker.tools.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification;
import kieker.tools.slastic.metamodel.executionEnvironment.Resource;
import kieker.tools.slastic.metamodel.executionEnvironment.ResourceSpecification;
import kieker.tools.slastic.metamodel.typeRepository.ComponentType;
import kieker.tools.slastic.metamodel.typeRepository.ExecutionContainerType;
import kieker.tools.slastic.metamodel.typeRepository.Interface;
import kieker.tools.slastic.metamodel.typeRepository.Operation;
import kieker.tools.slastic.metamodel.typeRepository.ResourceType;
import kieker.tools.slastic.metamodel.typeRepository.resourceTypes.CPUType;
import kieker.tools.slastic.metamodel.typeRepository.resourceTypes.GenericResourceType;
import kieker.tools.slastic.metamodel.typeRepository.resourceTypes.MemSwapType;
import kieker.tools.slastic.plugins.slasticImpl.ModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.NameUtils;
import kieker.tools.slastic.plugins.slasticImpl.model.arch2implMapping.Arch2ImplNameMappingManager.EntityType;
import kieker.tools.slastic.plugins.slasticImpl.monitoring.kieker.filters.AbstractTransformationComponent;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public abstract class AbstractModelReconstructionComponent extends AbstractTransformationComponent {

	private static final Log LOG = LogFactory.getLog(AbstractModelReconstructionComponent.class);

	public AbstractModelReconstructionComponent(final ModelManager modelManager) {
		super(modelManager);
	}

	private final static String CPU_RESOURCE_NAME_PREFX = "cpu";
	private final static String MEM_SWAP_RESOURCE_NAME = "memSwap";

	public static String createGenericResourceSpecName(final String name) {
		return name;
	}

	public static String createCPUResourceSpecName(final String cpuId) {
		return CPU_RESOURCE_NAME_PREFX + cpuId;
	}

	public static String createMemSwapResourceSpecName() {
		return MEM_SWAP_RESOURCE_NAME;
	}

	// // Part of hack#10
	// boolean reportedMatch = false;

	/**
	 * 
	 * @param hostNameImpl
	 * @return
	 */
	protected ExecutionContainer lookupOrCreateExecutionContainerByName(final String hostNameImpl) {

		ExecutionContainer executionContainer;

		String hostNameArch = this.getModelManager().getArch2ImplNameMappingManager().lookupArchName4ImplName(EntityType.EXECUTION_CONTAINER, hostNameImpl);
		if (hostNameArch == null) {
			hostNameArch = hostNameImpl;
		}

		{
			/*
			 * Lookup execution container
			 */
			executionContainer = this.getExecutionEnvModelManager().lookupExecutionContainer(hostNameArch);
			if (executionContainer == null) {
				/* We need to create the execution container */
				executionContainer = this.createExecutionContainer(hostNameArch);
			}
		}
		return executionContainer;
	}

	/**
	 * 
	 * @param resourceName
	 * @param memCapacityBytes
	 * @param swapCapacityBytes
	 * @param resourceTypeName
	 * @param executionContainer
	 * @return
	 */
	protected Resource lookupOrCreateMemSwapResource(final String resourceName, final long memCapacityBytes,
			final long swapCapacityBytes, final String resourceTypeName, final ExecutionContainer executionContainer) {
		Resource resource;
		{
			/*
			 * Lookup resource -- which may exist already
			 */
			resource = this.getExecutionEnvModelManager().lookupExecutionContainerResource(executionContainer, resourceName);

			if ((resource != null) && !(resource.getResourceSpecification().getResourceType() instanceof MemSwapType)) {
				this.logConflictingTypeForResource(resourceName, MemSwapType.class, resource.getResourceSpecification().getResourceType().getClass());
				return null;
			}

			if ((resource != null) && !(resource.getResourceSpecification() instanceof MemSwapResourceSpecification)) {
				LOG.fatal("Expecting resource specification to be an instance of " + MemSwapResourceSpecification.class
						+ "' but found '" + resource.getResourceSpecification().getClass() + "'");
				return null;
			}

			if (resource == null) {
				// Lookup resource type which may exist already

				final ResourceType resourceType = this.getTypeModelManager().lookupResourceType(resourceTypeName);
				if ((resourceType != null) && !(resourceType instanceof MemSwapType)) {
					LOG.error("Resource type with name '" + resourceTypeName + "' exists already but is of type " + resourceType.getClass()
							+ " instead of " + MemSwapType.class);
					return null;
				}

				final MemSwapType memSwapResourceType;

				if (resourceType == null) {
					memSwapResourceType = this.getTypeModelManager().createAndRegisterMemSwapResourceType(resourceTypeName);

				} else {
					memSwapResourceType = (MemSwapType) resourceType;
				}

				// Create resource specification and add it to the container
				// type
				final ResourceSpecification resourceSpecification =
						this.getTypeModelManager().createAndAddMemSwapResourceSpecification(
								executionContainer.getExecutionContainerType(), memCapacityBytes, swapCapacityBytes,
								memSwapResourceType, resourceName);

				resource = this.createResource(executionContainer, resourceSpecification);
			}
		}
		return resource;
	}

	/**
	 * 
	 * @param resourceName
	 * @param resourceTypeName
	 * @param executionContainer
	 * @return
	 */
	protected Resource lookupOrCreateCPUResource(final String resourceName, final String resourceTypeName,
			final ExecutionContainer executionContainer) {
		Resource resource;
		{
			/*
			 * Lookup resource -- which may exist already
			 */
			resource = this.getExecutionEnvModelManager().lookupExecutionContainerResource(executionContainer, resourceName);

			if ((resource != null) && !(resource.getResourceSpecification().getResourceType() instanceof CPUType)) {
				this.logConflictingTypeForResource(resourceName, CPUType.class, resource.getResourceSpecification().getResourceType().getClass());
				return null;
			}

			if (resource == null) {
				ResourceType resourceType = this.getTypeModelManager().lookupResourceType(resourceTypeName);

				if (resourceType == null) {
					resourceType = this.getTypeModelManager().createAndRegisterCPUResourceType(resourceTypeName);
				}

				// TODO: What if the resource specification already exists for this container type?

				// Create resource specification and add it to the container
				// type
				final ResourceSpecification resourceSpecification =
						this.getTypeModelManager().createAndAddResourceSpecification(executionContainer.getExecutionContainerType(), resourceType, resourceName);

				resource = this.createResource(executionContainer, resourceSpecification);
			}
		}
		return resource;
	}

	/**
	 * 
	 * @param resourceName
	 * @param resourceTypeName
	 * @param executionContainer
	 * @return
	 */
	protected Resource lookupOrCreateGenericResource(final String resourceName, final String resourceTypeName,
			final ExecutionContainer executionContainer) {
		Resource resource;
		{
			/*
			 * Lookup resource -- which may exist already
			 */
			resource = this.getExecutionEnvModelManager().lookupExecutionContainerResource(executionContainer, resourceName);

			if ((resource != null)
					&& !(resource.getResourceSpecification().getResourceType() instanceof GenericResourceType)) {
				this.logConflictingTypeForResource(resourceName, GenericResourceType.class, resource.getResourceSpecification().getResourceType().getClass());
				return null;
			}

			if (resource == null) {
				ResourceType resourceType = this.getTypeModelManager().lookupResourceType(resourceTypeName);

				if (resourceType == null) {
					resourceType = this.getTypeModelManager().createAndRegisterGenericResourceType(resourceTypeName);
				}

				// TODO: What if the resource specification already exists for this container type?

				// Create resource specification and add it to the container
				// type
				final ResourceSpecification resourceSpecification =
						this.getTypeModelManager().createAndAddResourceSpecification(executionContainer.getExecutionContainerType(), resourceType, resourceName);

				resource = this.createResource(executionContainer, resourceSpecification);
			}
		}
		return resource;
	}

	/**
	 * 
	 * @param resourceName
	 * @param expected
	 * @param found
	 */
	private void logConflictingTypeForResource(final String resourceName, final Class<? extends ResourceType> expected, final Class<? extends ResourceType> found) {
		LOG.fatal("Conflicting resource with name '" + resourceName + "' exists. Expecting type '" + expected.getName() + "' but found '" + found.getName() + "'");
	}

	/**
	 * Appended to instance names, to form a type name in the type repository.
	 */
	public static final String DEFAULT_TYPE_POSTFIX = "__T";

	/**
	 * Prefix to be used for naming {@link Interface}s corresponding to {@link ComponentType}s.
	 */
	public static final String DEFAULT_INTERFACE_PREFIX = "I";

	/**
	 * <p>
	 * Creates a new {@link AssemblyComponent} with the given fqComponentName. The associated {@link ComponentType} is selected by using a (existing or newly created
	 * by the method) {@link ComponentType} with the same name.
	 * </p>
	 * 
	 * <p>
	 * An assembly component with the name fqComponentName must not be registered prior to the call.
	 * </p>
	 * 
	 * @param fqComponentName
	 * @return
	 */
	protected AssemblyComponent createAssemblyComponent(final String fqComponentName) {

		ComponentType componentType = this.getTypeModelManager().lookupComponentType(fqComponentName + DEFAULT_TYPE_POSTFIX);
		if (componentType == null) {
			componentType = this.getTypeModelManager().createAndRegisterComponentType(fqComponentName + DEFAULT_TYPE_POSTFIX);

			/* Also create a corresponding (provided) interface */
			final String fqIfaceName = NameUtils.createFQName(componentType.getPackageName(), DEFAULT_INTERFACE_PREFIX + componentType.getName());
			final Interface iface = this.getTypeModelManager().createAndRegisterInterface(fqIfaceName);
			this.getTypeModelManager().registerProvidedInterface(componentType, iface);
		}

		return this.getAssemblyModelManager().createAndRegisterAssemblyComponent(fqComponentName, componentType);
	}

	/**
	 * TODO: Remove method if lookupOrCreateOperationByName is working fine
	 * 
	 * Copied from Kieker.TraceAnalysis
	 * 
	 * @param operationSignatureStr
	 * @return
	 */
	// private Signature createSignature(final String operationSignatureStr) {
	//
	// final String returnTypeAndOperationName;
	//
	// String[] paramTypeList;
	// final int openParenIdx = operationSignatureStr.indexOf('(');
	// if (openParenIdx == -1) { // no parameter list
	// paramTypeList = new String[] {};
	// returnTypeAndOperationName = operationSignatureStr;
	// } else {
	// returnTypeAndOperationName = operationSignatureStr.substring(0, openParenIdx);
	// final StringTokenizer strTokenizer =
	// new StringTokenizer(operationSignatureStr.substring(openParenIdx + 1,
	// operationSignatureStr.length() - 1), ",");
	// paramTypeList = new String[strTokenizer.countTokens()];
	// for (int i = 0; strTokenizer.hasMoreTokens(); i++) {
	// paramTypeList[i] = strTokenizer.nextToken().trim();
	// }
	// }
	//
	// final String[] returnTypeAndOperationNameSplit = returnTypeAndOperationName.split("\\s+");
	//
	// String returnType = "N/A";
	// final String name;
	//
	// switch (returnTypeAndOperationNameSplit.length) {
	// case 1:
	// // no return type
	// name = returnTypeAndOperationName;
	// break;
	// case 2:
	// // return type + operation name
	// returnType = returnTypeAndOperationNameSplit[0];
	// name = returnTypeAndOperationNameSplit[1];
	// break;
	// default:
	// log
	// .error("Failed to split returnTypeAndOperationName by whitespace: '" + returnTypeAndOperationName
	// + "'");
	// return null;
	//
	// }
	//
	// return new Signature(name, returnType, paramTypeList);
	// }

	/**
	 * 
	 * @param componentType
	 * @param operationSignatureStr
	 * @return
	 */
	protected Operation lookupOrCreateOperationByName(final ComponentType componentType,
			final String signatureName, final String returnType, final String[] argTypes) {

		Operation res = this.getTypeModelManager().lookupOperation(componentType, signatureName, returnType, argTypes);

		if (res == null) {
			res = this.getTypeModelManager().createAndRegisterOperation(componentType, signatureName, returnType, argTypes);

			/* Now, we want to add the new operation also to the interface */
			final EList<Interface> providedInterfaces = res.getComponentType().getProvidedInterfaces();
			if (providedInterfaces.isEmpty()) {
				LOG.warn("Component type " + componentType + " has no provided interface yet. Cannot add operation signature.");
			} else {
				// Add the operation to the last interface in the list (as this
				// should be the one we've added in #createAssemblyComponent)
				final Interface iface = providedInterfaces.get(providedInterfaces.size() - 1);
				this.getTypeModelManager().createAndRegisterSignature(iface, signatureName, returnType, argTypes);
			}
		}

		return res;
	}

	/**
	 * 
	 * @param containerName
	 * @return
	 */
	public ExecutionContainer createExecutionContainer(final String containerName) {
		ExecutionContainerType containerType = this.getTypeModelManager().lookupExecutionContainerType(containerName + DEFAULT_TYPE_POSTFIX);
		if (containerType == null) {
			containerType =
					this.getTypeModelManager().createAndRegisterExecutionContainerType(containerName + DEFAULT_TYPE_POSTFIX);
		}

		return this.getExecutionEnvModelManager().createAndRegisterExecutionContainer(containerName, containerType,
				/* mark allocated */true);
	}

	public static final String DEFAULT_GENERIC_RESOURCE_TYPE_NAME = "DEFAULT.GENERIC_RESOURCE_TYPE";

	public static final String DEFAULT_CPU_RESOURCE_TYPE_NAME = "DEFAULT.CPU_RESOURCE_TYPE";

	public static final String DEFAULT_MEMSWAP_RESOURCE_TYPE_NAME = "DEFAULT.MEMSWAP_RESOURCE_TYPE";

	/**
	 * 
	 * @param executionContainer
	 * @param resourceSpecification
	 * @return
	 */
	private Resource createResource(final ExecutionContainer executionContainer, final ResourceSpecification resourceSpecification) {
		final Resource resource = ExecutionEnvironmentFactory.eINSTANCE.createResource();
		resource.setExecutionContainer(executionContainer);
		resource.setResourceSpecification(resourceSpecification);
		return resource;

		// TODO: Don't we have to add the resource to the model?
	}
}
