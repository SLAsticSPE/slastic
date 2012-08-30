package kieker.tools.slastic.plugins.cloud.amazon.service.logging;

import java.util.ArrayList;
import java.util.Collection;

import kieker.tools.slastic.plugins.cloud.amazon.model.*;

/**
 * Receives notifications from {@link IAmazonServiceEventListener} and
 * passes these notification to the registered
 * {@link IAmazonServiceEventListener}s.
 * 
 * @author Andre van Hoorn
 * 
 */
public class AmazonServiceEventNotifier implements IAmazonServiceEventListener {

	private final Collection<IAmazonServiceEventListener> eventListeners =
			new ArrayList<IAmazonServiceEventListener>();

	/**
	 * 
	 * @param l
	 */
	public void addEventListener(final IAmazonServiceEventListener l) {
		this.eventListeners.add(l);
	}

	/**
	 * 
	 * @param l
	 * @return
	 */
	public boolean removeEventListener(final IAmazonServiceEventListener l) {
		return this.eventListeners.remove(l);
	}

	@Override
	public void notifyAllocateNodeSuccess(final String name,
			final AmazonCloudNodeType type, final AmazonCloudNode node) {
		for (final IAmazonServiceEventListener l : this.eventListeners) {
			l.notifyAllocateNodeSuccess(name, type, node);
		}
	}

	@Override
	public void notifyDeallocateNodeSuccess(final AmazonCloudNode node) {
		for (final IAmazonServiceEventListener l : this.eventListeners) {
			l.notifyDeallocateNodeSuccess(node);
		}
	}

	@Override
	public void notifyCreateAndRegisterCloudedApplicationSuccess(
			final String name,
			final AmazonCloudedApplicationConfiguration configuration,
			final AmazonCloudedApplication application) {
		for (final IAmazonServiceEventListener l : this.eventListeners) {
			l.notifyCreateAndRegisterCloudedApplicationSuccess(name,
					configuration, application);
		}
	}

	@Override
	public void notifyRemoveCloudedApplicationSuccess(
			final AmazonCloudedApplication application) {
		for (final IAmazonServiceEventListener l : this.eventListeners) {
			l.notifyRemoveCloudedApplicationSuccess(application);
		}
	}

	@Override
	public void notifyDeployApplicationInstanceSuccess(
			final AmazonCloudedApplication application,
			final AmazonApplicationInstanceConfiguration configuration,
			final AmazonCloudNode node,
			final AmazonApplicationInstance instance) {
		for (final IAmazonServiceEventListener l : this.eventListeners) {
			l.notifyDeployApplicationInstanceSuccess(application,
					configuration, node, instance);
		}
	}

	@Override
	public void notifyUndeployApplicationInstanceSuccess(
			final AmazonApplicationInstance instance) {
		for (final IAmazonServiceEventListener l : this.eventListeners) {
			l.notifyUndeployApplicationInstanceSuccess(instance);
		}
	}
}
