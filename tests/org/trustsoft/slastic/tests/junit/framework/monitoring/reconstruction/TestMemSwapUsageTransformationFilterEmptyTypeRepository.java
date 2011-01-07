package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction;

import junit.framework.Assert;
import kieker.common.record.MemSwapUsageRecord;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.MemSwapUsageRecordTransformationFilter;

import de.cau.se.slastic.metamodel.monitoring.MemSwapUsage;

/**
 * Tests if the {@link MemSwapUsageRecordTransformationFilter} filter correctly
 * transforms a {@link MemSwapUsageRecord} into an {@link MemSwapUsage}
 * including the required initializations of an empty system model.
 * 
 * @author Andre van Hoorn
 */
public class TestMemSwapUsageTransformationFilterEmptyTypeRepository extends
		AbstractReconstructionTest {

	private final MemSwapUsageRecord kiekerRecord = new MemSwapUsageRecord();
	{
		/*
		 * notice that the values make no sense; it's only important to choose
		 * different ones
		 */
		long usage = 7676l;
		this.kiekerRecord.setTimestamp(456346l);
		this.kiekerRecord.setHostName("theHostname");
		this.kiekerRecord.setMemFree(usage++);
		this.kiekerRecord.setMemTotal(usage++);
		this.kiekerRecord.setMemUsed(usage++);
		this.kiekerRecord.setSwapFree(usage++);
		this.kiekerRecord.setSwapTotal(usage++);
		this.kiekerRecord.setSwapUsed(usage++);
	}

	public void testTransformRecordEmptyModel() {
		/* Create type repository manager for empty type repository */
		final ModelManager modelManager = new ModelManager();

		final MemSwapUsageRecordTransformationFilter filter =
				new MemSwapUsageRecordTransformationFilter(modelManager);

		final MemSwapUsage slasticRecord =
				filter.transformMemSwapUsageRecord(this.kiekerRecord);

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
			Assert.assertEquals("Unexpected timestamp",
					this.kiekerRecord.getTimestamp(), slasticRec.getTimestamp());
			Assert.assertEquals("Unexpected memFree value",
					this.kiekerRecord.getMemFree(),
					slasticRec.getMemFreeBytes());
			Assert.assertEquals("Unexpected memUsed value",
					this.kiekerRecord.getMemUsed(),
					slasticRec.getMemUsedBytes());
			Assert.assertEquals("Unexpected swapFree value",
					this.kiekerRecord.getSwapFree(),
					slasticRec.getSwapFreeBytes());
			Assert.assertEquals("Unexpected swapUsed value",
					this.kiekerRecord.getSwapUsed(),
					slasticRec.getSwapUsedBytes());
		}

		/* TODO: Check resource */

		/* TODO: Check total cpu/swap */
	}
}
