package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction;

import junit.framework.Assert;
import kieker.common.record.system.ResourceUtilizationRecord;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.AbstractModelReconstructionComponent;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ResourceUtilizationRecordTransformationFilter;

import de.cau.se.slastic.metamodel.executionEnvironment.Resource;
import de.cau.se.slastic.metamodel.executionEnvironment.ResourceSpecification;
import de.cau.se.slastic.metamodel.monitoring.ResourceUtilization;
import de.cau.se.slastic.metamodel.typeRepository.ResourceType;
import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.GenericResourceType;

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

		{
			/*
			 * This is the easy part: compare the plain values among the
			 * record's:
			 */
			Assert.assertEquals("Unexpected timestamp",
					this.kiekerRecord.getTimestamp(),
					slasticResourceUtilRec.getTimestamp());
			Assert.assertEquals("Unexpected utilization",
					this.kiekerRecord.getUtilization(),
					slasticResourceUtilRec.getUtilization());
		}

		final Resource res = slasticResourceUtilRec.getResource();

		/* Check execution container */
		this.checkExecutionContainerAndType(
				mgr,
				this.kiekerRecord.getHostname(),
				this.kiekerRecord.getHostname()
						+ AbstractModelReconstructionComponent.DEFAULT_TYPE_POSTFIX,
				res.getExecutionContainer());

		/* Check resource specification */
		final ResourceSpecification resSpec = res.getResourceSpecification();
		Assert.assertNotNull("resourceSpecification is null", resSpec);
		Assert.assertEquals("Unexpected resource specification name",
				AbstractModelReconstructionComponent
						.createGenericResourceSpecName(this.kiekerRecord
								.getResourceName()), resSpec.getName());

		/* Check resource type */
		final ResourceType resType = resSpec.getResourceType();
		Assert.assertEquals(
				"Unexpected resource type name",
				AbstractModelReconstructionComponent.DEFAULT_GENERIC_RESOURCE_TYPE_NAME,
				NameUtils.createFQName(resType.getPackageName(),
						resType.getName())); //
		Assert.assertTrue(
				"Resource type must be instanceof " + GenericResourceType.class
						+ " ; found: " + resType.getClass(),
				resType instanceof GenericResourceType);
	}
}
