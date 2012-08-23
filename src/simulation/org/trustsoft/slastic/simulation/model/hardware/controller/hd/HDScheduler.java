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

package org.trustsoft.slastic.simulation.model.hardware.controller.hd;

import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.model.hardware.controller.engine.AbstractScheduler;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import desmoj.core.simulator.SimTime;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class HDScheduler extends AbstractScheduler<HardDrive, IOOperation> {

	public HDScheduler(final Model model, final String name) {
		super(model, name, new Queue<IOOperation>(model, name + "Queue", Constants.DEBUG, Constants.DEBUG));
	}

	@Override
	public void schedule(final IOOperation process) {
		throw new UnsupportedOperationException("Needs to be implemented");
	}

	@Override
	public SimTime tick() {
		throw new UnsupportedOperationException("Needs to be implemented");
	}

	@Override
	public Queue<IOOperation> getQueue() {
		return this.queue;
	}

	@Override
	public double getBusiness() {
		throw new UnsupportedOperationException("Needs to be implemented");
	}

	@Override
	public void resumeBusinessMonitoringAt(final SimTime t) {
		throw new UnsupportedOperationException("Needs to be implemented");
	}

	@Override
	public void pauseBusinessMonitoring() {
		throw new UnsupportedOperationException("Needs to be implemented");
	}

}
