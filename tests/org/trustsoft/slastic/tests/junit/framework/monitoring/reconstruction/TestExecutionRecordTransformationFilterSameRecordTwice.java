package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction;

import java.util.ArrayList;

import junit.framework.Assert;
import kieker.common.record.OperationExecutionRecord;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;

import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.monitoring.OperationExecution;

/**
 * Tests if the {@link ExecutionRecordTransformationFilter} filter correctly
 * re-uses existing model entities.
 * 
 * @author Andre van Hoorn
 */
public class TestExecutionRecordTransformationFilterSameRecordTwice extends
		AbstractReconstructionTest {

	final OperationExecutionRecord kiekerRecord =
			new OperationExecutionRecord();
	{
		this.kiekerRecord.setClassName("package.subpackage.classname");
		this.kiekerRecord.setEoi(77);
		this.kiekerRecord.setEss(98);
		this.kiekerRecord.setHostName("theHostname");
		this.kiekerRecord.setOperationName("com.ibatis.jpetstore.service.AccountService.getAccount(java.lang.String, java.lang.String)");
		this.kiekerRecord.setSessionId("ZUKGHGF435JJ");
		this.kiekerRecord.setTin(65656868l);
		this.kiekerRecord.setTout(9878787887l);
		this.kiekerRecord.setTraceId(88878787877887l);
	}

	public void testEntitiesReUsed() {
		/* Create type repository manager for empty type repository */
		final ModelManager modelManager = new ModelManager();

		final ExecutionRecordTransformationFilter execRecFilter =
				new ExecutionRecordTransformationFilter(modelManager,
				/* We test this with CLASS_NAME mode only */
				NameUtils.ABSTRACTION_MODE_CLASS);

		/* Used to receive the created operations from the filter */
		final ArrayList<OperationExecution> slasticExecRef =
				new ArrayList<OperationExecution>();

		/* Let the filter transform the same record twice */
		slasticExecRef.add(execRecFilter
				.transformExecutionRecord(this.kiekerRecord));
		slasticExecRef.add(execRecFilter
				.transformExecutionRecord(this.kiekerRecord));

		final OperationExecution slasticRecordA = slasticExecRef.get(0);
		final OperationExecution slasticRecordB = slasticExecRef.get(1);

		this.checkResult(slasticRecordA, slasticRecordB);
	}

	/**
	 * Performs a number of validity checks on the model and the record.
	 */
	private void checkResult(final OperationExecution slasticRecordA,
			final OperationExecution slasticRecordB) {
		Assert.assertNotNull("slasticRecordA must not be null", slasticRecordA);
		Assert.assertNotNull("slasticRecordB must not be null", slasticRecordB);

		Assert
				.assertTrue(
						"Expected type "
								+ DeploymentComponentOperationExecution.class
										.getName() + " but found "
								+ slasticRecordA.getClass().getName(),
						slasticRecordA instanceof DeploymentComponentOperationExecution);
		Assert
				.assertTrue(
						"Expected type "
								+ DeploymentComponentOperationExecution.class
										.getName() + " but found "
								+ slasticRecordB.getClass().getName(),
						slasticRecordB instanceof DeploymentComponentOperationExecution);

		final DeploymentComponentOperationExecution slasticComponentExecRecA =
				(DeploymentComponentOperationExecution) slasticRecordA;
		final DeploymentComponentOperationExecution slasticComponentExecRecB =
				(DeploymentComponentOperationExecution) slasticRecordB;

		{
			/*
			 * Compare all record elements:
			 */
			Assert.assertSame("Unexpected deployment component",
					slasticComponentExecRecA.getDeploymentComponent(),
					slasticComponentExecRecB.getDeploymentComponent());
			Assert.assertSame("Unexpected operation", slasticComponentExecRecA
					.getOperation(), slasticComponentExecRecB.getOperation());
			Assert.assertEquals("Unexpected session ID",
					slasticComponentExecRecA.getSessionId(),
					slasticComponentExecRecB.getSessionId());
			Assert.assertEquals("Unexpected trace ID", slasticComponentExecRecA
					.getTraceId(), slasticComponentExecRecB.getTraceId());
			Assert.assertEquals("Unexpected tin", slasticComponentExecRecA
					.getTin(), slasticComponentExecRecB.getTin());
			Assert.assertEquals("Unexpected tout", slasticComponentExecRecA
					.getTout(), slasticComponentExecRecB.getTout());
			Assert.assertEquals("Unexpected eoi", slasticComponentExecRecA
					.getEoi(), slasticComponentExecRecB.getEoi());
			Assert.assertEquals("Unexpected ess", slasticComponentExecRecA
					.getEss(), slasticComponentExecRecB.getEss());
		}
	}
}
