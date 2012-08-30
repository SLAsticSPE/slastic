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

import junit.framework.TestCase;

/**
 * Scenario from Appendix A.5.6 of Esper's Reference Guide.
 * 
 * @author Andre van Hoorn
 * 
 */
public class Test__A_5_6__FullyAggregatedGroupedRateLimitingSnapshot extends
		TestCase {

	private final static String stmString =
			"select irstream symbol, sum(price) "
					+ "from " + MarketData.class.getName()
					+ ".win:time(5.5 sec) "
					+ "group by symbol "
					+ "output snapshot every 1 seconds "
					+ "order by symbol";

	/**
	 * Executes the scenario.
	 */
	public void testReferenceExample() {
		/* Execute the test */
		AbstractExampleTestExecutor.executeTest(
				EPServiceFactory.defaultInstanceExternalClock(200),
				ExampleDataFactory.A_5_6__inputEvents(),
				ExampleDataFactory.A_5_6__expectedIStream(),
				ExampleDataFactory.A_5_6__expectedRStream(),
				Test__A_5_6__FullyAggregatedGroupedRateLimitingSnapshot.stmString);
	}
}
