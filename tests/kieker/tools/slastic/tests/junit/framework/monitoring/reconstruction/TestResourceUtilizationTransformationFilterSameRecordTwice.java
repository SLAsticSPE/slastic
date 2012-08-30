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


import kieker.tools.slastic.metamodel.monitoring.ResourceUtilization;

import kieker.common.record.system.ResourceUtilizationRecord;
import kieker.tools.slastic.plugins.slasticImpl.ModelManager;
import kieker.tools.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;
import kieker.tools.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ResourceUtilizationRecordTransformationFilter;

/**
 * Tests if the {@link ExecutionRecordTransformationFilter} filter correctly
 * re-uses existing model entities.
 * 
 * @author Andre van Hoorn
 */
public class TestResourceUtilizationTransformationFilterSameRecordTwice extends
		AbstractReconstructionTest {

	private final ResourceUtilizationRecord kiekerRecord = new ResourceUtilizationRecord(76767676l, "theHostname", "theResourceName", 0.56);

	public void testEntitiesReUsed() {
		/* Create type repository manager for empty type repository */
		final ModelManager modelManager = new ModelManager();

		final ResourceUtilizationRecordTransformationFilter filter = new ResourceUtilizationRecordTransformationFilter(modelManager);

		/* Used to receive the created operations from the filter */
		final ArrayList<ResourceUtilization> slasticRef = new ArrayList<ResourceUtilization>();

		/* Let the filter transform the same record twice */
		slasticRef.add(filter.transformResourceUtilizationRecord(this.kiekerRecord));
		slasticRef.add(filter.transformResourceUtilizationRecord(this.kiekerRecord));

		final ResourceUtilization slasticRecordA = slasticRef.get(0);
		final ResourceUtilization slasticRecordB = slasticRef.get(1);

		this.checkResult(slasticRecordA, slasticRecordB);
	}

	/**
	 * Performs a number of validity checks on the model and the record.
	 */
	private void checkResult(final ResourceUtilization slasticRecordA,
			final ResourceUtilization slasticRecordB) {
		Assert.assertNotNull("slasticRecordA must not be null", slasticRecordA);
		Assert.assertNotNull("slasticRecordB must not be null", slasticRecordB);

		{
			/*
			 * Compare all record elements:
			 */
			Assert.assertEquals("Unexpected timestamp", slasticRecordA.getTimestamp(), slasticRecordB.getTimestamp());
			Assert.assertSame("Unexpected resource", slasticRecordA.getResource(), slasticRecordB.getResource());
			Assert.assertEquals("Unexpected utilization", slasticRecordA.getUtilization(), slasticRecordB.getUtilization());
		}
	}
}
