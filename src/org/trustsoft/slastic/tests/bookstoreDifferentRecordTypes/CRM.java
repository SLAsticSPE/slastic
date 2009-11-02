package org.trustsoft.slastic.tests.bookstoreDifferentRecordTypes;

import kieker.tpmon.annotation.TpmonExecutionMonitoringProbe;
import org.trustsoft.slastic.monadapt.annotation.SLAsticSLAMonitoringProbe;

/**
 * org.trustsoft.slastic.tests.bookstoreDifferentRecordTypes.CRM.java
 *
 * ==================LICENCE=========================
 * Copyright 2006-2009 Kieker Project
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
 * ==================================================
 *
 * @author Matthias Rohr
 * History:
 * 2008/01/09: Refactoring for the first release of
 *             Kieker and publication under an open source licence
 * 2007-04-18: Initial version
 *
 */

public class CRM {
 
    /**
     * This method will be monitored, since it has an annotation.
     */
    @SLAsticSLAMonitoringProbe(serviceId=12)
    @TpmonExecutionMonitoringProbe()
    public static void getOffers(){
	Catalog.getBook(true);
	try {
		Thread.sleep(200);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
}
