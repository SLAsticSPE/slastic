package org.trustsoft.slastic.plugins.daLena.control;

import java.util.HashMap;
import java.util.Map;

import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openarchitectureware.workflow.WorkflowRunner;
import org.openarchitectureware.workflow.monitor.NullProgressMonitor;
import org.trustsoft.slastic.control.BasicSLAsticControl;
import org.trustsoft.slastic.plugins.daLena.modelManager.ModelManager;

/**
 * @author Lena Stoever
 */
public class ControlComponent extends BasicSLAsticControl {

    private static final Log log = LogFactory.getLog(ControlComponent.class);
    private String workflow_fn;
    private boolean isInitialized = false;

    @Override
    public void update(AbstractKiekerMonitoringRecord record) {
        try {
            this.consumeMonitoringRecord(record);
        } catch (RecordConsumerExecutionException e) {
            e.printStackTrace();
        }

    }

    private void init() throws IllegalArgumentException {
        this.workflow_fn = this.getInitProperty("initWorkflow_fn");
        if (this.workflow_fn == null || this.workflow_fn.equals("")){
            throw new IllegalArgumentException("No property 'initWorkflow_fn' defined.");
        }
        this.isInitialized = true;
    }

    @Override
    public boolean execute() {
        if (!this.isInitialized){
            this.init();
        }
        
        Map<String, String> properties = new HashMap<String, String>();
        Map<String, String> slotContents = new HashMap<String, String>();

        //workflow runner of the oAW-framework
        WorkflowRunner runner = new WorkflowRunner();
        runner.run(workflow_fn, new NullProgressMonitor(), properties, slotContents);
        //reading the SLA-model
        slal.Model slas = (slal.Model) runner.getContext().get("theModel");
        //reading the reconfiguration model
        reconfMM.ReconfigurationModel reconfigurationModel = (reconfMM.ReconfigurationModel) runner.getContext().get("reconfigurationModel");

        //intialize Model Manager object
        ((ModelManager)this.getModelManager()).setMaxResponseTime(reconfigurationModel.getMaxResponseTimes());
        ((ModelManager)this.getModelManager()).setModel(reconfigurationModel);

        //initialize Analysis object
        this.getAnalysis().setSLAs(slas);
        //this.analysis.setReconfigurationManager(this.reconfigurationManager);

        super.execute();
        log.info("Analysis started.");
        return true;
    }

    @Override
    public String[] getRecordTypeSubscriptionList() {
        return null;
    }
}
