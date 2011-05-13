package org.trustsoft.slastic.plugins.cloud.model;

/**
 * 
 * @author Andre van Hoorn
 *
 */
public abstract class AbstractCloudedApplication extends AbstractNamedEntity implements ICloudedApplication {

	private final ICloudedApplicationConfiguration configuration;
	
	/**
	 * Must not be used for construction.
	 */
	private AbstractCloudedApplication (){
		super(null);
		this.configuration = null;
	}
	
	/**
	 * Creates a new {@link AbstractCloudedApplication} with the given name.
	 * 
	 * @param name
	 */
	public AbstractCloudedApplication(final String name, final ICloudedApplicationConfiguration configuration) {
		super(name);
		this.configuration = configuration;
	}

	@Override
	public ICloudedApplicationConfiguration getConfiguration() {
		return this.configuration;
	}
}
