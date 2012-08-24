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

package org.trustsoft.slastic.simulation;

import desmoj.core.simulator.Experiment;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class DynamicSimExpirement extends Experiment {

	private final StopCondition stopCondition;

	public DynamicSimExpirement(final String name, final String referenceTime, final int referenceUnit, final StopCondition stopCondition) {
		super(name, referenceTime, referenceUnit);
		this.stopCondition = stopCondition;
	}

	public boolean stopRun() {
		return this.stopCondition.setStopped(true);
	}
}
