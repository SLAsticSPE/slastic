package org.trustsoft.slastic.plugins.cloud.model;

/**
 * 
 * @author Andre van Hoorn
 *
 */
public interface ICloudedApplication extends INamedEntity {
	
	/**
	 * 
	 * @return
	 */
	public ICloudedApplicationConfiguration getConfiguration (); 
}
