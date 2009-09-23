package org.trustsoft.slastic.control.analysis;

import org.trustsoft.slastic.control.systemModel.AbstractModelManager;
import org.trustsoft.slastic.control.systemModel.ModelManager;

public class Analysis {
	protected final static AbstractModelManager modelManager = ModelManager.getInstance();
	
	private IAdaptationAnalyzer adaptionAnalyzer;
	private IPerformanceAnalyzer performanceAnalyzer;
	private IPerformanceForecaster performanceForcaster;
	private IWorkloadAnalyzer workloadAnalyzer;
	public void setAdaptionAnalyzer(IAdaptationAnalyzer adaptionAnalyzer) {
		this.adaptionAnalyzer = adaptionAnalyzer;
	}
	public IAdaptationAnalyzer getAdaptionAnalyzer() {
		return adaptionAnalyzer;
	}
	public void setPerformanceAnalyzer(IPerformanceAnalyzer performanceAnalyzer) {
		this.performanceAnalyzer = performanceAnalyzer;
	}
	public IPerformanceAnalyzer getPerformanceAnalyzer() {
		return performanceAnalyzer;
	}
	public void setPerformanceForcaster(IPerformanceForecaster performanceForcaster) {
		this.performanceForcaster = performanceForcaster;
	}
	public IPerformanceForecaster getPerformanceForcaster() {
		return performanceForcaster;
	}
	public void setWorkloadAnalyzer(IWorkloadAnalyzer workloadAnalyzer) {
		this.workloadAnalyzer = workloadAnalyzer;
	}
	public IWorkloadAnalyzer getWorkloadAnalyzer() {
		return workloadAnalyzer;
	}

}
