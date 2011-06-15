package org.trustsoft.slastic.plugins.cloud.eucalyptus.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;

import kieker.analysis.util.PropertyMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.util.PropertiesFileUtils;

public class EucalyptusApplicationCloudingServiceConfiguration implements
		IEucalyptusApplicationCloudingServiceConfiguration {

	private static final Log log = LogFactory
			.getLog(EucalyptusApplicationCloudingServiceConfiguration.class);

	private boolean debugEnabled;

	private boolean dummyModeEnabled;

	private boolean loadBalancerEnabled;

	private String loadBalancerServletURL;

	private String eucatoolsPath;

	private HashMap<String, String> emis;

	private Collection<String[]> initialNodeInstances;

	private Collection<String> initialApplications;

	private Collection<String[]> initialApplicationInstances;

	private String eucalyptusKeyName;

	private String eucalyptusGroup;

	private String sshPrivateKeyFile;

	private String sshUserName;

	private String tomcatHome;

	/**
	 * Must only be called by the factory methods.
	 */
	private EucalyptusApplicationCloudingServiceConfiguration() {
	}

	/**
	 * Factory methods that returns an
	 * {@link IEucalyptusApplicationCloudingServiceConfiguration} created based
	 * on the given {@link Properties}.
	 * 
	 * @param props
	 * @return
	 */
	// TODO: We might want to add a dedicated Exception to be thrown
	public static final IEucalyptusApplicationCloudingServiceConfiguration createConfiguration(
			final Properties props) {
		/* Create uninitialized configuration */
		EucalyptusApplicationCloudingServiceConfiguration configuration =
				new EucalyptusApplicationCloudingServiceConfiguration();

		/* Initialize configuration based on the properties. */
		final boolean success =
				EucalyptusApplicationCloudingServiceConfiguration
						.initVariablesFromProps(configuration, props);

		/* Check if initialization was successful */
		if (!success) {
			EucalyptusApplicationCloudingServiceConfiguration.log
					.error("Failed to create configuration from properties: "
							+ props);
			configuration = null;
		}

		return configuration;
	}

	/**
	 * Factory method that returns an
	 * {@link IEucalyptusApplicationCloudingServiceConfiguration} created based
	 * on the contents of the given configuration file.
	 * 
	 * @param configurationFile
	 * @return the {@link IEucalyptusApplicationCloudingServiceConfiguration};
	 *         null if an error occurred.
	 */
	// TODO: We might want to add a dedicated Exception to be thrown
	public static final IEucalyptusApplicationCloudingServiceConfiguration createConfiguration(
			final String configurationFile) {
		/* Load configuration file. */
		final Properties props =
				PropertiesFileUtils.loadPropertiesFile(configurationFile);

		/* Use factory method allowing to initialize from props */
		final IEucalyptusApplicationCloudingServiceConfiguration configuration =
				EucalyptusApplicationCloudingServiceConfiguration
						.createConfiguration(props);

		/* Check if initialization was successful */
		if (configuration == null) {
			EucalyptusApplicationCloudingServiceConfiguration.log
					.error("Failed to load configuration from file '"
							+ configurationFile + "'");
		}

		return configuration;
	}

	/**
	 * Initializes the given
	 * {@link EucalyptusApplicationCloudingServiceConfiguration} based on the
	 * given {@link Properties}.
	 * 
	 * @param configuration
	 * @param props
	 * @return true on success; false if an error occurred
	 */
	private static boolean initVariablesFromProps(
			final EucalyptusApplicationCloudingServiceConfiguration configuration,
			final Properties props) {

		/* Set the debug level */
		{
			final boolean debugEnabled =
					EucalyptusApplicationCloudingServiceConfiguration
							.loadBooleanConfigurationProperty(props,
									ConfigurationProperty.DEBUG_ENABLED);
			configuration.setDebugEnabled(debugEnabled);
		}

		/* Sets the dummy mode */
		{
			final boolean dummyModeEnabled =
					EucalyptusApplicationCloudingServiceConfiguration
							.loadBooleanConfigurationProperty(props,
									ConfigurationProperty.DUMMY_MODE_ENABLED);
			configuration.setDummyModeEnabled(dummyModeEnabled);
		}

		/* */
		{
			final boolean loadBalancerEnabled =
					EucalyptusApplicationCloudingServiceConfiguration
							.loadBooleanConfigurationProperty(props,
									ConfigurationProperty.LOAD_BALANCER_ENABLED);
			configuration.setLoadBalancerEnabled(loadBalancerEnabled);
		}

		/* Sets the loadBalancerServletURL */
		{
			final String loadBalancerServletURL =
					EucalyptusApplicationCloudingServiceConfiguration
							.loadStringConfigurationProperty(props,
									ConfigurationProperty.LOAD_BALANCER_SERVLET_URL);
			configuration.setLoadBalancerServletURL(loadBalancerServletURL);
		}

		/* Sets the eucatoolsPath */
		{
			final String eucatoolsPath =
					EucalyptusApplicationCloudingServiceConfiguration
							.loadStringConfigurationProperty(props,
									ConfigurationProperty.EUCATOOLS_PATH);
			configuration.setEucatoolsPath(eucatoolsPath);
		}

		/* Sets the emis */
		{
			final String emisString =
					EucalyptusApplicationCloudingServiceConfiguration
							.loadStringConfigurationProperty(props,
									ConfigurationProperty.EUCA_EMIS);
			final PropertyMap propertyMap =
					new PropertyMap(emisString, ";", ":");
			final HashMap<String, String> emis = propertyMap.getMap();
			configuration.setEMIs(emis);
		}

		/* Sets the initial nodes */
		{
			final String initialNodesString =
					EucalyptusApplicationCloudingServiceConfiguration
							.loadStringConfigurationProperty(props,
									ConfigurationProperty.INITIAL_NODES);
			final String[] initialNodesPairs = initialNodesString.split(";");
			final Collection<String[]> initialNodes = new ArrayList<String[]>();
			for (final String initialNodePair : initialNodesPairs) {
				final String[] initialNodePairSplit =
						initialNodePair.split(":");
				if (initialNodePairSplit.length != 3) {
					EucalyptusApplicationCloudingServiceConfiguration.log
							.error("Invalid initialNodePair: '"
									+ initialNodePair + "'");
				}
				initialNodes.add(initialNodePairSplit);
			}
			configuration.setInitialNodeInstances(initialNodes);
		}

		/* Set the initial applications */
		{
			final String initialApplicationsString =
					EucalyptusApplicationCloudingServiceConfiguration
							.loadStringConfigurationProperty(props,
									ConfigurationProperty.INITIAL_APPLICATIONS);
			final String[] applications = initialApplicationsString.split(";");
			final Collection<String> initialApplications =
					new ArrayList<String>();
			Collections.addAll(initialApplications, applications);
			configuration.setInitialApplications(initialApplications);
		}

		/* Sets the initial application instances */
		{
			final String initialApplicationInstancesString =
					EucalyptusApplicationCloudingServiceConfiguration
							.loadStringConfigurationProperty(
									props,
									ConfigurationProperty.INITIAL_APPLICATION_INSTANCES);
			final String[] initialApplicationInstancePairs =
					initialApplicationInstancesString.split(";");
			final Collection<String[]> initialApplicationInstances =
					new ArrayList<String[]>();
			for (final String initialApplicationInstancePair : initialApplicationInstancePairs) {
				final String[] initialApplicationInstanceSplit =
						initialApplicationInstancePair.split(":");
				if (initialApplicationInstanceSplit.length != 2) {
					EucalyptusApplicationCloudingServiceConfiguration.log
							.error("Invalid initialApplicationInstancePair: '"
									+ initialApplicationInstancePair + "'");
				}
				initialApplicationInstances
						.add(initialApplicationInstanceSplit);
			}
			configuration
					.setInitialApplicationInstances(initialApplicationInstances);
		}

		/* Sets the eucalyptusKeyName */
		{
			final String eucalyptusKeyName =
					EucalyptusApplicationCloudingServiceConfiguration
							.loadStringConfigurationProperty(props,
									ConfigurationProperty.EUCA_KEY_NAME);
			configuration.setEucalyptusKeyName(eucalyptusKeyName);
		}

		/* Sets the eucalyptusGroup */
		{
			final String eucalyptusGroup =
					EucalyptusApplicationCloudingServiceConfiguration
							.loadStringConfigurationProperty(props,
									ConfigurationProperty.EUCA_GROUP);
			configuration.setEucalyptusGroup(eucalyptusGroup);
		}

		/* Sets the sshPrivateKeyFile */
		{
			final String sshPrivateKeyFile =
					EucalyptusApplicationCloudingServiceConfiguration
							.loadStringConfigurationProperty(props,
									ConfigurationProperty.SSH_PRIV_KEY);
			configuration.setSSHPrivateKeyFile(sshPrivateKeyFile);
		}

		/* Sets the sshUserName */
		{
			final String sshUserName =
					EucalyptusApplicationCloudingServiceConfiguration
							.loadStringConfigurationProperty(props,
									ConfigurationProperty.SSH_USER_NAME);
			configuration.setSSHUserName(sshUserName);
		}

		/* Sets the tomcatHome */
		{
			final String tomcatHome =
					EucalyptusApplicationCloudingServiceConfiguration
							.loadStringConfigurationProperty(props,
									ConfigurationProperty.TOMCAT_HOME);
			configuration.setTomcatHome(tomcatHome);
		}

		return true;
	}

	/**
	 * Sets the debug configuration value.
	 * 
	 * @param debugEnabled
	 *            the debugEnabled to set
	 */
	public void setDebugEnabled(final boolean debugEnabled) {
		this.debugEnabled = debugEnabled;
		EucalyptusApplicationCloudingServiceConfiguration.log
				.debug("Setting debugEnabled: " + this.debugEnabled);
	}

	/**
	 * Returns the debug configuration value.
	 * 
	 * @return the debugEnabled
	 */
	@Override
	public boolean isDebugEnabled() {
		return this.debugEnabled;
	}

	/**
	 * @param dummyModeEnabled
	 *            the dummyMode to set
	 */
	public final void setDummyModeEnabled(final boolean dummyModeEnabled) {
		this.dummyModeEnabled = dummyModeEnabled;
		EucalyptusApplicationCloudingServiceConfiguration.log
				.debug("Setting dummyModeEnabled: " + this.dummyModeEnabled);
	}

	/**
	 * @return the dummyMode
	 */
	@Override
	public final boolean isDummyModeEnabled() {
		return this.dummyModeEnabled;
	}

	/**
	 * @return the loadBalancerEnabled
	 */
	@Override
	public final boolean isLoadBalancerEnabled() {
		return this.loadBalancerEnabled;
	}

	/**
	 * @param loadBalancerEnabled
	 *            the loadBalancerEnabled to set
	 */
	public final void setLoadBalancerEnabled(final boolean loadBalancerEnabled) {
		this.loadBalancerEnabled = loadBalancerEnabled;
	}

	/**
	 * @param loadBalancerServletURL
	 *            the loadBalancerServletURL to set
	 */
	public final void setLoadBalancerServletURL(
			final String loadBalancerServletURL) {
		this.loadBalancerServletURL = loadBalancerServletURL;
		EucalyptusApplicationCloudingServiceConfiguration.log
				.debug("Setting loadBalancerServletURL: "
						+ this.loadBalancerServletURL);
	}

	@Override
	public String getLoadBalancerServletURL() {
		return this.loadBalancerServletURL;
	}

	@Override
	public String getEucatoolsPath() {
		return this.eucatoolsPath;
	}

	/**
	 * @param eucatoolsPath
	 *            the eucatoolsPath to set
	 */
	public final void setEucatoolsPath(final String eucatoolsPath) {
		this.eucatoolsPath = eucatoolsPath;
		EucalyptusApplicationCloudingServiceConfiguration.log
				.debug("Setting eucatoolsPath: " + this.eucatoolsPath);
	}

	@Override
	public HashMap<String, String> getEMIs() {
		return this.emis;
	}

	/**
	 * @param emis
	 *            the emis to set
	 */
	public final void setEMIs(final HashMap<String, String> emis) {
		this.emis = emis;
		EucalyptusApplicationCloudingServiceConfiguration.log
				.debug("Setting emis: " + this.emis);
	}

	@Override
	public final Collection<String[]> getInitialNodeInstances() {
		return this.initialNodeInstances;
	}

	/**
	 * @param initialNodeInstances
	 *            the initialNodeInstances to set
	 */
	public final void setInitialNodeInstances(
			final Collection<String[]> initialNodeInstances) {
		this.initialNodeInstances = initialNodeInstances;
	}

	@Override
	public final Collection<String> getInitialApplications() {
		return this.initialApplications;
	}

	/**
	 * @param initialApplications
	 *            the initialApplications to set
	 */
	public final void setInitialApplications(
			final Collection<String> initialApplications) {
		this.initialApplications = initialApplications;
	}

	@Override
	public final Collection<String[]> getInitialApplicationInstances() {
		return this.initialApplicationInstances;
	}

	/**
	 * @param initialApplicationInstances
	 *            the initialApplicationInstances to set
	 */
	public final void setInitialApplicationInstances(
			final Collection<String[]> initialApplicationInstances) {
		this.initialApplicationInstances = initialApplicationInstances;
	}

	@Override
	public String getEucalyptusKeyName() {
		return this.eucalyptusKeyName;
	}

	/**
	 * @param eucalyptusKeyName
	 *            the eucalyptusKeyName to set
	 */
	public final void setEucalyptusKeyName(final String eucalyptusKeyName) {
		this.eucalyptusKeyName = eucalyptusKeyName;
		EucalyptusApplicationCloudingServiceConfiguration.log
				.debug("Setting eucalyptusKeyName: " + this.eucalyptusKeyName);
	}

	@Override
	public String getEucalyptusGroup() {
		return this.eucalyptusGroup;
	}

	/**
	 * @param eucalyptusGroup
	 *            the eucalyptusGroup to set
	 */
	public final void setEucalyptusGroup(final String eucalyptusGroup) {
		this.eucalyptusGroup = eucalyptusGroup;
		EucalyptusApplicationCloudingServiceConfiguration.log
				.debug("Setting eucalyptusGroup: " + this.eucalyptusGroup);
	}

	@Override
	public String getSSHPrivateKeyFile() {
		return this.sshPrivateKeyFile;
	}

	/**
	 * @param sshPrivateKeyFile
	 *            the sshPrivateKeyFile to set
	 */
	public final void setSSHPrivateKeyFile(final String sshPrivateKeyFile) {
		this.sshPrivateKeyFile = sshPrivateKeyFile;
		EucalyptusApplicationCloudingServiceConfiguration.log
				.debug("Setting sshPrivateKeyFile: " + this.sshPrivateKeyFile);
	}

	@Override
	public String getSSHUserName() {
		return this.sshUserName;
	}

	/**
	 * @param sshUserName
	 *            the sshUserName to set
	 */
	public final void setSSHUserName(final String sshUserName) {
		this.sshUserName = sshUserName;
		EucalyptusApplicationCloudingServiceConfiguration.log
				.debug("Setting sshUserName: " + this.sshUserName);
	}

	@Override
	public String getTomcatHome() {
		return this.tomcatHome;
	}

	/**
	 * @param tomcatHome
	 *            the tomcatHome to set
	 */
	public final void setTomcatHome(final String tomcatHome) {
		this.tomcatHome = tomcatHome;
		EucalyptusApplicationCloudingServiceConfiguration.log
				.debug("Setting tomcatHome: " + this.tomcatHome);
	}

	/**
	 * 
	 * @param props
	 * @param propertyName
	 * @param defaultValue
	 * @return
	 */
	public static boolean loadBooleanConfigurationProperty(
			final Properties props, final ConfigurationProperty property) {
		final String stringValue =
				EucalyptusApplicationCloudingServiceConfiguration
						.loadStringConfigurationProperty(props, property);

		return Boolean.parseBoolean(stringValue);
	}

	/**
	 * 
	 * @param props
	 * @param property
	 * @return
	 * @throws NumberFormatException
	 */
	public static int loadIntConfigurationProperty(final Properties props,
			final ConfigurationProperty property) throws NumberFormatException {
		final String stringValue =
				EucalyptusApplicationCloudingServiceConfiguration
						.loadStringConfigurationProperty(props, property);

		return Integer.parseInt(stringValue);
	}

	/**
	 * 
	 * @param props
	 * @param property
	 * @return
	 */
	public static String loadStringConfigurationProperty(
			final Properties props, final ConfigurationProperty property) {
		String propertyValue;
		if (property.getPropertyName() != null) {
			/* we use the value from the properties map */
			propertyValue = props.getProperty(property.getPropertyName());
		} else {
			/* Un-named property with a default value */
			propertyValue = property.getDefaultValue();
		}

		if ((propertyValue == null)
				|| (!property.isAllowEmpty() && propertyValue.isEmpty())) {
			propertyValue = property.getDefaultValue();
			EucalyptusApplicationCloudingServiceConfiguration.log
					.warn("Missing value for property '"
							+ property.getPropertyName()
							+ "' using default value " + propertyValue);
		}

		return propertyValue;
	}
}
