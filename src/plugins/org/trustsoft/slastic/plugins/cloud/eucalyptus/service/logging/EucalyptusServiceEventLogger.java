/**
 * 
 */
package org.trustsoft.slastic.plugins.cloud.eucalyptus.service.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.ICurrentTimeProvider;

/**
 * @author Andre van Hoorn
 * 
 */
public class EucalyptusServiceEventLogger extends
		AbstractEucalyptusServiceEventLogger {

	private static final Log log = LogFactory
			.getLog(EucalyptusServiceEventLogger.class);

	public EucalyptusServiceEventLogger() {
		super();
	}

	public EucalyptusServiceEventLogger(
			final ICurrentTimeProvider currentTimeProvider) {
		super(currentTimeProvider);
	}

	@Override
	protected void logEvent(final String eventMsg) {
		EucalyptusServiceEventLogger.log.info(eventMsg);
	}
}
