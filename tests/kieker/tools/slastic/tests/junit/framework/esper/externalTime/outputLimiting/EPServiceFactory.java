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

package kieker.tools.slastic.tests.junit.framework.esper.externalTime.outputLimiting;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.time.CurrentTimeEvent;
import com.espertech.esper.client.time.TimerControlEvent;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class EPServiceFactory {

	/**
	 * Returns a new {@link EPServiceProvider} with external clocking enabled
	 * and the time set to startTime.
	 * 
	 * @param startTime
	 * @return
	 */
	public static EPServiceProvider defaultInstanceExternalClock(final long startTime) {
		final Configuration config = new Configuration();
		final EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(config);
		final EPRuntime epRuntime = epService.getEPRuntime();
		epRuntime.sendEvent(new TimerControlEvent(TimerControlEvent.ClockType.CLOCK_EXTERNAL));
		epRuntime.sendEvent(new CurrentTimeEvent(startTime));
		return epService;
	}
}
