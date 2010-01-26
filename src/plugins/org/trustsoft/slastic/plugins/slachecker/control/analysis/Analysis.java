package org.trustsoft.slastic.plugins.slachecker.control.analysis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.trustsoft.slastic.control.components.analysis.AbstractSLAsticAnalysis;
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
        ((SLAChecker) this.getPerformanceEvaluator()).setSLAs(slas);
    }
}
