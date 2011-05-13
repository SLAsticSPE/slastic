/**
 * 
 */
package org.trustsoft.slastic.plugins.cloud.eucalyptus.model;

import java.util.concurrent.atomic.AtomicInteger;

import org.trustsoft.slastic.plugins.cloud.model.AbstractCloudedApplication;

/**
 * An application which can be deployed to Eucalyptus.
 * 
 * @author Andre van Hoorn
 * 
 */
public class EucalyptusCloudedApplication extends AbstractCloudedApplication {

	private static final AtomicInteger nextInstanceID = new AtomicInteger(0);

	/**
	 * @param name
	 * @param configuration
	 */
	public EucalyptusCloudedApplication(final String name,
			final EucalyptusCloudedApplicationConfiguration configuration) {
		super(name, configuration);
	}

	/**
	 * Returns a unique ID which can be used to identify
	 * {@link EucalyptusApplicationInstance} associated with this
	 * {@link EucalyptusCloudedApplication}.
	 * 
	 * @return
	 */
	public int acquireInstanceId() {
		return EucalyptusCloudedApplication.nextInstanceID.getAndIncrement();
	}

	@Override
	public String toString() {
		final StringBuilder strB = new StringBuilder("[");
		strB.append("name: " + this.getName());
		strB.append("; configuration: ").append(this.getConfiguration());
		return strB.append("]").toString();
	}
}
