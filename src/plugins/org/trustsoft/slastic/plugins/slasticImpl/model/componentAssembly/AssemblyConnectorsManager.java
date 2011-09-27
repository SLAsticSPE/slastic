package org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyFactory;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.Interface;

/**
 * 
 * @author Andre van Hoorn
 */
public class AssemblyConnectorsManager extends AbstractFQNamedEntityManager<AssemblyConnector> implements
		IAssemblyConnectorsManager {
	private static final Log log = LogFactory.getLog(AssemblyConnectorsManager.class);

	public AssemblyConnectorsManager(final List<AssemblyConnector> AssemblyConnectors) {
		super(AssemblyConnectors);
	}

	@Override
	public AssemblyConnector lookupAssemblyConnector(
			final String fullyQualifiedName) {
		return this.lookup(fullyQualifiedName);
	}

	@Override
	public AssemblyConnector lookupAssemblyConnector(final long id) {
		return this.lookupEntityById(id);
	}

	public final static String ASSEMBLY_NO_NAME_PREFIX = "ASM_CONN_NN_";

	@Override
	public AssemblyConnector createAndRegisterAssemblyConnector(
			final String fullyQualifiedName,
			final ConnectorType connectorType) {
		final AssemblyConnector assemblyConnector =
				this.createAndRegister(fullyQualifiedName);
		assemblyConnector.setConnectorType(connectorType);
		return assemblyConnector;
	}

	@Override
	public AssemblyConnector createAndRegisterAssemblyConnector(final ConnectorType connectorType) {
		final String rndName = NameUtils.createUniqueName(AssemblyConnectorsManager.ASSEMBLY_NO_NAME_PREFIX);

		return this.createAndRegisterAssemblyConnector(rndName, connectorType);
	}

	@Override
	protected AssemblyConnector createEntity() {
		return ComponentAssemblyFactory.eINSTANCE.createAssemblyConnector();
	}

	@Override
	public boolean connect(final AssemblyConnector assemblyConnector, final AssemblyComponent requiringComponent,
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

		/* The interfaces match -> connect */
		assemblyConnector.setProvidingComponent(providingComponent);
		assemblyConnector.setRequiringComponent(requiringComponent);
		
		// TODO: we need to check if the reverse direction is set automatically
		//       or if we need to do this manually.
		
		return true;
	}
}
