package org.trustsoft.slastic.simulation.software.controller;

import org.trustsoft.slastic.simulation.config.Constants;

import kieker.tpmon.monitoringRecord.executions.KiekerExecutionRecord;

public class StackFrame {

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
		timeEnter = time;
	}

	public String getCalledServiceName() {
		return calledServiceName;
	}

	public String getAsmContextTo() {
		return asmContextTo;
	}

	public String getServerId() {
		return serverId;
	}

	public double getTimeEnter() {
		return timeEnter;
	}

	public KiekerExecutionRecord createRecord(final double timeExit,
			final int depth, final int eoi) {
		final KiekerExecutionRecord rec = KiekerExecutionRecord.getInstance(
				asmContextTo, calledServiceName, Long.parseLong(traceId),
				(long) (Constants.SIM_TIME_TO_MON_TIME * timeEnter),
				(long) (Constants.SIM_TIME_TO_MON_TIME * timeExit));
		rec.ess = depth;
		rec.eoi = eoi;
		return rec;
	}

	public String getTraceId() {
		return traceId;
	}

	public int getEoi() {
		return eoi;
	}

	public void setEoi(final int eoi) {
		this.eoi = eoi;
	}

}
