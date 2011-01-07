package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction;

import java.util.ArrayList;

import junit.framework.Assert;
import kieker.common.record.CPUUtilizationRecord;

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
public class TestCPUUtilizationTransformationFilterSameRecordTwice extends
		AbstractReconstructionTest {

	private final CPUUtilizationRecord kiekerRecord =
			new CPUUtilizationRecord();
	{
		/*
		 * notice that the values make no sense; it's only important to choose
		 * different ones
		 */
		double util = 0.11;
		this.kiekerRecord.setTimestamp(456346l);
		this.kiekerRecord.setHostName("theHostname");
		this.kiekerRecord.setCpuID("2");
		this.kiekerRecord.setIdle(util++);
		this.kiekerRecord.setIrq(util++);
		this.kiekerRecord.setNice(util++);
		this.kiekerRecord.setSystem(util++);
		this.kiekerRecord.setTotalUtilization(util++);
		this.kiekerRecord.setUser(util++);
		this.kiekerRecord.setWait(util++);
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
		slasticRef.add(filter.transformCPUUtilizationRecord(this.kiekerRecord));
		slasticRef.add(filter.transformCPUUtilizationRecord(this.kiekerRecord));

		final CPUUtilization slasticRecordA = slasticRef.get(0);
		final CPUUtilization slasticRecordB = slasticRef.get(1);

		this.checkResult(slasticRecordA, slasticRecordB);
	}

	/**
	 * Performs a number of validity checks on the model and the record.
	 */
	private void checkResult(final CPUUtilization slasticRecordA,
			final CPUUtilization slasticRecordB) {
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

			Assert.assertEquals("Unexpected idle time",
					slasticRecordA.getIdle(), slasticRecordB.getIdle());
			Assert.assertEquals("Unexpected irq time", slasticRecordA.getIrq(),
					slasticRecordB.getIrq());
			Assert.assertEquals("Unexpected nice time",
					slasticRecordA.getNice(), slasticRecordB.getNice());
			Assert.assertEquals("Unexpected system time",
					slasticRecordA.getSystem(), slasticRecordB.getSystem());
			Assert.assertEquals("Unexpected combine utilization",
					slasticRecordA.getCombined(), slasticRecordB.getCombined());
			Assert.assertEquals("Unexpected user time",
					slasticRecordA.getUser(), slasticRecordB.getUser());
			Assert.assertEquals("Unexpected wait time",
					slasticRecordA.getWait(), slasticRecordB.getWait());
		}
	}
}
