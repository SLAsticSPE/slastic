package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction;

import junit.framework.Assert;
import kieker.common.record.ResourceUtilizationRecord;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ModelEntityFactory;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ResourceUtilizationRecordTransformationFilter;

import de.cau.se.slastic.metamodel.executionEnvironment.Resource;
import de.cau.se.slastic.metamodel.monitoring.ResourceUtilization;

/**
 * Tests if the {@link ResourceUtilizationRecordTransformationFilter} filter
 * correctly transforms a {@link ResourceUtilizationRecord} into a
 * {@link ResourceUtilization} including the required initializations of an
 * empty system model.
 * 
 * @author Andre van Hoorn
 */
public class TestResourceUtilizationRecordTransformationFilterEmptyTypeRepository
		extends AbstractReconstructionTest {

	private final ResourceUtilizationRecord kiekerRecord =
			new ResourceUtilizationRecord(76767676l, "theHostname",
					"theResourceName", 0.56);

	public void testTransformRecordEmptyModel() {
		/* Create type repository manager for empty type repository */
		final ModelManager modelManager = new ModelManager();

		final ResourceUtilizationRecordTransformationFilter resourceUtilRecFilter =
				new ResourceUtilizationRecordTransformationFilter(modelManager);

		final ResourceUtilization slasticRecord =
				resourceUtilRecFilter
						.transformResourceUtilizationRecord(this.kiekerRecord);

		this.checkResult(modelManager, slasticRecord);
	}

	/**
	 * Performs a number of validity checks on the model and the record.
	 * 
	 * @param mgr
	 * @param slasticResourceUtilRec
	 */
	private void checkResult(final ModelManager mgr,
			final ResourceUtilization slasticResourceUtilRec) {
		Assert.assertNotNull("slasticResourceUtilRec must not be null",
				slasticResourceUtilRec);

		final Resource resource = slasticResourceUtilRec.getResource();
		Assert.assertNotNull("Resource is null", resource);

		{
			/*
			 * This is the easy part: compare the plain values among the
			 * record's:
			 */
			Assert.assertEquals("Unexpected resource name ",
					this.kiekerRecord.getResourceName(), resource
							.getResourceSpecification().getName());
			Assert.assertEquals("Unexpected timestamp",
					this.kiekerRecord.getTimestamp(),
					slasticResourceUtilRec.getTimestamp());
			Assert.assertEquals("Unexpected utilization",
					this.kiekerRecord.getUtilization(),
					slasticResourceUtilRec.getUtilization());
		}

		this.checkExecutionContainerAndType(mgr,
				this.kiekerRecord.getHostName(),
				this.kiekerRecord.getHostName()
						+ ModelEntityFactory.DEFAULT_TYPE_POSTFIX,
				resource.getExecutionContainer());
	}
}
