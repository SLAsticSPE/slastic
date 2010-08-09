package org.trustsoft.slastic.tests.junit.model;

import de.cau.se.slastic.metamodel.core.CoreFactory;
import de.cau.se.slastic.metamodel.core.SystemModel;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import java.io.File;
import java.io.IOException;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;

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

        /* Create a tmp file the type repository model will be saved to
         * and mark the file to be deleted on jvm termination */
        final File tmpFile =
                File.createTempFile("systemModel-", "");
        tmpFile.deleteOnExit();

        /* Create a system model with two components types and save it to the
         * tmp file */
        final ModelManager systemModelManager =
                new ModelManager(); // the manager creates a new system model
        final ComponentType componentType0 =
                systemModelManager.getTypeRepositoryManager().createAndRegisterComponentType(fqnComponentType0);
        final ComponentType componentType1 =
                systemModelManager.getTypeRepositoryManager().createAndRegisterComponentType(fqnComponentType1);
        log.info("Saving system model to file " + tmpFile.getAbsolutePath());
        systemModelManager.saveModel(tmpFile.getAbsolutePath());

        /* Load the model from the file */
        log.info("Loading system model from file " + tmpFile.getAbsolutePath());
        final ModelManager systemModelManagerLoadedModel =
                new ModelManager(tmpFile.getAbsolutePath());
        final ComponentType componentType0Loaded =
                systemModelManagerLoadedModel.getTypeRepositoryManager().lookupComponentType(fqnComponentType0);
        final ComponentType componentType1Loaded =
                systemModelManagerLoadedModel.getTypeRepositoryManager().lookupComponentType(fqnComponentType1);

        /* Perform a simple (incomplete) equality check */
        assertEquals(componentType0.getId(), componentType0Loaded.getId());
        assertEquals(componentType1.getId(), componentType1Loaded.getId());
    }
}