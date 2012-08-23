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

package org.trustsoft.slastic.tests.junit.model.manager.assembly;

import java.util.Arrays;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;
import org.trustsoft.slastic.tests.junit.model.ModelEntityCreationUtils;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector;
import de.cau.se.slastic.metamodel.core.SystemModel;
import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.Signature;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestAssemblyConnectorCreation extends TestCase {

	/**
	 * Makes sure that valid connections work.
	 */
	public void testValidConfiguration() {
		final String operationName = "opName";
		final String returnType = Boolean.class.getName();
		final String[] argTypes = { Integer.class.getName() };

		final SystemModel systemModel = ModelManager.createInitializedSystemModel();
		final ModelManager systemModelManager = new ModelManager(systemModel);
		final ComponentAssemblyModelManager componentAssemblyModelManager =
				systemModelManager.getComponentAssemblyModelManager();
		final TypeRepositoryModelManager typeRepositoryModelManager = systemModelManager.getTypeRepositoryManager();

		/*
		 * Create common interface used by all the two assembly components and
		 * the connector
		 */
		final Interface iface =
				this.createAndRegisterInterface(typeRepositoryModelManager,
						"MyInterface", operationName, returnType, argTypes);

		/*
		 * Create providing assembly component, register interface and implement
		 * operations in type
		 */
		final AssemblyComponent providingAsmComp =
				this.createAndRegisterAssemblyComponent(systemModelManager,
						"ProvidingComponentT", "providingComponent",
						/* provided interface */iface,
						/* required interface */null);

		/*
		 * Create requiring assembly component and register interface in type
		 */
		final AssemblyComponent requiringAsmComp =
				this.createAndRegisterAssemblyComponent(systemModelManager,
						"RequiringComponentT", "requiringComponent",
						/* provided interface */null,
						/* required interface */iface);

		
		/* Create assembly connector, register interface in type */
		final AssemblyComponentConnector asmConn =
				ModelEntityCreationUtils.createAssemblyConnector(systemModelManager, "AsmConnectT", iface);

		/* Connect (and assert that this was --- claimed to be --- successful) */
		Assert.assertTrue("Failed to connect assembly components via connector",
				componentAssemblyModelManager.connect(asmConn, requiringAsmComp, providingAsmComp));
		
		/* Assert that it was really successful by navigating the model */
		Assert.assertSame("Requiring component not registered in connector", requiringAsmComp,
				asmConn.getRequiringComponent());
		Assert.assertSame("Providing component not registered in connector", providingAsmComp,
				asmConn.getProvidingComponent());
		Assert.assertEquals("Unexpected number of providing connectors for requiring component", 1, requiringAsmComp
				.getProvidingConnectors().size());
		Assert.assertTrue("Connector not registered for requiring component", requiringAsmComp.getProvidingConnectors()
				.contains(asmConn));
		Assert.assertEquals("Unexpected number of requiring connectors for providing component", 1, providingAsmComp
				.getRequiringConnectors().size());
		Assert.assertTrue("Connector not registered for providing component", providingAsmComp.getRequiringConnectors()
				.contains(asmConn));
	}

	/**
	 * Makes sure that invalid connections (in this case an
	 * {@link AssemblyComponentConnector} with an {@link Interface} different than that
	 * of {@link AssemblyComponent}s) raise an error.
	 */
	public void testUnmatchingConnector() {
		final String operationName = "opName";
		final String returnType = Boolean.class.getName();
		final String[] argTypes = { Integer.class.getName() };

		final SystemModel systemModel = ModelManager.createInitializedSystemModel();
		final ModelManager systemModelManager = new ModelManager(systemModel);
		final ComponentAssemblyModelManager componentAssemblyModelManager =
				systemModelManager.getComponentAssemblyModelManager();
		final TypeRepositoryModelManager typeRepositoryModelManager = systemModelManager.getTypeRepositoryManager();

		/*
		 * Create two different interfaces; one used by the two assembly
		 * components and another used by the connector
		 */
		final Interface componentIface =
				this.createAndRegisterInterface(typeRepositoryModelManager, "IComponentInterface",
						operationName, returnType, argTypes);
		final Interface connectorIface =
				this.createAndRegisterInterface(typeRepositoryModelManager, "IConnectorInterface",
						operationName, returnType, argTypes);

		/*
		 * Create providing assembly component, register interface and implement
		 * operations in type
		 */
		final AssemblyComponent providingAsmComp =
				this.createAndRegisterAssemblyComponent(systemModelManager,
						"ProvidingComponentT", "providingComponent",
						/* provided interface */componentIface,
						/* required interface */null);

		/*
		 * Create requiring assembly component and register interface in type
		 */
		final AssemblyComponent requiringAsmComp =
				this.createAndRegisterAssemblyComponent(systemModelManager,
						"RequiringComponentT", "requiringComponent",
						/* provided interface */null,
						/* required interface */componentIface);

		/* Create assembly connector, register interface in type */
		final AssemblyComponentConnector asmConn =
				ModelEntityCreationUtils.createAssemblyConnector(systemModelManager, "AsmConnectT", connectorIface);

		/* Connect (and assert that this was --- claimed to be --- successful) */
		Assert.assertFalse("Exepected connection request to fail",
				componentAssemblyModelManager.connect(asmConn, requiringAsmComp, providingAsmComp));
	}

	/**
	 * Creates and registers an {@link Interface} with the given name, as well
	 * as a single signature with the given parameters.
	 * 
	 * @param mgr
	 * @param ifaceName
	 * @param operationName
	 * @param returnType
	 * @param argTypes
	 * @return
	 */
	private Interface createAndRegisterInterface(final TypeRepositoryModelManager mgr, final String ifaceName,
			final String operationName, final String returnType, final String[] argTypes) {
		final Interface iface = mgr.createAndRegisterInterface(ifaceName);
		mgr.createAndRegisterSignature(iface, operationName, returnType,
				Arrays.copyOf(argTypes, argTypes.length));
		return iface;
	}

	/**
	 * 
	 * @param modelManager
	 * @param fqComponentTypeName
	 * @param fqAssemblyComponentName
	 * @param providedInterface
	 *            may be null
	 * @param requiredInterface
	 *            may be null
	 * @return
	 */
	private AssemblyComponent createAndRegisterAssemblyComponent(
			final ModelManager modelManager,
			final String fqComponentTypeName,
			final String fqAssemblyComponentName,
			final Interface providedInterface,
			final Interface requiredInterface) {
		final TypeRepositoryModelManager tMgr = modelManager.getTypeRepositoryManager();

		final AssemblyComponent asmComp =
				ModelEntityCreationUtils.createAssemblyComponent(modelManager, fqComponentTypeName,
						fqAssemblyComponentName);

		// Register provided interface (if such)
		if (providedInterface != null) {
			for (final Signature s : providedInterface.getSignatures()) {
				tMgr.createAndRegisterOperation(asmComp.getComponentType(),
						s.getName(), s.getReturnType(), s.getParamTypes().toArray(new String[] {}));
			}
			tMgr.registerProvidedInterface(asmComp.getComponentType(), providedInterface);
		}

		// Register required interface (if such)
		if (requiredInterface != null) {
			// Operations of required interfaces must not be registered!
			tMgr.registerRequiredInterface(asmComp.getComponentType(), requiredInterface);
		}

		return asmComp;
	}
}
