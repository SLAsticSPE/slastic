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

package org.trustsoft.slastic.simulation.software.controller;

import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.model.ModelManager;

import kieker.common.record.controlflow.OperationExecutionRecord;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class StackFrame {
	// private static final Log LOG = LogFactory.getLog(StackFrame.class);

	private final String calledServiceName;
	private final String asmContextTo;
	private final String serverId;
	private final double timeEnter;
	private final String traceId;

	private volatile int eoi = 0;

	public StackFrame(final String traceId, final String calledServiceName, final String asmContextTo, final String serverId, final double time) {
		this.traceId = traceId;
		this.calledServiceName = calledServiceName;
		this.asmContextTo = asmContextTo;
		this.serverId = serverId;
		this.timeEnter = time;
	}

	public final String getCalledServiceName() {
		return this.calledServiceName;
	}

	public final String getAsmContextTo() {
		return this.asmContextTo;
	}

	public final String getServerId() {
		return this.serverId;
	}

	public final double getTimeEnter() {
		return this.timeEnter;
	}

	public final OperationExecutionRecord createRecord(final double timeExit, final int depth) {

		final String componentName = ModelManager.getInstance().getAssemblyController().getASMInstanceAndComponentNameById(this.asmContextTo);
		final String operationSignature = componentName + "." + this.calledServiceName;
		final long tin = (long) (Constants.SIM_TIME_TO_MON_TIME * this.timeEnter);
		final long tout = (long) (Constants.SIM_TIME_TO_MON_TIME * timeExit);
		final long traceId = Long.parseLong(this.traceId);
		final String hostname = this.serverId;
		final int eoi = this.eoi;
		final int ess = depth;

		final OperationExecutionRecord rec =
				new OperationExecutionRecord(operationSignature, OperationExecutionRecord.NO_SESSION_ID, traceId, tin, tout, hostname, eoi, ess);
		return rec;
	}

	public final String getTraceId() {
		return this.traceId;
	}

	public final int getEoi() {
		return this.eoi;
	}

	public final void setEoi(final int eoi) {
		this.eoi = eoi;
	}

}
