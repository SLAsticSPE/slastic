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

package kieker.tools.slastic.tests.junit.framework.monitoring.reconstruction;

import java.util.ArrayList;

import junit.framework.Assert;


import kieker.tools.slastic.metamodel.monitoring.MemSwapUsage;

import kieker.common.record.system.MemSwapUsageRecord;
import kieker.tools.slastic.plugins.slasticImpl.ModelManager;
import kieker.tools.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;
import kieker.tools.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.MemSwapUsageRecordTransformationFilter;

/**
 * Tests if the {@link ExecutionRecordTransformationFilter} filter correctly
 * re-uses existing model entities.
 * 
 * @author Andre van Hoorn
 */
public class TestMemSwapUsageTransformationFilterSameResourceTypeOnTwoContainers extends AbstractReconstructionTest {

	private final MemSwapUsageRecord kiekerRecord1;
	{
		/*
		 * notice that the values make no sense; it's only important to choose
		 * different ones
		 */
		long usage = 7676l;
		final long timestamp = 456346l;
		final String hostname = "theHostname1";
		final long memTotal = usage++;
		final long memUsed = usage++;
		final long memFree = usage++;
		final long swapTotal = usage++;
		final long swapUsed = usage++;
		final long swapFree = usage++;

		this.kiekerRecord1 = new MemSwapUsageRecord(timestamp, hostname, memTotal, memUsed, memFree, swapTotal, swapUsed, swapFree);
	}

	private final MemSwapUsageRecord kiekerRecord2;
	{
		/*
		 * notice that the values make no sense; it's only important to choose
		 * different ones
		 */
		long usage = 7676l;
		final long timestamp = 456346l;
		final String hostname = "theHostname2";
		final long memTotal = usage++;
		final long memUsed = usage++;
		final long memFree = usage++;
		final long swapTotal = usage++;
		final long swapUsed = usage++;
		final long swapFree = usage++;

		this.kiekerRecord2 = new MemSwapUsageRecord(timestamp, hostname, memTotal, memUsed, memFree, swapTotal, swapUsed, swapFree);
	}

	public void testEntitiesReUsed() {
		/* Create type repository manager for empty type repository */
		final ModelManager modelManager = new ModelManager();

		final MemSwapUsageRecordTransformationFilter filter = new MemSwapUsageRecordTransformationFilter(modelManager);

		/* Used to receive the created operations from the filter */
		final ArrayList<MemSwapUsage> slasticRef = new ArrayList<MemSwapUsage>();

		/* Let the filter transform the same record twice */
		slasticRef.add(filter.transformMemSwapUsageRecord(this.kiekerRecord1));
		slasticRef.add(filter.transformMemSwapUsageRecord(this.kiekerRecord2));

		final MemSwapUsage slasticRecordA = slasticRef.get(0);
		final MemSwapUsage slasticRecordB = slasticRef.get(1);

		Assert.assertSame("Expecting same type for both records",
				slasticRecordA.getResource().getResourceSpecification().getResourceType(), slasticRecordB.getResource().getResourceSpecification().getResourceType());
	}
}
