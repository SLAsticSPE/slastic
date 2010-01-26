package org.trustsoft.slastic.plugins.slachecker.control.analysis;

import org.trustsoft.slastic.plugins.pcmreconfiguration.control.modelManager.ModelManager;
import org.trustsoft.slastic.reconfiguration.SLAsticReconfigurationException;

import ReconfigurationPlanModel.ComponentRedeploymentOP;
import ReconfigurationPlanModel.ReconfigurationPlanModelFactory;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import ReconfigurationPlanModel.impl.ReconfigurationPlanModelFactoryImpl;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.repository.BasicComponent;

import org.trustsoft.slastic.control.components.analysis.AbstractAdaptationPlanner;
import org.trustsoft.slastic.control.components.events.ISLAsticEvent;
import reconfMM.ReconfigurationModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is an implementation of the Adaptation Analyzer component of the
 * SLAstic.CONTROL framework. It belongs to the JPetStore example. So it created
 * a reconfiguration plan that consists of one ComponentRedeplyOP.
 * 
 * @author Lena Stoever
 * 
 */
public class JPetStoreAdaptationPlanner extends AbstractAdaptationPlanner {

    private static final Log log = LogFactory.getLog(AbstractAdaptationPlanner.class);

    public void init(String initString) throws IllegalArgumentException {
        super.initVarsFromInitString(initString);
        // we don't expect init properties so far, so just return.
    }

    @Override
    public void handleSLAsticEvent(ISLAsticEvent event) {
        //this component can only handle Events of the type SLAViolationEvent
        if (event instanceof SLAViolationEvent) {
            SLAViolationEvent evt = (SLAViolationEvent) event;
            int serviceID = evt.getServiceID();
            ModelManager modelManager = (ModelManager) this.getParentAnalysisComponent().getParentControlComponent().getModelManager();
            ReconfigurationModel model = modelManager.getModel();
            BasicComponent comp = null;
            AssemblyContext context = null;

            //Identify Component type by running through the model and comparing the service ID with the given ID.
            synchronized (model) {
                for (int i = 0; i < model.getComponents().size(); i++) {
                    for (int j = 0; j < model.getComponents().get(i).getServices().size(); j++) {
                        if (model.getComponents().get(i).getServices().get(j).getServiceID() == serviceID) {
                            comp = model.getComponents().get(i).getComponent();
                            break;
                        }
                    }
                }
                //Identifying an instance of the component by running through the model and comparing it with the type
                for (int i = 0; i < model.getAllocation().getAllocationContexts_Allocation().size(); i++) {
                    if (model.getAllocation().getAllocationContexts_Allocation().get(i).getAssemblyContext_AllocationContext().getEncapsulatedComponent_ChildComponentContext() == comp) {
                        context = model.getAllocation().getAllocationContexts_Allocation().get(i).getAssemblyContext_AllocationContext();
                    }
                }
            }

            //creating new plan
            ReconfigurationPlanModelFactory fac = ReconfigurationPlanModelFactoryImpl.init();
            SLAsticReconfigurationPlan plan = fac.createSLAsticReconfigurationPlan();
            ComponentRedeploymentOP op = fac.createComponentRedeploymentOP();
            op.setComponentImpl(comp);
            op.setTargetComponent(context);

            //add operation to the new plan
            plan.getOperations().add(op);
            try {
                //forward the plan to the Reconfiguration Manager that executes it.
                this.getReconfigurationManager().doReconfiguration(plan);
            } catch (SLAsticReconfigurationException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean execute() {
        return true;
    }

    public void terminate() {
    }
}
