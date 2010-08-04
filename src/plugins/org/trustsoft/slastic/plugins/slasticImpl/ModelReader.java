package org.trustsoft.slastic.plugins.slasticImpl;

import de.cau.se.slastic.metamodel.typeRepository.TypeRepository;
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
public class ModelReader {

    public final static slal.Model readSLAModel(final String model_fn) {
        final String OUTPUT_SLOT_NAME = "theModel";
        ParserComponent slaModelParser = new ParserComponent();
        slaModelParser.setModelFile(model_fn);
        slaModelParser.setOutputSlot(OUTPUT_SLOT_NAME);
        WorkflowContext ctx = new WorkflowContextDefaultImpl();
        slaModelParser.invoke(ctx, new NullProgressMonitor(), new IssuesImpl());
        return (slal.Model) ctx.get(OUTPUT_SLOT_NAME);
    }

    private static Object readXMIModel(final String model_fn, final String metaModelPackage) {
        final String OUTPUT_SLOT_NAME = "theModel";
        XmiReader r = new XmiReader();
        r.setModelFile(model_fn);
        r.setMetaModelPackage(metaModelPackage);
        r.setOutputSlot(OUTPUT_SLOT_NAME);
        WorkflowContext ctx = new WorkflowContextDefaultImpl();
        r.invoke(ctx, new NullProgressMonitor(), new IssuesImpl());
        return ctx.get(OUTPUT_SLOT_NAME);
    }

    public static ReconfigurationModel readOLDReconfigurationModel(final String model_fn) {
        return (ReconfigurationModel) readXMIModel(model_fn, reconfMM.ReconfMMPackage.class.getName());
    }

    public static SLAsticResourceEnvironment readOLDResourceEnvironmentModel(final String model_fn) {
        return (SLAsticResourceEnvironment) readXMIModel(model_fn, org.trustsoft.slastic.slasticresourceenvironment.SlasticresourceenvironmentPackage.class.getName());
    }

    public static SLAsticQoSAnnotations readOLDQoSAnnotationsModel(final String model_fn) {
        return (SLAsticQoSAnnotations) readXMIModel(model_fn, org.trustsoft.slastic.slasticqosannotations.SlasticqosannotationsPackage.class.getName());
    }

    public static TypeRepository readTypeRepositoryModel(final String model_fn) {
        return (TypeRepository) readXMIModel(model_fn, de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage.class.getName());
    }
}
