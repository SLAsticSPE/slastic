package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * 
 * @author Andre van Hoorn
 *
 */
public class AbstractReconstructionTest extends TestCase {
	/**
	 * Lookup execution container and its type and compare with record content.
	 * 
	 * @param mgr
	 * @param resource
	 */
	protected void checkExecutionContainerAndType(final ModelManager mgr,
			final String executionContainerLookupFQName,
			final String containerTypeLookupFQName,
			final ExecutionContainer executionContainer) {
		final ExecutionContainer lookedUpExecutionContainer;
		{
			lookedUpExecutionContainer =
					mgr.getExecutionEnvironmentModelManager()
							.lookupExecutionContainer(
									executionContainerLookupFQName);
			Assert.assertNotNull("Lookup of execution container "
					+ executionContainerLookupFQName + " failed",
					lookedUpExecutionContainer);
			Assert.assertSame("Unexpected execution container",
					lookedUpExecutionContainer,
					executionContainer);
		}
		final ExecutionContainerType lookedUpContainerType;
		{
			lookedUpContainerType =
					mgr.getTypeRepositoryManager()
							.lookupExecutionContainerType(
									containerTypeLookupFQName);
			Assert.assertNotNull("Lookup of container type "
					+ containerTypeLookupFQName + " returned null",
					lookedUpContainerType);
			Assert.assertSame("Unexpected container type",
					lookedUpContainerType, executionContainer
							.getExecutionContainerType());
		}
	}
}
