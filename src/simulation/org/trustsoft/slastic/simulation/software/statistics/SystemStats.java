package org.trustsoft.slastic.simulation.software.statistics;

import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.model.hardware.controller.cpu.UtilizationRecord;
import org.trustsoft.slastic.simulation.software.controller.StackFrame;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import desmoj.core.simulator.Model;

import kieker.monitoring.core.controller.IMonitoringController;
import kieker.monitoring.core.controller.MonitoringController;

/**
 * 
 * @author Robert von Massow
 * 
 */
@Singleton
public final class SystemStats implements ISystemStats {

	private volatile int numUsers;

	private final IMonitoringController mCtrl = MonitoringController.getInstance();

	private final Model model = ModelManager.getInstance().getModel();

	@Inject
	public SystemStats() {}

	@Override
	public final synchronized void addSystemUser() {
		this.numUsers++;
		this.mCtrl.newMonitoringRecord(new ActiveUsersRecord(this.numUsers, this.getMonTime()));
	}

	private final long getMonTime() {
		return (long) (this.model.currentTime().getTimeValue() * Constants.SIM_TIME_TO_MON_TIME);
	}

	@Override
	public final synchronized void subSystemUser() {
		this.numUsers--;
		this.mCtrl.newMonitoringRecord(new ActiveUsersRecord(this.numUsers, this.getMonTime()));
	}

	@Override
	public final void logComponentUsers(final String component, final String host, final Integer users) {
		this.mCtrl.newMonitoringRecord(new ComponentUsersRecord(component, host, users, this.getMonTime()));
	}

	@Override
	public final void logCPUUsage(final String server, final double usage) {
		final UtilizationRecord urectum = new UtilizationRecord();
		urectum.setUtilization(usage);
		urectum.setTime(this.getMonTime());
		urectum.setServer(server);
		this.mCtrl.newMonitoringRecord(urectum);
	}

	@Override
	public final void logExecution(final StackFrame frame, final int depth) {
		this.mCtrl.newMonitoringRecord(frame.createRecord(this.model.getModel().currentTime().getTimeValue(), depth));
	}
}
