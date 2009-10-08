package org.trustsoft.slastic.control.analysis;

import org.trustsoft.slastic.control.ReconfigurationPlanForwarder;
import org.trustsoft.slastic.control.systemModel.ModelManager;

import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;

import ReconfigurationPlanModel.*;
import ReconfigurationPlanModel.impl.ReconfigurationPlanModelFactoryImpl;

public class AdaptationAnalyzer implements IAdaptationAnalyzer {
	
	SLAsticReconfigurationPlan plan;

	@Override
	public SLAsticReconfigurationPlan getReconfigurationPlan() {
		return plan;
	}
	
	public void analyze(){
		/**
		ReconfigurationPlanModelFactory fac = ReconfigurationPlanModelFactoryImpl.init();
		this.plan = fac.createSLAsticReconfigurationPlan();
		**/
		//This is just Test-Code and has to be deleted if it works
		SLAsticReconfigurationPlan testPlan;
		ReconfigurationPlanModelFactory fac = ReconfigurationPlanModelFactoryImpl.init();
		testPlan = fac.createSLAsticReconfigurationPlan();
		
		ComponentMigrationOP op = fac.createComponentMigrationOP();
		op.setComponent(ModelManager.getInstance().getModel().getAllocation().getAllocationContexts_Allocation().get(0));
		op.setDestination((ResourceContainer) ModelManager.getInstance().getAllocatedServers().toArray()[0]);
		
		testPlan.getOperations().add(op);
		this.plan = testPlan;
		ReconfigurationPlanForwarder.getInstance().addPlan(plan);
	}

}
