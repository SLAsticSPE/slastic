package org.trustsoft.slastic.control.systemModel;

import java.util.concurrent.ConcurrentSkipListSet;

import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;
import org.trustsoft.slastic.control.analysis.SLACheckerGUI;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;

import reconfMM.ReconfigurationModel;
import reconfMM.Service;
import ReconfigurationPlanModel.ComponentDeReplicationOP;
import ReconfigurationPlanModel.ComponentMigrationOP;
import ReconfigurationPlanModel.ComponentReplicationOP;
import ReconfigurationPlanModel.NodeAllocationOP;
import ReconfigurationPlanModel.NodeDeAllocationOP;
import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.allocation.AllocationFactory;
import de.uka.ipd.sdq.pcm.allocation.impl.AllocationFactoryImpl;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.core.composition.CompositionFactory;
import de.uka.ipd.sdq.pcm.core.composition.impl.CompositionFactoryImpl;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;

public class ModelManager extends AbstractModelManager {

	private final Log log = LogFactory.getLog(ModelManager.class);
	private static ModelManager instance;
	boolean firstupdate = false;

	private ModelManager() {

	}

	private ModelManager(ReconfigurationModel reconfigurationModel) {
		model = reconfigurationModel;
		this.initSet();
	}

	public void initModel(ReconfigurationModel m) {
		model = m;
		this.initSet();
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
                synchronized(this.model){
		for (int i = 0; i < model.getComponents().size(); i++) {
			for (int k = 0; k < model.getComponents().get(i).getServices()
					.size(); k++) {
				Service service = model.getComponents().get(i).getServices()
						.get(k);
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
		if (updated) {

		}
		updated = false;
	}

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
		AllocationFactory fac = AllocationFactoryImpl.init();
		AllocationContext newContext = fac.createAllocationContext();
		newContext.setAssemblyContext_AllocationContext(component
				.getAssemblyContext_AllocationContext());
		newContext.setResourceContainer_AllocationContext(newServer);
		newContext.setEntityName(component.getEntityName());
		newContext.setId(component.getId());
		this.add(component);
		this.remove(component);
	}

	@Override
	protected void replicate(AllocationContext component, /* AssemblyContext */
			ResourceContainer destination) {
		AllocationFactory fac = AllocationFactoryImpl.init();
		AllocationContext newAllocationContext = fac.createAllocationContext();

		CompositionFactory compFac = CompositionFactoryImpl.init();
		AssemblyContext newAssemblyContext = compFac.createAssemblyContext();

		newAssemblyContext
				.setEncapsulatedComponent_ChildComponentContext(component
						.getAssemblyContext_AllocationContext()
						.getEncapsulatedComponent_ChildComponentContext());

		newAllocationContext
				.setAssemblyContext_AllocationContext(newAssemblyContext);
		newAllocationContext.setResourceContainer_AllocationContext(destination);

	}

	protected void allocate(ResourceContainer container) {
		AllocationFactory fac = AllocationFactoryImpl.init();
		AllocationContext context = fac.createAllocationContext();

		context.setResourceContainer_AllocationContext(container);
		model.getAllocation().getAllocationContexts_Allocation().add(context);
	}

	protected void deallocate(ResourceContainer container) {
		for (int i = 0; i < model.getAllocation()
				.getAllocationContexts_Allocation().size(); i++) {
			if (model.getAllocation().getAllocationContexts_Allocation().get(i)
					.getResourceContainer_AllocationContext() == container) {
				AllocationContext context = model.getAllocation()
						.getAllocationContexts_Allocation().get(i);
				model.getAllocation().getAllocationContexts_Allocation()
						.remove(context);
			}
		}
	}

	protected void dereplicate(AllocationContext component) {
		this.remove(component);
	}

	public boolean doReconfiguration(ReconfigurationPlanModel.SLAsticReconfigurationPlan plan) {
                EList<SLAsticReconfigurationOpType> operations = plan.getOperations();
                synchronized(this.model){
                for (int i = 0; i < operations.size(); i++) {
			SLAsticReconfigurationOpType op = operations.get(i);
			Class opClass = op.getClass();
			if (opClass == ComponentDeReplicationOP.class) {
				AllocationContext comp = ((ComponentDeReplicationOP)op).getComponent();
				this.dereplicate(comp);
			} else if (opClass == ComponentMigrationOP.class) {
				AllocationContext comp = ((ComponentMigrationOP) op)
						.getComponent();
				ResourceContainer destination = ((ComponentMigrationOP) op)
						.getDestination();
				this.migrate(comp, destination);
			} else if (opClass == ComponentReplicationOP.class) {
				AllocationContext comp = ((ComponentReplicationOP) op)
						.getComponent();
				ResourceContainer destination = ((ComponentReplicationOP) op)
						.getDestination();
					this.replicate(comp, destination);
			} else if (opClass == NodeAllocationOP.class) {
				ResourceContainer container = ((NodeAllocationOP) op).getNode();
				this.allocate(container);
			} else if (opClass == NodeDeAllocationOP.class) {
				ResourceContainer container = ((NodeDeAllocationOP) op)
						.getNode();
				this.deallocate(container);
			} else {
				log.error("Operationtype " + plan.getClass().getCanonicalName()
						+ " not supported!");
				return false;
			}
		}
                }
		return true;
	}
}
