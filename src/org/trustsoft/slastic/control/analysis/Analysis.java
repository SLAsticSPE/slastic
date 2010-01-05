package org.trustsoft.slastic.control.analysis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;

import slal.Model;

/**
 * This class is responsible for holding different analysis components and delegating specific events to them
 * @author Lena Stoever
 *
 */
public class Analysis implements IAnalysis {	
	private static final Log log = LogFactory.getLog(Analysis.class);
	private IAdaptationPlanner adaptationAnalyzer;
	private IPerformanceEvaluator performanceAnalyzer;
	private IPerformancePredictor performancePredictor;
	private IWorkloadForecaster workloadAnalyzer;
	private IReconfigurationManager reconfigurationManager;
	
	public IAdaptationPlanner getAdaptionAnalyzer() {
		return adaptationAnalyzer;
	}
	public void setPerformanceEvaluator(IPerformanceEvaluator performanceAnalyzer) {
		this.performanceAnalyzer = performanceAnalyzer;
	}
	public IPerformanceEvaluator getPerformanceAnalyzer() {
		return performanceAnalyzer;
	}
	public void setWorkloadForecaster(IWorkloadForecaster workloadAnalyzer) {
		this.workloadAnalyzer = workloadAnalyzer;
	}
	public IWorkloadForecaster getWorkloadAnalyzer() {
		return workloadAnalyzer;
	}
	@Override
	public void execute() {
		this.adaptationAnalyzer.setReconfigurationManager(this.reconfigurationManager);
		this.adaptationAnalyzer.setAnalysis(this);
		this.performanceAnalyzer.setAnalysis(this);
		//TODO At the moment not all analysis components are implemented, so this class must handle with null-arguments. normally this should not be possible
		if(adaptationAnalyzer != null){
			this.adaptationAnalyzer.execute();
			log.info("AdaptationAnalyzer ausgefhr");
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
	public void setAdaptationPlanner(IAdaptationPlanner adaptationAnalyzer) {
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
		//At the moment there is only one kind of ISlasticAnalysisEvent, the SLAViolationEvent which belongs to the Adaptation Analyzer
		if(evt instanceof SLAViolationEvent){
			//forward the event to the responsible analysis-object
			this.adaptationAnalyzer.handle(evt);
			
		}else{
			log.error("EventType not supported");
		}
		
	}

}
