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
