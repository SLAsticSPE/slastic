/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.reconfiguration;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

/**
 *
 * @author Andre van Hoorn
 */
public interface ISLAsticReconfigurationManager {

	void doReconfiguration(SLAsticReconfigurationPlan plan) throws SLAsticReconfigurationException;
}
