package org.trustsoft.slastic.plugins.cloud.model;

/**
 * 
 * @author Andre van Hoorn
 *
 */
public interface ICloudNode extends INamedEntity  {
	/**
	 * Returns the type of the {@link ICloudNode}.
	 * 
	 * @return
	 */
	public ICloudNodeType getNodeType ();
}
