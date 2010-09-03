package org.trustsoft.slastic.plugins.pcm.control.analysis;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.pcm.control.modelManager.ModelManager;


import ReconfigurationPlanModel.ComponentDeReplicationOP;
import ReconfigurationPlanModel.ComponentMigrationOP;
import ReconfigurationPlanModel.ComponentReplicationOP;
import ReconfigurationPlanModel.NodeAllocationOP;
import ReconfigurationPlanModel.NodeDeAllocationOP;
import ReconfigurationPlanModel.ReconfigurationPlanModelFactory;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import org.trustsoft.slastic.control.components.analysis.AbstractAdaptationPlannerComponent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.plugins.slasticImpl.control.modelManager.PCMModelManager;
import org.trustsoft.slastic.reconfiguration.ReconfigurationException;

/**
 * The only Implementation of an Adaptation Analyzer that currently exists. It contains example ReconfigurationOperations.
 * @author Lena Stoever
 *
 */
public class AdaptationPlannerBookstoreSamplePlan extends AbstractAdaptationPlannerComponent {

    private static final Log log = LogFactory.getLog(AdaptationPlannerBookstoreSamplePlan.class);

    private final static ReconfigurationPlanModelFactory reconfModelFact =
                ReconfigurationPlanModelFactory.eINSTANCE;

    private volatile PCMModelManager pcmModelMgr;

    private ComponentMigrationOP createComponentMigrationOP (AllocationContext allCtx, ResourceContainer resCont){
        ComponentMigrationOP componentMigration = reconfModelFact.createComponentMigrationOP();
        componentMigration.setComponent(allCtx);
        componentMigration.setDestination(resCont);
        return componentMigration;
    }

    private ComponentReplicationOP createComponentReplicationOP (AssemblyContext asmCtx, ResourceContainer resCont){
        ComponentReplicationOP componentReplication = reconfModelFact.createComponentReplicationOP();
        componentReplication.setComponent(asmCtx);
        componentReplication.setDestination(resCont);
        return componentReplication;
    }

    private ComponentDeReplicationOP createDeReplicationOP (AllocationContext allCtx){
        ComponentDeReplicationOP deReplication = reconfModelFact.createComponentDeReplicationOP();
        deReplication.setComponent(allCtx);
        deReplication.setClone(allCtx);
        return deReplication;
    }

    private NodeAllocationOP createNodeAllocationOP (ResourceContainer resCont){
        NodeAllocationOP nodeAllocation = reconfModelFact.createNodeAllocationOP();
        nodeAllocation.setNode(resCont);
        return nodeAllocation;
    }

    private NodeDeAllocationOP createDeallocationOP (ResourceContainer resCont){
        NodeDeAllocationOP deallocation = reconfModelFact.createNodeDeAllocationOP();
        deallocation.setNode(resCont);
        return deallocation;
    }

    private SLAsticReconfigurationPlan createSamplePlan (){

        SLAsticReconfigurationPlan testPlan = ReconfigurationPlanModelFactory.eINSTANCE.createSLAsticReconfigurationPlan();
        AllocationContext catalogAllCtx = pcmModelMgr.getAllocationContextsByAssemblyContextName().get("Assembly_Catalog <Catalog>").iterator().next();
        AssemblyContext catalogAsmCtx = catalogAllCtx.getAssemblyContext_AllocationContext();

        ResourceContainer initiallyUnusedserver = pcmModelMgr.getUnusedResourceContainersByresourceContainerName().elements().nextElement();
        ResourceContainer initialCatalogServer = catalogAllCtx.getResourceContainer_AllocationContext();

        // Allocation of a new Server
        NodeAllocationOP nodeAllocation0 = this.createNodeAllocationOP(initiallyUnusedserver);
        testPlan.getOperations().add(nodeAllocation0);

        // Migration of catalog assembly to unused server
        ComponentMigrationOP componentMigration = createComponentMigrationOP(catalogAllCtx, initiallyUnusedserver);
        testPlan.getOperations().add(componentMigration);

        // Replication of catalog back to initial server
        ComponentReplicationOP componentReplication = this.createComponentReplicationOP(catalogAsmCtx, initialCatalogServer);
        testPlan.getOperations().add(componentReplication);
        /* Create auxiliary assembly ctx which will be used for the dereplication */
        AllocationContext newCatalogAsmCtx = de.uka.ipd.sdq.pcm.allocation.AllocationFactory.eINSTANCE.createAllocationContext();
        newCatalogAsmCtx.setAssemblyContext_AllocationContext(catalogAsmCtx);
        newCatalogAsmCtx.setResourceContainer_AllocationContext(initialCatalogServer);
                
        // DeReplication of catalog from "unused" server
        ComponentDeReplicationOP componentDeReplication = this.createDeReplicationOP(newCatalogAsmCtx);
        testPlan.getOperations().add(componentDeReplication);

        // Deallocation of unused Server
        NodeDeAllocationOP nodeDeAllocationOP = this.createDeallocationOP(initiallyUnusedserver);
        testPlan.getOperations().add(nodeDeAllocationOP);

        return testPlan;
    }

    @Override
    public boolean execute() {
            this.pcmModelMgr = (PCMModelManager) this.getParentAnalysisComponent().getParentControlComponent().getModelManager();
            
            //this.pcmModelMgr.doReconfiguration(this.createSamplePlan());
            return true;
    }

    @Override
    public void terminate(final boolean error) {
    }

    @Override
    public void handleEvent(IEvent ev) { }

    @Override
    public boolean init() {
        return true;
    }
}
