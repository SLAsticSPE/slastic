/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.control.plugins.slasticImpl;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;
import org.trustsoft.slastic.control.components.modelManager.AbstractSLAsticModelManager;

/**
 *
 * @author Andre van Hoorn
 */
public class ModelManager extends AbstractSLAsticModelManager {

    public void update(AbstractKiekerMonitoringRecord newRecord) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void doReconfiguration(SLAsticReconfigurationPlan plan) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
