package org.trustsoft.slastic.plugins.cloud.eucalyptus.service.logging;

import java.util.ArrayList;
import java.util.Collection;

import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstance;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstanceConfiguration;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNode;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNodeType;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudedApplication;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudedApplicationConfiguration;

/**
 * Receives notifications from {@link IEucalyptusServiceEventListener} and
 * passes these notification to the registered
 * {@link IEucalyptusServiceEventListener}s.
 * 
 * @author Andre van Hoorn
 * 
 */
public class EucalyptusServiceEventNotifier implements IEucalyptusServiceEventListener {

	private final Collection<IEucalyptusServiceEventListener> eventListeners =
			new ArrayList<IEucalyptusServiceEventListener>();

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
			l.notifyCreateAndRegisterCloudedApplicationSuccess(name,
					configuration, application);
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
			l.notifyDeployApplicationInstanceSuccess(application,
					configuration, node, instance);
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
