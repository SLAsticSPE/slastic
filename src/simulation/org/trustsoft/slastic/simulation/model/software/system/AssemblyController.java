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

package org.trustsoft.slastic.simulation.model.software.system;

import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyConnector;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.core.composition.ProvidedDelegationConnector;
import de.uka.ipd.sdq.pcm.repository.ProvidedRole;
import de.uka.ipd.sdq.pcm.repository.ProvidesComponentType;
import de.uka.ipd.sdq.pcm.repository.Signature;
import de.uka.ipd.sdq.pcm.system.System;
import desmoj.core.simulator.Model;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class AssemblyController {
	private static final Log LOG = LogFactory.getLog(AssemblyController.class);

	private final Hashtable<String, AssemblyContext> assemblyContextsById = new Hashtable<String, AssemblyContext>();

	/**
	 * table contains asmContext -> service name -> asmContext
	 */
	private final Hashtable<String, Hashtable<String, String>> assemblyContextIdForRequiredServices = new Hashtable<String, Hashtable<String, String>>();

	// TODO: Most likely, this map is useless
	private final Hashtable<String, ProvidesComponentType> componentTypesByAssemblyContextId = new Hashtable<String, ProvidesComponentType>();

	/**
	 * table contains externally visible service -> asmContext
	 */
	private final Hashtable<String, String> assemblyContextIdsBySystemProvidedServiceNames = new Hashtable<String, String>();

	private final Hashtable<String, Signature> systemProvidedServiceNamesToSignature = new Hashtable<String, Signature>();

	public AssemblyController(final System system, final Model model) {
		this.genAssembly(system);
	}

	private void genAssembly(final System system) {
		/*
		 * 1. Handle contained assembly contexts
		 */
		final List<AssemblyContext> structures = system.getChildComponentContexts_ComposedStructure();
		for (final AssemblyContext structure : structures) {
			this.assemblyContextsById.put(structure.getId().toString(), structure);
			this.componentTypesByAssemblyContextId.put(structure.getId().toString(), structure.getEncapsulatedComponent_ChildComponentContext());
		}

		/*
		 * 2. Handle assembly connectors
		 */
		for (final AssemblyConnector connector : system.getCompositeAssemblyConnectors_ComposedStructure()) {
			final AssemblyContext targetContext = connector.getProvidingChildComponentContext_CompositeAssemblyConnector();
			final AssemblyContext srcContext = connector.getRequiringChildComponentContext_CompositeAssemblyConnector();
			Hashtable<String, String> srcIdMapping = this.assemblyContextIdForRequiredServices.get(srcContext.getId());
			if (srcIdMapping == null) {
				srcIdMapping = new Hashtable<String, String>();
				this.assemblyContextIdForRequiredServices.put(srcContext.getId(), srcIdMapping);
				LOG.info("Creating Lookup Table for Required Services of " + srcContext.getId());
			}
			for (final ProvidedRole role : targetContext.getEncapsulatedComponent_ChildComponentContext().getProvidedRoles_InterfaceProvidingEntity()) {
				for (final Signature i : role.getProvidedInterface__ProvidedRole().getSignatures__Interface()) {
					srcIdMapping.put(i.getServiceName(), targetContext.getId());
					LOG.info("Service " + i.getServiceName() + " required by " + srcContext.getId() + " maps to " + targetContext.getId());
				}
			}
		}

		/*
		 * 3. Handle system-provided services
		 */
		for (final ProvidedDelegationConnector systemServiceConnector : system.getProvidedDelegationConnectors_ComposedStructure()) {
			LOG.info(systemServiceConnector);
			final ProvidedRole role = systemServiceConnector.getInnerProvidedRole_ProvidedDelegationConnector();
			LOG.info(role);
			LOG.info(role.getProvidedInterface__ProvidedRole());
			for (final Signature signature : role.getProvidedInterface__ProvidedRole().getSignatures__Interface()) {
				this.assemblyContextIdsBySystemProvidedServiceNames
						.put(signature.getServiceName(), systemServiceConnector.getChildComponentContext_ProvidedDelegationConnector().getId());
				this.systemProvidedServiceNamesToSignature.put(signature.getServiceName(), signature);
			}
		}
		// TODO: find solution for simsystem
	}

	// TODO: What's a use case for this method?
	public final String getComponentTypeByAssemblyContextedId(final String asmId) {
		final ProvidesComponentType asmC = this.componentTypesByAssemblyContextId.get(asmId);
		LOG.info("Looked up Component " + asmC + " for asm context " + asmId);
		if (asmC != null) {
			return asmC.getId();
		} else {
			return null;
		}
	}

	public final Collection<AssemblyContext> getAssemblyContexts() {
		return this.assemblyContextsById.values();
	}

	public final AssemblyContext getAssemblyContextById(final String id) {
		return this.assemblyContextsById.get(id);
	}

	public String getAssemblyContextForServiceCall(final String asmContextCaller, final String signatureId) {
		final Hashtable<String, String> caller = this.assemblyContextIdForRequiredServices.get(asmContextCaller);
		if (caller != null) {
			return caller.get(signatureId);
			// caller;
		}
		return null;
	}

	public String getAssemblyContextBySystemServiceName(final String service) {
		return this.assemblyContextIdsBySystemProvidedServiceNames.get(service);
	}

	// TODO: What's a use case for this method?
	public Collection<String> getASMContextsProvidingExternalServices() {
		return this.assemblyContextIdsBySystemProvidedServiceNames.values();
	}

	public String getServiceASMContextConnectedWithContext(final String serviceName, final String asmContext) {
		return this.assemblyContextIdForRequiredServices.get(asmContext).get(serviceName);
	}

	public Signature getSignatureByExternalServiceName(final String serviceName) {
		for (final String s : this.systemProvidedServiceNamesToSignature.keySet()) {
			LOG.info(s + " maps to " + this.systemProvidedServiceNamesToSignature.get(s));
		}
		return this.systemProvidedServiceNamesToSignature.get(serviceName);
	}

	public String getASMInstanceAndComponentNameById(final String id) {
		return this.assemblyContextsById.get(id).getEntityName() + ":" + this.componentTypesByAssemblyContextId.get(id).getEntityName();
	}
}
