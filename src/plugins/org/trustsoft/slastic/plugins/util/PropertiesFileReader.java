package org.trustsoft.slastic.plugins.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class PropertiesFileReader {

    private static final Log log = LogFactory.getLog(PropertiesFileReader.class);

    public static Properties loadPropertiesFile(String fn) throws IllegalArgumentException {
        // Load configuration file
        InputStream is = null;
        Properties prop = new Properties();

        try {
            is = new FileInputStream(fn);
            log.info("Loading properties from file '" + fn + "'");
            prop.load(is);
        } catch (Exception ex) {
            log.error("Failed to load properties from file '" + fn + "'", ex);
            throw new IllegalArgumentException("Failed to load properties from file '" + fn + "'", ex);
        } finally {
            try {
                is.close();
            } catch (Exception ex) {
                log.error("Failed to close property input stream", ex);
            }
        }
        return prop;
    }
}
