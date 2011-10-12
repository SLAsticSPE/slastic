package org.trustsoft.slastic.tests.junit.model.manager.createRegisterLookupEntities;

import junit.framework.Assert;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyFactory;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel;
import de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.Interface;

/**
 * Tests the functionalities provided by the connector assembly manager for
 * creating, registering, and looking up
 * {@link SystemProvidedInterfaceDelegationConnector}s. All test methods are
 * inherited from the abstract super class $
 * {@link AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest}.
 * 
 * @author Andre van Hoorn
 */
public class TestComponentAssemblyModelManager_SystemProvidedInterfaces
		extends
		AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest<ComponentAssemblyModel, SystemProvidedInterfaceDelegationConnector> {

	// private static final Log log = LogFactory
	// .getLog(TestComponentAssemblyModelManager_SystemProvidedInterfaces.class);

	@Override
	protected ComponentAssemblyModel createModel() {
		return ComponentAssemblyFactory.eINSTANCE.createComponentAssemblyModel();
	}

	@Override
	protected SystemProvidedInterfaceDelegationConnector createAndRegisterEntity(
			final AbstractModelManager<ComponentAssemblyModel> mgr, final String fqEntityName,
			final ModelManager systemModelMgr) {
		if (!(mgr instanceof ComponentAssemblyModelManager)) {
			Assert.fail("mgr must be instance of ComponentAssemblyModelManager");
			return null;
		}
		/*
		 * A connector type is needed to create a connector -- we'll create one
		 * with a generated name or use an existing one, if a connector type
		 * with this name exists already.
		 */
		final String connectorTypeName = fqEntityName + "Type"; // create type
																// name from
																// connector
																// name
		final TypeRepositoryModelManager typeModelMgr = systemModelMgr.getTypeRepositoryManager();

		ConnectorType connectorType = // use existing type instance if it exists
										// already
				typeModelMgr.lookupConnectorType(connectorTypeName);
		if (connectorType == null) {
			final Interface iface = typeModelMgr.createAndRegisterInterface("I" + fqEntityName);
			connectorType =
					systemModelMgr.getTypeRepositoryManager().createAndRegisterConnectorType(connectorTypeName, iface);
		}
		Assert.assertNotNull("Test invalid: connectorType == null", connectorType);
		return ((ComponentAssemblyModelManager) mgr).createAndRegisterProvidedInterfaceDelegationConnector(
				fqEntityName, connectorType);
	}

	@Override
	protected SystemProvidedInterfaceDelegationConnector lookupEntity(
			final AbstractModelManager<ComponentAssemblyModel> mgr,
			final String fqEntityName) {
		if (!(mgr instanceof ComponentAssemblyModelManager)) {
			Assert.fail("mgr must be instance of ComponentAssemblyModelManager");
			return null;
		}
		return ((ComponentAssemblyModelManager) mgr).lookupProvidedInterfaceDelegationConnector(fqEntityName);
	}

	@Override
	protected SystemProvidedInterfaceDelegationConnector lookupEntity(
			final AbstractModelManager<ComponentAssemblyModel> mgr,
			final long entityId) {
		if (!(mgr instanceof ComponentAssemblyModelManager)) {
			Assert.fail("mgr must be instance of ComponentAssemblyModelManager");
			return null;
		}
		return ((ComponentAssemblyModelManager) mgr).lookupProvidedInterfaceDelegationConnector(entityId);
	}

	@Override
	protected AbstractModelManager<ComponentAssemblyModel> getModelManager(final ModelManager systemModelMgr) {
		return systemModelMgr.getComponentAssemblyModelManager();
	}
}
