package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.typeRepository.Operation;

/**
 * 
 * @author Andre van Hoorn
 *
 */
public interface IAssemblyComponentOperationExecutionCountReceiver {

	public void update(final long currentTimestampMillis,
			final AssemblyComponent assemblyComponent, Operation operation, final Long count);
}
