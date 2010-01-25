package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.monitoring.AbstractSLAsticMonitoringManager;

/**
 *
 * @author Andre van Hoorn
 */
public class KiekerMonitoringManager extends AbstractSLAsticMonitoringManager {
    private static final Log log = LogFactory.getLog(KiekerMonitoringManager.class);
	
	@Override
	public boolean execute() {
		log.info("KiekerMonitoringManager now executing");
		return false;
	}

	@Override
	public void terminate() {
		log.info("KiekerMonitoringManager now terminating");
	}

}
