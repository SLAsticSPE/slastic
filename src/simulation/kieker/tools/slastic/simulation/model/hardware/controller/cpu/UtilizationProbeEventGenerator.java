/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package kieker.tools.slastic.simulation.model.hardware.controller.cpu;


import com.google.inject.Inject;
import com.google.inject.name.Named;

import kieker.tools.slastic.simulation.model.ModelManager;
import kieker.tools.slastic.simulation.software.statistics.ISystemStats;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimTime;

/**
 * 
 * @author Robert von Massow
 * 
 */
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
	private volatile boolean pause;

	public UtilizationProbeEventGenerator(final Model model, final String name, final boolean debug, final CPURRScheduler cpurrScheduler) {
		this.model = model;
		this.name = name;
		this.debug = debug;
		this.scheduler = cpurrScheduler;
	}

	public final void tick() {
		final UtilizationProbeTickEvent tick = new UtilizationProbeTickEvent(this.model, this.name, this.debug, this);
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
		final SimTime t2 = SimTime.diff(t, ModelManager.getInstance().getModel().currentTime());
		this.resume();
		final UtilizationProbeTickEvent tick = new UtilizationProbeTickEvent(this.model, this.name, this.debug, this);
		tick.schedule(t2);
	}

}
