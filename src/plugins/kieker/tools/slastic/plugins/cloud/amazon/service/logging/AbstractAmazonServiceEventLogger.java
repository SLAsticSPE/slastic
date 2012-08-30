/**
 * 
 */
package kieker.tools.slastic.plugins.cloud.amazon.service.logging;

import kieker.tools.slastic.plugins.cloud.amazon.model.*;
import kieker.tools.slastic.plugins.cloud.common.ICurrentTimeProvider;
import kieker.tools.util.LoggingTimestampConverter;


/**
 * @author Andre van Hoorn
 * 
 */
public abstract class AbstractAmazonServiceEventLogger implements
		IAmazonServiceEventListener {

	private final String CSV_FIELD_DELIM = ";";

	private final ICurrentTimeProvider currentTimeProvider;

	public AbstractAmazonServiceEventLogger() {
		this(ICurrentTimeProvider.SYSTEM_CURRENT_TIME_PROVIDER);
	}

	public AbstractAmazonServiceEventLogger(
			final ICurrentTimeProvider currentTimeProvider) {
		this.currentTimeProvider = currentTimeProvider;
	}

	private final void logEvent(final String[] columns) {
		final StringBuilder strB = new StringBuilder();

		final long currentTimestampMillis =
				this.currentTimeProvider.getCurrentTimeMillis();

		/* Add time */
		final String currentTimeUTCString =
				LoggingTimestampConverter
						.convertLoggingTimestampToUTCString(currentTimestampMillis
								* (1000 * 1000));

		strB.append(currentTimestampMillis).append(this.CSV_FIELD_DELIM);
		strB.append(currentTimeUTCString);

		/* Add provided columns */
		for (final String field : columns) {
			strB.append(this.CSV_FIELD_DELIM);
			strB.append(field);
		}

		this.logEvent(strB.toString());
	}

	protected abstract void logEvent(String eventMsg);

	private static final String ACTION_ALLOCATE_NODE = "allocateNode";
	private static final String ACTION_DEALLOCATE_NODE = "deallocateNode";
	private static final String ACTION_CREATEREGISTER_APP =
			"createAndRegisterCloudedApplication";
	private static final String ACTION_REMOVE_APP = "removeCloudedApplication";
	private static final String ACTION_DEPLOY_INST =
			"deployApplicationInstance";
	private static final String ACTION_UNEPLOY_INST =
			"undeployApplicationInstance";
	private static final String STATUS_SUCCESS = "SUCCESS";

	// private static final String STATUS_FAILURE = "FAILURE";
	// private static final String PLACEHOLDER_NULL = "---";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cau.se.ffi.cloud.Amazon.service.IAmazonServiceEventListener
	 * #notifyAllocateNodeSuccess(java.lang.String,
	 * de.cau.se.ffi.cloud.Amazon.model.AmazonCloudNodeType,
	 * de.cau.se.ffi.cloud.Amazon.model.AmazonCloudNode)
	 */
	@Override
	public final void notifyAllocateNodeSuccess(final String name,
			final AmazonCloudNodeType type, final AmazonCloudNode node) {
		this.logEvent(new String[] {
				AbstractAmazonServiceEventLogger.ACTION_ALLOCATE_NODE,
				AbstractAmazonServiceEventLogger.STATUS_SUCCESS,
				"node: " + node });
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * de.cau.se.ffi.cloud.Amazon.service.IAmazonServiceEventListener
	// * #notifyAllocateNodeFailure(java.lang.String,
	// * de.cau.se.ffi.cloud.Amazon.model.AmazonCloudNodeType)
	// */
	// @Override
	// public void notifyAllocateNodeFailure(final String name,
	// final AmazonCloudNodeType type) {
	// this.logEvent(new String[] {
	// AmazonServiceEventLogger.ACTION_ALLOCATE_NODE,
	// AmazonServiceEventLogger.STATUS_FAILURE, "type: " + type,
	// "node: " + AmazonServiceEventLogger.PLACEHOLDER_NULL });
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cau.se.ffi.cloud.Amazon.service.IAmazonServiceEventListener
	 * #notifyDeallocateNodeSuccess
	 * (de.cau.se.ffi.cloud.Amazon.model.AmazonCloudNode)
	 */
	@Override
	public final void notifyDeallocateNodeSuccess(final AmazonCloudNode node) {
		this.logEvent(new String[] {
				AbstractAmazonServiceEventLogger.ACTION_DEALLOCATE_NODE,
				AbstractAmazonServiceEventLogger.STATUS_SUCCESS,
				"node: " + node });
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * de.cau.se.ffi.cloud.Amazon.service.IAmazonServiceEventListener
	// * #notifyDeallocateNodeFailure
	// * (de.cau.se.ffi.cloud.Amazon.model.AmazonCloudNode)
	// */
	// @Override
	// public void notifyDeallocateNodeFailure(final AmazonCloudNode node) {
	// this.logEvent(new String[] {
	// AmazonServiceEventLogger.ACTION_DEALLOCATE_NODE,
	// AmazonServiceEventLogger.STATUS_FAILURE, "node: " + node });
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cau.se.ffi.cloud.Amazon.service.IAmazonServiceEventListener
	 * #notifyCreateAndRegisterCloudedApplicationSuccess(java.lang.String,
	 * de.cau
	 * .se.ffi.cloud.Amazon.model.AmazonCloudedApplicationConfiguration,
	 * de.cau.se.ffi.cloud.Amazon.model.AmazonCloudedApplication)
	 */
	@Override
	public final void notifyCreateAndRegisterCloudedApplicationSuccess(
			final String name,
			final AmazonCloudedApplicationConfiguration configuration,
			final AmazonCloudedApplication application) {
		this.logEvent(new String[] {
				AbstractAmazonServiceEventLogger.ACTION_CREATEREGISTER_APP,
				AbstractAmazonServiceEventLogger.STATUS_SUCCESS,
				"application: " + application });
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * de.cau.se.ffi.cloud.Amazon.service.IAmazonServiceEventListener
	// * #notifyCreateAndRegisterCloudedApplicationFailure(java.lang.String,
	// * de.cau
	// *
	// .se.ffi.cloud.Amazon.model.AmazonCloudedApplicationConfiguration)
	// */
	// @Override
	// public void notifyCreateAndRegisterCloudedApplicationFailure(
	// final String name,
	// final AmazonCloudedApplicationConfiguration configuration) {
	// this.logEvent(new String[] {
	// AmazonServiceEventLogger.ACTION_CREATEREGISTER_APP,
	// AmazonServiceEventLogger.STATUS_FAILURE,
	// "configuration: " + configuration,
	// "application: " + AmazonServiceEventLogger.PLACEHOLDER_NULL });
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cau.se.ffi.cloud.Amazon.service.IAmazonServiceEventListener
	 * #notifyRemoveCloudedApplicationSuccess
	 * (de.cau.se.ffi.cloud.Amazon.model.AmazonCloudedApplication)
	 */
	@Override
	public final void notifyRemoveCloudedApplicationSuccess(
			final AmazonCloudedApplication application) {
		this.logEvent(new String[] {
				AbstractAmazonServiceEventLogger.ACTION_REMOVE_APP,
				AbstractAmazonServiceEventLogger.STATUS_SUCCESS,
				"application: " + application });
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * de.cau.se.ffi.cloud.Amazon.service.IAmazonServiceEventListener
	// * #notifyRemoveCloudedApplicationFailure
	// * (de.cau.se.ffi.cloud.Amazon.model.AmazonCloudedApplication)
	// */
	// @Override
	// public void notifyRemoveCloudedApplicationFailure(
	// final AmazonCloudedApplication application) {
	// this.logEvent(new String[] {
	// AmazonServiceEventLogger.ACTION_REMOVE_APP,
	// AmazonServiceEventLogger.STATUS_FAILURE,
	// "application: " + application });
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cau.se.ffi.cloud.Amazon.service.IAmazonServiceEventListener
	 * #notifyDeployApplicationInstanceSuccess
	 * (de.cau.se.ffi.cloud.Amazon.model.AmazonCloudedApplication,
	 * de.cau
	 * .se.ffi.cloud.Amazon.model.AmazonApplicationInstanceConfiguration
	 * , de.cau.se.ffi.cloud.Amazon.model.AmazonCloudNode,
	 * de.cau.se.ffi.cloud.Amazon.model.AmazonApplicationInstance)
	 */
	@Override
	public final void notifyDeployApplicationInstanceSuccess(
			final AmazonCloudedApplication application,
			final AmazonApplicationInstanceConfiguration configuration,
			final AmazonCloudNode node,
			final AmazonApplicationInstance instance) {
		this.logEvent(new String[] {
				AbstractAmazonServiceEventLogger.ACTION_DEPLOY_INST,
				AbstractAmazonServiceEventLogger.STATUS_SUCCESS,
				"instance: " + instance });
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * de.cau.se.ffi.cloud.Amazon.service.IAmazonServiceEventListener
	// * #notifyDeployApplicationInstanceFailure
	// * (de.cau.se.ffi.cloud.Amazon.model.AmazonCloudedApplication,
	// * de.cau
	// *
	// .se.ffi.cloud.Amazon.model.AmazonApplicationInstanceConfiguration
	// * , de.cau.se.ffi.cloud.Amazon.model.AmazonCloudNode)
	// */
	// @Override
	// public void notifyDeployApplicationInstanceFailure(
	// final AmazonCloudedApplication application,
	// final AmazonApplicationInstanceConfiguration configuration,
	// final AmazonCloudNode node) {
	// this.logEvent(new String[] {
	// AmazonServiceEventLogger.ACTION_DEPLOY_INST,
	// AmazonServiceEventLogger.STATUS_FAILURE,
	// "application: " + application,
	// "configuration: " + configuration, "node: " + node,
	// "instance: " + AmazonServiceEventLogger.PLACEHOLDER_NULL });
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cau.se.ffi.cloud.Amazon.service.IAmazonServiceEventListener
	 * #notifyUndeployApplicationInstanceSuccess
	 * (de.cau.se.ffi.cloud.Amazon.model.AmazonApplicationInstance)
	 */
	@Override
	public final void notifyUndeployApplicationInstanceSuccess(
			final AmazonApplicationInstance instance) {
		this.logEvent(new String[] {
				AbstractAmazonServiceEventLogger.ACTION_UNEPLOY_INST,
				AbstractAmazonServiceEventLogger.STATUS_SUCCESS,
				"instance: " + instance });
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * de.cau.se.ffi.cloud.Amazon.service.IAmazonServiceEventListener
	// * #notifyUndeployApplicationInstanceFailure
	// * (de.cau.se.ffi.cloud.Amazon.model.AmazonApplicationInstance)
	// */
	// @Override
	// public void notifyUndeployApplicationInstanceFailure(
	// final AmazonApplicationInstance instance) {
	// this.logEvent(new String[] {
	// AmazonServiceEventLogger.ACTION_UNEPLOY_INST,
	// AmazonServiceEventLogger.STATUS_FAILURE,
	// "instance: " + instance });
	// }
}
