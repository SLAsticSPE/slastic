package org.trustsoft.slastic.plugins.starter;

import java.util.Collection;
import java.util.Properties;
import kieker.common.record.MonitoringRecordReceiverException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.pcm.PCMModelReader;
import org.trustsoft.slastic.plugins.slasticImpl.ModelIOUtils;
import org.trustsoft.slastic.plugins.starter.reconfigurationPipe.SLAsticSimPlanReceiver;
import org.trustsoft.slastic.simulation.SimulationController;
import org.trustsoft.slastic.simulation.listeners.ReconfEventListener;
import org.trustsoft.slastic.simulation.model.interfaces.IReconfPlanReceiver;

import reconfMM.ReconfigurationModel;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.system.System;
import kieker.analysis.AnalysisInstance;
import kieker.analysis.plugin.IMonitoringRecordConsumerPlugin;
import kieker.analysis.reader.filesystem.FSReader;
import kieker.common.record.IMonitoringRecord;

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
    private String pcmRespositoryModel_fn;
    private String pcmSystemModel_fn;
    private String pcmResourceEnvironmentModel_fn;
    private String pcmAllocationModel_fn;
    private String slasticReconfigurationModel_fn;
    private Repository pcmRepository;
    private System pcmSystem;
    private Allocation pcmAllocation;
    private ResourceEnvironment pcmResourceEnvironment;
    private ReconfigurationModel slasticReconfigurationModel;
    private AnalysisInstance tpanInstance;
    private SimulationController simCtrl;
    private SLAsticSimPlanReceiver reconfPlanReceiver;

    /** No construction via default constructor. */
    private SLAsticSimulatorInstance() {
    }

    public SLAsticSimulatorInstance(final Properties props) {
        this.props = props;
        this.initFromProps();
    }

    private void initFromProps() throws IllegalArgumentException {
        try {
            this.fsReaderInputDir = this.props.getProperty(SLAsticSimulatorInstance.PROP_NAME_FSREADER_INPUTDIRS);
            this.fsReaderRTMode = this.props.getProperty(
                    SLAsticSimulatorInstance.PROP_NAME_FSREADER_RTMODE, "").trim().toLowerCase().equals("true");
            this.fsReaderRTNumThreads = Integer.parseInt(this.props.getProperty(SLAsticSimulatorInstance.PROP_NAME_FSREADER_RTNUMTHREADS));

            this.reconfPipeName = this.props.getProperty(SLAsticSimulatorInstance.PROP_NAME_RECONF_PIPENAME);

            if (this.fsReaderInputDir == null
                    || this.fsReaderInputDir.length() == 0) {
                throw new IllegalArgumentException("Missing or empty property "
                        + SLAsticSimulatorInstance.PROP_NAME_FSREADER_INPUTDIRS);
            }
            if (this.fsReaderRTMode && this.fsReaderRTNumThreads <= 0) {
                throw new IllegalArgumentException(
                        "Missing, empty or invalid property "
                        + SLAsticSimulatorInstance.PROP_NAME_FSREADER_RTNUMTHREADS);
            }

            if (this.reconfPipeName == null
                    || this.reconfPipeName.length() == 0) {
                throw new IllegalArgumentException("Missing or empty property "
                        + SLAsticSimulatorInstance.PROP_NAME_RECONF_PIPENAME);
            }

            this.pcmRespositoryModel_fn = this.props.getProperty(SLAsticSimulatorInstance.PROP_NAME_PCM_REPOSITORY_FN);
            this.pcmSystemModel_fn = this.props.getProperty(SLAsticSimulatorInstance.PROP_NAME_PCM_SYSTEM_FN);
            this.pcmAllocationModel_fn = this.props.getProperty(SLAsticSimulatorInstance.PROP_NAME_PCM_ALLOCATION_FN);
            this.pcmResourceEnvironmentModel_fn = this.props.getProperty(SLAsticSimulatorInstance.PROP_NAME_PCM_RESOURCEENV_FN);
            this.slasticReconfigurationModel_fn = this.props.getProperty(SLAsticSimulatorInstance.PROP_NAME_SLASTIC_RECONFIGURATIONMODEL_FN);

            this.pcmRepository = PCMModelReader.readRepository(
                    this.pcmRespositoryModel_fn);
            if (this.pcmRepository == null) {
                SLAsticSimulatorInstance.log.error("Failed to read PCM repository from file '"
                        + this.pcmRespositoryModel_fn + "'");
                throw new IllegalArgumentException(
                        "Failed to read PCM repository from file '"
                        + this.pcmRespositoryModel_fn + "'");
            } else {
                SLAsticSimulatorInstance.log.info("Loaded repository: "
                        + this.pcmRepository.getEntityName());
            }
            this.pcmSystem = PCMModelReader.readSystem(
                    this.pcmSystemModel_fn);
            if (this.pcmSystem == null) {
                SLAsticSimulatorInstance.log.error("Failed to read PCM system from file '"
                        + this.pcmSystemModel_fn + "'");
                throw new IllegalArgumentException(
                        "Failed to read PCM system from file '"
                        + this.pcmSystemModel_fn + "'");
            }
            this.pcmAllocation = PCMModelReader.readAllocation(
                    this.pcmAllocationModel_fn);
            if (this.pcmAllocation == null) {
                SLAsticSimulatorInstance.log.error("Failed to read PCM allocation from file '"
                        + this.pcmAllocationModel_fn + "'");
                throw new IllegalArgumentException(
                        "Failed to read PCM allocation from file '"
                        + this.pcmAllocationModel_fn + "'");
            }
            this.pcmResourceEnvironment = PCMModelReader.readResourceEnvironment(
                    this.pcmResourceEnvironmentModel_fn);
            if (this.pcmResourceEnvironment == null) {
                SLAsticSimulatorInstance.log.error("Failed to read PCM resource environment from file '"
                        + this.pcmResourceEnvironmentModel_fn + "'");
                throw new IllegalArgumentException(
                        "Failed to read PCM resource environment from file '"
                        + this.pcmResourceEnvironmentModel_fn + "'");
            }
            this.slasticReconfigurationModel = ModelIOUtils.readOLDReconfigurationModel(
                    this.slasticReconfigurationModel_fn);
            if (this.slasticReconfigurationModel == null) {
                SLAsticSimulatorInstance.log.error("Failed to read SLAstic reconfiguration model from file '"
                        + this.slasticReconfigurationModel_fn + "'");
                throw new IllegalArgumentException(
                        "Failed to read SLAstic reconfiguration model from file '"
                        + this.slasticReconfigurationModel_fn + "'");
            }
        } catch (final Exception exc) {
            SLAsticSimulatorInstance.log.error("Init error", exc);
            throw new IllegalArgumentException("Init error", exc);
        }
    }

    public void run() {
        /* Construct simulation instance */
        SLAsticSimulatorInstance.log.info("Instatiating Simulator with: Repository "
                + this.pcmRepository + ", System " + this.pcmSystem
                + " Resource Environment "
                + this.pcmResourceEnvironment + " Initial allocation "
                + this.pcmAllocation + " reconf "
                + this.slasticReconfigurationModel);
        this.simCtrl = new SimulationController("A name", this.pcmRepository,
                this.pcmSystem, this.pcmResourceEnvironment,
                this.pcmAllocation, this.slasticReconfigurationModel);

        /* Construct and start reconfiguration plan receiver */
        this.reconfPlanReceiver = new SLAsticSimPlanReceiver(
                this.reconfPipeName, new IReconfPlanReceiver() {

            public void reconfigure(
                    final SLAsticReconfigurationPlan plan,
                    final ReconfEventListener listener) {
                SLAsticSimulatorInstance.log.info("Received plan "
                        + plan);
                // TODO: here, we would delegate to the simulator
                SLAsticSimulatorInstance.log.info("Sending confirmation to listener "
                        + listener);
                listener.notifyPlanDone(plan);
            }

            public void reconfigure(
                    final SLAsticReconfigurationPlan plan) {
                SLAsticSimulatorInstance.log.info("Received plan "
                        + plan);
            }

            public void addReconfigurationEventListener(
                    final ReconfEventListener listener) {
                throw new UnsupportedOperationException(
                        "Not supported yet.");
            }

            public void removeReconfigurationEventListener(
                    final ReconfEventListener listener) {
                throw new UnsupportedOperationException(
                        "Not supported yet.");
            }
        });
        this.reconfPlanReceiver.execute();

        /* Construct and start Tpan instance with FS reader */
        final FSReader r = new FSReader(new String[]{this.fsReaderInputDir});
        this.tpanInstance = new AnalysisInstance();
        this.tpanInstance.setLogReader(r);
        this.tpanInstance.registerPlugin(new IMonitoringRecordConsumerPlugin() {

            final IMonitoringRecordConsumerPlugin delegate = SLAsticSimulatorInstance.this.simCtrl.getRecordConsumerPluginPort();

            @Override
            public boolean newMonitoringRecord(final IMonitoringRecord record) {
                return this.delegate.newMonitoringRecord(record);
            }

            @Override
            public boolean execute() {
                return this.delegate.execute();
            }

            public void terminate(final boolean error) {
                this.delegate.newMonitoringRecord(SimulationController.TERMINATION_RECORD);
                this.delegate.terminate(error);
            }

            public Collection<Class<? extends IMonitoringRecord>> getRecordTypeSubscriptionList() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        try {
            SLAsticSimulatorInstance.log.info("Starting simulator instance ...");
            this.tpanInstance.run();
        } catch (final Exception ex) {
            SLAsticSimulatorInstance.log.error("Exeception occured:", ex);
        }
        this.simCtrl.start();
    }
}
