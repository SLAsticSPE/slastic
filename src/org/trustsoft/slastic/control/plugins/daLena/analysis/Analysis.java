package org.trustsoft.slastic.control.plugins.daLena.analysis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.trustsoft.slastic.control.components.analysis.AbstractSLAsticAnalysis;
import org.trustsoft.slastic.control.components.analysis.IAdaptationPlanner;
import org.trustsoft.slastic.control.components.analysis.IPerformanceEvaluator;
import org.trustsoft.slastic.control.components.analysis.IPerformancePredictor;
import org.trustsoft.slastic.control.components.analysis.ISLAsticAnalysisEvent;
import org.trustsoft.slastic.control.components.analysis.IWorkloadForecaster;
import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;
import slal.Model;

/**
 * This class is responsible for holding different analysis components and delegating specific events to them
 * @author Lena Stoever
 *
 */
public class Analysis extends AbstractSLAsticAnalysis {

    private static final Log log = LogFactory.getLog(Analysis.class);

    public Analysis(
            IReconfigurationManager reconfigurationManager,
            IPerformanceEvaluator performanceEvaluator,
            IWorkloadForecaster workloadForecaster,
            IPerformancePredictor performancePredictor,
            IAdaptationPlanner adaptationPlanner) {
        super(reconfigurationManager, performanceEvaluator,
                workloadForecaster, performancePredictor,
                adaptationPlanner);
    }

//	private IAdaptationPlanner adaptationAnalyzer;
//	private IPerformanceEvaluator performanceAnalyzer;
//	private IPerformancePredictor performancePredictor;
//	private IWorkloadForecaster workloadAnalyzer;
//	private IReconfigurationManager reconfigurationManager;
//	public void setPerformanceEvaluator(IPerformanceEvaluator performanceAnalyzer) {
//		this.performanceAnalyzer = performanceAnalyzer;
//	}
//	public IPerformanceEvaluator getPerformanceAnalyzer() {
//		return performanceAnalyzer;
//	}
//	public void setWorkloadForecaster(IWorkloadForecaster workloadAnalyzer) {
//		this.workloadAnalyzer = workloadAnalyzer;
//	}
//	public IWorkloadForecaster getWorkloadAnalyzer() {
//		return workloadAnalyzer;
//	}
    @Override
    public void execute() {
//		this.adaptationAnalyzer.setReconfigurationManager(this.reconfigurationManager);
//		this.adaptationAnalyzer.setAnalysis(this);
//		this.performanceAnalyzer.setAnalysis(this);
        super.execute();
    }

//	public void setAdaptationPlanner(IAdaptationPlanner adaptationAnalyzer) {
//		this.adaptationAnalyzer = adaptationAnalyzer;
//	}
//
//	public void setPerformancePredictor(
//			IPerformancePredictor performancePredictor) {
//		this.performancePredictor = performancePredictor;
//	}
//
//	public void setReconfigurationManager(IReconfigurationManager manager) {
//		this.reconfigurationManager = manager;
//	}
    @Override
    public void setSLAs(Model slas) {
        this.performanceEvaluator.setSLAs(slas);
    }

    // TODO: move this to the abstract Analysis
    @Override
    public void handleInternalEvent(ISLAsticAnalysisEvent evt) {
        log.info("SLAViolation recognized");
        //At the moment there is only one kind of ISlasticAnalysisEvent, the SLAViolationEvent which belongs to the Adaptation Analyzer
        if (evt instanceof SLAViolationEvent) {
            //forward the event to the responsible analysis-object
            this.adaptationPlanner.handle(evt);
        } else {
            log.error("EventType not supported");
        }

    }
}
