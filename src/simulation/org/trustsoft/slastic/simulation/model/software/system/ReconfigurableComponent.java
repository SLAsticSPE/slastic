package org.trustsoft.slastic.simulation.model.software.system;

import de.uka.ipd.sdq.pcm.repository.ProvidesComponentType;

/**
 * 
 * @author Robert von Massow
 * 
 */
public final class ReconfigurableComponent {

	private final ProvidesComponentType component;
	private final int maxInstances;
	private final boolean migratable;

	public ReconfigurableComponent(final ProvidesComponentType component, final int maxInstances, final boolean migratable) {
		this.component = component;
		this.maxInstances = maxInstances;
		this.migratable = migratable;
	}

	public final ProvidesComponentType getComponent() {
		return this.component;
	}

	public final int getMaxInstances() {
		return this.maxInstances;
	}

	public final boolean isMigratable() {
		return this.migratable;
	}
}
