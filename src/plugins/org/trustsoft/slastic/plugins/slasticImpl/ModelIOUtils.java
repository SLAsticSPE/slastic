package org.trustsoft.slastic.plugins.slasticImpl;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.openarchitectureware.workflow.WorkflowContext;
import org.openarchitectureware.workflow.WorkflowContextDefaultImpl;
import org.openarchitectureware.workflow.issues.IssuesImpl;
import org.openarchitectureware.workflow.monitor.NullProgressMonitor;
import org.trustsoft.slastic.control.sla.parser.ParserComponent;
import org.trustsoft.slastic.slasticqosannotations.SLAsticQoSAnnotations;
import org.trustsoft.slastic.slasticresourceenvironment.SLAsticResourceEnvironment;

import reconfMM.ReconfigurationModel;
import de.cau.se.slastic.metamodel.core.CorePackage;
import de.cau.se.slastic.metamodel.core.SystemModel;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryModel;

/**
 *
 * @author Andre van Hoorn
 */
public class ModelIOUtils {

    private static final Log log = LogFactory.getLog(ModelIOUtils.class);

    public final static slal.Model readSLAModel(final String model_fn) {
        final String OUTPUT_SLOT_NAME = "theModel";
        final ParserComponent slaModelParser = new ParserComponent();
        slaModelParser.setModelFile(model_fn);
        slaModelParser.setOutputSlot(OUTPUT_SLOT_NAME);
        final WorkflowContext ctx = new WorkflowContextDefaultImpl();
        slaModelParser.invoke(ctx, new NullProgressMonitor(), new IssuesImpl());
        return (slal.Model) ctx.get(OUTPUT_SLOT_NAME);
    }

    public static ReconfigurationModel readOLDReconfigurationModel(final String model_fn) throws IOException {
        //return (ReconfigurationModel) readXMIModel(model_fn, reconfMM.ReconfMMPackage.class.getName());
        return (ReconfigurationModel) ModelIOUtils.loadModel(reconfMM.ReconfMMPackage.eINSTANCE, model_fn);
    }

    public static SLAsticResourceEnvironment readOLDResourceEnvironmentModel(final String model_fn) throws IOException {
        //return (SLAsticResourceEnvironment) readXMIModel(model_fn, org.trustsoft.slastic.slasticresourceenvironment.SlasticresourceenvironmentPackage.class.getName());
        return (SLAsticResourceEnvironment) ModelIOUtils.loadModel(org.trustsoft.slastic.slasticresourceenvironment.SlasticresourceenvironmentPackage.eINSTANCE, model_fn);
    }

    public static SLAsticQoSAnnotations readOLDQoSAnnotationsModel(final String model_fn) throws IOException {
        //return (SLAsticQoSAnnotations) readXMIModel(model_fn, org.trustsoft.slastic.slasticqosannotations.SlasticqosannotationsPackage.class.getName());
        return (SLAsticQoSAnnotations) ModelIOUtils.loadModel(org.trustsoft.slastic.slasticqosannotations.SlasticqosannotationsPackage.eINSTANCE, model_fn);
    }

    public static TypeRepositoryModel readTypeRepositoryModel(final String model_fn) throws IOException {
        //return (TypeRepositoryModel) readXMIModel(model_fn, de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage.class.getName());
        return (TypeRepositoryModel) ModelIOUtils.loadModel(de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage.eINSTANCE, model_fn);
    }

    protected static void saveModel(final EObject model, final String outputFn) throws IOException {
        final ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, /* matches any extension */
                new XMIResourceFactoryImpl());
        final Resource resource = resourceSet.createResource(URI.createFileURI(outputFn));
        resource.getContents().add(model);
        resource.save(null);

    }

    protected static EObject loadModel(final EPackage ePackage, final String inputFn, final ResourceSet resourceSet) throws IOException {
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, /* matches any extension */
                new XMIResourceFactoryImpl());
        final Resource resource = resourceSet.createResource(URI.createFileURI(inputFn));
        resourceSet.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
// load resource
        resource.load(null);
        return resource.getContents().get(0);
    }
    protected static EObject loadModel(final EPackage ePackage, final String inputFn) throws IOException {
    	final ResourceSet resourceSet = new ResourceSetImpl();
    	resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, /* matches any extension */
                new XMIResourceFactoryImpl());
        final Resource resource = resourceSet.createResource(URI.createFileURI(inputFn));
        resourceSet.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
// load resource
        resource.load(null);
        return resource.getContents().get(0);
    }

    public static void saveSystemModel(final SystemModel systemModel, final String outputFn) throws IOException {
        ModelIOUtils.log.info("Trying to save system model " + systemModel + " to file " + outputFn);
        ModelIOUtils.saveModel(systemModel, outputFn);
    }

    public static SystemModel loadSystemModel(final String inputFn) throws IOException {
        return (SystemModel) ModelIOUtils.loadModel(CorePackage.eINSTANCE, inputFn);
    }
}
