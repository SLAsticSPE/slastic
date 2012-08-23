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

import java.util.List;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector;
import de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.Signature;

/**
 * 
 * @author Andre van Hoorn
 */
public interface ISystemProvidedInterfacesManager {

	/**
	 * Returns the system-provided {@link Interface}s.
	 * 
	 * @return
	 */
	public List<Interface> getSystemProvidedInterfaces();
	
	/**
	 * Adds the given {@link Interface} to the list of system-provided
	 * interfaces.
	 * 
	 * @param iface
	 * @return
	 */
	public boolean registerSystemProvidedInterface(Interface iface);

	/**
	 * Returns the {@link SystemProvidedInterfaceDelegationConnector} with the
	 * given fully-qualified name or null if no such
	 * {@link SystemProvidedInterfaceDelegationConnector} with this name.
	 * 
	 * @param fullyQualifiedName
	 *            the fully-qualified name of the
	 *            {@link SystemProvidedInterfaceDelegationConnector} to lookup
	 * @return the looked up {@link SystemProvidedInterfaceDelegationConnector}
	 */
	public SystemProvidedInterfaceDelegationConnector lookupProvidedInterfaceDelegationConnector(
			final String fullyQualifiedName);

	/**
	 * Returns the {@link SystemProvidedInterfaceDelegationConnector} with the
	 * given id or null if no {@link SystemProvidedInterfaceDelegationConnector}
	 * with this id.
	 * 
	 * @param id
	 *            the id of the
	 *            {@link SystemProvidedInterfaceDelegationConnector} to lookup
	 * @return the looked up {@link SystemProvidedInterfaceDelegationConnector}
	 */
	public SystemProvidedInterfaceDelegationConnector lookupProvidedInterfaceDelegationConnector(final long id);

	/**
	 * Returns the {@link SystemProvidedInterfaceDelegationConnector} which
	 * delegates the given {@link Interface} to the given
	 * {@link AssemblyComponent}.
	 * 
	 * @param iface
	 * @param providingComponent
	 * @param signature
	 * @return the requested {@link SystemProvidedInterfaceDelegationConnector}
	 *         or null if it doesn't exist.
	 */
	public SystemProvidedInterfaceDelegationConnector lookupProvidedInterfaceDelegationConnector(
			final AssemblyComponent providingComponent, final Signature signature);

	/**
	 * Creates and registers a
	 * {@link SystemProvidedInterfaceDelegationConnector} with the given
	 * full-qualified name and {@link ConnectorType}.
	 * 
	 * @param fullyQualifiedName
	 * @param connectorType
	 * @return the new assembly connector
	 * @throws IllegalArgumentException
	 *             if a {@link SystemProvidedInterfaceDelegationConnector} with
	 *             the given fully-qualified name has already been registered
	 */
	public SystemProvidedInterfaceDelegationConnector createAndRegisterProvidedInterfaceDelegationConnector(
			final String fullyQualifiedName,
			final ConnectorType connectorType);

	/**
	 * Creates and registers a new
	 * {@link SystemProvidedInterfaceDelegationConnector} with a random but
	 * unique full-qualified name and {@link ConnectorType}.
	 * 
	 * @param connectorType
	 * @return the new assembly connector
	 */
	public SystemProvidedInterfaceDelegationConnector createAndRegisterProvidedInterfaceDelegationConnector(
			final ConnectorType connectorType);

	/**
	 * Delegates the given provided {@link Interface}s to the given
	 * {@link AssemblyComponentConnector} via the given
	 * {@link SystemProvidedInterfaceDelegationConnector}. Note that the
	 * {@link Interface} must be contained in the component's list of provided
	 * interfaces {@link ComponentType#getRequiredInterfaces()}.
	 * 
	 * @param providedInterface
	 * @param providingComponent
	 * @return true if the connection could successfully be established
	 */
	public boolean delegate(SystemProvidedInterfaceDelegationConnector delegationConnector,
			Interface providedInterface, AssemblyComponent providingComponent);
}
