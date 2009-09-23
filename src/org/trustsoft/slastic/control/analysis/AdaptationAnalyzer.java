package org.trustsoft.slastic.control.analysis;

import ReconfigurationPlanModel.*;
import ReconfigurationPlanModel.impl.ReconfigurationPlanModelFactoryImpl;

public class AdaptationAnalyzer implements IAdaptationAnalyzer {
	
	SLAsticReconfigurationPlan plan;

	@Override
	public String getAdaptationPlan() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SLAsticReconfigurationPlan getReconfigurationPlan() {
		return plan;
	}
	
	public void analyze(){
		ReconfigurationPlanModelFactory fac = ReconfigurationPlanModelFactoryImpl.init();
		this.plan = fac.createSLAsticReconfigurationPlan();
	}

}
