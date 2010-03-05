package org.trustsoft.slastic.reconfiguration;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

/**
 *
 * @author Andre van Hoorn
 */
public interface ISLAsticReconfigurationPlanReceiver {

	void doReconfiguration(SLAsticReconfigurationPlan plan) throws SLAsticReconfigurationException;
}
