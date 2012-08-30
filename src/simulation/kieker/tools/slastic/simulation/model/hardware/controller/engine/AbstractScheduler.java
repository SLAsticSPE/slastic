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

package kieker.tools.slastic.simulation.model.hardware.controller.engine;

import kieker.tools.slastic.simulation.config.Constants;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import desmoj.core.simulator.SimTime;

/**
 * 
 * @author Robert von Massow
 * 
 * @param <PRType>
 * @param <SPType>
 */
public abstract class AbstractScheduler<PRType extends AbstractProcessingResource<?>, SPType extends AbstractSchedulableProcess> extends Entity {

	private volatile PRType owner;
	private volatile long tickRate;
	private volatile SimTime tickSimTime;
	private volatile boolean timeUnitSet, tickRateSet;
	private volatile TimeUnit unit;

	private volatile TickEventGenerator tickEventGenerator;

	protected final Queue<SPType> queue;

	public AbstractScheduler(final Model model, final String name, final Queue<SPType> queue) {
		super(model, name, Constants.DEBUG);
		this.queue = queue;
	}

	public void setOwner(final PRType owner) {
		if (this.owner == null) {
			this.owner = owner;
			this.tickEventGenerator = new TickEventGenerator(this.getModel(), this.getName(), Constants.DEBUG, this.owner);
		}
	}

	public PRType getOwner() {
		return this.owner;
	}

	public abstract void schedule(SPType process);

	public long getTickRate() {
		return this.tickRate;
	}

	public void setTickRate(final long l) {
		if (!this.tickRateSet) {
			this.tickRate = l;
			this.tickSimTime = new SimTime(l / 1000.0);
			this.tickRateSet = true;
		}
	}

	public TimeUnit getUnit() {
		return this.unit;
	}

	public void setUnit(final TimeUnit unit) {
		if (!this.timeUnitSet) {
			this.unit = unit;
			this.timeUnitSet = true;
		}
	}

	public boolean isTimeUnitSet() {
		return this.timeUnitSet;
	}

	public boolean isTickRateSet() {
		return this.tickRateSet;
	}

	public abstract SimTime tick();

	public boolean isIdle() {
		return this.queue.isEmpty();
	}

	public TickEventGenerator getTickEventGenerator() {
		return this.tickEventGenerator;
	}

	public abstract Queue<? extends AbstractSchedulableProcess> getQueue();

	public SimTime getTickSimTime() {
		return this.tickSimTime;
	}

	public abstract double getBusiness();

	public int getProcessCount() {
		return this.queue.length();
	}

	public abstract void resumeBusinessMonitoringAt(SimTime t);

	public abstract void pauseBusinessMonitoring();

}
