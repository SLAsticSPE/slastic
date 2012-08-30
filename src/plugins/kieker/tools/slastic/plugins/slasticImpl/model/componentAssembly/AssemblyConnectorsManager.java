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

import kieker.tools.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;
import kieker.tools.slastic.plugins.slasticImpl.model.NameUtils;
import kieker.tools.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponentConnector;
import kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyFactory;
import kieker.tools.slastic.metamodel.typeRepository.ConnectorType;
import kieker.tools.slastic.metamodel.typeRepository.Interface;
import kieker.tools.slastic.metamodel.typeRepository.Signature;

/**
 * 
 * @author Andre van Hoorn
 */
public class AssemblyConnectorsManager extends AbstractFQNamedEntityManager<AssemblyComponentConnector> implements IAssemblyConnectorsManager {
	private static final Log LOG = LogFactory.getLog(AssemblyConnectorsManager.class);

	public final static String ASMCONNECT_NO_NAME_PREFIX = "ASM_CONN_NN_";

	private final TypeRepositoryModelManager typeRepositoryModelManager;

	public AssemblyConnectorsManager(final List<AssemblyComponentConnector> AssemblyConnectors, final TypeRepositoryModelManager typeRepositoryModelManager) {
		super(AssemblyConnectors);
		this.typeRepositoryModelManager = typeRepositoryModelManager;
	}

	@Override
	public AssemblyComponentConnector createAndRegisterAssemblyConnector(
			final String fullyQualifiedName,
			final ConnectorType connectorType) {
		final AssemblyComponentConnector assemblyConnector = this.createAndRegister(fullyQualifiedName);
		assemblyConnector.setConnectorType(connectorType);
		return assemblyConnector;
	}

	@Override
	public AssemblyComponentConnector createAndRegisterAssemblyConnector(final ConnectorType connectorType) {
		final String rndName = NameUtils.createUniqueName(ASMCONNECT_NO_NAME_PREFIX);

		return this.createAndRegisterAssemblyConnector(rndName, connectorType);
	}

	@Override
	protected AssemblyComponentConnector createEntity() {
		return ComponentAssemblyFactory.eINSTANCE.createAssemblyComponentConnector();
	}

	@Override
	public AssemblyComponentConnector lookupAssemblyConnector(final String fullyQualifiedName) {
		return this.lookup(fullyQualifiedName);
	}

	@Override
	public AssemblyComponentConnector lookupAssemblyConnector(final long id) {
		return this.lookupEntityById(id);
	}

	@Override
	public AssemblyComponentConnector lookupAssemblyConnector(final AssemblyComponent requiringComponent, final AssemblyComponent providingComponent,
			final Signature signature) {
		for (final AssemblyComponentConnector connector : requiringComponent.getProvidingConnectors()) {
			final Signature lookedUpSignature =
					this.typeRepositoryModelManager.lookupSignature(connector.getConnectorType().getInterface(),
							signature.getName(), signature.getReturnType(), signature.getParamTypes().toArray(new String[] {}));
			if (lookedUpSignature != null) {
				return connector;
			}
		}
		// no matching connector found
		return null;
	}

	@Override
	public boolean connect(final AssemblyComponentConnector assemblyConnector, final AssemblyComponent requiringComponent,
			final AssemblyComponent providingComponent) {
		/* First, we need to make sure that the interfaces match */
		final Interface connectorInterface = assemblyConnector.getConnectorType().getInterface();
		if (!requiringComponent.getComponentType().getRequiredInterfaces().contains(connectorInterface)) {
			LOG.error("Requiring component's type " + requiringComponent
					+ " does not have connetor's interface " + connectorInterface + " in list of required interfaces");
			return false;
		}
		if (!providingComponent.getComponentType().getProvidedInterfaces().contains(connectorInterface)) {
			LOG.error("Providing component's type " + providingComponent
					+ " does not have connetor's interface " + connectorInterface + " in list of provided interfaces");
			return false;
		}

		/* The interfaces match -> connect (reverse direction set by EMF framework classes) */
		assemblyConnector.setProvidingComponent(providingComponent);
		assemblyConnector.setRequiringComponent(requiringComponent);

		return true;
	}
}
