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

import java.util.ArrayList;
import java.util.Collection;

import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstance;
import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstanceConfiguration;
import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNode;
import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNodeType;
import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudedApplication;
import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudedApplicationConfiguration;

/**
 * Receives notifications from {@link IEucalyptusServiceEventListener} and
 * passes these notification to the registered {@link IEucalyptusServiceEventListener}s.
 * 
 * @author Andre van Hoorn
 * 
 */
public class EucalyptusServiceEventNotifier implements IEucalyptusServiceEventListener {

	private final Collection<IEucalyptusServiceEventListener> eventListeners = new ArrayList<IEucalyptusServiceEventListener>();

	/**
	 * 
	 * @param l
	 */
	public void addEventListener(final IEucalyptusServiceEventListener l) {
		this.eventListeners.add(l);
	}

	/**
	 * 
	 * @param l
	 * @return
	 */
	public boolean removeEventListener(final IEucalyptusServiceEventListener l) {
		return this.eventListeners.remove(l);
	}

	@Override
	public void notifyAllocateNodeSuccess(final String name,
			final EucalyptusCloudNodeType type, final EucalyptusCloudNode node) {
		for (final IEucalyptusServiceEventListener l : this.eventListeners) {
			l.notifyAllocateNodeSuccess(name, type, node);
		}
	}

	@Override
	public void notifyDeallocateNodeSuccess(final EucalyptusCloudNode node) {
		for (final IEucalyptusServiceEventListener l : this.eventListeners) {
			l.notifyDeallocateNodeSuccess(node);
		}
	}

	@Override
	public void notifyCreateAndRegisterCloudedApplicationSuccess(
			final String name,
			final EucalyptusCloudedApplicationConfiguration configuration,
			final EucalyptusCloudedApplication application) {
		for (final IEucalyptusServiceEventListener l : this.eventListeners) {
			l.notifyCreateAndRegisterCloudedApplicationSuccess(name, configuration, application);
		}
	}

	@Override
	public void notifyRemoveCloudedApplicationSuccess(
			final EucalyptusCloudedApplication application) {
		for (final IEucalyptusServiceEventListener l : this.eventListeners) {
			l.notifyRemoveCloudedApplicationSuccess(application);
		}
	}

	@Override
	public void notifyDeployApplicationInstanceSuccess(
			final EucalyptusCloudedApplication application,
			final EucalyptusApplicationInstanceConfiguration configuration,
			final EucalyptusCloudNode node,
			final EucalyptusApplicationInstance instance) {
		for (final IEucalyptusServiceEventListener l : this.eventListeners) {
			l.notifyDeployApplicationInstanceSuccess(application, configuration, node, instance);
		}
	}

	@Override
	public void notifyUndeployApplicationInstanceSuccess(
			final EucalyptusApplicationInstance instance) {
		for (final IEucalyptusServiceEventListener l : this.eventListeners) {
			l.notifyUndeployApplicationInstanceSuccess(instance);
		}
	}
}
