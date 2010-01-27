/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.plugins.simulation.kiekerReaderWriterPipe;

import kieker.common.logReader.LogReaderExecutionException;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

/**
 *
 * @author Andre van Hoorn
 */
public interface IPipeReader {
    public void newRecord (AbstractKiekerMonitoringRecord rec) throws LogReaderExecutionException;
}
