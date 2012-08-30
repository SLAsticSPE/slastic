package kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.typeRepository.Operation;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public interface IAssemblyComponentMedianOperationExecutionResponseTimeReceiver {

	public void update(
			final long currentTimestampMillis,
			final AssemblyComponent assemblyComponent,
			final Operation operation, final Double medianRTNanos);
}
