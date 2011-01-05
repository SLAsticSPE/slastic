package org.trustsoft.slastic.plugins.pcm.control.modelManager;

import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.trustsoft.slastic.common.event.IObservationEvent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.control.components.modelManager.AbstractModelManagerComponent;
import org.trustsoft.slastic.control.exceptions.IllegalReconfigurationOperationException;
import org.trustsoft.slastic.plugins.slasticImpl.ModelIOUtils;
import org.trustsoft.slastic.reconfiguration.ReconfigurationException;

import reconfMM.ReconfigurationModel;
import reconfMM.ReconfigurationSpecification;
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
import de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan;
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
public class ModelManager extends AbstractModelManagerComponent {

	private final Log log = LogFactory.getLog(ModelManager.class);
	// private static ModelManager instance;
	protected volatile ReconfigurationModel reconfigurationModel;
	// map of types of components with their belonging instances within the
	// reconfigurationModel
	private volatile ConcurrentHashMap<BasicComponent, Vector<AllocationContext>> componentAllocationList;
	// map of types of components with their belonging reconfiguration
	// information (service-IDs, responseTimes etc., see package reconfmm for
	// more information)
	private volatile ConcurrentHashMap<BasicComponent, ReconfigurationSpecification> componentReconfigurationSpecification;
	// map of components with the number of instances of each type
	private volatile ConcurrentHashMap<BasicComponent, Integer> instanceCount;
	// list of allocated servers
	private volatile ConcurrentLinkedQueue<ResourceContainer> allocatedServers;
	// list of not allocated models
	private volatile ConcurrentLinkedQueue<ResourceContainer> notAllocatedServers;

	private static final String PROP_NAME_RECONFIGURATIONMODEL_FN =
			"reconfigurationmodel_fn";
	private static final String PROP_NAME_RESOURCEENVIRONMENTMODEL_FN =
			"resourceenvironmentmodel_fn";
	private static final String PROP_NAME_QOSANNOTATIONSMODEL_FN =
			"qosannotationsmodel_fn";

	public ModelManager() {
	}

	@Override
	public boolean init() {
		try {
			this.reconfigurationModel =
					ModelIOUtils
							.readOLDReconfigurationModel(this
									.getInitProperty(ModelManager.PROP_NAME_RECONFIGURATIONMODEL_FN));
			this.initComponentAllocationList();
			this.initAllocatedServers();
			this.initInstanceCount();
			return true;
		} catch (final IOException ex) {
			this.log.error("Failed to load reconfiguration model", ex);
			return false;
		}
	}

	@Override
	public boolean execute() {
		return true;
	}

	/**
	 * instantiating server lists and initializing them by the given
	 * reconfigurationModel.
	 */
	private void initAllocatedServers() {
		this.allocatedServers = new ConcurrentLinkedQueue<ResourceContainer>();
		this.notAllocatedServers =
				new ConcurrentLinkedQueue<ResourceContainer>();

		// run through all allocationContexts and add the already allocated
		// servers
		for (int i = 0; i < this.reconfigurationModel.getAllocation()
				.getTargetResourceEnvironment_Allocation()
				.getResourceContainer_ResourceEnvironment().size(); i++) {
			if (!this.allocatedServers.contains(this.reconfigurationModel
					.getAllocation().getTargetResourceEnvironment_Allocation()
					.getResourceContainer_ResourceEnvironment().get(i))) {
				this.allocatedServers.add(this.reconfigurationModel
						.getAllocation()
						.getTargetResourceEnvironment_Allocation()
						.getResourceContainer_ResourceEnvironment().get(i));
			}
		}

	}

	// This is possible because of debugging reasons. This method should be
	// deleted when simulation works.
	public synchronized void addNotAllocatedServer(
			final ResourceContainer server) {
		this.notAllocatedServers.add(server);
		this.reconfigurationModel.getAllocation()
				.getTargetResourceEnvironment_Allocation()
				.getResourceContainer_ResourceEnvironment().add(server);
	}

	/**
	 * Within this method the map with component types and the belonging
	 * instances is created initialized by the given reconfigurationModel
	 */
	private void initComponentAllocationList() {
		this.componentAllocationList =
				new ConcurrentHashMap<BasicComponent, Vector<AllocationContext>>();
		this.componentReconfigurationSpecification =
				new ConcurrentHashMap<BasicComponent, ReconfigurationSpecification>();
		for (int i = 0; i < this.reconfigurationModel.getComponents().size(); i++) {
			this.componentAllocationList.put(this.reconfigurationModel
					.getComponents().get(i).getComponent(),
					new Vector<AllocationContext>());
			this.componentReconfigurationSpecification.put(
					this.reconfigurationModel.getComponents().get(i)
							.getComponent(), this.reconfigurationModel
							.getComponents().get(i));
			this.log.info(this.reconfigurationModel.getAllocation());
			for (int k = 0; k < this.reconfigurationModel.getAllocation()
					.getAllocationContexts_Allocation().size(); k++) {
				if (this.reconfigurationModel.getComponents().get(i)
						.getComponent() == this.reconfigurationModel
						.getAllocation().getAllocationContexts_Allocation()
						.get(k).getAssemblyContext_AllocationContext()
						.getEncapsulatedComponent_ChildComponentContext()) {
					this.componentAllocationList.get(
							this.reconfigurationModel.getComponents().get(i)
									.getComponent()).add(
							this.reconfigurationModel.getAllocation()
									.getAllocationContexts_Allocation().get(k));

				}
			}
		}

	}

	/**
	 * This method is responsible for identifying the current instances of
	 * components out of the given reconfigurationModel and putting them into
	 * the hashmap
	 */
	private void initInstanceCount() {
		this.instanceCount = new ConcurrentHashMap<BasicComponent, Integer>();
		// log.info("anzahl allocations: "+this.reconfigurationModel.getAllocation().getAllocationContexts_Allocation().size());
		for (int i = 0; i < this.reconfigurationModel.getAllocation()
				.getAllocationContexts_Allocation().size(); i++) {
			for (int k = 0; k < this.reconfigurationModel.getComponents()
					.size(); k++) {
				if (this.reconfigurationModel.getComponents().get(k)
						.getComponent() == this.reconfigurationModel
						.getAllocation().getAllocationContexts_Allocation()
						.get(i).getAssemblyContext_AllocationContext()
						.getEncapsulatedComponent_ChildComponentContext()) {
					this.log.info("i=" + i + " k=" + k
							+ " this.model.getComponente.size: "
							+ this.reconfigurationModel.getComponents().size());
					this.log.info(this.instanceCount.size());
					if (this.reconfigurationModel.getComponents().get(k)
							.isMigratable()
							&& !this.instanceCount
									.containsKey(this.reconfigurationModel
											.getComponents().get(k)
											.getComponent())) {
						this.instanceCount.put(this.reconfigurationModel
								.getComponents().get(k).getComponent(), 1);
					} else if (this.instanceCount
							.containsKey(this.reconfigurationModel
									.getComponents().get(k).getComponent())) {
						this.instanceCount.replace(this.reconfigurationModel
								.getComponents().get(k).getComponent(),
								this.instanceCount
										.get(this.reconfigurationModel
												.getComponents().get(k)
												.getComponent()) + 1);
					}
				}
			}
		}
		this.log.info("InitInstanceCount is done");
	}

	// /**
	// * Singleton implementation. Don't forget to call the initModel()-method
	// * before using the returned Instance
	// *
	// * @return returns the only instance of the ModelManager
	// */
	// public synchronized static ModelManager getInstance() {
	// if (instance == null) {
	// instance = new ModelManager();
	// }
	// return instance;
	//
	// }
	@Override
	public void newObservation(final IObservationEvent ime) {
		// do nothing
	}

	/**
	 * Method that represents the migration-operation of the
	 * ReconfigurationPlanMetaModel.
	 * 
	 * @param component
	 *            AllocationContext that is moved to another ResourceContainer.
	 * @param newServer
	 *            Destination to which the component is moved.
	 * @throws ServerNotAllocatedException
	 * @throws AllocationContextNotInModelException
	 */
	private void migrate(final AllocationContext component,
			final ResourceContainer newServer)
			throws ServerNotAllocatedException,
			AllocationContextNotInModelException {
		if (this.reconfigurationModel.getAllocation()
				.getAllocationContexts_Allocation().contains(component)) {
			if (this.allocatedServers.contains(newServer)) {
				component.setResourceContainer_AllocationContext(newServer);
				this.log.info("Migrate-Operation successfull");
			} else {
				throw new ServerNotAllocatedException();
			}
		} else {
			throw new AllocationContextNotInModelException();
		}

	}

	/**
	 * Method that represents the replication-operation of the
	 * ReconfigurationPlanMetaModel.
	 * 
	 * @param component
	 *            AssemblyContext which contains the type of the instance is
	 *            created.
	 * @param destination
	 *            ResourceContainer to which the new instance is added.
	 * @throws AllocationContextNotInModelException
	 * @throws ServerNotAllocatedException
	 */
	private void replicate(final AssemblyContext component,
			final ResourceContainer destination)
			throws AllocationContextNotInModelException,
			ServerNotAllocatedException {
		boolean componentExists = false;
		for (int i = 0; i < this.reconfigurationModel.getAllocation()
				.getAllocationContexts_Allocation().size(); i++) {
			if (this.reconfigurationModel.getAllocation()
					.getAllocationContexts_Allocation().get(i)
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
			final AllocationFactory fac = AllocationFactoryImpl.init();
			final AllocationContext newAllocationContext =
					fac.createAllocationContext();
			newAllocationContext
					.setAssemblyContext_AllocationContext(component);
			newAllocationContext
					.setResourceContainer_AllocationContext(destination);

			// add new AllocationContext-Object to the AllocationModel
			this.reconfigurationModel.getAllocation()
					.getAllocationContexts_Allocation().add(
							newAllocationContext);

			// update HashMap of AllocationContexts
			this.componentAllocationList.get(
					component.getEncapsulatedComponent_ChildComponentContext())
					.add(newAllocationContext);

			// update number of instances
			this.log.info("instanceCount " + this.instanceCount.size());
			this.log.info("component "
					+ component
							.getEncapsulatedComponent_ChildComponentContext()
							.getEntityName());
			this.instanceCount
					.replace(
							(BasicComponent) component
									.getEncapsulatedComponent_ChildComponentContext(),
							(this.instanceCount.get(component
									.getEncapsulatedComponent_ChildComponentContext()) + 1));
			this.log.info("Replicate-Operation successfull");
		} else {
			throw new ServerNotAllocatedException();
		}

	}

	/**
	 * Method that represents the allocate-operation of the
	 * ReconfigurationPlanMetaModel.
	 * 
	 * @param container
	 *            ResourceContainer that has to be allocated to the system.
	 * @throws ServerNotAllocatedException
	 */
	private void allocate(final ResourceContainer container)
			throws ServerNotAllocatedException {
		if (this.notAllocatedServers.contains(container)) {
			this.allocatedServers.add(container);
			this.notAllocatedServers.remove(container);
			this.log.info("Allocate-Operation successfull");
		} else {
			throw new ServerNotAllocatedException();
		}

	}

	/**
	 * Method that represents the deallocation-operation of the
	 * ReconfigurationPlanMetaMode.
	 * 
	 * @param container
	 *            ResourceContainer which is after the execution of this method
	 *            not longer available.
	 * @throws ServerNotAllocatedException
	 */
	private void deallocate(final ResourceContainer container)
			throws ServerNotAllocatedException {
		if (this.allocatedServers.contains(container)) {
			this.notAllocatedServers.add(container);
			this.allocatedServers.remove(container);
			this.log.info("Deallocate-Operation successfull");
		} else {
			throw new ServerNotAllocatedException();
		}
	}

	/**
	 * Method that represents the de-replication-operation of the
	 * ReconfigurationPlanMetaModel.
	 * 
	 * @param component
	 *            AllocationContext that should is removed of the
	 *            reconfigurationModel.
	 * @throws AllocationContextNotInModelException
	 */
	protected void dereplicate(final AllocationContext component)
			throws org.trustsoft.slastic.plugins.pcm.control.modelManager.AllocationContextNotInModelException {

		// The Component can only be dereplicated if there is more than one
		// instance and if the reconfigurationModel contains the component.
		if (this.reconfigurationModel.getAllocation()
				.getAllocationContexts_Allocation().contains(
						component)
				&& (this.instanceCount.get(component
						.getAssemblyContext_AllocationContext()
						.getEncapsulatedComponent_ChildComponentContext()) > 1)) {
			this.reconfigurationModel.getAllocation()
					.getAllocationContexts_Allocation().remove(component);
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
			this.log.info("Dereplicate-Operation successfull");
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
	 * @param savePersistent
	 *            if this is true, it will be saved a persistent version of the
	 *            reconfigurationModel after the execution of the plan
	 * @return returns false if any operation-type is not available
	 * @throws AllocationContextNotInModelException
	 * @throws ServerNotAllocatedException
	 * @throws IllegalReconfigurationOperationException
	 */
	public boolean doReconfiguration(
			final ReconfigurationPlanModel.SLAsticReconfigurationPlan plan,
			final boolean savePersistent)
			throws AllocationContextNotInModelException,
			ServerNotAllocatedException,
			IllegalReconfigurationOperationException {
		final EList<SLAsticReconfigurationOpType> operations =
				plan.getOperations();
		synchronized (this.reconfigurationModel) {
			for (int i = 0; i < operations.size(); i++) {
				final SLAsticReconfigurationOpType op = operations.get(i);
				// Check of which type the Operation is and executing belonging
				// method
				if (op instanceof ComponentDeReplicationOPImpl) {
					final AllocationContext comp =
							((ComponentDeReplicationOP) op).getClone();
					this.dereplicate(comp);
					this.log.info("dereplication");
				} else if (op instanceof ComponentMigrationOPImpl) {
					final AllocationContext comp =
							((ComponentMigrationOP) op).getComponent();
					final ResourceContainer destination =
							((ComponentMigrationOP) op).getDestination();
					this.migrate(comp, destination);
					this.log.info("migration");
				} else if (op instanceof ComponentReplicationOPImpl) {
					this.log.info("Es ist eine Replication");
					final AssemblyContext comp =
							((ComponentReplicationOP) op).getComponent();
					final ResourceContainer destination =
							((ComponentReplicationOP) op).getDestination();
					this.replicate(comp, destination);
					this.log.info("replication");
					this.log.info("Replication ausgefuehrt");
				} else if (op instanceof NodeAllocationOPImpl) {
					final ResourceContainer container =
							((NodeAllocationOP) op).getNode();
					this.allocate(container);
					this.log.info("allocation");
				} else if (op instanceof NodeDeAllocationOPImpl) {
					final ResourceContainer container =
							((NodeDeAllocationOP) op).getNode();
					this.deallocate(container);
					this.log.info("deallocation");
				} else {
					throw new IllegalReconfigurationOperationException();
				}
				if (savePersistent) {
					try {
						this.savePersistent();
					} catch (final IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return true;
	}

	public ReconfigurationModel getReconfigurationModel() {
		return this.reconfigurationModel;
	}

	public ConcurrentLinkedQueue<ResourceContainer> getAllocatedServers() {
		return this.allocatedServers;
	}

	/**
	 * This method is responsible for saving the current version of the
	 * reconfigurationModel.
	 * 
	 * @throws IOException
	 */
	private void savePersistent() throws IOException {
		java.lang.System.out.println("VERDAMMT SPEICHERN");
		// Save ResourceEnvironment
		synchronized (this.reconfigurationModel) {
			final ResourceSet resourceEnvironmentResourceSet =
					new ResourceSetImpl();
			final String resourceEnvironmentResourceLocation =
					"out.resourceenvironment";
			final URI resourceEnvironmentURI =
					URI.createURI(resourceEnvironmentResourceLocation);
			final Resource resourceEnvironmentResource =
					resourceEnvironmentResourceSet
							.createResource(resourceEnvironmentURI);
			resourceEnvironmentResource.getContents().add(
					this.reconfigurationModel.getAllocation()
							.getTargetResourceEnvironment_Allocation());

			// Save System
			Resource repositoryResource = null;
			if (this.reconfigurationModel.getComponents().size() > 0) {
				final ResourceSet repositoryResourceSet = new ResourceSetImpl();
				final String repositoryLocation = "out.repository";
				final URI repositoryURI = URI.createURI(repositoryLocation);
				repositoryResource =
						repositoryResourceSet.createResource(repositoryURI);
				final Repository repository =
						this.reconfigurationModel.getComponents().get(0)
								.getComponent()
								.getRepository_ProvidesComponentType();
				repositoryResource.getContents().add(repository);

			}

			this.log.info("repository saved");

			// Save System
			final ResourceSet systemResourceSet = new ResourceSetImpl();
			final String systemLocation = "out.system";
			final URI systemURI = URI.createURI(systemLocation);
			final Resource systemResource =
					systemResourceSet.createResource(systemURI);
			final System systemAllocation =
					this.reconfigurationModel.getAllocation()
							.getSystem_Allocation();
			final EList<EObject> contents = systemResource.getContents();
			contents.add(systemAllocation);

			this.log.info("System saved");
			// Save Allocation and AllocationContexts
			final ResourceSet allocationResourceSet = new ResourceSetImpl();
			final String allocationLocation = "out.allocation";
			final URI allocationURI = URI.createURI(allocationLocation);
			final Resource allocationResource =
					allocationResourceSet.createResource(allocationURI);
			allocationResource.getContents().add(
					this.reconfigurationModel.getAllocation());

			// Save ReconfigurationModel
			final ResourceSet reconfigurationResourceSEt =
					new ResourceSetImpl();
			final String reconfigurationLocation = "out.reconfMM";
			final URI reconfigurationURI =
					URI.createURI(reconfigurationLocation);
			final Resource reconfigurationResource =
					reconfigurationResourceSEt
							.createResource(reconfigurationURI);
			reconfigurationResource.getContents()
					.add(this.reconfigurationModel);

			resourceEnvironmentResource.save(null);
			repositoryResource.save(null);
			systemResource.save(null);
			allocationResource.save(null);
			reconfigurationResource.save(null);
			this.log.info("ReconfigurationModel saved");
		}

	}

	@Override
	public void doReconfiguration(final SLAsticReconfigurationPlan plan) {
		// TODO Auto-generated method stub
	}

	@Override
	public void handleEvent(final IEvent ev) {
	}

	@Override
	public void terminate(final boolean error) {
		// do nothing
	}

	@Override
	public void doReconfiguration(final ReconfigurationPlan plan)
			throws ReconfigurationException {
		throw new UnsupportedOperationException();
	}
}
