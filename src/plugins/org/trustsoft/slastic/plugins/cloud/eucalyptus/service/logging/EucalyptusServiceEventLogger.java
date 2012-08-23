/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

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
