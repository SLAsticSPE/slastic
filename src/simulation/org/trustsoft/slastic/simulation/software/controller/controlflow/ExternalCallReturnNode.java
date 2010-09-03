package org.trustsoft.slastic.simulation.software.controller.controlflow;

import kieker.common.record.OperationExecutionRecord;
import kieker.monitoring.core.MonitoringController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.software.controller.CallHandler;
import org.trustsoft.slastic.simulation.software.controller.StackFrame;

import desmoj.core.simulator.SimTime;

public class ExternalCallReturnNode extends ControlFlowNode {

	private final ExternalCallEnterNode ece;
	private SimTime exitTime;
	private static Log log = LogFactory.getLog(ExternalCallReturnNode.class);
        private final static MonitoringController tpmonCtrl = MonitoringController.getInstance();

	public ExternalCallReturnNode(final ExternalCallEnterNode ece) {
		super("Return from " + ece.getName(), ece.getTraceId());
		this.ece = ece;
	}

	@Override
	public void eventRoutine() {
		// TODO monitor!

		this.exitTime = this.getModel().currentTime();
		// spawn record!
		// tell simulator to schedule next action in this trace
		final StackFrame f = CallHandler.getInstance().popContext(
				this.ece.getTraceId());
		final OperationExecutionRecord erec = f.createRecord(this.getModel()
				.currentTime().getTimeValue(), CallHandler.getInstance()
				.getStackDepth(this.ece.getTraceId()), f.getEoi());
		ExternalCallReturnNode.tpmonCtrl.newMonitoringRecord(erec);
		// ExternalCallReturnNode.log.info(f.getAsmContextTo() + " resides on "
		// + f.getServerId());
		// ExternalCallReturnNode.log.info(erec);
		// ExternalCallReturnNode.log.info("Returned from "
		// + this.ece.getCalledService() + " on "
		// + this.ece.getASMContTo());
		ModelManager.getInstance().getAllocCont().remUser(this.ece.getASMContTo(),
				this.ece.getServerId());
		CallHandler.getInstance().actionReturn(this.ece.getTraceId());
	}
}
