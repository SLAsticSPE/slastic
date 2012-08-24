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

package org.trustsoft.slastic.tests.junit.framework.esper.externalTime.outputLimiting;

import java.util.List;

import junit.framework.Assert;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public abstract class AbstractExampleTestExecutor {
	/**
	 * 
	 * 
	 * @param epServiceProvider
	 * @param inputStream
	 * @param expectedIStream
	 * @param expectedRStream
	 * @return
	 */
	public static final IRStreamCollector executeTest(
			final EPServiceProvider epServiceProvider,
			final List<Object> inputStream,
			final List<StreamRecord> expectedIStream,
			final List<StreamRecord> expectedRStream,
			final String eplStatementString) {
		/* Create stream collector */
		final IRStreamCollector irStreamCollector =
				new IRStreamCollector(epServiceProvider);

		/* Create and register statement; register stream collector */
		final EPStatement stm =
				epServiceProvider.getEPAdministrator().createEPL(
						eplStatementString);
		stm.setSubscriber(irStreamCollector);

		/* Send events from input stream */
		for (final Object event : inputStream) {
			epServiceProvider.getEPRuntime().sendEvent(event);
		}

		/* Check result */
		Assert.assertEquals("Unexpected insert stream", expectedIStream,
				irStreamCollector.getiStream());
		Assert.assertEquals("Unexpected remove stream", expectedRStream,
				irStreamCollector.getrStream());
		return irStreamCollector;
	}
}
