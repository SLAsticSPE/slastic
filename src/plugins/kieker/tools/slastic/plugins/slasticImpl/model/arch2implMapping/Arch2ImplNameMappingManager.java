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

package kieker.tools.slastic.plugins.slasticImpl.model.arch2implMapping;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class Arch2ImplNameMappingManager {
	public enum EntityType {
		EXECUTION_CONTAINER, ASSEMBLY_COMPONENT
	}

	private final Map<EntityType, Map<String, String>> arch2implNameMappings = new ConcurrentHashMap<EntityType, Map<String, String>>();
	private final Map<EntityType, Map<String, String>> impl2archNameMappings = new ConcurrentHashMap<EntityType, Map<String, String>>();

	public Arch2ImplNameMappingManager() {
		for (final EntityType type : EntityType.values()) {
			{
				final Map<String, String> arch2implNameMapping = new ConcurrentHashMap<String, String>();
				this.arch2implNameMappings.put(type, arch2implNameMapping);
			}

			{
				final Map<String, String> impl2archNameMapping = new ConcurrentHashMap<String, String>();
				this.impl2archNameMappings.put(type, impl2archNameMapping);
			}

		}
	}

	private Map<String, String> arch2implNameMappingForType(final EntityType type) {
		return this.arch2implNameMappings.get(type);
	}

	private Map<String, String> impl2archNameMappingForType(final EntityType type) {
		return this.impl2archNameMappings.get(type);
	}

	/**
	 * Registers a bi-directional mapping among the given architectural and
	 * implementation-level names.
	 * 
	 * @param fqArchName
	 * @param fqImplName
	 */
	public void registerArch2implNameMapping(final EntityType type, final String fqArchName, final String fqImplName) {

		final Map<String, String> arch2implNameMappingForType = this.arch2implNameMappingForType(type);
		final Map<String, String> impl2archNameMappingForType = this.impl2archNameMappingForType(type);

		if (arch2implNameMappingForType.containsKey(fqArchName)) {
			throw new IllegalArgumentException("Mapping for architectural name '" + fqArchName + "' exists already");
		}
		if (impl2archNameMappingForType.containsKey(fqImplName)) {
			throw new IllegalArgumentException("Mapping for implementation-level name '" + fqImplName + "' exists already");
		}

		arch2implNameMappingForType.put(fqArchName, fqImplName);
		impl2archNameMappingForType.put(fqImplName, fqArchName);
	}

	/**
	 * Returns the architectural name that is mapped to the given
	 * implementation-level name.
	 * 
	 * @param implName
	 * @return
	 */
	public String lookupArchName4ImplName(final EntityType type, final String implName) {
		final Map<String, String> impl2archNameMappingForType = this.impl2archNameMappingForType(type);

		return impl2archNameMappingForType.get(implName);
	}

	/**
	 * Returns the implementation-level name that is mapped to the given
	 * architectural name.
	 * 
	 * @param archName
	 * @return
	 */
	public String lookupImplName4ArchName(final EntityType type, final String archName) {
		final Map<String, String> arch2implNameMappingForType = this.arch2implNameMappingForType(type);

		return arch2implNameMappingForType.get(archName);
	}

	@Override
	public String toString() {
		final StringBuffer strB = new StringBuffer();
		strB.append("arch2implNameMappings:\n").append(this.arch2implNameMappings);
		strB.append("\n\n");
		strB.append("impl2archNameMappings:\n").append(this.impl2archNameMappings);
		return strB.toString();
	}
}
