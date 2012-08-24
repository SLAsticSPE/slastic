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

package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction;

import java.util.ArrayList;

import junit.framework.Assert;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;

import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.monitoring.OperationExecution;

import kieker.common.record.controlflow.OperationExecutionRecord;
import kieker.common.util.ClassOperationSignaturePair;
import kieker.common.util.Signature;

/**
 * Tests if the {@link ExecutionRecordTransformationFilter} filter correctly
 * re-uses existing model entities.
 * 
 * @author Andre van Hoorn
 */
public class TestExecutionRecordTransformationFilterSameRecordTwice extends AbstractReconstructionTest {

	private final String packageName = "com.ibatis.jpetstore.service";
	private final String classNameNoPackage = "AccountService";
	private final Signature signature = new Signature("getAccount", new String[] {}, "returnType", new String[] { "java.lang.String", "java.lang.String" });

	final OperationExecutionRecord kiekerRecord;
	{
		final String fqClassname = this.packageName + "." + this.classNameNoPackage;
		final String operationSignatureStr = ClassOperationSignaturePair.createOperationSignatureString(fqClassname, this.signature);
		final String sessionId = "ZUKGHGF435JJ";
		final long traceId = 88878787877887l;
		final long tin = 65656868l;
		final long tout = 9878787887l;
		final String hostname = "theHostname";
		final int eoi = 77;
		final int ess = 98;

		this.kiekerRecord = new OperationExecutionRecord(operationSignatureStr, sessionId, traceId, tin, tout, hostname, eoi, ess);
	}

	public void testEntitiesReUsed() {
		/* Create type repository manager for empty type repository */
		final ModelManager modelManager = new ModelManager();

		final ExecutionRecordTransformationFilter execRecFilter =
				new ExecutionRecordTransformationFilter(modelManager,
						/* We test this with CLASS_NAME mode only */
						NameUtils.ABSTRACTION_MODE_CLASS);

		/* Used to receive the created operations from the filter */
		final ArrayList<OperationExecution> slasticExecRef = new ArrayList<OperationExecution>();

		/* Let the filter transform the same record twice */
		slasticExecRef.add(execRecFilter.transformExecutionRecord(this.kiekerRecord));
		slasticExecRef.add(execRecFilter.transformExecutionRecord(this.kiekerRecord));

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

		Assert.assertTrue("Expected type " + DeploymentComponentOperationExecution.class.getName() + " but found " + slasticRecordA.getClass().getName(),
				slasticRecordA instanceof DeploymentComponentOperationExecution);
		Assert.assertTrue("Expected type " + DeploymentComponentOperationExecution.class.getName() + " but found " + slasticRecordB.getClass().getName(),
				slasticRecordB instanceof DeploymentComponentOperationExecution);

		final DeploymentComponentOperationExecution slasticComponentExecRecA = (DeploymentComponentOperationExecution) slasticRecordA;
		final DeploymentComponentOperationExecution slasticComponentExecRecB = (DeploymentComponentOperationExecution) slasticRecordB;

		{
			/*
			 * Compare all record elements:
			 */
			Assert.assertSame("Unexpected deployment component",
					slasticComponentExecRecA.getDeploymentComponent(), slasticComponentExecRecB.getDeploymentComponent());
			Assert.assertSame("Unexpected operation", slasticComponentExecRecA.getOperation(), slasticComponentExecRecB.getOperation());
			Assert.assertEquals("Unexpected session ID", slasticComponentExecRecA.getSessionId(), slasticComponentExecRecB.getSessionId());
			Assert.assertEquals("Unexpected trace ID", slasticComponentExecRecA.getTraceId(), slasticComponentExecRecB.getTraceId());
			Assert.assertEquals("Unexpected tin", slasticComponentExecRecA.getTin(), slasticComponentExecRecB.getTin());
			Assert.assertEquals("Unexpected tout", slasticComponentExecRecA.getTout(), slasticComponentExecRecB.getTout());
			Assert.assertEquals("Unexpected eoi", slasticComponentExecRecA.getEoi(), slasticComponentExecRecB.getEoi());
			Assert.assertEquals("Unexpected ess", slasticComponentExecRecA.getEss(), slasticComponentExecRecB.getEss());
		}
	}
}
