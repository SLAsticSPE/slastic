package org.trustsoft.slastic.control.components;

import java.util.HashMap;
import java.util.StringTokenizer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class AbstractSLAsticComponent implements ISLAsticComponent {
    private static final Log log = LogFactory.getLog(AbstractSLAsticComponent.class);

    public boolean init(String initString) {
        if (initString == null || initString.length() == 0) {
            log.error("Invalid initString. Valid example for tpmon.properties:\n" +
                    "monitoringDataWriterInitString=jmsProviderUrl=tcp://localhost:3035/ | jmsTopic=queue1 | jmsContextFactoryType=org.exolab.jms.jndi.InitialContextFactory | jmsFactoryLookupName=ConnectionFactory | jmsMessageTimeToLive = 10000");
            return false;
        }

        boolean retVal = true;
        try {
            if (!this.initVarsFromInitString(initString)) {
                log.error("init failed");
                return false;
            }
       } catch (Exception exc) {
            log.fatal("Error initiliazing JMS Connector", exc);
            retVal = false;
        } 

        return retVal;
    }

    private boolean initVarsFromInitString(String initString) {
        HashMap<String, String> map = new HashMap<String, String>();
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
