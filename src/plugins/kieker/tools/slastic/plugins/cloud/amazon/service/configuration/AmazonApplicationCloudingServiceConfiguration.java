package kieker.tools.slastic.plugins.cloud.amazon.service.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.tools.slastic.common.util.PropertiesFileUtils;
import kieker.tools.slastic.plugins.cloud.eucalyptus.service.configuration.PropertyMap;

public class AmazonApplicationCloudingServiceConfiguration implements
		IAmazonApplicationCloudingServiceConfiguration {

	private static final Log log = LogFactory.getLog(AmazonApplicationCloudingServiceConfiguration.class);

	private boolean debugEnabled;

	private boolean dummyModeEnabled;

	private boolean loadBalancerEnabled;

	private String loadBalancerServletURL;

	private String ec2toolsPath;

	private Map<String, String> amis;

	private Collection<String[]> initialNodeInstances;

	private Collection<String> initialApplications;

	private Collection<String[]> initialApplicationInstances;

	private int nodeShutDownDelaySeconds;

	private int nodeAllocationMaxWaitTimeSeconds;

	private int nodeAllocationPollPeriodSeconds;

	private int applicationInstanceDeployMaxWaitTimeSeconds;

	private int applicationInstanceDeployPollPeriodSeconds;

	private int defaultApplicationInstanceQueryPort;

	private String defaultApplicationInstanceQueryPath;

	private String defaultApplicationDeploymentArtifact;

	private String amazonKeyName;

	private String amazonGroup;

	private String sshPrivateKeyFile;

	private String sshUserName;

	private String tomcatHome;

	/**
	 * Must only be called by the factory methods.
	 */
	private AmazonApplicationCloudingServiceConfiguration() {}

	/**
	 * Factory methods that returns an {@link IAmazonApplicationCloudingServiceConfiguration} created based
	 * on the given {@link Properties}.
	 * 
	 * @param props
	 * @return
	 */
	// TODO: We might want to add a dedicated Exception to be thrown
	public static final IAmazonApplicationCloudingServiceConfiguration createConfiguration(final Properties props) {
		/* Create uninitialized configuration */
		AmazonApplicationCloudingServiceConfiguration configuration =
				new AmazonApplicationCloudingServiceConfiguration();

		/* Initialize configuration based on the properties. */
		final boolean success =
				AmazonApplicationCloudingServiceConfiguration.initVariablesFromProps(configuration, props);

		/* Check if initialization was successful */
		if (!success) {
			AmazonApplicationCloudingServiceConfiguration.log
					.error("Failed to create configuration from properties: " + props);
			configuration = null;
		}

		return configuration;
	}

	/**
	 * Factory method that returns an {@link IAmazonApplicationCloudingServiceConfiguration} created based
	 * on the contents of the given configuration file.
	 * 
	 * @param configurationFile
	 * @return the {@link IAmazonApplicationCloudingServiceConfiguration};
	 *         null if an error occurred.
	 */
	// TODO: We might want to add a dedicated Exception to be thrown
	public static final IAmazonApplicationCloudingServiceConfiguration createConfiguration(
			final String configurationFile) {
		/* Load configuration file. */
		final Properties props = PropertiesFileUtils.loadPropertiesFile(configurationFile);

		/* Use factory method allowing to initialize from props */
		final IAmazonApplicationCloudingServiceConfiguration configuration =
				AmazonApplicationCloudingServiceConfiguration.createConfiguration(props);

		/* Check if initialization was successful */
		if (configuration == null) {
			AmazonApplicationCloudingServiceConfiguration.log.error("Failed to load configuration from file '"
					+ configurationFile + "'");
		}

		return configuration;
	}

	/**
	 * Initializes the given {@link AmazonApplicationCloudingServiceConfiguration} based on the
	 * given {@link Properties}.
	 * 
	 * @param configuration
	 * @param props
	 * @return true on success; false if an error occurred
	 */
	private static boolean initVariablesFromProps(
			final AmazonApplicationCloudingServiceConfiguration configuration, final Properties props) {

		/* Set the debug level */
		{
			final boolean debugEnabled =
					AmazonApplicationCloudingServiceConfiguration.loadBooleanConfigurationProperty(props,
							ConfigurationProperty.DEBUG_ENABLED);
			configuration.setDebugEnabled(debugEnabled);
		}

		/* Sets the dummy mode */
		{
			final boolean dummyModeEnabled =
					AmazonApplicationCloudingServiceConfiguration.loadBooleanConfigurationProperty(props,
							ConfigurationProperty.DUMMY_MODE_ENABLED);
			configuration.setDummyModeEnabled(dummyModeEnabled);
		}

		/* */
		{
			final boolean loadBalancerEnabled =
					AmazonApplicationCloudingServiceConfiguration.loadBooleanConfigurationProperty(props,
							ConfigurationProperty.LOAD_BALANCER_ENABLED);
			configuration.setLoadBalancerEnabled(loadBalancerEnabled);
		}

		/* Sets the loadBalancerServletURL */
		{
			final String loadBalancerServletURL =
					AmazonApplicationCloudingServiceConfiguration.loadStringConfigurationProperty(props,
							ConfigurationProperty.LOAD_BALANCER_SERVLET_URL);
			configuration.setLoadBalancerServletURL(loadBalancerServletURL);
		}

		/* Sets the ec2toolsPath */
		{
			final String ec2toolsPath =
					AmazonApplicationCloudingServiceConfiguration.loadStringConfigurationProperty(props,
							ConfigurationProperty.EC2TOOLS_PATH);
			configuration.setEC2toolsPath(ec2toolsPath);
		}

		/* Sets the amis */
		{
			final String amisString =
					AmazonApplicationCloudingServiceConfiguration.loadStringConfigurationProperty(props,
							ConfigurationProperty.AMA_AMIS);
			final PropertyMap propertyMap = new PropertyMap(amisString, ";", ":");
			final Map<String, String> amis = propertyMap.getMap();
			configuration.setAMIs(amis);
		}

		/* Sets the initial nodes */
		{
			final String initialNodesString =
					AmazonApplicationCloudingServiceConfiguration.loadStringConfigurationProperty(props,
							ConfigurationProperty.INITIAL_NODES);
			final Collection<String[]> initialNodes = new ArrayList<String[]>();
			if (!initialNodesString.isEmpty()) { // if not specified
				final String[] initialNodesPairs = initialNodesString.split(";");

				for (final String initialNodePair : initialNodesPairs) {
					final String[] initialNodePairSplit = initialNodePair.split(":");
					if (initialNodePairSplit.length != 4) {
						AmazonApplicationCloudingServiceConfiguration.log.error("Invalid initialNodePair: '"
								+ initialNodePair + "'");
					}
					initialNodes.add(initialNodePairSplit);
				}
			}
			configuration.setInitialNodeInstances(initialNodes);
		}

		/* Set the initial applications */
		{
			final String initialApplicationsString =
					AmazonApplicationCloudingServiceConfiguration.loadStringConfigurationProperty(props,
							ConfigurationProperty.INITIAL_APPLICATIONS);
			final Collection<String> initialApplications = new ArrayList<String>();
			if (!initialApplicationsString.isEmpty()) { // if not specified
				final String[] applications = initialApplicationsString.split(";");
				Collections.addAll(initialApplications, applications);
			}
			configuration.setInitialApplications(initialApplications);
		}

		/* Sets the initial application instances */
		{
			final String initialApplicationInstancesString =
					AmazonApplicationCloudingServiceConfiguration.loadStringConfigurationProperty(props,
							ConfigurationProperty.INITIAL_APPLICATION_INSTANCES);
			final Collection<String[]> initialApplicationInstances = new ArrayList<String[]>();
			if (!initialApplicationInstancesString.isEmpty()) { // if not
																// specified
				final String[] initialApplicationInstancePairs = initialApplicationInstancesString.split(";");
				for (final String initialApplicationInstancePair : initialApplicationInstancePairs) {
					final String[] initialApplicationInstanceSplit = initialApplicationInstancePair.split(":");
					if (initialApplicationInstanceSplit.length != 2) {
						AmazonApplicationCloudingServiceConfiguration.log
								.error("Invalid initialApplicationInstancePair: '" + initialApplicationInstancePair
										+ "'");
					}
					initialApplicationInstances.add(initialApplicationInstanceSplit);
				}
			}
			configuration.setInitialApplicationInstances(initialApplicationInstances);
		}

		/* Sets the nodeShutDownDelayMillis */
		{
			final int nodeShutDownDelayMillis =
					AmazonApplicationCloudingServiceConfiguration.loadIntConfigurationProperty(props,
							ConfigurationProperty.NODE_SHUTDOWN_DELAY_SECONDS);
			configuration.setNodeShutDownDelayMillis(nodeShutDownDelayMillis);
		}

		/* Sets the nodeAllocationMaxWaitTimeSeconds */
		{
			final int nodeAllocationMaxWaitTimeSeconds =
					AmazonApplicationCloudingServiceConfiguration.loadIntConfigurationProperty(props,
							ConfigurationProperty.NODE_ALLOCATION_MAX_WAIT_SECONDS);
			configuration.setNodeAllocationMaxWaitTimeSeconds(nodeAllocationMaxWaitTimeSeconds);
		}

		/* Sets the nodeAllocationPollPeriodSeconds */
		{
			final int nodeAllocationPollPeriodSeconds =
					AmazonApplicationCloudingServiceConfiguration.loadIntConfigurationProperty(props,
							ConfigurationProperty.NODE_ALLOCATION_POLL_PERIOD_SECONDS);
			configuration.setNodeAllocationPollPeriodSeconds(nodeAllocationPollPeriodSeconds);
		}

		/* Sets the applicationInstanceDeployMaxWaitTimeMillis */
		{
			final int applicationInstanceDeployMaxWaitTimeMillis =
					AmazonApplicationCloudingServiceConfiguration.loadIntConfigurationProperty(props,
							ConfigurationProperty.APP_INST_DEPLOY_MAX_WAIT_TIME_SECONDS);
			configuration.setApplicationInstanceDeployMaxWaitTimeMillis(applicationInstanceDeployMaxWaitTimeMillis);

		}

		/* Sets the applicationInstanceDeployPollPeriodMillis */
		{
			final int applicationInstanceDeployPollPeriodMillis =
					AmazonApplicationCloudingServiceConfiguration.loadIntConfigurationProperty(props,
							ConfigurationProperty.APP_INST_DEPLOY_POLL_PERIOD_SECONDS);
			configuration.setApplicationInstanceDeployPollPeriodMillis(applicationInstanceDeployPollPeriodMillis);
		}

		/* Sets the defaultApplicationInstanceQueryPort */
		{
			final int defaultApplicationInstanceQueryPort =
					AmazonApplicationCloudingServiceConfiguration.loadIntConfigurationProperty(props,
							ConfigurationProperty.APP_INST_DEFAULT_QUERY_PORT);
			configuration.setDefaultApplicationInstanceQueryPort(defaultApplicationInstanceQueryPort);
		}

		/* Sets the defaultApplicationInstanceQueryPath */
		{
			final String defaultApplicationInstanceQueryPath =
					AmazonApplicationCloudingServiceConfiguration.loadStringConfigurationProperty(props,
							ConfigurationProperty.APP_INST_DEFAULT_QUERY_PATH);
			configuration.setDefaultApplicationInstanceQueryPath(defaultApplicationInstanceQueryPath);
		}

		/* Sets the AmazonKeyName */
		{
			final String AmazonKeyName =
					AmazonApplicationCloudingServiceConfiguration.loadStringConfigurationProperty(props,
							ConfigurationProperty.AMA_KEY_NAME);
			configuration.setAmazonKeyName(AmazonKeyName);
		}

		/* Sets the defaultApplicationDeploymentArtifact */
		{
			final String defaultApplicationDeploymentArtifact =
					AmazonApplicationCloudingServiceConfiguration.loadStringConfigurationProperty(props,
							ConfigurationProperty.APP_DEFAULT_DEPLOYMENT_ARTIFACT);
			configuration.setDefaultApplicationDeploymentArtifact(defaultApplicationDeploymentArtifact);
		}

		/* Sets the AmazonGroup */
		{
			final String AmazonGroup =
					AmazonApplicationCloudingServiceConfiguration.loadStringConfigurationProperty(props,
							ConfigurationProperty.AMA_GROUP);
			configuration.setAmazonGroup(AmazonGroup);
		}

		/* Sets the sshPrivateKeyFile */
		{
			final String sshPrivateKeyFile =
					AmazonApplicationCloudingServiceConfiguration.loadStringConfigurationProperty(props,
							ConfigurationProperty.SSH_PRIV_KEY);
			configuration.setSSHPrivateKeyFile(sshPrivateKeyFile);
		}

		/* Sets the sshUserName */
		{
			final String sshUserName =
					AmazonApplicationCloudingServiceConfiguration.loadStringConfigurationProperty(props,
							ConfigurationProperty.SSH_USER_NAME);
			configuration.setSSHUserName(sshUserName);
		}

		/* Sets the tomcatHome */
		{
			final String tomcatHome =
					AmazonApplicationCloudingServiceConfiguration.loadStringConfigurationProperty(props,
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
		AmazonApplicationCloudingServiceConfiguration.log.debug("Setting debugEnabled: " + this.debugEnabled);
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
		AmazonApplicationCloudingServiceConfiguration.log.debug("Setting dummyModeEnabled: "
				+ this.dummyModeEnabled);
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
	public final void setLoadBalancerServletURL(final String loadBalancerServletURL) {
		this.loadBalancerServletURL = loadBalancerServletURL;
		AmazonApplicationCloudingServiceConfiguration.log.debug("Setting loadBalancerServletURL: "
				+ this.loadBalancerServletURL);
	}

	@Override
	public String getLoadBalancerServletURL() {
		return this.loadBalancerServletURL;
	}

	@Override
	public String getEC2toolsPath() {
		return this.ec2toolsPath;
	}

	/**
	 * @param ec2toolsPath
	 *            the eucatoolsPath to set
	 */
	public final void setEC2toolsPath(final String ec2toolsPath) {
		this.ec2toolsPath = ec2toolsPath;
		AmazonApplicationCloudingServiceConfiguration.log.debug("Setting ec2toolsPath: " + this.ec2toolsPath);
	}

	@Override
	public Map<String, String> getAMIs() {
		return this.amis;
	}

	/**
	 * @param amis
	 *            the emis to set
	 */
	public final void setAMIs(final Map<String, String> amis) {
		this.amis = amis;
		AmazonApplicationCloudingServiceConfiguration.log.debug("Setting amis: " + this.amis);
	}

	@Override
	public final Collection<String[]> getInitialNodeInstances() {
		return this.initialNodeInstances;
	}

	/**
	 * @param initialNodeInstances
	 *            the initialNodeInstances to set
	 */
	public final void setInitialNodeInstances(final Collection<String[]> initialNodeInstances) {
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
	public final void setInitialApplications(final Collection<String> initialApplications) {
		this.initialApplications = initialApplications;
	}

	@Override
	public final Collection<String[]> getInitialApplicationInstances() {
		return this.initialApplicationInstances;
	}

	@Override
	public int getNodeShutDownDelaySeconds() {
		return this.nodeShutDownDelaySeconds;
	}

	/**
	 * @param nodeShutDownDelayMillis
	 *            the nodeShutDownDelayMillis to set
	 */
	public final void setNodeShutDownDelayMillis(final int nodeShutDownDelayMillis) {
		this.nodeShutDownDelaySeconds = nodeShutDownDelayMillis;
	}

	/**
	 * @return the nodeAllocationMaxWaitTimeSeconds
	 */
	@Override
	public final int getNodeAllocationMaxWaitTimeSeconds() {
		return this.nodeAllocationMaxWaitTimeSeconds;
	}

	/**
	 * @param nodeAllocationMaxWaitTimeSeconds
	 *            the nodeAllocationMaxWaitTimeSeconds to set
	 */
	public final void setNodeAllocationMaxWaitTimeSeconds(final int nodeAllocationMaxWaitTimeSeconds) {
		this.nodeAllocationMaxWaitTimeSeconds = nodeAllocationMaxWaitTimeSeconds;
	}

	/**
	 * @return the nodeAllocationPollPeriodSeconds
	 */
	@Override
	public final int getNodeAllocationPollPeriodSeconds() {
		return this.nodeAllocationPollPeriodSeconds;
	}

	/**
	 * @param nodeAllocationPollPeriodSeconds
	 *            the nodeAllocationPollPeriodSeconds to set
	 */
	public final void setNodeAllocationPollPeriodSeconds(final int nodeAllocationPollPeriodSeconds) {
		this.nodeAllocationPollPeriodSeconds = nodeAllocationPollPeriodSeconds;
	}

	@Override
	public int getApplicationInstanceDeployMaxWaitTimeSeconds() {
		return this.applicationInstanceDeployMaxWaitTimeSeconds;
	}

	/**
	 * @param applicationInstanceDeployMaxWaitTimeMillis
	 *            the applicationInstanceDeployMaxWaitTimeMillis to set
	 */
	public final void setApplicationInstanceDeployMaxWaitTimeMillis(final int applicationInstanceDeployMaxWaitTimeMillis) {
		this.applicationInstanceDeployMaxWaitTimeSeconds = applicationInstanceDeployMaxWaitTimeMillis;
	}

	@Override
	public int getApplicationInstanceDeployPollPeriodSeconds() {
		return this.applicationInstanceDeployPollPeriodSeconds;
	}

	/**
	 * @param applicationInstanceDeployPollPeriodMillis
	 *            the applicationInstanceDeployPollPeriodMillis to set
	 */
	public final void setApplicationInstanceDeployPollPeriodMillis(final int applicationInstanceDeployPollPeriodMillis) {
		this.applicationInstanceDeployPollPeriodSeconds = applicationInstanceDeployPollPeriodMillis;
	}

	@Override
	public int getDefaultApplicationInstanceQueryPort() {
		return this.defaultApplicationInstanceQueryPort;
	}

	/**
	 * @param defaultApplicationInstanceQueryPort
	 *            the defaultApplicationInstanceQueryPort to set
	 */
	public final void setDefaultApplicationInstanceQueryPort(final int defaultApplicationInstanceQueryPort) {
		this.defaultApplicationInstanceQueryPort = defaultApplicationInstanceQueryPort;
	}

	@Override
	public String getDefaultApplicationInstanceQueryPath() {
		return this.defaultApplicationInstanceQueryPath;
	}

	/**
	 * @param defaultApplicationInstanceQueryPath
	 *            the defaultApplicationInstanceQueryPath to set
	 */
	public final void setDefaultApplicationInstanceQueryPath(final String defaultApplicationInstanceQueryPath) {
		this.defaultApplicationInstanceQueryPath = defaultApplicationInstanceQueryPath;
	}

	/**
	 * @return the defaultApplicationDeploymentArtifact
	 */
	@Override
	public final String getDefaultApplicationDeploymentArtifact() {
		return this.defaultApplicationDeploymentArtifact;
	}

	/**
	 * @param defaultApplicationDeploymentArtifact
	 *            the defaultApplicationDeploymentArtifact to set
	 */
	public final void setDefaultApplicationDeploymentArtifact(final String defaultApplicationDeploymentArtifact) {
		this.defaultApplicationDeploymentArtifact = defaultApplicationDeploymentArtifact;
	}

	/**
	 * @param initialApplicationInstances
	 *            the initialApplicationInstances to set
	 */
	public final void setInitialApplicationInstances(final Collection<String[]> initialApplicationInstances) {
		this.initialApplicationInstances = initialApplicationInstances;
	}

	@Override
	public String getAmazonKeyName() {
		return this.amazonKeyName;
	}

	/**
	 * @param AmazonKeyName
	 *            the AmazonKeyName to set
	 */
	public final void setAmazonKeyName(final String AmazonKeyName) {
		this.amazonKeyName = AmazonKeyName;
		AmazonApplicationCloudingServiceConfiguration.log.debug("Setting AmazonKeyName: "
				+ this.amazonKeyName);
	}

	@Override
	public String getAmazonGroup() {
		return this.amazonGroup;
	}

	/**
	 * @param AmazonGroup
	 *            the AmazonGroup to set
	 */
	public final void setAmazonGroup(final String AmazonGroup) {
		this.amazonGroup = AmazonGroup;
		AmazonApplicationCloudingServiceConfiguration.log.debug("Setting AmazonGroup: " + this.amazonGroup);
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
		AmazonApplicationCloudingServiceConfiguration.log.debug("Setting sshPrivateKeyFile: "
				+ this.sshPrivateKeyFile);
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
		AmazonApplicationCloudingServiceConfiguration.log.debug("Setting sshUserName: " + this.sshUserName);
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
		AmazonApplicationCloudingServiceConfiguration.log.debug("Setting tomcatHome: " + this.tomcatHome);
	}

	/**
	 * 
	 * @param props
	 * @param propertyName
	 * @param defaultValue
	 * @return
	 */
	public static boolean loadBooleanConfigurationProperty(final Properties props, final ConfigurationProperty property) {
		final String stringValue =
				AmazonApplicationCloudingServiceConfiguration.loadStringConfigurationProperty(props, property);

		return Boolean.parseBoolean(stringValue);
	}

	/**
	 * 
	 * @param props
	 * @param property
	 * @return
	 * @throws NumberFormatException
	 */
	public static int loadIntConfigurationProperty(final Properties props, final ConfigurationProperty property)
			throws NumberFormatException {
		final String stringValue =
				AmazonApplicationCloudingServiceConfiguration.loadStringConfigurationProperty(props, property);

		return Integer.parseInt(stringValue);
	}

	/**
	 * 
	 * @param props
	 * @param property
	 * @return
	 */
	public static String loadStringConfigurationProperty(final Properties props, final ConfigurationProperty property) {
		String propertyValue;
		if (property.getPropertyName() != null) {
			/* we use the value from the properties map */
			propertyValue = props.getProperty(property.getPropertyName());
		} else {
			/* Un-named property with a default value */
			propertyValue = property.getDefaultValue();
		}

		if ((propertyValue == null) || (!property.isAllowEmpty() && propertyValue.isEmpty())) {
			propertyValue = property.getDefaultValue();
			AmazonApplicationCloudingServiceConfiguration.log.warn("Missing value for property '"
					+ property.getPropertyName() + "' using default value " + propertyValue);
		}

		return propertyValue;
	}
}
