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
		super(calledService_ExternalService.getServiceName() + " from "
				+ asmContextCurrent + " to "
		// + (ModelManager.getInstance().getAssemblyCont()
				// .getServiceASMContextConnectedWithContext(
				// calledService_ExternalService.getServiceName(),
				// asmContextCurrent)),
				, traceId);
		this.calledServiceName = calledService_ExternalService.getServiceName();
		this.traceId = traceId;
		this.asmContextFrom = asmContextCurrent;
		this.asmContextTo = ModelManager.getInstance().getAssemblyCont()
				.getServiceASMContextConnectedWithContext(
						calledService_ExternalService.getServiceName(),
						asmContextCurrent);
	}

	@Override
	public void eventRoutine() {
		this.serverId = ModelManager.getInstance().getAllocCont().getServer(
				this.asmContextTo);
		ModelManager.getInstance().getAllocCont().addUser(this.asmContextTo,
				this.serverId);
		// TODO Start monitoring here!
		this.enterTime = this.getModel().currentTime();
		CallHandler.getInstance().pushContext(
				this.traceId,
				new StackFrame(this.traceId, this.calledServiceName,
						this.asmContextTo, this.serverId, this.enterTime
								.getTimeValue()));
	}

	public String getASMCont() {
		return this.asmContextTo;
	}

	public String getTraceId() {
		return this.traceId;
	}

	public String getServerId() {
		return this.serverId;
	}

	public SimTime getEnterTime() {
		return this.enterTime;
	}

	public String getCalledService() {
		return this.calledServiceName;
	}
}
