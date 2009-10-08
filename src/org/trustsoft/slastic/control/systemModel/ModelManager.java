package org.trustsoft.slastic.control.systemModel;

import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;

import com.sun.org.apache.bcel.internal.generic.ALOAD;

import reconfMM.ReconfMMPackage;
import reconfMM.ReconfigurationModel;
import reconfMM.Service;
import ReconfigurationPlanModel.ComponentDeReplicationOP;
import ReconfigurationPlanModel.ComponentMigrationOP;
import ReconfigurationPlanModel.ComponentReplicationOP;
import ReconfigurationPlanModel.NodeAllocationOP;
import ReconfigurationPlanModel.NodeDeAllocationOP;
import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import ReconfigurationPlanModel.impl.ComponentDeReplicationOPImpl;
import ReconfigurationPlanModel.impl.ComponentMigrationOPImpl;
import ReconfigurationPlanModel.impl.ComponentReplicationOPImpl;
import ReconfigurationPlanModel.impl.NodeAllocationOPImpl;
import ReconfigurationPlanModel.impl.NodeDeAllocationOPImpl;
import ReconfigurationPlanModel.impl.SLAsticReconfigurationOpTypeImpl;
import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.allocation.AllocationFactory;
import de.uka.ipd.sdq.pcm.allocation.impl.AllocationFactoryImpl;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.core.composition.CompositionFactory;
import de.uka.ipd.sdq.pcm.core.composition.impl.CompositionFactoryImpl;
import de.uka.ipd.sdq.pcm.repository.BasicComponent;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import de.uka.ipd.sdq.pcm.system.System;

/**
 * The only ModelManager-Implementation that currently exists.
 * 
 * @author Lena Stöver
 * 
 */
public class ModelManager extends AbstractModelManager {

	// TODO Exceptions einführen!
	private final Log log = LogFactory.getLog(ModelManager.class);
	private static ModelManager instance;
	private ConcurrentHashMap<BasicComponent, Vector<AllocationContext>> componentAllocationList;
	private ConcurrentLinkedQueue<ResourceContainer> allocatedServers;
	private ConcurrentLinkedQueue<ResourceContainer> notAllocatedServers;

	private ModelManager() {

	}

	/**
	 * This method has to be called before the instance of this ModelManager can
	 * be used. Otherwise the Reconfigurationmodel is not initialized.
	 * 
	 * @param m
	 *            ReconfigurationModel which represents the initialization-model
	 */
	public void initModel(ReconfigurationModel m) {
		model = m;
		this.initSet();
		this.initComponentAllocationList();
		this.initAllocatedServers();
	}

	private void initAllocatedServers() {
		this.allocatedServers = new ConcurrentLinkedQueue<ResourceContainer>();
		this.notAllocatedServers = new ConcurrentLinkedQueue<ResourceContainer>();

		// run through all allocationContexts and add the already allocated
		// servers
		for (int i = 0; i < model.getAllocation().getTargetResourceEnvironment_Allocation().getResourceContainer_ResourceEnvironment().size(); i++) {
			if (!this.allocatedServers.contains(model.getAllocation().getTargetResourceEnvironment_Allocation().getResourceContainer_ResourceEnvironment().get(i)))
				this.allocatedServers.add(model.getAllocation().getTargetResourceEnvironment_Allocation().getResourceContainer_ResourceEnvironment().get(i));
		}

	}

	private void initComponentAllocationList() {
		this.componentAllocationList = new ConcurrentHashMap<BasicComponent, Vector<AllocationContext>>();
		for (int i = 0; i < model.getComponents().size(); i++) {
			this.componentAllocationList.put(model.getComponents().get(i)
					.getComponent(), new Vector<AllocationContext>());
			for (int k = 0; k < model.getAllocation()
					.getAllocationContexts_Allocation().size(); k++) {
				if (model.getComponents().get(i).getComponent() == model
						.getAllocation().getAllocationContexts_Allocation()
						.get(k).getAssemblyContext_AllocationContext()
						.getEncapsulatedComponent_ChildComponentContext()) {
					this.componentAllocationList.get(
							model.getComponents().get(i).getComponent()).add(
							model.getAllocation()
									.getAllocationContexts_Allocation().get(k));

				}
			}
		}

	}

	private synchronized void initSet() {
		for (int i = 0; i < model.getComponents().size(); i++) {
			for (int k = 0; k < model.getComponents().get(i).getServices()
					.size(); k++) {
				// It is important not to use a Set of Longs, because of the
				// possibility of equal values of response times
				ConcurrentSkipListSet<SLOMonitoringRecord> list = new ConcurrentSkipListSet<SLOMonitoringRecord>();
				model.getComponents().get(i).getServices().get(k)
						.setResponseTimes(list);
			}
		}

	}

	/**
	 * Singleton implementation. Don't forget to call the initModel()-method
	 * before using the returned Instance
	 * 
	 * @return returns the only instance of the ModelManager
	 */
	public synchronized static ModelManager getInstance() {
		if (instance == null) {
			instance = new ModelManager();
		}
		return instance;

	}

	@Override
	public void update(AbstractKiekerMonitoringRecord newRecord,
			AbstractKiekerMonitoringRecord oldRecord) {
		SLOMonitoringRecord newSLOrecord = (SLOMonitoringRecord) newRecord;
		SLOMonitoringRecord oldSLOrecord = (SLOMonitoringRecord) oldRecord;
		int serviceID = newSLOrecord.serviceId;
		boolean updated = false;
		synchronized (this.model) {
			for (int i = 0; i < model.getComponents().size(); i++) {
				for (int k = 0; k < model.getComponents().get(i).getServices()
						.size(); k++) {
					Service service = model.getComponents().get(i)
							.getServices().get(k);
					if (service.getServiceID() == serviceID) {
						synchronized ((ConcurrentSkipListSet<SLOMonitoringRecord>) service
								.getResponseTimes()) {
							((ConcurrentSkipListSet<SLOMonitoringRecord>) service
									.getResponseTimes()).add(newSLOrecord);
						}

						updated = true;
						if (oldSLOrecord != null) {
							if (service.getServiceID() == oldSLOrecord.serviceId) {
								synchronized ((ConcurrentSkipListSet<SLOMonitoringRecord>) service
										.getResponseTimes()) {
									((ConcurrentSkipListSet<SLOMonitoringRecord>) service
											.getResponseTimes())
											.remove(oldSLOrecord);

								}
								oldSLOrecord = null;
							}
						}
					}

				}
			}
		}
		updated = false;
	}

	/**
	 * runs through the model and returns the set of responsetimes that belongs
	 * to the given service
	 * 
	 * @param serviceID
	 *            identifies the service
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ConcurrentSkipListSet<SLOMonitoringRecord> getResponseTimes(
			int serviceID) {
		// log.info("getResponseTimes wurde aufgerufen");
		for (int i = 0; i < model.getComponents().size(); i++) {
			for (int k = 0; k < model.getComponents().get(i).getServices()
					.size(); k++) {
				if (model.getComponents().get(i).getServices().get(k)
						.getServiceID() == serviceID) {
					return ((ConcurrentSkipListSet<SLOMonitoringRecord>) model
							.getComponents().get(i).getServices().get(k)
							.getResponseTimes());
				}
			}
		}
		log.error("No entry for ServiceID:" + serviceID + " found");
		return null;
	}

	@Override
	protected void migrate(AllocationContext component,
			ResourceContainer newServer) {
		if (this.model.getAllocation().getAllocationContexts_Allocation()
				.contains(component)) {
			if (this.allocatedServers.contains(newServer)) {
				AllocationFactory fac = AllocationFactoryImpl.init();
				AllocationContext newContext = fac.createAllocationContext();
				newContext.setAssemblyContext_AllocationContext(component
						.getAssemblyContext_AllocationContext());
				newContext.setResourceContainer_AllocationContext(newServer);
				newContext.setEntityName(component.getEntityName());
				newContext.setId(component.getId());
				this.add(component);
				this.remove(component);
				log.info("Migrate-Operation successfull");
			} else {
				log.error("Server: " + newServer.getEntityName()
						+ "not yet allocated, component can not be migrated");
			}
		} else {
			log.error("Component of type: "
					+ component.getAssemblyContext_AllocationContext()
							.getEntityName()
					+ "is not allocated to server: "
					+ component.getResourceContainer_AllocationContext()
							.getEntityName());
		}

	}

	@Override
	protected void replicate(AssemblyContext component,
			ResourceContainer destination) {
		// TODO Testen ob der AssemblyContext auch existiert?
		if (this.allocatedServers.contains(destination)) {
			// Create new AllocationContext-Object
			AllocationFactory fac = AllocationFactoryImpl.init();
			AllocationContext newAllocationContext = fac
					.createAllocationContext();

			// Create new AssemblyContext-Object
			CompositionFactory compFac = CompositionFactoryImpl.init();
			AssemblyContext newAssemblyContext = compFac
					.createAssemblyContext();

			// Make the AssemblyContext to a replication of the given component,
			// with adding the same BasicComponent
			newAssemblyContext
					.setEncapsulatedComponent_ChildComponentContext(component
							.getEncapsulatedComponent_ChildComponentContext());

			// complete the creation of the AllocationContext
			newAllocationContext
					.setAssemblyContext_AllocationContext(newAssemblyContext);
			newAllocationContext
					.setResourceContainer_AllocationContext(destination);

			// add new AllocationContext-Object to the AllocationModel
			this.model.getAllocation().getAllocationContexts_Allocation().add(
					newAllocationContext);

			// update HashMap
			this.componentAllocationList.get(
					component.getEncapsulatedComponent_ChildComponentContext())
					.add(newAllocationContext);
			
			log.info("Replicate-Operation successfull");
		} else {
			log.error("Component of type: "
					+ component
							.getEncapsulatedComponent_ChildComponentContext()
							.getEntityName()
					+ " can not be replicated, Server "
					+ destination.getEntityName() + "is not allocated");
		}

	}

	@Override
	protected void allocate(ResourceContainer container) {
		if (this.notAllocatedServers.contains(container)) {
			this.allocatedServers.add(container);
			this.notAllocatedServers.remove(container);
			log.info("Allocate-Operation successfull");
		} else {
			log.error("Server: " + container.getEntityName()
					+ " not available for allocation");
		}

	}

	@Override
	protected void deallocate(ResourceContainer container) {
		if (this.allocatedServers.contains(container)) {
			this.notAllocatedServers.add(container);
			this.allocatedServers.remove(container);
			log.info("Deallocate-Operation successfull");
		} else {
			log.error("Server: " + container.getEntityName()
					+ "cannot be deallocated, because it is not allocated");
		}
	}

	@Override
	protected void dereplicate(AllocationContext component) {
		if (model.getAllocation().getAllocationContexts_Allocation().contains(
				component)) {
			this.remove(component);
			log.info("Dereplicate-Operation successfull");
		} else {
			log.error("Allocation for Component: "
					+ component.getAssemblyContext_AllocationContext()
							.getEncapsulatedComponent_ChildComponentContext()
					+ " to Server: "
					+ component.getResourceContainer_AllocationContext()
							.getEntityName() + "does not exist");
		}
	}

	/**
	 * Run through all operations of the given reconfiguration-plan and execute
	 * them successively
	 * 
	 * @param plan
	 *            Reconfiguration-Plan object which contains a list of
	 *            operations, available operation-types have to be synchronized
	 *            with the ReconfigurationPlanMetaModel
	 * @return returns false if any operation-type is not available
	 */
	@SuppressWarnings("unchecked")
	public boolean doReconfiguration(
			ReconfigurationPlanModel.SLAsticReconfigurationPlan plan, boolean savePersistent) {
		EList<SLAsticReconfigurationOpType> operations = plan.getOperations();
		synchronized (this.model) {
			for (int i = 0; i < operations.size(); i++) {
				SLAsticReconfigurationOpType op = operations.get(i);
				String opname = op.toString();
				//Class opClass = (op.eClass()).class;
				if (op instanceof ComponentDeReplicationOPImpl) {
					AllocationContext comp = ((ComponentDeReplicationOP) op)
							.getComponent();
					this.dereplicate(comp);
				} else if (op instanceof ComponentMigrationOPImpl) {
					AllocationContext comp = ((ComponentMigrationOP) op)
							.getComponent();
					log.info("Migration of Component:"+comp.getEntityName());
					ResourceContainer destination = ((ComponentMigrationOP) op)
							.getDestination();
					log.info("Migration to ResourceContainer: "+destination.getEntityName());
					this.migrate(comp, destination);
				} else if (op instanceof ComponentReplicationOPImpl) {
					AssemblyContext comp = ((ComponentReplicationOP) op)
							.getComponent();
					ResourceContainer destination = ((ComponentReplicationOP) op)
							.getDestination();
					this.replicate(comp, destination);
				} else if (op instanceof NodeAllocationOPImpl) {
					ResourceContainer container = ((NodeAllocationOP) op)
							.getNode();
					this.allocate(container);
				} else if (op instanceof NodeDeAllocationOPImpl) {
					ResourceContainer container = ((NodeDeAllocationOP) op)
							.getNode();
					this.deallocate(container);
				} else {
					log.error("Operationtype "
							+ plan.getClass().getCanonicalName()
							+ " not supported!");
					return false;
				}
				if(savePersistent){
					try {
						this.savePersistent();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return true;
	}
	
	//The following methods have to be deleted after the debugging-phase, because they can cause behavior we do not want
	public ReconfigurationModel getModel(){
		return model;
	}
	
	public ConcurrentLinkedQueue<ResourceContainer> getAllocatedServers(){
		return this.allocatedServers;
	}
	
	private boolean savePersistent() throws IOException{
		synchronized(model){
		//Save ResourceEnvironment
		ResourceSet resourceEnvironmentResourceSet = new ResourceSetImpl();
		String resourceEnvironmentResourceLocation = "out.resourceenvironment";
		URI resourceEnvironmentURI = URI.createURI(resourceEnvironmentResourceLocation);
		Resource resourceEnvironmentResource = resourceEnvironmentResourceSet.createResource(resourceEnvironmentURI);
		resourceEnvironmentResource.getContents().add(model.getAllocation().getTargetResourceEnvironment_Allocation());
		for(int i = 0; i < model.getAllocation().getTargetResourceEnvironment_Allocation().getResourceContainer_ResourceEnvironment().size();i++){
			resourceEnvironmentResource.getContents().add(model.getAllocation().getTargetResourceEnvironment_Allocation().getResourceContainer_ResourceEnvironment().get(i));
		}
		
		//Save System
		Resource repositoryResource = null;
		if(model.getComponents().size() > 0){
			ResourceSet repositoryResourceSet = new ResourceSetImpl();
			String repositoryLocation = "out.repository";
			URI repositoryURI = URI.createURI(repositoryLocation);
			 repositoryResource = repositoryResourceSet.createResource(repositoryURI);
			Repository repository = model.getComponents().get(0).getComponent().getRepository_ProvidesComponentType();
			repositoryResource.getContents().add(repository);
			
		}
		
		log.info("repository saved");
		
		//Save System
		ResourceSet systemResourceSet = new ResourceSetImpl();
		String systemLocation = "out.system";
		URI systemURI = URI.createURI(systemLocation);
		Resource systemResource = systemResourceSet.createResource(systemURI);
		System systemAllocation = model.getAllocation().getSystem_Allocation();
		EList<EObject> contents = systemResource.getContents();
		contents.add(systemAllocation);


		log.info("System saved");
		//Save Allocation and AllocationContexts
		ResourceSet allocationResourceSet = new ResourceSetImpl();
		String allocationLocation="out.allocation";
		URI allocationURI = URI.createURI(allocationLocation);
		Resource allocationResource = allocationResourceSet.createResource(allocationURI);
		allocationResource.getContents().add(model.getAllocation());
		//allocationResource.getContents().add(model.getAllocation().getTargetResourceEnvironment_Allocation());
		for(int i = 0; i < model.getAllocation().getAllocationContexts_Allocation().size(); i++){
			allocationResource.getContents().add(model.getAllocation().getAllocationContexts_Allocation().get(i));
		}
		
		
		
		//Save ReconfigurationModel
		ResourceSet reconfigurationResourceSEt = new ResourceSetImpl();
		String reconfigurationLocation = "out.reconfMM";
		URI reconfigurationURI = URI.createURI(reconfigurationLocation);
		Resource reconfigurationResource = reconfigurationResourceSEt.createResource(reconfigurationURI);
		reconfigurationResource.getContents().add(model);

		
		resourceEnvironmentResource.save(null);
		repositoryResource.save(null);
		systemResource.save(null);
		allocationResource.save(null);
		reconfigurationResource.save(null);
		log.info("ReconfigurationModel saved");
		}
		
		return true;
	}
}
