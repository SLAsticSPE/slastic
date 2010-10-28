package org.trustsoft.slastic.tests.junit.model.manager.createRegisterLookupEntities;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryModel;

/**
 * Tests the functionalities provided by the type repository manager for
 * creating, registering, and looking up component type. All test methods are
 * inherited from the abstract super class $
 * {@link AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest}.
 * 
 * @author Andre van Hoorn
 */
public class TestTypeRepositoryModelManager_ExecutionContainerTypes
		extends
		AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest<TypeRepositoryModel, ExecutionContainerType> {

	private static final Log log =
			LogFactory
					.getLog(TestTypeRepositoryModelManager_ExecutionContainerTypes.class);

	@Override
	protected TypeRepositoryModel createModel() {
		return TypeRepositoryFactory.eINSTANCE.createTypeRepositoryModel();
	}

	@Override
	protected ExecutionContainerType createAndRegisterEntity(
			final AbstractModelManager<TypeRepositoryModel> mgr,
			final String fqEntityName,
			final ModelManager systemModelMgr) {
		if (!(mgr instanceof TypeRepositoryModelManager)) {
			Assert.fail("mgr must be instance of TypeRepositoryModelManager");
			return null;
		}
		final ExecutionContainerType executionContainerType =
				((TypeRepositoryModelManager) mgr)
						.createAndRegisterExecutionContainerType(fqEntityName);
		Assert.assertNotNull("List of resources must not be null",
				executionContainerType.getResources());
		return executionContainerType;
	}

	@Override
	protected ExecutionContainerType lookupEntity(
			final AbstractModelManager<TypeRepositoryModel> mgr,
			final String fqEntityName) {
		if (!(mgr instanceof TypeRepositoryModelManager)) {
			Assert.fail("mgr must be instance of TypeRepositoryModelManager");
			return null;
		}
		return ((TypeRepositoryModelManager) mgr)
				.lookupExecutionContainerType(fqEntityName);
	}

	@Override
	protected ExecutionContainerType lookupEntity(
			final AbstractModelManager<TypeRepositoryModel> mgr,
			final long entityId) {
		if (!(mgr instanceof TypeRepositoryModelManager)) {
			Assert.fail("mgr must be instance of TypeRepositoryModelManager");
			return null;
		}
		return ((TypeRepositoryModelManager) mgr)
				.lookupExecutionContainerType(entityId);
	}

	@Override
	protected AbstractModelManager<TypeRepositoryModel> getModelManager(
			final ModelManager systemModelMgr) {
		return systemModelMgr.getTypeRepositoryManager();
	}
}
