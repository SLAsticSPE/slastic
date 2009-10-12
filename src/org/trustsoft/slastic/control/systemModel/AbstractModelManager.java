package org.trustsoft.slastic.control.systemModel;

import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

import org.trustsoft.slastic.control.exceptions.AllocationContextNotInModelException;
import org.trustsoft.slastic.control.exceptions.ServerNotAllocatedException;

import reconfMM.ReconfigurationModel;
import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;

/**
 * Abstract class that has to be extended by any class that should manage the ReconfigurationModel of SLAsticControl.
 * It contains all methods that represent the operations of the ReconfigurationPlanModel.
 * @author Lena Stoever
 *
 */
public abstract class AbstractModelManager {
	protected ReconfigurationModel model;

	/**
	 * Synchronized updating of the ReconfigurationModel via MonitoringRecords
	 * @param newRecord Record that updates the model
	 * @param oldRecord Record that represents an older value that can be deleted, because of the update of the model
	 */
	public abstract void update(AbstractKiekerMonitoringRecord newRecord,
			AbstractKiekerMonitoringRecord oldRecord);

	/**
	 * Method that is responsible for adding new instances of components via AllocationContexts to the model.
	 * @param component AllocationContext that has to be added to the model
	 * @return success of addition
	 */
	protected boolean add(AllocationContext component) {
		return model.getAllocation().getAllocationContexts_Allocation().add(component);
		
	}

	/**
	 * Method that is responsible for removing an instance of a component via a AllocationContext.
	 * @param component AllocationContext that has to be deleted.
	 * @return success of delete-operation
	 * @throws AllocationContextNotInModelException 
	 */
	protected boolean remove(AllocationContext component) throws AllocationContextNotInModelException {
		if(model.getAllocation().getAllocationContexts_Allocation().contains(component)){
			return model.getAllocation().getAllocationContexts_Allocation().remove(component);
		}else{
			throw new AllocationContextNotInModelException();
		}
		
	}

	/**
	 * Method that represents the migration-operation of the ReconfigurationPlanMetaModel.
	 * @param component AllocationContext that is moved to another ResourceContainer.
	 * @param newServer Destination to which the component is moved.
	 * @throws ServerNotAllocatedException 
	 * @throws AllocationContextNotInModelException 
	 */
	protected abstract void migrate(AllocationContext component,
			ResourceContainer newServer) throws ServerNotAllocatedException, AllocationContextNotInModelException;

	/**
	 * Method that represents the de-replication-operation of the ReconfigurationPlanMetaModel.
	 * @param component AllocationContext that should is removed of the model.
	 * @throws AllocationContextNotInModelException 
	 */
	protected abstract void dereplicate(AllocationContext component) throws AllocationContextNotInModelException;

	
	/**
	 * Method that represents the replication-operation of the ReconfigurationPlanMetaModel.
	 * @param component AssemblyContext which contains the type of the instance is created.
	 * @param destination ResourceContainer to which the new instance is added.
	 * @throws AllocationContextNotInModelException 
	 * @throws ServerNotAllocatedException 
	 */
	protected abstract void replicate(AssemblyContext component,
			ResourceContainer destination) throws AllocationContextNotInModelException, ServerNotAllocatedException;


	/**
	 * Method that represents the allocate-operation of the ReconfigurationPlanMetaModel.
	 * @param container ResourceContainer that has to be allocated to the system.
	 * @throws ServerNotAllocatedException 
	 */
	protected abstract void allocate(ResourceContainer container) throws ServerNotAllocatedException;

	/**
	 * Method that represents the deallocation-operation of the ReconfigurationPlanMetaMode.
	 * @param container ResourceContainer which is after the execution of this method not longer available.
	 * @throws ServerNotAllocatedException 
	 */
	protected abstract void deallocate(ResourceContainer container) throws ServerNotAllocatedException;

}
