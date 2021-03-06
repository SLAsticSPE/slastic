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

package kieker.tools.slastic.plugins.cloud.eucalyptus.service;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.tools.slastic.plugins.cloud.common.ExternalCommandExecuter;
import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstance;
import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstanceConfiguration;
import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNode;
import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNodeType;
import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudedApplication;
import kieker.tools.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudedApplicationConfiguration;
import kieker.tools.slastic.plugins.cloud.eucalyptus.service.configuration.EucalyptusApplicationCloudingServiceConfiguration;
import kieker.tools.slastic.plugins.cloud.eucalyptus.service.configuration.IEucalyptusApplicationCloudingServiceConfiguration;
import kieker.tools.slastic.plugins.cloud.eucalyptus.service.eucaToolsIntegration.EucalyptusCommand;
import kieker.tools.slastic.plugins.cloud.eucalyptus.service.eucaToolsIntegration.EucalyptusCommandFactory;
import kieker.tools.slastic.plugins.cloud.eucalyptus.service.loadBalancer.EucalyptusLoadBalancerConnector;
import kieker.tools.slastic.plugins.cloud.eucalyptus.service.logging.EucalyptusServiceEventNotifier;
import kieker.tools.slastic.plugins.cloud.eucalyptus.service.logging.IEucalyptusServiceEventListener;
import kieker.tools.slastic.plugins.cloud.model.IApplicationInstance;
import kieker.tools.slastic.plugins.cloud.model.IApplicationInstanceConfiguration;
import kieker.tools.slastic.plugins.cloud.model.ICloudNode;
import kieker.tools.slastic.plugins.cloud.model.ICloudNodeType;
import kieker.tools.slastic.plugins.cloud.model.ICloudedApplication;
import kieker.tools.slastic.plugins.cloud.model.ICloudedApplicationConfiguration;
import kieker.tools.slastic.plugins.cloud.service.ApplicationCloudingServiceException;
import kieker.tools.slastic.plugins.cloud.service.IApplicationCloudingService;

/**
 * This is the place to call the Eucalyptus tools.
 * 
 * @author Andre van Hoorn, Florian Fittkau
 * 
 */
public class EucalyptusApplicationCloudingService implements IApplicationCloudingService {

	private static final Log LOG = LogFactory.getLog(EucalyptusApplicationCloudingService.class);

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

	private final EucalyptusLoadBalancerConnector lbConnector;

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
	private EucalyptusApplicationCloudingService(final IEucalyptusApplicationCloudingServiceConfiguration config)
			throws ApplicationCloudingServiceException {
		this.configuration = config;

		/* Initialize the connector to the LoadBalancerServlet */
		this.lbConnector =
				new EucalyptusLoadBalancerConnector(this.configuration.getLoadBalancerServletURL(), false,
						EucalyptusApplicationCloudingService.WGET_LOG);
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
		for (final Entry<String, String> image : this.configuration.getEMIs().entrySet()) {
			this.nodeTypes.add(new EucalyptusCloudNodeType(image.getKey(), image.getValue()));
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

			this.allocatedNodes.add(new EucalyptusCloudNode(nodeName, nodeType, instanceId, ip, nodeName));
		}
	}

	/**
	 * 
	 * @throws ApplicationCloudingServiceException
	 */
	private void initApplications() throws ApplicationCloudingServiceException {
		for (final String appName : this.configuration.getInitialApplications()) {
			this.applications.add(new EucalyptusCloudedApplication(appName, new EucalyptusCloudedApplicationConfiguration()));
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

			final EucalyptusCloudedApplication app = (EucalyptusCloudedApplication) this.lookupCloudedApplication(appName);
			if (app == null) {
				throw new ApplicationCloudingServiceException("Failed to lookup application with name '" + appName + "'");
			}

			final EucalyptusCloudNode node = (EucalyptusCloudNode) this.lookupNode(nodeInstanceName);
			if (node == null) {
				throw new ApplicationCloudingServiceException("Failed to lookup node with name '" + nodeInstanceName + "'");
			}

			this.applicationInstances.add(
					new EucalyptusApplicationInstance(this.genApplicationInstanceId(app), app, new EucalyptusApplicationInstanceConfiguration(), node));
		}
	}

	/**
	 * Factory methods returning an {@link EucalyptusApplicationCloudingService} configured according to the contents of the given configuration file.
	 * 
	 * @param configurationFileName
	 *            path to the configuration file
	 * @return
	 */
	// TODO: We might re-throw a possible Exception thrown by
	// #loadEucalyptusConfiguration.
	public static EucalyptusApplicationCloudingService createService(final String configurationFile) {
		final IEucalyptusApplicationCloudingServiceConfiguration config =
				EucalyptusApplicationCloudingServiceConfiguration.createConfiguration(configurationFile);
		return EucalyptusApplicationCloudingService.createService(config);
	}

	/**
	 * Factory method returning an {@link EucalyptusApplicationCloudingService} based on the given {@link EucalyptusApplicationCloudingServiceConfiguration}.
	 * 
	 * @param config
	 * @return the {@link EucalyptusApplicationCloudingServiceConfiguration};
	 *         null if an error occurred
	 */
	// TODO: We might re-throw a possible Exception thrown by
	// #loadEucalyptusConfiguration.
	public static EucalyptusApplicationCloudingService createService(final IEucalyptusApplicationCloudingServiceConfiguration config) {
		EucalyptusApplicationCloudingService svc = null;

		try {
			svc = new EucalyptusApplicationCloudingService(config);
		} catch (final ApplicationCloudingServiceException e) {
			LOG.error("Failed to create EucalyptusApplicationCloudingService: " + e.getMessage(), e);
		}

		return svc;
	}

	@Override
	public Collection<EucalyptusCloudNodeType> getNodeTypes() {
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
		 * Assert that node is Eucalyptus-specific.
		 */
		if (!(type instanceof EucalyptusCloudNodeType)) {
			final String errorsMsg = "type must be of class " + EucalyptusCloudNodeType.class + " but found " + type.getClass();
			LOG.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final EucalyptusCloudNodeType euType = (EucalyptusCloudNodeType) type;

		final ExternalCommandExecuter executer = new ExternalCommandExecuter(this.configuration.isDummyModeEnabled());
		final EucalyptusCommand allocateNodeCommand =
				EucalyptusCommandFactory.getAllocateNodeCommand(
						euType.getEmiImageName(), this.configuration.getEucalyptusKeyName(), this.configuration.getEucalyptusGroup());

		String result = executer.executeCommandWithEnv(allocateNodeCommand, this.configuration.getEucatoolsPath());

		final String instanceID;
		String ipAddress;

		/* 1. Start instance and determine IP address */
		if (this.configuration.isDummyModeEnabled()) {
			instanceID = Integer.toString(this.nextDummyEucaInstanceId++);
			ipAddress = "110.110.110." + this.nextDummyEucaIPCount++;
		} else {
			instanceID = this.getInstanceIDFromAllocateNodeResult(result);

			final EucalyptusCommand descibeInstanceCommand = EucalyptusCommandFactory.getDescribeNodeCommand(instanceID);

			ipAddress = "0.0.0.0";
			int secondCounter = 0;

			while ((ipAddress.equals("")) || ipAddress.equals("0.0.0.0")) {
				result = executer.executeCommandWithEnv(descibeInstanceCommand, this.configuration.getEucatoolsPath());
				ipAddress = this.getIPAddressFromDescribeNodeResult(result);

				try {
					Thread.sleep(this.configuration.getNodeAllocationPollPeriodSeconds() * 1000);
					secondCounter += this.configuration.getNodeAllocationPollPeriodSeconds();
				} catch (final InterruptedException e) {
					LOG.error("Waiting for node startup failed: " + e.getMessage(), e);
				}

				if (secondCounter > this.configuration.getNodeAllocationMaxWaitTimeSeconds()) {
					throw new ApplicationCloudingServiceException(
							this.configuration.getNodeAllocationMaxWaitTimeSeconds() + " seconds timeout for allocateNode reached.");
				}
			}
		}

		final EucalyptusCommand setHostname =
				EucalyptusCommandFactory.getStartRemoteCommandCommand(this.configuration.getSSHPrivateKeyFile(),
						this.configuration.getSSHUserName(), ipAddress,
						"/etc/init.d/hostname.sh");
		executer.executeCommandWithEnv(setHostname,
				this.configuration.getEucatoolsPath());

		/* 2. Determine hostname */

		final EucalyptusCommand fetchHostnameCommand =
				EucalyptusCommandFactory.getFetchHostnameCommand(this.configuration.getSSHPrivateKeyFile(), this.configuration.getSSHUserName(), ipAddress);
		String hostResult = executer.executeCommandWithEnv(fetchHostnameCommand, this.configuration.getEucatoolsPath());

		if (this.configuration.isDummyModeEnabled()) {
			hostResult = "dummy-hostname-" + this.nextHostname++;
		}

		final String hostname = this.getHostnameFromResult(hostResult);

		final EucalyptusCloudNode node = new EucalyptusCloudNode(name, type, instanceID, ipAddress, hostname);

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
		 * Assert that node is Eucalyptus-specific.
		 */
		if (!(node instanceof EucalyptusCloudNode)) {
			final String errorsMsg = "node must be of class " + EucalyptusCloudNode.class + " but found " + node.getClass();
			LOG.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final EucalyptusCloudNode euNode = (EucalyptusCloudNode) node;

		final ExternalCommandExecuter executer = new ExternalCommandExecuter(this.configuration.isDummyModeEnabled());
		final EucalyptusCommand deallocateNodeCommand = EucalyptusCommandFactory.getDeallocateNodeCommand(euNode.getInstanceID());

		if (!this.configuration.isDummyModeEnabled()) {
			// spawn execution of shutdown command with given delay
			executer.executeCommandWithEnvAndDelayAsync(
					deallocateNodeCommand, this.configuration.getEucatoolsPath(), this.configuration.getNodeShutDownDelaySeconds() * 1000);
		} else {
			LOG.warn("Not executing " + deallocateNodeCommand);
		}

		this.eventNotifier.notifyDeallocateNodeSuccess(euNode);
	}

	/**
	 * The given configuration must be of class {@link EucalyptusCloudedApplicationConfiguration}.
	 */
	@Override
	public ICloudedApplication createAndRegisterCloudedApplication(final String name,
			final ICloudedApplicationConfiguration configuration) throws ApplicationCloudingServiceException {

		this.printDebugMsg("createAndRegisterCloudedApplication --- " + "name: " + name + "; configuration: " + configuration);

		/*
		 * Assert that configuration is Eucalyptus-specific.
		 */
		if (!(configuration instanceof EucalyptusCloudedApplicationConfiguration)) {
			final String errorsMsg = "configuration must be of class " + EucalyptusCloudedApplicationConfiguration.class + " but found " + configuration.getClass();
			LOG.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final EucalyptusCloudedApplicationConfiguration euConfiguration = (EucalyptusCloudedApplicationConfiguration) configuration;

		final EucalyptusCloudedApplication app = new EucalyptusCloudedApplication(name, euConfiguration);

		/* Register application in load balancer */
		if (!this.lbConnector.createContext("JPetStore")) {
			final String errorMsg = "Failed to register application '" + app.getName() + "' in load balancer";
			LOG.error(errorMsg);
			throw new ApplicationCloudingServiceException(errorMsg);
		}

		/* Store application in internal table */
		this.applications.add(app);

		// Notice that no calls to the Eucalyptus tools required.

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
			final String errorMsg = "Failed to deregister application '" + application.getName() + "' from load balancer";
			LOG.error(errorMsg);
			throw new ApplicationCloudingServiceException(errorMsg);
		}

		/* Remove application from internal table */
		this.applications.remove(application);

		// Notice that no calls to the Eucalyptus tools required.

		this.eventNotifier.notifyRemoveCloudedApplicationSuccess((EucalyptusCloudedApplication) application);
	}

	@Override
	public IApplicationInstance deployApplicationInstance(final ICloudedApplication application,
			final IApplicationInstanceConfiguration configuration, final ICloudNode node)
			throws ApplicationCloudingServiceException {

		this.printDebugMsg("deployApplicationInstance --- " + "application: " + application + "; configuration: " + configuration);

		/*
		 * Assert that application is Eucalyptus-specific.
		 */
		if (!(application instanceof EucalyptusCloudedApplication)) {
			final String errorsMsg = "application must be of class " + EucalyptusCloudedApplication.class + " but found " + configuration.getClass();
			LOG.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final EucalyptusCloudedApplication euApplication = (EucalyptusCloudedApplication) application;

		/*
		 * Assert that configuration is Eucalyptus-specific.
		 */
		if (!(configuration instanceof EucalyptusApplicationInstanceConfiguration)) {
			final String errorsMsg = "configuration must be of class " + EucalyptusApplicationInstanceConfiguration.class + " but found " + configuration.getClass();
			LOG.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final EucalyptusApplicationInstanceConfiguration euConfiguration =
				(EucalyptusApplicationInstanceConfiguration) configuration;

		/*
		 * Assert that node is Eucalyptus-specific.
		 */
		if (!(node instanceof EucalyptusCloudNode)) {
			final String errorsMsg = "node must be of class " + EucalyptusCloudNode.class + " but found " + configuration.getClass();
			LOG.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final EucalyptusCloudNode euNode = (EucalyptusCloudNode) node;

		final EucalyptusApplicationInstance inst =
				new EucalyptusApplicationInstance(this.genApplicationInstanceId(euApplication), euApplication, euConfiguration, euNode);

		this.applicationInstances.add(inst);

		final ExternalCommandExecuter executer =
				new ExternalCommandExecuter(this.configuration.isDummyModeEnabled());

		/* 1. Deploy (if required) */
		if (!this.configuration.getDefaultApplicationDeploymentArtifact().isEmpty()) {
			final EucalyptusCommand applicationDeployCommand =
					EucalyptusCommandFactory.getApplicationDeployCommand(this.configuration.getSSHPrivateKeyFile(),
							this.configuration.getSSHUserName(), this.configuration.getTomcatHome(),
							this.configuration.getDefaultApplicationDeploymentArtifact(), euNode.getIpAddress());
			executer.executeCommandWithEnv(applicationDeployCommand, this.configuration.getEucatoolsPath());
		}

		try {
			Thread.sleep(15 * 1000); // why?
		} catch (final InterruptedException e) {
			LOG.error(e.getMessage(), e);
		}

		/* 2. Start tomcat */

		final EucalyptusCommand startTomcatCommand =
				EucalyptusCommandFactory.getStartTomcatCommand(this.configuration.getSSHPrivateKeyFile(),
						this.configuration.getSSHUserName(), euNode.getIpAddress(),
						"/etc/init.d/tomcat.sh");
		executer.executeCommandWithEnv(startTomcatCommand,
				this.configuration.getEucatoolsPath());

		// try {
		// Thread.sleep(WAIT_SECONDS_AFTER_DEPLOY
		// * 1000);
		// } catch (final InterruptedException e) {
		// log.error(e.getMessage(), e);
		// }

		/* 3. Wait for application to become available */

		if (!this.waitUntilInstanceAvailable(euNode.getIpAddress(),
				this.configuration.getDefaultApplicationInstanceQueryPort(),
				this.configuration.getDefaultApplicationInstanceQueryPath(),
				this.configuration.getApplicationInstanceDeployPollPeriodSeconds(),
				this.configuration.getApplicationInstanceDeployMaxWaitTimeSeconds())) {
			final String errorMsg = "Deployed intance " + inst + " not available after deployment";
			LOG.error(errorMsg);
			throw new ApplicationCloudingServiceException(errorMsg);
		}

		/* 4. Add instance to load balancer */
		if (this.configuration.isLoadBalancerEnabled() && !this.lbConnector.addHost(application.getName(), euNode.getIpAddress())) {
			final String errorMsg =
					"Failed to add host '" + euNode.getIpAddress() + "' for application '" + application.getName() + "' in load balancer";
			LOG.error(errorMsg);
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

	private boolean waitUntilInstanceAvailable(final String ipAddress, final int port, final String path, final int tryPeriodSeconds, final int maxWaitTimeSeconds) {
		final String url = ipAddress + ":" + port + "/" + path;
		System.out.println("Querying " + url);
		final ExternalCommandExecuter executer = new ExternalCommandExecuter(this.configuration.isDummyModeEnabled());
		final EucalyptusCommand fetchInstanceWebSite = EucalyptusCommandFactory.getFetchWebSiteCommand(url);
		String wgetResult = "";

		int totalWaitTimeSeconds = 0;

		while (true) {
			if (wgetResult.contains("200 OK")) {
				LOG.info("url '" + "'" + url + " available after " + totalWaitTimeSeconds + " seconds");
				return true;
			}

			if (totalWaitTimeSeconds > maxWaitTimeSeconds) {
				final String errorMsg = maxWaitTimeSeconds + " seconds try period elapsed to access url '" + url + "#";
				LOG.error(errorMsg);
				return false;
			}
			totalWaitTimeSeconds += tryPeriodSeconds;

			try {
				Thread.sleep(tryPeriodSeconds * 1000);
			} catch (final InterruptedException e) {
				final String errorMsg = "Interrupted: " + e.getMessage();
				LOG.error(errorMsg);
				return false;
			}

			wgetResult = executer.executeCommandWithEnv(fetchInstanceWebSite, this.configuration.getEucatoolsPath());
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
	 * Returns an ID for an {@link EucalyptusApplicationInstance} to be
	 * associated with the given {@link EucalyptusCloudedApplication}.
	 * 
	 * @param application
	 * @return
	 */
	private String genApplicationInstanceId(final EucalyptusCloudedApplication application) {
		return application.getName() + "--" + application.acquireInstanceId();
	}

	@Override
	public void undeployApplicationInstance(final IApplicationInstance instance) throws ApplicationCloudingServiceException {

		this.printDebugMsg("undeployApplicationInstance --- " + "instance: " + instance);

		final ICloudNode node = instance.getNode();

		/*
		 * Assert that node is Eucalyptus-specific.
		 */
		if (!(node instanceof EucalyptusCloudNode)) {
			final String errorsMsg = "node must be of class " + EucalyptusCloudNode.class + " but found " + this.configuration.getClass();
			LOG.error(errorsMsg);
			throw new ApplicationCloudingServiceException(errorsMsg);
		}
		final EucalyptusCloudNode euNode = (EucalyptusCloudNode) node;

		this.applicationInstances.remove(instance);

		/* 1. Remove from load balancer */

		if (this.configuration.isLoadBalancerEnabled()
				&& !this.lbConnector.removeHost(instance.getApplication().getName(), euNode.getIpAddress())) {
			final String errorMsg =
					"Failed to remove host '" + euNode.getIpAddress() + "' for application '" + instance.getApplication().getName() + "' from load balancer";
			LOG.error(errorMsg);
			// Continuing in order to avoid a broken state
			// throw new ApplicationCloudingServiceException(errorMsg);
		}

		/* 2. Undeploy */
		final ExternalCommandExecuter executer = new ExternalCommandExecuter(this.configuration.isDummyModeEnabled());
		final EucalyptusCommand applicationUndeployCommand = EucalyptusCommandFactory.getApplicationUndeployCommand("jpetstore.war", euNode.getIpAddress());
		executer.executeCommandWithEnv(applicationUndeployCommand, this.configuration.getEucatoolsPath());

		this.eventNotifier.notifyUndeployApplicationInstanceSuccess((EucalyptusApplicationInstance) instance);
	}

	/**
	 * Logs the given message if {@link IEucalyptusApplicationCloudingServiceConfiguration#isDebugEnabled()} for this service.
	 * 
	 * @param msg
	 */
	private final void printDebugMsg(final String msg) {
		if ((this.configuration != null) && this.configuration.isDebugEnabled()) {
			LOG.info(msg);
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
	public IApplicationInstance lookupApplicationInstance(final ICloudedApplication cloudedApplication, final ICloudNode cloudNode) {
		for (final IApplicationInstance applicationInstance : this.applicationInstances) {
			if (applicationInstance.getApplication().equals(cloudedApplication) && applicationInstance.getNode().equals(cloudNode)) {
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
	public void addEventListener(final IEucalyptusServiceEventListener l) {
		this.eventNotifier.addEventListener(l);
	}

	/**
	 * 
	 * @param l
	 * @return
	 */
	public boolean removeEventListener(final IEucalyptusServiceEventListener l) {
		return this.eventNotifier.removeEventListener(l);
	}
}
