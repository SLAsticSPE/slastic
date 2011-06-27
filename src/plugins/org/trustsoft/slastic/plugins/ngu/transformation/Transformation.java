package org.trustsoft.slastic.plugins.ngu.transformation;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;

import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IExtractor;
import org.eclipse.m2m.atl.core.IInjector;
import org.eclipse.m2m.atl.core.IModel;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFExtractor;
import org.eclipse.m2m.atl.core.emf.EMFInjector;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.core.launch.ILauncher;
import org.eclipse.m2m.atl.engine.emfvm.launch.EMFVMLauncher;

/**
 * @author Nicolas Günther
 * 
 */
public class Transformation {

	private static Transformation instance = null;
	private URL transformationResource;

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
		this.transformationResource = Transformation.class.getResource("transformation/slastic2pcm.asm");
	}

	/**
	 * 
	 * @param inputPath
	 * @param outputPath
	 */
	public void slastic2pcm(final String inputPath, final String outputPath) {
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
					"model/slastic.ecore");
			injector.inject(pcmMetaModel,
					"model/pcm.ecore");

			final ILauncher launcher = new EMFVMLauncher();
			launcher.initialize(Collections.<String, Object> emptyMap());

			final IModel slasticModel = modelFactory.newModel(slasticMetaModel);
			injector.inject(slasticModel, inputPath);

			final IModel pcmModel = modelFactory.newModel(pcmMetaModel);

			launcher.addInModel(slasticModel, "IN", "Slastic");
			launcher.addOutModel(pcmModel, "OUT", "Pcm");

			launcher.launch(ILauncher.RUN_MODE, null,
					Collections.<String, Object> emptyMap(),
					this.transformationResource.openStream());

			extractor.extract(pcmModel, outputPath);
		} catch (final ATLCoreException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}