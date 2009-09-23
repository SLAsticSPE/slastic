package org.trustsoft.slastic.control.systemModel;

import java.util.concurrent.ConcurrentSkipListSet;

import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;

import reconfMM.ReconfigurationModel;
import reconfMM.Service;
import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.repository.BasicComponent;
import de.uka.ipd.sdq.pcm.repository.RepositoryFactory;
import de.uka.ipd.sdq.pcm.repository.impl.RepositoryFactoryImpl;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;

public class ModelManager extends AbstractModelManager {

	private static final Log log = LogFactory.getLog(ModelManager.class);
	private static ModelManager instance;
	boolean firstupdate = false;

	private ModelManager(){
		
	}
	private ModelManager(ReconfigurationModel reconfigurationModel) {
		model = reconfigurationModel;
		this.initSet();
	}
	
	public void initModel(ReconfigurationModel m){
		model = m;
		this.initSet();
	}
	
	
	
	public BasicComponent getComponent(int serviceID) {
		for (int i = 0; i < model.getComponents().size(); i++) {
			for (int k = 0; k < model.getComponents().get(i).getServices()
					.size(); k++) {
				if(model.getComponents().get(i).getServices().get(k).getServiceID() == serviceID){
					return model.getComponents().get(i).getComponent();
				}
				
			}
		}
		log.error("No Component with ID: " + serviceID
				+ " found! Returned NULL in getComponent()");
		return null;
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

	public synchronized static ModelManager getInstance() {
		if(instance == null){
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
		for (int i = 0; i < model.getComponents().size(); i++) {
			for (int k = 0; k < model.getComponents().get(i).getServices()
					.size(); k++) {
				Service service = model.getComponents().get(i).getServices()
						.get(k);
				if (service.getServiceID() == serviceID) {
					synchronized((ConcurrentSkipListSet<SLOMonitoringRecord>) service
							.getResponseTimes()){((ConcurrentSkipListSet<SLOMonitoringRecord>) service
							.getResponseTimes()).add(newSLOrecord);}
					
					updated = true;
					if (oldSLOrecord != null) {
						if (service.getServiceID() == oldSLOrecord.serviceId) {
							synchronized((ConcurrentSkipListSet<SLOMonitoringRecord>) service
									.getResponseTimes()){
							((ConcurrentSkipListSet<SLOMonitoringRecord>) service
									.getResponseTimes()).remove(oldSLOrecord);}
							oldSLOrecord = null;
						}
					}
				}

			}
		}
		if (updated){
			
			
			
		}
		updated = false;
	}

	@SuppressWarnings("unchecked")
	public ConcurrentSkipListSet<SLOMonitoringRecord> getResponseTimes(
			int serviceID) {
		//log.info("getResponseTimes wurde aufgerufen");
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
	public void migrate(BasicComponent component, ResourceContainer newServer) {
		this.remove(component);
		this.add(component, newServer);
	}

	@Override
	public void replicate(BasicComponent component) {
		BasicComponent newComponent;
		RepositoryFactory fac = RepositoryFactoryImpl.init();
		newComponent = fac.createBasicComponent();

		newComponent.setImplementationComponentType(component
				.getImplementationComponentType());
		newComponent.setRepository_ProvidesComponentType(component
				.getRepository_ProvidesComponentType());

		EList<AllocationContext> allocationContexts_Allocation = model
				.getAllocation().getAllocationContexts_Allocation();
		ResourceContainer container = null;
		for (int i = 0; i < allocationContexts_Allocation.size(); i++) {
			if (allocationContexts_Allocation.get(i)
					.getAssemblyContext_AllocationContext()
					.getEncapsulatedComponent_ChildComponentContext().getId() == component
					.getId()) {
				container = allocationContexts_Allocation.get(i)
						.getResourceContainer_AllocationContext();
			}
		}
		if (container != null)
			this.add(newComponent, container);
		else
			log.error("Component with ID: " + component.getId()
					+ " does not exist in current AllocationContext");

	}

	@Override
	public void replicate(BasicComponent component,
			ResourceContainer destination) {
		BasicComponent newComponent;
		RepositoryFactory fac = RepositoryFactoryImpl.init();
		newComponent = fac.createBasicComponent();

		newComponent.setImplementationComponentType(component
				.getImplementationComponentType());
		newComponent.setRepository_ProvidesComponentType(component
				.getRepository_ProvidesComponentType());

		this.add(newComponent, destination);

	}
}
