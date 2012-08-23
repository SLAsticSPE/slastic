package org.trustsoft.slastic.simulation.software.controller.controlflow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.software.controller.CallHandler;
import org.trustsoft.slastic.simulation.software.controller.StackFrame;
import org.trustsoft.slastic.simulation.software.statistics.ISystemStats;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import kieker.monitoring.core.controller.IMonitoringController;
import kieker.monitoring.core.controller.MonitoringController;

/**
 * 
 * @author Robert von Massow
 * 
 */
@SuppressWarnings("unused")
public class ExternalCallReturnEvent extends AbstractControlFlowEvent {
	private static final Log LOG = LogFactory.getLog(ExternalCallReturnEvent.class);

	@Inject
	@Named("SystemUsersOnReturn")
	private static ISystemStats stats;

	@Inject
	@Named("Execution")
	private static ISystemStats exeStats;

	private static final IMonitoringController tpmonCtrl = MonitoringController.getInstance();

	private final ExternalCallEnterEvent ece;

	// private SimTime exitTime;

	public ExternalCallReturnEvent(final ExternalCallEnterEvent ece) {
		super("Return from " + ece.getName(), ece.getTraceId());
		this.ece = ece;
	}

	@Override
	public final void eventRoutine() {
		// this.exitTime = this.getModel().currentTime();
		// spawn record!
		final StackFrame f = CallHandler.getInstance().popContext(this.ece.getTraceId());
		if (this.ece.getASMContFrom() == null) {
			stats.subSystemUser();
		}
		exeStats.logExecution(f, CallHandler.getInstance().getStackDepth(this.ece.getTraceId()));

		// tell simulator to schedule next action in this trace
		ModelManager.getInstance().getAllocationController().remUser(this.ece.getASMContTo(), this.ece.getServerId());
		CallHandler.getInstance().actionReturn(this.ece.getTraceId());
	}
}
