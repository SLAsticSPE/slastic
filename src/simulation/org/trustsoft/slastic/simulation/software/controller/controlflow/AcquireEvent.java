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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.software.controller.CallHandler;
import org.trustsoft.slastic.simulation.software.controller.StackFrame;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class AcquireEvent extends AbstractControlFlowEvent {

	private static final Log LOG = LogFactory.getLog(AcquireEvent.class);

	private final String resName;

	public AcquireEvent(final String name, final String traceId,
			final String resName) {
		super(name, traceId);
		this.resName = resName;

	}

	@Override
	public void eventRoutine() {
		final AbstractControlFlowEvent next = CallHandler.getInstance().getNextInTrace(
				this.getTraceId());
		if (next == null) {
			LOG.warn("There is no release action following the acquiration!");
		}
		final StackFrame f = CallHandler.getInstance().getStackTop(this.getTraceId());
		final String asm = f.getAsmContextTo(), server = f.getServerId();
		ModelManager.getInstance().getAllocationController().acquirePassive(server, asm, this.resName, next);
	}

}
