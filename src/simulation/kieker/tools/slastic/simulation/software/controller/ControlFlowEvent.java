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

package kieker.tools.slastic.simulation.software.controller;

import kieker.tools.slastic.simulation.config.Constants;

import de.uka.ipd.sdq.pcm.seff.AbstractAction;
import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;

/**
 * 
 * @author Robert von Massow
 * 
 * @param <T>
 */
// TODO: It seems that this class is not used at all
public class ControlFlowEvent<T extends AbstractAction> extends ExternalEvent {

	private final boolean resourceDemanding;
	private final T action;

	public ControlFlowEvent(final Model model, final String name, final T action, final boolean resourceDemanding) {
		super(model, name, Constants.DEBUG);
		this.resourceDemanding = resourceDemanding;
		this.action = action;
	}

	public boolean isResourceDemanding() {
		return this.resourceDemanding;
	}

	public T getAction() {
		return this.action;
	}

	@Override
	public void eventRoutine() {
		// TODO Auto-generated method stub
	}
}
