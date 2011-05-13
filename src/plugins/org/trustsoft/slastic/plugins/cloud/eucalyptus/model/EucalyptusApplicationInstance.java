package org.trustsoft.slastic.plugins.cloud.eucalyptus.model;

import org.trustsoft.slastic.plugins.cloud.model.AbstractApplicationInstance;
import org.trustsoft.slastic.plugins.cloud.model.ICloudedApplication;

/**
 * An instance of an {@link ICloudedApplication} which can be deployed to
 * Eucalyptus.
 * 
 * @author Andre van Hoorn
 * 
 */
public class EucalyptusApplicationInstance extends AbstractApplicationInstance {

	/**
	 * @param name
	 * @param configuration
	 */
	public EucalyptusApplicationInstance(final String name,
			final EucalyptusCloudedApplication application,
			final EucalyptusApplicationInstanceConfiguration configuration,
			final EucalyptusCloudNode node) {
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
