package org.trustsoft.slastic.common;

import java.util.HashMap;
import java.util.Properties;
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

    @Override
    public void init(Properties properties) {
        this.properties = properties;
    }

    /** Returns the value for the initialization property @a propName or the
     *  the passed default value @a default if no value for this property
     *  exists. */
    protected final String getInitProperty(String propName, String defaultVal) {
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
    protected final String getInitProperty(final String propName) {
        if (this.properties != null) {
            return this.properties.getProperty(propName, null);
        } else { // TODO: remove
            return this.getInitProperty(propName, null);
        }
    }
}
