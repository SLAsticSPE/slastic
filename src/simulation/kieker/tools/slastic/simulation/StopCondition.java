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

package kieker.tools.slastic.simulation;

import desmoj.core.simulator.Condition;
import desmoj.core.simulator.Entity;

/**
 * 
 * @author Robert von Massow
 * 
 */
public final class StopCondition extends Condition {

	private boolean stopped;

	public boolean isStopped() {
		return this.stopped;
	}

	public boolean setStopped(final boolean stopped) {
		return this.stopped = stopped;
	}

	public StopCondition(final DynamicSimulationModel owner, final String name,
			final boolean showInTrace) {
		super(owner, name, showInTrace);
	}

	@Override
	public boolean check() {
		return this.stopped;
	}

	@Override
	public boolean check(final Entity arg0) {
		return this.stopped;
	}
}
