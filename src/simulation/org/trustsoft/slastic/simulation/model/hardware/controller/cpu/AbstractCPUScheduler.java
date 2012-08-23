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

package org.trustsoft.slastic.simulation.model.hardware.controller.cpu;

import org.trustsoft.slastic.simulation.model.hardware.controller.engine.AbstractScheduler;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import desmoj.core.simulator.SimTime;

/**
 * 
 * @author Robert von Massow
 * 
 */
public abstract class AbstractCPUScheduler extends AbstractScheduler<CPU, CPUSchedulableProcess> {

	public AbstractCPUScheduler(final Model model, final String name,
			final Queue<CPUSchedulableProcess> queue) {
		super(model, name, queue);
	}

	@Override
	public final Queue<CPUSchedulableProcess> getQueue() {
		return this.queue;
	}

	@Override
	public abstract void schedule(final CPUSchedulableProcess process);

	@Override
	public abstract SimTime tick();

}
