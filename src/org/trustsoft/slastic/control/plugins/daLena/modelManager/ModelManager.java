package org.trustsoft.slastic.control.plugins.daLena.modelManager;

import org.trustsoft.slastic.control.components.modelManager.IModelManager;
import org.trustsoft.slastic.control.systemModel.*;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.trustsoft.slastic.control.exceptions.AllocationContextNotInModelException;
import org.trustsoft.slastic.control.exceptions.IllegalReconfigurationOperationException;
import org.trustsoft.slastic.control.exceptions.ServerNotAllocatedException;
import org.trustsoft.slastic.control.exceptions.ServiceIDDoesNotExistException;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;

import reconfMM.ReconfigurationModel;
import reconfMM.ReconfigurationSpecification;
import reconfMM.Service;
import ReconfigurationPlanModel.ComponentDeReplicationOP;
import ReconfigurationPlanModel.ComponentMigrationOP;
import ReconfigurationPlanModel.ComponentReplicationOP;
import ReconfigurationPlanModel.NodeAllocationOP;
import ReconfigurationPlanModel.NodeDeAllocationOP;
import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import ReconfigurationPlanModel.impl.ComponentDeReplicationOPImpl;
import ReconfigurationPlanModel.impl.ComponentMigrationOPImpl;
import ReconfigurationPlanModel.impl.ComponentReplicationOPImpl;
import ReconfigurationPlanModel.impl.NodeAllocationOPImpl;
import ReconfigurationPlanModel.impl.NodeDeAllocationOPImpl;
import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.allocation.AllocationFactory;
import de.uka.ipd.sdq.pcm.allocation.impl.AllocationFactoryImpl;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.repository.BasicComponent;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import de.uka.ipd.sdq.pcm.system.System;

/**
 * The only ModelManager-Implementation that currently exists.
 * 
 * @author Lena Stoever
 * 
 */
public class ModelManager implements IModelManager {
	private final Log log = LogFactory.getLog(ModelManager.class);
	private int capacity = 0;
	private static ModelManager instance;
	private ReconfigurationModel model;
	//map of types of components with their belonging instances within the model
	private ConcurrentHashMap<BasicComponent, Vector<AllocationContext>> componentAllocationList;
	//map of types of components with their belonging reconfiguration information (service-IDs, responseTimes etc., see package reconfmm for more information)
	private ConcurrentHashMap<BasicComponent, ReconfigurationSpecification> componentReconfigurationSpecification;
	//map of components with the number of instances of each type
	private ConcurrentHashMap<BasicComponent, Integer> instanceCount;
	//list of allocated servers
	private ConcurrentLinkedQueue<ResourceContainer> allocatedServers;
	//list of not allocated models
	private ConcurrentLinkedQueue<ResourceContainer> notAllocatedServers;
	//map with the serviceID and the belonging queue of response times. This is necessary for deleting the oldest values when the maximum number is reached.
	private ConcurrentHashMap<Integer, BlockingQueue<SLOMonitoringRecord>> responseTimeQueues;

	private ModelManager() {
		log.info("ModelManager created");
	}

	/**
	 * This method has to be called before the instance of this ModelManager can
	 * be used. Otherwise the Reconfigurationmodel is not initialized.
	 * 
	 * @param m
	 *            ReconfigurationModel which represents the initialization-model
	 */
	public void setModel(ReconfigurationModel m) {
		log.info(m);
		this.model = m;
		this.initQueues();
		this.initComponentAllocationList();
		this.initAllocatedServers();
		this.initInstanceCount();
	}

	/**
	 * instantiating server lists and initializing them by the given model.
	 */
	private void initAllocatedServers() {
		this.allocatedServers = new ConcurrentLinkedQueue<ResourceContainer>();
		this.notAllocatedServers = new ConcurrentLinkedQueue<ResourceContainer>();

		// run through all allocationContexts and add the already allocated
		// servers
		for (int i = 0; i < this.model.getAllocation()
				.getTargetResourceEnvironment_Allocation()
				.getResourceContainer_ResourceEnvironment().size(); i++) {
			if (!this.allocatedServers.contains(this.model.getAllocation()
					.getTargetResourceEnvironment_Allocation()
					.getResourceContainer_ResourceEnvironment().get(i)))
				this.allocatedServers.add(this.model.getAllocation()
						.getTargetResourceEnvironment_Allocation()
						.getResourceContainer_ResourceEnvironment().get(i));
		}

	}

	// This is possible because of debugging reasons. This method should be
	// deleted when simulation works.
	public synchronized void addNotAllocatedServer(ResourceContainer server) {
		this.notAllocatedServers.add(server);
		this.model.getAllocation().getTargetResourceEnvironment_Allocation()
				.getResourceContainer_ResourceEnvironment().add(server);
	}

	/**
	 * Within this method the map with component types and the belonging instances is created initialized by the given model
	 */
	private void initComponentAllocationList() {
		this.componentAllocationList = new ConcurrentHashMap<BasicComponent, Vector<AllocationContext>>();
		this.componentReconfigurationSpecification = new ConcurrentHashMap<BasicComponent, ReconfigurationSpecification>();
		for (int i = 0; i < this.model.getComponents().size(); i++) {
			this.componentAllocationList.put(this.model.getComponents().get(i)
					.getComponent(), new Vector<AllocationContext>());
			this.componentReconfigurationSpecification.put(this.model
					.getComponents().get(i).getComponent(), this.model
					.getComponents().get(i));
			log.info(this.model.getAllocation());
			for (int k = 0; k < this.model.getAllocation()
					.getAllocationContexts_Allocation().size(); k++) {
				if (this.model.getComponents().get(i).getComponent() == this.model
						.getAllocation().getAllocationContexts_Allocation()
						.get(k).getAssemblyContext_AllocationContext()
						.getEncapsulatedComponent_ChildComponentContext()) {
					this.componentAllocationList.get(
							this.model.getComponents().get(i).getComponent()).add(
							this.model.getAllocation()
									.getAllocationContexts_Allocation().get(k));

				}
			}
		}

	}

	/**
	 * This method is responsible for identifying the current instances of components out of the given model and putting them into the hashmap 
	 */
	private void initInstanceCount() {
		this.instanceCount = new ConcurrentHashMap<BasicComponent, Integer>();
		//log.info("anzahl allocations: "+this.model.getAllocation().getAllocationContexts_Allocation().size());
		for (int i = 0; i < this.model.getAllocation()
				.getAllocationContexts_Allocation().size(); i++) {
			for (int k = 0; k < this.model.getComponents().size(); k++) {
				if (this.model.getComponents().get(k).getComponent() == this.model.getAllocation()
						.getAllocationContexts_Allocation().get(i)
						.getAssemblyContext_AllocationContext()
						.getEncapsulatedComponent_ChildComponentContext()) {
					log.info("i="+i+" k="+k+" this.model.getComponente.size: "+this.model.getComponents().size());
					log.info(this.instanceCount.size());
					if (this.model.getComponents().get(k).isMigratable()
							&& !this.instanceCount.containsKey(this.model
									.getComponents().get(k).getComponent())) {
						this.instanceCount.put(this.model.getComponents().get(k)
								.getComponent(), 1);
					} else if (this.instanceCount.containsKey(this.model
							.getComponents().get(k).getComponent())) {
						this.instanceCount.replace(this.model.getComponents().get(k)
								.getComponent(), this.instanceCount.get(this.model
								.getComponents().get(k).getComponent()) + 1);
					}
				}
			}
		}
		log.info("InitInstanceCount is done");
	}

	/**
	 * Initializing the HashMap with the response time queues 
	 */
	private void initQueues() {
		this.responseTimeQueues = new ConcurrentHashMap<Integer, BlockingQueue<SLOMonitoringRecord>>();
		for (int i = 0; i < this.model.getComponents().size(); i++) {
			for (int k = 0; k <this.model.getComponents().get(i).getServices()
					.size(); k++) {
				// It is important not to use a Set of Longs, because of the
				// possibility of equal values of response times
				ConcurrentSkipListSet<SLOMonitoringRecord> list = new ConcurrentSkipListSet<SLOMonitoringRecord>();
				this.responseTimeQueues.put(this.model.getComponents().get(i).getServices().get(k).getServiceID(), new ArrayBlockingQueue<SLOMonitoringRecord>(this.capacity));
				this.model.getComponents().get(i).getServices().get(k)
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
	public void update(AbstractKiekerMonitoringRecord newRecord) {
		SLOMonitoringRecord newSLORecord = (SLOMonitoringRecord) newRecord;
		int serviceID = newSLORecord.serviceId;
		synchronized (this.model) {
			for (int i = 0; i < this.model.getComponents().size(); i++) {
				for (int k = 0; k < this.model.getComponents().get(i).getServices()
						.size(); k++) {
					Service service = this.model.getComponents().get(i)
							.getServices().get(k);
					if (service.getServiceID() == serviceID) {
						ConcurrentSkipListSet<SLOMonitoringRecord> list = (ConcurrentSkipListSet<SLOMonitoringRecord>) service
						.getResponseTimes();
						BlockingQueue<SLOMonitoringRecord> queue = this.responseTimeQueues.get(serviceID);
						synchronized (list) {
							if(list.size()<this.capacity){
								list.add(newSLORecord);
								queue.add(newSLORecord);
							}else{
								list.remove(queue.poll());
								list.add(newSLORecord);
								queue.add(newSLORecord);
							}
							//log.info("ListSize: "+list.size());
						}
					}

				}
			}
		}
	}

	/**
	 * runs through the model and returns the set of responsetimes that belongs
	 * to the given service
	 * 
	 * @param serviceID
	 *            identifies the service
	 * @return
	 * @throws ServiceIDDoesNotExistException
	 */
	@SuppressWarnings("unchecked")
	public ConcurrentSkipListSet<SLOMonitoringRecord> getResponseTimes(
			int serviceID) throws ServiceIDDoesNotExistException {
		synchronized (this.model) {
			for (int i = 0; i < this.model.getComponents().size(); i++) {
				for (int k = 0; k < this.model.getComponents().get(i).getServices()
						.size(); k++) {
					if (this.model.getComponents().get(i).getServices().get(k)
							.getServiceID() == serviceID) {
						return ((ConcurrentSkipListSet<SLOMonitoringRecord>) this.model
								.getComponents().get(i).getServices().get(k)
								.getResponseTimes());
					}
				}
			}
		}
		throw new ServiceIDDoesNotExistException();
	}

	/**
	 * Method that represents the migration-operation of the ReconfigurationPlanMetaModel.
	 * @param component AllocationContext that is moved to another ResourceContainer.
	 * @param newServer Destination to which the component is moved.
	 * @throws ServerNotAllocatedException 
	 * @throws AllocationContextNotInModelException 
	 */
	private void migrate(AllocationContext component,
			ResourceContainer newServer) throws ServerNotAllocatedException,
			AllocationContextNotInModelException {
		if (this.model.getAllocation().getAllocationContexts_Allocation()
				.contains(component)) {
			if (this.allocatedServers.contains(newServer)) {
				component.setResourceContainer_AllocationContext(newServer);
				log.info("Migrate-Operation successfull");
			} else {
				throw new ServerNotAllocatedException();
			}
		} else {
			throw new AllocationContextNotInModelException();
		}

	}


	/**
	 * Method that represents the replication-operation of the ReconfigurationPlanMetaModel.
	 * @param component AssemblyContext which contains the type of the instance is created.
	 * @param destination ResourceContainer to which the new instance is added.
	 * @throws AllocationContextNotInModelException 
	 * @throws ServerNotAllocatedException 
	 */
	private void replicate(AssemblyContext component,
			ResourceContainer destination)
			throws AllocationContextNotInModelException,
			ServerNotAllocatedException {
		boolean componentExists = false;
		for (int i = 0; i < model.getAllocation()
				.getAllocationContexts_Allocation().size(); i++) {
			if (model.getAllocation().getAllocationContexts_Allocation().get(i)
					.getAssemblyContext_AllocationContext() == component) {
				componentExists = true;
				break;
			}
		}
		if (!componentExists) {
			throw new AllocationContextNotInModelException();
		}
		if (this.allocatedServers.contains(destination) && componentExists) {
			// Create new AllocationContext-Object
			AllocationFactory fac = AllocationFactoryImpl.init();
			AllocationContext newAllocationContext = fac
					.createAllocationContext();
			newAllocationContext
					.setAssemblyContext_AllocationContext(component);
			newAllocationContext
					.setResourceContainer_AllocationContext(destination);

			// add new AllocationContext-Object to the AllocationModel
			this.model.getAllocation().getAllocationContexts_Allocation().add(
					newAllocationContext);

			// update HashMap of AllocationContexts
			this.componentAllocationList.get(
					component.getEncapsulatedComponent_ChildComponentContext())
					.add(newAllocationContext);

			// update number of instances
			log.info("instanceCount "+this.instanceCount.size());
			log.info("component "+component.getEncapsulatedComponent_ChildComponentContext().getEntityName());
			this.instanceCount
					.replace(
							(BasicComponent) component
									.getEncapsulatedComponent_ChildComponentContext(),
							(this.instanceCount
									.get(component
											.getEncapsulatedComponent_ChildComponentContext()) + 1));
			log.info("Replicate-Operation successfull");
		} else {
			throw new ServerNotAllocatedException();
		}

	}

	/**
	 * Method that represents the allocate-operation of the ReconfigurationPlanMetaModel.
	 * @param container ResourceContainer that has to be allocated to the system.
	 * @throws ServerNotAllocatedException 
	 */
	private void allocate(ResourceContainer container)
			throws ServerNotAllocatedException {
		if (this.notAllocatedServers.contains(container)) {
			this.allocatedServers.add(container);
			this.notAllocatedServers.remove(container);
			log.info("Allocate-Operation successfull");
		} else {
			throw new ServerNotAllocatedException();
		}

	}

	/**
	 * Method that represents the deallocation-operation of the ReconfigurationPlanMetaMode.
	 * @param container ResourceContainer which is after the execution of this method not longer available.
	 * @throws ServerNotAllocatedException 
	 */
	private void deallocate(ResourceContainer container)
			throws ServerNotAllocatedException {
		if (this.allocatedServers.contains(container)) {
			this.notAllocatedServers.add(container);
			this.allocatedServers.remove(container);
			log.info("Deallocate-Operation successfull");
		} else {
			throw new ServerNotAllocatedException();
		}
	}

	/**
	 * Method that represents the de-replication-operation of the ReconfigurationPlanMetaModel.
	 * @param component AllocationContext that should is removed of the model.
	 * @throws AllocationContextNotInModelException 
	 */
	protected void dereplicate(AllocationContext component)
			throws org.trustsoft.slastic.control.exceptions.AllocationContextNotInModelException {

		//The Component can only be dereplicated if there is more than one instance and if the model contains the component.
		if (model.getAllocation().getAllocationContexts_Allocation().contains(
				component)
				&& (this.instanceCount.get(component
						.getAssemblyContext_AllocationContext()
						.getEncapsulatedComponent_ChildComponentContext()) > 1)) {
			model.getAllocation().getAllocationContexts_Allocation().remove(component);
			// Update Hashmap with List of AllocationContexts
			this.componentAllocationList.get(
					component.getAssemblyContext_AllocationContext()
							.getEncapsulatedComponent_ChildComponentContext())
					.remove(component);
			// Update InstanceCount
			this.instanceCount
					.replace(
							(BasicComponent) component
									.getAssemblyContext_AllocationContext()
									.getEncapsulatedComponent_ChildComponentContext(),
							(this.instanceCount
									.get(component
											.getAssemblyContext_AllocationContext()
											.getEncapsulatedComponent_ChildComponentContext()) - 1));
			log.info("Dereplicate-Operation successfull");
		} else {
			throw new AllocationContextNotInModelException();
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
	 * @param  savePersistent if this is true, it will be saved a persistent version of the model after the execution of the plan
	 * @return returns false if any operation-type is not available
	 * @throws AllocationContextNotInModelException
	 * @throws ServerNotAllocatedException
	 * @throws IllegalReconfigurationOperationException
	 */
	public boolean doReconfiguration(
			ReconfigurationPlanModel.SLAsticReconfigurationPlan plan,
			boolean savePersistent)
			throws AllocationContextNotInModelException,
			ServerNotAllocatedException,
			IllegalReconfigurationOperationException {
		EList<SLAsticReconfigurationOpType> operations = plan.getOperations();
		synchronized (this.model) {
			for (int i = 0; i < operations.size(); i++) {
				SLAsticReconfigurationOpType op = operations.get(i);
				// Check of which type the Operation is and executing belonging method
				if (op instanceof ComponentDeReplicationOPImpl) {
					AllocationContext comp = ((ComponentDeReplicationOP) op)
							.getClone();
					this.dereplicate(comp);
					log.info("dereplication");
				} else if (op instanceof ComponentMigrationOPImpl) {
					AllocationContext comp = ((ComponentMigrationOP) op)
							.getComponent();
					ResourceContainer destination = ((ComponentMigrationOP) op)
							.getDestination();
					this.migrate(comp, destination);
					log.info("migration");
				} else if (op instanceof ComponentReplicationOPImpl) {
					log.info("Es ist eine Replication");
					AssemblyContext comp = ((ComponentReplicationOP) op)
							.getComponent();
					ResourceContainer destination = ((ComponentReplicationOP) op)
							.getDestination();
					this.replicate(comp, destination);
					log.info("replication");
					log.info("Replication ausgefuehrt");
				} else if (op instanceof NodeAllocationOPImpl) {
					ResourceContainer container = ((NodeAllocationOP) op)
							.getNode();
					this.allocate(container);
					log.info("allocation");
				} else if (op instanceof NodeDeAllocationOPImpl) {
					ResourceContainer container = ((NodeDeAllocationOP) op)
							.getNode();
					this.deallocate(container);
					log.info("deallocation");
				} else {
					throw new IllegalReconfigurationOperationException();
				}
				if (savePersistent) {
					try {
						this.savePersistent();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return true;
	}

	// The following two methods have to be deleted after the debugging-phase,
	// because they can cause behavior we do not want
	public ReconfigurationModel getModel() {
		return model;
	}

	public ConcurrentLinkedQueue<ResourceContainer> getAllocatedServers() {
		return this.allocatedServers;
	}

	/**
	 * This method is responsible for saving the current version of the model.
	 * @throws IOException
	 */
	private void savePersistent() throws IOException {
		java.lang.System.out.println("VERDAMMT SPEICHERN");
		// Save ResourceEnvironment
		synchronized (model) {
			ResourceSet resourceEnvironmentResourceSet = new ResourceSetImpl();
			String resourceEnvironmentResourceLocation = "out.resourceenvironment";
			URI resourceEnvironmentURI = URI
					.createURI(resourceEnvironmentResourceLocation);
			Resource resourceEnvironmentResource = resourceEnvironmentResourceSet
					.createResource(resourceEnvironmentURI);
			resourceEnvironmentResource.getContents().add(
					model.getAllocation()
							.getTargetResourceEnvironment_Allocation());

			// Save System
			Resource repositoryResource = null;
			if (model.getComponents().size() > 0) {
				ResourceSet repositoryResourceSet = new ResourceSetImpl();
				String repositoryLocation = "out.repository";
				URI repositoryURI = URI.createURI(repositoryLocation);
				repositoryResource = repositoryResourceSet
						.createResource(repositoryURI);
				Repository repository = model.getComponents().get(0)
						.getComponent().getRepository_ProvidesComponentType();
				repositoryResource.getContents().add(repository);

			}

			log.info("repository saved");

			// Save System
			ResourceSet systemResourceSet = new ResourceSetImpl();
			String systemLocation = "out.system";
			URI systemURI = URI.createURI(systemLocation);
			Resource systemResource = systemResourceSet
					.createResource(systemURI);
			System systemAllocation = model.getAllocation()
					.getSystem_Allocation();
			EList<EObject> contents = systemResource.getContents();
			contents.add(systemAllocation);

			log.info("System saved");
			// Save Allocation and AllocationContexts
			ResourceSet allocationResourceSet = new ResourceSetImpl();
			String allocationLocation = "out.allocation";
			URI allocationURI = URI.createURI(allocationLocation);
			Resource allocationResource = allocationResourceSet
					.createResource(allocationURI);
			allocationResource.getContents().add(model.getAllocation());

			// Save ReconfigurationModel
			ResourceSet reconfigurationResourceSEt = new ResourceSetImpl();
			String reconfigurationLocation = "out.reconfMM";
			URI reconfigurationURI = URI.createURI(reconfigurationLocation);
			Resource reconfigurationResource = reconfigurationResourceSEt
					.createResource(reconfigurationURI);
			reconfigurationResource.getContents().add(model);

			resourceEnvironmentResource.save(null);
			repositoryResource.save(null);
			systemResource.save(null);
			allocationResource.save(null);
			reconfigurationResource.save(null);
			log.info("ReconfigurationModel saved");
		}

	}

	@Override
	public void doReconfiguration(SLAsticReconfigurationPlan plan) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setMaxResponseTime(int capacity) {
		this.capacity = capacity;
		
	}
}
