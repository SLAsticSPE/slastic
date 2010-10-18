package org.trustsoft.slastic.tests.junit.framework.esper.externalTime.outputLimiting;

import junit.framework.TestCase;

/**
 * Scenario from Appendix A.2.2 of Esper's Reference Guide.
 * 
 * @author Andre van Hoorn
 * 
 */
public class Test__A_2_2__UnaggregatedUngroupedRateLimitingDefault extends
		TestCase {

	private final static String stmString =
			"select irstream symbol, volume, price "
					+ "from " + MarketData.class.getName()
					+ ".win:time(5.5 sec) "
					+ "output every 1 seconds";
	
	/**
	 * Executes the example presented in Appendix A.2.1.
	 */
	public void testReferenceExample() {
		/* Execute the test */
		ExampleTestExecutor
				.executeTest(
						EPServiceFactory.defaultInstanceExternalClock(200),
						ExampleDataFactory.A_2_2__inputEvents(),
						ExampleDataFactory.A_2_2__expectedIStream(),
						ExampleDataFactory.A_2_2__expectedRStream(),
						Test__A_2_2__UnaggregatedUngroupedRateLimitingDefault.stmString);
	}
}