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

package org.trustsoft.slastic.simulation.events.reconfiguration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.model.ModelManager;

import ReconfigurationPlanModel.ComponentReplicationOP;
import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import desmoj.core.simulator.Model;

/**
 * 
 * @author Robert von Massow
 * 
 */
public final class ReplicationEvent extends AbstractReconfigurationEvent {

	private static final Log LOG = LogFactory.getLog(ReplicationEvent.class);

	public ReplicationEvent(final Model owner, final String name, final boolean showInTrace, final SLAsticReconfigurationOpType reconfOp) {
		super(owner, name, showInTrace, reconfOp);
	}

	@Override
	public boolean concreteEventRoutine() {
		final ComponentReplicationOP repOp = (ComponentReplicationOP) super.getReconfOp();
		final AssemblyContext ac = repOp.getComponent();
		final ResourceContainer rc = repOp.getDestination();
		if (ModelManager.getInstance().getHardwareController().isAllocated(rc.getId())
				&& !ModelManager.getInstance().getAllocationController().hasAllocation(rc.getId(), ac.getId())) {
			ModelManager.getInstance().getAllocationController().add(rc.getId(), ac.getId());
			return true;
		} else {
			LOG.warn("Replication failed, target machine allocated: " + ModelManager.getInstance().getHardwareController().isAllocated(rc.getId())
					+ ", component already instantiated: " + ModelManager.getInstance().getAllocationController().hasAllocation(rc.getId(), ac.getId()));
			return false;
		}

	}

}
