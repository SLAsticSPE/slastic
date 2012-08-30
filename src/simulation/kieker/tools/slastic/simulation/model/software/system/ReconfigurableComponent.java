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

package kieker.tools.slastic.simulation.model.software.system;

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
