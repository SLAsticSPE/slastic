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

package kieker.tools.slastic.plugins.slasticImpl.model.typeRepository;

import kieker.tools.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification;
import kieker.tools.slastic.metamodel.executionEnvironment.ResourceSpecification;
import kieker.tools.slastic.metamodel.typeRepository.ExecutionContainerType;
import kieker.tools.slastic.metamodel.typeRepository.ResourceType;

/**
 * 
 * @author Andre van Hoorn
 */
public interface IExecutionContainerTypesManager {

	/**
	 * Returns the execution container type with the given fully-qualified name
	 * or null if no component with this name.
	 * 
	 * @param fullyQualifiedName
	 *            the fully-qualified name of the execution container type to
	 *            lookup
	 * @return the looked up execution container type
	 */
	public ExecutionContainerType lookupExecutionContainerType(final String fullyQualifiedName);

	/**
	 * Returns the execution container type with the given id or null if no
	 * component with this id.
	 * 
	 * @param id
	 *            the id of the execution container type to lookup
	 * @return the looked up execution container type
	 */
	public ExecutionContainerType lookupExecutionContainerType(final long id);

	/**
	 * Creates and registers a new execution container type with the given
	 * full-qualified name fullyQualifiedName.
	 * 
	 * @param fullyQualifiedName
	 * @return the new execution container type
	 * @throws IllegalArgumentException
	 *             if a execution container type with the given fully-qualified
	 *             name has already been registered
	 */
	public ExecutionContainerType createAndRegisterExecutionContainerType(final String fullyQualifiedName);

	/**
	 * Creates a {@link ResourceSpecification} with the given name and adds it
	 * to the given {@link ExecutionContainerType}.
	 * 
	 * @param executionContainerType
	 * @param resourceType
	 * @param resourceSpecificatioName
	 * @return the the {@link ResourceSpecification}
	 */
	public ResourceSpecification createAndAddResourceSpecification(
			ExecutionContainerType executionContainerType,
			ResourceType resourceType, String resourceSpecificatioName);

	/**
	 * Creates a {@link MemSwapResourceSpecification} with the given name and
	 * adds it to the given {@link ExecutionContainerType}.
	 * 
	 * @param executionContainerType
	 * @param resourceType
	 * @param resourceSpecificatioName
	 * @return the the {@link ResourceSpecification}
	 */
	public MemSwapResourceSpecification createAndAddMemSwapResourceSpecification(
			ExecutionContainerType executionContainerType,
			final long memCapacityBytes, final long swapCapacityBytes,
			ResourceType resourceType, String resourceSpecificatioName);
}
