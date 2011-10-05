package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction.usage;

import java.util.Collection;

import junit.framework.TestCase;

import org.exolab.jms.net.connector.IllegalStateException;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction.TraceReconstructor;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;
import org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction.exampleTraces.BookstoreTraceFactory;

import de.cau.se.slastic.metamodel.monitoring.OperationExecution;
import de.cau.se.slastic.metamodel.usage.MessageTrace;

/**
 * Tests the method
 * {@link TraceReconstructor#reconstructTrace(java.util.List, de.cau.se.slastic.metamodel.monitoring.OperationExecution)}
 * .
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestTraceReconstruction extends TestCase {
	
	/**
	 * 
	 * @throws IllegalStateException
	 */
	public void testReconstructionOfValidTrace() throws IllegalStateException {
		final ModelManager systemModelManager = new ModelManager();
		final ExecutionRecordTransformationFilter execRecFilter =
				new ExecutionRecordTransformationFilter(systemModelManager,
						NameUtils.ABSTRACTION_MODE_CLASS);
		final long traceId = 76767676l;
		
		final Collection<? extends OperationExecution> bookstoreTrace =
			BookstoreTraceFactory.createBookstoreTrace(execRecFilter, traceId);
		
		// TODO: register rootExecution in Usage Model
		
		final MessageTrace mt = TraceReconstructor.reconstructTrace(bookstoreTrace, null);
		
		
	}
}
