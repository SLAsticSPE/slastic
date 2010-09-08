package org.trustsoft.slastic.simulation.software.statistics;

import org.trustsoft.slastic.simulation.software.controller.StackFrame;

public interface ISystemStats {

	public abstract void subSystemUser();

	public abstract void addSystemUser();

	public abstract void logComponentUsers(String component, String Host,
			Integer users);

	public abstract void logCPUUsage(String server, double usage);

	public abstract void logExecution(final StackFrame frame, final int depth);

}
