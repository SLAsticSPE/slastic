package org.trustsoft.slastic.control.analysis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.systemModel.ModelManager;
import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;
import org.trustsoft.slastic.reconfigurationManager.ReconfigurationException;

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
/**
 * The only Implementation of an Adaptation Analyzer that currently exists. It contains example ReconfigurationOperations.
 * @author Lena Stoever
 *
 */
public class AdaptationAnalyzer implements IAdaptationAnalyzer {
	
	//Reconfiguration plan that is produced by this class
	private SLAsticReconfigurationPlan plan;	
	//Reconfiguration Manager to which the produced plan is sent
	private IReconfigurationManager reconfigurationManager;
	//Analysis Component from where the event comes
	private IAnalysis ana;
	private static final Log log = LogFactory.getLog(IAdaptationAnalyzer.class);
	@Override
	public void execute() {
		/**
		ReconfigurationPlanModelFactory fac = ReconfigurationPlanModelFactoryImpl.init();
		this.plan = fac.createSLAsticReconfigurationPlan();
		**/
		log.info("Plan wird jetzt erstellt");
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
		ModelManager.getInstance().addNotAllocatedServer(newServer);
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
			this.reconfigurationManager.doReconfiguration(plan);
		} catch (ReconfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.reconfigurationManager.execute();

		
	}
	@Override
	public void handle(ISLAsticAnalysisEvent event) {
		//this.execute();
		
	}
	@Override
	public void setReconfigurationManager(IReconfigurationManager manager) {
		this.reconfigurationManager = manager;
		
	}
	@Override
	public void terminate() {
		this.reconfigurationManager.terminate();
	}
	@Override
	public void setAnalysis(IAnalysis ana) {
		this.ana=ana;
	}

}
