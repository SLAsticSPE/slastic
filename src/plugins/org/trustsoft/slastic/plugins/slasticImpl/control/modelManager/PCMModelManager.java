package org.trustsoft.slastic.plugins.slasticImpl.control.modelManager;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;
import org.trustsoft.slastic.common.event.IObservationEvent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.control.components.modelManager.AbstractModelManagerComponent;
import org.trustsoft.slastic.plugins.pcm.PCMModelReader;
import org.trustsoft.slastic.plugins.pcm.control.PCMModelSet;

/**
 * The only ModelManager-Implementation that currently exists.
 * 
 * @author Lena Stoever
 * 
 */
public class PCMModelManager extends AbstractModelManagerComponent {

    private final Log log = LogFactory.getLog(PCMModelManager.class);
    private static final String PROP_NAME_PCM_REPOSITORY_FN = "pcmrepository_fn";
    private static final String PROP_NAME_PCM_SYSTEM_FN = "pcmsystem_fn";
    private static final String PROP_NAME_PCM_RESOURCEENV_FN = "pcmresourceenv_fn";
    private static final String PROP_NAME_PCM_ALLOCATION_FN = "pcmallocation_fn";
//    private static ModelManager instance;
//    protected volatile ReconfigurationModel reconfigurationModel;
    private volatile ConcurrentHashMap<String, AssemblyContext> assemblyContextsByAssemblyContextName =
            new ConcurrentHashMap<String, AssemblyContext>();
    private volatile ConcurrentHashMap<String, Collection<AllocationContext>> allocationContextsByAssemblyContextName =
            new ConcurrentHashMap<String, Collection<AllocationContext>>();
    private volatile ConcurrentHashMap<String, ResourceContainer> usedResourceContainersByresourceContainerName =
            new ConcurrentHashMap<String, ResourceContainer>();
    private volatile ConcurrentHashMap<String, ResourceContainer> unusedResourceContainersByresourceContainerName =
            new ConcurrentHashMap<String, ResourceContainer>();
    //map of types of components with their belonging instances within the reconfigurationModel
    //private volatile ConcurrentHashMap<BasicComponent, Vector<AllocationContext>> componentAllocationList;
    //list of not allocated models
    private volatile PCMModelSet pcmModel;

    public PCMModelManager() {
    }

    @Override
    public boolean init() {
        try {
            this.loadPCMModel();
            this.initTables();
            this.logTables();
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

    private void initTables() {
        /* Init assemblyContextsByAssemblyContextName */
        for (final AssemblyContext asmCtx : this.pcmModel.getPCMSystem().getChildComponentContexts_ComposedStructure()) {
            this.assemblyContextsByAssemblyContextName.put(asmCtx.getEntityName(), asmCtx);
        }

        /* Init unusedResourceContainersByresourceContainerName */
        for (final ResourceContainer resCont : this.pcmModel.getPCMResourceEnvironment().getResourceContainer_ResourceEnvironment()) {
            this.unusedResourceContainersByresourceContainerName.put(resCont.getEntityName(), resCont);
        }

        /* Init allocationContextsByAssemblyContextName */
        for (final AllocationContext allCtx : this.pcmModel.getPCMAllocation().getAllocationContexts_Allocation()) {
            /* Add allTx the asmCtx's list of allocations */
            final AssemblyContext asmCtx = allCtx.getAssemblyContext_AllocationContext();
            Collection<AllocationContext> allCtxForAssCtx =
                    this.allocationContextsByAssemblyContextName.get(asmCtx.getEntityName());
            if (allCtxForAssCtx == null) {
                allCtxForAssCtx = new ArrayList();
                this.allocationContextsByAssemblyContextName.put(asmCtx.getEntityName(), allCtxForAssCtx);
            }
            allCtxForAssCtx.add(allCtx);
            /* Add container to list of allocated containers */
            final ResourceContainer resCont = allCtx.getResourceContainer_AllocationContext();
            if (!this.usedResourceContainersByresourceContainerName.contains(resCont.getEntityName())) {
                this.usedResourceContainersByresourceContainerName.put(resCont.getEntityName(), resCont);
                this.unusedResourceContainersByresourceContainerName.remove(resCont.getEntityName());
            }
        }
    }

    private void logTables() {
        { /* Logging assemblyContextsByAssemblyContextName */
            log.info("assemblyContextsByAssemblyContextName: {{");
            for (Entry<String, AssemblyContext> asmCtxEntry : this.assemblyContextsByAssemblyContextName.entrySet()) {
                log.info(asmCtxEntry.getKey() + " => " + asmCtxEntry.getValue());
            }
            log.info("}}");
        }

        { /* Logging  allocationContextsByAssemblyContextName */
            log.info("allocationContextsByAssemblyContextName: {{");
            for (Entry<String, Collection<AllocationContext>> asmCtxAllocsEntry : this.allocationContextsByAssemblyContextName.entrySet()) {
                for (AllocationContext allocCtx : asmCtxAllocsEntry.getValue()) {
                    log.info(asmCtxAllocsEntry.getKey() + " => " + allocCtx);
                }
            }
            log.info("}}");
        }

        { /* Logging usedResourceContainersByresourceContainerName*/
            log.info("usedResourceContainersByresourceContainerName: {{");
            for (Entry<String, ResourceContainer> usedResourceContainer : this.usedResourceContainersByresourceContainerName.entrySet()) {
                log.info(usedResourceContainer.getKey() + " => " + usedResourceContainer);
            }
            log.info("}}");
        }

        { /* Logging UNusedResourceContainersByresourceContainerName*/
            log.info("unusedResourceContainersByresourceContainerName: {{");
            for (Entry<String, ResourceContainer> unusedResourceContainer : this.unusedResourceContainersByresourceContainerName.entrySet()) {
                log.info(unusedResourceContainer.getKey() + " => " + unusedResourceContainer);
            }
            log.info("}}");
        }
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

    public void terminate(boolean error) {
        // do nothing
    }

    public void newObservation(IObservationEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PCMModelSet getPcmModel() {
        return this.pcmModel;
    }

    public ConcurrentHashMap<String, Collection<AllocationContext>> getAllocationContextsByAssemblyContextName() {
        return allocationContextsByAssemblyContextName;
    }

    public ConcurrentHashMap<String, AssemblyContext> getAssemblyContextsByAssemblyContextName() {
        return assemblyContextsByAssemblyContextName;
    }

    public ConcurrentHashMap<String, ResourceContainer> getUnusedResourceContainersByresourceContainerName() {
        return unusedResourceContainersByresourceContainerName;
    }

    public ConcurrentHashMap<String, ResourceContainer> getUsedResourceContainersByresourceContainerName() {
        return usedResourceContainersByresourceContainerName;
    }
}
