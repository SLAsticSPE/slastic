package org.trustsoft.slastic.simulation.software.controller;

public final class EntryCall {

	private final long tin;
	private final long traceId;
	private final String opname;
	private final String componentName;
	private final long tout;
	private boolean scheduled = false;

	public EntryCall(final String componentName, final String opname,
			final long traceId, final long tin, final long tout) {
		this.componentName = componentName;
		this.opname = opname;
		this.traceId = traceId;
		this.tin = tin;
		this.tout = tout;
	}

	public long getTin() {
		return tin;
	}

	public long getTraceId() {
		return traceId;
	}

	public String getOpname() {
		return opname;
	}

	public String getComponentName() {
		return componentName;
	}

	public long getTout() {
		return tout;
	}

	public boolean isScheduled() {
		return scheduled;
	}

	public void setScheduled(final boolean state) {
		scheduled = state;
	}

}
