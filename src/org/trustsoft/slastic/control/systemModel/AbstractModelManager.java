package org.trustsoft.slastic.control.systemModel;

import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

import reconfMM.ReconfigurationModel;
import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.allocation.AllocationFactory;
import de.uka.ipd.sdq.pcm.allocation.impl.AllocationFactoryImpl;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.core.composition.CompositionFactory;
import de.uka.ipd.sdq.pcm.core.composition.impl.CompositionFactoryImpl;
import de.uka.ipd.sdq.pcm.repository.BasicComponent;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;

public abstract class AbstractModelManager {
	protected ReconfigurationModel model;

	public abstract void update(AbstractKiekerMonitoringRecord newRecord,
			AbstractKiekerMonitoringRecord oldRecord);

	protected boolean add(AllocationContext component) {
		return model.getAllocation().getAllocationContexts_Allocation().add(component);
		
	}

	protected boolean remove(AllocationContext component) {
		return model.getAllocation().getAllocationContexts_Allocation().remove(component);
	}

	protected abstract void migrate(AllocationContext component,
			ResourceContainer newServer);

	protected abstract void replicate(AllocationContext component);

	protected abstract void dereplicate(AllocationContext component);

	protected abstract void replicate(AllocationContext component,
			ResourceContainer destination);



	protected abstract void allocate(ResourceContainer container);

	protected abstract void deallocate(ResourceContainer container);

}
