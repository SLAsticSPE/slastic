package org.trustsoft.slastic.plugins.cloud.slastic.reconfiguration;

import java.io.File;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstance;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstanceConfiguration;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNode;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNodeType;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudedApplication;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.AbstractEucalyptusServiceEventLogger;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.EucalyptusApplicationCloudingService;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.ICurrentTimeProvider;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.service.IEucalyptusApplicationCloudingServiceConfiguration;
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
	private static final String NODE_TYPE_NAME = "tomcat";

	// private volatile EucalyptusCloudedApplication euApplication;
	private volatile EucalyptusCloudNodeType euTomcatNodeType;

	private static final String PROPERTY_CONFIGURATIONFN_NAME = "configFile";

	private static final String EUCALYPTUS_EVENT_LOG = "eucalyptus-events.log";
	private volatile PrintWriter eucalyptusEventWriter = null;

	/**
	 * Maps {@link AssemblyComponent}s to the corresponding
	 * {@link EucalyptusCloudedApplication}.
	 */
	private final HashMap<AssemblyComponent, EucalyptusCloudedApplication> assemblyComponentMapping =
			new HashMap<AssemblyComponent, EucalyptusCloudedApplication>();

	/**
	 * Maps {@link ExecutionContainer}s to the corresponding
	 * {@link EucalyptusCloudNode}s
	 */
	private final HashMap<ExecutionContainer, EucalyptusCloudNode> allocatedNodesMapping =
			new HashMap<ExecutionContainer, EucalyptusCloudNode>();

	/**
	 * Maps {@link DeploymentComponent}s to the corresponding
	 * {@link EucalyptusApplicationInstance}s
	 */
	private final HashMap<DeploymentComponent, EucalyptusApplicationInstance> deploymentComponentMapping =
			new HashMap<DeploymentComponent, EucalyptusApplicationInstance>();

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
			final String configurationFile =
					super.getInitProperty(EucalyptusReconfigurationManager.PROPERTY_CONFIGURATIONFN_NAME);
			if ((configurationFile == null)
					|| (configurationFile.length() == 0)) {
				final String errorMsg =
						"Invalid or missing value for property '"
								+ EucalyptusReconfigurationManager.PROPERTY_CONFIGURATIONFN_NAME
								+ "'";
				EucalyptusReconfigurationManager.log.error(errorMsg);
				return false;
			}

			/* Create an instance of the Eucalyptus service */
			eucalyptusApplicationCloudingService =
					EucalyptusApplicationCloudingService
							.createService(configurationFile);
		} catch (final Exception exc) {
			EucalyptusReconfigurationManager.log.error("Error initializing :"
					+ exc.getMessage(), exc);
			eucalyptusApplicationCloudingService = null;
		}

		this.eucalyptusApplicationCloudingService =
				eucalyptusApplicationCloudingService;
		return this.eucalyptusApplicationCloudingService != null;
	}

	// // Part of hack #10
	// private volatile ConcurrentHashMap<String, ExecutionContainer>
	// containerNameMapping;

	private volatile ModelManager modelManager;

	@Override
	public boolean execute() {
		try {
			this.modelManager =
					(ModelManager) this.getControlComponent().getModelManager();
			// {
			// // Part of hack #10
			// this.containerNameMapping =
			// ((ModelManager) this.getControlComponent()
			// .getModelManager())
			// .getExecutionEnvironmentModelManager().containerNameMapping;
			// }

			/* Create print writer for eucalyptus events */
			final File euEventsFile =
					this.getComponentContext()
							.createFileInContextDir(
									EucalyptusReconfigurationManager.EUCALYPTUS_EVENT_LOG);
			this.eucalyptusEventWriter = new PrintWriter(euEventsFile);
			if (this.eucalyptusEventWriter == null) {
				EucalyptusReconfigurationManager.log
						.error("Failed to create event logger");
				return false;
			}

			/* Register logger */
//			final ICurrentTimeProvider currentTimeProvider =
//					new SLAsticCurrentTimeProvider();
			final EucalyptusEventLogger euEventLogger =
					new EucalyptusEventLogger(this.eucalyptusEventWriter);
			this.eucalyptusApplicationCloudingService
					.addEventListener(euEventLogger);

			// TODO: do this based on SLAstic model/property file
			/* Create and register the application */
			// final EucalyptusCloudedApplicationConfiguration euAppConfig =
			// new EucalyptusCloudedApplicationConfiguration();
			// this.euApplication =
			// (EucalyptusCloudedApplication)
			// this.eucalyptusApplicationCloudingService
			// .createAndRegisterCloudedApplication(
			// EucalyptusReconfigurationManager.APP_NAME,
			// euAppConfig);

			/* Lookup the tomcat node type */
			final Collection<EucalyptusCloudNodeType> euNodeTypes =
					this.eucalyptusApplicationCloudingService.getNodeTypes();
			for (final EucalyptusCloudNodeType nt : euNodeTypes) {
				if (nt.getName().equals(
						EucalyptusReconfigurationManager.NODE_TYPE_NAME)) {
					this.euTomcatNodeType = nt;
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
			if (this.eucalyptusEventWriter != null) {
				this.eucalyptusEventWriter.close();
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
			final EucalyptusApplicationInstanceConfiguration config =
					new EucalyptusApplicationInstanceConfiguration();

			final EucalyptusCloudNode dstNode =
					this.allocatedNodesMapping.get(toExecutionContainer);
			if (dstNode == null) {
				throw new ApplicationCloudingServiceException(
						"dstNode is null for execution container: "
								+ toExecutionContainer);
			}

			final EucalyptusCloudedApplication eucaApplication =
					this.assemblyComponentMapping.get(assemblyComponent);
			if (eucaApplication == null) {
				throw new ApplicationCloudingServiceException(
						"eucaApplication is null for assembly component: "
								+ assemblyComponent);
			}

			final EucalyptusApplicationInstance appInstance =
					(EucalyptusApplicationInstance) this.eucalyptusApplicationCloudingService
							.deployApplicationInstance(eucaApplication, config,
									dstNode);
			if (appInstance != null) {
				success = true;
				this.deploymentComponentMapping.put(resDeploymentComponent,
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

		successReplication =
				this.concreteReplicateComponent(
						deploymentComponent.getAssemblyComponent(),
						destination, resDeploymentComponent);
		if (!successReplication) {
			EucalyptusReconfigurationManager.log
					.error("concreteReplicateComponent(..) failed");
		} else {
			successDereplication =
					this.concreteDereplicateComponent(deploymentComponent);
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

			final EucalyptusApplicationInstance appInstance =
					this.deploymentComponentMapping.get(deploymentComponent);

			if (appInstance == null) {
				final String errorMsg =
						"appInstance for " + deploymentComponent + " is null";
				EucalyptusReconfigurationManager.log.error(errorMsg);
				return false;
			}

			// TODO: add boolean return value for exit status?
			this.eucalyptusApplicationCloudingService
					.undeployApplicationInstance(appInstance);

			this.deploymentComponentMapping.remove(deploymentComponent);
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
			final String fqNodeName =
					NameUtils.createFQName(
							resExecutionContainer.getPackageName(),
							resExecutionContainer.getName());
			final EucalyptusCloudNode euNode =
					(EucalyptusCloudNode) this.eucalyptusApplicationCloudingService
							.allocateNode(fqNodeName, this.euTomcatNodeType);

			if (euNode == null) {
				EucalyptusReconfigurationManager.log
						.error("allocateNode(..) failed");
				success = false;
			} else {
				success = true;
				this.allocatedNodesMapping.put(resExecutionContainer, euNode);

				this.modelManager.getArch2ImplNameMappingManager()
						.registerArch2implNameMapping(
								EntityType.EXECUTION_CONTAINER, fqNodeName,
								euNode.getHostname());

				// HACK:
				// this.containerNameMapping.put(euNode.getHostname(),
				// resExecutionContainer);
				//
				EucalyptusReconfigurationManager.log
						.info("Added hostname mapping " + euNode.getHostname()
								+ " x " + resExecutionContainer);
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

			final EucalyptusCloudNode euNode =
					this.allocatedNodesMapping.get(executionContainer);

			if (euNode == null) {
				EucalyptusReconfigurationManager.log
						.error("Failed to lookup node");
				success = false;
			} else {
				this.eucalyptusApplicationCloudingService
						.deallocateNode(euNode);

				if (this.allocatedNodesMapping.remove(executionContainer) == null) {
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
			this.pw.println(eventMsg);
			this.pw.flush();
		}

	}

	private class SLAsticCurrentTimeProvider implements ICurrentTimeProvider {

		@Override
		public long getCurrentTimeMillis() {
			return EucalyptusReconfigurationManager.this.getControlComponent()
					.getCurrentTimeMillis();
		}

	}

	@Override
	protected DeploymentComponent createPreliminaryDeploymentComponentInModel(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer executionContainer) {
		// TODO: set preliminary flag or alike?
		return ((ModelManager) this.getControlComponent().getModelManager())
				.getComponentDeploymentModelManager()
				.createAndRegisterDeploymentComponent(assemblyComponent,
						executionContainer);
	}

	@Override
	protected ExecutionContainer createPreliminaryExecutionContainerInModel(
			final String fullyQualifiedName,
			final ExecutionContainerType executionContainerType) {
		return ((ModelManager) this.getControlComponent().getModelManager())
				.getExecutionEnvironmentModelManager()
				.createAndRegisterExecutionContainer(fullyQualifiedName,
						executionContainerType);
	}

	@Override
	protected boolean deletePreliminaryDeploymentComponentFromModel(
			final DeploymentComponent deploymentComponent) {
		return ((ModelManager) this.getControlComponent().getModelManager())
				.getComponentDeploymentModelManager()
				.deleteDeploymentComponent(deploymentComponent);
	}

	@Override
	protected boolean deletePreliminaryExecutionContainerFromModel(
			final ExecutionContainer executionContainer) {
		return ((ModelManager) this.getControlComponent().getModelManager())
				.getExecutionEnvironmentModelManager()
				.deallocateExecutionContainer(executionContainer);

	}

	@Override
	protected boolean deleteDeploymentComponentFromModel(
			final DeploymentComponent deploymentComponent) {
		EucalyptusReconfigurationManager.log
				.info("deleteDeploymentComponentFromModel(...)");
		return ((ModelManager) this.getControlComponent().getModelManager())
				.getComponentDeploymentModelManager()
				.deleteDeploymentComponent(deploymentComponent);
	}

	@Override
	protected boolean deleteExecutionContainerFromModel(
			final ExecutionContainer executionContainer) {
		EucalyptusReconfigurationManager.log
				.info("deleteExecutionContainerFromModel");
		return ((ModelManager) this.getControlComponent().getModelManager())
				.getExecutionEnvironmentModelManager()
				.deallocateExecutionContainer(executionContainer);
	}
}
