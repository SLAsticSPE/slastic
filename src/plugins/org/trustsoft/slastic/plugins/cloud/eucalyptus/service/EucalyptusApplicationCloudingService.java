package org.trustsoft.slastic.plugins.cloud.eucalyptus.service;

import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.*;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.configuration.EucalyptusApplicationCloudingServiceConfiguration;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.configuration.IEucalyptusApplicationCloudingServiceConfiguration;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.eucaToolsIntegration.*;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.loadBalancer.LoadBalancerConnector;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.logging.EucalyptusServiceEventNotifier;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.logging.IEucalyptusServiceEventListener;
import org.trustsoft.slastic.plugins.cloud.model.*;
import org.trustsoft.slastic.plugins.cloud.service.ApplicationCloudingServiceException;
import org.trustsoft.slastic.plugins.cloud.service.IApplicationCloudingService;

/**
 * This is the place to call the Eucalyptus tools.
 * 
 * @author Andre van Hoorn, Florian Fittkau
 * 
 */
public class EucalyptusApplicationCloudingService implements
		IApplicationCloudingService {

	private static final Log log = LogFactory
			.getLog(EucalyptusApplicationCloudingService.class);

	// TODO: Introduce methods for safe casts of ICloud* to Eucalyptus* (nodes,
	// node types, applications, ...)
	// Currently the recurring verbose patterns make the code hardly readable.

	// TODO: The same for non-null checks

	// TODO: refine, e.g., writing to the component context
	private final static String WGET_LOG = "wget.log";

	// TODO: extract parameters: 1-2) tomcat start/stop script, 3-4) Kieker
	// configuration file/path
	// This should be defined based as 'remote-post-start-script' and
	// 'local-remote-post-start-script' or alike

	private final IEucalyptusApplicationCloudingServiceConfiguration configuration;

	// TODO: Move the following collections to an abstract class?
	private final Collection<EucalyptusCloudNodeType> nodeTypes = new Vector<EucalyptusCloudNodeType>();
	private final Collection<EucalyptusCloudNode> allocatedNodes = new Vector<EucalyptusCloudNode>();
	private final Collection<EucalyptusCloudedApplication> applications = new Vector<EucalyptusCloudedApplication>();
	private final Collection<EucalyptusApplicationInstance> applicationInstances = new Vector<EucalyptusApplicationInstance>();

	private final LoadBalancerConnector lbConnector;

	private final EucalyptusServiceEventNotifier eventNotifier = new EucalyptusServiceEventNotifier();

	private int nextHostname = 0;

	private int nextDummyEucaInstanceId = 19077777;
	private int nextDummyEucaIPCount = 1;

	/**
	 * Must only be called by the factory methods.
	 * 
	 * @param config
	 * @throws ApplicationCloudingServiceException
	 */
	private EucalyptusApplicationCloudingService(
			final IEucalyptusApplicationCloudingServiceConfiguration config)
			throws ApplicationCloudingServiceException {
		configuration = config;

		/* Initialize the connector to the LoadBalancerServlet */
		lbConnector = new LoadBalancerConnector(
				configuration.getLoadBalancerServletURL(), false,
				EucalyptusApplicationCloudingService.WGET_LOG);
		lbConnector.setDummyMode(configuration.isDummyModeEnabled());

		initNodeTypes(); // throws an exception on error
		initNodes(); // throws an exception on error
		initApplications(); // throws an exception on error
		initApplicationInstances(); // throws an exception on error
	}

	/**
	 * 
	 * @throws ApplicationCloudingServiceException
	 */
	private void initNodeTypes() throws ApplicationCloudingServiceException {
		printDebugMsg("initNodeTypes(..)");

		for (final Entry<String, String> image : configuration.getEMIs()
				.entrySet()) {
			nodeTypes.add(new EucalyptusCloudNodeType(image.getKey(), image
					.getValue()));
		}
	}

	/**
	 * 
	 * @throws ApplicationCloudingServiceException
	 */
	private void initNodes() throws ApplicationCloudingServiceException {
		for (final String[] nodeSpec : configuration.getInitialNodeInstances()) {
			final String nodeName = nodeSpec[0];
			final String ip = nodeSpec[1];
			final String instanceId = nodeSpec[2];
			final String nodeTypeName = nodeSpec[3];
			final ICloudNodeType nodeType = lookupCloudNodeType(nodeTypeName);
			if (nodeType == null) {
				throw new ApplicationCloudingServiceException(
						"Failed to lookup node type '" + nodeTypeName + "'");
			}

			allocatedNodes.add(new EucalyptusCloudNode(nodeName, nodeType,
					instanceId, ip, nodeName));
		}
	}

	/**
	 * 
	 * @throws ApplicationCloudingServiceException
	 */
	private void initApplications() throws ApplicationCloudingServiceException {
		for (final String appName : configuration.getInitialApplications()) {
			applications.add(new EucalyptusCloudedApplication(appName,
					new EucalyptusCloudedApplicationConfiguration()));
		}
	}

	/**
	 * 
	 * @throws ApplicationCloudingServiceException
	 */
	private void initApplicationInstances()
			throws ApplicationCloudingServiceException {
		for (final String[] appInstDesc : configuration
				.getInitialApplicationInstances()) {
			final String appName = appInstDesc[0];
			final String nodeInstanceName = appInstDesc[1];

			final EucalyptusCloudedApplication app = (EucalyptusCloudedApplication) lookupCloudedApplication(appName);
			if (app == null) {
				throw new ApplicationCloudingServiceException(
						"Failed to lookup application with name '" + appName
								+ "'");
			}

			final EucalyptusCloudNode node = (EucalyptusCloudNode) lookupNode(nodeInstanceName);
			if (node == null) {
				throw new ApplicationCloudingServiceException(
						"Failed to lookup node with name '" + nodeInstanceName
								+ "'");
			}

			applicationInstances.add(new EucalyptusApplicationInstance(
					genApplicationInstanceId(app), app,
					new EucalyptusApplicationInstanceConfiguration(), node));
		}
	}

	/**
	 * Factory methods returning an {@link EucalyptusApplicationCloudingService}
	 * configured according to the contents of the given configuration file.
	 * 
	 * @param configurationFileName
	 *            path to the configuration file
	 * @return
	 */
	// TODO: We might re-throw a possible Exception thrown by
	// #loadEucalyptusConfiguration.
	public static EucalyptusApplicationCloudingService createService(
			final String configurationFile) {
		final IEucalyptusApplicationCloudingServiceConfiguration config = EucalyptusApplicationCloudingServiceConfiguration
				.createConfiguration(configurationFile);
		return EucalyptusApplicationCloudingService.createService(config);
	}

	/**
	 * Factory method returning an {@link EucalyptusApplicationCloudingService}
	 * based on the given
	 * {@link EucalyptusApplicationCloudingServiceConfiguration}.
	 * 
	 * @param config
	 * @return the {@link EucalyptusApplicationCloudingServiceConfiguration};
	 *         null if an error occurred
	 */
	// TODO: We might re-throw a possible Exception thrown by
	// #loadEucalyptusConfiguration.
	public static EucalyptusApplicationCloudingService createService(
			final IEucalyptusApplicationCloudingServiceConfiguration config) {
		EucalyptusApplicationCloudingService svc = null;

		try {
			svc = new EucalyptusApplicationCloudingService(config);
		} catch (final ApplicationCloudingServiceException e) {
			EucalyptusApplicationCloudingService.log.error(
					"Failed to create EucalyptusApplicationCloudingService: "
							+ e.getMessage(), e);
		}

		return svc;
	}

	@Override
	public Collection<EucalyptusCloudNodeType> getNodeTypes() {
		printDebugMsg("getNodeTypes(..)");

		return nodeTypes;
	}

	// TODO: structure implementation (in sub-methods); currently, this is a
	// mess
	@Override
	public ICloudNode allocateNode(final String name, final ICloudNodeType type)
			throws ApplicationCloudingServiceException {

		printDebugMsg("allocateNode --- " + "name: " + name + "; type: " + type);

		/*
		 * Assert that node is Eucalyptus-specific.
		 */
		if (!(type instanceof EucalyptusCloudNodeType)) {
			final String errorsMsg = "type must be of class "
					+ EucalyptusCloudNodeType.class + " but found "
					+ type.getClass();
			EucalyptusApplicationCloudingService.log.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final EucalyptusCloudNodeType euType = (EucalyptusCloudNodeType) type;

		final ExternalCommandExecuter executer = new ExternalCommandExecuter(
				configuration.isDummyModeEnabled());
		final EucalyptusCommand allocateNodeCommand = EucalyptusCommandFactory
				.getAllocateNodeCommand(euType.getEmiImageName(),
						configuration.getEucalyptusKeyName(),
						configuration.getEucalyptusGroup());

		String result = executer.executeCommandWithEnv(allocateNodeCommand,
				configuration.getEucatoolsPath());

		final String instanceID;
		String ipAddress;

		/* 1. Start instance and determine IP address */
		if (configuration.isDummyModeEnabled()) {
			instanceID = Integer.toString(nextDummyEucaInstanceId++);
			ipAddress = "110.110.110." + nextDummyEucaIPCount++;
		} else {
			instanceID = getInstanceIDFromAllocateNodeResult(result);

			final EucalyptusCommand descibeInstanceCommand = EucalyptusCommandFactory
					.getDescribeNodeCommand(instanceID);

			ipAddress = "0.0.0.0";
			int secondCounter = 0;

			while ((ipAddress.equals("")) || ipAddress.equals("0.0.0.0")) {
				result = executer.executeCommandWithEnv(descibeInstanceCommand,
						configuration.getEucatoolsPath());
				ipAddress = getIPAddressFromDescribeNodeResult(result);

				try {
					Thread.sleep(configuration
							.getNodeAllocationPollPeriodSeconds() * 1000);
					secondCounter += configuration
							.getNodeAllocationPollPeriodSeconds();
				} catch (final InterruptedException e) {
					EucalyptusApplicationCloudingService.log.error(
							"Waiting for node startup failed: "
									+ e.getMessage(), e);
				}

				if (secondCounter > configuration
						.getNodeAllocationMaxWaitTimeSeconds()) {
					throw new ApplicationCloudingServiceException(
							configuration.getNodeAllocationMaxWaitTimeSeconds()
									+ " seconds timeout for allocateNode reached.");
				}
			}
		}

		/* 2. Set hostname */

		final EucalyptusCommand setHostameCommand = EucalyptusCommandFactory
				.getSetHostnameCommand(configuration.getSSHPrivateKeyFile(),
						configuration.getSSHUserName(), ipAddress);
		executer.executeCommandWithEnv(setHostameCommand,
				configuration.getEucatoolsPath());

		/* 3. Determine hostname */

		final EucalyptusCommand fetchHostameCommand = EucalyptusCommandFactory
				.getFetchHostnameCommand(configuration.getSSHPrivateKeyFile(),
						configuration.getSSHUserName(), ipAddress);
		String hostResult = executer.executeCommandWithEnv(fetchHostameCommand,
				configuration.getEucatoolsPath());

		if (configuration.isDummyModeEnabled()) {
			hostResult = "dummy-hostname-" + nextHostname++;
		}

		final String hostname = getHostnameFromResult(hostResult);

		/* 3. Do ImageName specific Start */

		if (euType.getEmiImageName().equalsIgnoreCase("adempiere")) {

			final EucalyptusCommand startAdempiereCommand = EucalyptusCommandFactory
					.getStartAdempiereCommand(
							configuration.getSSHPrivateKeyFile(),
							configuration.getSSHUserName(),
							ipAddress,
							". /opt/adempiere/Adempiere/startAndConfigureAdempiere.sh",
							"192.168.48.85");
			executer.executeCommandWithEnv(startAdempiereCommand,
					configuration.getEucatoolsPath());
		}

		EucalyptusCloudNode node = new EucalyptusCloudNode(name, type,
				instanceID, ipAddress, hostname);

		printDebugMsg("allocateNode --- " + "name: " + name + "; type: " + type
				+ " has finished: " + node);

		allocatedNodes.add(node);

		eventNotifier.notifyAllocateNodeSuccess(name, euType, node);

		return node;
	}

	/**
	 * 
	 * @param result
	 * @return
	 */
	private String getHostnameFromResult(final String result) {
		return result;
	}

	/**
	 * 
	 * @param result
	 * @return
	 */
	private String getInstanceIDFromAllocateNodeResult(final String result) {
		final int begin = result.indexOf("INSTANCE");
		String instanceString = result.substring(begin);

		final int start = instanceString.indexOf("\t");
		instanceString = instanceString.substring(start + 1);

		final int end = instanceString.indexOf("\t");
		return instanceString.substring(0, end);
	}

	/**
	 * 
	 * @param result
	 * @return
	 */
	private String getIPAddressFromDescribeNodeResult(final String result) {
		final int begin = result.indexOf("INSTANCE");
		String instanceString = result.substring(begin);

		final int instanceStart = instanceString.indexOf("\t");
		instanceString = instanceString.substring(instanceStart + 1);

		final int emiStart = instanceString.indexOf("\t");
		instanceString = instanceString.substring(emiStart + 1);

		final int ipStart = instanceString.indexOf("\t");
		instanceString = instanceString.substring(ipStart + 1);

		final int ipEnd = instanceString.indexOf("\t");
		return instanceString.substring(0, ipEnd);
	}

	@Override
	public void deallocateNode(final ICloudNode node)
			throws ApplicationCloudingServiceException {
		printDebugMsg("deallocateNode --- " + "node: " + node);

		allocatedNodes.remove(node);

		/*
		 * Assert that node is Eucalyptus-specific.
		 */
		if (!(node instanceof EucalyptusCloudNode)) {
			final String errorsMsg = "node must be of class "
					+ EucalyptusCloudNode.class + " but found "
					+ node.getClass();
			EucalyptusApplicationCloudingService.log.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final EucalyptusCloudNode euNode = (EucalyptusCloudNode) node;

		final ExternalCommandExecuter executer = new ExternalCommandExecuter(
				configuration.isDummyModeEnabled());
		final EucalyptusCommand deallocateNodeCommand = EucalyptusCommandFactory
				.getDeallocateNodeCommand(euNode.getInstanceID());

		if (configuration.isDummyModeEnabled()) {
			// spawn execution of shutdown command with given delay
			executer.executeCommandWithEnvAndDelayAsync(deallocateNodeCommand,
					configuration.getEucatoolsPath(),
					configuration.getNodeShutDownDelaySeconds() * 1000);
		} else {
			EucalyptusApplicationCloudingService.log.warn("Not executing "
					+ deallocateNodeCommand);
		}

		eventNotifier.notifyDeallocateNodeSuccess(euNode);
	}

	/**
	 * The given configuration must be of class
	 * {@link EucalyptusCloudedApplicationConfiguration}.
	 */
	@Override
	public ICloudedApplication createAndRegisterCloudedApplication(
			final String name,
			final ICloudedApplicationConfiguration configuration)
			throws ApplicationCloudingServiceException {

		printDebugMsg("createAndRegisterCloudedApplication --- " + "name: "
				+ name + "; configuration: " + configuration);

		/*
		 * Assert that configuration is Eucalyptus-specific.
		 */
		if (!(configuration instanceof EucalyptusCloudedApplicationConfiguration)) {
			final String errorsMsg = "configuration must be of class "
					+ EucalyptusCloudedApplicationConfiguration.class
					+ " but found " + configuration.getClass();
			EucalyptusApplicationCloudingService.log.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final EucalyptusCloudedApplicationConfiguration euConfiguration = (EucalyptusCloudedApplicationConfiguration) configuration;

		final EucalyptusCloudedApplication app = new EucalyptusCloudedApplication(
				name, euConfiguration);

		/* Register application in load balancer */
		if (!lbConnector.createContext(app.getName())) {
			final String errorMsg = "Failed to register application '"
					+ app.getName() + "' in load balancer";
			EucalyptusApplicationCloudingService.log.error(errorMsg);
			throw new ApplicationCloudingServiceException(errorMsg);
		}

		/* Store application in internal table */
		applications.add(app);

		// Notice that no calls to the Eucalyptus tools required.

		eventNotifier.notifyCreateAndRegisterCloudedApplicationSuccess(name,
				euConfiguration, app);

		return app;
	}

	@Override
	public void removeCloudedApplication(final ICloudedApplication application)
			throws ApplicationCloudingServiceException {

		printDebugMsg("removeCloudedApplication --- " + "application: "
				+ application);

		/* Remove application from load balancer */
		// TODO: check if load balancer enabled?
		if (!lbConnector.removeContext(application.getName())) {
			final String errorMsg = "Failed to deregister application '"
					+ application.getName() + "' from load balancer";
			EucalyptusApplicationCloudingService.log.error(errorMsg);
			throw new ApplicationCloudingServiceException(errorMsg);
		}

		/* Remove application from internal table */
		applications.remove(application);

		// Notice that no calls to the Eucalyptus tools required.

		eventNotifier
				.notifyRemoveCloudedApplicationSuccess((EucalyptusCloudedApplication) application);
	}

	@Override
	public IApplicationInstance deployApplicationInstance(
			final ICloudedApplication application,
			final IApplicationInstanceConfiguration configuration,
			final ICloudNode node) throws ApplicationCloudingServiceException {

		printDebugMsg("deployApplicationInstance --- " + "application: "
				+ application + "; configuration: " + configuration);

		/*
		 * Assert that application is Eucalyptus-specific.
		 */
		if (!(application instanceof EucalyptusCloudedApplication)) {
			final String errorsMsg = "application must be of class "
					+ EucalyptusCloudedApplication.class + " but found "
					+ configuration.getClass();
			EucalyptusApplicationCloudingService.log.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final EucalyptusCloudedApplication euApplication = (EucalyptusCloudedApplication) application;

		/*
		 * Assert that configuration is Eucalyptus-specific.
		 */
		if (!(configuration instanceof EucalyptusApplicationInstanceConfiguration)) {
			final String errorsMsg = "configuration must be of class "
					+ EucalyptusApplicationInstanceConfiguration.class
					+ " but found " + configuration.getClass();
			EucalyptusApplicationCloudingService.log.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final EucalyptusApplicationInstanceConfiguration euConfiguration = (EucalyptusApplicationInstanceConfiguration) configuration;

		/*
		 * Assert that node is Eucalyptus-specific.
		 */
		if (!(node instanceof EucalyptusCloudNode)) {
			final String errorsMsg = "node must be of class "
					+ EucalyptusCloudNode.class + " but found "
					+ configuration.getClass();
			EucalyptusApplicationCloudingService.log.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final EucalyptusCloudNode euNode = (EucalyptusCloudNode) node;

		final EucalyptusApplicationInstance inst = new EucalyptusApplicationInstance(
				genApplicationInstanceId(euApplication), euApplication,
				euConfiguration, euNode);

		applicationInstances.add(inst);

		/* 1. Deploy */
		final ExternalCommandExecuter executer = new ExternalCommandExecuter(
				this.configuration.isDummyModeEnabled());
		final EucalyptusCommand applicationDeployCommand = EucalyptusCommandFactory
				.getApplicationDeployCommand(this.configuration
						.getSSHPrivateKeyFile(), this.configuration
						.getSSHUserName(), this.configuration.getTomcatHome(),
						this.configuration
								.getDefaultApplicationDeploymentArtifact(),
						euNode.getIpAddress());
		executer.executeCommandWithEnv(applicationDeployCommand,
				this.configuration.getEucatoolsPath());

		// try {
		// Thread.sleep(EucalyptusApplicationCloudingService.WAIT_SECONDS_AFTER_DEPLOY
		// * 1000);
		// } catch (final InterruptedException e) {
		// EucalyptusApplicationCloudingService.log.error(e.getMessage(), e);
		// }

		/* 2. Wait for application to become available */

		if (!waitUntilInstanceAvailable(euNode.getIpAddress(),
				this.configuration.getDefaultApplicationInstanceQueryPort(),
				this.configuration.getDefaultApplicationInstanceQueryPath(),
				this.configuration
						.getApplicationInstanceDeployPollPeriodSeconds(),
				this.configuration
						.getApplicationInstanceDeployMaxWaitTimeSeconds())) {
			final String errorMsg = "Deployed intance " + inst
					+ " not available after deployment";
			EucalyptusApplicationCloudingService.log.error(errorMsg);
			throw new ApplicationCloudingServiceException(errorMsg);
		}

		/* 3. Add instance to load balancer */
		if (this.configuration.isLoadBalancerEnabled()
				&& !lbConnector.addHost(application.getName(),
						euNode.getIpAddress())) {
			final String errorMsg = "Failed to add host '"
					+ euNode.getIpAddress() + "' for application '"
					+ application.getName() + "' in load balancer";
			EucalyptusApplicationCloudingService.log.error(errorMsg);
			// clean up broken state (to be correct, we should only role back
			// the deployment):
			undeployApplicationInstance(inst);
			throw new ApplicationCloudingServiceException(errorMsg);
		}

		eventNotifier.notifyDeployApplicationInstanceSuccess(euApplication,
				euConfiguration, euNode, inst);

		return inst;
	}

	final String WGET_SUCCESS_EXAMPLE = "--2011-01-13 12:34:37--  http://192.168.48.94:8080/jpetstore\n"
			+ "Verbindungsaufbau zu 192.168.48.94:8080... verbunden.\n"
			+ "HTTP Anforderung gesendet, warte auf Antwort... 302 Moved Temporarily\n"
			+ "Platz: http://192.168.48.94:8080/jpetstore/ [folge]\n"
			+ "--2011-01-13 12:34:37--  http://192.168.48.94:8080/jpetstore/\n"
			+ "Verbindungsaufbau zu 192.168.48.94:8080... verbunden.\n"
			+ "HTTP Anforderung gesendet, warte auf Antwort... 200 OK\n"
			+ "Länge: 523 [text/html]\n"
			+ "In »»jpetstore.3«« speichern\n."
			+ "\n"
			+ "100%[========================================================================================================================================================================>] 523         --.-K/s   in 0s\n"
			+ "\n"
			+ "2011-01-13 12:34:37 (80,8 MB/s) - »»jpetstore.3«« gespeichert [523/523]\n"
			+ "\n";

	private boolean waitUntilInstanceAvailable(final String ipAddress,
			final int port, final String path, final int tryPeriodSeconds,
			final int maxWaitTimeSeconds) {
		final String url = ipAddress + ":" + port + "/" + path;
		final ExternalCommandExecuter executer = new ExternalCommandExecuter(
				configuration.isDummyModeEnabled());
		final EucalyptusCommand fetchInstanceWebSite = EucalyptusCommandFactory
				.getFetchWebSiteCommand(url);
		String wgetResult = "";

		int totalWaitTimeSeconds = 0;

		while (true) {
			if (wgetResult.contains("200 OK")) {
				EucalyptusApplicationCloudingService.log.info("url '" + "'"
						+ url + " available after " + totalWaitTimeSeconds
						+ " seconds");
				return true;
			}

			if (totalWaitTimeSeconds > maxWaitTimeSeconds) {
				final String errorMsg = maxWaitTimeSeconds
						+ " seconds try period elapsed to access url '" + url
						+ "#";

				EucalyptusApplicationCloudingService.log.error(errorMsg);
				return false;
			}
			totalWaitTimeSeconds += tryPeriodSeconds;

			try {
				Thread.sleep(tryPeriodSeconds * 1000);
			} catch (final InterruptedException e) {
				final String errorMsg = "Interrupted: " + e.getMessage();
				EucalyptusApplicationCloudingService.log.error(errorMsg);
				return false;
			}

			wgetResult = executer.executeCommandWithEnv(fetchInstanceWebSite,
					configuration.getEucatoolsPath());
			if (configuration.isDummyModeEnabled()
					&& ((totalWaitTimeSeconds + tryPeriodSeconds > maxWaitTimeSeconds) // (max.
																						// possible
																						// number
																						// of
																						// tries)
																						// )
					|| (maxWaitTimeSeconds > 5))) {
				wgetResult = WGET_SUCCESS_EXAMPLE;

			}
		}
	}

	/**
	 * Returns an ID for an {@link EucalyptusApplicationInstance} to be
	 * associated with the given {@link EucalyptusCloudedApplication}.
	 * 
	 * @param application
	 * @return
	 */
	private String genApplicationInstanceId(
			final EucalyptusCloudedApplication application) {
		return application.getName() + "--" + application.acquireInstanceId();
	}

	@Override
	public void undeployApplicationInstance(final IApplicationInstance instance)
			throws ApplicationCloudingServiceException {

		printDebugMsg("undeployApplicationInstance --- " + "instance: "
				+ instance);

		final ICloudNode node = instance.getNode();

		/*
		 * Assert that node is Eucalyptus-specific.
		 */
		if (!(node instanceof EucalyptusCloudNode)) {
			final String errorsMsg = "node must be of class "
					+ EucalyptusCloudNode.class + " but found "
					+ configuration.getClass();
			EucalyptusApplicationCloudingService.log.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final EucalyptusCloudNode euNode = (EucalyptusCloudNode) node;

		applicationInstances.remove(instance);

		/* 1. Remove from load balancer */
		if (configuration.isLoadBalancerEnabled()
				&& !lbConnector.removeHost(instance.getApplication().getName(),
						euNode.getIpAddress())) {
			final String errorMsg = "Failed to remove host '"
					+ euNode.getIpAddress() + "' for application '"
					+ instance.getApplication().getName()
					+ "' from load balancer";
			EucalyptusApplicationCloudingService.log.error(errorMsg);
			// Continuing in order to avoid a broken state
			// throw new ApplicationCloudingServiceException(errorMsg);
		}

		/* 2. Undeploy */
		final ExternalCommandExecuter executer = new ExternalCommandExecuter(
				configuration.isDummyModeEnabled());
		final EucalyptusCommand applicationUndeployCommand = EucalyptusCommandFactory
				.getApplicationUndeployCommand("jpetstore.war",
						euNode.getIpAddress());
		executer.executeCommandWithEnv(applicationUndeployCommand,
				configuration.getEucatoolsPath());

		eventNotifier
				.notifyUndeployApplicationInstanceSuccess((EucalyptusApplicationInstance) instance);
	}

	/**
	 * Logs the given message if
	 * {@link IEucalyptusApplicationCloudingServiceConfiguration#isDebugEnabled()}
	 * for this service.
	 * 
	 * @param msg
	 */
	private final void printDebugMsg(final String msg) {
		if ((configuration != null) && configuration.isDebugEnabled()) {
			EucalyptusApplicationCloudingService.log.info(msg);
		}
	}

	@Override
	public ICloudNode lookupNode(final String name) {
		for (final ICloudNode node : allocatedNodes) {
			if (node.getName().equals(name)) {
				return node;
			}
		}
		return null;
	}

	@Override
	public ICloudedApplication lookupCloudedApplication(final String name) {
		for (final ICloudedApplication app : applications) {
			if (app.getName().equals(name)) {
				return app;
			}
		}
		return null;
	}

	@Override
	public ICloudNodeType lookupCloudNodeType(final String name) {
		for (final ICloudNodeType type : nodeTypes) {
			if (type.getName().equals(name)) {
				return type;
			}
		}
		return null;
	}

	@Override
	public IApplicationInstance lookupApplicationInstance(
			final ICloudedApplication cloudedApplication,
			final ICloudNode cloudNode) {
		for (final IApplicationInstance applicationInstance : applicationInstances) {
			if (applicationInstance.getApplication().equals(cloudedApplication)
					&& applicationInstance.getNode().equals(cloudNode)) {
				return applicationInstance;
			}
		}
		return null;
	}

	@Override
	public Collection<? extends ICloudNode> getCloudNodes() {
		return allocatedNodes;
	}

	@Override
	public Collection<? extends ICloudedApplication> getCloudedApplications() {
		return applications;
	}

	@Override
	public final Collection<? extends IApplicationInstance> getApplicationInstances() {
		return applicationInstances;
	}

	/**
	 * 
	 * @param l
	 */
	public void addEventListener(final IEucalyptusServiceEventListener l) {
		eventNotifier.addEventListener(l);
	}

	/**
	 * 
	 * @param l
	 * @return
	 */
	public boolean removeEventListener(final IEucalyptusServiceEventListener l) {
		return eventNotifier.removeEventListener(l);
	}
}
