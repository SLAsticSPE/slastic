package org.trustsoft.slastic.control.analysis;

import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;

import slal.Model;

public class Analysis implements IAnalysis {	
	private IAdaptationAnalyzer adaptionAnalyzer;
	private IPerformanceAnalyzer performanceAnalyzer;
	private IPerformancePredictor performancePredictor;
	private IWorkloadAnalyzer workloadAnalyzer;
	private IReconfigurationManager reconfigurationManager;
	
	public IAdaptationAnalyzer getAdaptionAnalyzer() {
		return adaptionAnalyzer;
	}
	public void setPerformanceAnalyzer(IPerformanceAnalyzer performanceAnalyzer) {
		this.performanceAnalyzer = performanceAnalyzer;
	}
	public IPerformanceAnalyzer getPerformanceAnalyzer() {
		return performanceAnalyzer;
	}
	public void setWorkloadAnalyzer(IWorkloadAnalyzer workloadAnalyzer) {
		this.workloadAnalyzer = workloadAnalyzer;
	}
	public IWorkloadAnalyzer getWorkloadAnalyzer() {
		return workloadAnalyzer;
	}
	@Override
	public void execute() {
		this.adaptionAnalyzer.setReconfigurationManager(this.reconfigurationManager);
		this.adaptionAnalyzer.execute();
		this.performanceAnalyzer.execute();
		this.performancePredictor.execute();
		this.workloadAnalyzer.execute();
	}
	@Override
	public void setAdaptationAnalyzer(IAdaptationAnalyzer adaptationAnalyzer) {
		this.adaptionAnalyzer = adaptationAnalyzer;
		
	}
	@Override
	public void setPerformancePredictor(
			IPerformancePredictor performancePredictor) {
		this.performancePredictor = performancePredictor;
		
	}
	@Override
	public void setReconfigurationManager(IReconfigurationManager manager) {
		this.reconfigurationManager = manager;
		
	}
	@Override
	public void terminate() {
		this.adaptionAnalyzer.terminate();
		this.performanceAnalyzer.terminate();
		this.performancePredictor.terminate();
		this.workloadAnalyzer.terminate();
		
	}
	@Override
	public void setSLAs(Model slas) {
		this.performanceAnalyzer.setSLAs(slas);
		
	}

}
