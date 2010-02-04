package org.trustsoft.slastic.simulation.model.software.system;

import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyConnector;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.core.composition.ProvidedDelegationConnector;
import de.uka.ipd.sdq.pcm.repository.ProvidedRole;
import de.uka.ipd.sdq.pcm.repository.ProvidesComponentType;
import de.uka.ipd.sdq.pcm.repository.Signature;
import de.uka.ipd.sdq.pcm.system.System;
import desmoj.core.simulator.Model;

public class AssemblyController {

	private final Hashtable<String, SimSystem> nameToSystemMap = new Hashtable<String, SimSystem>();
	private final Hashtable<String, SimSystem> idToSystemMap = new Hashtable<String, SimSystem>();
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

	private final Model model;

	public AssemblyController(final System system, final Model model) {
		genAssembly(system);
		this.model = model;
	}

	private void genAssembly(final System system) {
		final SimSystem simSystem = SimSystem.getInstance();
		final List<AssemblyContext> structures = system
				.getChildComponentContexts_ComposedStructure();
		for (final AssemblyContext structure : structures) {
			simSystem.addComponent(system.getEntityName(), structure
					.getEncapsulatedComponent_ChildComponentContext());
			idToASMContext.put(structure.getId().toString(), structure);
			asmIdToComponent.put(structure.getId().toString(), structure
					.getEncapsulatedComponent_ChildComponentContext());
		}
		for (final AssemblyConnector connector : system
				.getCompositeAssemblyConnectors_ComposedStructure()) {
			final AssemblyContext targetContext = connector
					.getProvidingChildComponentContext_CompositeAssemblyConnector();
			final AssemblyContext srcContext = connector
					.getRequiringChildComponentContext_CompositeAssemblyConnector();
			Hashtable<String, String> srcIdMapping = requiringProvidingConnector
					.get(srcContext);
			if (srcIdMapping == null) {
				srcIdMapping = new Hashtable<String, String>();
				requiringProvidingConnector.put(srcContext.getId(),
						srcIdMapping);
			}
			for (final ProvidedRole role : targetContext
					.getEncapsulatedComponent_ChildComponentContext()
					.getProvidedRoles_InterfaceProvidingEntity()) {
				for (final Signature i : role
						.getProvidedInterface__ProvidedRole()
						.getSignatures__Interface()) {
					srcIdMapping.put(i.getServiceName(), targetContext.getId());
				}
			}
		}
		for (final ProvidedDelegationConnector systemServiceConnector : system
				.getProvidedDelegationConnectors_ComposedStructure()) {
			final ProvidedRole role = systemServiceConnector
					.getInnerProvidedRole_ProvidedDelegationConnector();
			for (final Signature signature : role
					.getProvidedInterface__ProvidedRole()
					.getSignatures__Interface()) {
				systemProvidedServices
						.put(
								signature.getServiceName(),
								systemServiceConnector
										.getChildComponentContext_ProvidedDelegationConnector()
										.getId());
				systemProvidedServicesToSignature.put(signature
						.getServiceName(), signature);
			}
		}
		// TODO: find solution for simsystem
	}

	public final SimSystem getSystemById(final String id) {
		return idToSystemMap.get(id);
	}

	public final SimSystem getSystemByName(final String name) {
		return nameToSystemMap.get(name);
	}

	public final String getComponentByASMId(final String asmId) {
		final ProvidesComponentType asmC = asmIdToComponent.get(asmId);
		if (asmC != null) {
			return asmC.getId();
		} else {
			return null;
		}
	}

	public final Collection<AssemblyContext> getAllASMContexts() {
		return idToASMContext.values();
	}

	public String asmContextForServiceCall(final String asmContextCaller,
			final String signatureId) {
		final Hashtable<String, String> caller = requiringProvidingConnector
				.get(asmContextCaller);
		if (caller != null) {
			return caller.get(signatureId);
			// caller;
		}
		return null;
	}

	public String getASMContextBySystemService(final String service) {
		return systemProvidedServices.get(service);
	}

	public String getServiceASMContextConnectedWithContext(
			final String serviceName, final String asmContext) {
		return requiringProvidingConnector.get(asmContext).get(serviceName);
	}

	public Signature getSignatureByExternalServiceName(final String serviceName) {
		return systemProvidedServicesToSignature.get(serviceName);
	}
}