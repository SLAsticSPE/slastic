package org.trustsoft.slastic.simulation.software.controller.controlflow;

import kieker.common.record.OperationExecutionRecord;
import kieker.monitoring.core.MonitoringController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.software.controller.CallHandler;
import org.trustsoft.slastic.simulation.software.controller.StackFrame;

import desmoj.core.simulator.SimTime;

@SuppressWarnings("unused")
public class ExternalCallReturnNode extends ControlFlowNode {

	private final ExternalCallEnterNode ece;
	private SimTime exitTime;
	private static Log log = LogFactory.getLog(ExternalCallReturnNode.class);
	private final static MonitoringController tpmonCtrl = MonitoringController
			.getInstance();

	public ExternalCallReturnNode(final ExternalCallEnterNode ece) {
		super("Return from " + ece.getName(), ece.getTraceId());
		this.ece = ece;
	}

	@Override
	public void eventRoutine() {
		this.exitTime = this.getModel().currentTime();
		// spawn record!
		final StackFrame f = CallHandler.getInstance().popContext(
				this.ece.getTraceId());
		final OperationExecutionRecord erec = f.createRecord(this.getModel()
				.currentTime().getTimeValue(), CallHandler.getInstance()
				.getStackDepth(this.ece.getTraceId()), f.getEoi());
		ExternalCallReturnNode.tpmonCtrl.newMonitoringRecord(erec);

		// tell simulator to schedule next action in this trace
		ModelManager.getInstance().getAllocCont().remUser(
				this.ece.getASMContTo(), this.ece.getServerId());
		CallHandler.getInstance().actionReturn(this.ece.getTraceId());
	}
}
