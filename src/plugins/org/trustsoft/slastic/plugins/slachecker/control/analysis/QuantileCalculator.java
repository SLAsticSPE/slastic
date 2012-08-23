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

/**
 * 
 */
package org.trustsoft.slastic.plugins.slachecker.control.analysis;

import java.util.concurrent.ConcurrentSkipListSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slachecker.control.ServiceIDDoesNotExistException;
import org.trustsoft.slastic.plugins.slachecker.control.modelManager.SLOModelManager;
import org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.monitoringRecord.sla.SLOMonitoringRecord;

/**
 * Class for calculating the given quantiles for a given service which is identified via serviceID.
 * @author Lena Stoever
 * 
 */
public class QuantileCalculator {

    private final Log log = LogFactory.getLog(QuantileCalculator.class);
    private final SLOModelManager mng;

    /**
     * Simple constructor of the QuantileCalculator.
     */
    public QuantileCalculator(final SLOModelManager mng) {
        log.info("QuantileCalculatorThread created!");
        this.mng = mng;
    }

    /**
     * The main method of this class, which is responsible for the calculation of the quantiles.
     * @param quantiles array of that identifies the quantiles in percent
     * @param serviceID ID that identified the services for which the quantiles should be calculated
     * @return array of results
     * @throws ServiceIDDoesNotExistException 
     */
    public long[] getResponseTimeQuantiles(float[] quantiles, int serviceID) throws ServiceIDDoesNotExistException {
        long[] retVal;
        //System.out.println("Quantile Request for ServiceID: " + serviceID);
        retVal = new long[quantiles.length];

        //rtArr = new SLOMonitoringRecord[this.mng.getResponseTimes(serviceID).size()];
        ConcurrentSkipListSet<SLOMonitoringRecord> rtSet = mng.getResponseTimes(serviceID); // never null
        SLOMonitoringRecord[] rtArr = rtSet.toArray(new SLOMonitoringRecord[0]);
        //Calculating quantiles, if there are any response times available
        for (int i = 0; i < retVal.length; i++) {
            try {
                // for the calculation check out the thesis ;)
                if (rtArr.length == 0){
                    retVal[i] = -1;
                    continue;
                }
                if ((rtArr.length % (1.0f / (quantiles[i])) != 0.0f)) {
                    int index = (int) ((rtArr.length + 1) * (quantiles[i])) - 1;
                    //log.info("rtsetSize"+rtArr.length+" und der Index: "+index);
                    retVal[i] = rtArr[index].rtNseconds;
                    //log.info("NEW Quantiles calculated............." + retVal[i] + "......................");

                } else {
                    int index = (int) ((rtArr.length) * (quantiles[i])) - 1;
                    //log.info(rtArr.length);
                    retVal[i] = (long) (0.5 * (rtArr[index].rtNseconds + rtArr[index + 1].rtNseconds));
                    //log.info("NEW Quantile calculated.............." + retVal[i] + ".....................");
                }
            } catch (Exception e) {
                log.error(e);
            }
        }
        return retVal;
    }

    public void terminate() {
    }
}
