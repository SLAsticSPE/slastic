package org.trustsoft.slastic.tests.bookstoreDifferentRecordTypes;

import org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.annotation.SLAsticSLAMonitoringProbe;

import kieker.monitoring.annotation.OperationExecutionMonitoringProbe;

/**
 * @author Matthias Rohr
 *         History:
 *         2008/01/09: Refactoring for the first release of
 *         Kieker and publication under an open source licence
 *         2007-04-18: Initial version
 * 
 */

public class CRM {

	/**
	 * This method will be monitored, since it has an annotation.
	 */
	@SLAsticSLAMonitoringProbe(serviceId = 12)
	@OperationExecutionMonitoringProbe()
	public static void getOffers() {
		Catalog.getBook(true);
		try {
			Thread.sleep(200);
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
