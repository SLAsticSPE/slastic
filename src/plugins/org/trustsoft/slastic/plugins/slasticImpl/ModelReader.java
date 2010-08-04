package org.trustsoft.slastic.plugins.slasticImpl;

import de.cau.se.slastic.metamodel.typeRepository.TypeRepository;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage;
import de.cau.se.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.openarchitectureware.emf.XmiReader;
import org.openarchitectureware.workflow.WorkflowContext;
import org.openarchitectureware.workflow.WorkflowContextDefaultImpl;
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

    private static final Log log = LogFactory.getLog(ModelReader.class);

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

    private static void saveModel(final EObject model, final String outputFn) throws IOException {
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, /* matches any extension */
                new XMIResourceFactoryImpl());
        Resource resource = resourceSet.createResource(URI.createFileURI(outputFn));
        resource.getContents().add(model);
        resource.save(null);

    }

    private static EObject loadModel(final EPackage ePackage, final String inputFn) throws IOException {
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, /* matches any extension */
                new XMIResourceFactoryImpl());
        Resource resource = resourceSet.createResource(URI.createFileURI(inputFn));
        resourceSet.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
// load resource
        resource.load(null);
        return resource.getContents().get(0);
    }

    public static void saveTypeRepositoryModel(final TypeRepository typeRepository, final String outputFn) throws IOException {
        log.info("Trying to save type repository model " + typeRepository + " to file " + outputFn);
        saveModel(typeRepository, outputFn);
    }

    public static TypeRepository loadTypeRepositoryModel(final String inputFn) throws IOException{
        return (TypeRepository) loadModel(TypeRepositoryPackage.eINSTANCE, inputFn);
    }
}
