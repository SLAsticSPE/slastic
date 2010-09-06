package org.trustsoft.slastic.plugins.pcm.control.modelManager;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import java.util.Collection;
import org.trustsoft.slastic.common.event.IObservationEvent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.control.components.modelManager.AbstractModelManagerComponent;
import org.trustsoft.slastic.plugins.pcm.PCMModelReader;
import org.trustsoft.slastic.plugins.pcm.control.PCMModelSet;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class PCMModelManager extends AbstractModelManagerComponent {

    private final Log log = LogFactory.getLog(PCMModelManager.class);
    private static final String PROP_NAME_PCM_REPOSITORY_FN = "pcmrepository_fn";
    private static final String PROP_NAME_PCM_SYSTEM_FN = "pcmsystem_fn";
    private static final String PROP_NAME_PCM_RESOURCEENV_FN = "pcmresourceenv_fn";
    private static final String PROP_NAME_PCM_ALLOCATION_FN = "pcmallocation_fn";

    protected volatile PCMModelSet pcmModel;

    public PCMModelManager() {
    }

    @Override
    public boolean init() {
        try {
            this.loadPCMModel();
            return true;
        } catch (IOException ex) {
            log.error("Failed to load reconfiguration model", ex);
            return false;
        }
    }

    private void loadPCMModel() throws IOException {
        final String pcmRespositoryModel_fn = this.getInitProperty(PROP_NAME_PCM_REPOSITORY_FN);
        final String pcmSystemModel_fn = this.getInitProperty(PROP_NAME_PCM_SYSTEM_FN);
        final String pcmResourceEnvironmentModel_fn = this.getInitProperty(PROP_NAME_PCM_RESOURCEENV_FN);
        final String pcmAllocationModel_fn = this.getInitProperty(PROP_NAME_PCM_ALLOCATION_FN);
        this.pcmModel = PCMModelReader.readPCMModel(pcmRespositoryModel_fn, pcmSystemModel_fn, pcmResourceEnvironmentModel_fn, pcmAllocationModel_fn);
    }

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public void doReconfiguration(SLAsticReconfigurationPlan plan) {
        // TODO Auto-generated method stub
    }

    @Override
    public void handleEvent(IEvent ev) {
    }

    @Override
    public void terminate(boolean error) {
        // do nothing
    }

    @Override
    public void newObservation(IObservationEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PCMModelSet getPcmModel() {
        return this.pcmModel;
    }
}
