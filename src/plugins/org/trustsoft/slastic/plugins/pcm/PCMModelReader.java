package org.trustsoft.slastic.plugins.pcm;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
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

    public static final PCMModelReader getInstance(){
        return INSTANCE;
    }

    public Repository readRepository (final String model_fn){
        return (Repository)this.readXMIModel(model_fn, de.uka.ipd.sdq.pcm.repository.RepositoryPackage.class.getName());
    }

    public System readSystem (final String model_fn){
        return (System)this.readXMIModel(model_fn, de.uka.ipd.sdq.pcm.system.SystemPackage.class.getName());
    }

    public Allocation readAllocation (final String model_fn){
        return (Allocation)this.readXMIModel(model_fn, de.uka.ipd.sdq.pcm.allocation.AllocationPackage.class.getName());
    }

    public ResourceEnvironment readResourceEnvironment (final String model_fn){
        return (ResourceEnvironment)this.readXMIModel(model_fn, de.uka.ipd.sdq.pcm.resourceenvironment.ResourceenvironmentPackage.class.getName());
    }

    private Object readXMIModel (final String model_fn, final String metaModelPackage){
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
