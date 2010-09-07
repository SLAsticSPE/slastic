package org.trustsoft.slastic.simulation.software.statistics;

import kieker.monitoring.core.MonitoringController;

import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.model.ModelManager;

import desmoj.core.simulator.Model;

public class SystemStats {

	private static int users;
	private static MonitoringController mCtrl = MonitoringController
			.getInstance();
	private static Model model = ModelManager.getInstance().getModel();

	public static final synchronized void addSystemUser() {
		SystemStats.users++;
		SystemStats.mCtrl.newMonitoringRecord(new ActiveUsersRecord(
				SystemStats.users, (long) (SystemStats.model.currentTime()
						.getTimeValue() * Constants.SIM_TIME_TO_MON_TIME)));
	}

	public static final synchronized void subSystemUser() {
		SystemStats.users--;
		SystemStats.mCtrl.newMonitoringRecord(new ActiveUsersRecord(
				SystemStats.users, (long) (SystemStats.model.currentTime()
						.getTimeValue() * Constants.SIM_TIME_TO_MON_TIME)));
	}
}
