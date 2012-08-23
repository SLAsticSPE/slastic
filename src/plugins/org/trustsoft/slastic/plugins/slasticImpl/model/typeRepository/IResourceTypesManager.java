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

import de.cau.se.slastic.metamodel.typeRepository.ResourceType;
import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.CPUType;
import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.GenericResourceType;
import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.MemSwapType;

/**
 * 
 * @author Andre van Hoorn
 */
public interface IResourceTypesManager {

	/**
	 * Returns the resource type with the given fully-qualified name or null if
	 * no resource type with this name.
	 * 
	 * @param fullyQualifiedName
	 *            the fully-qualified name of the resource type to lookup
	 * @return the looked up resource type
	 */
	public ResourceType lookupResourceType(final String fullyQualifiedName);

	/**
	 * Returns the resource type with the given id or null if no resource type
	 * with this id.
	 * 
	 * @param id
	 *            the id of the network link type to lookup
	 * @return the looked up network link type
	 */
	public ResourceType lookupResourceType(final long id);

	/**
	 * Creates and registers a {@link GenericResourceType} with the given
	 * full-qualified name fullyQualifiedName.
	 * 
	 * @param fullyQualifiedName
	 * @return the new resource type
	 * @throws IllegalArgumentException
	 *             if a resource type with the given fully-qualified name has
	 *             already been registered
	 */
	public GenericResourceType createAndRegisterGenericResourceType(
			final String fullyQualifiedName);

	/**
	 * Creates and registers a {@link MemSwapType} with the given full-qualified
	 * name fullyQualifiedName.
	 * 
	 * @param fullyQualifiedName
	 * @return the new resource type
	 * @throws IllegalArgumentException
	 *             if a resource type with the given fully-qualified name has
	 *             already been registered
	 */
	public MemSwapType createAndRegisterMemSwapResourceType(
			String fullyQualifiedName);
	
	/**
	 * Creates and registers a {@link CPUType} with the given full-qualified
	 * name fullyQualifiedName.
	 * 
	 * @param fullyQualifiedName
	 * @return the new resource type
	 * @throws IllegalArgumentException
	 *             if a resource type with the given fully-qualified name has
	 *             already been registered
	 */
	public CPUType createAndRegisterCPUResourceType(
			String fullyQualifiedName);
}
