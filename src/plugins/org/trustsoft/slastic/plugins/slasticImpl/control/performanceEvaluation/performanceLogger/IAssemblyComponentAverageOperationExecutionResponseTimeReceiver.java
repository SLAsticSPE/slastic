package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.typeRepository.Operation;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public interface IAssemblyComponentAverageOperationExecutionResponseTimeReceiver {

	public void update(
			final long currentTimestampMillis,
			final AssemblyComponent assemblyComponent,
			final Operation operation, final Double avgRTNanos);
}
