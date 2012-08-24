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

package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction.usage;

import java.util.Collection;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.exolab.jms.net.connector.IllegalStateException;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction.TraceReconstructor;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.model.usage.UsageModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;
import org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction.exampleTraces.BookstoreTraceFactory;

import de.cau.se.slastic.metamodel.monitoring.OperationExecution;
import de.cau.se.slastic.metamodel.usage.ExecutionTrace;
import de.cau.se.slastic.metamodel.usage.InvalidExecutionTrace;

/**
 * Tests the method {@link TraceReconstructor#reconstructMessageTrace(java.util.List, de.cau.se.slastic.metamodel.monitoring.OperationExecution)}.
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestTraceReconstructionInvalidTraces extends TestCase {
	/**
	 * Makes sure, that a broken trace (manipulated eoi) cannot be correctly
	 * reconstructed by {@link TraceReconstructor#reconstructMessageTrace(Collection, OperationExecution)} .
	 * 
	 * @throws IllegalStateException
	 */
	public void testReconstructionOfInvalidTraceSkipEoi() {
		final int eoiToBreak = 2; // 2: CRM.getOffers

		final ModelManager systemModelManager = new ModelManager();
		final ExecutionRecordTransformationFilter execRecFilter = new ExecutionRecordTransformationFilter(systemModelManager, NameUtils.ABSTRACTION_MODE_CLASS);
		final long traceId = 76767676l;

		final Collection<? extends OperationExecution> bookstoreTrace =
				BookstoreTraceFactory.createBookstoreTrace(execRecFilter, traceId);
		this.breakTrace(bookstoreTrace, eoiToBreak);

		try {
			TraceReconstructor.reconstructMessageTrace(bookstoreTrace, UsageModelManager.rootExec);
			Assert.fail("Expected IllegalStateException to be thrown for broken trace");
		} catch (final IllegalStateException e) {
			// we expect this exception to be thrown!
		}
	}

	/**
	 * Tests the method {@link TraceReconstructor#reconstructTraceSave(Collection, OperationExecution)} for an invalid trace.
	 */
	public void testReconstructionOfInValidTraceSkipEoiSave() {
		final int eoiToBreak = 2; // 2: CRM.getOffers

		final ModelManager systemModelManager = new ModelManager();
		final ExecutionRecordTransformationFilter execRecFilter = new ExecutionRecordTransformationFilter(systemModelManager, NameUtils.ABSTRACTION_MODE_CLASS);
		final long traceId = 76768676l;

		final Collection<? extends OperationExecution> bookstoreTrace = BookstoreTraceFactory.createBookstoreTrace(execRecFilter, traceId);
		this.breakTrace(bookstoreTrace, eoiToBreak);

		final ExecutionTrace et = TraceReconstructor.reconstructTraceSave(bookstoreTrace, UsageModelManager.rootExec);

		{ /* Check results */
			Assert.assertTrue("Expected execution trace to be instance of " + InvalidExecutionTrace.class + "; found: " + et.getClass(),
					et instanceof InvalidExecutionTrace);
			final InvalidExecutionTrace ivet = (InvalidExecutionTrace) et;
			// note that in invalid trace has no trace id and no associated
			// message trace
			Assert.assertEquals("invalid execution trace as unexpected number of executions",
					BookstoreTraceFactory.NUM_EXECUTIONS_PER_TRACE, ivet.getOperationExecutions().size());
		}
	}

	/**
	 * Turns the given trace into an invalid trace by changing the eoi of the
	 * execution with the given eoi.
	 * 
	 * @param bookstoreTrace
	 * @param eoiToBreak
	 */
	private void breakTrace(final Collection<? extends OperationExecution> bookstoreTrace, final int eoiToBreak) {
		final java.util.Iterator<? extends OperationExecution> it = bookstoreTrace.iterator();
		OperationExecution opExecToBrake = it.next();
		while (opExecToBrake.getEoi() != eoiToBreak) {
			if (!it.hasNext()) {
				Assert.fail("Invalid test: No execution with eoi " + eoiToBreak);
			}

			opExecToBrake = it.next(); // simply skip iterator forward
		}
		opExecToBrake.setEoi(88);
	}

	/**
	 * Makes sure, that a broken trace (manipulated ess) cannot be correctly
	 * reconstructed.
	 * 
	 * @throws IllegalStateException
	 */
	public void testReconstructionOfInvalidTraceSkipEss() {
		final int eoiToBreak = 1; // 2: Catalog.getBook (with ess 1)

		final ModelManager systemModelManager = new ModelManager();
		final ExecutionRecordTransformationFilter execRecFilter = new ExecutionRecordTransformationFilter(systemModelManager, NameUtils.ABSTRACTION_MODE_CLASS);
		final long traceId = 76767676l;

		final Collection<? extends OperationExecution> bookstoreTrace = BookstoreTraceFactory.createBookstoreTrace(execRecFilter, traceId);
		final java.util.Iterator<? extends OperationExecution> it = bookstoreTrace.iterator();
		OperationExecution opExecToBrake = it.next();
		while (opExecToBrake.getEoi() != eoiToBreak) {
			if (!it.hasNext()) {
				Assert.fail("Invalid test: No execution with eoi " + eoiToBreak);
			}

			opExecToBrake = it.next(); // simply skip iterator forward
		}
		opExecToBrake.setEss(2);

		try {
			TraceReconstructor.reconstructMessageTrace(bookstoreTrace, UsageModelManager.rootExec);
			Assert.fail("Expected IllegalStateException to be thrown for broken trace");
		} catch (final IllegalStateException e) {
			// we expect this exception to be thrown!
		}
	}
}
