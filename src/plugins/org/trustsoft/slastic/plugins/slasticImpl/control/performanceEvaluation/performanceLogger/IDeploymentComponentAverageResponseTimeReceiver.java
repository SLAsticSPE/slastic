package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;

/**
 * 
 * @author Andre van Hoorn
 *
 */
public interface IDeploymentComponentAverageResponseTimeReceiver {

	public void update(final long currentTimestampMillis,
			final DeploymentComponent deplComp, final Double avgRTNanos);
}
