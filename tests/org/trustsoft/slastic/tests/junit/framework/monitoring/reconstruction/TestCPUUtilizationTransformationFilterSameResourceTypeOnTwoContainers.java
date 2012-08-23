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

package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction;

import java.util.ArrayList;

import junit.framework.Assert;
import kieker.common.record.system.CPUUtilizationRecord;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.CPUUtilizationRecordTransformationFilter;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;

import de.cau.se.slastic.metamodel.monitoring.CPUUtilization;

/**
 * Tests if the {@link ExecutionRecordTransformationFilter} filter correctly
 * re-uses existing model entities.
 * 
 * @author Andre van Hoorn
 */
public class TestCPUUtilizationTransformationFilterSameResourceTypeOnTwoContainers
		extends AbstractReconstructionTest {

	private final CPUUtilizationRecord kiekerRecord1;
	{
		/*
		 * notice that the values make no sense; it's only important to choose
		 * different ones
		 */
		double util = 0.11;
		final long timestamp = 456346l;
		final String hostname = "theHostname1";
		final String cpuID = "2";

		final double user = util++;
		final double system = util++;
		final double wait = util++;
		final double nice = util++;		
		final double irq = util++;
		final double totalUtilization = util++;
		final double idle = util++;

		this.kiekerRecord1 = new CPUUtilizationRecord(timestamp, hostname, cpuID, user, system, wait, nice, irq, totalUtilization, idle);
	}

	private final CPUUtilizationRecord kiekerRecord2;
	{
		/*
		 * notice that the values make no sense; it's only important to choose
		 * different ones
		 */
		double util = 0.11;
		final long timestamp = 456346l;
		final String hostname = "theHostname2";
		final String cpuID = "2";

		final double user = util++;
		final double system = util++;
		final double wait = util++;
		final double nice = util++;		
		final double irq = util++;
		final double totalUtilization = util++;
		final double idle = util++;

		this.kiekerRecord2 = new CPUUtilizationRecord(timestamp, hostname, cpuID, user, system, wait, nice, irq, totalUtilization, idle);
	}

	public void testEntitiesReUsed() {
		/* Create type repository manager for empty type repository */
		final ModelManager modelManager = new ModelManager();

		final CPUUtilizationRecordTransformationFilter filter =
				new CPUUtilizationRecordTransformationFilter(modelManager);

		/* Used to receive the created operations from the filter */
		final ArrayList<CPUUtilization> slasticRef =
				new ArrayList<CPUUtilization>();

		/* Let the filter transform the same record twice */
		slasticRef
				.add(filter.transformCPUUtilizationRecord(this.kiekerRecord1));
		slasticRef
				.add(filter.transformCPUUtilizationRecord(this.kiekerRecord2));

		final CPUUtilization slasticRecordA = slasticRef.get(0);
		final CPUUtilization slasticRecordB = slasticRef.get(1);

		Assert.assertSame("Expecting same type for both records",
				slasticRecordA.getResource().getResourceSpecification()
						.getResourceType(), slasticRecordB.getResource()
						.getResourceSpecification().getResourceType());
	}
}
