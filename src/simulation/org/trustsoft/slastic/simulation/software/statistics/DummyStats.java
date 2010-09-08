package org.trustsoft.slastic.simulation.software.statistics;

import org.trustsoft.slastic.simulation.software.controller.StackFrame;

import com.google.inject.Singleton;

@Singleton
public final class DummyStats implements ISystemStats {

	@Override
	public final void subSystemUser() {
	}

	@Override
	public final void addSystemUser() {
	}

	@Override
	public final void logComponentUsers(final String component,
			final String Host, final Integer users) {
	}

	@Override
	public final void logCPUUsage(final String server, final double usage) {
	}

	@Override
	public final void logExecution(final StackFrame frame, final int depth) {
	}

}
