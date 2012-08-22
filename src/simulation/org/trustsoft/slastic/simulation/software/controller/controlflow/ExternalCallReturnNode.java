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

@SuppressWarnings("unused")
public class ExternalCallReturnNode extends AbstractControlFlowEvent {

	@Inject
	@Named("SystemUsersOnReturn")
	private static ISystemStats stats;

	@Inject
	@Named("Execution")
	private static ISystemStats exeStats;

	private final ExternalCallEnterNode ece;
	// private SimTime exitTime;
	private static Log log = LogFactory.getLog(ExternalCallReturnNode.class);
	private final static IMonitoringController tpmonCtrl = MonitoringController.getInstance();

	public ExternalCallReturnNode(final ExternalCallEnterNode ece) {
		super("Return from " + ece.getName(), ece.getTraceId());
		this.ece = ece;
	}

	@Override
	public final void eventRoutine() {
		// this.exitTime = this.getModel().currentTime();
		// spawn record!
		final StackFrame f = CallHandler.getInstance().popContext(this.ece.getTraceId());
		if (this.ece.getASMContFrom() == null) {
			ExternalCallReturnNode.stats.subSystemUser();
		}
		ExternalCallReturnNode.exeStats.logExecution(f, CallHandler.getInstance().getStackDepth(this.ece.getTraceId()));

		// tell simulator to schedule next action in this trace
		ModelManager.getInstance().getAllocationController().remUser(this.ece.getASMContTo(), this.ece.getServerId());
		CallHandler.getInstance().actionReturn(this.ece.getTraceId());
	}
}
