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
public class CFFrame {
	private final ResourceDemandingBehaviour seff;

	private AbstractAction action;

	private final String asmContextCurrent;

	public CFFrame(final ResourceDemandingBehaviour seff,
			final AbstractAction headAction, final String asmContext) {
		this.seff = seff;
		this.action = headAction;
		this.asmContextCurrent = asmContext;
	}

	/**
	 * @return the seff
	 */
	public final ResourceDemandingBehaviour getSeff() {
		return this.seff;
	}

	/**
	 * @return the action
	 */
	public final AbstractAction getAction() {
		return this.action;
	}

	/**
	 * @return the asmContextCurrent
	 */
	public final String getAsmContextCurrent() {
		return this.asmContextCurrent;
	}

	public final void setAction(final AbstractAction action) {
		this.action = action;
	}
}
