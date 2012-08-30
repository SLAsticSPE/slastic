/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package kieker.tools.slastic.tests.junit.framework.monitoring.reconstruction;

import junit.framework.Assert;
import junit.framework.TestCase;

import kieker.tools.slastic.plugins.slasticImpl.ModelManager;

import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;
import kieker.tools.slastic.metamodel.typeRepository.ExecutionContainerType;

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
			lookedUpExecutionContainer = mgr.getExecutionEnvironmentModelManager().lookupExecutionContainer(executionContainerLookupFQName);
			Assert.assertNotNull("Lookup of execution container " + executionContainerLookupFQName + " failed", lookedUpExecutionContainer);
			Assert.assertSame("Unexpected execution container", lookedUpExecutionContainer, executionContainer);
		}
		final ExecutionContainerType lookedUpContainerType;
		{
			lookedUpContainerType = mgr.getTypeRepositoryManager().lookupExecutionContainerType(containerTypeLookupFQName);
			Assert.assertNotNull("Lookup of container type " + containerTypeLookupFQName + " returned null", lookedUpContainerType);
			Assert.assertSame("Unexpected container type", lookedUpContainerType, executionContainer.getExecutionContainerType());
		}
	}
}
