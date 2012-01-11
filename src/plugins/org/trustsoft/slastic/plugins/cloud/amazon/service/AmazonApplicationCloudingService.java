package org.trustsoft.slastic.plugins.cloud.amazon.service;

import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.cloud.amazon.model.*;
import org.trustsoft.slastic.plugins.cloud.amazon.service.configuration.AmazonApplicationCloudingServiceConfiguration;
import org.trustsoft.slastic.plugins.cloud.amazon.service.configuration.IAmazonApplicationCloudingServiceConfiguration;
import org.trustsoft.slastic.plugins.cloud.amazon.service.ec2ToolsIntegration.AmazonCommand;
import org.trustsoft.slastic.plugins.cloud.amazon.service.ec2ToolsIntegration.AmazonCommandFactory;
import org.trustsoft.slastic.plugins.cloud.amazon.service.loadBalancer.AmazonLoadBalancerConnector;
import org.trustsoft.slastic.plugins.cloud.amazon.service.logging.AmazonServiceEventNotifier;
import org.trustsoft.slastic.plugins.cloud.amazon.service.logging.IAmazonServiceEventListener;
import org.trustsoft.slastic.plugins.cloud.common.ExternalCommandExecuter;
import org.trustsoft.slastic.plugins.cloud.model.*;
import org.trustsoft.slastic.plugins.cloud.service.ApplicationCloudingServiceException;
import org.trustsoft.slastic.plugins.cloud.service.IApplicationCloudingService;

/**
 * This is the place to call the Amazon tools.
 * 
 * @author Andre van Hoorn, Florian Fittkau
 * 
 */
public class AmazonApplicationCloudingService implements IApplicationCloudingService {

	private static final Log log = LogFactory.getLog(AmazonApplicationCloudingService.class);

	// TODO: Introduce methods for safe casts of ICloud* to Amazon* (nodes,
	// node types, applications, ...)
	// Currently the recurring verbose patterns make the code hardly readable.

	// TODO: The same for non-null checks

	// TODO: refine, e.g., writing to the component context
	private final static String WGET_LOG = "wget.log";

	// TODO: extract parameters: 1-2) tomcat start/stop script, 3-4) Kieker
	// configuration file/path
	// This should be defined based as 'remote-post-start-script' and
	// 'local-remote-post-start-script' or alike

	private final IAmazonApplicationCloudingServiceConfiguration configuration;

	// TODO: Move the following collections to an abstract class?
	private final Collection<AmazonCloudNodeType> nodeTypes = new Vector<AmazonCloudNodeType>();
	private final Collection<AmazonCloudNode> allocatedNodes = new Vector<AmazonCloudNode>();
	private final Collection<AmazonCloudedApplication> applications = new Vector<AmazonCloudedApplication>();
	private final Collection<AmazonApplicationInstance> applicationInstances =
			new Vector<AmazonApplicationInstance>();

	private final AmazonLoadBalancerConnector lbConnector;

	private final AmazonServiceEventNotifier eventNotifier = new AmazonServiceEventNotifier();

	private int nextHostname = 0;

	private int nextDummyEC2InstanceId = 19077777;
	private int nextDummyEC2IPCount = 1;

	/**
	 * Must only be called by the factory methods.
	 * 
	 * @param config
	 * @throws ApplicationCloudingServiceException
	 */
	private AmazonApplicationCloudingService(final IAmazonApplicationCloudingServiceConfiguration config)
			throws ApplicationCloudingServiceException {
		this.configuration = config;

		/* Initialize the connector to the LoadBalancerServlet */
		this.lbConnector =
				new AmazonLoadBalancerConnector(this.configuration.getLoadBalancerServletURL(), false,
						AmazonApplicationCloudingService.WGET_LOG);
		this.lbConnector.setDummyMode(this.configuration.isDummyModeEnabled());

		this.initNodeTypes(); // throws an exception on error
		this.initNodes(); // throws an exception on error
		this.initApplications(); // throws an exception on error
		this.initApplicationInstances(); // throws an exception on error
	}

	/**
	 * 
	 * @throws ApplicationCloudingServiceException
	 */
	private void initNodeTypes() throws ApplicationCloudingServiceException {
		for (final Entry<String, String> image : this.configuration.getAMIs().entrySet()) {
			this.nodeTypes.add(new AmazonCloudNodeType(image.getKey(), image.getValue()));
		}
	}

	/**
	 * 
	 * @throws ApplicationCloudingServiceException
	 */
	private void initNodes() throws ApplicationCloudingServiceException {
		for (final String[] nodeSpec : this.configuration.getInitialNodeInstances()) {
			final String nodeName = nodeSpec[0];
			final String ip = nodeSpec[1];
			final String instanceId = nodeSpec[2];
			final String nodeTypeName = nodeSpec[3];
			final ICloudNodeType nodeType = this.lookupCloudNodeType(nodeTypeName);
			if (nodeType == null) {
				throw new ApplicationCloudingServiceException("Failed to lookup node type '" + nodeTypeName + "'");
			}

			this.allocatedNodes.add(new AmazonCloudNode(nodeName, nodeType, instanceId, ip, nodeName));
		}
	}

	/**
	 * 
	 * @throws ApplicationCloudingServiceException
	 */
	private void initApplications() throws ApplicationCloudingServiceException {
		for (final String appName : this.configuration.getInitialApplications()) {
			this.applications.add(new AmazonCloudedApplication(appName,
					new AmazonCloudedApplicationConfiguration()));
		}
	}

	/**
	 * 
	 * @throws ApplicationCloudingServiceException
	 */
	private void initApplicationInstances() throws ApplicationCloudingServiceException {
		for (final String[] appInstDesc : this.configuration.getInitialApplicationInstances()) {
			final String appName = appInstDesc[0];
			final String nodeInstanceName = appInstDesc[1];

			final AmazonCloudedApplication app =
					(AmazonCloudedApplication) this.lookupCloudedApplication(appName);
			if (app == null) {
				throw new ApplicationCloudingServiceException("Failed to lookup application with name '" + appName
						+ "'");
			}

			final AmazonCloudNode node = (AmazonCloudNode) this.lookupNode(nodeInstanceName);
			if (node == null) {
				throw new ApplicationCloudingServiceException("Failed to lookup node with name '" + nodeInstanceName
						+ "'");
			}

			this.applicationInstances.add(new AmazonApplicationInstance(this.genApplicationInstanceId(app), app,
					new AmazonApplicationInstanceConfiguration(), node));
		}
	}

	/**
	 * Factory methods returning an {@link AmazonApplicationCloudingService}
	 * configured according to the contents of the given configuration file.
	 * 
	 * @param configurationFileName
	 *            path to the configuration file
	 * @return
	 */
	// TODO: We might re-throw a possible Exception thrown by
	// #loadAmazonConfiguration.
	public static AmazonApplicationCloudingService createService(final String configurationFile) {
		final IAmazonApplicationCloudingServiceConfiguration config =
				AmazonApplicationCloudingServiceConfiguration.createConfiguration(configurationFile);
		return AmazonApplicationCloudingService.createService(config);
	}

	/**
	 * Factory method returning an {@link AmazonApplicationCloudingService}
	 * based on the given
	 * {@link AmazonApplicationCloudingServiceConfiguration}.
	 * 
	 * @param config
	 * @return the {@link AmazonApplicationCloudingServiceConfiguration};
	 *         null if an error occurred
	 */
	// TODO: We might re-throw a possible Exception thrown by
	// #loadAmazonConfiguration.
	public static AmazonApplicationCloudingService createService(
			final IAmazonApplicationCloudingServiceConfiguration config) {
		AmazonApplicationCloudingService svc = null;

		try {
			svc = new AmazonApplicationCloudingService(config);
		} catch (final ApplicationCloudingServiceException e) {
			AmazonApplicationCloudingService.log.error("Failed to create AmazonApplicationCloudingService: "
					+ e.getMessage(), e);
		}

		return svc;
	}

	@Override
	public Collection<AmazonCloudNodeType> getNodeTypes() {
		this.printDebugMsg("getNodeTypes(..)");

		return this.nodeTypes;
	}

	// TODO: structure implementation (in sub-methods); currently, this is a
	// mess
	@Override
	public ICloudNode allocateNode(final String name, final ICloudNodeType type)
			throws ApplicationCloudingServiceException {

		this.printDebugMsg("allocateNode --- " + "name: " + name + "; type: " + type);

		/*
		 * Assert that node is Amazon-specific.
		 */
		if (!(type instanceof AmazonCloudNodeType)) {
			final String errorsMsg =
					"type must be of class " + AmazonCloudNodeType.class + " but found " + type.getClass();
			AmazonApplicationCloudingService.log.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final AmazonCloudNodeType euType = (AmazonCloudNodeType) type;

		final ExternalCommandExecuter executer = new ExternalCommandExecuter(this.configuration.isDummyModeEnabled());
		final AmazonCommand allocateNodeCommand =
				AmazonCommandFactory.getAllocateNodeCommand(euType.getAmiImageName(),
						this.configuration.getAmazonKeyName(), this.configuration.getAmazonGroup());

		String result = executer.executeCommandWithEnv(allocateNodeCommand, this.configuration.getEC2toolsPath());

		final String instanceID;
		String ipAddress;

		/* 1. Start instance and determine IP address */
		if (this.configuration.isDummyModeEnabled()) {
			instanceID = Integer.toString(this.nextDummyEC2InstanceId++);
			ipAddress = "110.110.110." + this.nextDummyEC2IPCount++;
		} else {
			instanceID = this.getInstanceIDFromAllocateNodeResult(result);

			final AmazonCommand descibeInstanceCommand =
					AmazonCommandFactory.getDescribeNodeCommand(instanceID);

			ipAddress = "0.0.0.0";
			int secondCounter = 0;

			while ((ipAddress.equals("")) || ipAddress.equals("0.0.0.0")) {
				result = executer.executeCommandWithEnv(descibeInstanceCommand, this.configuration.getEC2toolsPath());
				ipAddress = this.getIPAddressFromDescribeNodeResult(result);

				try {
					Thread.sleep(this.configuration.getNodeAllocationPollPeriodSeconds() * 1000);
					secondCounter += this.configuration.getNodeAllocationPollPeriodSeconds();
				} catch (final InterruptedException e) {
					AmazonApplicationCloudingService.log.error(
							"Waiting for node startup failed: " + e.getMessage(), e);
				}

				if (secondCounter > this.configuration.getNodeAllocationMaxWaitTimeSeconds()) {
					throw new ApplicationCloudingServiceException(
							this.configuration.getNodeAllocationMaxWaitTimeSeconds()
									+ " seconds timeout for allocateNode reached.");
				}
			}
		}
		
		final AmazonCommand setHostname =
				AmazonCommandFactory.getStartRemoteCommandCommand(this.configuration.getSSHPrivateKeyFile(),
						this.configuration.getSSHUserName(), ipAddress,
						"/etc/init.d/hostname.sh");
		executer.executeCommandWithEnv(setHostname,
				this.configuration.getEC2toolsPath());

		/* 2. Determine hostname */

		final AmazonCommand fetchHostnameCommand =
				AmazonCommandFactory.getFetchHostnameCommand(this.configuration.getSSHPrivateKeyFile(),
						this.configuration.getSSHUserName(), ipAddress);
		String hostResult = executer.executeCommandWithEnv(fetchHostnameCommand, this.configuration.getEC2toolsPath());

		if (this.configuration.isDummyModeEnabled()) {
			hostResult = "dummy-hostname-" + this.nextHostname++;
		}

		final String hostname = this.getHostnameFromResult(hostResult);

		/* 3. Start tomcat */

		final AmazonCommand startTomcatCommand =
				AmazonCommandFactory.getStartTomcatCommand(this.configuration.getSSHPrivateKeyFile(),
						this.configuration.getSSHUserName(), ipAddress,
						"/etc/init.d/tomcat.sh");
		executer.executeCommandWithEnv(startTomcatCommand,
				this.configuration.getEC2toolsPath());

		final AmazonCloudNode node = new AmazonCloudNode(name, type, instanceID, ipAddress, hostname);

		this.printDebugMsg("allocateNode --- " + "name: " + name + "; type: " + type + " has finished: " + node);

		this.allocatedNodes.add(node);

		this.eventNotifier.notifyAllocateNodeSuccess(name, euType, node);

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
	public void deallocateNode(final ICloudNode node) throws ApplicationCloudingServiceException {
		this.printDebugMsg("deallocateNode --- " + "node: " + node);

		this.allocatedNodes.remove(node);

		/*
		 * Assert that node is Amazon-specific.
		 */
		if (!(node instanceof AmazonCloudNode)) {
			final String errorsMsg =
					"node must be of class " + AmazonCloudNode.class + " but found " + node.getClass();
			AmazonApplicationCloudingService.log.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final AmazonCloudNode euNode = (AmazonCloudNode) node;

		final ExternalCommandExecuter executer = new ExternalCommandExecuter(this.configuration.isDummyModeEnabled());
		final AmazonCommand deallocateNodeCommand =
				AmazonCommandFactory.getDeallocateNodeCommand(euNode.getInstanceID());

		if (!this.configuration.isDummyModeEnabled()) {
			// spawn execution of shutdown command with given delay
			executer.executeCommandWithEnvAndDelayAsync(deallocateNodeCommand, this.configuration.getEC2toolsPath(),
					this.configuration.getNodeShutDownDelaySeconds() * 1000);
		} else {
			AmazonApplicationCloudingService.log.warn("Not executing " + deallocateNodeCommand);
		}

		this.eventNotifier.notifyDeallocateNodeSuccess(euNode);
	}

	/**
	 * The given configuration must be of class
	 * {@link AmazonCloudedApplicationConfiguration}.
	 */
	@Override
	public ICloudedApplication createAndRegisterCloudedApplication(final String name,
			final ICloudedApplicationConfiguration configuration) throws ApplicationCloudingServiceException {

		this.printDebugMsg("createAndRegisterCloudedApplication --- " + "name: " + name + "; configuration: "
				+ configuration);

		/*
		 * Assert that configuration is Amazon-specific.
		 */
		if (!(configuration instanceof AmazonCloudedApplicationConfiguration)) {
			final String errorsMsg =
					"configuration must be of class " + AmazonCloudedApplicationConfiguration.class + " but found "
							+ configuration.getClass();
			AmazonApplicationCloudingService.log.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final AmazonCloudedApplicationConfiguration euConfiguration =
				(AmazonCloudedApplicationConfiguration) configuration;

		final AmazonCloudedApplication app = new AmazonCloudedApplication(name, euConfiguration);

		/* Register application in load balancer */
		if (!this.lbConnector.createContext(app.getName())) {
			final String errorMsg = "Failed to register application '" + app.getName() + "' in load balancer";
			AmazonApplicationCloudingService.log.error(errorMsg);
			throw new ApplicationCloudingServiceException(errorMsg);
		}

		/* Store application in internal table */
		this.applications.add(app);

		// Notice that no calls to the Amazon tools required.

		this.eventNotifier.notifyCreateAndRegisterCloudedApplicationSuccess(name, euConfiguration, app);

		return app;
	}

	@Override
	public void removeCloudedApplication(final ICloudedApplication application)
			throws ApplicationCloudingServiceException {

		this.printDebugMsg("removeCloudedApplication --- " + "application: " + application);

		/* Remove application from load balancer */
		// TODO: check if load balancer enabled?
		if (!this.lbConnector.removeContext(application.getName())) {
			final String errorMsg =
					"Failed to deregister application '" + application.getName() + "' from load balancer";
			AmazonApplicationCloudingService.log.error(errorMsg);
			throw new ApplicationCloudingServiceException(errorMsg);
		}

		/* Remove application from internal table */
		this.applications.remove(application);

		// Notice that no calls to the Amazon tools required.

		this.eventNotifier.notifyRemoveCloudedApplicationSuccess((AmazonCloudedApplication) application);
	}

	@Override
	public IApplicationInstance deployApplicationInstance(final ICloudedApplication application,
			final IApplicationInstanceConfiguration configuration, final ICloudNode node)
			throws ApplicationCloudingServiceException {

		this.printDebugMsg("deployApplicationInstance --- " + "application: " + application + "; configuration: "
				+ configuration);

		/*
		 * Assert that application is Amazon-specific.
		 */
		if (!(application instanceof AmazonCloudedApplication)) {
			final String errorsMsg =
					"application must be of class " + AmazonCloudedApplication.class + " but found "
							+ configuration.getClass();
			AmazonApplicationCloudingService.log.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final AmazonCloudedApplication euApplication = (AmazonCloudedApplication) application;

		/*
		 * Assert that configuration is Amazon-specific.
		 */
		if (!(configuration instanceof AmazonApplicationInstanceConfiguration)) {
			final String errorsMsg =
					"configuration must be of class " + AmazonApplicationInstanceConfiguration.class
							+ " but found " + configuration.getClass();
			AmazonApplicationCloudingService.log.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final AmazonApplicationInstanceConfiguration euConfiguration =
				(AmazonApplicationInstanceConfiguration) configuration;

		/*
		 * Assert that node is Amazon-specific.
		 */
		if (!(node instanceof AmazonCloudNode)) {
			final String errorsMsg =
					"node must be of class " + AmazonCloudNode.class + " but found " + configuration.getClass();
			AmazonApplicationCloudingService.log.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final AmazonCloudNode euNode = (AmazonCloudNode) node;

		final AmazonApplicationInstance inst =
				new AmazonApplicationInstance(this.genApplicationInstanceId(euApplication), euApplication,
						euConfiguration, euNode);

		this.applicationInstances.add(inst);

		/* 1. Deploy (if required) */
		if (!this.configuration.getDefaultApplicationDeploymentArtifact().isEmpty()) {
			final ExternalCommandExecuter executer =
					new ExternalCommandExecuter(this.configuration.isDummyModeEnabled());
			final AmazonCommand applicationDeployCommand =
					AmazonCommandFactory.getApplicationDeployCommand(this.configuration.getSSHPrivateKeyFile(),
							this.configuration.getSSHUserName(), this.configuration.getTomcatHome(),
							this.configuration.getDefaultApplicationDeploymentArtifact(), euNode.getIpAddress());
			executer.executeCommandWithEnv(applicationDeployCommand, this.configuration.getEC2toolsPath());
		}

		// try {
		// Thread.sleep(AmazonApplicationCloudingService.WAIT_SECONDS_AFTER_DEPLOY
		// * 1000);
		// } catch (final InterruptedException e) {
		// AmazonApplicationCloudingService.log.error(e.getMessage(), e);
		// }

		/* 2. Wait for application to become available */

		if (!this.waitUntilInstanceAvailable(euNode.getIpAddress(),
				this.configuration.getDefaultApplicationInstanceQueryPort(),
				this.configuration.getDefaultApplicationInstanceQueryPath(),
				this.configuration.getApplicationInstanceDeployPollPeriodSeconds(),
				this.configuration.getApplicationInstanceDeployMaxWaitTimeSeconds())) {
			final String errorMsg = "Deployed intance " + inst + " not available after deployment";
			AmazonApplicationCloudingService.log.error(errorMsg);
			throw new ApplicationCloudingServiceException(errorMsg);
		}

		/* 3. Add instance to load balancer */
		if (this.configuration.isLoadBalancerEnabled()
				&& !this.lbConnector.addHost(application.getName(), euNode.getIpAddress())) {
			final String errorMsg =
					"Failed to add host '" + euNode.getIpAddress() + "' for application '" + application.getName()
							+ "' in load balancer";
			AmazonApplicationCloudingService.log.error(errorMsg);
			// clean up broken state (to be correct, we should only role back
			// the deployment):
			this.undeployApplicationInstance(inst);
			throw new ApplicationCloudingServiceException(errorMsg);
		}

		this.eventNotifier.notifyDeployApplicationInstanceSuccess(euApplication, euConfiguration, euNode, inst);

		return inst;
	}

	final String WGET_SUCCESS_EXAMPLE =
			"--2011-01-13 12:34:37--  http://192.168.48.94:8080/jpetstore\n"
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
					+ "\n" + "2011-01-13 12:34:37 (80,8 MB/s) - »»jpetstore.3«« gespeichert [523/523]\n" + "\n";

	private boolean waitUntilInstanceAvailable(final String ipAddress, final int port, final String path,
			final int tryPeriodSeconds, final int maxWaitTimeSeconds) {
		final String url = ipAddress + ":" + port + "/" + path;
		final ExternalCommandExecuter executer = new ExternalCommandExecuter(this.configuration.isDummyModeEnabled());
		final AmazonCommand fetchInstanceWebSite = AmazonCommandFactory.getFetchWebSiteCommand(url);
		String wgetResult = "";

		int totalWaitTimeSeconds = 0;

		while (true) {
			if (wgetResult.contains("200 OK")) {
				AmazonApplicationCloudingService.log.info("url '" + "'" + url + " available after "
						+ totalWaitTimeSeconds + " seconds");
				return true;
			}

			if (totalWaitTimeSeconds > maxWaitTimeSeconds) {
				final String errorMsg = maxWaitTimeSeconds + " seconds try period elapsed to access url '" + url + "#";
				AmazonApplicationCloudingService.log.error(errorMsg);
				return false;
			}
			totalWaitTimeSeconds += tryPeriodSeconds;

			try {
				Thread.sleep(tryPeriodSeconds * 1000);
			} catch (final InterruptedException e) {
				final String errorMsg = "Interrupted: " + e.getMessage();
				AmazonApplicationCloudingService.log.error(errorMsg);
				return false;
			}

			wgetResult = executer.executeCommandWithEnv(fetchInstanceWebSite, this.configuration.getEC2toolsPath());
			if (this.configuration.isDummyModeEnabled()
					&& (((totalWaitTimeSeconds + tryPeriodSeconds) > maxWaitTimeSeconds) // (max.
																							// possible
																							// number
																							// of
																							// tries)
																							// )
					|| (maxWaitTimeSeconds > 5))) {
				wgetResult = this.WGET_SUCCESS_EXAMPLE;

			}
		}
	}

	/**
	 * Returns an ID for an {@link AmazonApplicationInstance} to be
	 * associated with the given {@link AmazonCloudedApplication}.
	 * 
	 * @param application
	 * @return
	 */
	private String genApplicationInstanceId(final AmazonCloudedApplication application) {
		return application.getName() + "--" + application.acquireInstanceId();
	}

	@Override
	public void undeployApplicationInstance(final IApplicationInstance instance)
			throws ApplicationCloudingServiceException {

		this.printDebugMsg("undeployApplicationInstance --- " + "instance: " + instance);

		final ICloudNode node = instance.getNode();

		/*
		 * Assert that node is Amazon-specific.
		 */
		if (!(node instanceof AmazonCloudNode)) {
			final String errorsMsg =
					"node must be of class " + AmazonCloudNode.class + " but found "
							+ this.configuration.getClass();
			AmazonApplicationCloudingService.log.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final AmazonCloudNode euNode = (AmazonCloudNode) node;

		this.applicationInstances.remove(instance);

		/* 1. Remove from load balancer */
		if (this.configuration.isLoadBalancerEnabled()
				&& !this.lbConnector.removeHost(instance.getApplication().getName(), euNode.getIpAddress())) {
			final String errorMsg =
					"Failed to remove host '" + euNode.getIpAddress() + "' for application '"
							+ instance.getApplication().getName() + "' from load balancer";
			AmazonApplicationCloudingService.log.error(errorMsg);
			// Continuing in order to avoid a broken state
			// throw new ApplicationCloudingServiceException(errorMsg);
		}

		/* 2. Undeploy */
		final ExternalCommandExecuter executer = new ExternalCommandExecuter(this.configuration.isDummyModeEnabled());
		final AmazonCommand applicationUndeployCommand =
				AmazonCommandFactory.getApplicationUndeployCommand("jpetstore.war", euNode.getIpAddress());
		executer.executeCommandWithEnv(applicationUndeployCommand, this.configuration.getEC2toolsPath());

		this.eventNotifier.notifyUndeployApplicationInstanceSuccess((AmazonApplicationInstance) instance);
	}

	/**
	 * Logs the given message if
	 * {@link IAmazonApplicationCloudingServiceConfiguration#isDebugEnabled()}
	 * for this service.
	 * 
	 * @param msg
	 */
	private final void printDebugMsg(final String msg) {
		if ((this.configuration != null) && this.configuration.isDebugEnabled()) {
			AmazonApplicationCloudingService.log.info(msg);
		}
	}

	@Override
	public ICloudNode lookupNode(final String name) {
		for (final ICloudNode node : this.allocatedNodes) {
			if (node.getName().equals(name)) {
				return node;
			}
		}
		return null;
	}

	@Override
	public ICloudedApplication lookupCloudedApplication(final String name) {
		for (final ICloudedApplication app : this.applications) {
			if (app.getName().equals(name)) {
				return app;
			}
		}
		return null;
	}

	@Override
	public ICloudNodeType lookupCloudNodeType(final String name) {
		for (final ICloudNodeType type : this.nodeTypes) {
			if (type.getName().equals(name)) {
				return type;
			}
		}
		return null;
	}

	@Override
	public IApplicationInstance lookupApplicationInstance(final ICloudedApplication cloudedApplication,
			final ICloudNode cloudNode) {
		for (final IApplicationInstance applicationInstance : this.applicationInstances) {
			if (applicationInstance.getApplication().equals(cloudedApplication)
					&& applicationInstance.getNode().equals(cloudNode)) {
				return applicationInstance;
			}
		}
		return null;
	}

	@Override
	public Collection<? extends ICloudNode> getCloudNodes() {
		return this.allocatedNodes;
	}

	@Override
	public Collection<? extends ICloudedApplication> getCloudedApplications() {
		return this.applications;
	}

	@Override
	public final Collection<? extends IApplicationInstance> getApplicationInstances() {
		return this.applicationInstances;
	}

	/**
	 * 
	 * @param l
	 */
	public void addEventListener(final IAmazonServiceEventListener l) {
		this.eventNotifier.addEventListener(l);
	}

	/**
	 * 
	 * @param l
	 * @return
	 */
	public boolean removeEventListener(final IAmazonServiceEventListener l) {
		return this.eventNotifier.removeEventListener(l);
	}
}
