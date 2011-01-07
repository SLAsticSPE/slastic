package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction;

import junit.framework.Assert;
import kieker.common.record.CPUUtilizationRecord;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.CPUUtilizationRecordTransformationFilter;

import de.cau.se.slastic.metamodel.monitoring.CPUUtilization;

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

		/* TODO: Check resource */
		
//		final ExecutionContainerType lookedUpContainerType;
//		final ResourceType lookedUpResourceType;
//
//		{
//			/* Check type repository contents */
//			/* 1. Lookup resource type by name and compare with record content */
//			final String resourceTypeLookupFQName =
//					this.kiekerRecord.className
//							+ ModelEntityFactory.DEFAULT_TYPE_POSTFIX;
//			lookedUpComponentType =
//					mgr.getTypeRepositoryManager().lookupComponentType(
//							componentTypeLookupFQName);
//			Assert.assertNotNull("Lookup of component type "
//					+ componentTypeLookupFQName + " returned null",
//					lookedUpComponentType);
//			Assert.assertSame("Unexpected component types",
//					lookedUpComponentType, slasticComponentExecRec
//							.getDeploymentComponent().getAssemblyComponent()
//							.getComponentType());
//			/* 2. Lookup container type and compare with record content */
//			final String containerTypeLookupFQName =
//					this.kiekerRecord.hostName
//							+ ModelEntityFactory.DEFAULT_TYPE_POSTFIX;
//			lookedUpContainerType =
//					mgr.getTypeRepositoryManager()
//							.lookupExecutionContainerType(
//									containerTypeLookupFQName);
//			Assert.assertNotNull("Lookup of container type "
//					+ containerTypeLookupFQName + " returned null",
//					lookedUpContainerType);
//			Assert.assertSame("Unexpected container type",
//					lookedUpContainerType, slasticComponentExecRec
//							.getDeploymentComponent().getExecutionContainer()
//							.getExecutionContainerType());
//		}
//
//		/* Check execution environment contents */
//		/* 4. Lookup execution container and compare with record content */
//		this.checkExecutionContainerAndType(mgr, this.kiekerRecord.hostName,
//				this.kiekerRecord.hostName
//						+ ModelEntityFactory.DEFAULT_TYPE_POSTFIX,
//				slasticComponentExecRec.getDeploymentComponent()
//						.getExecutionContainer());
	}
}
