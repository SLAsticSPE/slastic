package org.trustsoft.slastic.reconfiguration;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

/**
 *
 * @author Andre van Hoorn
 */
public interface IReconfigurationPlanReceiver {

	void doReconfiguration(SLAsticReconfigurationPlan plan) throws ReconfigurationException;
}
