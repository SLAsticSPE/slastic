package org.trustsoft.slastic.simulation.software.controller.controlflow;

import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.software.controller.CallHandler;
import org.trustsoft.slastic.simulation.software.controller.StackFrame;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class ReleaseEvent extends AbstractControlFlowEvent {

	private final String resName;

	public ReleaseEvent(final String name, final String traceId, final String resName) {
		super(name, traceId);
		this.resName = resName;
	}

	@Override
	public void eventRoutine() {
		final StackFrame f = CallHandler.getInstance().getStackTop(this.getTraceId());
		ModelManager.getInstance().getAllocationController().releasePassive(f.getServerId(), f.getAsmContextTo(), this.resName);
		CallHandler.getInstance().actionReturn(this.getTraceId());
	}
}
