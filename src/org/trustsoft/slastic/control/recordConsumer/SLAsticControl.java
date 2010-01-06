package org.trustsoft.slastic.control.recordConsumer;

import java.util.HashMap;
import java.util.Map;

import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openarchitectureware.workflow.WorkflowRunner;
import org.openarchitectureware.workflow.monitor.NullProgressMonitor;
import org.trustsoft.slastic.control.components.analysis.ISLAsticAnalysis;
import org.trustsoft.slastic.control.systemModel.IModelManager;
import org.trustsoft.slastic.control.systemModel.IModelUpdater;
import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;

/**
 * @author Lena Stoever
 */
public class SLAsticControl implements ISLAsticControl {

    private static final Log log = LogFactory.getLog(SLAsticControl.class);
    private ISLAsticAnalysis analysis;
    private IModelManager manager;
    private IModelUpdater updater;
    private IReconfigurationManager reconfigurationManager;
    private final String INIT_WORKFLOW_FN;

    /**
     * Only Constructor of the SLAsticControl-class
     * @param initWorkflow_fn location of the workflow for the initialization
     */
    public SLAsticControl(String initWorkflow_fn) {
        this.INIT_WORKFLOW_FN = initWorkflow_fn;
    }

    @Override
    public void setAnalysis(ISLAsticAnalysis analysis) {
        this.analysis = analysis;

    }

    @Override
    public void setModelManager(IModelManager mng) {
        this.manager = mng;

    }

    @Override
    public void setModelUpdater(IModelUpdater updater) {
        this.updater = updater;

    }

    @Override
    public void setReconfigurationManager(
            IReconfigurationManager reconfManager) {
        this.reconfigurationManager = reconfManager;

    }

    @Override
    public void update(AbstractKiekerMonitoringRecord record) {
        try {
            this.consumeMonitoringRecord(record);
        } catch (RecordConsumerExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void consumeMonitoringRecord(
            AbstractKiekerMonitoringRecord monitoringRecord)
            throws RecordConsumerExecutionException {
        this.updater.update(monitoringRecord);

    }

    @Override
    public boolean execute() throws RecordConsumerExecutionException {
        Map<String, String> properties = new HashMap<String, String>();
        Map<String, String> slotContents = new HashMap<String, String>();

        //workflow runner of the oAW-framework
        WorkflowRunner runner = new WorkflowRunner();
        runner.run(INIT_WORKFLOW_FN, new NullProgressMonitor(), properties, slotContents);
        //reading the SLA-model
        slal.Model slas = (slal.Model) runner.getContext().get("theModel");
        //reading the reconfiguration model
        reconfMM.ReconfigurationModel reconfigurationModel = (reconfMM.ReconfigurationModel) runner.getContext().get("reconfigurationModel");

        //intialize Model Manager object
        this.manager.setMaxResponseTime(reconfigurationModel.getMaxResponseTimes());
        this.manager.setModel(reconfigurationModel);

        //initialize Analysis object
        this.analysis.setSLAs(slas);
        //this.analysis.setReconfigurationManager(this.reconfigurationManager);

        //execute analysis and updater object
        this.analysis.execute();
        this.updater.execute();
        log.info("Analysis started.");
        return true;
    }

    @Override
    public String[] getRecordTypeSubscriptionList() {
        return null;
    }

    @Override
    public void terminate() {
        this.analysis.terminate();
        this.updater.terminate();
        log.info("Bye, this was SLAsticControl");

    }
}
