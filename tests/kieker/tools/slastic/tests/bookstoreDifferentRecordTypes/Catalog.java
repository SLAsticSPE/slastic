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

package kieker.tools.slastic.tests.bookstoreDifferentRecordTypes;

import java.util.Random;


import kieker.monitoring.annotation.OperationExecutionMonitoringProbe;
import kieker.monitoring.probe.manual.BranchingProbe;
import kieker.tools.slastic.plugins.slachecker.monitoring.kieker.annotation.SLAsticSLAMonitoringProbe;

/**
 * @author Matthias Rohr, Andre van Hoorn
 * 
 *         History:
 *         2009/06/23: Adapted for "different record type test"
 *         2008/01/09: Refactoring for the first release of
 *         Kieker and publication under an open source licence
 *         2007-04-18: Initial version
 * 
 */
public class Catalog {
	private static final Random rnd = new Random(200);

	@SLAsticSLAMonitoringProbe(serviceId = 77)
	@OperationExecutionMonitoringProbe()
	public static void getBook(final boolean complexQuery) {
		if (complexQuery) {
			BranchingProbe.monitorBranch(1, 0);
			Bookstore.waitabit(1000 + rnd.nextInt(1000));
		} else {
			BranchingProbe.monitorBranch(1, 1);
			Bookstore.waitabit(2);
		}
	}
}
