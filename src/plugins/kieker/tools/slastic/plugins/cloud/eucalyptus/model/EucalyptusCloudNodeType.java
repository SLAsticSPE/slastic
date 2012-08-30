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

/**
 * 
 */
package kieker.tools.slastic.plugins.cloud.eucalyptus.model;

import kieker.tools.slastic.plugins.cloud.model.AbstractCloudNodeType;

/**
 * A node type from an Eucalyptus cloud.
 * 
 * @author Andre van Hoorn
 * 
 */
public class EucalyptusCloudNodeType extends AbstractCloudNodeType {

	private final String emiImageName;

	public EucalyptusCloudNodeType(final String name, final String emiImageName) {
		super(name);
		this.emiImageName = emiImageName;
	}

	public String getEmiImageName() {
		return this.emiImageName;
	}

	@Override
	public String toString() {
		final StringBuilder strB = new StringBuilder("[");
		strB.append("name: ").append(this.getName());
		strB.append("; emi: ").append(this.getEmiImageName());
		return strB.append("]").toString();
	}
}
