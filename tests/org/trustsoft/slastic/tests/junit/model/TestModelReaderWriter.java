package org.trustsoft.slastic.tests.junit.model;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;

import de.cau.se.slastic.metamodel.typeRepository.ComponentType;

/**
 *
 * @author Andre van Hoorn
 */
public class TestModelReaderWriter extends TestCase {

    private static final Log log = LogFactory.getLog(TestModelReaderWriter.class);

    /**
     * Tests whether a system model gets properly saved to a
     * file and can subsequently be reloaded from the file.
     *
     * @throws IOException
     */
    public void testSaveReadSystemModel() throws IOException {
        /* Two fully-qualified  component type names used in the test */
        final String fqnComponentType0 = "a.b.C";
        final String fqnComponentType1 = "a.b.D";

        /* Create a tmp files the models will be saved to
         * and mark the files to be deleted on jvm termination */
        final File tmpSystemModelFile =
                File.createTempFile("systemModel-", "");
        //tmpSystemModelFile.deleteOnExit();
        
        final File tmpUsageModelFile =
            File.createTempFile("usageModel-", "");
        //tmpSystemModelFile.deleteOnExit();

        /* Create a system model with two components types and save it to the
         * tmp file */
        final ModelManager systemModelManager =
                new ModelManager(); // the manager creates a new system model
        final ComponentType componentType0 =
                systemModelManager.getTypeRepositoryManager().createAndRegisterComponentType(fqnComponentType0);
        final ComponentType componentType1 =
                systemModelManager.getTypeRepositoryManager().createAndRegisterComponentType(fqnComponentType1);
        TestModelReaderWriter.log.info("Saving system model to file " + tmpSystemModelFile.getAbsolutePath() 
        		+ " and usage model to file " + tmpUsageModelFile.getAbsolutePath());
        systemModelManager.saveModels(tmpSystemModelFile.getAbsolutePath(), tmpUsageModelFile.getAbsolutePath());

        /* Load the model from the file */
        TestModelReaderWriter.log.info("Loading system model from file " + tmpSystemModelFile.getAbsolutePath());
        final ModelManager systemModelManagerLoadedModel =
                new ModelManager(tmpSystemModelFile.getAbsolutePath());
        final ComponentType componentType0Loaded =
                systemModelManagerLoadedModel.getTypeRepositoryManager().lookupComponentType(fqnComponentType0);
        final ComponentType componentType1Loaded =
                systemModelManagerLoadedModel.getTypeRepositoryManager().lookupComponentType(fqnComponentType1);

        /* Perform a simple (incomplete) equality check */
        Assert.assertEquals(componentType0.getId(), componentType0Loaded.getId());
        Assert.assertEquals(componentType1.getId(), componentType1Loaded.getId());
    }
}
