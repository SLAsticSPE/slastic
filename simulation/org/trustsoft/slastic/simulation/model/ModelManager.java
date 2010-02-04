package org.trustsoft.slastic.simulation.model;

import java.util.LinkedList;
import java.util.List;

import org.trustsoft.slastic.simulation.listeners.ReconfEventListener;
import org.trustsoft.slastic.simulation.model.hardware.controller.HardwareController;
import org.trustsoft.slastic.simulation.model.interfaces.IReconfPlanReceiver;
import org.trustsoft.slastic.simulation.model.mapping.AllocationController;
import org.trustsoft.slastic.simulation.model.reconfiguration.ReconfigurationController;
import org.trustsoft.slastic.simulation.model.software.repository.ComponentController;
import org.trustsoft.slastic.simulation.model.software.system.AssemblyController;

import reconfMM.ReconfigurationModel;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.system.System;
import desmoj.core.simulator.Model;

public class ModelManager implements IReconfPlanReceiver {

    private final AllocationController allocCont;
    private final HardwareController hwCont;
    private final ComponentController compCont;
    private final AssemblyController assemblyCont;
    private final ReconfigurationController reconfCont;
    private static ModelManager instance;
    private final List<SLAsticReconfigurationPlan> plans = new LinkedList<SLAsticReconfigurationPlan>();
    private final Model model;

    public ModelManager(final Repository repository, final System system,
            final ResourceEnvironment resources, final Allocation allocation,
            final ReconfigurationModel reconfModel, final Model model) {
        compCont = new ComponentController(repository, reconfModel, model);
        assemblyCont = new AssemblyController(system, model);
        hwCont = new HardwareController(resources, model);
        allocCont = new AllocationController(allocation, model);
        reconfCont = new ReconfigurationController(reconfModel, model);
        instance = this;
        this.model = model;
    }

    public AllocationController getAllocCont() {
        return allocCont;
    }

    public HardwareController getHwCont() {
        return hwCont;
    }

    public ComponentController getCompCont() {
        return compCont;
    }

    public AssemblyController getAssemblyCont() {
        return assemblyCont;
    }

    public static ModelManager getInstance() {
        return ModelManager.instance;
    }

    @Override
    public void reconfigure(final SLAsticReconfigurationPlan plan) {
        if (plans.isEmpty() && reconfCont.checkValidity(plan)) {
            reconfCont.schedulePlan(plan);
        }
    }

    public ReconfigurationController getReconfController() {
        return reconfCont;
    }

    public void addReconfEventListener(final ReconfEventListener listener) {
        reconfCont.addReconfEventListener(listener);
    }

    public Model getModel() {
        return model;
    }

    @Override
    public void addReconfigurationEventListener(
            final ReconfEventListener listener) {
        reconfCont.addReconfEventListener(listener);
    }

    @Override
    public void removeReconfigurationEventListener(
            final ReconfEventListener listener) {
        reconfCont.removeReconfEventListener(listener);
    }

    public void reconfigure(SLAsticReconfigurationPlan plan, ReconfEventListener listener) {
        // Von Andre: Hi Robert, ich schlage vor, an einen abgeschickten Plan
        // immer genau einen listener zu binden. Dann brauchen wir die
        // add/remove-Listener-Teile auch nicht im Interface
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
