package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction;

import java.util.ArrayList;

import junit.framework.Assert;
import kieker.common.record.ResourceUtilizationRecord;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ResourceUtilizationRecordTransformationFilter;

import de.cau.se.slastic.metamodel.monitoring.ResourceUtilization;

/**
 * Tests if the {@link ExecutionRecordTransformationFilter} filter correctly
 * re-uses existing model entities.
 * 
 * @author Andre van Hoorn
 */
public class TestResourceUtilizationTransformationFilterSameResourceTypeOnTwoContainers
		extends AbstractReconstructionTest {

	private final ResourceUtilizationRecord kiekerRecord1 =
			new ResourceUtilizationRecord(76767676l, "theHostname1",
					"theResourceName", 0.56);

	private final ResourceUtilizationRecord kiekerRecord2 =
			new ResourceUtilizationRecord(7676576l, "theHostname2",
					"theResourceName", 0.57);

	public void testEntitiesReUsed() {
		/* Create type repository manager for empty type repository */
		final ModelManager modelManager = new ModelManager();

		final ResourceUtilizationRecordTransformationFilter filter =
				new ResourceUtilizationRecordTransformationFilter(modelManager);

		/* Used to receive the created operations from the filter */
		final ArrayList<ResourceUtilization> slasticRef =
				new ArrayList<ResourceUtilization>();

		/* Let the filter transform the same record twice */
		slasticRef.add(filter
				.transformResourceUtilizationRecord(this.kiekerRecord1));
		slasticRef.add(filter
				.transformResourceUtilizationRecord(this.kiekerRecord2));

		final ResourceUtilization slasticRecordA = slasticRef.get(0);
		final ResourceUtilization slasticRecordB = slasticRef.get(1);

		Assert.assertSame("Expecting same type for both records",
				slasticRecordA.getResource().getResourceSpecification()
						.getResourceType(), slasticRecordB.getResource()
						.getResourceSpecification().getResourceType());
	}
}
