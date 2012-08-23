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

	private final Hashtable<String, AssemblyContext> idToASMContext = new Hashtable<String, AssemblyContext>();

	/**
	 * table contains asmContext -> service name -> asmContext
	 */
	private final Hashtable<String, Hashtable<String, String>> requiringProvidingConnector = new Hashtable<String, Hashtable<String, String>>();
	private final Hashtable<String, ProvidesComponentType> asmIdToComponent = new Hashtable<String, ProvidesComponentType>();

	/**
	 * table contains externally visible service -> asmContext
	 */
	private final Hashtable<String, String> systemProvidedServices = new Hashtable<String, String>();
	private final Hashtable<String, Signature> systemProvidedServicesToSignature = new Hashtable<String, Signature>();

	public AssemblyController(final System system, final Model model) {
		this.genAssembly(system);
	}

	private void genAssembly(final System system) {
		final List<AssemblyContext> structures = system
				.getChildComponentContexts_ComposedStructure();
		for (final AssemblyContext structure : structures) {
			this.idToASMContext.put(structure.getId().toString(), structure);
			this.asmIdToComponent.put(structure.getId().toString(), structure.getEncapsulatedComponent_ChildComponentContext());
		}
		for (final AssemblyConnector connector : system.getCompositeAssemblyConnectors_ComposedStructure()) {
			final AssemblyContext targetContext = connector.getProvidingChildComponentContext_CompositeAssemblyConnector();
			final AssemblyContext srcContext = connector.getRequiringChildComponentContext_CompositeAssemblyConnector();
			Hashtable<String, String> srcIdMapping = this.requiringProvidingConnector.get(srcContext.getId());
			if (srcIdMapping == null) {
				srcIdMapping = new Hashtable<String, String>();
				this.requiringProvidingConnector.put(srcContext.getId(), srcIdMapping);
				LOG.info("Creating Lookup Table for Required Services of " + srcContext.getId());
			}
			for (final ProvidedRole role : targetContext.getEncapsulatedComponent_ChildComponentContext().getProvidedRoles_InterfaceProvidingEntity()) {
				for (final Signature i : role.getProvidedInterface__ProvidedRole().getSignatures__Interface()) {
					srcIdMapping.put(i.getServiceName(), targetContext.getId());
					LOG.info("Service " + i.getServiceName() + " required by " + srcContext.getId() + " maps to " + targetContext.getId());
				}
			}
		}
		for (final ProvidedDelegationConnector systemServiceConnector : system
				.getProvidedDelegationConnectors_ComposedStructure()) {
			LOG.info(systemServiceConnector);
			final ProvidedRole role = systemServiceConnector.getInnerProvidedRole_ProvidedDelegationConnector();
			LOG.info(role);
			LOG.info(role.getProvidedInterface__ProvidedRole());
			for (final Signature signature : role.getProvidedInterface__ProvidedRole().getSignatures__Interface()) {
				this.systemProvidedServices.put(signature.getServiceName(), systemServiceConnector.getChildComponentContext_ProvidedDelegationConnector().getId());
				this.systemProvidedServicesToSignature.put(signature.getServiceName(), signature);
			}
		}
		// TODO: find solution for simsystem
	}

	public final String getComponentByASMId(final String asmId) {
		final ProvidesComponentType asmC = this.asmIdToComponent.get(asmId);
		LOG.info("Looked up Component " + asmC + " for asm context " + asmId);
		if (asmC != null) {
			return asmC.getId();
		} else {
			return null;
		}
	}

	public final Collection<AssemblyContext> getAllASMContexts() {
		return this.idToASMContext.values();
	}

	public final AssemblyContext getASMContextById(final String id) {
		return this.idToASMContext.get(id);
	}

	public String asmContextForServiceCall(final String asmContextCaller, final String signatureId) {
		final Hashtable<String, String> caller = this.requiringProvidingConnector.get(asmContextCaller);
		if (caller != null) {
			return caller.get(signatureId);
			// caller;
		}
		return null;
	}

	public String getASMContextBySystemService(final String service) {
		return this.systemProvidedServices.get(service);
	}

	public Collection<String> getASMContextsProvidingExternalServices() {
		return this.systemProvidedServices.values();
	}

	public String getServiceASMContextConnectedWithContext(final String serviceName, final String asmContext) {
		return this.requiringProvidingConnector.get(asmContext)
				.get(serviceName);
	}

	public Signature getSignatureByExternalServiceName(final String serviceName) {
		for (final String s : this.systemProvidedServicesToSignature.keySet()) {
			LOG.info(s + " maps to " + this.systemProvidedServicesToSignature.get(s));
		}
		return this.systemProvidedServicesToSignature.get(serviceName);
	}

	public String getASMInstanceAndComponentNameById(final String id) {
		return this.idToASMContext.get(id).getEntityName() + ":" + this.asmIdToComponent.get(id).getEntityName();
	}
}
