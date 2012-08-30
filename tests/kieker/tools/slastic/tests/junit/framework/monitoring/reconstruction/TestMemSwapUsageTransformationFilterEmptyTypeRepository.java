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

import junit.framework.Assert;


import kieker.tools.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification;
import kieker.tools.slastic.metamodel.executionEnvironment.Resource;
import kieker.tools.slastic.metamodel.executionEnvironment.ResourceSpecification;
import kieker.tools.slastic.metamodel.monitoring.MemSwapUsage;
import kieker.tools.slastic.metamodel.typeRepository.ResourceType;
import kieker.tools.slastic.metamodel.typeRepository.resourceTypes.MemSwapType;

import kieker.common.record.system.MemSwapUsageRecord;
import kieker.tools.slastic.plugins.slasticImpl.ModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.NameUtils;
import kieker.tools.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.AbstractModelReconstructionComponent;
import kieker.tools.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.MemSwapUsageRecordTransformationFilter;

/**
 * Tests if the {@link MemSwapUsageRecordTransformationFilter} filter correctly
 * transforms a {@link MemSwapUsageRecord} into an {@link MemSwapUsage} including the required initializations of an empty system model.
 * 
 * @author Andre van Hoorn
 */
public class TestMemSwapUsageTransformationFilterEmptyTypeRepository extends AbstractReconstructionTest {

	private final MemSwapUsageRecord kiekerRecord;
	{
		/*
		 * notice that the values make no sense; it's only important to choose
		 * different ones
		 */
		long usage = 7676l;
		final long timestamp = 456346l;
		final String hostname = "theHostname";
		final long memTotal = usage++;
		final long memUsed = usage++;
		final long memFree = usage++;
		final long swapTotal = usage++;
		final long swapUsed = usage++;
		final long swapFree = usage++;

		this.kiekerRecord = new MemSwapUsageRecord(timestamp, hostname, memTotal, memUsed, memFree, swapTotal, swapUsed, swapFree);
	}

	public void testTransformRecordEmptyModel() {
		/* Create type repository manager for empty type repository */
		final ModelManager modelManager = new ModelManager();

		final MemSwapUsageRecordTransformationFilter filter = new MemSwapUsageRecordTransformationFilter(modelManager);

		final MemSwapUsage slasticRecord = filter.transformMemSwapUsageRecord(this.kiekerRecord);

		this.checkResult(modelManager, slasticRecord);
	}

	/**
	 * Performs a number of validity checks on the model and the record.
	 * 
	 * @param mgr
	 * @param slasticRec
	 */
	private void checkResult(final ModelManager mgr,
			final MemSwapUsage slasticRec) {
		Assert.assertNotNull("slasticRec must not be null", slasticRec);

		{
			/*
			 * This is the easy part: compare the plain values among the
			 * records:
			 */
			Assert.assertEquals("Unexpected timestamp", this.kiekerRecord.getTimestamp(), slasticRec.getTimestamp());
			Assert.assertEquals("Unexpected memFree value", this.kiekerRecord.getMemFree(), slasticRec.getMemFreeBytes());
			Assert.assertEquals("Unexpected memUsed value", this.kiekerRecord.getMemUsed(), slasticRec.getMemUsedBytes());
			Assert.assertEquals("Unexpected swapFree value", this.kiekerRecord.getSwapFree(), slasticRec.getSwapFreeBytes());
			Assert.assertEquals("Unexpected swapUsed value", this.kiekerRecord.getSwapUsed(), slasticRec.getSwapUsedBytes());
		}

		final Resource res = slasticRec.getResource();

		/* Check execution container */
		this.checkExecutionContainerAndType(
				mgr,
				this.kiekerRecord.getHostname(),
				this.kiekerRecord.getHostname() + AbstractModelReconstructionComponent.DEFAULT_TYPE_POSTFIX,
				res.getExecutionContainer());

		/* Check resource specification */
		final ResourceSpecification resSpec = res.getResourceSpecification();
		Assert.assertNotNull("resourceSpecification is null", resSpec);
		Assert.assertTrue("Expected resSpec to be instanceof " + MemSwapResourceSpecification.class + " but found: " + resSpec.getClass(),
				resSpec instanceof MemSwapResourceSpecification);
		Assert.assertEquals("Unexpected resource specification name", AbstractModelReconstructionComponent.createMemSwapResourceSpecName(), resSpec.getName());

		/* Check capacity values */
		final MemSwapResourceSpecification memSwapResSpec = (MemSwapResourceSpecification) resSpec;
		Assert.assertEquals("Unexpected mem capacity", this.kiekerRecord.getMemTotal(), memSwapResSpec.getMemCapacityBytes());
		Assert.assertEquals("Unexpected swap capacity", this.kiekerRecord.getSwapTotal(), memSwapResSpec.getSwapCapacityBytes());

		/* Check resource type */
		final ResourceType resType = resSpec.getResourceType();
		Assert.assertEquals("Unexpected resource type name",
				AbstractModelReconstructionComponent.DEFAULT_MEMSWAP_RESOURCE_TYPE_NAME, NameUtils.createFQName(resType.getPackageName(), resType.getName()));
		Assert.assertTrue("Resource type must be instanceof " + MemSwapType.class + " ; found: " + resType.getClass(), resType instanceof MemSwapType);

		/* TODO: Check total cpu/swap */
	}
}
