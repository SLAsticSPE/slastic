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

package org.trustsoft.slastic.plugins.pcm.control.analysis;

import ReconfigurationPlanModel.ComponentDeReplicationOP;
import ReconfigurationPlanModel.ComponentMigrationOP;
import ReconfigurationPlanModel.ComponentReplicationOP;
import ReconfigurationPlanModel.NodeAllocationOP;
import ReconfigurationPlanModel.NodeDeAllocationOP;
import ReconfigurationPlanModel.ReconfigurationPlanModelFactory;
import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import org.trustsoft.slastic.control.components.analysis.AbstractAdaptationPlannerComponent;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractPCMAdaptationPlanner extends AbstractAdaptationPlannerComponent {
    private final static ReconfigurationPlanModelFactory reconfModelFact =
                ReconfigurationPlanModelFactory.eINSTANCE;

    protected final ComponentMigrationOP createComponentMigrationOP (AllocationContext allCtx, ResourceContainer resCont){
        ComponentMigrationOP componentMigration = reconfModelFact.createComponentMigrationOP();
        componentMigration.setComponent(allCtx);
        componentMigration.setDestination(resCont);
        return componentMigration;
    }

    protected final ComponentReplicationOP createComponentReplicationOP (AssemblyContext asmCtx, ResourceContainer resCont){
        ComponentReplicationOP componentReplication = reconfModelFact.createComponentReplicationOP();
        componentReplication.setComponent(asmCtx);
        componentReplication.setDestination(resCont);
        return componentReplication;
    }

    protected final ComponentDeReplicationOP createComponentDeReplicationOP (AllocationContext allCtx){
        ComponentDeReplicationOP deReplication = reconfModelFact.createComponentDeReplicationOP();
        deReplication.setComponent(allCtx);
        deReplication.setClone(allCtx);
        return deReplication;
    }

    protected final NodeAllocationOP createNodeAllocationOP (ResourceContainer resCont){
        NodeAllocationOP nodeAllocation = reconfModelFact.createNodeAllocationOP();
        nodeAllocation.setNode(resCont);
        return nodeAllocation;
    }

    protected final NodeDeAllocationOP createNodeDeallocationOP (ResourceContainer resCont){
        NodeDeAllocationOP deallocation = reconfModelFact.createNodeDeAllocationOP();
        deallocation.setNode(resCont);
        return deallocation;
    }
}
