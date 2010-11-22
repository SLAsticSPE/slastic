package org.trustsoft.slastic.simulation.software.controller.controlflow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.software.controller.CallHandler;
import org.trustsoft.slastic.simulation.software.controller.StackFrame;

public class AcquireNode extends ControlFlowNode {

	static private Log log = LogFactory.getLog(AcquireNode.class);
	private final String resName;

	public AcquireNode(final String name, final String traceId,
			final String resName) {
		super(name, traceId);
		this.resName = resName;

	}

	@Override
	public void eventRoutine() {
		final ControlFlowNode next = CallHandler.getInstance().getNextInTrace(
				this.getTraceId());
		if (next == null) {
			AcquireNode.log
					.warn("There is no release action following the acquiration!");
		}
		final StackFrame f = CallHandler.getInstance().getStackTop(
				this.getTraceId());
		final String asm = f.getAsmContextTo(), server = f.getServerId();
		ModelManager.getInstance().getAllocCont()
				.acquirePassive(server, asm, this.resName, next);
	}

}
