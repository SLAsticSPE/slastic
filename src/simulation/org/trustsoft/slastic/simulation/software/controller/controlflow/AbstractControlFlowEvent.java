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

package org.trustsoft.slastic.simulation.software.controller.controlflow;

import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.model.ModelManager;

import desmoj.core.simulator.ExternalEvent;

/**
 * 
 * @author Robert von Massow
 * 
 */
public abstract class AbstractControlFlowEvent extends ExternalEvent {

	private final String traceId;

	public AbstractControlFlowEvent(final String name, final String traceId) {
		super(ModelManager.getInstance().getModel(), name, Constants.DEBUG);
		this.traceId = traceId;
	}

	/**
	 * @return the traceId
	 */
	public final String getTraceId() {
		return this.traceId;
	}

	@Override
	public abstract void eventRoutine();
}
