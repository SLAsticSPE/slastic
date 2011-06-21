package org.trustsoft.slastic.plugins.cloud.eucalyptus.service.logging;

import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstance;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstanceConfiguration;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNode;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNodeType;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudedApplication;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudedApplicationConfiguration;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public interface IEucalyptusServiceEventListener {
	/**
	 * 
	 * @param name
	 * @param type
	 * @param node
	 */
	public void notifyAllocateNodeSuccess(final String name,
			final EucalyptusCloudNodeType type, final EucalyptusCloudNode node);

//	/**
//	 * 
//	 * @param name
//	 * @param type
//	 */
//	public void notifyAllocateNodeFailure(final String name,
//			final EucalyptusCloudNodeType type);

	/**
	 * 
	 * @param node
	 */
	public void notifyDeallocateNodeSuccess(EucalyptusCloudNode node);

//	/**
//	 * 
//	 * @param node
//	 */
//	public void notifyDeallocateNodeFailure(EucalyptusCloudNode node);

	/**
	 * 
	 * @param name
	 * @param configuration
	 * @param application
	 */
	public void notifyCreateAndRegisterCloudedApplicationSuccess(String name,
			EucalyptusCloudedApplicationConfiguration configuration,
			EucalyptusCloudedApplication application);

//	/**
//	 * 
//	 * @param name
//	 * @param configuration
//	 */
//	public void notifyCreateAndRegisterCloudedApplicationFailure(String name,
//			EucalyptusCloudedApplicationConfiguration configuration);

	/**
	 * 
	 * @param application
	 */
	public void notifyRemoveCloudedApplicationSuccess(
			EucalyptusCloudedApplication application);

//	/**
//	 * 
//	 * @param application
//	 */
//	public void notifyRemoveCloudedApplicationFailure(
//			EucalyptusCloudedApplication application);

	/**
	 * 
	 * @param application
	 * @param configuration
	 * @param node
	 * @param instance
	 */
	public void notifyDeployApplicationInstanceSuccess(
			EucalyptusCloudedApplication application,
			EucalyptusApplicationInstanceConfiguration configuration,
			EucalyptusCloudNode node, EucalyptusApplicationInstance instance);

//	/**
//	 * 
//	 * @param application
//	 * @param configuration
//	 * @param node
//	 */
//	public void notifyDeployApplicationInstanceFailure(
//			EucalyptusCloudedApplication application,
//			EucalyptusApplicationInstanceConfiguration configuration,
//			EucalyptusCloudNode node);

	/**
	 * 
	 * @param instance
	 */
	public void notifyUndeployApplicationInstanceSuccess(
			EucalyptusApplicationInstance instance);

//	/**
//	 * 
//	 * @param instance
//	 */
//	public void notifyUndeployApplicationInstanceFailure(
//			EucalyptusApplicationInstance instance);
}
