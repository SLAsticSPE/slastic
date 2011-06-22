package org.trustsoft.slastic.tests.junit.model.manager.createRegisterLookupEntities;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentDeployment.ComponentDeploymentModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentDeployment.IDeploymentComponentsManager;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.ComponentDeploymentFactory;
import de.cau.se.slastic.metamodel.componentDeployment.ComponentDeploymentModel;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.core.SystemModel;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * Tests the functionalities provided by the component deployment manager for
 * creating, registering, and looking up deployment components. All test methods
 * are inherited from the abstract super class $
 * {@link AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest}.
 * 
 * @author Andre van Hoorn
 */
public class TestComponentDeploymentModelManager_DeploymentComponents extends
		AbstractSubmodelManagerCreateRegisterLookupEntityTest<ComponentDeploymentModel, DeploymentComponent> {

	private static final Log log = LogFactory.getLog(TestComponentDeploymentModelManager_DeploymentComponents.class);

	@Override
	protected ComponentDeploymentModel createModel() {
		return ComponentDeploymentFactory.eINSTANCE.createComponentDeploymentModel();
	}

	private DeploymentComponent createAndRegisterEntity(final AbstractModelManager<ComponentDeploymentModel> mgr,
			final ModelManager systemModelMgr) {
		if (!(mgr instanceof ComponentDeploymentModelManager)) {
			Assert.fail("mgr must be instance of ComponentDeploymentModelManager");
			return null;
		}
		final String nameBase = "XJKLHKH";
		/*
		 * An assembly component (type) and an execution container (type) is
		 * needed to create a deployment component -- we'll create these with
		 * generated names or use existing, if they exist already.
		 */
		/* Create component type */
		final String componentTypeName = nameBase + "Type"; // create type name
		ComponentType componentType = // use existing type instance if it exists
										// already
				systemModelMgr.getTypeRepositoryManager().lookupComponentType(componentTypeName);
		if (componentType == null) {
			componentType = systemModelMgr.getTypeRepositoryManager().createAndRegisterComponentType(componentTypeName);
		}
		Assert.assertNotNull("Test invalid: componentType == null", componentType);
		/* Create assembly component */
		final String assemblyComponentName = nameBase + "AssemblyComponent";
		AssemblyComponent assemblyComponent = // use existing assembly instance
												// if it exists already
				systemModelMgr.getComponentAssemblyModelManager().lookupAssemblyComponent(assemblyComponentName);
		if (assemblyComponent == null) {
			assemblyComponent =
					systemModelMgr.getComponentAssemblyModelManager().createAndRegisterAssemblyComponent(
							assemblyComponentName, componentType);
		}
		Assert.assertNotNull("Test invalid: assemblyComponent == null", assemblyComponent);
		/* Create execution container type */
		final String executionContainerTypeName = nameBase + "Type"; // create
																		// type
																		// name
																		// from
																		// execution
																		// container
																		// name
		ExecutionContainerType executionContainerType = // use existing type
														// instance if it exists
														// already
				systemModelMgr.getTypeRepositoryManager().lookupExecutionContainerType(executionContainerTypeName);
		if (executionContainerType == null) {
			executionContainerType =
					systemModelMgr.getTypeRepositoryManager().createAndRegisterExecutionContainerType(
							executionContainerTypeName);
		}
		Assert.assertNotNull("Test invalid: executionContainerType == null", executionContainerType);
		/* Create execution container */
		final String executionContainerName = nameBase + "ExecutionContainer";
		ExecutionContainer executionContainer = // use existing container
												// instance if it exists already
				systemModelMgr.getExecutionEnvironmentModelManager().lookupExecutionContainer(executionContainerName);
		if (executionContainer == null) {
			executionContainer =
					systemModelMgr.getExecutionEnvironmentModelManager().createAndRegisterExecutionContainer(
							executionContainerName, executionContainerType,
							/* mark allocated */ true);
		}
		Assert.assertNotNull("Test invalid: executionContainer == null", executionContainer);

		return ((IDeploymentComponentsManager) mgr).createAndRegisterDeploymentComponent(assemblyComponent,
				executionContainer);
	}

	/**
	 * Tests whether the lookup functions work properly.
	 */
	public final void testRegisterNewAndLookup() {
		final SystemModel systemModel = ModelManager.createInitializedSystemModel();
		final ModelManager systemModelMgr = new ModelManager(systemModel);
		final AbstractModelManager<ComponentDeploymentModel> mgr = this.getModelManager(systemModelMgr);
		final DeploymentComponent entity = this.createAndRegisterEntity(mgr, systemModelMgr);
		final DeploymentComponent componentTypeLookedUpById = this.lookupEntity(mgr, entity.getId());
		Assert.assertSame("Entity lookup by ID failed", entity, componentTypeLookedUpById);
	}

	@Override
	protected DeploymentComponent lookupEntity(final AbstractModelManager<ComponentDeploymentModel> mgr,
			final long entityId) {
		if (!(mgr instanceof ComponentDeploymentModelManager)) {
			Assert.fail("mgr must be instance of ComponentDeploymentModelManager");
			return null;
		}
		return ((IDeploymentComponentsManager) mgr).lookupDeploymentComponent(entityId);
	}

	@Override
	protected AbstractModelManager<ComponentDeploymentModel> getModelManager(final ModelManager systemModelMgr) {
		return systemModelMgr.getComponentDeploymentModelManager();
	}
}
