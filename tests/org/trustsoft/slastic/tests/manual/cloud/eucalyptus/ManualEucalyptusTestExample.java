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

package org.trustsoft.slastic.tests.manual.cloud.eucalyptus;

import java.util.Collection;
import java.util.Properties;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstanceConfiguration;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNodeType;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudedApplicationConfiguration;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.EucalyptusApplicationCloudingService;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.configuration.ConfigurationProperty;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.configuration.EucalyptusApplicationCloudingServiceConfiguration;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.configuration.IEucalyptusApplicationCloudingServiceConfiguration;
import org.trustsoft.slastic.plugins.cloud.model.IApplicationInstance;
import org.trustsoft.slastic.plugins.cloud.model.ICloudNode;
import org.trustsoft.slastic.plugins.cloud.model.ICloudedApplication;
import org.trustsoft.slastic.plugins.cloud.service.ApplicationCloudingServiceException;

/**
 * TODO: Florian: You may create tests for your Eucalyptus API based on this
 * examples class.
 * 
 * @author Andre van Hoorn
 * 
 */
public class ManualEucalyptusTestExample {

	private static final Log log = LogFactory
			.getLog(ManualEucalyptusTestExample.class);

	/**
	 * Executes the {@link ManualEucalyptusTestExample}.
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		final boolean success = false;

		final ManualEucalyptusTestExample manualEucalyptusTestExample = new ManualEucalyptusTestExample();
		try {
			manualEucalyptusTestExample.testDummyService();
		} catch (final ApplicationCloudingServiceException e) {
			ManualEucalyptusTestExample.log.error("Test threw an Exception", e);
		}

		/* Set exit code appropriately */
		if (!success) {
			System.exit(1);
		}
	}

	/**
	 * Performs the actual test.
	 * 
	 * @throws ApplicationCloudingServiceException
	 * 
	 */
	public void testDummyService() throws ApplicationCloudingServiceException {
		final EucalyptusApplicationCloudingService svc = ManualEucalyptusTestExample
				.createService();

		// TODO test eucalyptus specific parameters like EMI's

		/*
		 * We will now test all methods from the
		 * EucalyptusApplicationCloudingService
		 */
		/* 1: getNodeTypes */
		final Collection<EucalyptusCloudNodeType> types = svc.getNodeTypes();

		Assert.assertEquals("Unexpected number of node types", 1, types.size());
		/*
		 * Pick the first (and only) existing node type
		 */
		final EucalyptusCloudNodeType nodeType = types.iterator().next();

		/* 2: allocateNode */
		final ICloudNode allocatedNode = svc.allocateNode("NodeName", nodeType);
		Assert.assertNotNull("allocation is null", allocatedNode);

		/* 3: createAndRegisterCloudedApplication */

		final ICloudedApplication app = svc
				.createAndRegisterCloudedApplication("CloudedApplication",
						new EucalyptusCloudedApplicationConfiguration());
		Assert.assertNotNull("application is null", app);

		/* 4: deployApplicationInstance */
		final IApplicationInstance appInstance = svc.deployApplicationInstance(
				app, new EucalyptusApplicationInstanceConfiguration(),
				allocatedNode);
		Assert.assertNotNull("appInstance is null", appInstance);

		/* 5: undeployApplicationInstance */
		svc.undeployApplicationInstance(appInstance);

		/* 6: removeCloudedApplication */
		svc.removeCloudedApplication(app);

		/* 7: deallocateNode */
		svc.deallocateNode(allocatedNode);
	}

	/**
	 * Creates an {@link AbstractEucalyptusApplicationCloudingService} based on
	 * the {@link IEucalyptusApplicationCloudingServiceConfiguration} as
	 * returned by {@link #genProperties()}.
	 * 
	 * @return
	 */
	private static EucalyptusApplicationCloudingService createService() {
		final Properties props = ManualEucalyptusTestExample.genProperties();

		final IEucalyptusApplicationCloudingServiceConfiguration config = EucalyptusApplicationCloudingServiceConfiguration
				.createConfiguration(props);

		final EucalyptusApplicationCloudingService svc = EucalyptusApplicationCloudingService
				.createService(config);

		return svc;
	}

	/**
	 * Generate {@link Properties} constituting the
	 * {@link EucalyptusCloudedApplicationConfiguration}. In real applications,
	 * these {@link Properties} are read from a configuration file.
	 * 
	 * @return
	 */
	private static Properties genProperties() {
		final Properties props = new Properties();
		props.put(ConfigurationProperty.DEBUG_ENABLED.getPropertyName(), "true");
		props.put(ConfigurationProperty.DUMMY_MODE_ENABLED.getPropertyName(),
				"false");
		return props;
	}
}
