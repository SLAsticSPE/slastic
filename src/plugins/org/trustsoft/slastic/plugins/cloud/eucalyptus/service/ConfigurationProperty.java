package org.trustsoft.slastic.plugins.cloud.eucalyptus.service;

import java.util.Properties;

import org.trustsoft.slastic.plugins.cloud.loadBalancerServlet.LoadBalancerServlet;

/**
 * TODO: add required field which forces to set certain values for which a
 * default value is not sufficient
 * 
 * @author Andre van Hoorn
 * 
 */
public enum ConfigurationProperty {
	/* */
	DEBUG_ENABLED("debug", "false",
			/* must not be empty: */false),
	/* */
	DUMMY_MODE_ENABLED("dummyMode", "false",
			/* must not be empty: */false),

	/* */
	LOAD_BALANCER_ENABLED("enableLoadBalancer", "true",
			/* must not be empty: */false),

	/* */
	LOAD_BALANCER_SERVLET_URL("loadBalancerServletURL",
			"http://localhost:8080/"
					+ LoadBalancerServlet.class.getPackage().getName(),
			/* must not be empty: */true),

	/* */
	EUCATOOLS_PATH("eucatoolsPath", "/usr/local/bin/",
			/* must not be empty: */true),

	/* */
	// TODO: Does this default value make any sense?
	EUCA_EMIS("emis", "app-server:emi-ABCDEFGH;db-server:emi-IJKLMNOP",
			/* must not be empty: */ true),

	/* */
	// Example: dbsrv0:192.168.44.12:i-34E606AA:db-server;appsrv0:192.168.44.13:i-45E644DD:app-server
	INITIAL_NODES("initialNodeInstances", "",
			/* may be empty */ false ),
			
	/* Example: org.compiere.WebstoreRest;org.compiere.WebstoreHotspots */
	INITIAL_APPLICATIONS("initialApplications", "",
			/* may be empty: */ false ),
	
	/* Example: org.compiere.WebstoreRest:appsrv0;org.compiere.WebstoreHotspots:appsrv0 */
	INITIAL_APPLICATION_INSTANCES("initialApplicationInstances", "",
			/* may be empty: */ false),

	/* */ 
	NODE_SHUTDOWN_DELAY_MILLIS ("nodeShutDownDelayMillis", "0",
			/* must not be empty: */ true),
			
	/* */
	EUCA_KEY_NAME("eucalyptusKeyName", "myKey",
			/* must not be empty: */true),

	/* */
	EUCA_GROUP("eucalyptusGroup", "default",
			/* must not be empty: */true),

	/* */
	SSH_PRIV_KEY("sshPrivateKeyFile", "/home/user/.ssh/myKey.priv",
			/* must not be empty: */true),

	/* */
	SSH_USER_NAME("sshUserName", "sshUser",
			/* must not be empty: */true),

	/* */
	TOMCAT_HOME("tomcatHome", "/opt/tomcat/",
			/* must not be empty: */true);

	private final String propertyName;
	private final String defaultValue;
	private final boolean allowEmpty;

	/**
	 * Constructs an enum property.
	 * 
	 * @param propertyName
	 *            the property name used in the configuration file
	 * @param defaultValue
	 *            the String representation of the default value or null if none
	 * @param allowEmpty
	 *            whether an empty value is allows or not
	 */
	ConfigurationProperty(final String propertyName, final String defaultValue,
			final boolean allowEmpty) {
		this.propertyName = propertyName;
		this.defaultValue = defaultValue;
		this.allowEmpty = allowEmpty;
	}

	/**
	 * Returns a properties map with the default configuration.
	 * 
	 * @return
	 */
	public static Properties defaultProperties() {
		final Properties props = new Properties();

		for (final ConfigurationProperty prop : ConfigurationProperty.values()) {
			if (prop.propertyName == null) {
				continue;
			}
			props.setProperty(prop.propertyName, prop.defaultValue);
		}

		return props;
	}

	public String getPropertyName() {
		return this.propertyName;
	}

	/**
	 * Returns whether the empty String is a valid property value.
	 * 
	 * @return
	 */
	public boolean isAllowEmpty() {
		return this.allowEmpty;
	}

	/**
	 * Returns the string representation of the default value.
	 * 
	 * @return
	 */
	public String getDefaultValue() {
		return this.defaultValue;
	}
}
