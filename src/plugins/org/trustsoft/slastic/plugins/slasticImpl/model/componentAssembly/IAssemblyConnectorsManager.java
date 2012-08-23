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
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.Signature;

/**
 * 
 * @author Andre van Hoorn
 */
public interface IAssemblyConnectorsManager {

	/**
	 * Returns the assembly connector with the given fully-qualified name or
	 * null if no assembly connector with this name.
	 * 
	 * @param fullyQualifiedName
	 *            the fully-qualified name of the assembly connector to lookup
	 * @return the looked up assembly connector
	 */
	public AssemblyComponentConnector lookupAssemblyConnector(final String fullyQualifiedName);

	/**
	 * Returns the assembly connector with the given id or null if no execution
	 * container with this id.
	 * 
	 * @param id
	 *            the id of the assembly connector to lookup
	 * @return the looked up assembly connector
	 */
	public AssemblyComponentConnector lookupAssemblyConnector(final long id);

	/**
	 * Returns the {@link AssemblyComponentConnector} which connects the two given
	 * {@link AssemblyComponent}s and whose {@link Interface} declares the given
	 * {@link Signature}.
	 * 
	 * @param requiringComponent
	 * @param providingComponent
	 * @param signature
	 * @return the requested {@link AssemblyComponentConnector} or null if it doesn't
	 *         exist.
	 */
	public AssemblyComponentConnector lookupAssemblyConnector(final AssemblyComponent requiringComponent,
			final AssemblyComponent providingComponent, final Signature signature);

	/**
	 * Creates and registers a new assembly connector with the given
	 * full-qualified name fullyQualifiedName and of type connectorType.
	 * 
	 * @param fullyQualifiedName
	 * @param connectorType
	 * @return the new assembly connector
	 * @throws IllegalArgumentException
	 *             if an assembly connector with the given fully-qualified name
	 *             has already been registered
	 */
	public AssemblyComponentConnector createAndRegisterAssemblyConnector(
			final String fullyQualifiedName,
			final ConnectorType connectorType);

	/**
	 * Creates and registers a new assembly connector with a random but unique
	 * full-qualified name and of type connectorType.
	 * 
	 * @param connectorType
	 * @return the new assembly connector
	 */
	public AssemblyComponentConnector createAndRegisterAssemblyConnector(
			final ConnectorType connectorType);

	/**
	 * Connects the given {@link AssemblyComponent}s by the given
	 * {@link AssemblyComponentConnector}. Note that the {@link Interface} corresponding
	 * to the {@link AssemblyComponentConnector}'s type
	 * {@link ConnectorType#getInterface()} must be contained in the requiring
	 * component's list of required interfaces
	 * {@link ComponentType#getRequiredInterfaces()} and in the providing
	 * component's list of provided interfaces
	 * {@link ComponentType#getProvidedInterfaces()}.
	 * 
	 * @param assemblyConnector
	 * @param requiringComponent
	 * @param providingComponent
	 * @return true if the connection could successfully be established
	 */
	public boolean connect(AssemblyComponentConnector assemblyConnector, AssemblyComponent requiringComponent,
			AssemblyComponent providingComponent);
}
