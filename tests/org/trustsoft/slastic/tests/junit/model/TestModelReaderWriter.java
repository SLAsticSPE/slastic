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

package org.trustsoft.slastic.tests.junit.model;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

import de.cau.se.slastic.metamodel.core.SystemModel;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.Operation;
import de.cau.se.slastic.metamodel.usage.OperationCallFrequency;
import de.cau.se.slastic.metamodel.usage.UsageFactory;
import de.cau.se.slastic.metamodel.usage.UsageModel;

/**
 * 
 * @author Andre van Hoorn
 */
public class TestModelReaderWriter extends TestCase {

	private static final Log LOG = LogFactory.getLog(TestModelReaderWriter.class);

	/**
	 * Temporary file the {@link SystemModel} will be saved to (deleted after
	 * the test)
	 */
	private File tmpSystemModelFile;
	/**
	 * Temporary file the {@link UsageModel} will be saved to (deleted after the
	 * test)
	 */
	private File tmpUsageModelFile;

	/* Two fully-qualified component type names used in the test */
	private final String fqnComponentType0 = "a.b.C";
	private final String fqnComponentType1 = "a.b.D";

	private ComponentType componentType0, componentType0Loaded;
	private ComponentType componentType1, componentType1Loaded;

	/**
	 * {@link ModelManager} that manages the models to be saved
	 */
	private final ModelManager systemModelManager = new ModelManager();

	/**
	 * Tests whether a system model gets properly saved to a file and can
	 * subsequently be reloaded from the file.
	 * 
	 * @throws IOException
	 */
	public void testSaveReadSystemAndUSageModel() throws IOException {
		this.createTmpFiles();
		this.fillSystemModel();
		this.fillUsageModel();
		this.saveModels();
		this.loadModels();
		this.checkResults();
	}

	/**
	 * Creates tmp files the models will be saved to and mark the files to be
	 * deleted on jvm termination
	 */
	private void createTmpFiles() throws IOException {
		this.tmpSystemModelFile = File.createTempFile("systemModel-", "");
		// this.tmpSystemModelFile.deleteOnExit();

		this.tmpUsageModelFile = File.createTempFile("usageModel-", "");
		// this.tmpSystemModelFile.deleteOnExit();
	}

	/**
	 * Registers the two {@link ComponentType}s {@link #componentType0} and {@link #componentType1}, each having a single {@link Operation}.
	 */
	private void fillSystemModel() {
		final TypeRepositoryModelManager tMgr = this.systemModelManager.getTypeRepositoryManager();
		this.componentType0 = tMgr.createAndRegisterComponentType(this.fqnComponentType0);
		tMgr.createAndRegisterOperation(this.componentType0, "op0", Boolean.class.getName(), new String[] { void.class.getName() });
		this.componentType1 = tMgr.createAndRegisterComponentType(this.fqnComponentType1);
		tMgr.createAndRegisterOperation(this.componentType1, "op1", Integer.class.getName(), new String[] { String.class.getName() });
	}

	/**
	 * Adds two {@link OperationCallFrequency}s for the {@link Operation}s of
	 * the two {@link ComponentType}s created in {@link #fillSystemModel()} to
	 * the {@link UsageModel}.
	 */
	private void fillUsageModel() {
		// TODO: note that this is somehow dirty, as we are not using the usage
		// model manager (we may fix this, as soon as we extended the manager's)
		// capabilities.
		final UsageModel usageModel = this.systemModelManager.getUsageModelManager().getModel();

		final OperationCallFrequency opCallFreq0 = UsageFactory.eINSTANCE.createOperationCallFrequency();
		opCallFreq0.setOperation(this.componentType0.getOperations().get(0));
		opCallFreq0.setFrequency(17);
		usageModel.getOperationCallFrequencies().add(opCallFreq0);

		final OperationCallFrequency opCallFreq1 = UsageFactory.eINSTANCE.createOperationCallFrequency();
		opCallFreq1.setOperation(this.componentType1.getOperations().get(0));
		opCallFreq1.setFrequency(33);
		usageModel.getOperationCallFrequencies().add(opCallFreq1);
	}

	private void saveModels() throws IOException {
		TestModelReaderWriter.LOG.info("Saving system model to file " + this.tmpSystemModelFile.getAbsolutePath()
				+ " and usage model to file " + this.tmpUsageModelFile.getAbsolutePath());
		this.systemModelManager.saveModels(this.tmpSystemModelFile.getAbsolutePath(), this.tmpUsageModelFile.getAbsolutePath());
	}

	private ModelManager systemModelManagerLoadedModel;

	private void loadModels() throws IOException {
		/* Load the model from the file */
		TestModelReaderWriter.LOG.info("Loading system model from file " + this.tmpSystemModelFile.getAbsolutePath()
				+ " and usage model from file " + this.tmpUsageModelFile.getAbsolutePath());
		this.systemModelManagerLoadedModel = new ModelManager(this.tmpSystemModelFile.getAbsolutePath(), this.tmpUsageModelFile.getAbsolutePath());
	}

	private void checkResults() {
		final TypeRepositoryModelManager tMgr = this.systemModelManagerLoadedModel.getTypeRepositoryManager();
		this.componentType0Loaded = tMgr.lookupComponentType(this.fqnComponentType0);
		this.componentType1Loaded = tMgr.lookupComponentType(this.fqnComponentType1);

		/* Perform a simple (incomplete) equality check on the component types */
		Assert.assertEquals(this.componentType0.getName(), this.componentType0Loaded.getName());
		Assert.assertEquals(this.componentType1.getName(), this.componentType1Loaded.getName());

		final Operation op0Loaded = this.componentType0Loaded.getOperations().get(0);
		final Operation op1Loaded = this.componentType1Loaded.getOperations().get(0);

		// TODO: note that this is somehow dirty, as we are not using the usage
		// model manager (we may fix this, as soon as we extended the manager's)
		// capabilities.
		final UsageModel usageModelLoaded = this.systemModelManagerLoadedModel.getUsageModelManager().getModel();

		// Now we'll check if the loaded system model and the usage model refer to the SAME objects
		// by comparing the operation objects

		for (final OperationCallFrequency opCallFreq : usageModelLoaded.getOperationCallFrequencies()) {
			final Operation op = opCallFreq.getOperation();
			Assert.assertTrue("Objects contained in system model are not the same!", (op == op0Loaded) || (op == op1Loaded));
		}
	}
}
