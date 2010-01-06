package org.trustsoft.slastic.control.plugins.daLena.analysis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.trustsoft.slastic.control.components.analysis.AbstractSLAsticAnalysis;
import org.trustsoft.slastic.control.components.analysis.ISLAsticAnalysisEvent;
import slal.Model;

/**
 * This class is responsible for holding different analysis components and delegating specific events to them
 * @author Lena Stoever
 *
 */
public class Analysis extends AbstractSLAsticAnalysis {

    private static final Log log = LogFactory.getLog(Analysis.class);

    @Override
    public void setSLAs(Model slas) {
        // TODO: be careful!
        ((SLAChecker)this.getPerformanceEvaluator()).setSLAs(slas);
    }

    @Override
    public void handleInternalEvent(ISLAsticAnalysisEvent evt) {
        log.info("SLAViolation recognized");
        //At the moment there is only one kind of ISlasticAnalysisEvent, the SLAViolationEvent which belongs to the Adaptation Analyzer
        if (evt instanceof SLAViolationEvent) {
            //forward the event to the responsible analysis-object
            this.getAdaptationPlanner().handle(evt);
        } else {
            log.error("EventType not supported");
        }

    }
}
