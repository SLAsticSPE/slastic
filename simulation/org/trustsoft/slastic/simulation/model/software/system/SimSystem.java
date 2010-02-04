package org.trustsoft.slastic.simulation.model.software.system;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import org.trustsoft.slastic.simulation.user.model.CallNode;

import de.uka.ipd.sdq.pcm.repository.ProvidesComponentType;

public class SimSystem {

	private Set<ProvidesComponentType> components;
	private final Hashtable<String, Set<ProvidesComponentType>> systemTable = new Hashtable<String, Set<ProvidesComponentType>>();

	private static final SimSystem instance = new SimSystem();

	private SimSystem() {
	}

	public void addComponent(
			final String name,
			final ProvidesComponentType encapsulatedComponent_ChildComponentContext) {
		if (systemTable.get(name) == null) {
			systemTable.put(name, new HashSet<ProvidesComponentType>());
		}
		systemTable.get(name).add(encapsulatedComponent_ChildComponentContext);
	}

	public static SimSystem getInstance() {
		return SimSystem.instance;
	}

	public void call(final String signature, final CallNode callNode) {

	}

}
