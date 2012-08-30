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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import kieker.tools.slastic.simulation.model.ModelManager;
import kieker.tools.slastic.simulation.software.controller.CallHandler;
import kieker.tools.slastic.simulation.software.controller.StackFrame;
import kieker.tools.slastic.simulation.software.statistics.ISystemStats;

import de.uka.ipd.sdq.pcm.repository.Signature;
import desmoj.core.simulator.SimTime;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class ExternalCallEnterEvent extends AbstractControlFlowEvent {
	private static final Log LOG = LogFactory.getLog(ExternalCallEnterEvent.class);

	@Inject
	@Named("SystemUsersOnCall")
	private static ISystemStats stats;

	private final String asmContextFrom;
	private volatile String asmContextTo;
	private final String calledServiceName;
	private volatile String serverIdTo;

	public ExternalCallEnterEvent(final Signature calledService_ExternalService, final String asmContextCurrent, final String traceId) {
		super(calledService_ExternalService.getServiceName() + " from " + asmContextCurrent, traceId);
		this.calledServiceName = calledService_ExternalService.getServiceName();
		this.asmContextFrom = asmContextCurrent;
		if (asmContextCurrent != null) {
			this.asmContextTo = ModelManager.getInstance().getAssemblyController()
					.getServiceASMContextConnectedWithContext(calledService_ExternalService.getServiceName(), asmContextCurrent);
		} else {
			this.asmContextTo = ModelManager.getInstance().getAssemblyController().getAssemblyContextBySystemServiceName(this.calledServiceName);
		}
	}

	@SuppressWarnings("unused")
	private void setAsmContextAndSignature(final Signature calledService_ExternalService, final String asmContextCurrent) {
		if (asmContextCurrent != null) {
			this.asmContextTo =
					ModelManager.getInstance().getAssemblyController()
							.getServiceASMContextConnectedWithContext(calledService_ExternalService.getServiceName(), asmContextCurrent);
		} else {
			this.asmContextTo = ModelManager.getInstance().getAssemblyController().getAssemblyContextBySystemServiceName(this.calledServiceName);
		}
	}

	@Override
	public final void eventRoutine() {
		if (this.asmContextFrom == null) {
			stats.addSystemUser();
		}
		this.serverIdTo = ModelManager.getInstance().getAllocationController().getServerLoadBalanced(this.asmContextTo);
		ModelManager.getInstance().getAllocationController().addUser(this.asmContextTo, this.serverIdTo);
		// TODO Start monitoring here!
		final SimTime enterTime = this.getModel().currentTime();
		LOG.debug("External Call from "
				+ this.asmContextFrom + " to Service " + this.calledServiceName
				+ " on asm context " + this.asmContextTo + "on server "
				+ this.serverIdTo + " at simtime " + this.currentTime());
		CallHandler.getInstance().pushContext(this.getTraceId(),
				new StackFrame(this.getTraceId(), this.calledServiceName, this.asmContextTo, this.serverIdTo, enterTime.getTimeValue()));
		CallHandler.getInstance().actionReturn(this.getTraceId());
	}

	public final String getASMContTo() {
		return this.asmContextTo;
	}

	public final String getServerIdTo() {
		return this.serverIdTo;
	}

	public final String getCalledService() {
		return this.calledServiceName;
	}

	public final String getASMContFrom() {
		return this.asmContextFrom;
	}
}
