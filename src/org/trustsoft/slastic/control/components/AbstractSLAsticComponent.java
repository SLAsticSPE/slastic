package org.trustsoft.slastic.control.components;

import java.util.HashMap;
import java.util.StringTokenizer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.components.events.ISimpleSLAsticEventServiceClient;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticComponent implements ISLAsticComponent {

    private static final Log log = LogFactory.getLog(AbstractSLAsticComponent.class);
    private final HashMap<String, String> map = new HashMap<String, String>();

   /** Returns the value for the initialization property @a propName or the
     *  the passed default value @a default if no value for this property
     *  exists. */
    protected final String getInitProperty(String propName, String defaultVal) {
        if (!this.initStringProcessed){
            log.error("InitString not yet processed. "+
                    " Call method initVarsFromInitString(..) first.");
            return null;
        }

        String retVal = this.map.get(propName);
        if (retVal == null) {
            retVal = defaultVal;
        }

        return retVal;
    }

    /** Returns the value for the initialization property @a propName or null
     *  if no value for this property exists. */
    protected final String getInitProperty(String propName) {
        return this.getInitProperty(propName, null);
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

        try{
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
            throw new IllegalArgumentException("Error parsing init string '"+initString+"'", exc);
        }

        this.initStringProcessed = true;
    }
}
