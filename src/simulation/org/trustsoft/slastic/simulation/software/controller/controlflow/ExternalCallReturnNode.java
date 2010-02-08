package org.trustsoft.slastic.simulation.software.controller.controlflow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.software.controller.CallHandler;
import org.trustsoft.slastic.simulation.software.controller.StackFrame;

import desmoj.core.simulator.SimTime;
import kieker.tpmon.core.TpmonController;
import kieker.tpmon.monitoringRecord.executions.KiekerExecutionRecord;

public class ExternalCallReturnNode extends ControlFlowNode {

	private final ExternalCallEnterNode ece;
	private SimTime exitTime;
	private static Log log = LogFactory.getLog(ExternalCallReturnNode.class);
        private final static TpmonController tpmonCtrl = TpmonController.getInstance();

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
                KiekerExecutionRecord erec = f.createRecord(this.getModel().currentTime().getTimeValue(),
				CallHandler.getInstance().getStackDepth(this.ece.getTraceId()),
				f.getEoi());
		tpmonCtrl.logMonitoringRecord(erec);
                log.info(f.getAsmContextTo() +" resides on "+f.getServerId());
                log.info(erec);
		ExternalCallReturnNode.log.info("Returned from "
				+ this.ece.getCalledService() + " on "
				+ this.ece.getASMContTo());
		CallHandler.getInstance().actionReturn(this.ece.getTraceId());

	}
}
