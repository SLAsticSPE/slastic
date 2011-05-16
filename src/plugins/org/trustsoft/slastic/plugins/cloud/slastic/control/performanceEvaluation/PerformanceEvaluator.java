package org.trustsoft.slastic.plugins.cloud.slastic.control.performanceEvaluation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformanceEvaluatorComponent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.plugins.cloud.slastic.control.performanceEvaluation.performanceLogger.PerformanceLogger;
import org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.IPerformanceLogger;

import com.espertech.esper.client.EPServiceProvider;

public class PerformanceEvaluator extends AbstractPerformanceEvaluatorComponent {

	private static final Log log = LogFactory
			.getLog(PerformanceEvaluator.class);

	private volatile EPServiceProvider epServiceProvider;

	private volatile IPerformanceLogger performanceLogger;

	@Override
	public void handleEvent(final IEvent ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean init() {
		return true;
	}

	// TODO: to be configured via properties
	private final int winTimeSec = 60;
	private final int outputIntervalSec = 15;

	@Override
	public boolean execute() {
		this.epServiceProvider =
				this.getParentAnalysisComponent().getParentControlComponent()
						.getEPServiceProvider();
		this.performanceLogger =
				new PerformanceLogger(this.epServiceProvider, this
						.getComponentContext().createSubcontext(
								PerformanceLogger.class.getSimpleName()),
						this.winTimeSec, this.outputIntervalSec);
		return true;
	}

	@Override
	public void terminate(final boolean error) {
		this.performanceLogger.closeLogs();
	}
}