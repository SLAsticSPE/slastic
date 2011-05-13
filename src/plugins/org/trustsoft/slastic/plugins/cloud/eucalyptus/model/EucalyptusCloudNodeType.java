/**
 * 
 */
package org.trustsoft.slastic.plugins.cloud.eucalyptus.model;

import org.trustsoft.slastic.plugins.cloud.model.AbstractCloudNodeType;

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
