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

import kieker.tools.slastic.simulation.model.hardware.controller.engine.AbstractProcessingResource;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimTime;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class CPU extends AbstractProcessingResource<CPUSchedulableProcess> {

	public CPU(final Model owner, final String myName, final boolean debugMode, final AbstractCPUScheduler scheduler, final int capacity) {
		super(owner, myName, debugMode, scheduler, capacity);
		scheduler.setOwner(this);
	}

	@Override
	public double getBusiness() {
		return this.getScheduler().getBusiness();
	}

	@Override
	public void init() {}

	public void resumeMonitoringAt(final SimTime t) {
		super.getScheduler().resumeBusinessMonitoringAt(t);
	}

	public void pauseMonitoring() {
		this.getScheduler().pauseBusinessMonitoring();
	}
}
