package org.trustsoft.slastic.plugins.starter;

import java.util.Properties;
import kieker.common.tools.logReplayer.FilesystemLogReplayer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class SLAsticSimulatorInstance {
    private static final Log log = LogFactory.getLog(SLAsticSimulatorInstance.class);

    private Properties props;

    //private SimulationController simCtrl;
    //    SimPlanReceiver // delegiert an ctrl.

    /** No construction via default constructor. */
    private SLAsticSimulatorInstance(){}

    public SLAsticSimulatorInstance(final Properties props){
        this.props = props;
    }

    public void run(){
        log.info("Simulator instance started");
        test();
    }

    private void test() {
        FilesystemLogReplayer player = new FilesystemLogReplayer("tmp/tpmon-20100126-154804106/", true, 5);
        player.execute();
    }
}
