package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction.usage;

import java.util.Collection;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.exolab.jms.net.connector.IllegalStateException;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction.TraceReconstructor;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;
import org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction.exampleTraces.BookstoreTraceFactory;

import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.monitoring.MonitoringFactory;
import de.cau.se.slastic.metamodel.monitoring.OperationExecution;

/**
 * Tests the method
 * {@link TraceReconstructor#reconstructTrace(java.util.List, de.cau.se.slastic.metamodel.monitoring.OperationExecution)}
 * .
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestTraceReconstructionInvalidTraces extends TestCase {
	// TODO: acquire rootExecution from Usage Model
	private static final DeploymentComponentOperationExecution rootExec =
			MonitoringFactory.eINSTANCE.createDeploymentComponentOperationExecution();

	/**
	 * Makes sure, that a broken trace (manipulated eoi) cannot be correctly reconstructed.
	 * 
	 * @throws IllegalStateException
	 */
	public void testReconstructionOfInvalidTraceSkipEoi() {
		final int eoiToBreak = 2; // 2: CRM.getOffers
		
		final ModelManager systemModelManager = new ModelManager();
		final ExecutionRecordTransformationFilter execRecFilter =
				new ExecutionRecordTransformationFilter(systemModelManager,
						NameUtils.ABSTRACTION_MODE_CLASS);
		final long traceId = 76767676l;

		final Collection<? extends OperationExecution> bookstoreTrace =
				BookstoreTraceFactory.createBookstoreTrace(execRecFilter, traceId);
		final java.util.Iterator<? extends OperationExecution> it = bookstoreTrace.iterator();
		OperationExecution opExecToBrake = it.next();
		while (opExecToBrake.getEoi() != eoiToBreak) {
			if (!it.hasNext()) {
				Assert.fail("Invalid test: No execution with eoi " + eoiToBreak);
			}
			
			opExecToBrake = it.next(); // simply skip iterator forward
		}
		opExecToBrake.setEoi(88);
		
		try {
			TraceReconstructor.reconstructTrace(bookstoreTrace, TestTraceReconstructionInvalidTraces.rootExec);
			Assert.fail("Expected IllegalStateException to be thrown for broken trace");
		} catch (final IllegalStateException e) { /* we expect this exception to be thrown! */ }
	}
	
	/**
	 * Makes sure, that a broken trace (manipulated ess) cannot be correctly reconstructed.
	 * 
	 * @throws IllegalStateException
	 */
	public void testReconstructionOfInvalidTraceSkipEss() {
		final int eoiToBreak = 1; // 2: Catalog.getBook (with ess 1)
		
		final ModelManager systemModelManager = new ModelManager();
		final ExecutionRecordTransformationFilter execRecFilter =
				new ExecutionRecordTransformationFilter(systemModelManager,
						NameUtils.ABSTRACTION_MODE_CLASS);
		final long traceId = 76767676l;

		final Collection<? extends OperationExecution> bookstoreTrace =
				BookstoreTraceFactory.createBookstoreTrace(execRecFilter, traceId);
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
			TraceReconstructor.reconstructTrace(bookstoreTrace, TestTraceReconstructionInvalidTraces.rootExec);
			Assert.fail("Expected IllegalStateException to be thrown for broken trace");
		} catch (final IllegalStateException e) { /* we expect this exception to be thrown! */ }
	}
}
