package org.trustsoft.slastic.plugins.starter.reconfigurationPipe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.listeners.IReconfigurationEventListener;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan;

/**
 * TODO: Either we should change {@link SLAsticReconfigurationPlan}, used e.g., to {@link ReconfigurationPlan} or move this class to a PCM-specific package.
 * 
 * @author Andre van Hoorn
 */
public class ReconfigurationPipe {

	private static final Log LOG = LogFactory.getLog(ReconfigurationPipe.class);

	private final String name;
	private volatile IReconfigurationPipePlanReceiver planReceiver;
	private volatile boolean closed;

	/** No construction employing default constructor */
	@SuppressWarnings("unused")
	private ReconfigurationPipe() {
		this.name = null;
	}

	public ReconfigurationPipe(final String name) {
		this.name = name;
	}

	public void setPlanReceiver(final IReconfigurationPipePlanReceiver planReceiver) {
		this.planReceiver = planReceiver;
		ReconfigurationPipe.LOG.info("PipeReader initialized");
	}

	public void reconfigure(final SLAsticReconfigurationPlan plan, final IReconfigurationEventListener listener) throws ReconfigurationPipeException {
		if (this.closed) {
			ReconfigurationPipe.LOG.error("trying to write to closed pipe");
			throw new ReconfigurationPipeException("trying to write to closed pipe");
		}
		this.planReceiver.reconfigure(plan, listener);
	}

	public String getName() {
		return this.name;
	}

	public void close() {
		this.closed = true;
	}
}
