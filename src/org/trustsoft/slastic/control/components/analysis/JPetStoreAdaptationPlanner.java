package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.control.plugins.daLena.analysis.SLAViolationEvent;
import org.trustsoft.slastic.control.systemModel.ModelManager;
import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;
import org.trustsoft.slastic.reconfigurationManager.ReconfigurationException;

import ReconfigurationPlanModel.ComponentRedeploymentOP;
import ReconfigurationPlanModel.ReconfigurationPlanModelFactory;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import ReconfigurationPlanModel.impl.ReconfigurationPlanModelFactoryImpl;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.repository.BasicComponent;

import reconfMM.ReconfigurationModel;

/**
 * This class is an implementation of the Adaptation Analyzer component of the
 * SLAstic.CONTROL framework. It belongs to the JPetStore example. So it created
 * a reconfiguration plan that consists of one ComponentRedeplyOP.
 * 
 * @author Lena Stoever
 * 
 */
public class JPetStoreAdaptationPlanner implements IAdaptationPlanner {
	private ISLAsticAnalysis ana;
	private IReconfigurationManager mng;

	@Override
	public void execute() {

	}

	@Override
	public void handle(ISLAsticAnalysisEvent event) {
		//this component can only handle Events of the type SLAViolationEvent
		if (event instanceof SLAViolationEvent) {
			SLAViolationEvent evt = (SLAViolationEvent) event;
			int serviceID = evt.getServiceID();
			ReconfigurationModel model = ModelManager.getInstance().getModel();
			BasicComponent comp = null;
			AssemblyContext context = null;
			
			//Identify Component type by running through the model and comparing the service ID with the given ID.
			synchronized (model) {
				for (int i = 0; i < model.getComponents().size(); i++) {
					for (int j = 0; j < model.getComponents().get(i)
							.getServices().size(); j++) {
						if (model.getComponents().get(i).getServices().get(j)
								.getServiceID() == serviceID) {
							comp = model.getComponents().get(i).getComponent();
							break;
						}
					}
				}
				//Identifying an instance of the component by running through the model and comparing it with the type
				for (int i = 0; i < model.getAllocation()
						.getAllocationContexts_Allocation().size(); i++) {
					if (model.getAllocation()
							.getAllocationContexts_Allocation().get(i)
							.getAssemblyContext_AllocationContext()
							.getEncapsulatedComponent_ChildComponentContext() == comp) {
						context = model.getAllocation()
								.getAllocationContexts_Allocation().get(i)
								.getAssemblyContext_AllocationContext();
					}
				}
			}
			
			//creating new plan
			ReconfigurationPlanModelFactory fac = ReconfigurationPlanModelFactoryImpl
					.init();
			SLAsticReconfigurationPlan plan = fac
					.createSLAsticReconfigurationPlan();
			ComponentRedeploymentOP op = fac.createComponentRedeploymentOP();
			op.setComponentImpl(comp);
			op.setTargetComponent(context);

			//add operation to the new plan
			plan.getOperations().add(op);
			try {
				//forward the plan to the Reconfiguration Manager that executes it.
				this.mng.doReconfiguration(plan);
			} catch (ReconfigurationException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void setAnalysis(ISLAsticAnalysis ana) {
		this.ana = ana;

	}

	@Override
	public void setReconfigurationManager(IReconfigurationManager manager) {
		this.mng = manager;

	}

	@Override
	public void terminate() {

	}

}
