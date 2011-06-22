package org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.Resource;
import de.cau.se.slastic.metamodel.executionEnvironment.ResourceSpecification;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;

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
	public ExecutionContainer createAndRegisterExecutionContainer(final String fullyQualifiedName,
			final ExecutionContainerType executionContainerType, final boolean markAllocated);

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
	 * Returns a {@link Resource} associated to the given
	 * {@link ExecutionContainer} which corresponds to the
	 * {@link ResourceSpecification} with the given name of the
	 * {@link ExecutionContainer}'s {@link ExecutionContainerType}.
	 * 
	 * @param fullyQualifiedResourceSpecificationName
	 * @return
	 */
	public Resource lookupExecutionContainerResource(final ExecutionContainer executionContainer,
			final String resourceSpecificationName);
}
