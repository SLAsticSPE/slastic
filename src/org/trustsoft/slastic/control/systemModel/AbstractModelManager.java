package org.trustsoft.slastic.control.systemModel;

import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	private static final Log log = LogFactory.getLog(ModelManager.class);
	private static ReconfigurationModel model;

	public synchronized static void initModel(ReconfigurationModel initModel){
		if(model == null){
			model = initModel;
		}
	}

	public abstract void update(AbstractKiekerMonitoringRecord newRecord,
			AbstractKiekerMonitoringRecord oldRecord);

	protected boolean add(BasicComponent component, ResourceContainer container) {
		boolean successful = false;
		// Add to Repository
		if (model.getComponents().size() > 0) {
			successful = model.getComponents().get(0).getComponent()
					.getRepository_ProvidesComponentType()
					.getComponents__Repository().add(component);
			if (successful) {
				// Add to Allocation-Model
				AllocationFactory fac = AllocationFactoryImpl.init();
				AllocationContext con = fac.createAllocationContext();
				con.setResourceContainer_AllocationContext(container);

				CompositionFactory comFac = CompositionFactoryImpl.init();
				AssemblyContext ass = comFac.createAssemblyContext();
				ass.setEncapsulatedComponent_ChildComponentContext(component);
				con.setAssemblyContext_AllocationContext(ass);

				return model.getAllocation().getAllocationContexts_Allocation()
						.add(con);
			}
		}
		return false;
	}

	protected boolean remove(BasicComponent component) {
		boolean successful = false;
		// remove out of Allocation-Model
		for (int i = 0; i < model.getAllocation()
				.getAllocationContexts_Allocation().size(); i++) {
			if (model.getAllocation().getAllocationContexts_Allocation().get(i)
					.getAssemblyContext_AllocationContext()
					.getEncapsulatedComponent_ChildComponentContext().getId() == component
					.getId()) {
				model.getAllocation().getAllocationContexts_Allocation()
						.remove(i);
				successful = true;
			}
		}
		// remove out of Repository
		if (successful == true)
			return model.getComponents().get(0).getComponent()
					.getRepository_ProvidesComponentType()
					.getComponents__Repository().remove(component);
		else
			return false;
	}

	public abstract void migrate(BasicComponent component,
			ResourceContainer newServer);

	public abstract void replicate(BasicComponent component);

	public abstract void replicate(BasicComponent component,
			ResourceContainer destination);

	public BasicComponent getComponent(int serviceID) {
		for (int i = 0; i < model.getComponents().size(); i++) {
			for (int k = 0; k < model.getComponents().get(i).getServices()
					.size(); k++) {
				return model.getComponents().get(i).getComponent();
			}
		}
		log.error("No Component with ID: " + serviceID
				+ " found! Returned NULL in getComponent()");
		return null;
	}

}
