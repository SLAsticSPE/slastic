package org.trustsoft.slastic.common;

import java.util.HashMap;
import java.util.Properties;
import java.util.StringTokenizer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticComponent implements ISLAsticComponent {

    private static final Log log = LogFactory.getLog(AbstractSLAsticComponent.class);
    private final HashMap<String, String> map = new HashMap<String, String>();
    private Properties properties;

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /** Returns the value for the initialization property @a propName or the
     *  the passed default value @a default if no value for this property
     *  exists. */
    protected final String getInitProperty(String propName, String defaultVal) {
        if (!this.initStringProcessed) {
            log.error("InitString not yet processed. " +
                    " Call method initVarsFromInitString(..) first.");
            return null;
        }

        String retVal;

        if (this.properties != null) {
            retVal = this.properties.getProperty(propName, defaultVal);
        } else { // TODO: REMOVE
            retVal = this.map.get(propName);
            if (retVal == null) {
                retVal = defaultVal;
            }
        }

        return retVal;
    }

    /** Returns the value for the initialization property @a propName or null
     *  if no value for this property exists. */
    protected final String getInitProperty(String propName) {
        if (this.properties != null){
            return this.properties.getProperty(propName, null);
        } else { // TODO: remove
            return this.getInitProperty(propName, null);   
        }
    }
    private boolean initStringProcessed = false;

    /**
     * Parses the initialization string @a initString for this component.
     * The initilization string consists of key/value pairs.
     * After this method is executed, the parameter values can be retrieved
     * using the method getInitProperty(..).
     */
    protected final void initVarsFromInitString(String initString) throws IllegalArgumentException {
        if (initString == null || initString.length() == 0) {
            this.initStringProcessed = true;
            return; // Empty string is allowed
        }

        try {
            StringTokenizer keyValListTokens = new StringTokenizer(initString, "|");
            while (keyValListTokens.hasMoreTokens()) {
                String curKeyValToken = keyValListTokens.nextToken().trim();
                StringTokenizer keyValTokens = new StringTokenizer(curKeyValToken, "=");
                if (keyValTokens.countTokens() != 2) {
                    throw new IllegalArgumentException("Expected key=value pair, found " + curKeyValToken);
                }
                String key = keyValTokens.nextToken().trim();
                String val = keyValTokens.nextToken().trim();
                log.info("Found key/value pair: " + key + "=" + val);
                map.put(key, val);
            }
        } catch (Exception exc) {
            throw new IllegalArgumentException("Error parsing init string '" + initString + "'", exc);
        }

        this.initStringProcessed = true;
    }
}
