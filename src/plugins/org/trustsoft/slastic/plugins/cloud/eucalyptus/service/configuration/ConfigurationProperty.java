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

package org.trustsoft.slastic.plugins.cloud.eucalyptus.service.configuration;

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
			/* allow empty: */ true),
	/* */
	DUMMY_MODE_ENABLED("dummyMode", "false",
			/* allow empty: */ true),

	/* */
	LOAD_BALANCER_ENABLED("enableLoadBalancer", "true",
			/* allow empty: */ true),

	/* */
	LOAD_BALANCER_SERVLET_URL("loadBalancerServletURL",
			"http://localhost:8080/"
					+ LoadBalancerServlet.class.getPackage().getName(),
			/* NOT allow empty: */ false),

	/* */
	EUCATOOLS_PATH("eucatoolsPath", "/usr/local/bin/",
			/* allow empty: */ true),

	/* */
	// TODO: Does this default value make any sense?
	EUCA_EMIS("emis", "app-server:emi-ABCDEFGH;db-server:emi-IJKLMNOP",
			/* NOT allow empty: */ false),

	/* */
	// Example: dbsrv0:192.168.44.12:i-34E606AA:db-server;appsrv0:192.168.44.13:i-45E644DD:app-server
	INITIAL_NODES("initialNodeInstances", "",
			/* allow empty: */ true ),
			
	/* Example: org.compiere.WebstoreRest;org.compiere.WebstoreHotspots */
	INITIAL_APPLICATIONS("initialApplications", "",
			/* allow empty: */ true ),
	
	/* Example: org.compiere.WebstoreRest:appsrv0;org.compiere.WebstoreHotspots:appsrv0 */
	INITIAL_APPLICATION_INSTANCES("initialApplicationInstances", "",
			/* allow empty: */ true),

	/* */ 
	NODE_SHUTDOWN_DELAY_SECONDS ("nodeShutDownDelaySeconds", "5",
			/* NOT allow empty: */ false),

	/* */
	NODE_ALLOCATION_MAX_WAIT_SECONDS ("nodeAllocationMaxWaitTimeSeconds", "300",
			/* NOT allow empty: */ false),			
		
	/* */
	NODE_ALLOCATION_POLL_PERIOD_SECONDS ("nodeAllocationPollPeriodSeconds", "1",
			/* NOT allow empty: */ false),
			
	/* */
	APP_INST_DEPLOY_MAX_WAIT_TIME_SECONDS ("applicationInstanceDeployMaxWaitTimeSeconds", "10", 
			/* NOT allow empty: */ false),
				
	/* */
	APP_INST_DEPLOY_POLL_PERIOD_SECONDS ("applicationInstanceDeployPollPeriodSeconds", "1",
			/* NOT allow empty: */ false),

	/* */
	APP_INST_DEFAULT_QUERY_PORT ("defaultApplicationInstanceQueryPort", "8080",
			/* NOT allow empty: */ false),
			
	/* */
	APP_INST_DEFAULT_QUERY_PATH ("defaultApplicationInstanceQueryPath", "/",
			/* NOT allow empty: */ false),
	
	/* */
	APP_DEFAULT_DEPLOYMENT_ARTIFACT ("defaultApplicationDeploymentArtifact", "artifact.war", 
			/* allow empty: */ true),
			
	/* */
	EUCA_KEY_NAME("eucalyptusKeyName", "myKey",
			/* NOT allow empty: */ false),

	/* */
	EUCA_GROUP("eucalyptusGroup", "default",
			/* NOT allow empty: */ false),

	/* */
	SSH_PRIV_KEY("sshPrivateKeyFile", "/home/user/.ssh/myKey.priv",
			/* NOT allow empty: */ false),

	/* */
	SSH_USER_NAME("sshUserName", "sshUser",
			/* NOT allow empty: */ false),

	/* */
	TOMCAT_HOME("tomcatHome", "/opt/tomcat/",
			/* allow empty: */ true);

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
