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
import org.trustsoft.slastic.simulation.software.statistics.ISystemStats;

import com.google.inject.Inject;
import com.google.inject.name.Named;

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

	private volatile String asmContextTo;
	private final String asmContextFrom;
	private volatile String serverId;
	private final String traceId;

	private volatile SimTime enterTime;
	private final String calledServiceName;

	public ExternalCallEnterEvent(final Signature calledService_ExternalService, final String asmContextCurrent, final String traceId) {
		super(calledService_ExternalService.getServiceName() + " from " + asmContextCurrent, traceId);
		this.calledServiceName = calledService_ExternalService.getServiceName();
		this.traceId = traceId;
		this.asmContextFrom = asmContextCurrent;
		if (asmContextCurrent != null) {
			this.asmContextTo = ModelManager.getInstance().getAssemblyController()
					.getServiceASMContextConnectedWithContext(calledService_ExternalService.getServiceName(), asmContextCurrent);
		} else {
			this.asmContextTo = ModelManager.getInstance().getAssemblyController().getASMContextBySystemService(this.calledServiceName);
		}
	}

	@SuppressWarnings("unused")
	private void setAsmContextAndSignature(
			final Signature calledService_ExternalService,
			final String asmContextCurrent) {
		if (asmContextCurrent != null) {
			this.asmContextTo = ModelManager.getInstance().getAssemblyController()
					.getServiceASMContextConnectedWithContext(calledService_ExternalService.getServiceName(), asmContextCurrent);
		} else {
			this.asmContextTo = ModelManager.getInstance().getAssemblyController().getASMContextBySystemService(this.calledServiceName);
		}
	}

	@Override
	public final void eventRoutine() {
		if (this.asmContextFrom == null) {
			stats.addSystemUser();
		}
		this.serverId = ModelManager.getInstance().getAllocationController().getServer(this.asmContextTo);
		ModelManager.getInstance().getAllocationController().addUser(this.asmContextTo, this.serverId);
		// TODO Start monitoring here!
		this.enterTime = this.getModel().currentTime();
		LOG.debug("External Call from "
				+ this.asmContextFrom + " to Service " + this.calledServiceName
				+ " on asm context " + this.asmContextTo + "on server "
				+ this.serverId + " at simtime " + this.currentTime());
		CallHandler.getInstance().pushContext(
				this.traceId,
				new StackFrame(this.traceId, this.calledServiceName,
						this.asmContextTo, this.serverId, this.enterTime
								.getTimeValue()));
		CallHandler.getInstance().actionReturn(this.traceId);
	}

	public final String getASMContTo() {
		return this.asmContextTo;
	}

	public final String getServerId() {
		return this.serverId;
	}

	public final SimTime getEnterTime() {
		return this.enterTime;
	}

	public final String getCalledService() {
		return this.calledServiceName;
	}

	public final String getASMContFrom() {
		return this.asmContextFrom;
	}
}
