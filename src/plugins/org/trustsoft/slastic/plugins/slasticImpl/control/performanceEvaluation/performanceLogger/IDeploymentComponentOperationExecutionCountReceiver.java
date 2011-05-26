package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.typeRepository.Operation;

/**
 * 
 * @author Andre van Hoorn
 *
 */
public interface IDeploymentComponentOperationExecutionCountReceiver {

	public void update(final long currentTimestampMillis,
			final DeploymentComponent deplComp, final Operation operation,  final Long count);
}
