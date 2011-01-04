package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import kieker.common.record.ResourceUtilizationRecord;
import de.cau.se.slastic.metamodel.monitoring.ResourceUtilization;

/**
 *
 * @author Andre van Hoorn
 */
public interface IResourceUtilizationRecordTransformation {

	/**
     * Transforms a Kieker resource utilization record of type
     * ${@link ResourceUtilizationRecord} into a SLAstic execution record of
     * type ${@link ResourceUtilization}
	 * 
	 * @param resourceUtilizationRecord
	 * @return
	 */
    public ResourceUtilization transformResourceUtilizationRecord (ResourceUtilizationRecord resourceUtilizationRecord);
}
