package org.trustsoft.slastic.control.recordConsumer;

import java.util.HashMap;
import java.util.Map;

import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openarchitectureware.workflow.WorkflowRunner;
import org.openarchitectureware.workflow.monitor.NullProgressMonitor;
import org.trustsoft.slastic.control.analysis.IAnalysis;
import org.trustsoft.slastic.control.systemModel.IModelManager;
import org.trustsoft.slastic.control.systemModel.IModelUpdater;
import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;


/**
 * @author Andre van Hoorn
 */
public class SLAsticControl implements IControl {

    private static final Log log = LogFactory.getLog(SLAsticControl.class);
    private IAnalysis analysis;
    private IModelManager manager;
    private IModelUpdater updater;
    private IReconfigurationManager reconfigurationManager;
    
    public SLAsticControl(){
    }        
		@Override
		public void setAnalysis(IAnalysis analysis) {
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
			String wfFile ="C:/workspace/slastic/src/org/trustsoft/slastic/control/InitModels.oaw";
	        //String wfFile = "../../../workspace2/SLAstic-Framework/trunk/src/org/trustsoft/slastic/control/InitModelsMac.oaw";
	        //String wfFile = "/home/voorn/svn_work/sw_DALenaRobert/SLAstic-Framework/trunk/src/org/trustsoft/slastic/control/InitModels-Andre.oaw";
	        Map<String, String> properties = new HashMap<String, String>();
	        Map<String, String> slotContents = new HashMap<String, String>();
	        WorkflowRunner runner = new WorkflowRunner();
	        runner.run(wfFile, new NullProgressMonitor(), properties, slotContents);
	        slal.Model slas = (slal.Model) runner.getContext().get("theModel");
	        reconfMM.ReconfigurationModel reconfigurationModel = (reconfMM.ReconfigurationModel) runner.getContext().get("reconfigurationModel");
	        this.updater.setMaxResponseTime(reconfigurationModel.getMaxResponseTimes());
	        this.updater.execute();
	        this.manager.setModel(reconfigurationModel);
	        this.analysis.setSLAs(slas);
	        this.analysis.setReconfigurationManager(this.reconfigurationManager);
	        this.analysis.execute();
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
