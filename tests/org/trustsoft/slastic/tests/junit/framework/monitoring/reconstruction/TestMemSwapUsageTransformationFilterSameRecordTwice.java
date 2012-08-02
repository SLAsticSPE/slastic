package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction;

import java.util.ArrayList;

import junit.framework.Assert;
import kieker.common.record.system.MemSwapUsageRecord;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.MemSwapUsageRecordTransformationFilter;

import de.cau.se.slastic.metamodel.monitoring.MemSwapUsage;

/**
 * Tests if the {@link ExecutionRecordTransformationFilter} filter correctly
 * re-uses existing model entities.
 * 
 * @author Andre van Hoorn
 */
public class TestMemSwapUsageTransformationFilterSameRecordTwice extends
		AbstractReconstructionTest {

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

	public void testEntitiesReUsed() {
		/* Create type repository manager for empty type repository */
		final ModelManager modelManager = new ModelManager();

		final MemSwapUsageRecordTransformationFilter filter =
				new MemSwapUsageRecordTransformationFilter(modelManager);

		/* Used to receive the created operations from the filter */
		final ArrayList<MemSwapUsage> slasticRef =
				new ArrayList<MemSwapUsage>();

		/* Let the filter transform the same record twice */
		slasticRef.add(filter.transformMemSwapUsageRecord(this.kiekerRecord));
		slasticRef.add(filter.transformMemSwapUsageRecord(this.kiekerRecord));

		final MemSwapUsage slasticRecordA = slasticRef.get(0);
		final MemSwapUsage slasticRecordB = slasticRef.get(1);

		this.checkResult(slasticRecordA, slasticRecordB);
	}

	/**
	 * Performs a number of validity checks on the model and the record.
	 */
	private void checkResult(final MemSwapUsage slasticRecordA,
			final MemSwapUsage slasticRecordB) {
		Assert.assertNotNull("slasticRecordA must not be null", slasticRecordA);
		Assert.assertNotNull("slasticRecordB must not be null", slasticRecordB);

		{
			/*
			 * Compare all record elements:
			 */
			Assert.assertEquals("Unexpected timestamp",
					slasticRecordA.getTimestamp(),
					slasticRecordB.getTimestamp());
			Assert.assertSame("Unexpected resource",
					slasticRecordA.getResource(), slasticRecordB.getResource());
			
			Assert.assertEquals("Unexpected memFree value",
					slasticRecordA.getMemFreeBytes(),
					slasticRecordB.getMemFreeBytes());
			Assert.assertEquals("Unexpected memUsed value",
					slasticRecordA.getMemUsedBytes(),
					slasticRecordB.getMemUsedBytes());
			Assert.assertEquals("Unexpected swapFree value",
					slasticRecordA.getSwapFreeBytes(),
					slasticRecordB.getSwapFreeBytes());
			Assert.assertEquals("Unexpected swapUsed value",
					slasticRecordA.getSwapUsedBytes(),
					slasticRecordB.getSwapUsedBytes());
		}
	}
}
