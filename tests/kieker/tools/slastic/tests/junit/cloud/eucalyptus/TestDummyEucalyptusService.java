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

package kieker.tools.slastic.tests.junit.cloud.eucalyptus;

import java.util.Collection;
import java.util.Properties;

import junit.framework.Assert;
import junit.framework.TestCase;

import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstanceConfiguration;
import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNodeType;
import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudedApplicationConfiguration;
import kieker.tools.slastic.plugins.cloud.eucalyptus.service.EucalyptusApplicationCloudingService;
import kieker.tools.slastic.plugins.cloud.eucalyptus.service.configuration.ConfigurationProperty;
import kieker.tools.slastic.plugins.cloud.eucalyptus.service.configuration.EucalyptusApplicationCloudingServiceConfiguration;
import kieker.tools.slastic.plugins.cloud.eucalyptus.service.configuration.IEucalyptusApplicationCloudingServiceConfiguration;
import kieker.tools.slastic.plugins.cloud.eucalyptus.service.logging.EucalyptusServiceEventLogger;
import kieker.tools.slastic.plugins.cloud.model.IApplicationInstance;
import kieker.tools.slastic.plugins.cloud.model.ICloudNode;
import kieker.tools.slastic.plugins.cloud.model.ICloudedApplication;
import kieker.tools.slastic.plugins.cloud.service.ApplicationCloudingServiceException;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestDummyEucalyptusService extends TestCase {

	// private static final Log LOG = LogFactory.getLog(TestDummyEucalyptusService.class);

	/**
	 * Performs the actual test.
	 * 
	 * @throws ApplicationCloudingServiceException
	 * 
	 */
	public void testDummyService() throws ApplicationCloudingServiceException {
		final EucalyptusApplicationCloudingService svc = TestDummyEucalyptusService.createService();
		svc.addEventListener(new EucalyptusServiceEventLogger());

		/*
		 * We will now test all methods from the
		 * EucalyptusApplicationCloudingService
		 */
		/* 1: getNodeTypes */
		final Collection<EucalyptusCloudNodeType> types = svc.getNodeTypes();

		Assert.assertEquals("Unexpected number of node types", 2, types.size());
		/*
		 * Pick the first (and only) existing node type (in this case the dummy
		 * type)
		 */
		final EucalyptusCloudNodeType nodeType = types.iterator().next();

		/* 2: allocateNode */
		final ICloudNode allocatedNode = svc.allocateNode("NodeName", nodeType);
		Assert.assertNotNull("allocation is null", allocatedNode);

		/* 3: createAndRegisterCloudedApplication */

		final ICloudedApplication app = svc.createAndRegisterCloudedApplication("CloudedApplication", new EucalyptusCloudedApplicationConfiguration());
		Assert.assertNotNull("application is null", app);

		/* 4: deployApplicationInstance */
		final IApplicationInstance appInstance = svc.deployApplicationInstance(app, new EucalyptusApplicationInstanceConfiguration(), allocatedNode);
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
		final Properties props = TestDummyEucalyptusService.genProperties();

		final IEucalyptusApplicationCloudingServiceConfiguration config = EucalyptusApplicationCloudingServiceConfiguration.createConfiguration(props);

		final EucalyptusApplicationCloudingService svc = EucalyptusApplicationCloudingService.createService(config);

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
		props.put(ConfigurationProperty.DUMMY_MODE_ENABLED.getPropertyName(), "true");
		props.put(ConfigurationProperty.LOAD_BALANCER_ENABLED.getPropertyName(), "true");
		props.put(ConfigurationProperty.LOAD_BALANCER_SERVLET_URL.getPropertyName(), "http://localhost:8080/LoadBalancerServlet");
		props.put(ConfigurationProperty.EUCATOOLS_PATH.getPropertyName(), "/usr/local/bin/");
		props.put(ConfigurationProperty.EUCA_EMIS.getPropertyName(), "app-server:emi-ABCDEFGH;db-server:emi-IJKLMNOP");

		props.put(ConfigurationProperty.INITIAL_NODES.getPropertyName(), ConfigurationProperty.INITIAL_NODES.getDefaultValue());
		props.put(ConfigurationProperty.INITIAL_APPLICATIONS.getPropertyName(), ConfigurationProperty.INITIAL_APPLICATIONS.getDefaultValue());
		props.put(ConfigurationProperty.INITIAL_APPLICATION_INSTANCES.getPropertyName(), ConfigurationProperty.INITIAL_APPLICATION_INSTANCES.getDefaultValue());

		props.put(ConfigurationProperty.EUCA_KEY_NAME.getPropertyName(), "myEucaKey");
		props.put(ConfigurationProperty.EUCA_GROUP.getPropertyName(), "default");
		props.put(ConfigurationProperty.SSH_PRIV_KEY.getPropertyName(), "/home/user/.ssh/myKey.priv");
		props.put(ConfigurationProperty.SSH_USER_NAME.getPropertyName(), "sshUser");
		props.put(ConfigurationProperty.TOMCAT_HOME.getPropertyName(), "/opt/tomcat/");
		return props;
	}
}
