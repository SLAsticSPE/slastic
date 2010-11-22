package org.trustsoft.slastic.simulation.software.controller.controlflow;

import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.software.controller.CallHandler;
import org.trustsoft.slastic.simulation.software.controller.StackFrame;

public class ReleaseNode extends ControlFlowNode {

	private final String resName;

	public ReleaseNode(final String name, final String traceId,
			final String resName) {
		super(name, traceId);
		this.resName = resName;
	}

	@Override
	public void eventRoutine() {
		final StackFrame f = CallHandler.getInstance().getStackTop(
				this.getTraceId());
		ModelManager
				.getInstance()
				.getAllocCont()
				.releasePassive(f.getServerId(), f.getAsmContextTo(),
						this.resName);
		CallHandler.getInstance().actionReturn(this.getTraceId());
	}
}
