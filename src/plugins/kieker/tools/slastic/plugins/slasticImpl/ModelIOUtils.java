/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package kieker.tools.slastic.plugins.slasticImpl;

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
import org.trustsoft.slastic.slasticqosannotations.SLAsticQoSAnnotations;
import org.trustsoft.slastic.slasticresourceenvironment.SLAsticResourceEnvironment;

import reconfMM.ReconfigurationModel;

import kieker.tools.slastic.metamodel.core.CorePackage;
import kieker.tools.slastic.metamodel.core.SystemModel;
import kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryModel;
import kieker.tools.slastic.metamodel.usage.UsageModel;
import kieker.tools.slastic.metamodel.usage.UsagePackage;

/**
 * 
 * @author Andre van Hoorn
 */
public class ModelIOUtils {

	private static final Log LOG = LogFactory.getLog(ModelIOUtils.class);

	public static slal.Model readSLAModel(final String model_fn) {
		throw new UnsupportedOperationException("Not supported any more");
		// final String OUTPUT_SLOT_NAME = "theModel";
		// final ParserComponent slaModelParser = new ParserComponent();
		// slaModelParser.setModelFile(model_fn);
		// slaModelParser.setOutputSlot(OUTPUT_SLOT_NAME);
		// final WorkflowContext ctx = new WorkflowContextDefaultImpl();
		// slaModelParser.invoke(ctx, new NullProgressMonitor(), new
		// IssuesImpl());
		// return (slal.Model) ctx.get(OUTPUT_SLOT_NAME);
	}

	public static ReconfigurationModel readOLDReconfigurationModel(final String model_fn) throws IOException {
		// return (ReconfigurationModel) readXMIModel(model_fn,
		// reconfMM.ReconfMMPackage.class.getName());
		return (ReconfigurationModel) ModelIOUtils.loadModels(new EPackage[] { reconfMM.ReconfMMPackage.eINSTANCE }, new String[] { model_fn })[0];
	}

	public static SLAsticResourceEnvironment readOLDResourceEnvironmentModel(final String model_fn) throws IOException {
		// return (SLAsticResourceEnvironment) readXMIModel(model_fn,
		// kieker.tools.slastic.slasticresourceenvironment.SlasticresourceenvironmentPackage.class.getName());
		return (SLAsticResourceEnvironment) ModelIOUtils
				.loadModels(
						new EPackage[] { org.trustsoft.slastic.slasticresourceenvironment.SlasticresourceenvironmentPackage.eINSTANCE }, new String[] { model_fn })[0];
	}

	public static SLAsticQoSAnnotations readOLDQoSAnnotationsModel(final String model_fn) throws IOException {
		// return (SLAsticQoSAnnotations) readXMIModel(model_fn,
		// kieker.tools.slastic.slasticqosannotations.SlasticqosannotationsPackage.class.getName());
		return (SLAsticQoSAnnotations) ModelIOUtils.loadModels(
				new EPackage[] { org.trustsoft.slastic.slasticqosannotations.SlasticqosannotationsPackage.eINSTANCE }, new String[] { model_fn })[0];
	}

	public static TypeRepositoryModel readTypeRepositoryModel(final String model_fn) throws IOException {
		// return (TypeRepositoryModel) readXMIModel(model_fn,
		// kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryPackage.class.getName());
		return (TypeRepositoryModel) ModelIOUtils.loadModels(
				new EPackage[] { kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryPackage.eINSTANCE }, new String[] { model_fn })[0];
	}

	/**
	 * Saves all models to the corresponding output files using the same {@link ResourceSet}.
	 * 
	 * @param models
	 * @param outputFns
	 * @throws IOException
	 */
	public static void saveModels(final EObject[] models, final String[] outputFns) throws IOException {
		if (models.length != outputFns.length) {
			LOG.error("Number of models and output file name must be equal. " + "Found: " + models.length + " <> " + outputFns.length);
			return;
		}

		final ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION, /* matches any extension */new XMIResourceFactoryImpl());

		for (int i = 0; i < models.length; i++) {
			final Resource resource = resourceSet.createResource(URI.createFileURI(outputFns[i]));
			resource.getContents().add(models[i]);
			resource.save(null);
		}
	}

	protected static EObject loadModel(final EPackage ePackage, final String inputFn, final ResourceSet resourceSet)
			throws IOException {
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION, /* matches any extension */new XMIResourceFactoryImpl());
		final Resource resource = resourceSet.createResource(URI.createFileURI(inputFn));
		resourceSet.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
		// load resource
		resource.load(null);
		return resource.getContents().get(0);
	}

	protected static EObject[] loadModels(final EPackage[] ePackages, final String[] inputFns) throws IOException {
		if (ePackages.length != inputFns.length) {
			LOG.error("Number of epackages and intput file names must be equal. " + "Found: " + ePackages.length + " <> " + inputFns.length);
			return null;
		}

		final EObject[] models = new EObject[ePackages.length];

		final ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION, /* matches any extension */new XMIResourceFactoryImpl());

		for (int i = 0; i < models.length; i++) {
			final Resource resource = resourceSet.createResource(URI.createFileURI(inputFns[i]));
			resourceSet.getPackageRegistry().put(ePackages[i].getNsURI(), ePackages[i]);
			// load resource
			resource.load(null);
			models[i] = resource.getContents().get(0);

		}

		return models;
	}

	public static void saveModels(
			final SystemModel systemModel, final String systemModelOutputFn,
			final UsageModel usageModel, final String usageModelFn) throws IOException {
		LOG.info("Trying to save system model to file " + systemModelOutputFn + " and usage model to file " + usageModelFn);

		ModelIOUtils.saveModels(new EObject[] { systemModel, usageModel }, new String[] { systemModelOutputFn, usageModelFn });
	}

	/**
	 * Loads the {@link SystemModel} from the given file.
	 * 
	 * @param inputFn
	 * @return
	 * @throws IOException
	 */
	public static SystemModel loadSystemModel(final String inputFn) throws IOException {
		return (SystemModel) ModelIOUtils.loadModels(new EPackage[] { CorePackage.eINSTANCE }, new String[] { inputFn })[0];
	}

	/**
	 * Loads a {@link SystemModel} and a {@link UsageModel} from the given
	 * files. Both file names must not be null.
	 * 
	 * @param systemInputFn
	 * @param usageModelFn
	 * @return an array with index 0 being the {@link SystemModel} and index 1
	 *         being the {@link UsageModel}
	 * @throws IOException
	 */
	public static EObject[] loadSystemAndUsageModel(final String systemInputFn, final String usageModelFn) throws IOException {
		return ModelIOUtils.loadModels(new EPackage[] { CorePackage.eINSTANCE, UsagePackage.eINSTANCE }, new String[] { systemInputFn, usageModelFn });
	}
}
