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

package org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;

/**
 * 
 * @author Andre van Hoorn
 */
public interface IAssemblyComponentsManager {

	/**
	 * Returns the assembly component with the given fully-qualified name or null
	 * if no assembly component with this name.
	 * 
	 * @param fullyQualifiedName
	 *            the fully-qualified name of the assembly component to
	 *            lookup
	 * @return the looked up assembly component
	 */
	public AssemblyComponent lookupAssemblyComponent(final String fullyQualifiedName);

	/**
	 * Returns the assembly component with the given id or null if no execution
	 * container with this id.
	 * 
	 * @param id
	 *            the id of the assembly component to lookup
	 * @return the looked up assembly component
	 */
	public AssemblyComponent lookupAssemblyComponent(final long id);

	/**
	 * Creates and registers a new assembly component with the given
	 * full-qualified name fullyQualifiedName and the type componentType.
	 * 
	 * @param fullyQualifiedName
	 * @param componentType
	 * @return the new assembly component
	 * @throws IllegalArgumentException
	 *             if an assembly component with the given
	 *             fully-qualified name has already been registered
	 */
	public AssemblyComponent createAndRegisterAssemblyComponent(final String fullyQualifiedName, final ComponentType componentType);
}
