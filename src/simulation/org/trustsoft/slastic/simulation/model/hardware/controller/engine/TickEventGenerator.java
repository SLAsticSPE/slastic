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

package org.trustsoft.slastic.simulation.model.hardware.controller.engine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.config.Constants;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimTime;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class TickEventGenerator {

	private static Log LOG = LogFactory.getLog(TickEventGenerator.class);

	private final AbstractScheduler<?, ? extends AbstractSchedulableProcess> scheduler;
	private final String name;
	private final Model owner;

	public TickEventGenerator(
			final Model owner,
			final String name,
			final boolean showInTrace,
			final AbstractProcessingResource<? extends AbstractSchedulableProcess> ressource) {
		this.owner = owner;
		this.name = name;
		this.scheduler = ressource.getScheduler();
	}

	public TickEventGenerator(
			final Model owner,
			final String name,
			final boolean showInTrace,
			final AbstractScheduler<?, ? extends AbstractSchedulableProcess> sched) {
		this.owner = owner;
		this.name = name;
		this.scheduler = sched;
	}

	/**
	 * Schedule next tick event
	 * 
	 * @see desmoj.core.simulator.ExternalEvent#eventRoutine()
	 */
	public void tick() {
		LOG.info("CPU ticks at " + this.owner.currentTime());
		if (this.scheduler.isIdle()) {
			return;
		} else {
			final SimTime nextTick = this.scheduler.tick();
			if (nextTick != null) {
				final TickEvent te = new TickEvent(this.owner, this.name, Constants.DEBUG, this);
				te.schedule(nextTick);
			}
		}
	}

	public void resume() {
		final TickEvent te = new TickEvent(this.owner, this.name, Constants.DEBUG, this);
		te.schedule(SimTime.NOW);
	}

}
