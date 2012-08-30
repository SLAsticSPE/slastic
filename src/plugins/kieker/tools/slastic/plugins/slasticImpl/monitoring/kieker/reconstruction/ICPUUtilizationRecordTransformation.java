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

package kieker.tools.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import kieker.common.record.system.CPUUtilizationRecord;
import kieker.tools.slastic.metamodel.monitoring.CPUUtilization;

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
