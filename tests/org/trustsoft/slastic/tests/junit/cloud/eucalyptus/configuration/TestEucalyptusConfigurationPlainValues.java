package org.trustsoft.slastic.tests.junit.cloud.eucalyptus.configuration;

import java.util.Properties;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.ConfigurationProperty;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.EucalyptusApplicationCloudingService;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.EucalyptusApplicationCloudingServiceConfiguration;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.IEucalyptusApplicationCloudingServiceConfiguration;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestEucalyptusConfigurationPlainValues extends TestCase {
	public void testInitializedProperly() {
		final Properties props =
				TestEucalyptusConfigurationPlainValues.genDefaultProperties();

		final IEucalyptusApplicationCloudingServiceConfiguration config =
				EucalyptusApplicationCloudingServiceConfiguration
						.createConfiguration(props);

		final EucalyptusApplicationCloudingService svc =
				EucalyptusApplicationCloudingService.createService(config);

		this.checkConfiguration(config, svc);
	}

	/**
	 * Checks if the configuration values of the given
	 * {@link IEucalyptusApplicationCloudingServiceConfiguration} are properly
	 * set corresponding to the default values in {@link ConfigurationProperty}.
	 * 
	 * @param config
	 */
	private void checkConfiguration(final IEucalyptusApplicationCloudingServiceConfiguration config,
			final EucalyptusApplicationCloudingService svc) {
		for (final ConfigurationProperty cfgProp : ConfigurationProperty.values()) {
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
						cfgProp.getDefaultValue(),
						config.getEucatoolsPath());
				// not checking svc, as there's no getter (required) for
				// eucatoolsPath
				break;
			case EUCA_EMIS:
				// we'll only perform a non-null check
				Assert.assertNotNull("Null value (config for) '" + cfgProp.getPropertyName() + "'", config.getEMIs());
				break;
			case INITIAL_NODES:
				// we'll only perform a non-null check
				Assert.assertNotNull("Null value (config for) '" + cfgProp.getPropertyName() + "'", config.getInitialNodeInstances());				
				break;
			case INITIAL_APPLICATIONS:
				// we'll only perform a non-null check
				Assert.assertNotNull("Null value (config for) '" + cfgProp.getPropertyName() + "'", config.getInitialApplications());				
				break;
			case INITIAL_APPLICATION_INSTANCES:
				// we'll only perform a non-null check
				Assert.assertNotNull("Null value (config for) '" + cfgProp.getPropertyName() + "'", config.getInitialApplicationInstances());				
				break;
			case NODE_SHUTDOWN_DELAY_MILLIS:
				Assert.fail("TOOD: hier weiter");
				break;
			case EUCA_KEY_NAME:
				break;
			case EUCA_GROUP:
				break;
			case SSH_PRIV_KEY:
				break;
			case SSH_USER_NAME:
				break;
			case TOMCAT_HOME:
				break;
			default:
				Assert.fail("Unhandled ConfigurationProperty: " + cfgProp.getPropertyName());
			}
		}
	}

	private static Properties genDefaultProperties() {
		final Properties props = new Properties();

		for (final ConfigurationProperty cfgProp : ConfigurationProperty.values()) {
			props.put(cfgProp.getPropertyName(), cfgProp.getDefaultValue());
		}

		return props;
	}
}
