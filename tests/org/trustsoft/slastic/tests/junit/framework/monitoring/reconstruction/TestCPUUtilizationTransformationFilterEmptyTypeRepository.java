package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction;

import junit.framework.Assert;
import kieker.common.record.CPUUtilizationRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.AbstractModelReconstructionComponent;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.CPUUtilizationRecordTransformationFilter;

import de.cau.se.slastic.metamodel.executionEnvironment.Resource;
import de.cau.se.slastic.metamodel.executionEnvironment.ResourceSpecification;
import de.cau.se.slastic.metamodel.monitoring.CPUUtilization;
import de.cau.se.slastic.metamodel.typeRepository.ResourceType;
import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.CPUType;

/**
 * Tests if the {@link CPUUtilizationRecordTransformationFilter} filter
 * correctly transforms a {@link CPUUtilizationRecord} into an
 * {@link CPUUtilization} including the required initializations of an empty
 * system model.
 * 
 * @author Andre van Hoorn
 */
public class TestCPUUtilizationTransformationFilterEmptyTypeRepository extends
		AbstractReconstructionTest {

	private static final Log log =
			LogFactory
					.getLog(TestCPUUtilizationTransformationFilterEmptyTypeRepository.class);

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

	public void testTransformRecordEmptyModel() {
		/* Create type repository manager for empty type repository */
		final ModelManager modelManager = new ModelManager();

		final CPUUtilizationRecordTransformationFilter filter =
				new CPUUtilizationRecordTransformationFilter(modelManager);

		final CPUUtilization slasticRecord =
				filter.transformCPUUtilizationRecord(this.kiekerRecord);

		this.checkResult(modelManager, slasticRecord);
	}

	/**
	 * Performs a number of validity checks on the model and the record.
	 * 
	 * @param mgr
	 * @param slasticRec
	 */
	private void checkResult(final ModelManager mgr,
			final CPUUtilization slasticRec) {
		Assert.assertNotNull("slasticRec must not be null", slasticRec);

		{
			/*
			 * This is the easy part: compare the plain values among the
			 * records:
			 */
			Assert.assertEquals("Unexpected timestamp",
					this.kiekerRecord.getTimestamp(), slasticRec.getTimestamp());
			Assert.assertEquals("Unexpected idle time",
					this.kiekerRecord.getIdle(), slasticRec.getIdle());
			Assert.assertEquals("Unexpected irq time",
					this.kiekerRecord.getIrq(), slasticRec.getIrq());
			Assert.assertEquals("Unexpected nice time",
					this.kiekerRecord.getNice(), slasticRec.getNice());
			Assert.assertEquals("Unexpected system time",
					this.kiekerRecord.getSystem(), slasticRec.getSystem());
			Assert.assertEquals("Unexpected combine utilization",
					this.kiekerRecord.getTotalUtilization(),
					slasticRec.getCombined());
			Assert.assertEquals("Unexpected user time",
					this.kiekerRecord.getUser(), slasticRec.getUser());
			Assert.assertEquals("Unexpected wait time",
					this.kiekerRecord.getWait(), slasticRec.getWait());
		}

		final Resource res = slasticRec.getResource();

		/* Check execution container */
		this.checkExecutionContainerAndType(
				mgr,
				this.kiekerRecord.getHostName(),
				this.kiekerRecord.getHostName()
						+ AbstractModelReconstructionComponent.DEFAULT_TYPE_POSTFIX,
				res.getExecutionContainer());

		/* Check resource specification */
		final ResourceSpecification resSpec = res.getResourceSpecification();
		Assert.assertNotNull("resourceSpecification is null", resSpec);
		Assert.assertEquals(
				"Unexpected resource specification name",
				AbstractModelReconstructionComponent
						.createCPUResourceSpecName(this.kiekerRecord.getCpuID()),
				resSpec.getName());

		/* Check resource type */
		final ResourceType resType = resSpec.getResourceType();
		Assert.assertEquals(
				"Unexpected resource type name",
				AbstractModelReconstructionComponent.DEFAULT_CPU_RESOURCE_TYPE_NAME,
				NameUtils.createFQName(resType.getPackageName(),
						resType.getName())); //
		Assert.assertTrue("Resource type must be instanceof " + CPUType.class
				+ " ; found: " + resType.getClass(), resType instanceof CPUType);
	}
}
