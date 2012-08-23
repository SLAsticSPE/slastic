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

package org.trustsoft.slastic.tests.junit.cloud.eucalyptus.configuration;

import java.util.Collection;
import java.util.Properties;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNodeType;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.EucalyptusApplicationCloudingService;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.configuration.ConfigurationProperty;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.configuration.EucalyptusApplicationCloudingServiceConfiguration;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.configuration.IEucalyptusApplicationCloudingServiceConfiguration;
import org.trustsoft.slastic.plugins.cloud.model.IApplicationInstance;
import org.trustsoft.slastic.plugins.cloud.model.ICloudNode;
import org.trustsoft.slastic.plugins.cloud.model.ICloudedApplication;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestEucalyptusConfigurationInitialEntities extends TestCase {

	private static final String DB_EMI = "emi-IJKLMNOP";
	private static final String APP_EMI = "emi-ABCDEFGH";

	private static final String[] DBSRV0_DATA = { "dbsrv0", "192.168.44.12",
			"i-34E606AA", "db-server" };
	private static final String[] APPSRV0_DATA = { "appsrv0", "192.168.44.13",
			"i-45E644DD", "app-server" };
	private static final String[] INITIAL_APPS =
	{ "a.App0", "b.App1", "c.App2" };
	private static final String[] INITIAL_APP_INSTANCE_0 =
	{ TestEucalyptusConfigurationInitialEntities.INITIAL_APPS[0],
			TestEucalyptusConfigurationInitialEntities.DBSRV0_DATA[0] };
	private static final String[] INITIAL_APP_INSTANCE_1 =
	{ TestEucalyptusConfigurationInitialEntities.INITIAL_APPS[1],
			TestEucalyptusConfigurationInitialEntities.DBSRV0_DATA[0] };
	private static final String[] INITIAL_APP_INSTANCE_2 =
	{ TestEucalyptusConfigurationInitialEntities.INITIAL_APPS[2],
			TestEucalyptusConfigurationInitialEntities.APPSRV0_DATA[0] };

	/**
	 * 
	 */
	public void testInitializedProperly() {
		final Properties props =
				TestEucalyptusConfigurationInitialEntities.genProperties();

		final IEucalyptusApplicationCloudingServiceConfiguration config =
				EucalyptusApplicationCloudingServiceConfiguration
						.createConfiguration(props);

		this.checkConfiguration(config);

		final EucalyptusApplicationCloudingService svc =
				EucalyptusApplicationCloudingService.createService(config);

		this.checkInitialNodeTypes(svc);
		this.checkInitialNodes(svc);
		this.checkInitialApplications(svc);
		this.checkInitialApplicationInstances(svc);
	}

	/**
	 * Performs some basic checks on the
	 * {@link IEucalyptusApplicationCloudingServiceConfiguration}.
	 * 
	 * @param config
	 */
	private void checkConfiguration(final IEucalyptusApplicationCloudingServiceConfiguration config) {
		{ /* Check initial nodes */
			final Collection<String[]> initialNodes = config.getInitialNodeInstances();
			Assert.assertNotNull("initialNodes must not be null", initialNodes);
			Assert.assertEquals("Unexpected number of initial nodes", 2, initialNodes.size());
			for (final String[] n : initialNodes) {
				Assert.assertEquals("Unexpected number of fields for node: " + n, 4, n.length);
			}
			// Note, we are not checking the actual content
		}

		{ /* Check initial applications */
			final Collection<String> initialApps = config.getInitialApplications();
			Assert.assertNotNull("initialApps must not be null", initialApps);
			Assert.assertEquals("Unexpected number of applications",
					TestEucalyptusConfigurationInitialEntities.INITIAL_APPS.length, initialApps.size());
			// Note, we are not checking the actual content
		}

		{ /* Check initial application instances */
			final Collection<String[]> initialAppInstances = config.getInitialApplicationInstances();
			Assert.assertNotNull("initialAppInstances must not be null", initialAppInstances);
			Assert.assertEquals("Unexpected number of initial applications", 3, initialAppInstances.size());
			// Note, we are not checking the actual content
		}
	}

	private void checkInitialNodes(final EucalyptusApplicationCloudingService svc) {
		Assert.assertEquals("Unexpected number of nodes", 2, svc.getCloudNodes().size());
	}

	private void checkInitialApplications(final EucalyptusApplicationCloudingService svc) {
		Assert.assertEquals("Unexpected number of applications",
				TestEucalyptusConfigurationInitialEntities.INITIAL_APPS.length, svc.getCloudedApplications().size());

		for (final String appName : TestEucalyptusConfigurationInitialEntities.INITIAL_APPS) {
			final ICloudedApplication app = svc.lookupCloudedApplication(appName);
			Assert.assertNotNull("Failed to lookup app with name '" + appName + "' (null)", app);
			Assert.assertEquals("Unexpected appName", appName, app.getName());
		}
	}

	private void checkInitialApplicationInstances(final EucalyptusApplicationCloudingService svc) {
		final Collection<? extends IApplicationInstance> appInstances = svc.getApplicationInstances();

		Assert.assertEquals("Unexpected number of initial appInstances", 3, appInstances.size());

		this.checkContainsAppInstance(appInstances,
				TestEucalyptusConfigurationInitialEntities.INITIAL_APP_INSTANCE_0[0],
				TestEucalyptusConfigurationInitialEntities.INITIAL_APP_INSTANCE_0[1]);
		this.checkContainsAppInstance(appInstances,
				TestEucalyptusConfigurationInitialEntities.INITIAL_APP_INSTANCE_1[0],
				TestEucalyptusConfigurationInitialEntities.INITIAL_APP_INSTANCE_1[1]);
		this.checkContainsAppInstance(appInstances,
				TestEucalyptusConfigurationInitialEntities.INITIAL_APP_INSTANCE_2[0],
				TestEucalyptusConfigurationInitialEntities.INITIAL_APP_INSTANCE_2[1]);
	}

	// There's probably a smarter/faster way to this, but it worx ;-)
	private void checkContainsAppInstance(final Collection<? extends IApplicationInstance> appInstances,
			final String expectedAppInstanceName, final String expectedNodeName) {
		for (final IApplicationInstance appInstance : appInstances) {
			if (!appInstance.getApplication().getName().equals(expectedAppInstanceName)) {
				continue;
			}

			final ICloudNode node = appInstance.getNode();
			if (!node.getName().equals(expectedNodeName)) {
				continue;
			}

			// if we reached this location, we're happy and return
			return;
		}

		Assert.fail("Failed to lookup instance for app '" + expectedAppInstanceName + "' on node '" + expectedNodeName
				+ "'");
	}

	/**
	 * Checks if the node types have been initialized properly.
	 * 
	 * @param svc
	 */
	private void checkInitialNodeTypes(
			final EucalyptusApplicationCloudingService svc) {
		Assert.assertEquals("Unexpected number of node types", 2, svc
				.getNodeTypes().size());

		final EucalyptusCloudNodeType lookedUpDbSrvType =
				(EucalyptusCloudNodeType) svc
						.lookupCloudNodeType(TestEucalyptusConfigurationInitialEntities.DBSRV0_DATA[3]);
		final EucalyptusCloudNodeType lookedUpAppSrvType =
				(EucalyptusCloudNodeType) svc
						.lookupCloudNodeType(TestEucalyptusConfigurationInitialEntities.APPSRV0_DATA[3]);

		this.checkNodeType(lookedUpDbSrvType,
				TestEucalyptusConfigurationInitialEntities.DBSRV0_DATA[3],
				TestEucalyptusConfigurationInitialEntities.DB_EMI);
		this.checkNodeType(lookedUpAppSrvType,
				TestEucalyptusConfigurationInitialEntities.APPSRV0_DATA[3],
				TestEucalyptusConfigurationInitialEntities.APP_EMI);
	}

	/**
	 * Checks whether the properties of the given node type are set as expected.
	 * 
	 * @param lookedUpNodeType
	 * @param expectedName
	 * @param expectedEmi
	 */
	private void checkNodeType(final EucalyptusCloudNodeType lookedUpNodeType,
			final String expectedName, final String expectedEmi) {
		Assert.assertNotNull(
				"Failed to lookup node type with name '"
						+ TestEucalyptusConfigurationInitialEntities.DBSRV0_DATA[3]
						+ "'",
				lookedUpNodeType);
		Assert.assertEquals("Unexpected name", expectedName,
				lookedUpNodeType.getName());
		Assert.assertEquals("Unexpected emi", expectedEmi,
				lookedUpNodeType.getEmiImageName());
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
		props.put(
				ConfigurationProperty.LOAD_BALANCER_ENABLED.getPropertyName(),
				"true");
		props.put(ConfigurationProperty.LOAD_BALANCER_SERVLET_URL
				.getPropertyName(), "http://localhost:8080/LoadBalancerServlet");
		props.put(ConfigurationProperty.EUCATOOLS_PATH.getPropertyName(),
				"/usr/local/bin/");
		/* BEGIN: Properties that are relevant to this test */
		props.put(
				ConfigurationProperty.EUCA_EMIS.getPropertyName(),
				String.format(
						"%s:%s;%s:%s",
						TestEucalyptusConfigurationInitialEntities.APPSRV0_DATA[3],
						TestEucalyptusConfigurationInitialEntities.APP_EMI,
						TestEucalyptusConfigurationInitialEntities.DBSRV0_DATA[3],
						TestEucalyptusConfigurationInitialEntities.DB_EMI));
		props.put(
				ConfigurationProperty.INITIAL_NODES.getPropertyName(),
				String.format(
						"%s;%s",
						StringUtils
								.join(TestEucalyptusConfigurationInitialEntities.APPSRV0_DATA,
										':'),
						StringUtils
								.join(TestEucalyptusConfigurationInitialEntities.DBSRV0_DATA,
										':')));
		props.put(
				ConfigurationProperty.INITIAL_APPLICATIONS.getPropertyName(),
				StringUtils
						.join(TestEucalyptusConfigurationInitialEntities.INITIAL_APPS,
								';'));
		props.put(
				ConfigurationProperty.INITIAL_APPLICATION_INSTANCES
						.getPropertyName(),
				String.format(
						"%s;%s;%s",
						StringUtils
								.join(TestEucalyptusConfigurationInitialEntities.INITIAL_APP_INSTANCE_0,
										':'),
						StringUtils
								.join(TestEucalyptusConfigurationInitialEntities.INITIAL_APP_INSTANCE_1,
										':'),
						StringUtils
								.join(TestEucalyptusConfigurationInitialEntities.INITIAL_APP_INSTANCE_2,
										':')));
		/* END: Properties that are relevant to this test */
		props.put(ConfigurationProperty.NODE_SHUTDOWN_DELAY_SECONDS.getPropertyName(),
				ConfigurationProperty.NODE_SHUTDOWN_DELAY_SECONDS.getDefaultValue());
		props.put(ConfigurationProperty.EUCA_KEY_NAME.getPropertyName(),
				"myEucaKey");
		props.put(ConfigurationProperty.EUCA_GROUP.getPropertyName(), "default");
		props.put(ConfigurationProperty.SSH_PRIV_KEY.getPropertyName(),
				"/home/user/.ssh/myKey.priv");
		props.put(ConfigurationProperty.SSH_USER_NAME.getPropertyName(),
				"sshUser");
		props.put(ConfigurationProperty.TOMCAT_HOME.getPropertyName(),
				"/opt/tomcat/");
		return props;
	}
}
