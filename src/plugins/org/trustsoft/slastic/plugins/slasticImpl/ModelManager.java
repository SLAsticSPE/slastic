/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trustsoft.slastic.plugins.slasticImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;
import org.trustsoft.slastic.control.components.events.ISLAsticEvent;
import org.trustsoft.slastic.control.components.modelManager.AbstractSLAsticModelManager;

/**
 *
 * @author Andre van Hoorn
 */
public class ModelManager extends AbstractSLAsticModelManager {

    private static final Log log = LogFactory.getLog(ModelManager.class);

   public void init(String initString) throws IllegalArgumentException {
        super.initVarsFromInitString(initString);
        // we don't expect init properties so far, so just return.
    }

    public void update(AbstractKiekerMonitoringRecord newRecord) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void doReconfiguration(SLAsticReconfigurationPlan plan) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void handleSLAsticEvent(ISLAsticEvent ev) {
        
    }
}
