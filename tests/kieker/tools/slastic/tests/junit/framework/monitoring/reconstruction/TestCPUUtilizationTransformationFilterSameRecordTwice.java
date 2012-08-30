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


import kieker.tools.slastic.metamodel.monitoring.CPUUtilization;

import kieker.common.record.system.CPUUtilizationRecord;
import kieker.tools.slastic.plugins.slasticImpl.ModelManager;
import kieker.tools.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.CPUUtilizationRecordTransformationFilter;
import kieker.tools.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;

/**
 * Tests if the {@link ExecutionRecordTransformationFilter} filter correctly
 * re-uses existing model entities.
 * 
 * @author Andre van Hoorn
 */
public class TestCPUUtilizationTransformationFilterSameRecordTwice extends AbstractReconstructionTest {

	private final CPUUtilizationRecord kiekerRecord;
	{
		/*
		 * notice that the values make no sense; it's only important to choose
		 * different ones
		 */
		double util = 0.11;
		final long timestamp = 456346l;
		final String hostname = "theHostname";
		final String cpuID = "2";

		final double user = util++;
		final double system = util++;
		final double wait = util++;
		final double nice = util++;
		final double irq = util++;
		final double totalUtilization = util++;
		final double idle = util++;

		this.kiekerRecord = new CPUUtilizationRecord(timestamp, hostname, cpuID, user, system, wait, nice, irq, totalUtilization, idle);
	}

	public void testEntitiesReUsed() {
		/* Create type repository manager for empty type repository */
		final ModelManager modelManager = new ModelManager();

		final CPUUtilizationRecordTransformationFilter filter = new CPUUtilizationRecordTransformationFilter(modelManager);

		/* Used to receive the created operations from the filter */
		final ArrayList<CPUUtilization> slasticRef = new ArrayList<CPUUtilization>();

		/* Let the filter transform the same record twice */
		slasticRef.add(filter.transformCPUUtilizationRecord(this.kiekerRecord));
		slasticRef.add(filter.transformCPUUtilizationRecord(this.kiekerRecord));

		final CPUUtilization slasticRecordA = slasticRef.get(0);
		final CPUUtilization slasticRecordB = slasticRef.get(1);

		this.checkResult(slasticRecordA, slasticRecordB);
	}

	/**
	 * Performs a number of validity checks on the model and the record.
	 */
	private void checkResult(final CPUUtilization slasticRecordA, final CPUUtilization slasticRecordB) {
		Assert.assertNotNull("slasticRecordA must not be null", slasticRecordA);
		Assert.assertNotNull("slasticRecordB must not be null", slasticRecordB);

		{
			/*
			 * Compare all record elements:
			 */
			Assert.assertEquals("Unexpected timestamp", slasticRecordA.getTimestamp(), slasticRecordB.getTimestamp());
			Assert.assertSame("Unexpected resource", slasticRecordA.getResource(), slasticRecordB.getResource());

			Assert.assertEquals("Unexpected idle time", slasticRecordA.getIdle(), slasticRecordB.getIdle());
			Assert.assertEquals("Unexpected irq time", slasticRecordA.getIrq(), slasticRecordB.getIrq());
			Assert.assertEquals("Unexpected nice time", slasticRecordA.getNice(), slasticRecordB.getNice());
			Assert.assertEquals("Unexpected system time", slasticRecordA.getSystem(), slasticRecordB.getSystem());
			Assert.assertEquals("Unexpected combine utilization", slasticRecordA.getCombined(), slasticRecordB.getCombined());
			Assert.assertEquals("Unexpected user time", slasticRecordA.getUser(), slasticRecordB.getUser());
			Assert.assertEquals("Unexpected wait time", slasticRecordA.getWait(), slasticRecordB.getWait());
		}
	}
}
