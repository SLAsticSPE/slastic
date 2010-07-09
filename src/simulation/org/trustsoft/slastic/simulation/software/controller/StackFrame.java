package org.trustsoft.slastic.simulation.software.controller;


import kieker.common.record.OperationExecutionRecord;
import org.trustsoft.slastic.simulation.config.Constants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.model.ModelManager;

public class StackFrame {
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

	public String getCalledServiceName() {
		return this.calledServiceName;
	}

	public String getAsmContextTo() {
		return this.asmContextTo;
	}

	public String getServerId() {
		return this.serverId;
	}

	public double getTimeEnter() {
		return this.timeEnter;
	}

	public OperationExecutionRecord createRecord(final double timeExit,
			final int depth, final int eoi) {
		final OperationExecutionRecord rec = new OperationExecutionRecord(
				ModelManager.getInstance().getAssemblyCont()
						.getASMInstanceAndComponentNameById(this.asmContextTo),
				this.calledServiceName, Long.parseLong(this.traceId),
				(long) (Constants.SIM_TIME_TO_MON_TIME * this.timeEnter),
				(long) (Constants.SIM_TIME_TO_MON_TIME * timeExit));
		rec.ess = depth;
		rec.eoi = eoi;
		rec.vmName = this.serverId;
		return rec;
	}

	public String getTraceId() {
		return this.traceId;
	}

	public int getEoi() {
		return this.eoi;
	}

	public void setEoi(final int eoi) {
		this.eoi = eoi;
	}

}
