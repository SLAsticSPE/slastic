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

package kieker.tools.slastic.plugins.cloud.eucalyptus.service.logging;

import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstance;
import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstanceConfiguration;
import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNode;
import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNodeType;
import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudedApplication;
import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudedApplicationConfiguration;

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
	public void notifyAllocateNodeSuccess(final String name, final EucalyptusCloudNodeType type, final EucalyptusCloudNode node);

	// /**
	// *
	// * @param name
	// * @param type
	// */
	// public void notifyAllocateNodeFailure(final String name,
	// final EucalyptusCloudNodeType type);

	/**
	 * 
	 * @param node
	 */
	public void notifyDeallocateNodeSuccess(EucalyptusCloudNode node);

	// /**
	// *
	// * @param node
	// */
	// public void notifyDeallocateNodeFailure(EucalyptusCloudNode node);

	/**
	 * 
	 * @param name
	 * @param configuration
	 * @param application
	 */
	public void notifyCreateAndRegisterCloudedApplicationSuccess(String name,
			EucalyptusCloudedApplicationConfiguration configuration,
			EucalyptusCloudedApplication application);

	// /**
	// *
	// * @param name
	// * @param configuration
	// */
	// public void notifyCreateAndRegisterCloudedApplicationFailure(String name,
	// EucalyptusCloudedApplicationConfiguration configuration);

	/**
	 * 
	 * @param application
	 */
	public void notifyRemoveCloudedApplicationSuccess(EucalyptusCloudedApplication application);

	// /**
	// *
	// * @param application
	// */
	// public void notifyRemoveCloudedApplicationFailure(
	// EucalyptusCloudedApplication application);

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

	// /**
	// *
	// * @param application
	// * @param configuration
	// * @param node
	// */
	// public void notifyDeployApplicationInstanceFailure(
	// EucalyptusCloudedApplication application,
	// EucalyptusApplicationInstanceConfiguration configuration,
	// EucalyptusCloudNode node);

	/**
	 * 
	 * @param instance
	 */
	public void notifyUndeployApplicationInstanceSuccess(EucalyptusApplicationInstance instance);

	// /**
	// *
	// * @param instance
	// */
	// public void notifyUndeployApplicationInstanceFailure(
	// EucalyptusApplicationInstance instance);
}
