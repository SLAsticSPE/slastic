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

import kieker.monitoring.core.controller.IMonitoringController;
import kieker.monitoring.core.controller.MonitoringController;
import kieker.tools.slastic.simulation.model.ModelManager;
import kieker.tools.slastic.simulation.software.controller.CallHandler;
import kieker.tools.slastic.simulation.software.controller.StackFrame;
import kieker.tools.slastic.simulation.software.statistics.ISystemStats;

/**
 * 
 * @author Robert von Massow
 * 
 */
@SuppressWarnings("unused")
public class ExternalCallReturnEvent extends AbstractControlFlowEvent {
	private static final Log LOG = LogFactory.getLog(ExternalCallReturnEvent.class);

	@Inject
	@Named("SystemUsersOnReturn")
	private static ISystemStats stats;

	@Inject
	@Named("Execution")
	private static ISystemStats exeStats;

	private static final IMonitoringController CTRL_INSTANCE = MonitoringController.getInstance();

	private final ExternalCallEnterEvent ece;

	// private SimTime exitTime;

	public ExternalCallReturnEvent(final ExternalCallEnterEvent ece) {
		super("Return from " + ece.getName(), ece.getTraceId());
		this.ece = ece;
	}

	@Override
	public final void eventRoutine() {
		// this.exitTime = this.getModel().currentTime();
		// spawn record!
		final StackFrame f = CallHandler.getInstance().popContext(this.ece.getTraceId());
		if (this.ece.getASMContFrom() == null) {
			stats.subSystemUser();
		}
		exeStats.logExecution(f, CallHandler.getInstance().getStackDepth(this.ece.getTraceId()));

		// tell simulator to schedule next action in this trace
		ModelManager.getInstance().getAllocationController().remUser(this.ece.getASMContTo(), this.ece.getServerIdTo());
		CallHandler.getInstance().actionReturn(this.ece.getTraceId());
	}
}
