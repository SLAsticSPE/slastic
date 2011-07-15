package org.trustsoft.slastic.plugins.ngu.transformation;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IExtractor;
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
import org.trustsoft.slastic.common.IComponentContext;

import de.cau.se.slastic.metamodel.core.SystemModel;

/**
 * @author Nicolas Günther
 * 
 */
public class SlasticToPcmTranformation {

	private static final Log log = LogFactory.getLog(SlasticToPcmTranformation.class);
	private URL transformationResource;
	private URL slasticMetaModelResource;
	private URL pcmMetaModelResource;
	private IModel pcmResourceTypeModel;
	private IModel pcmRepositoryModel;
	private IModel pcmResourceEnvironmentModel;
	private IModel pcmAllocationModel;
	private IModel pcmSystemModel;

	public IModel getPcmResourceTypeModel() {
		return this.pcmResourceTypeModel;
	}

	public IModel getPcmRepositoryModel() {
		return this.pcmRepositoryModel;
	}

	public IModel getPcmResourceEnvironmentModel() {
		return this.pcmResourceEnvironmentModel;
	}

	public IModel getPcmAllocationModel() {
		return this.pcmAllocationModel;
	}

	public IModel getPcmSystemModel() {
		return this.pcmSystemModel;
	}

	private void createResources() {
		this.transformationResource = SlasticToPcmTranformation.class.getResource("/transformation/slastic2pcm.asm");
		this.slasticMetaModelResource = SlasticToPcmTranformation.class.getResource("/model/slastic.ecore");
		this.pcmMetaModelResource = SlasticToPcmTranformation.class.getResource("/model/pcm.ecore");
	}

	public void transform(final SystemModel inputModel) {
		this.transform(inputModel.eResource().getURI().toString());
	}

	public void transform(final String inputPath) {
		EMFModel slasticModel = null;
		try {
			this.createResources();
			final ModelFactory modelFactory = new EMFModelFactory();

			final EMFReferenceModel slasticMetaModel = (EMFReferenceModel) modelFactory
					.newReferenceModel();
			final IReferenceModel pcmMetaModel = modelFactory
					.newReferenceModel();

			final EMFInjector injector = new EMFInjector();

			injector.inject(slasticMetaModel,
					this.slasticMetaModelResource.toString());
			injector.inject(pcmMetaModel,
					this.pcmMetaModelResource.toString());

			final ILauncher launcher = new EMFVMLauncher();
			launcher.initialize(Collections.<String, Object> emptyMap());

			slasticModel = (EMFModel) modelFactory.newModel(slasticMetaModel);

			injector.inject(slasticModel, inputPath);

			this.pcmResourceTypeModel = modelFactory.newModel(pcmMetaModel);
			this.pcmRepositoryModel = modelFactory.newModel(pcmMetaModel);
			this.pcmResourceEnvironmentModel = modelFactory.newModel(pcmMetaModel);
			this.pcmAllocationModel = modelFactory.newModel(pcmMetaModel);
			this.pcmSystemModel = modelFactory.newModel(pcmMetaModel);

			launcher.addInModel(slasticModel, "IN", "Slastic");
			launcher.addOutModel(this.pcmResourceTypeModel, "RESOURCETYPE", "Pcm");
			launcher.addOutModel(this.pcmRepositoryModel, "REPOSITORY", "Pcm");
			launcher.addOutModel(this.pcmResourceEnvironmentModel, "RESOURCEENVIRONMENT", "Pcm");
			launcher.addOutModel(this.pcmAllocationModel, "ALLOCATION", "Pcm");
			launcher.addOutModel(this.pcmSystemModel, "SYSTEM", "Pcm");

			final Map<String, Object> launchOptions = new HashMap<String, Object>();
			launchOptions.put("allowInterModelReferences", "true");

			SlasticToPcmTranformation.log.info(String.format("Launching transformation of slastic model instance [%s] to pcm.", slasticModel.toString()));
			launcher.launch(ILauncher.RUN_MODE, null, launchOptions, this.transformationResource.openStream());
		} catch (final ATLCoreException e) {
			SlasticToPcmTranformation.log.error(String.format("Error transforming slastic model instance [%s] to pcm.",
					slasticModel.toString()), e);
		} catch (final IOException e) {
			SlasticToPcmTranformation.log.error(
					String.format("Cannot open stream for transformation resource [%s].",
							this.transformationResource.toString()), e);
		}
	}

	public void extractPcmModel(final IComponentContext componentContext, final String outputFilePrefix) {
		final IExtractor extractor = new EMFExtractor();

		final Map<String, Object> extractOptions = new HashMap<String, Object>();
		extractOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");

		final IComponentContext outputContext = componentContext.createSubcontext(outputFilePrefix);
		this.extractPcmModel(extractor, this.pcmResourceTypeModel,
				outputContext.createFileInContextDir(outputFilePrefix + ".resourcetype").getPath(), extractOptions);
		this.extractPcmModel(extractor, this.pcmRepositoryModel,
				outputContext.createFileInContextDir(outputFilePrefix + ".repository").getPath(), extractOptions);
		this.extractPcmModel(extractor, this.pcmResourceEnvironmentModel,
				outputContext.createFileInContextDir(outputFilePrefix + ".resourceenvironment").getPath(),
				extractOptions);
		this.extractPcmModel(extractor, this.pcmAllocationModel,
				outputContext.createFileInContextDir(outputFilePrefix + ".allocation").getPath(), extractOptions);
		this.extractPcmModel(extractor, this.pcmSystemModel,
				outputContext.createFileInContextDir(outputFilePrefix + ".system").getPath(), extractOptions);
	}

	private void extractPcmModel(final IExtractor extractor, final IModel model, final String outputPath,
			final Map<String, Object> options) {
		try {
			SlasticToPcmTranformation.log.info(String.format("Trying to extract model [%s] to file [%s].",
					model.toString(), outputPath));
			extractor.extract(model, outputPath, options);
		} catch (final ATLCoreException e) {
			SlasticToPcmTranformation.log
					.error(
							String.format("Extraction of pcm model [%s] to file [%s] failed.", model.toString(),
									outputPath), e);
		}
	}
}