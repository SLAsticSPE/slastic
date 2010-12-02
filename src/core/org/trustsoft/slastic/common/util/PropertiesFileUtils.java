package org.trustsoft.slastic.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import kieker.monitoring.core.configuration.ConfigurationProperty;

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
        final Properties prop = new Properties();

        try {
            is = new FileInputStream(fn);
            PropertiesFileUtils.log.info("Loading properties from file '" + fn + "'");
            prop.load(is);
        } catch (final Exception ex) {
            PropertiesFileUtils.log.error("Failed to load properties from file '" + fn + "'", ex);
            throw new IllegalArgumentException("Failed to load properties from file '" + fn + "'", ex);
        } finally {
            try {
                is.close();
            } catch (final Exception ex) {
                PropertiesFileUtils.log.error("Failed to close property input stream", ex);
            }
        }
        return prop;
    }

    public static Properties storePropertiesFile(final Properties prop, final String fn) throws IllegalArgumentException {
        OutputStream os = null;

        try {
            os = new FileOutputStream(fn);
            PropertiesFileUtils.log.info("Storing properties to file '" + fn + "'");
            prop.store(os, null); // no comment
        } catch (final Exception ex) {
            PropertiesFileUtils.log.error("Failed to store properties to file '" + fn + "'", ex);
            throw new IllegalArgumentException("Failed to store properties to file '" + fn + "'", ex);
        } finally {
            try {
                os.close();
            } catch (final Exception ex) {
                PropertiesFileUtils.log.error("Failed to close property output stream", ex);
            }
        }
        return prop;
    }
    
	/**
	 * 
	 * @param props
	 * @param propertyName
	 * @param defaultValue
	 * @param considerSystemProperties
	 * @return
	 */
	public static boolean loadBooleanConfigurationProperty(
			final Properties props, final ConfigurationProperty property,
			final boolean considerSystemProperties) {
		final String stringValue = PropertiesFileUtils
				.loadStringConfigurationProperty(props, property,
						considerSystemProperties);

		return Boolean.parseBoolean(stringValue);
	}

	/**
	 * 
	 * @param props
	 * @param property
	 * @param considerSystemProperties
	 * @return
	 * @throws NumberFormatException
	 */
	public static int loadIntConfigurationProperty(final Properties props,
			final ConfigurationProperty property,
			final boolean considerSystemProperties)
			throws NumberFormatException {
		final String stringValue = PropertiesFileUtils
				.loadStringConfigurationProperty(props, property,
						considerSystemProperties);

		return Integer.parseInt(stringValue);
	}

	/**
	 * 
	 * @param props
	 * @param property
	 * @param considerSystemProperties
	 * @return
	 */
	public static String loadStringConfigurationProperty(
			final Properties props, final ConfigurationProperty property,
			final boolean considerSystemProperties) {
		String propertyValue;
		if (considerSystemProperties && property.hasJVMArgument()
				&& (System.getProperty(property.getJVMArgumentName()) != null)) {
			/* We use the present virtual machine parameter value */
			propertyValue = System.getProperty(property.getJVMArgumentName());
		} else if (property.getPropertyName() != null) {
			/* we use the value from the properties map */
			propertyValue = props.getProperty(property.getPropertyName());
		} else {
			/* Un-named property with a default value */
			propertyValue = property.getDefaultValue();
		}

		if ((propertyValue == null)
				|| (!property.isAllowEmpty() && propertyValue.isEmpty())) {
			propertyValue = property.getDefaultValue();
			PropertiesFileUtils.log.info("Missing value for property '"
					+ property.getPropertyName() + "' using default value "
					+ propertyValue);
		}

		return propertyValue;
	}
}
