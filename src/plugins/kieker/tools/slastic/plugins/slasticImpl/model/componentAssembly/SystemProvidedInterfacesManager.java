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

package kieker.tools.slastic.plugins.slasticImpl.model.componentAssembly;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyFactory;
import kieker.tools.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector;
import kieker.tools.slastic.metamodel.typeRepository.ConnectorType;
import kieker.tools.slastic.metamodel.typeRepository.Interface;
import kieker.tools.slastic.metamodel.typeRepository.Signature;
import kieker.tools.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;
import kieker.tools.slastic.plugins.slasticImpl.model.NameUtils;
import kieker.tools.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

/**
 *
 * @author Andre van Hoorn
 */
public class SystemProvidedInterfacesManager extends AbstractFQNamedEntityManager<SystemProvidedInterfaceDelegationConnector>
implements ISystemProvidedInterfacesManager {
	private static final Log LOG = LogFactory.getLog(SystemProvidedInterfacesManager.class);

	public final static String SYSPROVCONNECT_NO_NAME_PREFIX = "ASM_SYSPROVCONN_NN_";

	private final TypeRepositoryModelManager typeRepositoryModelManager;
	private final List<Interface> systemProvidedInterfaces;

	public SystemProvidedInterfacesManager(
			final List<Interface> systemProvidedInterfaces,
			final List<SystemProvidedInterfaceDelegationConnector> systemProvidedInterfaceDelegationConnectors,
			final TypeRepositoryModelManager typeRepositoryModelManager) {
		super(systemProvidedInterfaceDelegationConnectors);
		this.systemProvidedInterfaces = systemProvidedInterfaces;
		this.typeRepositoryModelManager = typeRepositoryModelManager;
	}

	@Override
	public SystemProvidedInterfaceDelegationConnector createAndRegisterProvidedInterfaceDelegationConnector(
			final String fullyQualifiedName, final ConnectorType connectorType) {
		final SystemProvidedInterfaceDelegationConnector connector = this.createAndRegister(fullyQualifiedName);
		connector.setConnectorType(connectorType);
		return connector;
	}

	@Override
	public SystemProvidedInterfaceDelegationConnector createAndRegisterProvidedInterfaceDelegationConnector(final ConnectorType connectorType) {
		final String rndName = NameUtils.createUniqueName(SYSPROVCONNECT_NO_NAME_PREFIX);

		return this.createAndRegisterProvidedInterfaceDelegationConnector(rndName, connectorType);
	}

	@Override
	protected SystemProvidedInterfaceDelegationConnector createEntity() {
		return ComponentAssemblyFactory.eINSTANCE.createSystemProvidedInterfaceDelegationConnector();
	}

	@Override
	public SystemProvidedInterfaceDelegationConnector lookupProvidedInterfaceDelegationConnector(final String fullyQualifiedName) {
		return this.lookup(fullyQualifiedName);
	}

	@Override
	public SystemProvidedInterfaceDelegationConnector lookupProvidedInterfaceDelegationConnector(final long id) {
		return this.lookupEntityById(id);
	}

	@Override
	public SystemProvidedInterfaceDelegationConnector lookupProvidedInterfaceDelegationConnector(final AssemblyComponent providingComponent,
			final Signature signature) {
		for (final SystemProvidedInterfaceDelegationConnector connector : this.getEntities()) {
			if (!connector.getProvidingComponent().equals(providingComponent)) {
				continue;
			}
			final Signature lookedUpSignature =
					this.typeRepositoryModelManager.lookupSignature(connector.getConnectorType().getInterface(),
							signature.getName(), signature.getReturnType(),
							signature.getParamTypes().toArray(new String[] {}),
							signature.getModifiers().toArray(new String[] {}));
			if (lookedUpSignature != null) {
				return connector;
			}
		}
		// no matching connector found
		return null;
	}

	@Override
	public boolean delegate(final SystemProvidedInterfaceDelegationConnector delegationConnector,
			final Interface providedInterface, final AssemblyComponent providingComponent) {
		/* First, we need to check if the interface is in the list of system-provided interfaces */
		if (!this.systemProvidedInterfaces.contains(providedInterface)) {
			LOG.error("Interface " + providedInterface + " not contained in the list of syste-provided " + "interfaces");
			return false;
		}

		/* Seconds, we need to make sure that the interfaces match */
		final Interface connectorInterface = delegationConnector.getConnectorType().getInterface();
		if (!providingComponent.getComponentType().getProvidedInterfaces().contains(connectorInterface)) {
			LOG.error("Providing component's type " + providingComponent + " does not have connetor's interface " + connectorInterface
					+ " in list of provided interfaces");
			return false;
		}

		/*
		 * The interfaces match -> connect
		 */
		delegationConnector.setProvidingComponent(providingComponent);

		return true;
	}

	@Override
	public boolean registerSystemProvidedInterface(final Interface iface) {
		return this.systemProvidedInterfaces.add(iface); // set is unique. Hence, we don't check if contained already
	}

	@Override
	public List<Interface> getSystemProvidedInterfaces() {
		return this.systemProvidedInterfaces;
	}
}
