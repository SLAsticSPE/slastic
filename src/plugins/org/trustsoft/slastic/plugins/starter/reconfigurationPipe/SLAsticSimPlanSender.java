package org.trustsoft.slastic.plugins.starter.reconfigurationPipe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.reconfiguration.AbstractReconfigurationManagerComponent;
import org.trustsoft.slastic.reconfiguration.ReconfigurationException;
import org.trustsoft.slastic.simulation.listeners.ReconfEventListener;

import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

/**
 *
 * @author Andre van Hoorn
 */
public class SLAsticSimPlanSender extends
		AbstractReconfigurationManagerComponent implements ReconfEventListener {

	private static final Log log = LogFactory
			.getLog(SLAsticSimPlanSender.class);
	private ReconfigurationPipe reconfigurationPipe;
	private static final String PROPERTY_PIPE_NAME = "pipeName";
	private String pipeName;

	@Override
	public boolean init() {
		this.pipeName = super
				.getInitProperty(SLAsticSimPlanSender.PROPERTY_PIPE_NAME);
		if (this.pipeName == null || this.pipeName.length() == 0) {
			SLAsticSimPlanSender.log
					.error("Invalid or missing pipeName value for property '"
							+ SLAsticSimPlanSender.PROPERTY_PIPE_NAME + "'");
			throw new IllegalArgumentException(
					"Invalid or missing pipeName value:" + this.pipeName);
		}
		this.reconfigurationPipe = ReconfigurationPipeBroker.getInstance()
				.acquirePipe(this.pipeName);
		if (this.reconfigurationPipe == null) {
			SLAsticSimPlanSender.log.error("Failed to get pipe with name:"
					+ this.pipeName);
			throw new IllegalArgumentException("Failed to get pipe with name:"
					+ this.pipeName);
		}
		SLAsticSimPlanSender.log.info("Connected to pipe '" + this.pipeName
				+ "'" + " (" + this.reconfigurationPipe + ")");
		return true;
	}

	public boolean execute() {
		return true;
	}

	public void terminate(final boolean error) {
	}

	public void doReconfiguration(final SLAsticReconfigurationPlan plan)
			throws ReconfigurationException {
		try {
			this.reconfigurationPipe.reconfigure(plan, this);
		} catch (final ReconfigurationPipeException ex) {
			SLAsticSimPlanSender.log.error("reconfiguration failed", ex);
			throw new ReconfigurationException("reconfiguration failed", ex);
		}
	}

	@Override
	public void notifyPlanDone(final SLAsticReconfigurationPlan plan) {
		SLAsticSimPlanSender.log.info("notifyPlanDone received");
	}

	@Override
	public void notifyOpFailed(final SLAsticReconfigurationPlan plan,
			final SLAsticReconfigurationOpType reconfOp) {
		SLAsticSimPlanSender.log.info("notifyOpFailed received");
	}

	@Override
	public void notifyPlanFailed(final SLAsticReconfigurationPlan plan) {
		SLAsticSimPlanSender.log.info("notifyOpFailed received");
	}
}
