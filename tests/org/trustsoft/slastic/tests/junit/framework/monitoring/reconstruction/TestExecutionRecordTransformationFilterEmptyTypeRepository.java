package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction;

import junit.framework.Assert;
import kieker.common.record.OperationExecutionRecord;
import kieker.tools.traceAnalysis.systemModel.Signature;

import org.apache.commons.lang.StringUtils;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.AbstractModelReconstructionComponent;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.monitoring.OperationExecution;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;
import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.Operation;

/**
 * Tests if the {@link ExecutionRecordTransformationFilter} filter correctly
 * transforms a {@link OperationExecutionRecord} into an
 * {@link OperationExecution} including the required initializations of an empty
 * system model.
 * 
 * @author Andre van Hoorn
 */
public class TestExecutionRecordTransformationFilterEmptyTypeRepository extends
		AbstractReconstructionTest {

	private final String packageName = "package.subpackage";
	private final String classNameNoPackage = "Classname";
	private final Signature signature =
			new Signature("theOpName", "returnType", new String[] { "ArgType0",
					"ArgType1", "ArgType2" });

	private final OperationExecutionRecord kiekerRecord =
			new OperationExecutionRecord();
	{
		this.kiekerRecord.className =
				this.packageName + "." + this.classNameNoPackage;
		this.kiekerRecord.eoi = 77;
		this.kiekerRecord.ess = 98;
		this.kiekerRecord.hostName = "theHostname";
		this.kiekerRecord.operationName =
				String.format("%s %s(%s)", this.signature.getReturnType(),
						this.signature.getName(), StringUtils.join(
								this.signature.getParamTypeList(), ","));
		this.kiekerRecord.sessionId = "ZUKGHGF435JJ";
		this.kiekerRecord.tin = 65656868l;
		this.kiekerRecord.tout = 9878787887l;
		this.kiekerRecord.traceId = 88878787877887l;
	}

	public void testTransformRecordEmptyModelComponentDiscoveryClassName() {
		/* Create type repository manager for empty type repository */
		final ModelManager modelManager = new ModelManager();

		final ExecutionRecordTransformationFilter execRecFilter =
				new ExecutionRecordTransformationFilter(modelManager,
						NameUtils.ABSTRACTION_MODE_CLASS);

		final OperationExecution slasticRecord =
				execRecFilter.transformExecutionRecord(this.kiekerRecord);

		this.checkResult(modelManager, slasticRecord,
				NameUtils.ABSTRACTION_MODE_CLASS);
	}

	/**
	 * Performs a number of validity checks on the model and the record.
	 * 
	 * @param mgr
	 * @param slasticExecRec
	 */
	private void checkResult(final ModelManager mgr,
			final OperationExecution slasticExecRec,
			final int componentDiscoveryLevel) {
		Assert.assertNotNull("slasticExecRec must not be null", slasticExecRec);

		Assert
				.assertTrue(
						"Expected type "
								+ DeploymentComponentOperationExecution.class
										.getName() + " but found "
								+ slasticExecRec.getClass().getName(),
						slasticExecRec instanceof DeploymentComponentOperationExecution);

		final DeploymentComponentOperationExecution slasticComponentExecRec =
				(DeploymentComponentOperationExecution) slasticExecRec;

		{
			/*
			 * This is the easy part: compare the plain values among the
			 * records:
			 */
			Assert.assertEquals("Unexpected session ID",
					this.kiekerRecord.sessionId, slasticExecRec.getSessionId());
			Assert.assertEquals("Unexpected trace ID",
					this.kiekerRecord.traceId, slasticExecRec.getTraceId());
			Assert.assertEquals("Unexpected tin", this.kiekerRecord.tin,
					slasticExecRec.getTin());
			Assert.assertEquals("Unexpected tout", this.kiekerRecord.tout,
					slasticExecRec.getTout());
			Assert.assertEquals("Unexpected eoi", this.kiekerRecord.eoi,
					slasticExecRec.getEoi());
			Assert.assertEquals("Unexpected ess", this.kiekerRecord.ess,
					slasticExecRec.getEss());
		}

		/*
		 * Determine expected componentTypeLookupFQName and
		 * assemblyComponentLookupFQName depending on selected
		 * componentDiscoveryMode
		 */
		final String componentTypeLookupFQName;
		final String interfaceLookupFQName;
		final String assemblyComponentLookupFQName;
		final String operationLookupName;

		{
			switch (componentDiscoveryLevel) {
			case NameUtils.ABSTRACTION_MODE_CLASS:
				assemblyComponentLookupFQName =
						this.packageName + "." + this.classNameNoPackage;
				operationLookupName = this.signature.getName();
				break;
			default:
				// this would be too difficult to test here
				Assert.fail("Invalid test (componentDiscoveryLevel)");
				assemblyComponentLookupFQName = null; // make javac happy
				operationLookupName = null; // make javac happy
			}

			componentTypeLookupFQName =
					assemblyComponentLookupFQName
							+ AbstractModelReconstructionComponent.DEFAULT_TYPE_POSTFIX;
			final String[] componentTypeFQNameSplit = NameUtils.splitFullyQualifiedName(componentTypeLookupFQName);
			interfaceLookupFQName =
					NameUtils
							.createFQName(componentTypeFQNameSplit[0],
									AbstractModelReconstructionComponent.DEFAULT_INTERFACE_PREFIX
											+ componentTypeFQNameSplit[1]);
		}

		final ComponentType lookedUpComponentType;
		final Interface lookedUpInterface;
		final ExecutionContainerType lookedUpContainerType;
		{
			/* Check type repository contents */
			/* 1. Lookup component type by name and compare with record content */
			lookedUpComponentType =
					mgr.getTypeRepositoryManager().lookupComponentType(
							componentTypeLookupFQName);
			Assert.assertNotNull("Lookup of component type "
					+ componentTypeLookupFQName + " returned null",
					lookedUpComponentType);
			Assert.assertSame("Unexpected component types",
					lookedUpComponentType, slasticComponentExecRec
							.getDeploymentComponent().getAssemblyComponent()
							.getComponentType());

			/*
			 * 2. Lookup interface by name, make sure that it contains the
			 * signature and make sure that the interface is contained in the
			 * list of provided interfaces.
			 */
			lookedUpInterface =
					mgr.getTypeRepositoryManager().lookupInterface(interfaceLookupFQName);
			Assert.assertNotNull("Lookup of interface " + interfaceLookupFQName + " returned null",
					lookedUpInterface);
			final de.cau.se.slastic.metamodel.typeRepository.Signature signature =
					mgr.getTypeRepositoryManager().lookupSignature(lookedUpInterface, operationLookupName,
							this.signature.getReturnType(), this.signature.getParamTypeList());
			Assert.assertNotNull("Lookup of signature returned null", signature);
			Assert.assertTrue("Interface not contained in list of provided interfaces", lookedUpComponentType
					.getProvidedInterfaces().contains(lookedUpInterface));

			/* 3. Lookup container type and compare with record content */
			final String containerTypeLookupFQName =
					this.kiekerRecord.hostName
							+ AbstractModelReconstructionComponent.DEFAULT_TYPE_POSTFIX;
			lookedUpContainerType =
					mgr.getTypeRepositoryManager()
							.lookupExecutionContainerType(
									containerTypeLookupFQName);
			Assert.assertNotNull("Lookup of container type "
					+ containerTypeLookupFQName + " returned null",
					lookedUpContainerType);
			Assert.assertSame("Unexpected container type",
					lookedUpContainerType, slasticComponentExecRec
							.getDeploymentComponent().getExecutionContainer()
							.getExecutionContainerType());
		}

		/* Check the operation */
		final Operation lookedUpOperation;
		{
			lookedUpOperation =
					mgr.getTypeRepositoryManager().lookupOperation(
							lookedUpComponentType,
							operationLookupName,
							this.signature.getReturnType(),
							this.signature.getParamTypeList());
			Assert
					.assertNotNull(
							String
									.format(
											"Lookup of operation '%s %s(%s)' for component type '%s' returned null",
											this.signature.getReturnType(),
											operationLookupName,
											StringUtils.join(this.signature
													.getParamTypeList(), ","),
													lookedUpComponentType),
							lookedUpOperation);
			Assert.assertSame("Unexpected operation", slasticComponentExecRec
					.getOperation(), lookedUpOperation);
		}

		final AssemblyComponent lookedUpAssemblyComponent;
		{
			/* Check component assembly contents */
			/* 3. Lookup assembly component and compare with record content */
			lookedUpAssemblyComponent =
					mgr.getComponentAssemblyModelManager()
							.lookupAssemblyComponent(
									assemblyComponentLookupFQName);
			Assert.assertNotNull("Lookup of assembly component "
					+ assemblyComponentLookupFQName + " failed",
					lookedUpAssemblyComponent);
			Assert.assertSame("Unexpected assembly component",
					lookedUpAssemblyComponent, slasticComponentExecRec
							.getDeploymentComponent().getAssemblyComponent());
		}

		/* Check execution environment contents */
		/* 4. Lookup execution container and compare with record content */
		this
				.checkExecutionContainerAndType(
						mgr,
						this.kiekerRecord.hostName,
						this.kiekerRecord.hostName
								+ AbstractModelReconstructionComponent.DEFAULT_TYPE_POSTFIX,
						slasticComponentExecRec.getDeploymentComponent()
								.getExecutionContainer());
	}
}
