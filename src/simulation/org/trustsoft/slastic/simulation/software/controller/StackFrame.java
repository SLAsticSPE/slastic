package org.trustsoft.slastic.simulation.software.controller;

import kieker.common.record.OperationExecutionRecord;

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
		final OperationExecutionRecord rec = new OperationExecutionRecord(
				ModelManager.getInstance().getAssemblyCont()
						.getASMInstanceAndComponentNameById(this.asmContextTo),
				this.calledServiceName, Long.parseLong(this.traceId),
				(long) (Constants.SIM_TIME_TO_MON_TIME * this.timeEnter),
				(long) (Constants.SIM_TIME_TO_MON_TIME * timeExit));
		rec.ess = depth;
		rec.eoi = this.eoi;
		rec.hostName = this.serverId;
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
