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
	public EucalyptusCloudNode(final String name, final ICloudNodeType type,
			final String instanceID, final String ipAddress,
			final String hostname) {
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