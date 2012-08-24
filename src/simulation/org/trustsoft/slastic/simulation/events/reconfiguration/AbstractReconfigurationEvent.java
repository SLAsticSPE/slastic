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

package org.trustsoft.slastic.simulation.events.reconfiguration;

import org.trustsoft.slastic.simulation.model.ModelManager;

import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;

/**
 * 
 * @author Robert von Massow
 * 
 */
public abstract class AbstractReconfigurationEvent extends ExternalEvent {
	private final SLAsticReconfigurationOpType reconfOp;

	public AbstractReconfigurationEvent(final Model owner, final String name, final boolean showInTrace, final SLAsticReconfigurationOpType reconfOp) {
		super(owner, name, showInTrace);
		this.reconfOp = reconfOp;
	}

	@Override
	final public void eventRoutine() {
		if (this.concreteEventRoutine()) {
			// TODO if dereplication wait for component empty event
			ModelManager.getInstance().getReconfigurationController().operationFinished(this.reconfOp);
		} else {
			ModelManager.getInstance().getReconfigurationController().operationFailed(this.reconfOp);
		}
	}

	abstract public boolean concreteEventRoutine();

	public final SLAsticReconfigurationOpType getReconfOp() {
		return this.reconfOp;
	}

}
