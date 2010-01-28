package org.trustsoft.slastic.common;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class SLAsticSimulatorInstance {
    private static final Log log = LogFactory.getLog(SLAsticSimulatorInstance.class);

    //private SimulationController simCtrl;
    //    SimPlanReceiver // delegiert an ctrl.

    

    /** No construction via default constructor. */
    private SLAsticSimulatorInstance(){}

    public SLAsticSimulatorInstance(Properties props){

    }

    public void run(){
        log.info("Simulator instance started");
    }
}
