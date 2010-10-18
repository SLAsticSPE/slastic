package org.trustsoft.slastic.tests.junit.framework.esper.externalTime.outputLimiting;

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
	public static EPServiceProvider defaultInstanceExternalClock (final long startTime){
		final Configuration config = new Configuration();
		final EPServiceProvider epService =
				EPServiceProviderManager.getDefaultProvider(config);
		final EPRuntime epRuntime = epService.getEPRuntime();
		epRuntime.sendEvent(new TimerControlEvent(
				TimerControlEvent.ClockType.CLOCK_EXTERNAL));
		epRuntime.sendEvent(new CurrentTimeEvent(startTime));
		return epService;
	}
}
