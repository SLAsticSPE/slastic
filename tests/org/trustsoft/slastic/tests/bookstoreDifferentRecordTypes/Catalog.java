package org.trustsoft.slastic.tests.bookstoreDifferentRecordTypes;

import java.util.Random;

import org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.annotation.SLAsticSLAMonitoringProbe;

import kieker.monitoring.annotation.OperationExecutionMonitoringProbe;
import kieker.monitoring.probe.manual.BranchingProbe;

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
