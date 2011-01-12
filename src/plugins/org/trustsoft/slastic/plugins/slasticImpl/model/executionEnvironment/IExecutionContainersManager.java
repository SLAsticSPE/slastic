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
	public ExecutionContainer lookupExecutionContainer(
			final String fullyQualifiedName);

	/**
	 * Returns the execution container with the given id or null if no execution
	 * container with this id.
	 * 
	 * @param id
	 *            the id of the execution container to lookup
	 * @return the looked up execution container
	 */
	public ExecutionContainer lookupExecutionContainer(final long id);

	// TODO: Add method deleteExecutionContainer?

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
			final String fullyQualifiedName,
			final ExecutionContainerType executionContainerType);

	/**
	 * Returns a {@link Resource} associated to the given
	 * {@link ExecutionContainer} which corresponds to the
	 * {@link ResourceSpecification} with the given name of the
	 * {@link ExecutionContainer}'s {@link ExecutionContainerType}.
	 * 
	 * @param fullyQualifiedResourceSpecificationName
	 * @return
	 */
	public Resource lookupExecutionContainerResource(
			final ExecutionContainer executionContainer,
			final String resourceSpecificationName);
}
