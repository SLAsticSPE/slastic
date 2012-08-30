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

package kieker.tools.slastic.simulation.software.controller.cfframes;

import de.uka.ipd.sdq.pcm.seff.AbstractAction;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingBehaviour;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class LoopFrame extends CFFrame {

	private final int max;
	private int i = 0;

	public LoopFrame(final ResourceDemandingBehaviour seff,
			final AbstractAction headAction, final String asmContext,
			final int iterations) {
		super(seff, headAction, asmContext);
		this.max = iterations;
	}

	/**
	 * @return the iterations
	 */
	public final int getIterations() {
		return this.max;
	}

	public int inc() {
		return ++this.i;
	}
}
