package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.arch2implMapping.Arch2ImplNameMappingManager.EntityType;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters.AbstractTransformationComponent;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentFactory;
import de.cau.se.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification;
import de.cau.se.slastic.metamodel.executionEnvironment.Resource;
import de.cau.se.slastic.metamodel.executionEnvironment.ResourceSpecification;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;
import de.cau.se.slastic.metamodel.typeRepository.Operation;
import de.cau.se.slastic.metamodel.typeRepository.ResourceType;
import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.CPUType;
import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.GenericResourceType;
import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.MemSwapType;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public abstract class AbstractModelReconstructionComponent extends AbstractTransformationComponent {

	private static final Log log = LogFactory.getLog(AbstractModelReconstructionComponent.class);

	public AbstractModelReconstructionComponent(final ModelManager modelManager) {
		super(modelManager);
	}

	private final static String CPU_RESOURCE_NAME_PREFX = "cpu";
	private final static String MEM_SWAP_RESOURCE_NAME = "memSwap";

	public static String createGenericResourceSpecName(final String name) {
		return name;
	}

	public static String createCPUResourceSpecName(final String cpuId) {
		return AbstractModelReconstructionComponent.CPU_RESOURCE_NAME_PREFX + cpuId;
	}

	public static String createMemSwapResourceSpecName() {
		return AbstractModelReconstructionComponent.MEM_SWAP_RESOURCE_NAME;
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

		String hostNameArch =
				this.getModelManager().getArch2ImplNameMappingManager()
						.lookupArchName4ImplName(EntityType.EXECUTION_CONTAINER, hostNameImpl);
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
			resource =
					this.getExecutionEnvModelManager().lookupExecutionContainerResource(executionContainer,
							resourceName);

			if ((resource != null) && !(resource.getResourceSpecification().getResourceType() instanceof MemSwapType)) {
				this.logConflictingTypeForResource(resourceName, MemSwapType.class, resource.getResourceSpecification()
						.getResourceType().getClass());
				return null;
			}

			if ((resource != null) && !(resource.getResourceSpecification() instanceof MemSwapResourceSpecification)) {
				AbstractModelReconstructionComponent.log.fatal("Expecting resource specification to be an instance of "
						+ MemSwapResourceSpecification.class + "' but found '"
						+ resource.getResourceSpecification().getClass() + "'");
				return null;
			}

			if (resource == null) {
				// Lookup resource type which may exist already

				final ResourceType resourceType = this.getTypeModelManager().lookupResourceType(resourceTypeName);
				if ((resourceType != null) && !(resourceType instanceof MemSwapType)) {
					AbstractModelReconstructionComponent.log.error("Resource type with name '" + resourceTypeName
							+ "' exists already but is of type " + resourceType.getClass() + " instead of "
							+ MemSwapType.class);
					return null;
				}

				final MemSwapType memSwapResourceType;

				if (resourceType == null) {

					memSwapResourceType =
							this.getTypeModelManager().createAndRegisterMemSwapResourceType(resourceTypeName);

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
			resource =
					this.getExecutionEnvModelManager().lookupExecutionContainerResource(executionContainer,
							resourceName);

			if ((resource != null) && !(resource.getResourceSpecification().getResourceType() instanceof CPUType)) {
				this.logConflictingTypeForResource(resourceName, CPUType.class, resource.getResourceSpecification()
						.getResourceType().getClass());
				return null;
			}

			if (resource == null) {
				ResourceType resourceType = this.getTypeModelManager().lookupResourceType(resourceTypeName);

				if (resourceType == null) {
					resourceType = this.getTypeModelManager().createAndRegisterCPUResourceType(resourceTypeName);
				}

				// Create resource specification and add it to the container
				// type
				final ResourceSpecification resourceSpecification =
						this.getTypeModelManager().createAndAddResourceSpecification(
								executionContainer.getExecutionContainerType(), resourceType, resourceName);

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
			resource =
					this.getExecutionEnvModelManager().lookupExecutionContainerResource(executionContainer,
							resourceName);

			if ((resource != null)
					&& !(resource.getResourceSpecification().getResourceType() instanceof GenericResourceType)) {
				this.logConflictingTypeForResource(resourceName, GenericResourceType.class, resource
						.getResourceSpecification().getResourceType().getClass());
				return null;
			}

			if (resource == null) {
				ResourceType resourceType = this.getTypeModelManager().lookupResourceType(resourceTypeName);

				if (resourceType == null) {
					resourceType = this.getTypeModelManager().createAndRegisterGenericResourceType(resourceTypeName);
				}

				// Create resource specification and add it to the container
				// type
				final ResourceSpecification resourceSpecification =
						this.getTypeModelManager().createAndAddResourceSpecification(
								executionContainer.getExecutionContainerType(), resourceType, resourceName);

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
	private void logConflictingTypeForResource(final String resourceName, final Class<? extends ResourceType> expected,
			final Class<? extends ResourceType> found) {
		AbstractModelReconstructionComponent.log.fatal("Conflicting resource with name '" + resourceName
				+ "' exists. Expecting type '" + expected.getName() + "' but found '" + found.getName() + "'");
	}

	/**
	 * Appended to instance names, to form a type name in the type repository.
	 */
	public static final String DEFAULT_TYPE_POSTFIX = "__T";

	/**
	 * <p>
	 * Creates a new {@link AssemblyComponent} with the given componentName. The
	 * associated {@link ComponentType} is selected by using a (existing or
	 * newly created by the method) {@link ComponentType} with the same name.
	 * </p>
	 * 
	 * <p>
	 * An assembly component with the name componentName must not be registered
	 * prior to the call.
	 * </p>
	 * 
	 * @param componentName
	 * @return
	 */
	public AssemblyComponent createAssemblyComponent(final String componentName) {

		ComponentType componentType =
				this.getTypeModelManager().lookupComponentType(
						componentName + AbstractModelReconstructionComponent.DEFAULT_TYPE_POSTFIX);
		if (componentType == null) {
			componentType =
					this.getTypeModelManager().createAndRegisterComponentType(
							componentName + AbstractModelReconstructionComponent.DEFAULT_TYPE_POSTFIX);
		}

		return this.getAssemblyModelManager().createAndRegisterAssemblyComponent(componentName, componentType);
	}

	/**
	 * Copied from Kieker.TraceAnalysis
	 * 
	 * @param operationSignatureStr
	 * @return
	 */
	private kieker.tools.traceAnalysis.systemModel.Signature createSignature(final String operationSignatureStr) {

		final String returnTypeAndOperationName;

		String[] paramTypeList;
		final int openParenIdx = operationSignatureStr.indexOf('(');
		if (openParenIdx == -1) { // no parameter list
			paramTypeList = new String[] {};
			returnTypeAndOperationName = operationSignatureStr;
		} else {
			returnTypeAndOperationName = operationSignatureStr.substring(0, openParenIdx);
			final StringTokenizer strTokenizer =
					new StringTokenizer(operationSignatureStr.substring(openParenIdx + 1,
							operationSignatureStr.length() - 1), ",");
			paramTypeList = new String[strTokenizer.countTokens()];
			for (int i = 0; strTokenizer.hasMoreTokens(); i++) {
				paramTypeList[i] = strTokenizer.nextToken().trim();
			}
		}

		final String[] returnTypeAndOperationNameSplit = returnTypeAndOperationName.split("\\s+");

		String returnType = "N/A";
		final String name;

		switch (returnTypeAndOperationNameSplit.length) {
		case 1:
			// no return type
			name = returnTypeAndOperationName;
			break;
		case 2:
			// return type + operation name
			returnType = returnTypeAndOperationNameSplit[0];
			name = returnTypeAndOperationNameSplit[1];
			break;
		default:
			AbstractModelReconstructionComponent.log
					.error("Failed to split returnTypeAndOperationName by whitespace: '" + returnTypeAndOperationName
							+ "'");
			return null;

		}

		return new kieker.tools.traceAnalysis.systemModel.Signature(name, returnType, paramTypeList);
	}

	/**
	 * 
	 * @param componentType
	 * @param operationSignatureStr
	 * @return
	 */
	protected Operation lookupOrCreateOperationByName(final ComponentType componentType,
			final String operationSignatureStr) {
		final kieker.tools.traceAnalysis.systemModel.Signature kiekerSignature =
				this.createSignature(operationSignatureStr);

		Operation res =
				this.getTypeModelManager().lookupOperation(componentType, kiekerSignature.getName(),
						kiekerSignature.getReturnType(), kiekerSignature.getParamTypeList());

		if (res == null) {
			res =
					this.getTypeModelManager().createAndRegisterOperation(componentType, kiekerSignature.getName(),
							kiekerSignature.getReturnType(), kiekerSignature.getParamTypeList());
		}

		return res;
	}

	/**
	 * 
	 * @param containerName
	 * @return
	 */
	public ExecutionContainer createExecutionContainer(final String containerName) {
		ExecutionContainerType containerType =
				this.getTypeModelManager().lookupExecutionContainerType(
						containerName + AbstractModelReconstructionComponent.DEFAULT_TYPE_POSTFIX);
		if (containerType == null) {
			containerType =
					this.getTypeModelManager().createAndRegisterExecutionContainerType(
							containerName + AbstractModelReconstructionComponent.DEFAULT_TYPE_POSTFIX);
		}

		return this.getExecutionEnvModelManager().createAndRegisterExecutionContainer(containerName, containerType,
				/* mark allocated */ true);
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
	private Resource createResource(final ExecutionContainer executionContainer,
			final ResourceSpecification resourceSpecification) {
		final Resource resource = ExecutionEnvironmentFactory.eINSTANCE.createResource();
		resource.setExecutionContainer(executionContainer);
		resource.setResourceSpecification(resourceSpecification);
		return resource;
	}
}
