package org.trustsoft.slastic.simulation.model.software.system;

import de.uka.ipd.sdq.pcm.repository.ProvidesComponentType;

public final class ReconfigurableComponent {

	private final ProvidesComponentType component;
	private final int maxInstances;
	private final boolean migratable;

	public ReconfigurableComponent(final ProvidesComponentType component,
			final int maxInstances, final boolean migratable) {
		this.component = component;
		this.maxInstances = maxInstances;
		this.migratable = migratable;
	}

	public final ProvidesComponentType getComponent() {
		return component;
	}

	public final int getMaxInstances() {
		return maxInstances;
	}

	public final boolean isMigratable() {
		return migratable;
	}
}
