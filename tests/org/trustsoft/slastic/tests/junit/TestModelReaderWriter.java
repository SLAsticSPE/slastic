package org.trustsoft.slastic.tests.junit;

import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepository;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;
import java.io.File;
import java.io.IOException;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelIOUtils;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

/**
 *
 * @author Andre van Hoorn
 */
public class TestModelReaderWriter extends TestCase {

    private static final Log log = LogFactory.getLog(TestModelReaderWriter.class);

    /**
     * Tests whether a type repository model gets properly saved to a
     * file and can subsequently be reloaded from the file.
     *
     * @throws IOException
     */
    public void testSaveReadTypeRepositoryModel() throws IOException {
        /* Two fully-qualified  component type names used in the test */
        final String fqnComponentType0 = "a.b.C";
        final String fqnComponentType1 = "a.b.D";

        /* Create a tmp file the type repository model will be saved to
         * and mark the file to be deleted on jvm termination */
        final File tmpFile =
                File.createTempFile("typeRepository-", "");
        tmpFile.deleteOnExit();

        /* Create a type repository model with two components and save it
         * to the tmp file */
        final TypeRepository typeRepositoryModel =
                TypeRepositoryFactory.eINSTANCE.createTypeRepository();
        final TypeRepositoryModelManager typeRepositoryManager =
                new TypeRepositoryModelManager(typeRepositoryModel);
        final ComponentType componentType0 =
                typeRepositoryManager.createAndRegisterComponentType(fqnComponentType0);
        final ComponentType componentType1 =
                typeRepositoryManager.createAndRegisterComponentType(fqnComponentType1);
        log.info("Saving type repository to file " + tmpFile.getAbsolutePath());
        ModelIOUtils.saveTypeRepositoryModel(typeRepositoryModel, tmpFile.getAbsolutePath());

        /* Load the model from the file */
        log.info("Loading repository from file " + tmpFile.getAbsolutePath());
        final TypeRepository typeRepositoryModelLoaded =
                ModelIOUtils.loadTypeRepositoryModel(tmpFile.getAbsolutePath());

        final TypeRepositoryModelManager typeRepositoryManagerLoadedModel =
                new TypeRepositoryModelManager(typeRepositoryModelLoaded);
        final ComponentType componentType0Loaded =
                typeRepositoryManagerLoadedModel.lookupComponentType(fqnComponentType0);
        final ComponentType componentType1Loaded =
                typeRepositoryManagerLoadedModel.lookupComponentType(fqnComponentType1);

        /* Perform a simple (incomplete) equality check */
        assertEquals(componentType0.getId(), componentType0Loaded.getId());
        assertEquals(componentType1.getId(), componentType1Loaded.getId());
    }
}
