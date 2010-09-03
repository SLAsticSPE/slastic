package org.trustsoft.slastic.simulation.model.hardware.controller.cpu;

import kieker.monitoring.core.MonitoringController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.config.Constants;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimTime;

// TODO do only for allocated server cpus
public class UtilizationProbeEventGenerator {

	private static final MonitoringController monCtrl = MonitoringController
			.getInstance();
	private static final SimTime TICK_TIME = new SimTime(.5);
	private final Model model;
	private final String name;
	private final boolean debug;
	private final CPURRScheduler scheduler;
	private final Log log = LogFactory.getLog(this.getClass());
	private boolean pause;

	public UtilizationProbeEventGenerator(final Model model, final String name,
			final boolean debug, final CPURRScheduler cpurrScheduler) {
		this.model = model;
		this.name = name;
		this.debug = debug;
		this.scheduler = cpurrScheduler;
	}

	public void tick() {
		final UtilizationProbeTick tick = new UtilizationProbeTick(this.model,
				this.name, this.debug, this);
		if(!this.pause) {
			tick.schedule(UtilizationProbeEventGenerator.TICK_TIME);
		}

		final double util = this.scheduler.recalcUtilization();
		final UtilizationRecord urectum = new UtilizationRecord();
		urectum.setUtilization(util);
		urectum.setTime((long) (Constants.SIM_TIME_TO_MON_TIME * this.model
				.currentTime().getTimeValue()));
		// this.log.info("util: " + util + "@"
		// + this.model.currentTime().getTimeValue());
		UtilizationProbeEventGenerator.monCtrl.newMonitoringRecord(urectum);
	}

	public void pause() {
		this.pause = true;
	}

	public void resume() {
		this.pause = false;
	}

	public void resumeAt(final SimTime t) {
		this.resume();
		final UtilizationProbeTick tick = new UtilizationProbeTick(this.model, this.name,
				this.debug, this);
		tick.schedule(t);
	}

}
