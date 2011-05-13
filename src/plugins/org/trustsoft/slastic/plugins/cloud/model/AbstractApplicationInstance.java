package org.trustsoft.slastic.plugins.cloud.model;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public abstract class AbstractApplicationInstance extends AbstractNamedEntity
		implements IApplicationInstance {

	private final ICloudedApplication application;
	private final IApplicationInstanceConfiguration configuration;
	private final ICloudNode node;

	/**
	 * Must not be used for construction.
	 */
	@SuppressWarnings("unused")
	private AbstractApplicationInstance() {
		super(null);
		this.application = null;
		this.configuration = null;
		this.node = null;
	}

	public AbstractApplicationInstance(final String name,
			final ICloudedApplication application,
			final IApplicationInstanceConfiguration configuration,
			final ICloudNode node) {
		super(name);
		this.application = application;
		this.configuration = configuration;
		this.node = node;
	}

	@Override
	public IApplicationInstanceConfiguration getConfiguration() {
		return this.configuration;
	}

	@Override
	public ICloudedApplication getApplication() {
		return this.application;
	}

	@Override
	public ICloudNode getNode() {
		return this.node;
	}
}
