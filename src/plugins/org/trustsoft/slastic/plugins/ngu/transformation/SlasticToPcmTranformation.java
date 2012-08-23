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

package org.trustsoft.slastic.plugins.ngu.transformation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl;
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
import org.trustsoft.slastic.plugins.slasticImpl.ModelIOUtils;

import de.cau.se.slastic.metamodel.core.SystemModel;
import de.cau.se.slastic.metamodel.usage.UsageModel;

/**
 * @author Nicolas Günther
 * 
 */
public class SlasticToPcmTranformation {

	private static final Log log = LogFactory.getLog(SlasticToPcmTranformation.class);
	
	private static final String TRANSFORMATION_RESOURCE_NAME = "/slastic2pcm.asm"; // to be found in the SLAStic Jar
	private static final String SLASTIC_METAMODEL_RESOURCE = "/slastic.ecore"; // to be found in the SLAStic Jar
	private static final String PCM_METAMODEL_RESOURCE = "/pcm.ecore"; // to be found in the SLAStic Jar
	
	private URL transformationResource;
	private URL slasticMetaModelResource;
	private URL pcmMetaModelResource;
	private IModel pcmResourceTypeModel;
	private IModel pcmRepositoryModel;
	private IModel pcmResourceEnvironmentModel;
	private IModel pcmAllocationModel;
	private IModel pcmSystemModel;
	private IModel pcmUsageModel;

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

	public IModel getPcmUsageModel() {
		return this.pcmUsageModel;
	}
	
	private boolean loadResources() {
		this.transformationResource = SlasticToPcmTranformation.class.getResource(SlasticToPcmTranformation.TRANSFORMATION_RESOURCE_NAME);
		if(this.transformationResource == null) {
			SlasticToPcmTranformation.log.error("Failed to load '" + SlasticToPcmTranformation.TRANSFORMATION_RESOURCE_NAME + "'");
			return false;
		}
		
		this.slasticMetaModelResource = SlasticToPcmTranformation.class.getResource(SlasticToPcmTranformation.SLASTIC_METAMODEL_RESOURCE);
		if(this.slasticMetaModelResource == null) {
			SlasticToPcmTranformation.log.error("Failed to load '" + SlasticToPcmTranformation.TRANSFORMATION_RESOURCE_NAME + "'");
			return false;
		}
		
		this.pcmMetaModelResource = SlasticToPcmTranformation.class.getResource(SlasticToPcmTranformation.PCM_METAMODEL_RESOURCE);
		if(this.pcmMetaModelResource == null) {
			SlasticToPcmTranformation.log.error("Failed to load '" + SlasticToPcmTranformation.PCM_METAMODEL_RESOURCE + "'");
			return false;
		}
		
		return true;
	}

	public boolean transform(final SystemModel systemModel, final UsageModel usageModel) {
		return this.transform(systemModel.eResource(), usageModel.eResource());
	}
	
	public boolean transform(final String systemModelPath, final String usageModelPath) throws IOException{
		final EObject[] models = ModelIOUtils.loadSystemAndUsageModel(systemModelPath, usageModelPath);

		final SystemModel systemModel = (SystemModel) models[0];
		final UsageModel usageModel = (UsageModel) models[1];
		return this.transform(systemModel.eResource(), usageModel.eResource());
	}
	
	public boolean transform(final Resource systemModelResource, final Resource usageModelResource) {
		EMFModel slasticSystemModel = null;
		EMFModel slasticUsageModel = null;
		
		try {
			if (!this.loadResources()) {
				SlasticToPcmTranformation.log.error("Failed to create resources");
				return false;
				
			}
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

			slasticSystemModel = (EMFModel) modelFactory.newModel(slasticMetaModel);
			slasticUsageModel = (EMFModel) modelFactory.newModel(slasticMetaModel);

			injector.inject(slasticSystemModel, systemModelResource.getURI().toString());
			injector.inject(slasticUsageModel, usageModelResource.getURI().toString());
			
			SlasticToPcmTranformation.log.info("slasticSystemModel: " + slasticSystemModel);
			SlasticToPcmTranformation.log.info("slasticUsageModel: " + slasticUsageModel);
			
			this.pcmResourceTypeModel = modelFactory.newModel(pcmMetaModel);
			this.pcmRepositoryModel = modelFactory.newModel(pcmMetaModel);
			this.pcmResourceEnvironmentModel = modelFactory.newModel(pcmMetaModel);
			this.pcmAllocationModel = modelFactory.newModel(pcmMetaModel);
			this.pcmSystemModel = modelFactory.newModel(pcmMetaModel);
			this.pcmUsageModel = modelFactory.newModel(pcmMetaModel);

			// The names of the models have to correspond to those defined in
			// the atl module.
			launcher.addInModel(slasticSystemModel, "SLASTIC_SYSTEM", "Slastic");
			launcher.addInModel(slasticUsageModel, "SLASTIC_USAGE", "Slastic");
			launcher.addOutModel(this.pcmResourceTypeModel, "RESOURCETYPE", "Pcm");
			launcher.addOutModel(this.pcmRepositoryModel, "REPOSITORY", "Pcm");
			launcher.addOutModel(this.pcmResourceEnvironmentModel, "RESOURCEENVIRONMENT", "Pcm");
			launcher.addOutModel(this.pcmAllocationModel, "ALLOCATION", "Pcm");
			launcher.addOutModel(this.pcmSystemModel, "SYSTEM", "Pcm");
			launcher.addOutModel(this.pcmUsageModel, "USAGEMODEL", "Pcm");

			final Map<String, Object> launchOptions = new HashMap<String, Object>();
			launchOptions.put("allowInterModelReferences", "true");

			SlasticToPcmTranformation.log
					.info(String
							.format(
									"Launching transformation of slastic system model instance [%s] and slastic usage model instance [%s] to pcm.",
									slasticSystemModel.toString(), slasticUsageModel.toString()));
			launcher.launch(ILauncher.RUN_MODE, null, launchOptions, this.transformationResource.openStream());
		} catch (final ATLCoreException e) {
			SlasticToPcmTranformation.log
					.error(String
							.format("Error transforming slastic system model instance [%s] and slastic usage model instance [%s] to pcm.",
									slasticSystemModel.toString(), slasticUsageModel.toString()), e);
			return false;
		} catch (final IOException e) {
			SlasticToPcmTranformation.log.error(
					String.format("Cannot open stream for transformation resource [%s].",
							this.transformationResource.toString()), e);
			return false;
		}
		
		return true;
	}

	public void extractPcmModel(final IComponentContext componentContext, final String outputFilePrefix) {
		final IComponentContext outputContext = componentContext.createSubcontext(outputFilePrefix);
		this.extractPcmModel(new File(outputContext.getDirectoryLocation()), outputFilePrefix);
	}

	/**
	 * Removes a given base dir from a URI. E.g., for a base dir <pre>tmp/</pre>, the 
	 * URI <pre>tmp/output.repository#c0</pre> is converted to <pre>output.repository</pre>.
	 * 
	 * @author Andre van Hoorn
	 *
	 */
	private static class MyURIHandler extends URIHandlerImpl {
		private final String baseDir;
		
		public MyURIHandler(final String baseDir) {
			this.baseDir = baseDir;
		}
		
		@Override
		public URI deresolve(final URI arg0) {
			final String oldURIString = arg0.toString();
			final String newURIString = oldURIString.replaceAll(this.baseDir+"/", "");
			//System.err.println("deresolve: " + oldURIString + "=>" + newURIString);
			return URI.createURI(newURIString);
		}

		@Override
		public URI resolve(final URI arg0) {
			//System.err.println("resolve: " + arg0);
			return super.resolve(arg0);
		}

		@Override
		public void setBaseURI(final URI arg0) {
			//System.err.println("setBaseURI: " + arg0);
			super.setBaseURI(arg0);
		}
	}
	
	public void extractPcmModel(final File directory, final String outputFilePrefix) {
		final IExtractor extractor = new EMFExtractor();

		final Map<String, Object> extractOptions = new HashMap<String, Object>();
		extractOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
	
		{
			final XMLResource.URIHandler myURIHandler = new MyURIHandler(directory.getPath());			
			extractOptions.put(XMLResource.OPTION_URI_HANDLER, myURIHandler);
			
		}
		
		this.extractPcmModel(extractor, this.pcmResourceTypeModel, 
				URI.createFileURI(new File(directory, outputFilePrefix+ ".resourcetype").getPath()).toFileString(), extractOptions);
		this.extractPcmModel(extractor, this.pcmRepositoryModel,
				URI.createFileURI(new File(directory, outputFilePrefix + ".repository").getPath()).toFileString(), extractOptions);
		this.extractPcmModel(extractor, this.pcmResourceEnvironmentModel, 
				URI.createFileURI(new File(directory, outputFilePrefix+ ".resourceenvironment").getPath()).toFileString(), extractOptions);
		this.extractPcmModel(extractor, this.pcmSystemModel,
				URI.createFileURI(new File(directory, outputFilePrefix + ".system").getPath()).toFileString(), extractOptions);
		this.extractPcmModel(extractor, this.pcmAllocationModel,
				URI.createFileURI(new File(directory, outputFilePrefix + ".allocation").getPath()).toFileString(), extractOptions);
		this.extractPcmModel(extractor, this.pcmUsageModel,
				URI.createFileURI(new File(directory, outputFilePrefix + ".usagemodel").getPath()).toFileString(), extractOptions);
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
	
	public static void main(final String[] args) {
		final CommandLineParser parser = new BasicParser();
		final Options options = new Options();
		final HelpFormatter helpFormatter = new HelpFormatter();

		options.addOption("h", "help", false, "Print this usage information");
		options.addOption("s", "system", true,
				"SLAstic system model instance input file");
		options.addOption("u", "usage", true,
				"SLAstic usage model instance input file");
		options.addOption("p", "path", true, "Path for PCM model output files");
		options.addOption("o", "output", true, "Prefix for PCM output files");

		String systemModelPath = null;
		String usageModelPath = null;
		String outputPath = ".";
		String outputFilePrefix = "output";
		try {
			final CommandLine commandLine = parser.parse(options, args);
			if (commandLine.hasOption('h')) {
				helpFormatter.printHelp(SlasticToPcmTranformation.class.getName(),
						options);
				System.exit(0);
			}
			if (commandLine.hasOption('s')) {
				systemModelPath = commandLine.getOptionValue('s');
			} else {
				System.err.println("A SLAstic system and usage model instance has to be specified");
				helpFormatter.printHelp(SlasticToPcmTranformation.class.getName(),
						options);
				System.exit(1);
			}
			if (commandLine.hasOption('u')) {
				usageModelPath = commandLine.getOptionValue('u');
			} else {
				System.err.println("A SLAstic usage input model instance has to be specified");
				helpFormatter.printHelp(SlasticToPcmTranformation.class.getName(),
						options);
				System.exit(1);
			}
			if (commandLine.hasOption('p')) {
				outputPath = commandLine.getOptionValue('p');
			}
			if (commandLine.hasOption('o')) {
				outputFilePrefix = commandLine.getOptionValue('o');
			}
		} catch (final ParseException e) {
			System.err.println("Error parsing arguments: " + e.getMessage());
			helpFormatter.printHelp(SlasticToPcmTranformation.class.getName(),
					options);
			System.exit(0);
		}
		final SlasticToPcmTranformation transformation = new
				SlasticToPcmTranformation();
		try {
			if (!transformation.transform(systemModelPath, usageModelPath)) {
				SlasticToPcmTranformation.log.error("An error occurred while executing the transformation");
				System.exit(0);
			}
		} catch (final IOException e) {
			SlasticToPcmTranformation.log.error("An error occurred while executing the transformation", e);
			System.exit(1);
		}
		transformation.extractPcmModel(new File(outputPath), outputFilePrefix);
	}
}