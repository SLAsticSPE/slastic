/**
 * 
 */
package org.trustsoft.slastic.plugins.cloud.amazon.service.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.cloud.common.ICurrentTimeProvider;

/**
 * @author Andre van Hoorn
 * 
 */
public class AmazonServiceEventLogger extends
		AbstractAmazonServiceEventLogger {

	private static final Log log = LogFactory
			.getLog(AmazonServiceEventLogger.class);

	public AmazonServiceEventLogger() {
		super();
	}

	public AmazonServiceEventLogger(
			final ICurrentTimeProvider currentTimeProvider) {
		super(currentTimeProvider);
	}

	@Override
	protected void logEvent(final String eventMsg) {
		AmazonServiceEventLogger.log.info(eventMsg);
	}
}
