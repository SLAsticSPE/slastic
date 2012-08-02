package org.trustsoft.slastic.simulation.software.controller;

import kieker.common.record.controlflow.OperationExecutionRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.model.ModelManager;

public class StackFrame {
	@SuppressWarnings("unused")
	private final static Log log = LogFactory.getLog(StackFrame.class);

	private final String calledServiceName;
	private final String asmContextTo;
	private final String serverId;
	private final double timeEnter;
	private final String traceId;

	private int eoi = 0;

	public StackFrame(final String traceId, final String calledServiceName,
			final String asmContextTo, final String serverId, final double time) {
		this.traceId = traceId;
		this.calledServiceName = calledServiceName;
		this.asmContextTo = asmContextTo;
		this.serverId = serverId;
		this.timeEnter = time;
	}

	public final String getCalledServiceName() {
		return this.calledServiceName;
	}

	public final String getAsmContextTo() {
		return this.asmContextTo;
	}

	public final String getServerId() {
		return this.serverId;
	}

	public final double getTimeEnter() {
		return this.timeEnter;
	}

	public final OperationExecutionRecord createRecord(final double timeExit,
			final int depth) {
		
		final String componentName = ModelManager.getInstance().getAssemblyCont().getASMInstanceAndComponentNameById(this.asmContextTo); 
		final String operationSignature = componentName + "." + this.calledServiceName;
		final long tin = (long) (Constants.SIM_TIME_TO_MON_TIME * this.timeEnter); 
		final long tout = (long) (Constants.SIM_TIME_TO_MON_TIME * timeExit); 
		final long traceId = Long.parseLong(this.traceId);
		final String hostname = this.serverId;
		final int eoi = this.eoi;
		final int ess = depth;
				
		final OperationExecutionRecord rec = new OperationExecutionRecord(operationSignature, OperationExecutionRecord.NO_SESSION_ID, traceId, tin, tout, hostname, eoi, ess);
		return rec;
	}

	public final String getTraceId() {
		return this.traceId;
	}

	public final int getEoi() {
		return this.eoi;
	}

	public final void setEoi(final int eoi) {
		this.eoi = eoi;
	}

}
