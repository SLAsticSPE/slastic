/**
 * 
 */
package org.trustsoft.slastic.plugins.cloud.eucalyptus.service;

import kieker.tools.util.LoggingTimestampConverter;

import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstance;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstanceConfiguration;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNode;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNodeType;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudedApplication;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudedApplicationConfiguration;

/**
 * @author Andre van Hoorn
 * 
 */
public abstract class AbstractEucalyptusServiceEventLogger implements
		IEucalyptusServiceEventListener {

	private final String CSV_FIELD_DELIM = ";";

	private final ICurrentTimeProvider currentTimeProvider;

	public AbstractEucalyptusServiceEventLogger() {
		this(ICurrentTimeProvider.SYSTEM_CURRENT_TIME_PROVIDER);
	}

	public AbstractEucalyptusServiceEventLogger(
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
	 * de.cau.se.ffi.cloud.eucalyptus.service.IEucalyptusServiceEventListener
	 * #notifyAllocateNodeSuccess(java.lang.String,
	 * de.cau.se.ffi.cloud.eucalyptus.model.EucalyptusCloudNodeType,
	 * de.cau.se.ffi.cloud.eucalyptus.model.EucalyptusCloudNode)
	 */
	@Override
	public final void notifyAllocateNodeSuccess(final String name,
			final EucalyptusCloudNodeType type, final EucalyptusCloudNode node) {
		this.logEvent(new String[] {
				AbstractEucalyptusServiceEventLogger.ACTION_ALLOCATE_NODE,
				AbstractEucalyptusServiceEventLogger.STATUS_SUCCESS,
				"node: " + node });
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * de.cau.se.ffi.cloud.eucalyptus.service.IEucalyptusServiceEventListener
	// * #notifyAllocateNodeFailure(java.lang.String,
	// * de.cau.se.ffi.cloud.eucalyptus.model.EucalyptusCloudNodeType)
	// */
	// @Override
	// public void notifyAllocateNodeFailure(final String name,
	// final EucalyptusCloudNodeType type) {
	// this.logEvent(new String[] {
	// EucalyptusServiceEventLogger.ACTION_ALLOCATE_NODE,
	// EucalyptusServiceEventLogger.STATUS_FAILURE, "type: " + type,
	// "node: " + EucalyptusServiceEventLogger.PLACEHOLDER_NULL });
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cau.se.ffi.cloud.eucalyptus.service.IEucalyptusServiceEventListener
	 * #notifyDeallocateNodeSuccess
	 * (de.cau.se.ffi.cloud.eucalyptus.model.EucalyptusCloudNode)
	 */
	@Override
	public final void notifyDeallocateNodeSuccess(final EucalyptusCloudNode node) {
		this.logEvent(new String[] {
				AbstractEucalyptusServiceEventLogger.ACTION_DEALLOCATE_NODE,
				AbstractEucalyptusServiceEventLogger.STATUS_SUCCESS,
				"node: " + node });
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * de.cau.se.ffi.cloud.eucalyptus.service.IEucalyptusServiceEventListener
	// * #notifyDeallocateNodeFailure
	// * (de.cau.se.ffi.cloud.eucalyptus.model.EucalyptusCloudNode)
	// */
	// @Override
	// public void notifyDeallocateNodeFailure(final EucalyptusCloudNode node) {
	// this.logEvent(new String[] {
	// EucalyptusServiceEventLogger.ACTION_DEALLOCATE_NODE,
	// EucalyptusServiceEventLogger.STATUS_FAILURE, "node: " + node });
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cau.se.ffi.cloud.eucalyptus.service.IEucalyptusServiceEventListener
	 * #notifyCreateAndRegisterCloudedApplicationSuccess(java.lang.String,
	 * de.cau
	 * .se.ffi.cloud.eucalyptus.model.EucalyptusCloudedApplicationConfiguration,
	 * de.cau.se.ffi.cloud.eucalyptus.model.EucalyptusCloudedApplication)
	 */
	@Override
	public final void notifyCreateAndRegisterCloudedApplicationSuccess(
			final String name,
			final EucalyptusCloudedApplicationConfiguration configuration,
			final EucalyptusCloudedApplication application) {
		this.logEvent(new String[] {
				AbstractEucalyptusServiceEventLogger.ACTION_CREATEREGISTER_APP,
				AbstractEucalyptusServiceEventLogger.STATUS_SUCCESS,
				"application: " + application });
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * de.cau.se.ffi.cloud.eucalyptus.service.IEucalyptusServiceEventListener
	// * #notifyCreateAndRegisterCloudedApplicationFailure(java.lang.String,
	// * de.cau
	// *
	// .se.ffi.cloud.eucalyptus.model.EucalyptusCloudedApplicationConfiguration)
	// */
	// @Override
	// public void notifyCreateAndRegisterCloudedApplicationFailure(
	// final String name,
	// final EucalyptusCloudedApplicationConfiguration configuration) {
	// this.logEvent(new String[] {
	// EucalyptusServiceEventLogger.ACTION_CREATEREGISTER_APP,
	// EucalyptusServiceEventLogger.STATUS_FAILURE,
	// "configuration: " + configuration,
	// "application: " + EucalyptusServiceEventLogger.PLACEHOLDER_NULL });
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cau.se.ffi.cloud.eucalyptus.service.IEucalyptusServiceEventListener
	 * #notifyRemoveCloudedApplicationSuccess
	 * (de.cau.se.ffi.cloud.eucalyptus.model.EucalyptusCloudedApplication)
	 */
	@Override
	public final void notifyRemoveCloudedApplicationSuccess(
			final EucalyptusCloudedApplication application) {
		this.logEvent(new String[] {
				AbstractEucalyptusServiceEventLogger.ACTION_REMOVE_APP,
				AbstractEucalyptusServiceEventLogger.STATUS_SUCCESS,
				"application: " + application });
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * de.cau.se.ffi.cloud.eucalyptus.service.IEucalyptusServiceEventListener
	// * #notifyRemoveCloudedApplicationFailure
	// * (de.cau.se.ffi.cloud.eucalyptus.model.EucalyptusCloudedApplication)
	// */
	// @Override
	// public void notifyRemoveCloudedApplicationFailure(
	// final EucalyptusCloudedApplication application) {
	// this.logEvent(new String[] {
	// EucalyptusServiceEventLogger.ACTION_REMOVE_APP,
	// EucalyptusServiceEventLogger.STATUS_FAILURE,
	// "application: " + application });
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cau.se.ffi.cloud.eucalyptus.service.IEucalyptusServiceEventListener
	 * #notifyDeployApplicationInstanceSuccess
	 * (de.cau.se.ffi.cloud.eucalyptus.model.EucalyptusCloudedApplication,
	 * de.cau
	 * .se.ffi.cloud.eucalyptus.model.EucalyptusApplicationInstanceConfiguration
	 * , de.cau.se.ffi.cloud.eucalyptus.model.EucalyptusCloudNode,
	 * de.cau.se.ffi.cloud.eucalyptus.model.EucalyptusApplicationInstance)
	 */
	@Override
	public final void notifyDeployApplicationInstanceSuccess(
			final EucalyptusCloudedApplication application,
			final EucalyptusApplicationInstanceConfiguration configuration,
			final EucalyptusCloudNode node,
			final EucalyptusApplicationInstance instance) {
		this.logEvent(new String[] {
				AbstractEucalyptusServiceEventLogger.ACTION_DEPLOY_INST,
				AbstractEucalyptusServiceEventLogger.STATUS_SUCCESS,
				"instance: " + instance });
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * de.cau.se.ffi.cloud.eucalyptus.service.IEucalyptusServiceEventListener
	// * #notifyDeployApplicationInstanceFailure
	// * (de.cau.se.ffi.cloud.eucalyptus.model.EucalyptusCloudedApplication,
	// * de.cau
	// *
	// .se.ffi.cloud.eucalyptus.model.EucalyptusApplicationInstanceConfiguration
	// * , de.cau.se.ffi.cloud.eucalyptus.model.EucalyptusCloudNode)
	// */
	// @Override
	// public void notifyDeployApplicationInstanceFailure(
	// final EucalyptusCloudedApplication application,
	// final EucalyptusApplicationInstanceConfiguration configuration,
	// final EucalyptusCloudNode node) {
	// this.logEvent(new String[] {
	// EucalyptusServiceEventLogger.ACTION_DEPLOY_INST,
	// EucalyptusServiceEventLogger.STATUS_FAILURE,
	// "application: " + application,
	// "configuration: " + configuration, "node: " + node,
	// "instance: " + EucalyptusServiceEventLogger.PLACEHOLDER_NULL });
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cau.se.ffi.cloud.eucalyptus.service.IEucalyptusServiceEventListener
	 * #notifyUndeployApplicationInstanceSuccess
	 * (de.cau.se.ffi.cloud.eucalyptus.model.EucalyptusApplicationInstance)
	 */
	@Override
	public final void notifyUndeployApplicationInstanceSuccess(
			final EucalyptusApplicationInstance instance) {
		this.logEvent(new String[] {
				AbstractEucalyptusServiceEventLogger.ACTION_UNEPLOY_INST,
				AbstractEucalyptusServiceEventLogger.STATUS_SUCCESS,
				"instance: " + instance });
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * de.cau.se.ffi.cloud.eucalyptus.service.IEucalyptusServiceEventListener
	// * #notifyUndeployApplicationInstanceFailure
	// * (de.cau.se.ffi.cloud.eucalyptus.model.EucalyptusApplicationInstance)
	// */
	// @Override
	// public void notifyUndeployApplicationInstanceFailure(
	// final EucalyptusApplicationInstance instance) {
	// this.logEvent(new String[] {
	// EucalyptusServiceEventLogger.ACTION_UNEPLOY_INST,
	// EucalyptusServiceEventLogger.STATUS_FAILURE,
	// "instance: " + instance });
	// }
}
