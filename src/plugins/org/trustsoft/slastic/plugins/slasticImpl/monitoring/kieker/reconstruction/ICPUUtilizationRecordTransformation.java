package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import kieker.common.record.CPUUtilizationRecord;
import de.cau.se.slastic.metamodel.monitoring.CPUUtilization;

/**
 *
 * @author Andre van Hoorn
 */
public interface ICPUUtilizationRecordTransformation {

	/**
     * Transforms a Kieker cpu utilization record of type
     * ${@link CPUUtilizationRecord} into a SLAstic execution record of
     * type ${@link CPUUtilization}.
	 * 
	 * @param cpuUtilizationRecord
	 * @return
	 */
    public CPUUtilization transformCPUUtilizationRecord (CPUUtilizationRecord cpuUtilizationRecord);
}
