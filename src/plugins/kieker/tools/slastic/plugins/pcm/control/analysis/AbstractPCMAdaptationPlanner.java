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

package kieker.tools.slastic.plugins.pcm.control.analysis;

import kieker.tools.slastic.control.components.analysis.AbstractAdaptationPlannerComponent;

import ReconfigurationPlanModel.ComponentDeReplicationOP;
import ReconfigurationPlanModel.ComponentMigrationOP;
import ReconfigurationPlanModel.ComponentReplicationOP;
import ReconfigurationPlanModel.NodeAllocationOP;
import ReconfigurationPlanModel.NodeDeAllocationOP;
import ReconfigurationPlanModel.ReconfigurationPlanModelFactory;
import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;

/**
 * 
 * @author Andre van Hoorn
 */
public abstract class AbstractPCMAdaptationPlanner extends AbstractAdaptationPlannerComponent {
	private final static ReconfigurationPlanModelFactory reconfModelFact = ReconfigurationPlanModelFactory.eINSTANCE;

	protected final ComponentMigrationOP createComponentMigrationOP(final AllocationContext allCtx, final ResourceContainer resCont) {
		final ComponentMigrationOP componentMigration = reconfModelFact.createComponentMigrationOP();
		componentMigration.setComponent(allCtx);
		componentMigration.setDestination(resCont);
		return componentMigration;
	}

	protected final ComponentReplicationOP createComponentReplicationOP(final AssemblyContext asmCtx, final ResourceContainer resCont) {
		final ComponentReplicationOP componentReplication = reconfModelFact.createComponentReplicationOP();
		componentReplication.setComponent(asmCtx);
		componentReplication.setDestination(resCont);
		return componentReplication;
	}

	protected final ComponentDeReplicationOP createComponentDeReplicationOP(final AllocationContext allCtx) {
		final ComponentDeReplicationOP deReplication = reconfModelFact.createComponentDeReplicationOP();
		deReplication.setComponent(allCtx);
		deReplication.setClone(allCtx);
		return deReplication;
	}

	protected final NodeAllocationOP createNodeAllocationOP(final ResourceContainer resCont) {
		final NodeAllocationOP nodeAllocation = reconfModelFact.createNodeAllocationOP();
		nodeAllocation.setNode(resCont);
		return nodeAllocation;
	}

	protected final NodeDeAllocationOP createNodeDeallocationOP(final ResourceContainer resCont) {
		final NodeDeAllocationOP deallocation = reconfModelFact.createNodeDeAllocationOP();
		deallocation.setNode(resCont);
		return deallocation;
	}
}
