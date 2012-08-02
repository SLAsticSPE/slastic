/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import kieker.common.record.controlflow.OperationExecutionRecord;
import de.cau.se.slastic.metamodel.monitoring.OperationExecution;

/**
 *
 * @author Andre van Hoorn
 */
public interface IExecutionRecordTransformation {

    /**
     * Transforms a Kieker operation execution record of type
     * ${@link OperationExecutionRecord} into a SLAstic execution record of
     * type ${@link OperationExecution}.
     *
     * @param operationExecutionRecord
     * @return
     */
    public OperationExecution transformExecutionRecord (OperationExecutionRecord operationExecutionRecord);
}
