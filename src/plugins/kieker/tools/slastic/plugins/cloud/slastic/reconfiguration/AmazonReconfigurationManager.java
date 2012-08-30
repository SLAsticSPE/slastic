package kieker.tools.slastic.plugins.cloud.slastic.reconfiguration;

import java.io.File;
import java.io.PrintWriter;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.tools.slastic.plugins.cloud.amazon.model.*;
import kieker.tools.slastic.plugins.cloud.amazon.service.AmazonApplicationCloudingService;
import kieker.tools.slastic.plugins.cloud.amazon.service.configuration.IAmazonApplicationCloudingServiceConfiguration;
import kieker.tools.slastic.plugins.cloud.amazon.service.logging.AbstractAmazonServiceEventLogger;
import kieker.tools.slastic.plugins.cloud.common.ICurrentTimeProvider;
import kieker.tools.slastic.plugins.cloud.service.ApplicationCloudingServiceException;
import kieker.tools.slastic.plugins.slasticImpl.ModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.NameUtils;
import kieker.tools.slastic.plugins.slasticImpl.model.arch2implMapping.Arch2ImplNameMappingManager.EntityType;
import kieker.tools.slastic.reconfiguration.AbstractReconfigurationManagerComponent;
import kieker.tools.slastic.reconfiguration.ReconfigurationException;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent;
import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;
import kieker.tools.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class AmazonReconfigurationManager extends AbstractReconfigurationManagerComponent {
	private static final Log log = LogFactory.getLog(AmazonReconfigurationManager.class);

	private static final String PROPERTY_CONFIGURATIONFN_NAME = "configFile";

	private static final String PROPERTY_DEFAULT_NODE_TYPE_NAME = "defaultNodeType";

	private static final String Amazon_EVENT_LOG = "amazon-events.log";

	// TODO: pull out as configuration property
	private static final boolean FALLBACK_IF_NO_ARCH2IMPL_APPNAME_MAPPING = true;

	private volatile PrintWriter amazonEventWriter = null;
	private volatile AmazonCloudNodeType ec2DefaultNodeType;

	/**
	 * The actual reconfigurations are performed by this
	 * {@link AmazonApplicationCloudingService}.
	 */
	private volatile AmazonApplicationCloudingService amazonApplicationCloudingService;

	@Override
	public void doReconfiguration(final SLAsticReconfigurationPlan arg0) throws ReconfigurationException {
		throw new UnsupportedOperationException(
				"doReconfiguration(final SLAsticReconfigurationPlan arg0) not supported");
	}

	/**
	 * Initializes the {@link AbstractAmazonApplicationCloudingService}
	 * based on an {@link IAmazonApplicationCloudingServiceConfiguration}
	 * provided by a {@link Properties} file.
	 */
	@Override
	public boolean init() {
		AmazonApplicationCloudingService amazonApplicationCloudingService;
		try {
			{ /* Initialize the Amazon service */
				/* Determine the path of the Amazon configuration file. */
				final String configurationFile =
						super.getInitProperty(AmazonReconfigurationManager.PROPERTY_CONFIGURATIONFN_NAME);
				if ((configurationFile == null) || (configurationFile.length() == 0)) {
					final String errorMsg =
							"Invalid or missing value for property '"
									+ AmazonReconfigurationManager.PROPERTY_CONFIGURATIONFN_NAME + "'";
					AmazonReconfigurationManager.log.error(errorMsg);
					return false;
				}

				/* Create an instance of the Amazon service */
				amazonApplicationCloudingService =
						AmazonApplicationCloudingService.createService(configurationFile);
			}

			{/* Lookup the default node type */
				final String defaultNodeTypeName =
						super.getInitProperty(AmazonReconfigurationManager.PROPERTY_DEFAULT_NODE_TYPE_NAME);
				if (defaultNodeTypeName == null) {
					AmazonReconfigurationManager.log.error("Invalid or missing value for property '"
							+ AmazonReconfigurationManager.PROPERTY_DEFAULT_NODE_TYPE_NAME + "'");
					return false;
				}

				this.ec2DefaultNodeType =
						(AmazonCloudNodeType) amazonApplicationCloudingService
								.lookupCloudNodeType(defaultNodeTypeName);
				if (this.ec2DefaultNodeType == null) {
					AmazonReconfigurationManager.log.error("Failed to lookup default node type '"
							+ defaultNodeTypeName + "'");
					return false;
				}
			}
		} catch (final Exception exc) {
			AmazonReconfigurationManager.log.error("Error initializing :" + exc.getMessage(), exc);
			amazonApplicationCloudingService = null;
		}

		this.amazonApplicationCloudingService = amazonApplicationCloudingService;
		return this.amazonApplicationCloudingService != null;
	}

	private volatile ModelManager modelManager;

	@Override
	public boolean execute() {
		try {
			this.modelManager = (ModelManager) this.getControlComponent().getModelManager();

			/* Create print writer for Amazon events */
			final File euEventsFile =
					this.getComponentContext().createFileInContextDir(
							AmazonReconfigurationManager.Amazon_EVENT_LOG);
			this.amazonEventWriter = new PrintWriter(euEventsFile);
			if (this.amazonEventWriter == null) {
				AmazonReconfigurationManager.log.error("Failed to create event logger");
				return false;
			}

			/* Register logger */
			// final ICurrentTimeProvider currentTimeProvider =
			// new SLAsticCurrentTimeProvider();
			final AmazonEventLogger euEventLogger = new AmazonEventLogger(this.amazonEventWriter);
			this.amazonApplicationCloudingService.addEventListener(euEventLogger);
		} catch (final Exception exc) {
			AmazonReconfigurationManager.log.error("Failed to create application: " + exc.getMessage());
			return false;
		}

		return true;
	}

	@Override
	public void terminate(final boolean arg0) {
		try {
			// TODO: remove app?
			// if ((this.euApplication != null)
			// && (this.AmazonApplicationCloudingService != null)) {
			// this.AmazonApplicationCloudingService
			// .removeCloudedApplication(this.euApplication);
			// }
		} catch (final Exception exc) {
			AmazonReconfigurationManager.log.error("Failed to remove application: " + exc.getMessage());
			return;
		} finally {
			if (this.amazonEventWriter != null) {
				this.amazonEventWriter.close();
			}
		}
	}

	/**
	 * 
	 * @param assemblyComponent
	 * @param fallBackIfNoArch2ImplNameMapping
	 * @return
	 */
	private AmazonCloudedApplication ec2ApplicationForAssemblyComponet(final AssemblyComponent assemblyComponent,
			final boolean fallBackIfNoArch2ImplNameMapping) {
		final String fqAssemblyComponentName =
				NameUtils.createFQName(assemblyComponent.getPackageName(), assemblyComponent.getName());
		String ec2ApplicationName =
				this.modelManager.getArch2ImplNameMappingManager().lookupImplName4ArchName(
						EntityType.ASSEMBLY_COMPONENT, fqAssemblyComponentName);
		if ((ec2ApplicationName == null) && fallBackIfNoArch2ImplNameMapping) {
			/* Fall-back: Use assembly component name as ec2 applicaion name */
			ec2ApplicationName = fqAssemblyComponentName;
		}

		final AmazonCloudedApplication AmazonCloudedApplication =
				(AmazonCloudedApplication) this.amazonApplicationCloudingService
						.lookupCloudedApplication(ec2ApplicationName);

		if (AmazonCloudedApplication == null) {
			AmazonReconfigurationManager.log.warn(String.format(
					"Failed to lookup ec2 app for name %s (fall-back mode: %s)", ec2ApplicationName,
					fallBackIfNoArch2ImplNameMapping));
		}

		return AmazonCloudedApplication;
	}

	/**
	 * 
	 * @param executionContainer
	 * @return
	 */
	private AmazonCloudNode ec2NodeForExecutionContainer(final ExecutionContainer executionContainer) {
		final String fqContainerName =
				NameUtils.createFQName(executionContainer.getPackageName(), executionContainer.getName());
		final String ec2NodeName =
				this.modelManager.getArch2ImplNameMappingManager().lookupImplName4ArchName(
						EntityType.EXECUTION_CONTAINER, fqContainerName);

		final AmazonCloudNode cloudNode =
				(AmazonCloudNode) this.amazonApplicationCloudingService.lookupNode(ec2NodeName);

		return cloudNode;
	}

	@Override
	protected boolean concreteReplicateComponent(final AssemblyComponent assemblyComponent,
			final ExecutionContainer toExecutionContainer, final DeploymentComponent resDeploymentComponent) {
		boolean success = false;

		try {
			final AmazonApplicationInstanceConfiguration config = new AmazonApplicationInstanceConfiguration();

			final AmazonCloudNode dstNode = this.ec2NodeForExecutionContainer(toExecutionContainer);
			if (dstNode == null) {
				throw new ApplicationCloudingServiceException("dstNode is null for execution container: "
						+ toExecutionContainer);
			}

			final AmazonCloudedApplication ec2Application =
					this.ec2ApplicationForAssemblyComponet(assemblyComponent,
							AmazonReconfigurationManager.FALLBACK_IF_NO_ARCH2IMPL_APPNAME_MAPPING);
			if (ec2Application == null) {
				final String errorMsg =
						String.format("ec2Application is null for assembly component: %s\n"
								+ "Most likely, no initial mapping was defined in the Amazon configuration",
								assemblyComponent);
				throw new ApplicationCloudingServiceException(errorMsg);
			}

			final AmazonApplicationInstance appInstance =
					(AmazonApplicationInstance) this.amazonApplicationCloudingService
							.deployApplicationInstance(ec2Application, config, dstNode);
			if (appInstance != null) {
				success = true;
			} else {
				AmazonReconfigurationManager.log.error("appInstance is null");
				success = false;
			}
		} catch (final ApplicationCloudingServiceException e) {
			AmazonReconfigurationManager.log.error(e.getMessage(), e);
			success = false;
		}
		return success;
	}

	@Override
	protected boolean concreteMigrateComponent(final DeploymentComponent deploymentComponent,
			final ExecutionContainer destination, final DeploymentComponent resDeploymentComponent) {
		boolean successReplication = false;
		boolean successDereplication = false;

		successReplication =
				this.concreteReplicateComponent(deploymentComponent.getAssemblyComponent(), destination,
						resDeploymentComponent);
		if (!successReplication) {
			AmazonReconfigurationManager.log.error("concreteReplicateComponent(..) failed");
		} else {
			successDereplication = this.concreteDereplicateComponent(deploymentComponent);
		}

		if (!successDereplication) {
			AmazonReconfigurationManager.log.error("concreteDereplicateComponent(..) failed");
		}

		return successReplication && successDereplication;
	}

	@Override
	protected boolean concreteDereplicateComponent(final DeploymentComponent deploymentComponent) {
		boolean success = false;

		try {
			AmazonReconfigurationManager.log.info("concreteDereplicateComponent");

			final AssemblyComponent assemblyComponent = deploymentComponent.getAssemblyComponent();
			final ExecutionContainer executionContainer = deploymentComponent.getExecutionContainer();

			final AmazonCloudedApplication euCloudedApplication =
					this.ec2ApplicationForAssemblyComponet(assemblyComponent,
							AmazonReconfigurationManager.FALLBACK_IF_NO_ARCH2IMPL_APPNAME_MAPPING);
			if (euCloudedApplication == null) {
				AmazonReconfigurationManager.log
						.error("Failed to lookup Amazon application for assembly component: " + assemblyComponent);
				return false;
			}

			final AmazonCloudNode euCloudNode = this.ec2NodeForExecutionContainer(executionContainer);
			if (euCloudNode == null) {
				AmazonReconfigurationManager.log.error("Failed to lookup Amazon node for execution container"
						+ executionContainer);
				return false;
			}

			final AmazonApplicationInstance appInstance =
					(AmazonApplicationInstance) this.amazonApplicationCloudingService
							.lookupApplicationInstance(euCloudedApplication, euCloudNode);

			if (appInstance == null) {
				final String errorMsg = "appInstance for " + deploymentComponent + " is null";
				AmazonReconfigurationManager.log.error(errorMsg);
				return false;
			}

			// TODO: add boolean return value for exit status?
			this.amazonApplicationCloudingService.undeployApplicationInstance(appInstance);

			success = true;
		} catch (final ApplicationCloudingServiceException e) {
			AmazonReconfigurationManager.log.error(e.getMessage(), e);
			success = false;
		}
		return success;
	}

	@Override
	protected boolean concreteAllocateExecutionContainer(final ExecutionContainerType executionContainerType,
			final ExecutionContainer resExecutionContainer) {
		boolean success = false;

		try {
			final String fqExecutionContainerName =
					NameUtils.createFQName(resExecutionContainer.getPackageName(), resExecutionContainer.getName());

			// TODO: Lookup node type based on execution container type
			// and if no mapping exists, fall back to default
			// node type

			final AmazonCloudNodeType euNodeType = this.ec2DefaultNodeType;

			final AmazonCloudNode euNode =
					(AmazonCloudNode) this.amazonApplicationCloudingService.allocateNode(
							fqExecutionContainerName, euNodeType);

			if (euNode == null) {
				AmazonReconfigurationManager.log.error("allocateNode(..) failed");
				success = false;
			} else {
				success = true;

				// TODO: does this make any sense?:
				// this.modelManager.getArch2ImplNameMappingManager().registerArch2implNameMapping(
				// EntityType.EXECUTION_CONTAINER, fqExecutionContainerName,
				// fqExecutionContainerName);

				this.modelManager.getArch2ImplNameMappingManager().registerArch2implNameMapping(
						EntityType.EXECUTION_CONTAINER, fqExecutionContainerName, euNode.getName());
			}
		} catch (final ApplicationCloudingServiceException e) {
			AmazonReconfigurationManager.log.error(e.getMessage(), e);
			success = false;
		}
		return success;
	}

	@Override
	protected boolean concreteDeallocateExecutionContainer(final ExecutionContainer executionContainer) {
		boolean success = false;

		try {
			AmazonReconfigurationManager.log.info("concreteDeallocateExecutionContainer");

			final AmazonCloudNode euNode = this.ec2NodeForExecutionContainer(executionContainer);

			if (euNode == null) {
				AmazonReconfigurationManager.log.error("Failed to lookup node");
				success = false;
			} else {
				// TODO: return value?
				this.amazonApplicationCloudingService.deallocateNode(euNode);

				success = true;
			}
		} catch (final ApplicationCloudingServiceException e) {
			AmazonReconfigurationManager.log.error(e.getMessage(), e);
			success = false;
		}
		return success;
	}

	private class AmazonEventLogger extends AbstractAmazonServiceEventLogger {

		private final PrintWriter pw;

		public AmazonEventLogger(final PrintWriter pw) {
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
			return AmazonReconfigurationManager.this.getControlComponent().getCurrentTimeMillis();
		}
	}

	@Override
	protected DeploymentComponent createPreliminaryDeploymentComponentInModel(
			final AssemblyComponent assemblyComponent, final ExecutionContainer executionContainer) {
		// TODO: set preliminary flag or alike? (could adopt the procedure for execution containers)
		return ((ModelManager) this.getControlComponent().getModelManager()).getComponentDeploymentModelManager()
				.createAndRegisterDeploymentComponent(assemblyComponent, executionContainer);
	}

	@Override
	protected ExecutionContainer createPreliminaryExecutionContainerInModel(final String fullyQualifiedName,
			final ExecutionContainerType executionContainerType) {
		return ((ModelManager) this.getControlComponent().getModelManager()).getExecutionEnvironmentModelManager()
				.createAndRegisterExecutionContainer(fullyQualifiedName, executionContainerType, 
						/* do not mark allocated */ false);
	}

	@Override
	protected boolean deletePreliminaryDeploymentComponentFromModel(final DeploymentComponent deploymentComponent) {
		return ((ModelManager) this.getControlComponent().getModelManager()).getComponentDeploymentModelManager()
				.deleteDeploymentComponent(deploymentComponent);
	}

	@Override
	protected boolean deletePreliminaryExecutionContainerFromModel(final ExecutionContainer executionContainer) {
		return ((ModelManager) this.getControlComponent().getModelManager()).getExecutionEnvironmentModelManager()
				.deallocateExecutionContainer(executionContainer);
	}

	@Override
	protected boolean deleteDeploymentComponentFromModel(final DeploymentComponent deploymentComponent) {
		AmazonReconfigurationManager.log.info("deleteDeploymentComponentFromModel(...)");
		return ((ModelManager) this.getControlComponent().getModelManager()).getComponentDeploymentModelManager()
				.deleteDeploymentComponent(deploymentComponent);
	}

	@Override
	protected boolean deleteExecutionContainerFromModel(final ExecutionContainer executionContainer) {
		AmazonReconfigurationManager.log.info("deleteExecutionContainerFromModel");
		return ((ModelManager) this.getControlComponent().getModelManager()).getExecutionEnvironmentModelManager()
				.deallocateExecutionContainer(executionContainer);
	}
}
