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

package kieker.tools.slastic.plugins.starter.reconfigurationPipe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.tools.slastic.simulation.listeners.IReconfigurationEventListener;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import kieker.tools.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan;

/**
 * TODO: Either we should change {@link SLAsticReconfigurationPlan}, used e.g., to {@link ReconfigurationPlan} or move this class to a PCM-specific package.
 * 
 * @author Andre van Hoorn
 */
public class ReconfigurationPipe {

	private static final Log LOG = LogFactory.getLog(ReconfigurationPipe.class);

	private final String name;
	private volatile IReconfigurationPipePlanReceiver planReceiver;
	private volatile boolean closed;

	/** No construction employing default constructor */
	@SuppressWarnings("unused")
	private ReconfigurationPipe() {
		this.name = null;
	}

	public ReconfigurationPipe(final String name) {
		this.name = name;
	}

	public void setPlanReceiver(final IReconfigurationPipePlanReceiver planReceiver) {
		this.planReceiver = planReceiver;
		ReconfigurationPipe.LOG.info("PipeReader initialized");
	}

	public void reconfigure(final SLAsticReconfigurationPlan plan, final IReconfigurationEventListener listener) throws ReconfigurationPipeException {
		if (this.closed) {
			ReconfigurationPipe.LOG.error("trying to write to closed pipe");
			throw new ReconfigurationPipeException("trying to write to closed pipe");
		}
		this.planReceiver.reconfigure(plan, listener);
	}

	public String getName() {
		return this.name;
	}

	public void close() {
		this.closed = true;
	}
}
