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

package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import kieker.common.record.system.MemSwapUsageRecord;
import de.cau.se.slastic.metamodel.monitoring.MemSwapUsage;

/**
 * 
 * @author Andre van Hoorn
 */
public interface IMemSwapUsageRecordTransformation {

	/**
	 * Transforms a Kieker memory/swap utilization record of type $
	 * {@link MemSwapUsageRecord} into a SLAstic execution record of type $
	 * {@link MemSwapUsage}.
	 * 
	 * @param memSwapUsageRecord
	 * @return
	 */
	public MemSwapUsage transformMemSwapUsageRecord(
			MemSwapUsageRecord memSwapUsageRecord);
}
