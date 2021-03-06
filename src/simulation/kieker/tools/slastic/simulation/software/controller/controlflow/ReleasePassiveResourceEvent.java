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

package kieker.tools.slastic.simulation.software.controller.controlflow;

import kieker.tools.slastic.simulation.model.ModelManager;
import kieker.tools.slastic.simulation.software.controller.CallHandler;
import kieker.tools.slastic.simulation.software.controller.StackFrame;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class ReleasePassiveResourceEvent extends AbstractControlFlowEvent {

	private final String resourceName;

	public ReleasePassiveResourceEvent(final String name, final String traceId, final String resName) {
		super(name, traceId);
		this.resourceName = resName;
	}

	@Override
	public void eventRoutine() {
		final StackFrame f = CallHandler.getInstance().getStackTop(this.getTraceId());
		ModelManager.getInstance().getAllocationController().releasePassiveResource(f.getServerId(), f.getAsmContextTo(), this.resourceName);
		CallHandler.getInstance().actionReturn(this.getTraceId());
	}
}
