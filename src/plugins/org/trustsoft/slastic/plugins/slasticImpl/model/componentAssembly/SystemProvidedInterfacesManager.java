package org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyFactory;
import de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.Signature;

/**
 * 
 * @author Andre van Hoorn
 */
public class SystemProvidedInterfacesManager extends
		AbstractFQNamedEntityManager<SystemProvidedInterfaceDelegationConnector> implements
		ISystemProvidedInterfacesManager {
	private static final Log log = LogFactory.getLog(SystemProvidedInterfacesManager.class);

	public final static String SYSPROVCONNECT_NO_NAME_PREFIX = "ASM_SYSPROVCONN_NN_";

	private final TypeRepositoryModelManager typeRepositoryModelManager; // TODO: required?
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
		final SystemProvidedInterfaceDelegationConnector connector =
				this.createAndRegister(fullyQualifiedName);
		connector.setConnectorType(connectorType);
		return connector;
	}

	@Override
	public SystemProvidedInterfaceDelegationConnector createAndRegisterProvidedInterfaceDelegationConnector(
			final ConnectorType connectorType) {
		final String rndName =
				NameUtils.createUniqueName(SystemProvidedInterfacesManager.SYSPROVCONNECT_NO_NAME_PREFIX);

		return this.createAndRegisterProvidedInterfaceDelegationConnector(rndName, connectorType);
	}

	@Override
	protected SystemProvidedInterfaceDelegationConnector createEntity() {
		return ComponentAssemblyFactory.eINSTANCE.createSystemProvidedInterfaceDelegationConnector();
	}

	@Override
	public SystemProvidedInterfaceDelegationConnector lookupProvidedInterfaceDelegationConnector(
			final String fullyQualifiedName) {
		return this.lookup(fullyQualifiedName);
	}

	@Override
	public SystemProvidedInterfaceDelegationConnector lookupProvidedInterfaceDelegationConnector(final long id) {
		return this.lookupEntityById(id);
	}

	@Override
	public SystemProvidedInterfaceDelegationConnector lookupProvidedInterfaceDelegationConnector(
			final AssemblyComponent providingComponent, final Signature signature) {
		for (final SystemProvidedInterfaceDelegationConnector connector : this.getEntities()) {
			if (!connector.getProvidingComponent().equals(providingComponent)) {
				continue;
			}
			final Signature lookedUpSignature =
					this.typeRepositoryModelManager.lookupSignature(connector.getConnectorType().getInterface(),
							signature.getName(), signature.getReturnType(),
							signature.getParamTypes().toArray(new String[] {}));
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
		/* First, we need to make sure that the interfaces match */
		final Interface connectorInterface = delegationConnector.getConnectorType().getInterface();
		if (!providingComponent.getComponentType().getProvidedInterfaces().contains(connectorInterface)) {
			SystemProvidedInterfacesManager.log.error("Providing component's type " + providingComponent
					+ " does not have connetor's interface " + connectorInterface + " in list of provided interfaces");
			return false;
		}

		/*
		 * The interfaces match -> connect (reverse direction set by EMF
		 * framework classes)
		 */
		delegationConnector.setProvidingComponent(providingComponent);

		return true;
	}

	@Override
	public boolean registerSystemProvidedInterface(final Interface iface) {
		return this.systemProvidedInterfaces.add(iface); // set is unique. Hence, we don't check if contained already
	}
}
