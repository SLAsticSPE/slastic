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
public class TestCPUUtilizationTransformationFilterSameResourceTypeOnTwoContainers
		extends AbstractReconstructionTest {

	private final CPUUtilizationRecord kiekerRecord1 =
			new CPUUtilizationRecord();
	{
		/*
		 * notice that the values make no sense; it's only important to choose
		 * different ones
		 */
		double util = 0.11;
		this.kiekerRecord1.setTimestamp(456346l);
		this.kiekerRecord1.setHostName("theHostname1");
		this.kiekerRecord1.setCpuID("2");
		this.kiekerRecord1.setIdle(util++);
		this.kiekerRecord1.setIrq(util++);
		this.kiekerRecord1.setNice(util++);
		this.kiekerRecord1.setSystem(util++);
		this.kiekerRecord1.setTotalUtilization(util++);
		this.kiekerRecord1.setUser(util++);
		this.kiekerRecord1.setWait(util++);
	}

	private final CPUUtilizationRecord kiekerRecord2 =
			new CPUUtilizationRecord();
	{
		/*
		 * notice that the values make no sense; it's only important to choose
		 * different ones
		 */
		double util = 0.11;
		this.kiekerRecord2.setTimestamp(456346l);
		this.kiekerRecord2.setHostName("theHostname2");
		this.kiekerRecord2.setCpuID("2");
		this.kiekerRecord2.setIdle(util++);
		this.kiekerRecord2.setIrq(util++);
		this.kiekerRecord2.setNice(util++);
		this.kiekerRecord2.setSystem(util++);
		this.kiekerRecord2.setTotalUtilization(util++);
		this.kiekerRecord2.setUser(util++);
		this.kiekerRecord2.setWait(util++);
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
