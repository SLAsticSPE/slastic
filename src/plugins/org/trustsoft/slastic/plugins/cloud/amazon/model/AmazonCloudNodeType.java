/**
 * 
 */
package org.trustsoft.slastic.plugins.cloud.amazon.model;

import org.trustsoft.slastic.plugins.cloud.model.AbstractCloudNodeType;

/**
 * A node type from an Eucalyptus cloud.
 * 
 * @author Andre van Hoorn
 * 
 */
public class AmazonCloudNodeType extends AbstractCloudNodeType {

	private final String amiImageName;

	public AmazonCloudNodeType(final String name, final String amiImageName) {
		super(name);
		this.amiImageName = amiImageName;
	}

	public String getAmiImageName() {
		return this.amiImageName;
	}

	@Override
	public String toString() {
		final StringBuilder strB = new StringBuilder("[");
		strB.append("name: ").append(this.getName());
		strB.append("; ami: ").append(this.getAmiImageName());
		return strB.append("]").toString();
	}
}
