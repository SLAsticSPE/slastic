package org.trustsoft.slastic.plugins.pcm;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.system.System;
import org.openarchitectureware.emf.XmiReader;
import org.openarchitectureware.workflow.WorkflowContext;
import org.openarchitectureware.workflow.WorkflowContextDefaultImpl;
import org.openarchitectureware.workflow.issues.IssuesImpl;
import org.openarchitectureware.workflow.monitor.NullProgressMonitor;

/**
 *
 * @author Andre van Hoorn
 */
public class PCMModelReader {

    private static final PCMModelReader INSTANCE = new PCMModelReader();

    public static final PCMModelReader getInstance() {
        return INSTANCE;
    }

    public Repository readRepository(final String model_fn) {
        return (Repository) this.readXMIModel(model_fn, de.uka.ipd.sdq.pcm.repository.RepositoryPackage.class.getName());
    }

    public void storeRepository(final Repository repos, final String model_fn) {
        this.storeXMIModel(repos, model_fn);
    }

    public System readSystem(final String model_fn) {
        return (System) this.readXMIModel(model_fn, de.uka.ipd.sdq.pcm.system.SystemPackage.class.getName());
    }

    public void storeSystem(final System system, final String model_fn) {
        this.storeXMIModel(system, model_fn);
    }

    public Allocation readAllocation(final String model_fn) {
        return (Allocation) this.readXMIModel(model_fn, de.uka.ipd.sdq.pcm.allocation.AllocationPackage.class.getName());
    }

    public void storeAllocation(final Allocation allocation, final String model_fn) {
        this.storeXMIModel(allocation, model_fn);
    }

    public ResourceEnvironment readResourceEnvironment(final String model_fn) {
        return (ResourceEnvironment) this.readXMIModel(model_fn, de.uka.ipd.sdq.pcm.resourceenvironment.ResourceenvironmentPackage.class.getName());
    }

    public void storeResourceEnvironment(final ResourceEnvironment resEnv, final String model_fn) {
        this.storeXMIModel(resEnv, model_fn);
    }

    private void storeXMIModel(final Object model, final String model_fn) {
//        ResourceSet resourceEnvironmentResourceSet = new ResourceSetImpl();
//        String resourceEnvironmentResourceLocation = "out.resourceenvironment";
//        URI resourceEnvironmentURI = URI.createURI(resourceEnvironmentResourceLocation);
//        Resource resourceEnvironmentResource = resourceEnvironmentResourceSet.createResource(resourceEnvironmentURI);
//        resourceEnvironmentResource.getContents().add(
//                reconfigurationModel.getAllocation().getTargetResourceEnvironment_Allocation());
//
//        // Save System
//        Resource repositoryResource = null;
//        ResourceSet repositoryResourceSet = new ResourceSetImpl();
//        String repositoryLocation = "out.repository";
//        URI repositoryURI = URI.createURI(repositoryLocation);
//        repositoryResource = repositoryResourceSet.createResource(repositoryURI);
//        repositoryResource.getContents().add(repository);
//
//        repositoryResource.save(null);
    }

    private Object readXMIModel(final String model_fn, final String metaModelPackage) {
        final String OUTPUT_SLOT_NAME = "theModel";
        XmiReader r = new XmiReader();
        r.setModelFile(model_fn);
        r.setMetaModelPackage(metaModelPackage);
        r.setOutputSlot(OUTPUT_SLOT_NAME);
        WorkflowContext ctx = new WorkflowContextDefaultImpl();
        r.invoke(ctx, new NullProgressMonitor(), new IssuesImpl());


        return ctx.get(OUTPUT_SLOT_NAME);

    }
}
