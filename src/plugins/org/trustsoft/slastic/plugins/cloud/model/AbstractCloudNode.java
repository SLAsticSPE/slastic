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
