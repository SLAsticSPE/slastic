package kieker.tools.slastic.plugins.cloud.amazon.model;

import kieker.tools.slastic.plugins.cloud.model.AbstractApplicationInstance;
import kieker.tools.slastic.plugins.cloud.model.ICloudedApplication;

/**
 * An instance of an {@link ICloudedApplication} which can be deployed to
 * Eucalyptus.
 * 
 * @author Andre van Hoorn
 * 
 */
public class AmazonApplicationInstance extends AbstractApplicationInstance {

	/**
	 * @param name
	 * @param configuration
	 */
	public AmazonApplicationInstance(final String name,
			final AmazonCloudedApplication application,
			final AmazonApplicationInstanceConfiguration configuration,
			final AmazonCloudNode node) {
		super(name, application, configuration, node);
	}

	@Override
	public String toString() {
		final StringBuilder strB = new StringBuilder("[");
		strB.append("name: ").append(this.getName());
		strB.append("; application: ").append(this.getApplication());
		strB.append("; configuration: ").append(this.getConfiguration());
		strB.append("; node").append(this.getNode());
		return strB.append("]").toString();
	}
}
