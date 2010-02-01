package org.trustsoft.slastic.plugins.slasticImpl;

import org.openarchitectureware.emf.XmiReader;
import org.openarchitectureware.workflow.WorkflowContext;
import org.openarchitectureware.workflow.WorkflowContextDefaultImpl;
import org.openarchitectureware.workflow.WorkflowRunner;
import org.openarchitectureware.workflow.issues.IssuesImpl;
import org.openarchitectureware.workflow.monitor.NullProgressMonitor;
import org.trustsoft.slastic.control.sla.parser.ParserComponent;
import org.trustsoft.slastic.slasticqosannotations.SLAsticQoSAnnotations;
import org.trustsoft.slastic.slasticresourceenvironment.SLAsticResourceEnvironment;
import reconfMM.ReconfigurationModel;

/**
 *
 * @author Andre van Hoorn
 */
public class SLAsticModelReader {
    private static final SLAsticModelReader INSTANCE = new SLAsticModelReader();

    protected final WorkflowRunner runner = new WorkflowRunner();

    public static final SLAsticModelReader getInstance(){
        return INSTANCE;
    }

    public final slal.Model readSLAModel (final String model_fn){
        final String OUTPUT_SLOT_NAME = "theModel";
        ParserComponent slaModelParser = new ParserComponent();
        slaModelParser.setModelFile(model_fn);
        slaModelParser.setOutputSlot(OUTPUT_SLOT_NAME);
        WorkflowContext ctx = new WorkflowContextDefaultImpl();
        slaModelParser.invoke(ctx, new NullProgressMonitor(), new IssuesImpl());
        return (slal.Model)ctx.get(OUTPUT_SLOT_NAME);
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

    public final ReconfigurationModel readReconfigurationModel (final String model_fn){
        return (ReconfigurationModel) this.readXMIModel(model_fn, reconfMM.ReconfMMPackage.class.getName());
    }

    public final SLAsticResourceEnvironment readResourceEnvironmentModel (final String model_fn){
        return (SLAsticResourceEnvironment) this.readXMIModel(model_fn, org.trustsoft.slastic.slasticresourceenvironment.SlasticresourceenvironmentPackage.class.getName());
    }

    public final SLAsticQoSAnnotations readQoSAnnotationsModel (final String model_fn){
        return (SLAsticQoSAnnotations) this.readXMIModel(model_fn, org.trustsoft.slastic.slasticqosannotations.SlasticqosannotationsPackage.class.getName());
    }
}
