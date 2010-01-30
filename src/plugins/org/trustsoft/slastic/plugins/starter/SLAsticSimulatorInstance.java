package org.trustsoft.slastic.plugins.starter;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import java.util.Properties;
import kieker.common.tools.logReplayer.FilesystemLogReplayer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.starter.reconfigurationPipe.SLAsticSimPlanReceiver;
import org.trustsoft.slastic.simulation.SimulationController;
import org.trustsoft.slastic.simulation.listeners.ReconfEventListener;
import org.trustsoft.slastic.simulation.model.interfaces.IReconfPlanReceiver;

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

    private static final String PROP_NAME_RECONF_PIPENAME=PROP_NAME_PREFIX+".reconfplanreceiver.pipeName";

    private static final String PROP_NAME_PCM_REPOSITORY_FN = PROP_NAME_PREFIX+".pcmrepository_fn";
    private static final String PROP_NAME_PCM_SYSTEM_FN = PROP_NAME_PREFIX+".pcmsystem_fn";
    private static final String PROP_NAME_PCM_RESOURCEENV_FN = PROP_NAME_PREFIX+".pcmresourceenv_fn";
    private static final String PROP_NAME_PCM_ALLOCATION_FN = PROP_NAME_PREFIX+".pcmallocation_fn";

    private static final String PROP_NAME_SLASTIC_RECONFIGURATIONMODEL_FN = PROP_NAME_PREFIX+".slasticreconfigurationmodel_fn";

    private String fsReaderInputDir;
    private boolean fsReaderRTMode = false;
    private int fsReaderRTNumThreads = -1;
    private String reconfPipeName;

    private String pcmRespositoryModel_fn;
    private String pcmSystemModel_fn;
    private String pcmResourceEnvironmentModel_fn;
    private String pcmAllocationModel_fn;
    private String slasticReconfigurationModel_fn;

    private SimulationController simCtrl;
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

        this.reconfPipeName = props.getProperty(PROP_NAME_RECONF_PIPENAME);

        if (this.fsReaderInputDir==null || this.fsReaderInputDir.length() == 0){
            throw new IllegalArgumentException("Missing or empty property "+PROP_NAME_FSREADER_INPUTDIR);
        }
        if (this.fsReaderRTMode && this.fsReaderRTNumThreads <= 0){
            throw new IllegalArgumentException("Missing, empty or invalid property "+PROP_NAME_FSREADER_RTNUMTHREADS);
        }

        if (this.reconfPipeName==null || this.reconfPipeName.length() == 0){
            throw new IllegalArgumentException("Missing or empty property "+PROP_NAME_RECONF_PIPENAME);
        }

        } catch (Exception exc){
            log.error("Init error", exc);
            throw new IllegalArgumentException("Init error", exc);
        }
    }

    public void run(){
//final String name, final Repository repos,
//final System struct, final ResourceEnvironment resourceEnv,
//final Allocation initAllocation,
//final ReconfigurationModel reconfModel) {
        this.simCtrl = new SimulationController(reconfPipeName, null, null, null, null, null);

        log.info("Simulator instance started");
        test();
    }

    private void test() {
        /**  */
        FilesystemLogReplayer player = new FilesystemLogReplayer(this.fsReaderInputDir, this.fsReaderRTMode, this.fsReaderRTNumThreads);
        player.execute();

        SLAsticSimPlanReceiver reconfPlanReceiver = new SLAsticSimPlanReceiver(this.reconfPipeName, new IReconfPlanReceiver() {

            public void reconfigure(SLAsticReconfigurationPlan plan, ReconfEventListener listener) {
                log.info("Received plan " + plan);
                // TODO: here, we would delegate to the simulator
                log.info("Sending confirmation to listener " + listener);
                listener.notifyPlanDone(plan);
            }

            public void reconfigure(SLAsticReconfigurationPlan plan) {
                log.info("Received plan " + plan);
            }

            public void addReconfigurationEventListener(ReconfEventListener listener) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void removeReconfigurationEventListener(ReconfEventListener listener) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }
}