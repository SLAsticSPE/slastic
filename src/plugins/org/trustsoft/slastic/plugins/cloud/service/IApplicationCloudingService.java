/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

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
	 * Returns the {@link ICloudNodeType} with the given name.
	 * 
	 * @param name
	 * @return the {@link ICloudNodeType}; null if no {@link ICloudNodeType}
	 *         with this name
	 */
	public ICloudNodeType lookupCloudNodeType(String name);

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
	 * Returns the list of allocated {@link ICloudNode}s.
	 * 
	 * @return
	 */
	public Collection<? extends ICloudNode> getCloudNodes();

	/**
	 * Returns the {@link ICloudNode} with the given name.
	 * 
	 * @param name
	 * @return the {@link ICloudNode}; null if no {@link ICloudNode} with this
	 *         name
	 */
	public ICloudNode lookupNode(String name);

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
	 * Returns the list of registered {@link ICloudedApplication}s.
	 * 
	 * @return
	 */
	public Collection<? extends ICloudedApplication> getCloudedApplications();

	/**
	 * Returns the list of registered {@link IApplicationInstance}s.
	 * 
	 * @return
	 */
	public Collection<? extends IApplicationInstance> getApplicationInstances();

	/**
	 * Returns the {@link ICloudedApplication} with the given name.
	 * 
	 * @param name
	 * @return the {@link ICloudedApplication}; null if no
	 *         {@link ICloudedApplication} with this name
	 */
	public ICloudedApplication lookupCloudedApplication(String name);

	/**
	 * Returns the {@link IApplicationInstance} for the given
	 * {@link ICloudedApplication} deployed on the given {@link ICloudNode}.
	 * 
	 * @param cloudedApplication
	 * @param cloudNode
	 * @return the {@link IApplicationInstance}; null if no such
	 *         {@link IApplicationInstance}
	 */
	public IApplicationInstance lookupApplicationInstance(ICloudedApplication cloudedApplication, ICloudNode cloudNode);

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
