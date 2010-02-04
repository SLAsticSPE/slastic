package org.trustsoft.slastic.simulation.software.controller.controlflow;

import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.software.controller.CallHandler;
import org.trustsoft.slastic.simulation.software.controller.StackFrame;

import de.uka.ipd.sdq.pcm.repository.Signature;
import desmoj.core.simulator.SimTime;

public class ExternalCallEnterNode extends ControlFlowNode {

	private final String asmContextTo;
	private final String asmContextFrom;
	private String serverId;
	private final String traceId;
	private SimTime enterTime;
	private final String calledServiceName;

	public ExternalCallEnterNode(final Signature calledService_ExternalService,
			final String asmContextCurrent, final String traceId) {
		super(calledService_ExternalService.getServiceName()
				+ " from "
				+ asmContextCurrent
				+ " to "
				+ (ModelManager.getInstance().getAssemblyCont()
						.getServiceASMContextConnectedWithContext(
								calledService_ExternalService.getServiceName(),
								asmContextCurrent)), traceId);
		calledServiceName = calledService_ExternalService.getServiceName();
		this.traceId = traceId;
		asmContextFrom = asmContextCurrent;
		asmContextTo = ModelManager.getInstance().getAssemblyCont()
				.getServiceASMContextConnectedWithContext(
						calledService_ExternalService.getServiceName(),
						asmContextCurrent);
	}

	@Override
	public void eventRoutine() {
		serverId = ModelManager.getInstance().getAllocCont().getServer(
				asmContextTo);
		ModelManager.getInstance().getAllocCont().addUser(asmContextTo,
				serverId);
		// TODO Start monitoring here!
		enterTime = getModel().currentTime();
		CallHandler.getInstance().pushContext(
				traceId,
				new StackFrame(traceId, calledServiceName, asmContextTo,
						serverId, enterTime.getTimeValue()));
	}

	public String getASMCont() {
		return asmContextTo;
	}

	public String getTraceId() {
		return traceId;
	}

	public String getServerId() {
		return serverId;
	}

	public SimTime getEnterTime() {
		return enterTime;
	}

	public String getCalledService() {
		return calledServiceName;
	}
}
