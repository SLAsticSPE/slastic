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

	public final long getTin() {
		return this.tin;
	}

	public final long getTraceId() {
		return this.traceId;
	}

	public final String getOpname() {
		return this.opname;
	}

	public final String getComponentName() {
		return this.componentName;
	}

	public final long getTout() {
		return this.tout;
	}

	public final boolean isScheduled() {
		return this.scheduled;
	}

	public final void setScheduled(final boolean state) {
		this.scheduled = state;
	}

}
