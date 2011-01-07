package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction;

import junit.framework.Assert;
import kieker.common.record.OperationExecutionRecord;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ModelEntityFactory;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.monitoring.OperationExecution;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;

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

	private final OperationExecutionRecord kiekerRecord =
			new OperationExecutionRecord();
	{
		this.kiekerRecord.className = "package.subpackage.classname";
		this.kiekerRecord.eoi = 77;
		this.kiekerRecord.ess = 98;
		this.kiekerRecord.hostName = "theHostname";
		this.kiekerRecord.operationName = "theOpName";
		this.kiekerRecord.sessionId = "ZUKGHGF435JJ";
		this.kiekerRecord.tin = 65656868l;
		this.kiekerRecord.tout = 9878787887l;
		this.kiekerRecord.traceId = 88878787877887l;
	}

	public void testTransformRecordEmptyModel() {
		/* Create type repository manager for empty type repository */
		final ModelManager modelManager = new ModelManager();

		final ExecutionRecordTransformationFilter execRecFilter =
				new ExecutionRecordTransformationFilter(modelManager);

		final OperationExecution slasticRecord =
				execRecFilter.transformExecutionRecord(this.kiekerRecord);

		this.checkResult(modelManager, slasticRecord);
	}

	/**
	 * Performs a number of validity checks on the model and the record.
	 * 
	 * @param mgr
	 * @param slasticExecRec
	 */
	private void checkResult(final ModelManager mgr,
			final OperationExecution slasticExecRec) {
		Assert.assertNotNull("slasticExecRec must not be null", slasticExecRec);

		Assert.assertTrue("Expected type "
				+ DeploymentComponentOperationExecution.class.getName()
				+ " but found " + slasticExecRec.getClass().getName(),
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

		// TODO: operation

		final ComponentType lookedUpComponentType;
		final ExecutionContainerType lookedUpContainerType;
		{
			/* Check type repository contents */
			/* 1. Lookup component type by name and compare with record content */
			final String componentTypeLookupFQName =
					this.kiekerRecord.className
							+ ModelEntityFactory.DEFAULT_TYPE_POSTFIX;
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
			/* 2. Lookup container type and compare with record content */
			final String containerTypeLookupFQName =
					this.kiekerRecord.hostName
							+ ModelEntityFactory.DEFAULT_TYPE_POSTFIX;
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

		final AssemblyComponent lookedUpAssemblyComponent;
		{
			/* Check component assembly contents */
			/* 3. Lookup assembly component and compare with record content */
			final String assemblyComponentLookupFQName =
					this.kiekerRecord.className;
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
		this.checkExecutionContainerAndType(mgr, this.kiekerRecord.hostName,
				this.kiekerRecord.hostName
						+ ModelEntityFactory.DEFAULT_TYPE_POSTFIX,
				slasticComponentExecRec.getDeploymentComponent()
						.getExecutionContainer());
	}
}
