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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.tools.slastic.simulation.software.controller.controlflow.InternalActionEvent;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;

/**
 * 
 * @author Robert von Massow
 * 
 */
public abstract class AbstractSchedulableProcess extends Entity {
	private static Log LOG = LogFactory.getLog(AbstractSchedulableProcess.class);

	private volatile long ticksRemaining;
	private final InternalActionEvent internalActionEvent;

	public AbstractSchedulableProcess(final Model owner, final String name, final boolean showInTrace, final long ticks, final InternalActionEvent belongs) {
		super(owner, name, showInTrace);
		this.ticksRemaining = ticks;
		this.internalActionEvent = belongs;
	}

	public long getCyclesRemaining() {
		return this.ticksRemaining;
	}

	public void substractFromRemaining(final long ticksRemaining) {
		if (ticksRemaining > 0) {
			this.ticksRemaining -= ticksRemaining;
			if (this.ticksRemaining <= 0) {
				LOG.info("Done Processing " + this + " at sim time " + this.currentTime());
				this.getBelongs().returned(this.currentTime(), this);
			}
		}
	}

	public final InternalActionEvent getBelongs() {
		return this.internalActionEvent;
	}

}
