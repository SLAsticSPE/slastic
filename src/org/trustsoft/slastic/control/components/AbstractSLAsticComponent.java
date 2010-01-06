package org.trustsoft.slastic.control.components;

import java.util.HashMap;
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

    /**
     * Parses the initialization string @a initString for this component.
     * The initilization string consists of key/value pairs.
     * After this method is executed, the parameter values can be retrieved
     * using the method getInitProperty(..).
     */
    public boolean init(String initString) {
        if (initString == null || initString.length() == 0) {
            return true; // Empty string is allowed
        }

        boolean retVal = true;
        try {
            if (!this.initVarsFromInitString(initString)) {
                log.error("init failed");
                return false;
            }
        } catch (Exception exc) {
            log.fatal("Error initiliazing component", exc);
            retVal = false;
        }

        return retVal;
    }

    /** Returns the value for the initialization property @a propName or the
     *  the passed default value @a default if no value for this property
     *  exists. */
    public String getInitProperty(String propName, String defaultVal) {
        String retVal = this.map.get(propName);
        if (retVal == null) {
            retVal = defaultVal;
        }

        return retVal;
    }

    /** Returns the value for the initialization property @a propName or null
     *  if no value for this property exists. */
    public String getInitProperty(String propName) {
        return this.getInitProperty(propName, null);
    }

    private boolean initVarsFromInitString(String initString) {
        StringTokenizer keyValListTokens = new StringTokenizer(initString, "|");
        while (keyValListTokens.hasMoreTokens()) {
            String curKeyValToken = keyValListTokens.nextToken().trim();
            StringTokenizer keyValTokens = new StringTokenizer(curKeyValToken, "=");
            if (keyValTokens.countTokens() != 2) {
                log.error("Expected key=value pair, found " + curKeyValToken);
                return false;
            }
            String key = keyValTokens.nextToken().trim();
            String val = keyValTokens.nextToken().trim();
            log.info("Found key/value pair: " + key + "=" + val);
            map.put(key, val);
        }

        return true;
    }
}
