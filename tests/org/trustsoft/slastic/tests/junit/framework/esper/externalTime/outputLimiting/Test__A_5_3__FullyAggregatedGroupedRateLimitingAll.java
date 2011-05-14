package org.trustsoft.slastic.tests.junit.framework.esper.externalTime.outputLimiting;

import junit.framework.TestCase;

/**
 * Scenario from Appendix A.5.3 of Esper's Reference Guide.
 * 
 * @author Andre van Hoorn
 * 
 */
public class Test__A_5_3__FullyAggregatedGroupedRateLimitingAll extends
		TestCase {

	private final static String stmString =
			"select irstream symbol, sum(price) "
					+ "from " + MarketData.class.getName()
					+ ".win:time(5.5 sec) "
					+ "group by symbol "
					+ "output all every 1 seconds " 
					+ "order by symbol";
	
	/**
	 * Executes the scenario.
	 */
	public void testReferenceExample() {
		/* Execute the test */
		AbstractExampleTestExecutor
				.executeTest(
						EPServiceFactory.defaultInstanceExternalClock(200),
						ExampleDataFactory.A_5_3__inputEvents(),
						ExampleDataFactory.A_5_3__expectedIStream(),
						ExampleDataFactory.A_5_3__expectedRStream(),
						Test__A_5_3__FullyAggregatedGroupedRateLimitingAll.stmString);
	}
}