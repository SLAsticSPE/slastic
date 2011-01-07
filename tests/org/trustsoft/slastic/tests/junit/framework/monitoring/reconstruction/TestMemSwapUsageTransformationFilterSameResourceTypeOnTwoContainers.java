package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction;

import java.util.ArrayList;

import junit.framework.Assert;
import kieker.common.record.MemSwapUsageRecord;

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
public class TestMemSwapUsageTransformationFilterSameResourceTypeOnTwoContainers
		extends AbstractReconstructionTest {

	private final MemSwapUsageRecord kiekerRecord1 = new MemSwapUsageRecord();
	{
		/*
		 * notice that the values make no sense; it's only important to choose
		 * different ones
		 */
		long usage = 7676l;
		this.kiekerRecord1.setTimestamp(456346l);
		this.kiekerRecord1.setHostName("theHostname1");
		this.kiekerRecord1.setMemFree(usage++);
		this.kiekerRecord1.setMemTotal(usage++);
		this.kiekerRecord1.setMemUsed(usage++);
		this.kiekerRecord1.setSwapFree(usage++);
		this.kiekerRecord1.setSwapTotal(usage++);
		this.kiekerRecord1.setSwapUsed(usage++);
	}

	private final MemSwapUsageRecord kiekerRecord2 = new MemSwapUsageRecord();
	{
		/*
		 * notice that the values make no sense; it's only important to choose
		 * different ones
		 */
		long usage = 7676l;
		this.kiekerRecord2.setTimestamp(456346l);
		this.kiekerRecord2.setHostName("theHostname2");
		this.kiekerRecord2.setMemFree(usage++);
		this.kiekerRecord2.setMemTotal(usage++);
		this.kiekerRecord2.setMemUsed(usage++);
		this.kiekerRecord2.setSwapFree(usage++);
		this.kiekerRecord2.setSwapTotal(usage++);
		this.kiekerRecord2.setSwapUsed(usage++);
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
		slasticRef.add(filter.transformMemSwapUsageRecord(this.kiekerRecord1));
		slasticRef.add(filter.transformMemSwapUsageRecord(this.kiekerRecord2));

		final MemSwapUsage slasticRecordA = slasticRef.get(0);
		final MemSwapUsage slasticRecordB = slasticRef.get(1);

		Assert.assertSame("Expecting same type for both records",
				slasticRecordA.getResource().getResourceSpecification()
						.getResourceType(), slasticRecordB.getResource()
						.getResourceSpecification().getResourceType());
	}
}
