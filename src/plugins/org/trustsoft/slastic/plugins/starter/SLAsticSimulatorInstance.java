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
    private static final String PROP_NAME_PREFIX="slastic.simulation";
    private static final String PROP_NAME_FSREADER_INPUTDIR=PROP_NAME_PREFIX+".fsreader.inputdir";
    private static final String PROP_NAME_FSREADER_RTMODE=PROP_NAME_PREFIX+".fsreader.rtmode";
    private static final String PROP_NAME_FSREADER_RTNUMTHREADS=PROP_NAME_PREFIX+".fsreader.rtnumthreads";

    private String fsReaderInputDir;
    private boolean fsReaderRTMode = false;
    private int fsReaderRTNumThreads = -1;


    //private SimulationController simCtrl;
    //    SimPlanReceiver // delegiert an ctrl.

    /** No construction via default constructor. */
    private SLAsticSimulatorInstance(){}

    public SLAsticSimulatorInstance(final Properties props){
        this.props = props;
        this.initFromProps();
    }

    private void initFromProps() throws IllegalArgumentException{
        try {
        this.fsReaderInputDir = props.getProperty(PROP_NAME_FSREADER_INPUTDIR);
        this.fsReaderRTMode = props.getProperty(PROP_NAME_FSREADER_RTMODE, "").trim().toLowerCase().equals("true");
        this.fsReaderRTNumThreads = Integer.parseInt(props.getProperty(PROP_NAME_FSREADER_RTNUMTHREADS));

        if (this.fsReaderInputDir==null || this.fsReaderInputDir.length() == 0){
            throw new IllegalArgumentException("Missing or empty property "+PROP_NAME_FSREADER_INPUTDIR);
        }
        if (this.fsReaderRTMode && this.fsReaderRTNumThreads <= 0){
            throw new IllegalArgumentException("Missing, empty or invalid property "+PROP_NAME_FSREADER_RTNUMTHREADS);
        }

        } catch (Exception exc){
            log.error("Init error", exc);
            throw new IllegalArgumentException("Init error", exc);
        }
    }

    public void run(){
        log.info("Simulator instance started");
        test();
    }

    private void test() {
        /**  */
        FilesystemLogReplayer player = new FilesystemLogReplayer(this.fsReaderInputDir, this.fsReaderRTMode, this.fsReaderRTNumThreads);
        player.execute();
    }
}
