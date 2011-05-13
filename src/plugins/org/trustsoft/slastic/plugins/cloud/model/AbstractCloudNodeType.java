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
