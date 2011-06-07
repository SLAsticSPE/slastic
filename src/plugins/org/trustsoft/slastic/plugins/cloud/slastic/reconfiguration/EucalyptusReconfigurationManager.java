package org.trustsoft.slastic.plugins.cloud.slastic.reconfiguration;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.*;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.*;
import org.trustsoft.slastic.plugins.cloud.service.ApplicationCloudingServiceException;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.reconfiguration.AbstractReconfigurationManagerComponent;
import org.trustsoft.slastic.reconfiguration.ReconfigurationException;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class EucalyptusReconfigurationManager extends
		AbstractReconfigurationManagerComponent {
	private static final Log log = LogFactory
			.getLog(EucalyptusReconfigurationManager.class);

	// TODO: turn into properties
	private static final String WEBSTORE_REST_NAME = "webstoreRest";
	private static final String WEBSTORE_HOTSPOTS_NAME = "webstoreHotSpots";
	private static final String POSTERITA_1_NAME = "posterita1";
	private static final String POSTERITA_2_NAME = "posterita2";
	private static final String POSTERITA_3_NAME = "posterita3";

	private static final String NODE_TYPE_NAME = "adempiere";

	private volatile EucalyptusCloudedApplication euWebstoreRestApp;
	private volatile EucalyptusCloudedApplication euWebstoreHotSpotsApp;
	private volatile EucalyptusCloudedApplication euPosterita1App;
	private volatile EucalyptusCloudedApplication euPosterita2App;
	private volatile EucalyptusCloudedApplication euPosterita3App;
	private volatile EucalyptusCloudNodeType euAdempiereNodeType;

	private static final String PROPERTY_CONFIGURATIONFN_NAME = "configFile";

	private static final String EUCALYPTUS_EVENT_LOG = "eucalyptus-events.log";

	private volatile PrintWriter eucalyptusEventWriter = null;

	/**
	 * Maps {@link ExecutionContainer}s to the corresponding
	 * {@link EucalyptusCloudNode}s
	 */
	private final HashMap<ExecutionContainer, EucalyptusCloudNode> allocatedNodesMapping = new HashMap<ExecutionContainer, EucalyptusCloudNode>();

	/**
	 * Maps {@link DeploymentComponent}s to the corresponding
	 * {@link EucalyptusApplicationInstance}s
	 */
	private final HashMap<DeploymentComponent, EucalyptusApplicationInstance> deploymentComponentMapping = new HashMap<DeploymentComponent, EucalyptusApplicationInstance>();

	/**
	 * The actual reconfigurations are performed by this
	 * {@link EucalyptusApplicationCloudingService}.
	 */
	private volatile EucalyptusApplicationCloudingService eucalyptusApplicationCloudingService;

	@Override
	public void doReconfiguration(final SLAsticReconfigurationPlan arg0)
			throws ReconfigurationException {
		throw new UnsupportedOperationException(
				"doReconfiguration(final SLAsticReconfigurationPlan arg0) not supported");
	}

	/**
	 * Initializes the {@link AbstractEucalyptusApplicationCloudingService}
	 * based on an {@link IEucalyptusApplicationCloudingServiceConfiguration}
	 * provided by a {@link Properties} file.
	 */
	@Override
	public boolean init() {
		EucalyptusApplicationCloudingService eucalyptusApplicationCloudingService;
		try {
			/* Determine the path of the Eucalyptus configuration file. */
			final String configurationFile = super
					.getInitProperty(EucalyptusReconfigurationManager.PROPERTY_CONFIGURATIONFN_NAME);
			if ((configurationFile == null)
					|| (configurationFile.length() == 0)) {
				final String errorMsg = "Invalid or missing value for property '"
						+ EucalyptusReconfigurationManager.PROPERTY_CONFIGURATIONFN_NAME
						+ "'";
				EucalyptusReconfigurationManager.log.error(errorMsg);
				return false;
			}

			/* Create an instance of the Eucalyptus service */
			eucalyptusApplicationCloudingService = EucalyptusApplicationCloudingService
					.createService(configurationFile);
		} catch (final Exception exc) {
			EucalyptusReconfigurationManager.log.error("Error initializing :"
					+ exc.getMessage(), exc);
			eucalyptusApplicationCloudingService = null;
		}

		this.eucalyptusApplicationCloudingService = eucalyptusApplicationCloudingService;
		return this.eucalyptusApplicationCloudingService != null;
	}

	// Part of hack #10
	private volatile ConcurrentHashMap<String, ExecutionContainer> containerNameMapping;

	@Override
	public boolean execute() {
		try {
			{
				// Part of hack #10
				containerNameMapping = ((ModelManager) getControlComponent()
						.getModelManager())
						.getExecutionEnvironmentModelManager().containerNameMapping;
			}

			/* Create print writer for eucalyptus events */
			final File euEventsFile = getComponentContext()
					.createFileInContextDir(
							EucalyptusReconfigurationManager.EUCALYPTUS_EVENT_LOG);
			eucalyptusEventWriter = new PrintWriter(euEventsFile);
			if (eucalyptusEventWriter == null) {
				EucalyptusReconfigurationManager.log
						.error("Failed to create event logger");
				return false;
			}

			/* Register logger */
			final ICurrentTimeProvider currentTimeProvider = new SLAsticCurrentTimeProvider();
			final EucalyptusEventLogger euEventLogger = new EucalyptusEventLogger(
					eucalyptusEventWriter);
			eucalyptusApplicationCloudingService
					.addEventListener(euEventLogger);

			/* Create and register the applications */
			final EucalyptusCloudedApplicationConfiguration euAppConfig = new EucalyptusCloudedApplicationConfiguration();
			euWebstoreRestApp = (EucalyptusCloudedApplication) eucalyptusApplicationCloudingService
					.createAndRegisterCloudedApplication(
							EucalyptusReconfigurationManager.WEBSTORE_REST_NAME,
							euAppConfig);

			euWebstoreHotSpotsApp = (EucalyptusCloudedApplication) eucalyptusApplicationCloudingService
					.createAndRegisterCloudedApplication(
							EucalyptusReconfigurationManager.WEBSTORE_HOTSPOTS_NAME,
							euAppConfig);

			euPosterita1App = (EucalyptusCloudedApplication) eucalyptusApplicationCloudingService
					.createAndRegisterCloudedApplication(
							EucalyptusReconfigurationManager.POSTERITA_1_NAME,
							euAppConfig);

			euPosterita2App = (EucalyptusCloudedApplication) eucalyptusApplicationCloudingService
					.createAndRegisterCloudedApplication(
							EucalyptusReconfigurationManager.POSTERITA_2_NAME,
							euAppConfig);

			euPosterita3App = (EucalyptusCloudedApplication) eucalyptusApplicationCloudingService
					.createAndRegisterCloudedApplication(
							EucalyptusReconfigurationManager.POSTERITA_3_NAME,
							euAppConfig);

			/* Lookup the tomcat node type */
			final Collection<EucalyptusCloudNodeType> euNodeTypes = eucalyptusApplicationCloudingService
					.getNodeTypes();
			for (final EucalyptusCloudNodeType nt : euNodeTypes) {
				if (nt.getName().equals(
						EucalyptusReconfigurationManager.NODE_TYPE_NAME)) {
					euAdempiereNodeType = nt;
				}
			}
		} catch (final Exception exc) {
			EucalyptusReconfigurationManager.log
					.error("Failed to create application: " + exc.getMessage());
			return false;
		}

		return true;
	}

	@Override
	public void terminate(final boolean arg0) {
		try {
			if ((euWebstoreRestApp != null)
					&& (eucalyptusApplicationCloudingService != null)) {
				eucalyptusApplicationCloudingService
						.removeCloudedApplication(euWebstoreRestApp);
			}
			if ((euWebstoreHotSpotsApp != null)
					&& (eucalyptusApplicationCloudingService != null)) {
				eucalyptusApplicationCloudingService
						.removeCloudedApplication(euWebstoreHotSpotsApp);
			}
			if ((euPosterita1App != null)
					&& (eucalyptusApplicationCloudingService != null)) {
				eucalyptusApplicationCloudingService
						.removeCloudedApplication(euPosterita1App);
			}
			if ((euPosterita2App != null)
					&& (eucalyptusApplicationCloudingService != null)) {
				eucalyptusApplicationCloudingService
						.removeCloudedApplication(euPosterita2App);
			}
			if ((euPosterita3App != null)
					&& (eucalyptusApplicationCloudingService != null)) {
				eucalyptusApplicationCloudingService
						.removeCloudedApplication(euPosterita3App);
			}
		} catch (final Exception exc) {
			EucalyptusReconfigurationManager.log
					.error("Failed to remove application: " + exc.getMessage());
			return;
		} finally {
			if (eucalyptusEventWriter != null) {
				eucalyptusEventWriter.close();
			}
		}
	}

	@Override
	protected boolean concreteReplicateComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer toExecutionContainer,
			final DeploymentComponent resDeploymentComponent) {
		boolean success = false;

		try {
			// TODO: add some intermediate checking of return values ...
			final EucalyptusApplicationInstanceConfiguration config = new EucalyptusApplicationInstanceConfiguration();
			final EucalyptusCloudNode dstNode = allocatedNodesMapping
					.get(toExecutionContainer);
			final EucalyptusApplicationInstance appInstance = (EucalyptusApplicationInstance) eucalyptusApplicationCloudingService
					.deployApplicationInstance(euApplication, config, dstNode);
			if (appInstance != null) {
				success = true;
				deploymentComponentMapping.put(resDeploymentComponent,
						appInstance);
			} else {
				EucalyptusReconfigurationManager.log
						.error("appInstance is null");
				success = false;
			}
		} catch (final ApplicationCloudingServiceException e) {
			EucalyptusReconfigurationManager.log.error(e.getMessage(), e);
			success = false;
		}
		return success;
	}

	@Override
	protected boolean concreteMigrateComponent(
			final DeploymentComponent deploymentComponent,
			final ExecutionContainer destination,
			final DeploymentComponent resDeploymentComponent) {
		boolean successReplication = false;
		boolean successDereplication = false;

		successReplication = concreteReplicateComponent(
				deploymentComponent.getAssemblyComponent(), destination,
				resDeploymentComponent);
		if (!successReplication) {
			EucalyptusReconfigurationManager.log
					.error("concreteReplicateComponent(..) failed");
		} else {
			successDereplication = concreteDereplicateComponent(deploymentComponent);
		}

		if (!successDereplication) {
			EucalyptusReconfigurationManager.log
					.error("concreteDereplicateComponent(..) failed");
		}

		return successReplication && successDereplication;
	}

	@Override
	protected boolean concreteDereplicateComponent(
			final DeploymentComponent deploymentComponent) {
		boolean success = false;

		try {
			EucalyptusReconfigurationManager.log
					.info("concreteDereplicateComponent");

			final EucalyptusApplicationInstance appInstance = deploymentComponentMapping
					.get(deploymentComponent);

			if (appInstance == null) {
				final String errorMsg = "appInstance for "
						+ deploymentComponent + " is null";
				EucalyptusReconfigurationManager.log.error(errorMsg);
				return false;
			}

			// TODO: add boolean return value for exit status?
			eucalyptusApplicationCloudingService
					.undeployApplicationInstance(appInstance);

			deploymentComponentMapping.remove(deploymentComponent);
			success = true;
		} catch (final ApplicationCloudingServiceException e) {
			EucalyptusReconfigurationManager.log.error(e.getMessage(), e);
			success = false;
		}
		return success;
	}

	@Override
	protected boolean concreteAllocateExecutionContainer(
			final ExecutionContainerType executionContainerType,
			final ExecutionContainer resExecutionContainer) {
		boolean success = false;

		try {
			final String fqNodeName = NameUtils.createFQName(
					resExecutionContainer.getPackageName(),
					resExecutionContainer.getName());
			final EucalyptusCloudNode euNode = (EucalyptusCloudNode) eucalyptusApplicationCloudingService
					.allocateNode(fqNodeName, euAdempiereNodeType);

			if (euNode == null) {
				EucalyptusReconfigurationManager.log
						.error("allocateNode(..) failed");
				success = false;
			} else {
				success = true;
				allocatedNodesMapping.put(resExecutionContainer, euNode);

				// HACK:
				containerNameMapping.put(euNode.getHostname(),
						resExecutionContainer);
				EucalyptusReconfigurationManager.log
						.info("Added hostname mapping " + euNode.getHostname()
								+ " x " + resExecutionContainer);
				//
			}
		} catch (final ApplicationCloudingServiceException e) {
			EucalyptusReconfigurationManager.log.error(e.getMessage(), e);
			success = false;
		}
		return success;
	}

	@Override
	protected boolean concreteDeallocateExecutionContainer(
			final ExecutionContainer executionContainer) {
		boolean success = false;

		try {
			EucalyptusReconfigurationManager.log
					.info("concreteDeallocateExecutionContainer");

			final EucalyptusCloudNode euNode = allocatedNodesMapping
					.get(executionContainer);

			if (euNode == null) {
				EucalyptusReconfigurationManager.log
						.error("Failed to lookup node");
				success = false;
			} else {
				eucalyptusApplicationCloudingService.deallocateNode(euNode);

				if (allocatedNodesMapping.remove(executionContainer) == null) {
					EucalyptusReconfigurationManager.log
							.error("Failed to remove node " + euNode
									+ " from internal table");
					return false;
				} else {
					success = true;
				}
			}
		} catch (final ApplicationCloudingServiceException e) {
			EucalyptusReconfigurationManager.log.error(e.getMessage(), e);
			success = false;
		}
		return success;
	}

	private class EucalyptusEventLogger extends
			AbstractEucalyptusServiceEventLogger {

		private final PrintWriter pw;

		public EucalyptusEventLogger(final PrintWriter pw) {
			super(new SLAsticCurrentTimeProvider());
			this.pw = pw;
		}

		@Override
		protected void logEvent(final String eventMsg) {
			pw.println(eventMsg);
			pw.flush();
		}

	}

	private class SLAsticCurrentTimeProvider implements ICurrentTimeProvider {

		@Override
		public long getCurrentTimeMillis() {
			return getControlComponent().getCurrentTimeMillis();
		}

	}

	@Override
	protected DeploymentComponent createPreliminaryDeploymentComponentInModel(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer executionContainer) {
		// TODO: set preliminary flag or alike?
		return ((ModelManager) getControlComponent().getModelManager())
				.getComponentDeploymentModelManager()
				.createAndRegisterDeploymentComponent(assemblyComponent,
						executionContainer);
	}

	@Override
	protected ExecutionContainer createPreliminaryExecutionContainerInModel(
			final String fullyQualifiedName,
			final ExecutionContainerType executionContainerType) {
		return ((ModelManager) getControlComponent().getModelManager())
				.getExecutionEnvironmentModelManager()
				.createAndRegisterExecutionContainer(fullyQualifiedName,
						executionContainerType);
	}

	@Override
	protected boolean deletePreliminaryDeploymentComponentFromModel(
			final DeploymentComponent deploymentComponent) {
		return ((ModelManager) getControlComponent().getModelManager())
				.getComponentDeploymentModelManager()
				.deleteDeploymentComponent(deploymentComponent);
	}

	@Override
	protected boolean deletePreliminaryExecutionContainerFromModel(
			final ExecutionContainer executionContainer) {
		return ((ModelManager) getControlComponent().getModelManager())
				.getExecutionEnvironmentModelManager()
				.deallocateExecutionContainer(executionContainer);

	}

	@Override
	protected boolean deleteDeploymentComponentFromModel(
			final DeploymentComponent deploymentComponent) {
		EucalyptusReconfigurationManager.log
				.info("deleteDeploymentComponentFromModel(...)");
		return ((ModelManager) getControlComponent().getModelManager())
				.getComponentDeploymentModelManager()
				.deleteDeploymentComponent(deploymentComponent);
	}

	@Override
	protected boolean deleteExecutionContainerFromModel(
			final ExecutionContainer executionContainer) {
		EucalyptusReconfigurationManager.log
				.info("deleteExecutionContainerFromModel");
		return ((ModelManager) getControlComponent().getModelManager())
				.getExecutionEnvironmentModelManager()
				.deallocateExecutionContainer(executionContainer);
	}
}
