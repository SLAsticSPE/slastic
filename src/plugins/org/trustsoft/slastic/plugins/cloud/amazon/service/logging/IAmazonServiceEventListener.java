package org.trustsoft.slastic.plugins.cloud.amazon.service.logging;

import org.trustsoft.slastic.plugins.cloud.amazon.model.*;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public interface IAmazonServiceEventListener {
	/**
	 * 
	 * @param name
	 * @param type
	 * @param node
	 */
	public void notifyAllocateNodeSuccess(final String name,
			final AmazonCloudNodeType type, final AmazonCloudNode node);

//	/**
//	 * 
//	 * @param name
//	 * @param type
//	 */
//	public void notifyAllocateNodeFailure(final String name,
//			final AmazonCloudNodeType type);

	/**
	 * 
	 * @param node
	 */
	public void notifyDeallocateNodeSuccess(AmazonCloudNode node);

//	/**
//	 * 
//	 * @param node
//	 */
//	public void notifyDeallocateNodeFailure(AmazonCloudNode node);

	/**
	 * 
	 * @param name
	 * @param configuration
	 * @param application
	 */
	public void notifyCreateAndRegisterCloudedApplicationSuccess(String name,
			AmazonCloudedApplicationConfiguration configuration,
			AmazonCloudedApplication application);

//	/**
//	 * 
//	 * @param name
//	 * @param configuration
//	 */
//	public void notifyCreateAndRegisterCloudedApplicationFailure(String name,
//			AmazonCloudedApplicationConfiguration configuration);

	/**
	 * 
	 * @param application
	 */
	public void notifyRemoveCloudedApplicationSuccess(
			AmazonCloudedApplication application);

//	/**
//	 * 
//	 * @param application
//	 */
//	public void notifyRemoveCloudedApplicationFailure(
//			AmazonCloudedApplication application);

	/**
	 * 
	 * @param application
	 * @param configuration
	 * @param node
	 * @param instance
	 */
	public void notifyDeployApplicationInstanceSuccess(
			AmazonCloudedApplication application,
			AmazonApplicationInstanceConfiguration configuration,
			AmazonCloudNode node, AmazonApplicationInstance instance);

//	/**
//	 * 
//	 * @param application
//	 * @param configuration
//	 * @param node
//	 */
//	public void notifyDeployApplicationInstanceFailure(
//			AmazonCloudedApplication application,
//			AmazonApplicationInstanceConfiguration configuration,
//			AmazonCloudNode node);

	/**
	 * 
	 * @param instance
	 */
	public void notifyUndeployApplicationInstanceSuccess(
			AmazonApplicationInstance instance);

//	/**
//	 * 
//	 * @param instance
//	 */
//	public void notifyUndeployApplicationInstanceFailure(
//			AmazonApplicationInstance instance);
}
