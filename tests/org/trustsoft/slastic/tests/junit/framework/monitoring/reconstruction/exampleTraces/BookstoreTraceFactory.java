package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction.exampleTraces;

import java.util.ArrayList;
import java.util.Collection;

import kieker.common.record.OperationExecutionRecord;

import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;

import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;

/**
 * Utility class that allows to create traces of the Bookstore application.
 * 
 * @author Andre van Hoorn
 * 
 */
public class BookstoreTraceFactory {
	public static final int NUM_EXECUTIONS_PER_TRACE = 4;
	public static final String BOOKSTORE_ASSEMBLY_COMPONENT_NAME = "Bookstore";
	public static final String BOOKSTORE_OPERATION_SIGNATURE = "searchBook()";
	public static final String CATALOG_ASSEMBLY_COMPONENT_NAME = "Catalog";
	public static final String CATALOG_OPERATION_SIGNATURE = "getBook";
	public static final String CRM_ASSEMBLY_COMPONENT_NAME = "CRM";
	public static final String CRM_OPERATION_SIGNATURE = "getOffers";

	/**
	 * Returns the collection of {@link DeploymentComponentOperationExecution}s for 
	 * a bookstore trace employing the given {@link ExecutionRecordTransformationFilter}.
	 * 
	 * @param execRecFilter
	 * @param traceId
	 * @return
	 */
	public static Collection<DeploymentComponentOperationExecution> createBookstoreTrace(
			final ExecutionRecordTransformationFilter execRecFilter,
			final long traceId) {
		final Collection<DeploymentComponentOperationExecution> retCollection =
				new ArrayList<DeploymentComponentOperationExecution>(BookstoreTraceFactory.NUM_EXECUTIONS_PER_TRACE);

		/*
		 * Now, we'll create the executions
		 */
		final DeploymentComponentOperationExecution exec00_bookstoreSearchBook =
				BookstoreTraceFactory.createExecution(execRecFilter,
						BookstoreTraceFactory.BOOKSTORE_ASSEMBLY_COMPONENT_NAME,
						BookstoreTraceFactory.BOOKSTORE_OPERATION_SIGNATURE, traceId, 0, 0);
		final DeploymentComponentOperationExecution exec11_catalogGetBook =
				BookstoreTraceFactory.createExecution(execRecFilter,
						BookstoreTraceFactory.CATALOG_ASSEMBLY_COMPONENT_NAME,
						BookstoreTraceFactory.CATALOG_OPERATION_SIGNATURE, traceId, 1, 1);
		final DeploymentComponentOperationExecution exec21_crmGetOffers =
				BookstoreTraceFactory.createExecution(execRecFilter, BookstoreTraceFactory.CRM_ASSEMBLY_COMPONENT_NAME,
						BookstoreTraceFactory.CRM_OPERATION_SIGNATURE, traceId, 2, 1);
		final DeploymentComponentOperationExecution exec32_catalogGetBook =
				BookstoreTraceFactory.createExecution(execRecFilter,
						BookstoreTraceFactory.CATALOG_ASSEMBLY_COMPONENT_NAME,
						BookstoreTraceFactory.CATALOG_OPERATION_SIGNATURE, traceId, 3, 2);

		/*
		 * Add the executions 
		 */
		retCollection.add(exec11_catalogGetBook);
		retCollection.add(exec32_catalogGetBook);
		retCollection.add(exec21_crmGetOffers);
		retCollection.add(exec00_bookstoreSearchBook);
		
		return retCollection;
	}

	private static DeploymentComponentOperationExecution createExecution(
			final ExecutionRecordTransformationFilter execRecFilter,
			final String fqAssemblyComponentName,
			final String opSignature,
			final long traceId,
			final int eoi, final int ess) {
		/**
		 * Record used to create a corresponding
		 * DeploymentComponentOperationExecution.
		 */
		final OperationExecutionRecord kiekerRecord =
				new OperationExecutionRecord();
		{
			kiekerRecord.className = fqAssemblyComponentName;
			kiekerRecord.eoi = eoi;
			kiekerRecord.ess = ess;
			kiekerRecord.hostName = "theHostname";
			kiekerRecord.operationName = opSignature;
			kiekerRecord.sessionId = "ZUKGHGF435JJ";
			kiekerRecord.tin = 65656868l;
			kiekerRecord.tout = 9878787887l;
			kiekerRecord.traceId = traceId;
		}

		final DeploymentComponentOperationExecution slasticRecord =
				(DeploymentComponentOperationExecution) execRecFilter
						.transformExecutionRecord(kiekerRecord);

		return slasticRecord;
	}
}
