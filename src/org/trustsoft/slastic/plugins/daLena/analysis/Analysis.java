package org.trustsoft.slastic.plugins.daLena.analysis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.trustsoft.slastic.control.components.analysis.AbstractSLAsticAnalysis;
import org.trustsoft.slastic.control.components.events.ISLAsticEvent;
import slal.Model;

/**
 * This class is responsible for holding different analysis components and delegating specific events to them
 * @author Lena Stoever
 *
 */
public class Analysis extends AbstractSLAsticAnalysis {

    private static final Log log = LogFactory.getLog(Analysis.class);

   public void init(String initString) throws IllegalArgumentException {
        super.initVarsFromInitString(initString);
        // we don't expect init properties so far, so just return.
    }

    @Override
    public void setSLAs(Model slas) {
        // TODO: be careful!
        ((SLAChecker) this.getPerformanceEvaluator()).setSLAs(slas);
    }
}
