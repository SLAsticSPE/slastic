package org.trustsoft.slastic.control.analysis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;

import slal.Model;

public class Analysis implements IAnalysis {	
	private static final Log log = LogFactory.getLog(Analysis.class);
	private IAdaptationAnalyzer adaptationAnalyzer;
	private IPerformanceAnalyzer performanceAnalyzer;
	private IPerformancePredictor performancePredictor;
	private IWorkloadAnalyzer workloadAnalyzer;
	private IReconfigurationManager reconfigurationManager;
	
	public IAdaptationAnalyzer getAdaptionAnalyzer() {
		return adaptationAnalyzer;
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
		this.adaptationAnalyzer.setReconfigurationManager(this.reconfigurationManager);
		this.adaptationAnalyzer.setAnalysis(this);
		this.performanceAnalyzer.setAnalysis(this);
		//TODO hier muss noch was passieren, wenn die Testphase beendet ist.
		if(adaptationAnalyzer != null){
			this.adaptationAnalyzer.execute();
		}
		if(this.performanceAnalyzer != null){
			this.performanceAnalyzer.execute();
		}
		if(this.performancePredictor != null){
			this.performancePredictor.execute();
		}
		if(this.workloadAnalyzer != null){
			this.workloadAnalyzer.execute();
		}
	}
	@Override
	public void setAdaptationAnalyzer(IAdaptationAnalyzer adaptationAnalyzer) {
		this.adaptationAnalyzer = adaptationAnalyzer;
		
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
		if(this.adaptationAnalyzer !=null){
			this.adaptationAnalyzer.terminate();
		}
		if(this.performanceAnalyzer != null){
			this.performanceAnalyzer.terminate();
		}
		if(this.performancePredictor != null){
			this.performancePredictor.terminate();
		}
		if(this.workloadAnalyzer != null){
			this.workloadAnalyzer.terminate();
		}

	}
	@Override
	public void setSLAs(Model slas) {
		this.performanceAnalyzer.setSLAs(slas);
		
	}
	@Override
	public void handleInternalEvent(ISLAsticAnalysisEvent evt) {
		log.info("SLAViolation recognized");
		if(evt instanceof SLAViolationEvent){
			
			this.adaptationAnalyzer.handle(evt);
			
		}else{
			log.error("EventType not supported");
		}
		
	}

}
