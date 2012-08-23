package org.trustsoft.slastic.simulation.software.statistics;

import org.trustsoft.slastic.simulation.software.controller.StackFrame;

/**
 * 
 * @author Robert von Massow
 * 
 */
public interface ISystemStats {

	public void subSystemUser();

	public void addSystemUser();

	public void logComponentUsers(String component, String Host, Integer users);

	public void logCPUUsage(String server, double usage);

	public void logExecution(final StackFrame frame, final int depth);

}
