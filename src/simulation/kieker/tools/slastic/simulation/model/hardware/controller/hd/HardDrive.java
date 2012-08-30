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

package kieker.tools.slastic.simulation.model.hardware.controller.hd;

import kieker.tools.slastic.simulation.model.hardware.controller.engine.AbstractProcessingResource;
import kieker.tools.slastic.simulation.model.hardware.controller.engine.AbstractScheduler;

import desmoj.core.advancedModellingFeatures.Res;
import desmoj.core.simulator.Model;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class HardDrive extends AbstractProcessingResource<IOOperation> {

	@SuppressWarnings("unused")
	private Res res;

	public HardDrive(final Model owner, final String name,
			final boolean showInTrace,
			final AbstractScheduler<HardDrive, IOOperation> scheduler,
			final int capacity) {
		super(owner, name, showInTrace, scheduler, capacity);
		scheduler.setOwner(this);
	}

	@Override
	public double getBusiness() {
		throw new UnsupportedOperationException("Needs to be implemented");
	}

	@Override
	public void init() {
		throw new UnsupportedOperationException("Needs to be implemented");
	}

}
