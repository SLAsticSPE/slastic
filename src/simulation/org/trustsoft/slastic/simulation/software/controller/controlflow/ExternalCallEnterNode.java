package org.trustsoft.slastic.simulation.software.controller.controlflow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.software.controller.CallHandler;
import org.trustsoft.slastic.simulation.software.controller.StackFrame;
import org.trustsoft.slastic.simulation.software.statistics.ISystemStats;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import de.uka.ipd.sdq.pcm.repository.Signature;
import desmoj.core.simulator.SimTime;

public class ExternalCallEnterNode extends ControlFlowNode {

	@Inject
	@Named("SystemUsersOnCall")
	private static ISystemStats stats;

	private String asmContextTo;
	private final String asmContextFrom;
	private String serverId;
	private final String traceId;

	private SimTime enterTime;
	private final String calledServiceName;
	private static Log log = LogFactory.getLog(ExternalCallEnterNode.class);

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
		if (asmContextCurrent != null) {
			this.asmContextTo = ModelManager
					.getInstance()
					.getAssemblyCont()
					.getServiceASMContextConnectedWithContext(
							calledService_ExternalService.getServiceName(),
							asmContextCurrent);
		} else {
			this.asmContextTo = ModelManager.getInstance().getAssemblyCont()
					.getASMContextBySystemService(this.calledServiceName);
		}
		// this.stats =
		// CallHandler.getInstance().getStatFacade().getCallStats();
	}

	private void setAsmContextAndSignature(
			final Signature calledService_ExternalService,
			final String asmContextCurrent) {
		if (asmContextCurrent != null) {
			this.asmContextTo = ModelManager
					.getInstance()
					.getAssemblyCont()
					.getServiceASMContextConnectedWithContext(
							calledService_ExternalService.getServiceName(),
							asmContextCurrent);
		} else {
			this.asmContextTo = ModelManager.getInstance().getAssemblyCont()
					.getASMContextBySystemService(this.calledServiceName);
		}
	}

	@Override
	public final void eventRoutine() {
		if (this.asmContextFrom == null) {
			ExternalCallEnterNode.stats.addSystemUser();
		}
		this.serverId = ModelManager.getInstance().getAllocCont()
				.getServer(this.asmContextTo);
		ModelManager.getInstance().getAllocCont()
				.addUser(this.asmContextTo, this.serverId);
		// TODO Start monitoring here!
		this.enterTime = this.getModel().currentTime();
		ExternalCallEnterNode.log.debug("External Call from "
				+ this.asmContextFrom + " to Service " + this.calledServiceName
				+ " on asm context " + this.asmContextTo + "on server "
				+ this.serverId + " at simtime " + this.currentTime());
		CallHandler.getInstance().pushContext(
				this.traceId,
				new StackFrame(this.traceId, this.calledServiceName,
						this.asmContextTo, this.serverId, this.enterTime
								.getTimeValue()));
		CallHandler.getInstance().actionReturn(this.traceId);
	}

	public final String getASMContTo() {
		return this.asmContextTo;
	}

	public final String getTraceId() {
		return this.traceId;
	}

	public final String getServerId() {
		return this.serverId;
	}

	public final SimTime getEnterTime() {
		return this.enterTime;
	}

	public final String getCalledService() {
		return this.calledServiceName;
	}

	public final String getASMContFrom() {
		return this.asmContextFrom;
	}
}
