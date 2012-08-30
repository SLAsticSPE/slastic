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

import kieker.tools.slastic.metamodel.typeRepository.Interface;
import kieker.tools.slastic.metamodel.typeRepository.Operation;
import kieker.tools.slastic.metamodel.typeRepository.Signature;

/**
 * 
 * @author Andre van Hoorn
 */
public interface IInterfacesManager {

	/**
	 * Returns the interface with the given fully-qualified name or null
	 * if no interface with this name.
	 * 
	 * @param fullyQualifiedName
	 *            the fully-qualified name of the interface to lookup
	 * @return the looked up interface
	 */
	public Interface lookupInterface(final String fullyQualifiedName);

	/**
	 * Returns the interface with the given id or null if no component
	 * with this id.
	 * 
	 * @param id
	 *            the id of the interface to lookup
	 * @return the looked up interface
	 */
	public Interface lookupInterface(final long id);

	/**
	 * Creates and registers a new interface with the given full-qualified
	 * name fullyQualifiedName.
	 * 
	 * @param fullyQualifiedName
	 * @return the new interface
	 * @throws IllegalArgumentException
	 *             if a interface with the given
	 *             fully-qualified name has already been registered
	 */
	public Interface createAndRegisterInterface(final String fullyQualifiedName);

	/**
	 * Creates and registers a new {@link Signature} with the given name, return
	 * type, and argument types, that is to be declared by the given {@link Interface}.
	 * 
	 * @param iface
	 * @param signatureName
	 * @param returnType
	 * @param argTypes
	 * @return
	 * @throws IllegalArgumentException
	 *             if such an {@link Operation} has already been registered
	 */
	public Signature createAndRegisterSignature(
			final Interface iface, final String signatureName,
			final String returnType, final String[] argTypes);

	/**
	 * Returns the {@link Signature} with given name, return type, and argument
	 * types, that is declared by the given {@link Interface}.
	 * 
	 * @param iface
	 * @param signatureName
	 * @param returnType
	 * @param argTypes
	 * @return the looked up {@link Signature}; null if no such {@link Signature}
	 */
	public Signature lookupSignature(final Interface iface, final String signatureName, final String returnType, final String[] argTypes);
}
