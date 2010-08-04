/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trustsoft.slastic.plugins.slasticImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import org.trustsoft.slastic.common.event.IObservationEvent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.control.components.modelManager.AbstractModelManagerComponent;

/**
 *
 * @author Andre van Hoorn
 */
public class ModelManager extends AbstractModelManagerComponent {

   private static final Log log = LogFactory.getLog(ModelManager.class);

   public void init(String initString) throws IllegalArgumentException {
        super.initVarsFromInitString(initString);
        // we don't expect init properties so far, so just return.
    }

    public void doReconfiguration(SLAsticReconfigurationPlan plan) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void handleEvent(IEvent ev) {
        
    }

    public void newObservation(IObservationEvent ioe) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
