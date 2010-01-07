package org.trustsoft.slastic.control.plugins.daLena.analysis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.plugins.daLena.modelManager.ModelManager;
import org.trustsoft.slastic.reconfigurationManager.SLAsticReconfigurationException;

import ReconfigurationPlanModel.ComponentDeReplicationOP;
import ReconfigurationPlanModel.ComponentMigrationOP;
import ReconfigurationPlanModel.ComponentReplicationOP;
import ReconfigurationPlanModel.NodeAllocationOP;
import ReconfigurationPlanModel.NodeDeAllocationOP;
import ReconfigurationPlanModel.ReconfigurationPlanModelFactory;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import ReconfigurationPlanModel.impl.ReconfigurationPlanModelFactoryImpl;
import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceenvironmentFactory;
import de.uka.ipd.sdq.pcm.resourceenvironment.impl.ResourceenvironmentFactoryImpl;
import org.trustsoft.slastic.control.components.analysis.AbstractAdaptationPlanner;
import org.trustsoft.slastic.control.components.events.ISLAsticEvent;

/**
 * The only Implementation of an Adaptation Analyzer that currently exists. It contains example ReconfigurationOperations.
 * @author Lena Stoever
 *
 */
public class AdaptationPlannerBookstoreSamplePlan extends AbstractAdaptationPlanner {

    //Reconfiguration plan that is produced by this class
    private SLAsticReconfigurationPlan plan;
    private static final Log log = LogFactory.getLog(AdaptationPlannerBookstoreSamplePlan.class);

   public void init(String initString) throws IllegalArgumentException {
        super.initVarsFromInitString(initString);
        // we don't expect init properties so far, so just return.
    }

    @Override
    public boolean execute() {
        /**
        ReconfigurationPlanModelFactory fac = ReconfigurationPlanModelFactoryImpl.init();
        this.plan = fac.createSLAsticReconfigurationPlan();
         **/
        log.info("Plan wird jetzt erstellt");
        //This is just Test-Code and has to be deleted when the simulator works


        SLAsticReconfigurationPlan testPlan;
        ReconfigurationPlanModelFactory fac = ReconfigurationPlanModelFactoryImpl.init();
        testPlan = fac.createSLAsticReconfigurationPlan();
        ModelManager modelManager = (ModelManager) this.getParentAnalysisComponent().getParentControlComponent().getModelManager();
        AllocationContext bookstore = modelManager.getModel().getAllocation().getAllocationContexts_Allocation().get(0);
        ResourceContainer server1 = (ResourceContainer) modelManager.getAllocatedServers().toArray()[0];

        //Migration of Bookstore-Allocation-Assembly to ResourceContainer Server1
        ComponentMigrationOP componentMigration = fac.createComponentMigrationOP();
        componentMigration.setComponent(bookstore);
        componentMigration.setDestination(server1);
        testPlan.getOperations().add(componentMigration);

        //Replication of Component Bookstore
        ComponentReplicationOP componentReplication = fac.createComponentReplicationOP();
        componentReplication.setComponent(bookstore.getAssemblyContext_AllocationContext());
        componentReplication.setDestination(server1);
        testPlan.getOperations().add(componentReplication);

        //Server Allocation of a new Server
        NodeAllocationOP nodeAllocation = fac.createNodeAllocationOP();
        ResourceenvironmentFactory resourceFac = ResourceenvironmentFactoryImpl.init();
        ResourceContainer newServer = resourceFac.createResourceContainer();
        newServer.setEntityName("newServer");
        modelManager.addNotAllocatedServer(newServer);
        nodeAllocation.setNode(newServer);
        //testPlan.getOperations().add(nodeAllocation);

        //Server DeAllocation of Server 1
        NodeDeAllocationOP nodeDeAllocation = fac.createNodeDeAllocationOP();
        nodeDeAllocation.setNode(server1);
        //testPlan.getOperations().add(nodeDeAllocation);

        //DeReplication of Component Bookstore
        ComponentDeReplicationOP componentDeReplication = fac.createComponentDeReplicationOP();
        componentDeReplication.setClone(bookstore);
        //testPlan.getOperations().add(componentDeReplication);

        this.plan = testPlan;
        try {
            log.info("ReconfigurationManager ist gestartet und plan wird gesendet");
            this.getReconfigurationManager().doReconfiguration(plan);
        } catch (SLAsticReconfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//		this.reconfigurationManager.execute();

        return true;
    }

    @Override
    public void handle(ISLAsticEvent event) {
        //this.execute();
    }

    public void terminate() {
    }
}
