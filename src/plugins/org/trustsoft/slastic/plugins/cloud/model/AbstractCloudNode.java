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
public abstract class AbstractCloudNode extends AbstractNamedEntity implements ICloudNode {


	/**
	 * The type of this {@link AbstractCloudNode}.
	 */
	private final ICloudNodeType type;

	/**
	 * Must not be used for construction.
	 */
	private AbstractCloudNode() {
		super(null);
		this.type = null;
	}

	/**
	 * Creates a new {@link AbstractCloudNode} with the given name.
	 * 
	 * @param name the name of the node type.
	 */
	public AbstractCloudNode(final String name, final ICloudNodeType type) {
		super(name);
		this.type = type;
	}

	@Override
	public ICloudNodeType getNodeType() {
		return this.type;
	}
}
