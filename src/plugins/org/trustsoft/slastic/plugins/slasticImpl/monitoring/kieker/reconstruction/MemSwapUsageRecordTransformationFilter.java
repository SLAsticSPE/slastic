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

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters.ISynchronousTransformationFilter;

import de.cau.se.slastic.metamodel.core.IEvent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.Resource;
import de.cau.se.slastic.metamodel.monitoring.MemSwapUsage;
import de.cau.se.slastic.metamodel.monitoring.MonitoringFactory;

import kieker.common.record.IMonitoringRecord;
import kieker.common.record.system.MemSwapUsageRecord;

/**
 * 
 * @author Andre van Hoorn
 */
public class MemSwapUsageRecordTransformationFilter extends AbstractModelReconstructionComponent
		implements ISynchronousTransformationFilter, IMemSwapUsageRecordTransformation {

	// private static final Log log =
	// LogFactory.getLog(MemSwapUsageRecordTransformationFilter.class);

	/**
	 * 
	 * @param modelManager
	 */
	public MemSwapUsageRecordTransformationFilter(final ModelManager modelManager) {
		super(modelManager);
	}

	@Override
	public MemSwapUsage transformMemSwapUsageRecord(final MemSwapUsageRecord memSwapUsageRecord) {

		/* Will become the return value. */
		final MemSwapUsage newUsage = MonitoringFactory.eINSTANCE.createMemSwapUsage();

		final ExecutionContainer executionContainer = this.lookupOrCreateExecutionContainerByName(memSwapUsageRecord.getHostname());

		final Resource resource =
				this.lookupOrCreateMemSwapResource(
						AbstractModelReconstructionComponent.createMemSwapResourceSpecName(),
						memSwapUsageRecord.getMemTotal(), memSwapUsageRecord.getSwapTotal(),
						AbstractModelReconstructionComponent.DEFAULT_MEMSWAP_RESOURCE_TYPE_NAME,
						executionContainer);

		// And finally, the simple part:
		newUsage.setTimestamp(memSwapUsageRecord.getTimestamp());
		newUsage.setResource(resource);

		newUsage.setMemFreeBytes(memSwapUsageRecord.getMemFree());
		newUsage.setMemUsedBytes(memSwapUsageRecord.getMemUsed());
		newUsage.setSwapFreeBytes(memSwapUsageRecord.getSwapFree());
		newUsage.setSwapUsedBytes(memSwapUsageRecord.getSwapUsed());

		return newUsage;
	}

	@Override
	public IEvent transform(final IMonitoringRecord record) {
		if (!(record instanceof MemSwapUsageRecord)) {
			return null;
		}

		final MemSwapUsageRecord usageRecord = (MemSwapUsageRecord) record;

		return this.transformMemSwapUsageRecord(usageRecord);
	}
}
