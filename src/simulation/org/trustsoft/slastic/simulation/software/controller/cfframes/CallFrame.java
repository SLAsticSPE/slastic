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

package org.trustsoft.slastic.simulation.software.controller.cfframes;

import org.trustsoft.slastic.simulation.software.controller.controlflow.ExternalCallEnterEvent;

import de.uka.ipd.sdq.pcm.seff.AbstractAction;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingBehaviour;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class CallFrame extends CFFrame {

	private final ExternalCallEnterEvent ece;

	public CallFrame(final ResourceDemandingBehaviour seff, final AbstractAction headAction, final String asmContext, final ExternalCallEnterEvent ece) {
		super(seff, headAction, asmContext);
		this.ece = ece;
	}

	/**
	 * @return the ece
	 */
	public final ExternalCallEnterEvent getEce() {
		return this.ece;
	}
}
