package org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly;

import java.util.List;

import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyFactory;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;

/**
 * 
 * @author Andre van Hoorn
 */
public class AssemblyConnectorsManager extends AbstractFQNamedEntityManager<AssemblyConnector> implements
		IAssemblyConnectorsManager {
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
		// TODO: implement
		return false;
	}
}
