package org.trustsoft.slastic.simulation.software.controller.controlflow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.software.controller.CallHandler;
import org.trustsoft.slastic.simulation.software.controller.StackFrame;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class AcquireEvent extends AbstractControlFlowEvent {

	private static final Log LOG = LogFactory.getLog(AcquireEvent.class);

	private final String resName;

	public AcquireEvent(final String name, final String traceId,
			final String resName) {
		super(name, traceId);
		this.resName = resName;

	}

	@Override
	public void eventRoutine() {
		final AbstractControlFlowEvent next = CallHandler.getInstance().getNextInTrace(
				this.getTraceId());
		if (next == null) {
			LOG.warn("There is no release action following the acquiration!");
		}
		final StackFrame f = CallHandler.getInstance().getStackTop(this.getTraceId());
		final String asm = f.getAsmContextTo(), server = f.getServerId();
		ModelManager.getInstance().getAllocationController().acquirePassive(server, asm, this.resName, next);
	}

}
