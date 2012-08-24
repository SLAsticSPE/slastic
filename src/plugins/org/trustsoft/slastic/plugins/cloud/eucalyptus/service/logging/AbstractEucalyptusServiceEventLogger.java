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

/**
 * 
 */
package org.trustsoft.slastic.plugins.cloud.eucalyptus.service.logging;

import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstance;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstanceConfiguration;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNode;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNodeType;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudedApplication;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudedApplicationConfiguration;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.ICurrentTimeProvider;

import kieker.tools.util.LoggingTimestampConverter;

/**
 * @author Andre van Hoorn
 * 
 */
public abstract class AbstractEucalyptusServiceEventLogger implements IEucalyptusServiceEventListener {

	private static final String CSV_FIELD_DELIM = ";";

	private final ICurrentTimeProvider currentTimeProvider;

	public AbstractEucalyptusServiceEventLogger() {
		this(ICurrentTimeProvider.SYSTEM_CURRENT_TIME_PROVIDER);
	}

	public AbstractEucalyptusServiceEventLogger(final ICurrentTimeProvider currentTimeProvider) {
		this.currentTimeProvider = currentTimeProvider;
	}

	private final void logEvent(final String[] columns) {
		final StringBuilder strB = new StringBuilder();

		final long currentTimestampMillis = this.currentTimeProvider.getCurrentTimeMillis();

		/* Add time */
		final String currentTimeUTCString = LoggingTimestampConverter.convertLoggingTimestampToUTCString(currentTimestampMillis * (1000 * 1000));

		strB.append(currentTimestampMillis).append(CSV_FIELD_DELIM);
		strB.append(currentTimeUTCString);

		/* Add provided columns */
		for (final String field : columns) {
			strB.append(CSV_FIELD_DELIM);
			strB.append(field);
		}

		this.logEvent(strB.toString());
	}

	protected abstract void logEvent(String eventMsg);

	private static final String ACTION_ALLOCATE_NODE = "allocateNode";
	private static final String ACTION_DEALLOCATE_NODE = "deallocateNode";
	private static final String ACTION_CREATEREGISTER_APP = "createAndRegisterCloudedApplication";
	private static final String ACTION_REMOVE_APP = "removeCloudedApplication";
	private static final String ACTION_DEPLOY_INST = "deployApplicationInstance";
	private static final String ACTION_UNEPLOY_INST = "undeployApplicationInstance";
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
		this.logEvent(new String[] { ACTION_ALLOCATE_NODE, STATUS_SUCCESS, "node: " + node });
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
		this.logEvent(new String[] { ACTION_DEALLOCATE_NODE, STATUS_SUCCESS, "node: " + node });
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
		this.logEvent(new String[] { ACTION_CREATEREGISTER_APP, STATUS_SUCCESS, "application: " + application });
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
		this.logEvent(new String[] { ACTION_REMOVE_APP, STATUS_SUCCESS, "application: " + application });
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
		this.logEvent(new String[] { ACTION_DEPLOY_INST, STATUS_SUCCESS, "instance: " + instance });
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
		this.logEvent(new String[] { ACTION_UNEPLOY_INST, STATUS_SUCCESS, "instance: " + instance });
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
