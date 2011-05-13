package org.trustsoft.slastic.plugins.cloud.service;

import java.util.Collection;

import org.trustsoft.slastic.plugins.cloud.model.AbstractCloudNodeType;
import org.trustsoft.slastic.plugins.cloud.model.IApplicationInstance;
import org.trustsoft.slastic.plugins.cloud.model.IApplicationInstanceConfiguration;
import org.trustsoft.slastic.plugins.cloud.model.ICloudNode;
import org.trustsoft.slastic.plugins.cloud.model.ICloudNodeType;
import org.trustsoft.slastic.plugins.cloud.model.ICloudedApplication;
import org.trustsoft.slastic.plugins.cloud.model.ICloudedApplicationConfiguration;

/**
 * Allows to manage load-balanced applications in the cloud.
 * 
 * @author Andre van Hoorn
 * 
 */
public interface IApplicationCloudingService {

	/**
	 * Returns the list of available {@link AbstractCloudNodeType}s.
	 * 
	 * @return
	 */
	public Collection<? extends ICloudNodeType> getNodeTypes();

	/**
	 * Returns a newly allocated {@link ICloudNode} with given name and
	 * {@link ICloudNodeType}.
	 * 
	 * This method is synchronous and returns after the {@link ICloudNode} is
	 * booted.
	 * 
	 * @param name
	 * @param type
	 * @return
	 * @throws ApplicationCloudingServiceException
	 */
	public ICloudNode allocateNode(String name, ICloudNodeType type)
			throws ApplicationCloudingServiceException;

	/**
	 * Deallocates the given {@link ICloudNode}.
	 * 
	 * This method returns immediately and does not wait until the
	 * {@link ICloudNode} is shut down.
	 * 
	 * @param node
	 * @throws ApplicationCloudingServiceException
	 */
	public void deallocateNode(ICloudNode node)
			throws ApplicationCloudingServiceException;

	/**
	 * Returns a newly created and registered {@link ICloudedApplication} with
	 * given name and configuration.
	 * 
	 * @param name
	 * @param configuration
	 * @return
	 * @throws ApplicationCloudingServiceException
	 */
	public ICloudedApplication createAndRegisterCloudedApplication(String name,
			ICloudedApplicationConfiguration configuration)
			throws ApplicationCloudingServiceException;

	/**
	 * Removes the {@link ICloudedApplication}.
	 * 
	 * Before removing an {@link ICloudedApplication}, all associated deployed
	 * {@link IApplicationInstance}s have to be removed using the method
	 * {@link #undeployApplicationInstance(IApplicationInstance)}.
	 * 
	 * @param application
	 * @throws ApplicationCloudingServiceException
	 */
	public void removeCloudedApplication(ICloudedApplication application)
			throws ApplicationCloudingServiceException;

	/**
	 * Deploys an {@link IApplicationInstance} for the given
	 * {@link ICloudedApplication} with the given
	 * {@link IApplicationInstanceConfiguration} to the given {@link ICloudNode}
	 * .
	 * 
	 * This method is synchronous and returns after the
	 * {@link IApplicationInstance} is available.
	 * 
	 * @param application
	 * @param configuration
	 * @param node
	 * @return
	 * @throws ApplicationCloudingServiceException
	 */
	public IApplicationInstance deployApplicationInstance(
			ICloudedApplication application,
			IApplicationInstanceConfiguration configuration, ICloudNode node)
			throws ApplicationCloudingServiceException;

	/**
	 * Undeploys the given {@link IApplicationInstance}.
	 * 
	 * This method returns immediately and does not wait until the
	 * {@link IApplicationInstance} is undeployed.
	 * 
	 * @param instance
	 * @throws ApplicationCloudingServiceException
	 */
	public void undeployApplicationInstance(IApplicationInstance instance)
			throws ApplicationCloudingServiceException;
}
