package org.trustsoft.slastic.control.analysis;

import org.trustsoft.slastic.control.ReconfigurationPlanForwarder;
import org.trustsoft.slastic.control.systemModel.ModelManager;

import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.repository.RepositoryFactory;
import de.uka.ipd.sdq.pcm.repository.impl.RepositoryFactoryImpl;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceenvironmentFactory;
import de.uka.ipd.sdq.pcm.resourceenvironment.impl.ResourceenvironmentFactoryImpl;

import ReconfigurationPlanModel.*;
import ReconfigurationPlanModel.impl.ReconfigurationPlanModelFactoryImpl;

public class AdaptationAnalyzer implements IAdaptationAnalyzer {
	
	SLAsticReconfigurationPlan plan;

	@Override
	public SLAsticReconfigurationPlan getReconfigurationPlan() {
		return plan;
	}
	
	public void analyze(){
		/**
		ReconfigurationPlanModelFactory fac = ReconfigurationPlanModelFactoryImpl.init();
		this.plan = fac.createSLAsticReconfigurationPlan();
		**/
		//This is just Test-Code and has to be deleted when the simulator works
		
		
		SLAsticReconfigurationPlan testPlan;
		ReconfigurationPlanModelFactory fac = ReconfigurationPlanModelFactoryImpl.init();
		testPlan = fac.createSLAsticReconfigurationPlan();
		AllocationContext bookstore = ModelManager.getInstance().getModel().getAllocation().getAllocationContexts_Allocation().get(0);
		ResourceContainer server1 = (ResourceContainer) ModelManager.getInstance().getAllocatedServers().toArray()[0];
		
		//Migration of Bookstore-Allocation-Assembly to ResourceContainer Server1
		ComponentMigrationOP componentMigration = fac.createComponentMigrationOP();
		componentMigration.setComponent(bookstore);
		componentMigration.setDestination(server1);
		//testPlan.getOperations().add(componentMigration);
		
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
		ModelManager.getInstance().addNotAllocatedServer(newServer);
		nodeAllocation.setNode(newServer);
		//testPlan.getOperations().add(nodeAllocation);
		
		//Server DeAllocation of Server 1
		NodeDeAllocationOP nodeDeAllocation = fac.createNodeDeAllocationOP();
		nodeDeAllocation.setNode(server1);
		//testPlan.getOperations().add(nodeDeAllocation);
		
		
		this.plan = testPlan;
		ReconfigurationPlanForwarder.getInstance().addPlan(plan);
	}

}
