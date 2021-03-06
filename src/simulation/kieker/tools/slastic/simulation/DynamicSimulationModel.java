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

package kieker.tools.slastic.simulation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.tools.slastic.simulation.config.Constants;
import kieker.tools.slastic.simulation.model.ModelManager;
import kieker.tools.slastic.simulation.software.controller.CallHandler;
import kieker.tools.slastic.simulation.software.controller.EntryCall;
import kieker.tools.slastic.simulation.software.controller.exceptions.BranchException;
import kieker.tools.slastic.simulation.software.controller.exceptions.NoSuchSeffException;
import kieker.tools.slastic.simulation.software.controller.exceptions.SumGreaterXException;
import kieker.tools.slastic.simulation.util.ExternalCallQueue;

import reconfMM.ReconfigurationModel;
import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.system.System;
import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.Model;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class DynamicSimulationModel extends Model {
	private static final Log LOG = LogFactory.getLog(DynamicSimulationModel.class);

	@SuppressWarnings("unused")
	private final ModelManager manager; // used to create (and hold) the singleton instance

	private final CallHandler callHandler;
	private final ExternalCallQueue buffer;

	// TODO: It seems that these variables aren't used at all
	// private volatile boolean terminating;
	// private volatile boolean written;

	public DynamicSimulationModel(final String name, final Repository repos,
			final System struct, final ResourceEnvironment resourceEnv,
			final Allocation initAllocation,
			final ReconfigurationModel reconfModel,
			final ExternalCallQueue simulatedThreadQueue,
			final Experiment experiment) {
		super(null, name, Constants.DEBUG, Constants.DEBUG);
		this.connectToExperiment(experiment);
		this.manager = new ModelManager(repos, struct, resourceEnv, initAllocation, reconfModel, this);
		this.buffer = simulatedThreadQueue;
		this.callHandler = new CallHandler(this);
	}

	@Override
	public String description() {
		return "This is SLAstic.SIM, the dynamic software architecture simulator.\nWe currently simulate " + super.getName();
	}

	@Override
	public void doInitialSchedules() {
		synchronized (this.buffer) {
			for (final EntryCall call : this.buffer) {
				try {
					LOG.info("Calling " + call.getComponentName() + "." + call.getOpname());
					// TODO: Should we use more sophisticated service IDs than just the opname? I expect we might
					// run into trouble having multiple services/operations with the same name.
					// For example, we might want to include the component (type) name in the id
					this.callHandler.call(call.getOpname(), call.getTraceId() + "", call.getComponentName(), call.getTin());
				} catch (final NoSuchSeffException e) {
					e.printStackTrace();
				} catch (final BranchException e) {
					e.printStackTrace();
				} catch (final SumGreaterXException e) {
					e.printStackTrace();
				}
				if (Constants.SINGLE_TRACE) {
					break;
				}
			}
			this.buffer.clear();
			this.buffer.notify();
		}

	}

	@Override
	public void init() {

	}

	/*
	 * call returns algo<br/> let the next entry call be scheduled
	 */
	public void callReturns(final String traceId) {
		final EntryCall call;
		LOG.info("Attempting to fetch next call");
		call = this.buffer.removeFirstBlocking();
		if (call == null) {
			LOG.info("No call in queue");
			return;
		}
		LOG.warn("Calling " + call.getComponentName() + "." + call.getOpname());
		try {
			this.callHandler.call(call.getOpname(), call.getTraceId() + "", call.getComponentName(), call.getTin());
		} catch (final NoSuchSeffException e) {
			e.printStackTrace();
		} catch (final BranchException e) {
			e.printStackTrace();
		} catch (final SumGreaterXException e) {
			e.printStackTrace();
		}

	}

	// TODO: Not used at all
	// public void setTerminating(final boolean b) {
	// this.terminating = b;
	// }
}
