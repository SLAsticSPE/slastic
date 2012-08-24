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

package org.trustsoft.slastic.plugins.starter.reconfigurationPipe;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.listeners.IReconfigurationEventListener;
import org.trustsoft.slastic.simulation.model.interfaces.IReconfigurationPlanReceiver;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan;

/**
 * TODO: There should be an AbstractReconfPlanReceiver including the
 * listener management. But in this case, we should probably use {@link ReconfigurationPlan}.
 * 
 * TODO: Move this class to a PCM-specific package (or slasticSIM-specific).
 * 
 * @author Andre van Hoorn
 */
public class SLAsticSimPlanReceiver implements IReconfigurationPipePlanReceiver {
	private static final Log LOG = LogFactory.getLog(SLAsticSimPlanReceiver.class);

	private final IReconfigurationPlanReceiver delegate;

	private volatile ReconfigurationPipe pipe;
	private final String pipeName;

	private final Set<IReconfigurationEventListener> registeredListeners = new HashSet<IReconfigurationEventListener>();

	public SLAsticSimPlanReceiver(final String pipeName, final IReconfigurationPlanReceiver delegate) {
		this.pipeName = pipeName;
		this.delegate = delegate;
	}

	public void execute() {
		this.connect();
	}

	private void connect() throws IllegalArgumentException {
		this.pipe = ReconfigurationPipeBroker.getInstance().acquirePipe(this.pipeName);
		if (this.pipe == null) {
			LOG.error("Failed to get Pipe with name " + this.pipeName);
			throw new IllegalArgumentException("Failed to get Pipe with name " + this.pipeName);
		}
		this.pipe.setPlanReceiver(this);
	}

	@Override
	public void reconfigure(final SLAsticReconfigurationPlan plan, final IReconfigurationEventListener listener) {
		LOG.debug("Delegating reconfiguration plan" + plan);
		/* Register only once */
		if (!this.registeredListeners.contains(listener)) {
			this.registeredListeners.add(listener);
			this.delegate.addReconfigurationEventListener(listener);
		}
		this.delegate.reconfigure(plan);
	}
}
