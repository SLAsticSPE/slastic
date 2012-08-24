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

package org.trustsoft.slastic.tests.junit.framework.esper.externalTime;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.time.CurrentTimeEvent;
import com.espertech.esper.client.time.TimerControlEvent;

/**
 * Examples to understand the Esper's API for use with external clock source.
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestSetGetCurrentTime extends TestCase {

	/**
	 * Sends a {@link TimerControlEvent.ClockType#CLOCK_EXTERNAL} event to a
	 * running {@link EPRuntime}, sends a {@link CurrentTimeEvent} and
	 * subsequently compares its value with the return value of {@link EPRuntime#getCurrentTime().
	 */
	public void testExternalTimeIsCurrenTime1() {
		final int timestamp = 17454;
		final Configuration config = new Configuration();
		final EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(config);
		final EPRuntime epRuntime = epService.getEPRuntime();
		epRuntime.sendEvent(new TimerControlEvent(TimerControlEvent.ClockType.CLOCK_EXTERNAL));
		epRuntime.sendEvent(new CurrentTimeEvent(timestamp));
		Assert.assertEquals("Unexpected current time returned from epRuntime. ", epRuntime.getCurrentTime(), timestamp);
	}

	/**
	 * Disables the internal time in a {@link Configuration}, starts a {@link EPServiceProvider} with this {@link Configuration}, sends a {@link CurrentTimeEvent}
	 * and subsequently compares its value with the
	 * return value of {@link EPRuntime#getCurrentTime().
	 */
	public void testExternalTimeIsCurrenTime2() {
		final int timestamp = 17;
		final Configuration config = new Configuration();
		config.getEngineDefaults().getThreading().setInternalTimerEnabled(false);
		final EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(config);
		final EPRuntime epRuntime = epService.getEPRuntime();
		epRuntime.sendEvent(new CurrentTimeEvent(timestamp));
		Assert.assertEquals("Unexpected current time returned from epRuntime. ", epRuntime.getCurrentTime(), timestamp);
	}
}
