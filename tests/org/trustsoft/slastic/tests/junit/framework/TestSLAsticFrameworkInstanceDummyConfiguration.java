package org.trustsoft.slastic.tests.junit.framework;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.AbstractControlComponent;
import org.trustsoft.slastic.plugins.starter.SLAsticAdaptationFrameworkInstance;
import org.trustsoft.slastic.plugins.util.PropertiesFileUtils;

/**
 *
 * @author Andre van Hoorn
 */
public class TestSLAsticFrameworkInstanceDummyConfiguration extends TestCase {
    private static final Log log = LogFactory.getLog(AbstractControlComponent.class);

    /**
     * Makes sure that a dummy instance can be initialized and executed.
     */
    public void testInitAndRunInstanceByProperties(){
        Properties configuration = SLAsticAdaptationFrameworkInstance.genDummyConfiguration();

        SLAsticAdaptationFrameworkInstance slasticInstance =
                new SLAsticAdaptationFrameworkInstance(configuration);
        boolean success = slasticInstance.run();
        assertTrue("slasticInstance.run() returned false", success);
        slasticInstance.terminate(false); // no error
    }

    /**
     * Makes sure that a dummy instance can be initialized and executed where
     * the configuration is read from a configuration file.
     */
    public void testInitAndRunInstanceByPropertiesFile() throws IOException{
        Properties configuration = SLAsticAdaptationFrameworkInstance.genDummyConfiguration();

        /* Create a tmp file the configuration will be saved to
         * and mark the file to be deleted on jvm termination */
        final File tmpFile =
                File.createTempFile("slastic-configuration-", "");
        //tmpFile.deleteOnExit();

        /* Save properties to file */
        log.info("Writing dummy configuration to file " + tmpFile.getAbsolutePath());
        PropertiesFileUtils.storePropertiesFile(configuration, tmpFile.getAbsolutePath());

        SLAsticAdaptationFrameworkInstance slasticInstance =
                new SLAsticAdaptationFrameworkInstance(tmpFile.getAbsolutePath());
        boolean success = slasticInstance.run();
        assertTrue("slasticInstance.run() returned false", success);
        slasticInstance.terminate(false); // no error
    }
}
