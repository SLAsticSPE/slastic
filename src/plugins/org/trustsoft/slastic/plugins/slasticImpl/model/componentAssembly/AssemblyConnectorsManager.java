package org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyFactory;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.Signature;

/**
 * 
 * @author Andre van Hoorn
 */
public class AssemblyConnectorsManager extends AbstractFQNamedEntityManager<AssemblyComponentConnector> implements
		IAssemblyConnectorsManager {
	private static final Log log = LogFactory.getLog(AssemblyConnectorsManager.class);

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
		final AssemblyComponentConnector assemblyConnector =
				this.createAndRegister(fullyQualifiedName);
		assemblyConnector.setConnectorType(connectorType);
		return assemblyConnector;
	}

	@Override
	public AssemblyComponentConnector createAndRegisterAssemblyConnector(final ConnectorType connectorType) {
		final String rndName = NameUtils.createUniqueName(AssemblyConnectorsManager.ASMCONNECT_NO_NAME_PREFIX);

		return this.createAndRegisterAssemblyConnector(rndName, connectorType);
	}

	@Override
	protected AssemblyComponentConnector createEntity() {
		return ComponentAssemblyFactory.eINSTANCE.createAssemblyComponentConnector();
	}

	@Override
	public AssemblyComponentConnector lookupAssemblyConnector(
			final String fullyQualifiedName) {
		return this.lookup(fullyQualifiedName);
	}

	@Override
	public AssemblyComponentConnector lookupAssemblyConnector(final long id) {
		return this.lookupEntityById(id);
	}


	@Override
	public AssemblyComponentConnector lookupAssemblyConnector(final AssemblyComponent requiringComponent,
			final AssemblyComponent providingComponent, final Signature signature) {
		for (final AssemblyComponentConnector connector : requiringComponent.getProvidingConnectors()) {
			final Signature lookedUpSignature = 
				this.typeRepositoryModelManager.lookupSignature(connector.getConnectorType().getInterface(), 
						signature.getName(), signature.getReturnType(), signature.getParamTypes().toArray(new String[]{}));
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
			AssemblyConnectorsManager.log.error("Requiring component's type " + requiringComponent
					+ " does not have connetor's interface " + connectorInterface + " in list of required interfaces");
			return false;
		}
		if (!providingComponent.getComponentType().getProvidedInterfaces().contains(connectorInterface)) {
			AssemblyConnectorsManager.log.error("Providing component's type " + providingComponent
					+ " does not have connetor's interface " + connectorInterface + " in list of provided interfaces");
			return false;
		}

		/* The interfaces match -> connect (reverse direction set by EMF framework classes) */
		assemblyConnector.setProvidingComponent(providingComponent);
		assemblyConnector.setRequiringComponent(requiringComponent);
		
		return true;
	}
}
