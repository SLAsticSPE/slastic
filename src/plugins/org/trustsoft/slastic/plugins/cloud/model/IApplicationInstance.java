package org.trustsoft.slastic.plugins.cloud.model;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public interface IApplicationInstance extends INamedEntity {
	/**
	 * 
	 * @return
	 */
	public IApplicationInstanceConfiguration getConfiguration();

	/**
	 * 
	 * @return
	 */
	public ICloudedApplication getApplication();

	/**
	 * 
	 * @return
	 */
	public ICloudNode getNode();
}
