package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;

/**
 * 
 * @author Andre van Hoorn
 *
 */
public interface IDeploymentComponentOperationExecutionCountReceiver {

	public void update(final long currentTimestampMillis,
			final DeploymentComponent deplComp, final Long count);
}
