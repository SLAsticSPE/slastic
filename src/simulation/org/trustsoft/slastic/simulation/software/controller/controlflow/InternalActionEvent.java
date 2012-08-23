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

import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.model.hardware.controller.cpu.CPUSchedulableProcess;
import org.trustsoft.slastic.simulation.model.hardware.controller.engine.AbstractSchedulableProcess;
import org.trustsoft.slastic.simulation.model.hardware.controller.engine.Server;
import org.trustsoft.slastic.simulation.software.controller.CallHandler;

import desmoj.core.simulator.SimTime;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class InternalActionEvent extends AbstractControlFlowEvent {
	private static final Log LOG = LogFactory.getLog(InternalActionEvent.class);

	private final Hashtable<String, Demand<Integer>> demands = new Hashtable<String, Demand<Integer>>();
	private final String traceId;

	public InternalActionEvent(final String name, final String traceId) {
		super(name, traceId);
		this.traceId = traceId;
	}

	@Override
	public final void eventRoutine() {
		final String server = CallHandler.getInstance().getCurrentServer(this.traceId);
		final Server s = ModelManager.getInstance().getHardwareController().getServer(server);
		// create schedulable processes and schedule to the server
		final Demand<Integer> cpuDemand = this.demands.get("pathmap://PCM_MODELS/Palladio.resourcetype#_oro4gG3fEdy4YaaT-RYrLQ");
		if (cpuDemand != null) {
			final CPUSchedulableProcess p = new CPUSchedulableProcess(this.getModel(), Constants.DEBUG, cpuDemand.getDemand(), this);
			// InternalActionNode.log.info("Scheduled CPU process " + p
			// + " for trace " + this.traceId);
			s.addCPUTask(p);
		}
	}

	public final void add(final String requiredResource, final String demand) {
		final Demand<Integer> d = new Demand<Integer>(demand, Integer.class);
		this.demands.put(requiredResource, d);
	}

	public final void returned(final SimTime t, final AbstractSchedulableProcess p) {
		if (p instanceof CPUSchedulableProcess) {
			this.demands.remove("pathmap://PCM_MODELS/Palladio.resourcetype#_oro4gG3fEdy4YaaT-RYrLQ");
		}
		InternalActionEvent.LOG.info("Internal Action done, list has size " + this.demands.size() + " " + p.getName());
		if (this.demands.isEmpty()) {
			CallHandler.getInstance().actionReturn(this.traceId);
		}

	}

}
