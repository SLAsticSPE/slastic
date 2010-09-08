package org.trustsoft.slastic.simulation.software.statistics;

import kieker.monitoring.core.MonitoringController;

import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.model.hardware.controller.cpu.UtilizationRecord;

import com.google.inject.Inject;

import desmoj.core.simulator.Model;

//@Singleton
public class SystemStats implements ISystemStats {

	private int users;
	private final MonitoringController mCtrl = MonitoringController
			.getInstance();
	private final Model model = ModelManager.getInstance().getModel();

	@Inject
	public SystemStats() {
	}

	@Override
	public final synchronized void addSystemUser() {
		this.users++;
		this.mCtrl.newMonitoringRecord(new ActiveUsersRecord(this.users, this
				.getMonTime()));
	}

	private final long getMonTime() {
		return (long) (this.model.currentTime().getTimeValue() * Constants.SIM_TIME_TO_MON_TIME);
	}

	@Override
	public final synchronized void subSystemUser() {
		this.users--;
		this.mCtrl.newMonitoringRecord(new ActiveUsersRecord(this.users, this
				.getMonTime()));
	}

	@Override
	public final void logComponentUsers(final String component,
			final String host, final Integer users) {
		this.mCtrl.newMonitoringRecord(new ComponentUsersRecord(component,
				host, users, this.getMonTime()));
	}

	@Override
	public void logCPUUsage(final String server, final double usage) {
		final UtilizationRecord urectum = new UtilizationRecord();
		urectum.setUtilization(usage);
		urectum.setTime(this.getMonTime());
		urectum.setServer(server);
		this.mCtrl.newMonitoringRecord(urectum);
	}

}
