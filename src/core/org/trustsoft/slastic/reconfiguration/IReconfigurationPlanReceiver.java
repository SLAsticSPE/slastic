package org.trustsoft.slastic.reconfiguration;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan;

/**
 *
 * @author Andre van Hoorn
 */
public interface IReconfigurationPlanReceiver {

	@Deprecated
	void doReconfiguration(SLAsticReconfigurationPlan plan) throws ReconfigurationException;
	
	void doReconfiguration(ReconfigurationPlan plan) throws ReconfigurationException;
}
