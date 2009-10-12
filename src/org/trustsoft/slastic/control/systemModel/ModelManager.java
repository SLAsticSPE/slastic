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
public class ModelManager extends AbstractModelManager {
	private final Log log = LogFactory.getLog(ModelManager.class);
	private static ModelManager instance;
	private ConcurrentHashMap<BasicComponent, Vector<AllocationContext>> componentAllocationList;
	private ConcurrentHashMap<BasicComponent, ReconfigurationSpecification> componentReconfigurationSpecification;
	private ConcurrentHashMap<BasicComponent, Integer> instanceCount;
	private ConcurrentLinkedQueue<ResourceContainer> allocatedServers;
	private ConcurrentLinkedQueue<ResourceContainer> notAllocatedServers;

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
	public void initModel(ReconfigurationModel m) {
		model = m;
		this.initSet();
		this.initComponentAllocationList();
		this.initAllocatedServers();
		this.initInstanceCount();
	}

	private void initAllocatedServers() {
		this.allocatedServers = new ConcurrentLinkedQueue<ResourceContainer>();
		this.notAllocatedServers = new ConcurrentLinkedQueue<ResourceContainer>();

		// run through all allocationContexts and add the already allocated
		// servers
		for (int i = 0; i < model.getAllocation()
				.getTargetResourceEnvironment_Allocation()
				.getResourceContainer_ResourceEnvironment().size(); i++) {
			if (!this.allocatedServers.contains(model.getAllocation()
					.getTargetResourceEnvironment_Allocation()
					.getResourceContainer_ResourceEnvironment().get(i)))
				this.allocatedServers.add(model.getAllocation()
						.getTargetResourceEnvironment_Allocation()
						.getResourceContainer_ResourceEnvironment().get(i));
		}

	}

	// This is possible because of debugging reasons. This method should be
	// deleted when simulation works.
	public synchronized void addNotAllocatedServer(ResourceContainer server) {
		this.notAllocatedServers.add(server);
		model.getAllocation().getTargetResourceEnvironment_Allocation()
				.getResourceContainer_ResourceEnvironment().add(server);
	}

	private void initComponentAllocationList() {
		this.componentAllocationList = new ConcurrentHashMap<BasicComponent, Vector<AllocationContext>>();
		this.componentReconfigurationSpecification = new ConcurrentHashMap<BasicComponent, ReconfigurationSpecification>();
		for (int i = 0; i < model.getComponents().size(); i++) {
			this.componentAllocationList.put(model.getComponents().get(i)
					.getComponent(), new Vector<AllocationContext>());
			this.componentReconfigurationSpecification.put(model
					.getComponents().get(i).getComponent(), model
					.getComponents().get(i));
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

	private void initInstanceCount() {
		for (int i = 0; i < model.getAllocation()
				.getAllocationContexts_Allocation().size(); i++) {
			for (int k = 0; k < model.getComponents().size(); k++) {
				if (model.getComponents().get(k) == model.getAllocation()
						.getAllocationContexts_Allocation().get(i)
						.getAssemblyContext_AllocationContext()
						.getEncapsulatedComponent_ChildComponentContext()) {
					if (model.getComponents().get(k).isMigratable()
							&& !this.instanceCount.containsKey(model
									.getComponents().get(i).getComponent())) {
						this.instanceCount.put(model.getComponents().get(k)
								.getComponent(), 1);
					} else if (this.instanceCount.containsKey(model
							.getComponents().get(k).getComponent())) {
						this.instanceCount.replace(model.getComponents().get(k)
								.getComponent(), this.instanceCount.get(model
								.getComponents().get(k).getComponent()) + 1);
					}
				}
			}
		}
	}

	private void initSet() {
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
		synchronized (model) {
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
		}
		throw new ServiceIDDoesNotExistException();
	}

	@Override
	protected void migrate(AllocationContext component,
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

	@Override
	protected void replicate(AssemblyContext component,
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

	@Override
	protected void allocate(ResourceContainer container)
			throws ServerNotAllocatedException {
		if (this.notAllocatedServers.contains(container)) {
			this.allocatedServers.add(container);
			this.notAllocatedServers.remove(container);
			log.info("Allocate-Operation successfull");
		} else {
			throw new ServerNotAllocatedException();
		}

	}

	@Override
	protected void deallocate(ResourceContainer container)
			throws ServerNotAllocatedException {
		if (this.allocatedServers.contains(container)) {
			this.notAllocatedServers.add(container);
			this.allocatedServers.remove(container);
			log.info("Deallocate-Operation successfull");
		} else {
			throw new ServerNotAllocatedException();
		}
	}

	@Override
	protected void dereplicate(AllocationContext component)
			throws org.trustsoft.slastic.control.exceptions.AllocationContextNotInModelException {
		// TODO dereplication muss noch getestet werden.
		if (model.getAllocation().getAllocationContexts_Allocation().contains(
				component)) {
			this.remove(component);
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
			throw new org.trustsoft.slastic.control.exceptions.AllocationContextNotInModelException();
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
				// Check of which type the Operation is
				if (op instanceof ComponentDeReplicationOPImpl) {
					AllocationContext comp = ((ComponentDeReplicationOP) op)
							.getComponent();
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

	private void savePersistent() throws IOException {
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
}
