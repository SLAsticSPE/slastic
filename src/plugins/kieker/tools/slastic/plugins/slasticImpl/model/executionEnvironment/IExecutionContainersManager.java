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

package kieker.tools.slastic.plugins.slasticImpl.model.executionEnvironment;

import java.util.Collection;

import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;
import kieker.tools.slastic.metamodel.executionEnvironment.Resource;
import kieker.tools.slastic.metamodel.executionEnvironment.ResourceSpecification;
import kieker.tools.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * 
 * @author Andre van Hoorn
 */
public interface IExecutionContainersManager {

	/**
	 * Returns the execution container with the given fully-qualified name or
	 * null if no execution container with this name.
	 * 
	 * @param fullyQualifiedName
	 *            the fully-qualified name of the execution container to lookup
	 * @return the looked up execution container
	 */
	public ExecutionContainer lookupExecutionContainer(final String fullyQualifiedName);

	/**
	 * Returns the execution container with the given id or null if no execution
	 * container with this id.
	 * 
	 * @param id
	 *            the id of the execution container to lookup
	 * @return the looked up execution container
	 */
	public ExecutionContainer lookupExecutionContainer(final long id);

	/**
	 * Creates and registers a new execution container with the given
	 * full-qualified name fullyQualifiedName and the type
	 * executionContainerType.
	 * 
	 * @param fullyQualifiedName
	 * @param executionContainerType
	 * @return the new execution container
	 * @throws IllegalArgumentException
	 *             if an execution container with the given fully-qualified name
	 *             has already been registered
	 */
	public ExecutionContainer createAndRegisterExecutionContainer(
			final String fullyQualifiedName, final ExecutionContainerType executionContainerType, final boolean markAllocated);

	/**
	 * Marks execution container executionContainer as allocated.
	 * 
	 * @param executionContainer
	 * @return iff the execution container is newly allocated, false if it was
	 *         already allocated
	 */
	public boolean allocateExecutionContainer(final ExecutionContainer executionContainer);

	// /**
	// * Removes the given {@link ExecutionContainer} from the model.
	// *
	// * @param executionContainer
	// * the {@link ExecutionContainer} to delete
	// * @return
	// * @throws NullPointerException
	// * if the given {@link ExecutionContainer} is null
	// */
	// public boolean deleteExecutionContainer(final ExecutionContainer
	// executionContainer);

	/**
	 * Marks execution container executionContainer as not allocated.
	 * 
	 * @param executionContainer
	 * @return iff the execution container is newly deallocated, false if wasn't
	 *         allocated
	 * @throws NullPointerException
	 *             if the given {@link ExecutionContainer} is null
	 */
	public boolean deallocateExecutionContainer(final ExecutionContainer executionContainer);

	/**
	 * Returns a {@link Resource} associated to the given {@link ExecutionContainer} which corresponds to the {@link ResourceSpecification} with the given name of
	 * the {@link ExecutionContainer}'s {@link ExecutionContainerType}.
	 * 
	 * @param fullyQualifiedResourceSpecificationName
	 * @return
	 */
	public Resource lookupExecutionContainerResource(final ExecutionContainer executionContainer,
			final String resourceSpecificationName);

	/**
	 * Returns all {@link ExecutionContainer}s of given {@link ExecutionContainerType}.
	 * 
	 * @param executionContainerType
	 * @param includeInactive
	 * @return
	 */
	public Collection<ExecutionContainer> executionContainersForType(ExecutionContainerType executionContainerType,
			final boolean includeInactive);
}
