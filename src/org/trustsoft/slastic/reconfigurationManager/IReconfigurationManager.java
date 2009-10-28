/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.reconfigurationManager;

/**
 *
 * @author Andre van Hoorn
 */
public interface IReconfigurationManager {
    public void doReconfiguration(ReconfigurationPlanModel.SLAsticReconfigurationPlan plan) throws ReconfigurationException;
}
