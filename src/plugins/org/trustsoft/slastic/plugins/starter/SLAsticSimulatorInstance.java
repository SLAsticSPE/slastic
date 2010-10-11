package org.trustsoft.slastic.plugins.starter;

import java.util.Collection;
import java.util.Properties;

import kieker.analysis.AnalysisController;
import kieker.analysis.plugin.IMonitoringRecordConsumerPlugin;
import kieker.analysis.reader.filesystem.FSReader;
import kieker.common.record.IMonitoringRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.pcm.PCMModelReader;
import org.trustsoft.slastic.plugins.pcm.control.PCMModelSet;
import org.trustsoft.slastic.plugins.slasticImpl.ModelIOUtils;
import org.trustsoft.slastic.plugins.starter.reconfigurationPipe.SLAsticSimPlanReceiver;
import org.trustsoft.slastic.simulation.SimulationController;
import org.trustsoft.slastic.simulation.listeners.ReconfEventListener;
import org.trustsoft.slastic.simulation.model.interfaces.IReconfPlanReceiver;

import reconfMM.ReconfigurationModel;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

/**
 *
 * @author Andre van Hoorn
 */
public class SLAsticSimulatorInstance {

    private static final Log log = LogFactory.getLog(SLAsticSimulatorInstance.class);
    private Properties props;
    private static final String PROP_NAME_PREFIX = "slastic.simulation";
    private static final String PROP_NAME_FSREADER_INPUTDIRS = SLAsticSimulatorInstance.PROP_NAME_PREFIX
            + ".fsreader.inputdirs";
    private static final String PROP_NAME_FSREADER_RTMODE = SLAsticSimulatorInstance.PROP_NAME_PREFIX
            + ".fsreader.rtmode";
    private static final String PROP_NAME_FSREADER_RTNUMTHREADS = SLAsticSimulatorInstance.PROP_NAME_PREFIX
            + ".fsreader.rtnumthreads";
    private static final String PROP_NAME_RECONF_PIPENAME = SLAsticSimulatorInstance.PROP_NAME_PREFIX
            + ".reconfplanreceiver.pipeName";
    private static final String PROP_NAME_PCM_REPOSITORY_FN = SLAsticSimulatorInstance.PROP_NAME_PREFIX
            + ".pcmrepository_fn";
    private static final String PROP_NAME_PCM_SYSTEM_FN = SLAsticSimulatorInstance.PROP_NAME_PREFIX
            + ".pcmsystem_fn";
    private static final String PROP_NAME_PCM_RESOURCEENV_FN = SLAsticSimulatorInstance.PROP_NAME_PREFIX
            + ".pcmresourceenv_fn";
    private static final String PROP_NAME_PCM_ALLOCATION_FN = SLAsticSimulatorInstance.PROP_NAME_PREFIX
            + ".pcmallocation_fn";
    private static final String PROP_NAME_SLASTIC_RECONFIGURATIONMODEL_FN = SLAsticSimulatorInstance.PROP_NAME_PREFIX
            + ".slasticreconfigurationmodel_fn";
    private String fsReaderInputDir;
    private boolean fsReaderRTMode = false;
    private int fsReaderRTNumThreads = -1;
    private String reconfPipeName;
    private PCMModelSet pcmModel;
    private ReconfigurationModel slasticReconfigurationModel;
    private AnalysisController tpanInstance;
    private SimulationController simCtrl;
    private SLAsticSimPlanReceiver reconfPlanReceiver;

    /** No construction via default constructor. */
    @SuppressWarnings("unused")
	private SLAsticSimulatorInstance() {
    }

    public SLAsticSimulatorInstance(final Properties props) {
        this.props = props;
        initFromProps();
    }

    private void initFromProps() throws IllegalArgumentException {
        try {
            fsReaderInputDir = props.getProperty(SLAsticSimulatorInstance.PROP_NAME_FSREADER_INPUTDIRS);
            fsReaderRTMode = props.getProperty(
                    SLAsticSimulatorInstance.PROP_NAME_FSREADER_RTMODE, "").trim().toLowerCase().equals("true");
            fsReaderRTNumThreads = Integer.parseInt(props.getProperty(SLAsticSimulatorInstance.PROP_NAME_FSREADER_RTNUMTHREADS));

            reconfPipeName = props.getProperty(SLAsticSimulatorInstance.PROP_NAME_RECONF_PIPENAME);

            if ((fsReaderInputDir == null)
                    || (fsReaderInputDir.length() == 0)) {
                throw new IllegalArgumentException("Missing or empty property "
                        + SLAsticSimulatorInstance.PROP_NAME_FSREADER_INPUTDIRS);
            }
            if (fsReaderRTMode && (fsReaderRTNumThreads <= 0)) {
                throw new IllegalArgumentException(
                        "Missing, empty or invalid property "
                        + SLAsticSimulatorInstance.PROP_NAME_FSREADER_RTNUMTHREADS);
            }

            if ((reconfPipeName == null)
                    || (reconfPipeName.length() == 0)) {
                throw new IllegalArgumentException("Missing or empty property "
                        + SLAsticSimulatorInstance.PROP_NAME_RECONF_PIPENAME);
            }

            final String pcmRespositoryModel_fn = props.getProperty(SLAsticSimulatorInstance.PROP_NAME_PCM_REPOSITORY_FN);
            final String pcmSystemModel_fn = props.getProperty(SLAsticSimulatorInstance.PROP_NAME_PCM_SYSTEM_FN);
            final String pcmResourceEnvironmentModel_fn = props.getProperty(SLAsticSimulatorInstance.PROP_NAME_PCM_RESOURCEENV_FN);
            final String pcmAllocationModel_fn = props.getProperty(SLAsticSimulatorInstance.PROP_NAME_PCM_ALLOCATION_FN);
            final String slasticReconfigurationModel_fn = props.getProperty(SLAsticSimulatorInstance.PROP_NAME_SLASTIC_RECONFIGURATIONMODEL_FN);

            pcmModel = PCMModelReader.readPCMModel(pcmRespositoryModel_fn, pcmSystemModel_fn, pcmResourceEnvironmentModel_fn, pcmAllocationModel_fn);

            slasticReconfigurationModel = ModelIOUtils.readOLDReconfigurationModel(
                    slasticReconfigurationModel_fn);
            if (slasticReconfigurationModel == null) {
                SLAsticSimulatorInstance.log.error("Failed to read SLAstic reconfiguration model from file '"
                        + slasticReconfigurationModel_fn + "'");
                throw new IllegalArgumentException(
                        "Failed to read SLAstic reconfiguration model from file '"
                        + slasticReconfigurationModel_fn + "'");
            }
        } catch (final Exception exc) {
            SLAsticSimulatorInstance.log.error("Init error", exc);
            throw new IllegalArgumentException("Init error", exc);
        }
    }

    public void run() {
        /* Construct simulation instance */
        SLAsticSimulatorInstance.log.info("Instantiating Simulator with "
                + "Repository: " + pcmModel.getPCMRepository()
                + ", System: " + pcmModel.getPCMSystem()
                + ", Resource Environment: " + pcmModel.getPCMResourceEnvironment()
                + " Initial allocation: " + pcmModel.getPCMAllocation()
                + " reconf: " + slasticReconfigurationModel);
        simCtrl = new SimulationController("A name",
                pcmModel.getPCMRepository(),
                pcmModel.getPCMSystem(),
                pcmModel.getPCMResourceEnvironment(),
                pcmModel.getPCMAllocation(),
                slasticReconfigurationModel);
   
        /* Construct and start reconfiguration plan receiver */
        reconfPlanReceiver = new SLAsticSimPlanReceiver(
                reconfPipeName, new IReconfPlanReceiver() {

            @Override
            public void reconfigure(
                    final SLAsticReconfigurationPlan plan) {
                SLAsticSimulatorInstance.log.debug("Received plan "
                        + plan);
                simCtrl.getReconfigurationPlanReceiverPort().reconfigure(plan);
            }

            @Override
            public void addReconfigurationEventListener(
                    final ReconfEventListener listener) {
                simCtrl.getReconfigurationPlanReceiverPort().addReconfigurationEventListener(listener);
            }

            @Override
            public void removeReconfigurationEventListener(
                    final ReconfEventListener listener) {
                simCtrl.getReconfigurationPlanReceiverPort().removeReconfigurationEventListener(listener);
            }
        });
        reconfPlanReceiver.execute();

        /* Construct and start Tpan instance with FS reader */
        final FSReader r = new FSReader(new String[]{fsReaderInputDir});
        tpanInstance = new AnalysisController();
        tpanInstance.setLogReader(r);
        tpanInstance.registerPlugin(new IMonitoringRecordConsumerPlugin() {

            final IMonitoringRecordConsumerPlugin delegate = simCtrl.getRecordConsumerPluginPort();

            @Override
            public boolean newMonitoringRecord(final IMonitoringRecord record) {
                return delegate.newMonitoringRecord(record);
            }

            @Override
            public boolean execute() {
                return delegate.execute();
            }

            @Override
            public void terminate(final boolean error) {
                delegate.newMonitoringRecord(SimulationController.TERMINATION_RECORD);
                delegate.terminate(error);
            }

            @Override
			public Collection<Class<? extends IMonitoringRecord>> getRecordTypeSubscriptionList() {
                return null;
            }
        });
        try {
            SLAsticSimulatorInstance.log.info("Starting to read workload ...");
            tpanInstance.run();
            SLAsticSimulatorInstance.log.info("Finished reading workload ...");
        } catch (final Exception ex) {
            SLAsticSimulatorInstance.log.error("Exception occurred:", ex);
        }
        SLAsticSimulatorInstance.log.info("Simulation starting ...");
        simCtrl.start();
        SLAsticSimulatorInstance.log.info("Simulation ended ...");
    }
}
