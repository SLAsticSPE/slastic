package org.trustsoft.slastic.tests.junit.framework.esper.externalTime.outputLimiting;

import java.util.List;

import junit.framework.Assert;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;

public abstract class ExampleTestExecutor {
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
