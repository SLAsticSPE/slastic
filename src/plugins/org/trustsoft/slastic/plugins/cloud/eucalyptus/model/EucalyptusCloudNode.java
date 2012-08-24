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
package org.trustsoft.slastic.plugins.cloud.eucalyptus.model;

import org.trustsoft.slastic.plugins.cloud.model.AbstractCloudNode;
import org.trustsoft.slastic.plugins.cloud.model.ICloudNodeType;

/**
 * A node allocated from an Eucalyptus cloud.
 * 
 * @author Andre van Hoorn
 * 
 */
public class EucalyptusCloudNode extends AbstractCloudNode {

	private final String instanceID;
	private final String ipAddress;
	private final String hostname;

	/**
	 * @param name
	 * @param type
	 */
	public EucalyptusCloudNode(final String name, final ICloudNodeType type, final String instanceID, final String ipAddress, final String hostname) {
		super(name, type);
		this.instanceID = instanceID;
		this.ipAddress = ipAddress;
		this.hostname = hostname;
	}

	public String getInstanceID() {
		return this.instanceID;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	/**
	 * @return the hostname
	 */
	public final String getHostname() {
		return this.hostname;
	}

	@Override
	public String toString() {
		final StringBuilder strB = new StringBuilder("[");
		strB.append("name: ").append(this.getName());
		strB.append("; type: ").append(this.getNodeType());
		strB.append("; instanceID: ").append(this.getInstanceID());
		strB.append("; ipAddress: ").append(this.getIpAddress());
		strB.append("; hostname: ").append(this.getHostname());
		return strB.append("]").toString();
	}
}
