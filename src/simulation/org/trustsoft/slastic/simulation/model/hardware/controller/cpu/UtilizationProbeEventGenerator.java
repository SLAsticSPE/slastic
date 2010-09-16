package org.trustsoft.slastic.simulation.model.hardware.controller.cpu;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.software.statistics.ISystemStats;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimTime;

// TODO do only for allocated server cpus
public class UtilizationProbeEventGenerator {

	@Inject
	@Named("CPUUsage")
	private static ISystemStats stats;

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

	public final void tick() {
		final UtilizationProbeTick tick = new UtilizationProbeTick(this.model,
				this.name, this.debug, this);
		if (!this.pause) {
			tick.schedule(UtilizationProbeEventGenerator.TICK_TIME);
		}

		// this.log.warn(this.name);

		final double util = this.scheduler.recalcUtilization();

		// FIXME use injected stats!
		UtilizationProbeEventGenerator.stats.logCPUUsage(this.name, util);

		// this.log.info("util: " + util + "@"
		// + this.model.currentTime().getTimeValue());
	}

	public final void pause() {
		this.pause = true;
	}

	public final void resume() {
		this.pause = false;
	}

	public void resumeAt(final SimTime t) {
		final SimTime t2 = SimTime.diff(t, ModelManager.getInstance()
				.getModel().currentTime());
		this.resume();
		final UtilizationProbeTick tick = new UtilizationProbeTick(this.model,
				this.name, this.debug, this);
		tick.schedule(t2);
	}

}
