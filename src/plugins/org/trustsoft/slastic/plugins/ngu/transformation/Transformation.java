package org.trustsoft.slastic.plugins.ngu.transformation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IExtractor;
import org.eclipse.m2m.atl.core.IInjector;
import org.eclipse.m2m.atl.core.IModel;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFExtractor;
import org.eclipse.m2m.atl.core.emf.EMFInjector;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFReferenceModel;
import org.eclipse.m2m.atl.core.launch.ILauncher;
import org.eclipse.m2m.atl.engine.emfvm.launch.EMFVMLauncher;

import de.cau.se.slastic.metamodel.core.SLAsticModel;

/**
 * @author Nicolas Günther
 * 
 */
public class Transformation {

	private static final Log log = LogFactory.getLog(Transformation.class);
	private static Transformation instance = null;
	private URL transformationResource;
	private URL slasticMetaModelResource;
	private URL pcmMetaModelResource;

	/**
	 * 
	 * @return
	 */
	static public Transformation getInstance() {
		if (Transformation.instance == null) {
			Transformation.instance = new Transformation();
		}
		return Transformation.instance;
	}

	private void createResources() {
		this.transformationResource = Transformation.class.getResource("/transformation/slastic2pcm.asm");
		this.slasticMetaModelResource = Transformation.class.getResource("/model/slastic.ecore");
		this.pcmMetaModelResource = Transformation.class.getResource("/model/pcm.ecore");
		Transformation.log.info(this.transformationResource);
	}

	/**
	 * 
	 * @param inputPath
	 * @param outputPath
	 */
	public void slastic2pcm(final File inputFile) {
		IModel slasticModel = null;
		try {
			this.createResources();
			final ModelFactory modelFactory = new EMFModelFactory();

			final IReferenceModel slasticMetaModel = modelFactory
					.newReferenceModel();
			final IReferenceModel pcmMetaModel = modelFactory
					.newReferenceModel();

			final IInjector injector = new EMFInjector();
			final IExtractor extractor = new EMFExtractor();

			injector.inject(slasticMetaModel,
					this.slasticMetaModelResource.toString());
			injector.inject(pcmMetaModel,
					this.pcmMetaModelResource.toString());

			final ILauncher launcher = new EMFVMLauncher();
			launcher.initialize(Collections.<String, Object> emptyMap());

			slasticModel = modelFactory.newModel(slasticMetaModel);
			injector.inject(slasticModel, inputFile.getPath());

			final IModel pcmResourceTypeModel = modelFactory.newModel(pcmMetaModel);
			final IModel pcmRepositoryModel = modelFactory.newModel(pcmMetaModel);
			final IModel pcmResourceEnvironmentModel = modelFactory.newModel(pcmMetaModel);
			final IModel pcmAllocationModel = modelFactory.newModel(pcmMetaModel);
			final IModel pcmSystemModel = modelFactory.newModel(pcmMetaModel);

			launcher.addInModel(slasticModel, "IN", "Slastic");
			launcher.addOutModel(pcmResourceTypeModel, "RESOURCETYPE", "Pcm");
			launcher.addOutModel(pcmRepositoryModel, "REPOSITORY", "Pcm");
			launcher.addOutModel(pcmResourceEnvironmentModel, "RESOURCEENVIRONMENT", "Pcm");
			launcher.addOutModel(pcmAllocationModel, "ALLOCATION", "Pcm");
			launcher.addOutModel(pcmSystemModel, "SYSTEM", "Pcm");

			final Map<String,Object> launchOptions = new HashMap<String, Object>();
			launchOptions.put("allowInterModelReferences", "true");
			
			launcher.launch(ILauncher.RUN_MODE, null,launchOptions,this.transformationResource.openStream());

			//Get filename without extension.
			String filename = inputFile.getName().replaceFirst("[.][^.]+$", "");
			filename = new StringBuilder(inputFile.getParent()).append('/').append(filename).toString();
			
			
			final Map<String, Object> extractOptions = new HashMap<String, Object>();
			extractOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
			
			extractor.extract(pcmResourceTypeModel, filename + ".resourcetype", extractOptions);
			extractor.extract(pcmRepositoryModel, filename + ".repository", extractOptions);
			extractor.extract(pcmResourceEnvironmentModel, filename + ".resourceenvironment", extractOptions);
			extractor.extract(pcmAllocationModel, filename + ".allocation", extractOptions);
			extractor.extract(pcmSystemModel, filename + ".system", extractOptions);
		} catch (final ATLCoreException e) {
			Transformation.log.error(String.format("Error transforming slastic model instance [%s] to pcm.", 
					slasticModel.toString()), e);
		} catch (final IOException e) {
			Transformation.log.error(String.format("IO Error processing input File [%s].", inputFile.toString()), e);
		}
	}
	
	public void slastic2pcm(final SLAsticModel inputModel){
		EMFModel slasticModel = null;
		try {
			this.createResources();
			final ModelFactory modelFactory = new EMFModelFactory();

			final EMFReferenceModel slasticMetaModel = (EMFReferenceModel) modelFactory
					.newReferenceModel();
			final IReferenceModel pcmMetaModel = modelFactory
					.newReferenceModel();

			final EMFInjector injector = new EMFInjector();
			final IExtractor extractor = new EMFExtractor();

			injector.inject(slasticMetaModel,
					this.slasticMetaModelResource.toString());
			injector.inject(pcmMetaModel,
					this.pcmMetaModelResource.toString());

			final ILauncher launcher = new EMFVMLauncher();
			launcher.initialize(Collections.<String, Object> emptyMap());

			slasticModel = (EMFModel) modelFactory.newModel(slasticMetaModel);
			final Resource inputModelResource = inputModel.eResource();
			Transformation.log.info(inputModelResource);
			
			injector.inject(slasticModel, inputModelResource.getURI().toString());
			
			final IModel pcmResourceTypeModel = modelFactory.newModel(pcmMetaModel);
			final IModel pcmRepositoryModel = modelFactory.newModel(pcmMetaModel);
			final IModel pcmResourceEnvironmentModel = modelFactory.newModel(pcmMetaModel);
			final IModel pcmAllocationModel = modelFactory.newModel(pcmMetaModel);
			final IModel pcmSystemModel = modelFactory.newModel(pcmMetaModel);

			launcher.addInModel(slasticModel, "IN", "Slastic");
			launcher.addOutModel(pcmResourceTypeModel, "RESOURCETYPE", "Pcm");
			launcher.addOutModel(pcmRepositoryModel, "REPOSITORY", "Pcm");
			launcher.addOutModel(pcmResourceEnvironmentModel, "RESOURCEENVIRONMENT", "Pcm");
			launcher.addOutModel(pcmAllocationModel, "ALLOCATION", "Pcm");
			launcher.addOutModel(pcmSystemModel, "SYSTEM", "Pcm");

			final Map<String,Object> launchOptions = new HashMap<String, Object>();
			launchOptions.put("allowInterModelReferences", "true");
			
			launcher.launch(ILauncher.RUN_MODE, null,launchOptions,this.transformationResource.openStream());

			//Get filename without extension.
			//String filename = inputFile.getName().replaceFirst("[.][^.]+$", "");
			//filename = new StringBuilder(inputFile.getParent()).append('/').append(filename).toString();
			final String filename = "test";
			
			final Map<String, Object> extractOptions = new HashMap<String, Object>();
			extractOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
			
			extractor.extract(pcmResourceTypeModel, filename + ".resourcetype", extractOptions);
			extractor.extract(pcmRepositoryModel, filename + ".repository", extractOptions);
			extractor.extract(pcmResourceEnvironmentModel, filename + ".resourceenvironment", extractOptions);
			extractor.extract(pcmAllocationModel, filename + ".allocation", extractOptions);
			extractor.extract(pcmSystemModel, filename + ".system", extractOptions);
		} catch (final ATLCoreException e) {
			Transformation.log.error(String.format("Error transforming slastic model instance [%s] to pcm.", 
					slasticModel.toString()), e);
		} catch (final IOException e) {
			//Transformation.log.error(String.format("IO Error processing input File [%s].", inputFile.toString()), e);
		}
	}
}