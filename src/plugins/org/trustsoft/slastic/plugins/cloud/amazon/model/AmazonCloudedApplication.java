/**
 * 
 */
package org.trustsoft.slastic.plugins.cloud.amazon.model;

import java.util.concurrent.atomic.AtomicInteger;

import org.trustsoft.slastic.plugins.cloud.model.AbstractCloudedApplication;

/**
 * An application which can be deployed to Eucalyptus.
 * 
 * @author Andre van Hoorn
 * 
 */
public class AmazonCloudedApplication extends AbstractCloudedApplication {

	private static final AtomicInteger nextInstanceID = new AtomicInteger(0);

	/**
	 * @param name
	 * @param configuration
	 */
	public AmazonCloudedApplication(final String name,
			final AmazonCloudedApplicationConfiguration configuration) {
		super(name, configuration);
	}

	/**
	 * Returns a unique ID which can be used to identify
	 * {@link AmazonApplicationInstance} associated with this
	 * {@link AmazonCloudedApplication}.
	 * 
	 * @return
	 */
	public int acquireInstanceId() {
		return AmazonCloudedApplication.nextInstanceID.getAndIncrement();
	}

	@Override
	public String toString() {
		final StringBuilder strB = new StringBuilder("[");
		strB.append("name: " + this.getName());
		strB.append("; configuration: ").append(this.getConfiguration());
		return strB.append("]").toString();
	}
}
