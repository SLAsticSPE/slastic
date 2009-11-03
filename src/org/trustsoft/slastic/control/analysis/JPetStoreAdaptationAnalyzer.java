package org.trustsoft.slastic.control.analysis;

import org.jfree.util.Log;
import org.trustsoft.slastic.control.systemModel.ModelManager;
import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;
import org.trustsoft.slastic.reconfigurationManager.ReconfigurationException;

import ReconfigurationPlanModel.ComponentRedeploymentOP;
import ReconfigurationPlanModel.ReconfigurationPlanModelFactory;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import ReconfigurationPlanModel.impl.ReconfigurationPlanModelFactoryImpl;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.repository.BasicComponent;

import reconfMM.ReconfigurationModel;

public class JPetStoreAdaptationAnalyzer implements IAdaptationAnalyzer {
	private IAnalysis ana;
	private IReconfigurationManager mng;

	@Override
	public void execute() {

	}

	@Override
	public void handle(ISLAsticAnalysisEvent event) {
		if(event instanceof SLAViolationEvent ){
			SLAViolationEvent evt = (SLAViolationEvent) event;
			int serviceID = evt.getServiceID();
			ReconfigurationModel model = ModelManager.getInstance().getModel();
			BasicComponent comp = null;
			AssemblyContext context = null;
			Log.info("hierher sollte ich aber kommen");
			synchronized(model){
				for(int i = 0; i<model.getComponents().size(); i++){
					for(int j = 0; j < model.getComponents().get(i).getServices().size(); j++){
						if(model.getComponents().get(i).getServices().get(j).getServiceID() == serviceID){
							comp = model.getComponents().get(i).getComponent();
							break;
						}
					}
				}
				for(int i = 0; i< model.getAllocation().getAllocationContexts_Allocation().size(); i++){
					if(model.getAllocation().getAllocationContexts_Allocation().get(i).getAssemblyContext_AllocationContext().getEncapsulatedComponent_ChildComponentContext() == comp){
						context = model.getAllocation().getAllocationContexts_Allocation().get(i).getAssemblyContext_AllocationContext();
					}
				}
			}
			ReconfigurationPlanModelFactory fac = ReconfigurationPlanModelFactoryImpl.init();
			SLAsticReconfigurationPlan plan = fac.createSLAsticReconfigurationPlan();
			ComponentRedeploymentOP op = fac.createComponentRedeploymentOP();
			//TODO hier muss der neue Komponententyp(mit weniger Delay) hin.
			op.setComponentImpl(comp);
			op.setTargetComponent(context);
			
			plan.getOperations().add(op);
			try {
				this.mng.doReconfiguration(plan);
			} catch (ReconfigurationException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void setAnalysis(IAnalysis ana) {
		this.ana = ana;

	}

	@Override
	public void setReconfigurationManager(IReconfigurationManager manager) {
		this.mng = manager;

	}

	@Override
	public void terminate() {

	}

}
