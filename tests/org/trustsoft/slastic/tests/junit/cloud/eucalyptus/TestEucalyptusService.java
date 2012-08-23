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

package org.trustsoft.slastic.tests.junit.cloud.eucalyptus;

import java.util.Collection;
import java.util.Properties;

import junit.framework.Assert;
import junit.framework.TestCase;

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
 * 
 * @author Florian Fittkau
 * 
 */
public class TestEucalyptusService extends TestCase {

	private static final Log log = LogFactory
			.getLog(TestEucalyptusService.class);

	/**
	 * Performs the actual test.
	 * 
	 * @throws ApplicationCloudingServiceException
	 * 
	 */
	public void testDummyService() throws ApplicationCloudingServiceException {
		final EucalyptusApplicationCloudingService svc = TestEucalyptusService
				.createService();

		/*
		 * We will now test all methods from the
		 * EucalyptusApplicationCloudingService
		 */
		/* 1: getNodeTypes */
		final Collection<EucalyptusCloudNodeType> types = svc.getNodeTypes();

		Assert.assertEquals("Unexpected number of node types", 1, types.size());
		/*
		 * Pick the first existing node type
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
		final Properties props = TestEucalyptusService.genProperties();

		final IEucalyptusApplicationCloudingServiceConfiguration config = EucalyptusApplicationCloudingServiceConfiguration
				.createConfiguration(props);

		final EucalyptusApplicationCloudingService svc = EucalyptusApplicationCloudingService
				.createService(config);

		return svc;
	}

	/**
	 * Generate dummy properties.
	 * 
	 * @return
	 */
	private static Properties genProperties() {
		final Properties props = new Properties();
		props.put(ConfigurationProperty.DEBUG_ENABLED.getPropertyName(), "true");
		props.put(ConfigurationProperty.DUMMY_MODE_ENABLED.getPropertyName(),
				"true");
		props.put(ConfigurationProperty.LOAD_BALANCER_ENABLED
				.getPropertyName(),
				"true");
		props.put(ConfigurationProperty.LOAD_BALANCER_SERVLET_URL
				.getPropertyName(),
				"http://localhost:8080/de.cau.se.ffi.cloud.loadBalancerServlet/");
		props.put(ConfigurationProperty.EUCATOOLS_PATH.getPropertyName(),
				"/usr/bin/");
		props.put(ConfigurationProperty.EUCA_EMIS.getPropertyName(),
				"tomcat:emi-85D10F25");
		props.put(
				ConfigurationProperty.INITIAL_NODES.getPropertyName(),
				ConfigurationProperty.INITIAL_NODES.getDefaultValue());
		props.put(
				ConfigurationProperty.INITIAL_APPLICATIONS.getPropertyName(),
				ConfigurationProperty.INITIAL_APPLICATIONS.getDefaultValue());
		props.put(
				ConfigurationProperty.INITIAL_APPLICATION_INSTANCES.getPropertyName(),
				ConfigurationProperty.INITIAL_APPLICATION_INSTANCES.getDefaultValue());
		props.put(ConfigurationProperty.EUCA_KEY_NAME.getPropertyName(),
				"slastic");
		props.put(ConfigurationProperty.EUCA_GROUP.getPropertyName(), "default");
		props.put(ConfigurationProperty.SSH_PRIV_KEY.getPropertyName(),
				"/home/voorn/svn_work/kiel-lehre-ws1011-spe-ffi/software/CloudService/slastic.priv");
		props.put(ConfigurationProperty.SSH_USER_NAME.getPropertyName(),
				"root");
		props.put(ConfigurationProperty.TOMCAT_HOME.getPropertyName(),
				"/opt/tomcat/webapps/");
		return props;
	}
}
