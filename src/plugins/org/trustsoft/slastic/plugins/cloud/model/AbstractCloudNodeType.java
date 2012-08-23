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
public abstract class AbstractCloudNodeType extends AbstractNamedEntity implements ICloudNodeType {

	/**
	 * Must not be used for construction
	 */
	private AbstractCloudNodeType() {
		super(null);
	}

	/**
	 * Creates a new {@link AbstractCloudNodeType} with the given name.
	 * 
	 * @param name the name of the node type.
	 */
	public AbstractCloudNodeType(final String name) {
		super(name);
	}
}
