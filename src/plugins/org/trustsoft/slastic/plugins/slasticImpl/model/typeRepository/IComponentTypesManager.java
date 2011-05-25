package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.Operation;

/**
 * 
 * @author Andre van Hoorn
 */
public interface IComponentTypesManager {

	/**
	 * Returns the component type with the given fully-qualified name or null if
	 * no component with this name.
	 * 
	 * @param fullyQualifiedName
	 *            the fully-qualified name of the component type to lookup
	 * @return the looked up component type
	 */
	public ComponentType lookupComponentType(final String fullyQualifiedName);

	/**
	 * Returns the component type with the given id or null if no component with
	 * this id.
	 * 
	 * @param id
	 *            the id of the component type to lookup
	 * @return the looked up component type
	 */
	public ComponentType lookupComponentType(final long id);

	/**
	 * Creates and registers a new component type with the given full-qualified
	 * name fullyQualifiedName.
	 * 
	 * @param fullyQualifiedName
	 * @return the new component type
	 * @throws IllegalArgumentException
	 *             if a component type with the given fully-qualified name has
	 *             already been registered
	 */
	public ComponentType createAndRegisterComponentType(
			final String fullyQualifiedName);

	/**
	 * Returns the {@link Operation} with given name, return type, and argument
	 * types, that is implemented by the given {@link ComponentType}.
	 * 
	 * @param componentType
	 * @param operationName
	 * @param returnType
	 * @param argTypes
	 * @return the looked up {@link Operation}; null if no such
	 *         {@link Operation}
	 */
	public Operation lookupOperation(final ComponentType componentType,
			final String operationName, final String returnType,
			final String[] argTypes);

	/**
	 * Creates and registers a new {@link Operation} with the given name, return
	 * type, and argument types, that is to be provided by the given
	 * {@link ComponentType}.
	 * 
	 * @param componentType
	 * @param operationName
	 * @param returnType
	 * @param argTypes
	 * @return
	 * @throws IllegalArgumentException
	 *             if such an {@link Operation} has already been registered
	 */
	public Operation createAndRegisterOperation(
			final ComponentType componentType, final String operationName,
			final String returnType, final String[] argTypes);
}
