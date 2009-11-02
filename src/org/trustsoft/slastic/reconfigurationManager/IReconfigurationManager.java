/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.reconfigurationManager;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

/**
 *
 * @author Andre van Hoorn
 */
public interface IReconfigurationManager {

	void doReconfiguration(SLAsticReconfigurationPlan plan) throws ReconfigurationException;

	void terminate();

	void execute();

}
