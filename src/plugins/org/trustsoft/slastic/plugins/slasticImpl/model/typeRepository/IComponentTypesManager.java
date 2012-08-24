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

package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.Operation;
import de.cau.se.slastic.metamodel.typeRepository.Signature;

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
	public ComponentType createAndRegisterComponentType(final String fullyQualifiedName);

	/**
	 * Returns the {@link Operation} with given name, return type, and argument
	 * types, that is implemented by the given {@link ComponentType}.
	 * 
	 * @param componentType
	 * @param operationName
	 * @param returnType
	 * @param argTypes
	 * @return the looked up {@link Operation}; null if no such {@link Operation}
	 */
	public Operation lookupOperation(final ComponentType componentType,
			final String operationName, final String returnType,
			final String[] argTypes);

	/**
	 * Creates and registers a new {@link Operation} with the given name, return
	 * type, and argument types, that is to be provided by the given {@link ComponentType}.
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

	/**
	 * Registers the given {@link Interface} in the given {@link ComponentType} 's list of provided interfaces.
	 * 
	 * @param componentType
	 * @param providedInterface
	 */
	public void registerProvidedInterface(final ComponentType componentType, final Interface providedInterface);

	/**
	 * Register the given {@link Interface} in the given {@link ComponentType}'s
	 * list of required interfaces.
	 * 
	 * @param componentType
	 * @param requiredInterface
	 */
	public void registerRequiredInterface(final ComponentType componentType, final Interface requiredInterface);

	/**
	 * Returns the {@link Interface} declaring the given {@link Signature} provided by the given {@link ComponentType}.
	 * 
	 * @param componentType
	 * @param signature
	 * @return
	 */
	public Interface lookupProvidedInterfaceForSignature(final ComponentType componentType, final Signature signature);

	/**
	 * Returns the {@link Interface} declaring the given {@link Signature} required by the given {@link ComponentType}.
	 * 
	 * @param componentType
	 * @param signature
	 * @return
	 */
	public Interface lookupRequiredInterfaceForSignature(final ComponentType componentType, final Signature signature);
}
