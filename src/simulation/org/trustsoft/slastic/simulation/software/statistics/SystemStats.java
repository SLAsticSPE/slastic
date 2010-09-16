package org.trustsoft.slastic.simulation.software.statistics;

import kieker.monitoring.core.MonitoringController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.model.hardware.controller.cpu.UtilizationRecord;
import org.trustsoft.slastic.simulation.software.controller.StackFrame;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import desmoj.core.simulator.Model;

@Singleton
public final class SystemStats implements ISystemStats {

	private int users;
	private final MonitoringController mCtrl = MonitoringController
			.getInstance();
	private final Model model = ModelManager.getInstance().getModel();
	private final Log log = LogFactory.getLog(this.getClass());

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
	public final void logCPUUsage(final String server, final double usage) {
		final UtilizationRecord urectum = new UtilizationRecord();
		urectum.setUtilization(usage);
		urectum.setTime(this.getMonTime());
		urectum.setServer(server);
		this.mCtrl.newMonitoringRecord(urectum);
	}

	@Override
	public final void logExecution(final StackFrame frame, final int depth) {
		this.mCtrl.newMonitoringRecord(frame.createRecord(this.model.getModel()
				.currentTime().getTimeValue(), depth));
	}

}
