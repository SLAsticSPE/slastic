package org.trustsoft.slastic.common;

import java.lang.reflect.Method;
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

    /**
     * An object of the class with name @classname is instantiated, its method
     * init(String initString) is called with parameter @a initString and the
     * object is returned. This implies, that the class for @a classname provide
     * the method init(String initString).
     *
     * @return the instance; null in case an error occured.
     */
    public static AbstractSLAsticComponent loadAndInitSLAsticComponentFromClassname(String classname,
            Properties props) {
        AbstractSLAsticComponent inst = null;
        try {
            Class cl = Class.forName(classname);
            inst = (AbstractSLAsticComponent) cl.newInstance();
            Method m = cl.getMethod("init", Properties.class);
            m.invoke(inst, props);
            log.info("Loaded and instantiated component ('" + classname
                    + "') with init string '" + props + "'");
        } catch (Exception ex) {
            inst = null;
            log.fatal("Failed to instantiate component of class '" + classname
                    + "'", ex);
        }
        return inst;
    }
}
