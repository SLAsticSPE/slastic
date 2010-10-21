package org.trustsoft.slastic.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class PropertiesFileUtils {

    private static final Log log = LogFactory.getLog(PropertiesFileUtils.class);

    public static Properties loadPropertiesFile(final String fn) throws IllegalArgumentException {
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

    public static Properties storePropertiesFile(final Properties prop, final String fn) throws IllegalArgumentException {
        OutputStream os = null;

        try {
            os = new FileOutputStream(fn);
            log.info("Storing properties to file '" + fn + "'");
            prop.store(os, null); // no comment
        } catch (Exception ex) {
            log.error("Failed to store properties to file '" + fn + "'", ex);
            throw new IllegalArgumentException("Failed to store properties to file '" + fn + "'", ex);
        } finally {
            try {
                os.close();
            } catch (Exception ex) {
                log.error("Failed to close property output stream", ex);
            }
        }
        return prop;
    }
}
