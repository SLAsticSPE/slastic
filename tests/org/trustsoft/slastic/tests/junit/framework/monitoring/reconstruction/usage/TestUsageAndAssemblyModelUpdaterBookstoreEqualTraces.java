package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction.usage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction.TraceReconstructor;
import org.trustsoft.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction.UsageAndAssemblyModelUpdater;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.usage.UsageModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;
import org.trustsoft.slastic.tests.junit.framework.esper.EPServiceProviderFactory;
import org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction.exampleTraces.BookstoreTraceFactory;

import com.espertech.esper.client.EPServiceProvider;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel;
import de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector;
import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.Operation;
import de.cau.se.slastic.metamodel.typeRepository.Signature;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryModel;
import de.cau.se.slastic.metamodel.usage.CallingRelationship;
import de.cau.se.slastic.metamodel.usage.FrequencyDistribution;
import de.cau.se.slastic.metamodel.usage.UsageModel;

/**
 * Tests if the {@link TypeRepositoryModel}, the {@link ComponentAssemblyModel},
 * and the {@link UsageModel} get updated correctly by the
 * {@link UsageAndAssemblyModelUpdater}. This test is based on
 * {@link #NUM_VALID_TRACES_TO_GENERATE} bookstore traces generated by the
 * {@link BookstoreTraceFactory}.
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestUsageAndAssemblyModelUpdaterBookstoreEqualTraces extends TestCase {
	private static final Log LOG = LogFactory.getLog(TestTraceReconstructorCEPComponent.class);

	private static final int NUM_VALID_TRACES_TO_GENERATE = 3;

	private static final long TRACE_DETECTION_TIMEOUT_MILLIS = 2000;

	/**
	 * Time to wait for CEP to complete.
	 */
	private static final long SHUTDOWN_TIMEOUT_MILLIS = 4000;

	private int nextTraceId = 0;

	final ModelManager systemModelManager = new ModelManager();

	// TODO: Register ExceptionHandler
	private final EPServiceProvider epService =
			EPServiceProviderFactory.createInstanceWithEMFSupport();

	/**
	 * Constructs a {@link TraceReconstructor} which registers itself as a
	 * subscriber to the {@link EPServiceProvider}.
	 */
	@SuppressWarnings("unused")
	private final TraceReconstructor traceReceiver =
			new TraceReconstructor(this.epService,
					TestUsageAndAssemblyModelUpdaterBookstoreEqualTraces.TRACE_DETECTION_TIMEOUT_MILLIS);

	/**
	 * Constructs a {@link UsageAndAssemblyModelUpdater} which registers itself
	 * as a subscriber to the {@link EPServiceProvider}.
	 */
	@SuppressWarnings("unused")
	private final UsageAndAssemblyModelUpdater usageAndAssemblyModelUpdater =
			new UsageAndAssemblyModelUpdater(this.epService, this.systemModelManager);

	/**
	 * Executes the test.
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 * 
	 */
	public void testUsageAndAssemblyModelUpdater() throws InterruptedException, IOException {
		this.sendBookstoreTraces(TestUsageAndAssemblyModelUpdaterBookstoreEqualTraces.NUM_VALID_TRACES_TO_GENERATE);

		// We have to wait for this time period, to make sure that all traces
		// are detected
		TestUsageAndAssemblyModelUpdaterBookstoreEqualTraces.LOG.info("Waiting "
				+ TestUsageAndAssemblyModelUpdaterBookstoreEqualTraces.SHUTDOWN_TIMEOUT_MILLIS
				+ " millis for timeout to elapse");
		Thread.sleep(TestUsageAndAssemblyModelUpdaterBookstoreEqualTraces.SHUTDOWN_TIMEOUT_MILLIS);

		this.checkSystemModel();

		this.saveModels();
	}

	private void saveModels() throws IOException {
		/*
		 * Create a tmp files the models will be saved to and mark the files to
		 * be deleted on jvm termination
		 */
		final File tmpSystemModelFile =
				File.createTempFile(this.getClass().getSimpleName() + "-systemModel-", "");
		// tmpSystemModelFile.deleteOnExit();

		final File tmpUsageModelFile =
				File.createTempFile(this.getClass().getSimpleName() + "-usageModel-", "");
		// tmpSystemModelFile.deleteOnExit();

		this.systemModelManager.saveModels(tmpSystemModelFile.getAbsolutePath(), tmpUsageModelFile.getAbsolutePath());
	}

	/**
	 * Makes sure that the required interfaces have been updated properly.
	 */
	private void checkSystemModel() {
		final ComponentAssemblyModelManager assemblyModelManager =
				this.systemModelManager.getComponentAssemblyModelManager();

		/*
		 * 1. Catalog requires no other services; Catalog required by Bookstore
		 * and CRM
		 */
		final AssemblyComponent catalogAssembly =
				assemblyModelManager.lookupAssemblyComponent(BookstoreTraceFactory.CATALOG_ASSEMBLY_COMPONENT_NAME);
		Assert.assertNotNull("Invalid test: catalogAssembly is null", catalogAssembly);
		// Some tests on the repository model
		final ComponentType catalogType =
				catalogAssembly.getComponentType();
		Assert.assertEquals("Unexpected number of interfaces provided by Catalog component type",
				1, catalogType.getProvidedInterfaces().size());
		final Interface catalogInterface = catalogType.getProvidedInterfaces().get(0);
		Assert.assertEquals("Unexpected number of interfaces required by Catalog component type",
				0, catalogType.getRequiredInterfaces().size());
		// Some tests on the assembly model
		Assert.assertEquals("Unexpected number of providing connectors for Catalog assembly",
				0, catalogAssembly.getProvidingConnectors().size());
		Assert.assertEquals("Unexpected number of requiring connectors for Catalog assembly",
				2, catalogAssembly.getRequiringConnectors().size());
		// requiring connectors' validity checks below

		/*
		 * 2. CRM requires Catalog; CRM required by Bookstore
		 */
		final AssemblyComponent crmAssembly =
				assemblyModelManager.lookupAssemblyComponent(BookstoreTraceFactory.CRM_ASSEMBLY_COMPONENT_NAME);
		Assert.assertNotNull("Invalid test: crmAssembly is null", crmAssembly);
		// Some tests on the repository model
		final ComponentType crmType =
				crmAssembly.getComponentType();
		Assert.assertEquals("Unexpected number of interfaces provided by CRM component type",
				1, crmType.getProvidedInterfaces().size());
		final Interface crmInterface = crmType.getProvidedInterfaces().get(0);
		Assert.assertEquals("Unexpected number of interfaces required by CRM component type",
				1, crmType.getRequiredInterfaces().size());
		Assert.assertEquals("CRM requires unexpected interface", catalogInterface,
				crmType.getRequiredInterfaces().get(0));
		// Some tests on the assembly model
		Assert.assertEquals("Unexpected number of providing connectors for CRM assembly",
				1, crmAssembly.getProvidingConnectors().size());
		final AssemblyComponentConnector asmConnect_crm_catalog = crmAssembly.getProvidingConnectors().get(0);
		Assert.assertEquals("Unexpected interface of asmConnect_crm_catalog",
				catalogInterface, asmConnect_crm_catalog.getConnectorType().getInterface());
		Assert.assertSame("Incomplete assembly of crm with connector asmConnect_crm_catalog",
				crmAssembly, asmConnect_crm_catalog.getRequiringComponent());
		Assert.assertSame("Incomplete assembly of catalog with connector asmConnect_crm_catalog",
				catalogAssembly, asmConnect_crm_catalog.getProvidingComponent());
		Assert.assertTrue(
				"Missing connector asmConnect_crm_catalog in Catalog assembly's list of requiring connectors",
				catalogAssembly.getRequiringConnectors().contains(asmConnect_crm_catalog));
		Assert.assertEquals("Unexpected number of requiring connectors for CRM assembly",
				1, crmAssembly.getRequiringConnectors().size());
		final AssemblyComponentConnector asmConnect_bookstore_crm = crmAssembly.getRequiringConnectors().get(0);
		Assert.assertEquals("Unexpected interface of asmConnect_bookstore_crm",
				crmInterface, asmConnect_bookstore_crm.getConnectorType().getInterface());
		Assert.assertSame("Incomplete assembly of crm with connector asmConnect_bookstore_crm",
				crmAssembly, asmConnect_bookstore_crm.getProvidingComponent());

		/*
		 * 3. Bookstore requires Catalog and CRM
		 */
		final AssemblyComponent bookstoreAssembly =
				assemblyModelManager.lookupAssemblyComponent(BookstoreTraceFactory.BOOKSTORE_ASSEMBLY_COMPONENT_NAME);
		Assert.assertNotNull("Invalid test: bookstoreAssembly is null", bookstoreAssembly);
		final ComponentType bookstoreType =
				bookstoreAssembly.getComponentType();
		Assert.assertEquals("Unexpected number of interfaces provided by Bookstore component type",
				1, bookstoreType.getProvidedInterfaces().size());
		Assert.assertEquals("Unexpected number of interfaces required by Bookstore component type",
				2, bookstoreType.getRequiredInterfaces().size());
		Assert.assertTrue("Expected Bookstore component type to require Catalog interface",
				bookstoreType.getRequiredInterfaces().contains(catalogInterface));
		Assert.assertTrue("Expected Bookstore component type to require CRM interface",
				bookstoreType.getRequiredInterfaces().contains(crmInterface));
		// Some tests on the assembly model
		Assert.assertEquals("Unexpected number of providing connectors for Bookstore assembly",
				2, bookstoreAssembly.getProvidingConnectors().size());
		Assert.assertTrue(
				"Missing connector asmConnect_crm_catalog in Bookstore assembly's list of providing connectors",
				bookstoreAssembly.getProvidingConnectors().contains(asmConnect_bookstore_crm));
		Assert.assertSame("Incomplete assembly of bookstore with connector asmConnect_bookstore_crm",
				bookstoreAssembly, asmConnect_bookstore_crm.getRequiringComponent());
		AssemblyComponentConnector asmConnect_bookstore_catalog = null;
		for (final AssemblyComponentConnector c : bookstoreAssembly.getProvidingConnectors()) {
			if (!c.equals(asmConnect_bookstore_crm)) {
				asmConnect_bookstore_catalog = c;
			}
		}
		Assert.assertEquals("Unexpected interface of asmConnect_bookstore_catalog",
				catalogInterface, asmConnect_bookstore_catalog.getConnectorType().getInterface());
		Assert.assertSame("Incomplete assembly of bookstore with connector asmConnect_bookstore_catalog",
				bookstoreAssembly, asmConnect_bookstore_catalog.getRequiringComponent());
		Assert.assertSame("Incomplete assembly of catalog with connector asmConnect_bookstore_catalog",
				catalogAssembly, asmConnect_bookstore_catalog.getProvidingComponent());
		Assert.assertTrue(
				"Missing connector asmConnect_bookstore_catalog in Catalog assembly's list of requiring connectors",
				catalogAssembly.getRequiringConnectors().contains(asmConnect_bookstore_catalog));

		// Test system-provided interface and delegation connectors
		Assert.assertEquals("Unexpected number of system-provided interfaces",
				1, assemblyModelManager.getSystemProvidedInterfaces().size());
		final Interface bookstoreInterface = bookstoreType.getProvidedInterfaces().get(0);
		Assert.assertSame("Missing expected system-provided interface",
				bookstoreInterface, assemblyModelManager.getSystemProvidedInterfaces().get(0));
		final Signature bookstoreInterfaceSignature =
				bookstoreInterface.getSignatures().get(0);
		final SystemProvidedInterfaceDelegationConnector sysProvDelegConnector =
				assemblyModelManager.lookupProvidedInterfaceDelegationConnector(bookstoreAssembly,
						bookstoreInterfaceSignature);
		Assert.assertNotNull("Failed to lookup system-provided delegation connector", sysProvDelegConnector);

		/*
		 * And some tests on the usage model
		 */
		final UsageModelManager usageModelManager = this.systemModelManager.getUsageModelManager();

		// Test operation call frequencies
		final Operation opBookstore_searchBook = bookstoreType.getOperations().get(0);
		Assert.assertEquals("Unexpected number of calls to Bookstore operation",
				TestUsageAndAssemblyModelUpdaterBookstoreEqualTraces.NUM_VALID_TRACES_TO_GENERATE,
				usageModelManager.lookupOperationCallFreq(opBookstore_searchBook));
		final Operation opCatalog_getBook = catalogType.getOperations().get(0);
		Assert.assertEquals("Unexpected number of calls to Catalog operation",
				2 * TestUsageAndAssemblyModelUpdaterBookstoreEqualTraces.NUM_VALID_TRACES_TO_GENERATE,
				usageModelManager.lookupOperationCallFreq(opCatalog_getBook));
		final Operation opCrm_getOffers = crmType.getOperations().get(0);
		Assert.assertEquals("Unexpected number of calls to CRM operation",
				TestUsageAndAssemblyModelUpdaterBookstoreEqualTraces.NUM_VALID_TRACES_TO_GENERATE,
				usageModelManager.lookupOperationCallFreq(opCrm_getOffers));

		// Test system-provided delegation connector call frequencies
		Assert.assertEquals("Unexpected number of calls to system-provided delegation connector interface",
				TestUsageAndAssemblyModelUpdaterBookstoreEqualTraces.NUM_VALID_TRACES_TO_GENERATE,
				usageModelManager.lookupSystemProvidedInterfaceDelegationConnectorFrequency(sysProvDelegConnector,
						bookstoreInterfaceSignature));

		// Test call relationship
		final Signature catalogSignature = catalogInterface.getSignatures().get(0);
		final Signature crmSignature = crmInterface.getSignatures().get(0);
		// Relationship Bookstore.searchBook()->ICatalog.getBook()
		final CallingRelationship crBookstore_searchBook__ICatalog_getBook =
				usageModelManager.lookupCallingRelationship(opBookstore_searchBook, catalogInterface, catalogSignature);
		final FrequencyDistribution fdBookstoreCatalog =
				crBookstore_searchBook__ICatalog_getBook.getFrequencyDistribution();
		this.compareFrequencyDistribution(fdBookstoreCatalog, new Long[] { 1l },
				new Long[] { TestUsageAndAssemblyModelUpdaterBookstoreEqualTraces.NUM_VALID_TRACES_TO_GENERATE + 0l });
		// Relationship Bookstore.searchBook()->ICRM.getOffers
		final CallingRelationship crBookstore_searchBook__ICRM_getOffers = 
			usageModelManager.lookupCallingRelationship(opBookstore_searchBook, crmInterface, crmSignature);
		final FrequencyDistribution fdBookstoreCRM = 
			crBookstore_searchBook__ICRM_getOffers.getFrequencyDistribution();
		this.compareFrequencyDistribution(fdBookstoreCRM, new Long[] { 1l },
				new Long[] { TestUsageAndAssemblyModelUpdaterBookstoreEqualTraces.NUM_VALID_TRACES_TO_GENERATE + 0l });
		// Relationship CRM.getOffers()->ICatalog.getBook
		final CallingRelationship crCRM_getOffers__ICatalog_getBook = 
			usageModelManager.lookupCallingRelationship(opCrm_getOffers, catalogInterface, catalogSignature);
		final FrequencyDistribution fdCrmCatalog = 
			crCRM_getOffers__ICatalog_getBook.getFrequencyDistribution();
		this.compareFrequencyDistribution(fdCrmCatalog, new Long[] { 1l },
				new Long[] { TestUsageAndAssemblyModelUpdaterBookstoreEqualTraces.NUM_VALID_TRACES_TO_GENERATE + 0l });
	}

	private void compareFrequencyDistribution(final FrequencyDistribution fd, final Long[] expectedValues,
			final Long[] expectedFrequencies) {
		final Long[] observedValues = fd.getValues().toArray(new Long[] {});
		Assert.assertTrue("Unexpected values in fd " + fd, Arrays.equals(expectedValues, observedValues));
		final Long[] observedFrequencies = fd.getFrequencies().toArray(new Long[] {});
		Assert.assertTrue("Unexpected frequencies in fd", Arrays.equals(expectedFrequencies, observedFrequencies));
	}

	/**
	 * Sends the {@link DeploymentComponentOperationExecution}s for
	 * {@value #NUM_VALID_TRACES_TO_GENERATE} bookstore traces.
	 */
	private void sendBookstoreTraces(final int numTraces) {
		final ExecutionRecordTransformationFilter execRecFilter =
				new ExecutionRecordTransformationFilter(this.systemModelManager,
						NameUtils.ABSTRACTION_MODE_CLASS);

		final List<DeploymentComponentOperationExecution> bookstoreExecutions =
				new ArrayList<DeploymentComponentOperationExecution>(
						numTraces
								* BookstoreTraceFactory.NUM_EXECUTIONS_PER_TRACE);

		for (int i = 0; i < numTraces; i++) {
			final Collection<DeploymentComponentOperationExecution> bookstoreTrace =
					BookstoreTraceFactory.createBookstoreTrace(execRecFilter, this.nextTraceId++);
			for (final DeploymentComponentOperationExecution exec : bookstoreTrace) {
				bookstoreExecutions.add(exec);
			}
		}

		Collections.shuffle(bookstoreExecutions);

		for (final DeploymentComponentOperationExecution exec : bookstoreExecutions) {
			this.epService.getEPRuntime().sendEvent(exec);
		}
	}
}
