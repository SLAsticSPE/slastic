package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;

/**
 * 
 * @author Andre van Hoorn
 *
 */
public interface IAssemblyComponentAverageResponseTimeReceiver {

	public void update(final long currentTimestampMillis,
			final AssemblyComponent assemblyComponent, final Double avgRTNanos);
}
