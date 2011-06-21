package org.trustsoft.slastic.plugins.cloud.slastic.reconfiguration;

import java.io.File;
import java.io.PrintWriter;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.*;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.EucalyptusApplicationCloudingService;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.ICurrentTimeProvider;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.configuration.IEucalyptusApplicationCloudingServiceConfiguration;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.logging.AbstractEucalyptusServiceEventLogger;
import org.trustsoft.slastic.plugins.cloud.service.ApplicationCloudingServiceException;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.model.arch2implMapping.Arch2ImplNameMappingManager.EntityType;
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
	// private static final String APP_NAME = "JPetStore";
	private static final String WEBSTORE_REST_NAME = "webstoreRest";
	private static final String WEBSTORE_HOTSPOTS_NAME = "webstoreHotSpots";
	private static final String POSTERITA_1_NAME = "posterita1";
	private static final String POSTERITA_2_NAME = "posterita2";
	private static final String POSTERITA_3_NAME = "posterita3";

	private static final String NODE_TYPE_NAME = "adempiere";

	// private volatile EucalyptusCloudedApplication euApplication;

	private static final String PROPERTY_CONFIGURATIONFN_NAME = "configFile";

	private static final String PROPERTY_DEFAULT_NODE_TYPE_NAME = "defaultEucaNodeType";

	private static final String EUCALYPTUS_EVENT_LOG = "eucalyptus-events.log";

	private volatile PrintWriter eucalyptusEventWriter = null;
	private volatile EucalyptusCloudNodeType euDefaultNodeType;

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
			{ /* Initialize the Eucalyptus service */
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
			}

			{/* Lookup the default node type */
				final String defaultNodeTypeName = super
						.getInitProperty(EucalyptusReconfigurationManager.PROPERTY_DEFAULT_NODE_TYPE_NAME);
				if (defaultNodeTypeName == null) {
					EucalyptusReconfigurationManager.log
							.error("Invalid or missing value for property '"
									+ EucalyptusReconfigurationManager.PROPERTY_DEFAULT_NODE_TYPE_NAME
									+ "'");
					return false;
				}

				euDefaultNodeType = (EucalyptusCloudNodeType) eucalyptusApplicationCloudingService
						.lookupCloudNodeType(defaultNodeTypeName);
				if (euDefaultNodeType == null) {
					EucalyptusReconfigurationManager.log
							.error("Failed to lookup default node type '"
									+ defaultNodeTypeName + "'");
					return false;
				}
			}
		} catch (final Exception exc) {
			EucalyptusReconfigurationManager.log.error("Error initializing :"
					+ exc.getMessage(), exc);
			eucalyptusApplicationCloudingService = null;
		}

		this.eucalyptusApplicationCloudingService = eucalyptusApplicationCloudingService;
		return this.eucalyptusApplicationCloudingService != null;
	}

	private volatile ModelManager modelManager;

	@Override
	public boolean execute() {
		try {
			modelManager = (ModelManager) getControlComponent()
					.getModelManager();

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
			// final ICurrentTimeProvider currentTimeProvider =
			// new SLAsticCurrentTimeProvider();
			final EucalyptusEventLogger euEventLogger = new EucalyptusEventLogger(
					eucalyptusEventWriter);
			eucalyptusApplicationCloudingService
					.addEventListener(euEventLogger);
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
			// TODO: remove app?
			// if ((this.euApplication != null)
			// && (this.eucalyptusApplicationCloudingService != null)) {
			// this.eucalyptusApplicationCloudingService
			// .removeCloudedApplication(this.euApplication);
			// }
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

	/**
	 * 
	 * @param assemblyComponent
	 * @return
	 */
	private EucalyptusCloudedApplication eucaApplicationForAssemblyComponet(
			final AssemblyComponent assemblyComponent) {
		final String fqAssemblyComponentName = NameUtils
				.createFQName(assemblyComponent.getPackageName(),
						assemblyComponent.getName());
		final String eucaApplicationName = modelManager
				.getArch2ImplNameMappingManager().lookupImplName4ArchName(
						EntityType.ASSEMBLY_COMPONENT, fqAssemblyComponentName);

		final EucalyptusCloudedApplication eucalyptusCloudedApplication = (EucalyptusCloudedApplication) eucalyptusApplicationCloudingService
				.lookupCloudedApplication(eucaApplicationName);

		return eucalyptusCloudedApplication;
	}

	/**
	 * 
	 * @param executionContainer
	 * @return
	 */
	private EucalyptusCloudNode eucaNodeForExecutionContainer(
			final ExecutionContainer executionContainer) {
		final String fqContainerName = NameUtils.createFQName(
				executionContainer.getPackageName(),
				executionContainer.getName());
		final String eucaNodeName = modelManager
				.getArch2ImplNameMappingManager().lookupImplName4ArchName(
						EntityType.EXECUTION_CONTAINER, fqContainerName);

		final EucalyptusCloudNode cloudNode = (EucalyptusCloudNode) eucalyptusApplicationCloudingService
				.lookupNode(eucaNodeName);

		return cloudNode;
	}

	@Override
	protected boolean concreteReplicateComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer toExecutionContainer,
			final DeploymentComponent resDeploymentComponent) {
		boolean success = false;

		try {
			final EucalyptusApplicationInstanceConfiguration config = new EucalyptusApplicationInstanceConfiguration();

			final EucalyptusCloudNode dstNode = eucaNodeForExecutionContainer(toExecutionContainer);
			if (dstNode == null) {
				throw new ApplicationCloudingServiceException(
						"dstNode is null for execution container: "
								+ toExecutionContainer);
			}

			final EucalyptusCloudedApplication eucaApplication = eucaApplicationForAssemblyComponet(assemblyComponent);
			if (eucaApplication == null) {
				throw new ApplicationCloudingServiceException(
						"eucaApplication is null for assembly component: "
								+ assemblyComponent);
			}

			final EucalyptusApplicationInstance appInstance = (EucalyptusApplicationInstance) eucalyptusApplicationCloudingService
					.deployApplicationInstance(eucaApplication, config, dstNode);
			if (appInstance != null) {
				success = true;
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

			final AssemblyComponent assemblyComponent = deploymentComponent
					.getAssemblyComponent();
			final ExecutionContainer executionContainer = deploymentComponent
					.getExecutionContainer();

			final EucalyptusCloudedApplication euCloudedApplication = eucaApplicationForAssemblyComponet(assemblyComponent);
			if (euCloudedApplication == null) {
				EucalyptusReconfigurationManager.log
						.error("Failed to lookup eucalyptus application for assembly component: "
								+ assemblyComponent);
				return false;
			}

			final EucalyptusCloudNode euCloudNode = eucaNodeForExecutionContainer(executionContainer);
			if (euCloudNode == null) {
				EucalyptusReconfigurationManager.log
						.error("Failed to lookup eucalyptus node for execution container"
								+ executionContainer);
				return false;
			}

			final EucalyptusApplicationInstance appInstance = (EucalyptusApplicationInstance) eucalyptusApplicationCloudingService
					.lookupApplicationInstance(euCloudedApplication,
							euCloudNode);

			if (appInstance == null) {
				final String errorMsg = "appInstance for "
						+ deploymentComponent + " is null";
				EucalyptusReconfigurationManager.log.error(errorMsg);
				return false;
			}

			// TODO: add boolean return value for exit status?
			eucalyptusApplicationCloudingService
					.undeployApplicationInstance(appInstance);

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

			// TODO: Lookup node type based on execution container type
			// and if no mapping exists, fall back to default
			// node type

			final EucalyptusCloudNodeType euNodeType = euDefaultNodeType;

			final EucalyptusCloudNode euNode = (EucalyptusCloudNode) eucalyptusApplicationCloudingService
					.allocateNode(fqNodeName, euNodeType);

			if (euNode == null) {
				EucalyptusReconfigurationManager.log
						.error("allocateNode(..) failed");
				success = false;
			} else {
				success = true;

				modelManager.getArch2ImplNameMappingManager()
						.registerArch2implNameMapping(
								EntityType.EXECUTION_CONTAINER, fqNodeName,
								fqNodeName);

				modelManager.getArch2ImplNameMappingManager()
						.registerArch2implNameMapping(
								EntityType.EXECUTION_CONTAINER, fqNodeName,
								euNode.getName());
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

			final EucalyptusCloudNode euNode = eucaNodeForExecutionContainer(executionContainer);

			if (euNode == null) {
				EucalyptusReconfigurationManager.log
						.error("Failed to lookup node");
				success = false;
			} else {
				// TODO: return value?
				eucalyptusApplicationCloudingService.deallocateNode(euNode);

				success = true;
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
