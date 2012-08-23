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

package org.trustsoft.slastic.tests.junit.framework.esper;

import java.util.UUID;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.BasicNotifierImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.trustsoft.slastic.control.exceptions.CEPEngineExceptionHandlerFactory;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.ConfigurationEventTypeLegacy;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class EPServiceProviderFactory {

	/**
	 * Creates an {@link EPServiceProvider} with a custom {@link Configuration}
	 * that support EMF types to be sent via {@link EPRuntime#sendEvent(Object)}
	 * .
	 * 
	 * @return
	 */
	public static EPServiceProvider createInstanceWithEMFSupport() {
		final EPServiceProvider epService;
		/*
		 * We use a custom Esper configuration:
		 */
		final Configuration configuration = new Configuration();

		/*
		 * Exclude some EMF classes from dynamic code generation and
		 * JavaBean-like access to members.
		 */
		final ConfigurationEventTypeLegacy legacyDef =
					new ConfigurationEventTypeLegacy();
		legacyDef
					.setCodeGeneration(ConfigurationEventTypeLegacy.CodeGeneration.DISABLED);
		legacyDef
					.setAccessorStyle(ConfigurationEventTypeLegacy.AccessorStyle.EXPLICIT);
		configuration.addEventType("EObject", EObject.class.getName(),
					legacyDef);
		configuration.addEventType("Notifier", Notifier.class.getName(),
					legacyDef);
		configuration.addEventType("EObjectImpl", EObjectImpl.class.getName(),
					legacyDef);
		configuration.addEventType("BasicEObjectImpl",
					BasicEObjectImpl.class.getName(), legacyDef);
		configuration.addEventType("BasicNotifierImpl",
					BasicNotifierImpl.class.getName(), legacyDef);
		configuration.addEventType("InternalEObject",
					InternalEObject.class.getName(), legacyDef);

		/* Register exception handler (factory class) */
		configuration.getEngineDefaults().getExceptionHandling()
					.addClass(CEPEngineExceptionHandlerFactory.class);

		epService =
					EPServiceProviderManager.getProvider(UUID.randomUUID().toString(), configuration);
		return epService;
	}
}
