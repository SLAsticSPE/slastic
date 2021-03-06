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

package kieker.tools.slastic.tests.junit.cloud.eucalyptus.configuration;

import java.util.Properties;

import junit.framework.Assert;
import junit.framework.TestCase;

import kieker.tools.slastic.plugins.cloud.eucalyptus.service.EucalyptusApplicationCloudingService;
import kieker.tools.slastic.plugins.cloud.eucalyptus.service.configuration.ConfigurationProperty;
import kieker.tools.slastic.plugins.cloud.eucalyptus.service.configuration.EucalyptusApplicationCloudingServiceConfiguration;
import kieker.tools.slastic.plugins.cloud.eucalyptus.service.configuration.IEucalyptusApplicationCloudingServiceConfiguration;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestEucalyptusConfigurationPlainValues extends TestCase {

	/**
	 * 
	 */
	public void testInitializedProperly() {
		final Properties props = ConfigurationProperty.defaultProperties();

		final IEucalyptusApplicationCloudingServiceConfiguration config = EucalyptusApplicationCloudingServiceConfiguration.createConfiguration(props);

		final EucalyptusApplicationCloudingService svc = EucalyptusApplicationCloudingService.createService(config);

		this.checkConfiguration(config, svc);
	}

	/**
	 * Checks if the configuration values of the given {@link IEucalyptusApplicationCloudingServiceConfiguration} are properly
	 * set corresponding to the default values in {@link ConfigurationProperty}.
	 * 
	 * @param config
	 */
	private void checkConfiguration(final IEucalyptusApplicationCloudingServiceConfiguration config, final EucalyptusApplicationCloudingService svc) {
		for (final ConfigurationProperty cfgProp : ConfigurationProperty
				.values()) {
			// new property values will need to be added as new cases
			switch (cfgProp) {
			case DEBUG_ENABLED:
				Assert.assertEquals("Unexpected value (config) for '" + cfgProp.getPropertyName() + "'",
						cfgProp.getDefaultValue(),
						Boolean.toString(config.isDebugEnabled()));
				// not checking svc, as there's no getter (required) for debug
				break;
			case DUMMY_MODE_ENABLED:
				Assert.assertEquals("Unexpected value (config) for '" + cfgProp.getPropertyName() + "'",
						cfgProp.getDefaultValue(),
						Boolean.toString(config.isDummyModeEnabled()));
				// not checking svc, as there's no getter (required) for
				// dummyMode
				break;
			case LOAD_BALANCER_ENABLED:
				Assert.assertEquals("Unexpected value (config) for '" + cfgProp.getPropertyName() + "'",
						cfgProp.getDefaultValue(),
						Boolean.toString(config.isLoadBalancerEnabled()));
				// not checking svc, as there's no getter (required) for
				// enableLoadBalancer
				break;
			case LOAD_BALANCER_SERVLET_URL:
				Assert.assertEquals("Unexpected value (config) for '" + cfgProp.getPropertyName() + "'",
						cfgProp.getDefaultValue(),
						config.getLoadBalancerServletURL());
				// not checking svc, as there's no getter (required) for
				// loadBalancerServletURL
				break;
			case EUCATOOLS_PATH:
				Assert.assertEquals("Unexpected value (config) for '" + cfgProp.getPropertyName() + "'",
						cfgProp.getDefaultValue(), config.getEucatoolsPath());
				// not checking svc, as there's no getter (required) for
				// eucatoolsPath
				break;
			case EUCA_EMIS:
				// we'll only perform a non-null check
				Assert.assertNotNull(
						"Null value (config for) '" + cfgProp.getPropertyName()
								+ "'", config.getEMIs());
				break;
			case INITIAL_NODES:
				// we'll only perform a non-null check
				Assert.assertNotNull("Null value (config for) '" + cfgProp.getPropertyName() + "'", config.getInitialNodeInstances());
				Assert.assertNotNull("Null value (svc) for initial nodes", svc.getCloudNodes());
				break;
			case INITIAL_APPLICATIONS:
				// we'll only perform a non-null check
				Assert.assertNotNull("Null value (config for) '" + cfgProp.getPropertyName() + "'", config.getInitialApplications());
				Assert.assertNotNull("Null value (svc) for initial applications", svc.getCloudedApplications());
				break;
			case INITIAL_APPLICATION_INSTANCES:
				// we'll only perform a non-null check
				Assert.assertNotNull("Null value (config for) '" + cfgProp.getPropertyName() + "'", config.getInitialApplicationInstances());
				Assert.assertNotNull("Null value (svc) for initial application instances", svc.getApplicationInstances());
				break;
			case NODE_SHUTDOWN_DELAY_SECONDS:
				Assert.assertEquals("Unexpected value (config) for '" + cfgProp.getPropertyName() + "'",
						cfgProp.getDefaultValue(), Integer.toString(config.getNodeShutDownDelaySeconds()));
				// not checking svc, as there's no getter (required) for debug
				break;
			case NODE_ALLOCATION_MAX_WAIT_SECONDS:
				Assert.assertEquals("Unexpected value (config) for '" + cfgProp.getPropertyName() + "'",
						cfgProp.getDefaultValue(), Integer.toString(config.getNodeAllocationMaxWaitTimeSeconds()));
				// not checking svc, as there's no getter (required) for debug
				break;
			case NODE_ALLOCATION_POLL_PERIOD_SECONDS:
				Assert.assertEquals("Unexpected value (config) for '" + cfgProp.getPropertyName() + "'",
						cfgProp.getDefaultValue(),
						Integer.toString(config.getNodeAllocationPollPeriodSeconds()));
				// not checking svc, as there's no getter (required) for debug
				break;
			case APP_INST_DEPLOY_MAX_WAIT_TIME_SECONDS:
				Assert.assertEquals("Unexpected value (config) for '" + cfgProp.getPropertyName() + "'",
						cfgProp.getDefaultValue(), Integer.toString(config.getApplicationInstanceDeployMaxWaitTimeSeconds()));
				// not checking svc, as there's no getter (required) for debug
				break;
			case APP_INST_DEPLOY_POLL_PERIOD_SECONDS:
				Assert.assertEquals("Unexpected value (config) for '" + cfgProp.getPropertyName() + "'",
						cfgProp.getDefaultValue(), Integer.toString(config.getApplicationInstanceDeployPollPeriodSeconds()));
				// not checking svc, as there's no getter (required) for debug
				break;
			case APP_INST_DEFAULT_QUERY_PORT:
				Assert.assertEquals("Unexpected value (config) for '" + cfgProp.getPropertyName() + "'",
						cfgProp.getDefaultValue(), Integer.toString(config.getDefaultApplicationInstanceQueryPort()));
				break;
			case APP_INST_DEFAULT_QUERY_PATH:
				Assert.assertEquals("Unexpected value (config) for '" + cfgProp.getPropertyName() + "'",
						cfgProp.getDefaultValue(), config.getDefaultApplicationInstanceQueryPath());
				break;
			case APP_DEFAULT_DEPLOYMENT_ARTIFACT:
				Assert.assertEquals("Unexpected value (config) for '" + cfgProp.getPropertyName() + "'",
						cfgProp.getDefaultValue(), config.getDefaultApplicationDeploymentArtifact());
				break;
			case EUCA_KEY_NAME:
				Assert.assertEquals("Unexpected value (config) for '" + cfgProp.getPropertyName() + "'",
						cfgProp.getDefaultValue(), config.getEucalyptusKeyName());
				// not checking svc, as there's no getter (required) for debug
				break;
			case EUCA_GROUP:
				Assert.assertEquals("Unexpected value (config) for '" + cfgProp.getPropertyName() + "'",
						cfgProp.getDefaultValue(), config.getEucalyptusGroup());
				// not checking svc, as there's no getter (required) for debug
				break;
			case SSH_PRIV_KEY:
				Assert.assertEquals("Unexpected value (config) for '" + cfgProp.getPropertyName() + "'",
						cfgProp.getDefaultValue(), config.getSSHPrivateKeyFile());
				// not checking svc, as there's no getter (required) for debug
				break;
			case SSH_USER_NAME:
				Assert.assertEquals("Unexpected value (config) for '" + cfgProp.getPropertyName() + "'",
						cfgProp.getDefaultValue(), config.getSSHUserName());
				// not checking svc, as there's no getter (required) for debug
				break;
			case TOMCAT_HOME:
				Assert.assertEquals("Unexpected value (config) for '" + cfgProp.getPropertyName() + "'",
						cfgProp.getDefaultValue(), config.getTomcatHome());
				// not checking svc, as there's no getter (required) for debug
				break;
			default:
				Assert.fail("Unhandled ConfigurationProperty: " + cfgProp.getPropertyName());
			}
		}
	}
}
