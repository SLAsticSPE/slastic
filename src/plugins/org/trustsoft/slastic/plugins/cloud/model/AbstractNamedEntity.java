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

package org.trustsoft.slastic.plugins.cloud.model;

/**
 * 
 * @author Andre van Hoorn
 *
 */
public class AbstractNamedEntity implements ICloudNodeType {
	
	/**
	 * The name of the {@link AbstractNamedEntity}.
	 */
	private final String name;
	
	/**
	 * Must not be used for construction
	 */
	@SuppressWarnings("unused")
	private AbstractNamedEntity() {
		this.name = null;
	}

	/**
	 * Creates a new {@link AbstractNamedEntity} with the given name.
	 * 
	 * @param name the name of the node type.
	 */
	public AbstractNamedEntity(final String name) {
		this.name = name;
	}

	@Override
	public final String getName() {
		return this.name;
	}
}
