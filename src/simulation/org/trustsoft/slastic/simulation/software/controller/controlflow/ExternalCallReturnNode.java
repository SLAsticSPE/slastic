package org.trustsoft.slastic.simulation.software.controller.controlflow;

import org.trustsoft.slastic.simulation.software.controller.CallHandler;
import org.trustsoft.slastic.simulation.software.controller.StackFrame;

import desmoj.core.simulator.SimTime;

public class ExternalCallReturnNode extends ControlFlowNode {

	private final ExternalCallEnterNode ece;
	private SimTime exitTime;

	public ExternalCallReturnNode(final ExternalCallEnterNode ece) {
		super("Return from " + ece.getName(), ece.getTraceId());
		this.ece = ece;
	}

	@Override
	public void eventRoutine() {
		// TODO monitor!
		exitTime = getModel().currentTime();
		// spawn record!
		// tell simulator to schedule next action in this trace
		final StackFrame f = CallHandler.getInstance().popContext(
				ece.getTraceId());
		f.createRecord(getModel().currentTime().getTimeValue(), CallHandler
				.getInstance().getStackDepth(ece.getTraceId()), f.getEoi());
		CallHandler.getInstance().actionReturn(ece.getTraceId());
	}

}
