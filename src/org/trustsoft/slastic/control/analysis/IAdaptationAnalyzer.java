package org.trustsoft.slastic.control.analysis;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;


public interface IAdaptationAnalyzer {
	public String getAdaptationPlan();
	
	SLAsticReconfigurationPlan getReconfigurationPlan();
}
