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

package kieker.tools.slastic.control.components.modelManager;

import kieker.tools.slastic.common.event.IObservationEvent;
import kieker.tools.slastic.control.components.events.IEvent;
import kieker.tools.slastic.reconfiguration.ReconfigurationException;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import kieker.tools.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan;

/**
 * A model manager that simply doesn't do anything.
 * 
 * @author Andre van Hoorn
 */
public class DummyModelManagerComponent extends AbstractModelManagerComponent {

	@Override
	public void doReconfiguration(final SLAsticReconfigurationPlan plan)
			throws ReconfigurationException {
		// do nothing
	}

	@Override
	public void doReconfiguration(final ReconfigurationPlan plan)
			throws ReconfigurationException {
		// do nothing
	}

	@Override
	public void newObservation(final IObservationEvent event) {
		// do nothing
	}

	@Override
	public void handleEvent(final IEvent ev) {
		// do nothing
	}

	@Override
	public boolean init() {
		return true;
	}

	@Override
	public boolean execute() {
		return true;
	}

	@Override
	public void terminate(final boolean error) {
		// do nothing
	}
}
