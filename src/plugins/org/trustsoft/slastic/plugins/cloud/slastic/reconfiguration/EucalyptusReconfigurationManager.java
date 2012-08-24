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

package org.trustsoft.slastic.plugins.cloud.slastic.reconfiguration;

import java.io.File;
import java.io.PrintWriter;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstance;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusApplicationInstanceConfiguration;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNode;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudNodeType;
import org.trustsoft.slastic.plugins.cloud.eucalyptus.model.EucalyptusCloudedApplication;
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
public class EucalyptusReconfigurationManager extends AbstractReconfigurationManagerComponent {
	private static final Log LOG = LogFactory.getLog(EucalyptusReconfigurationManager.class);

	private static final String PROPERTY_CONFIGURATIONFN_NAME = "configFile";

	private static final String PROPERTY_DEFAULT_NODE_TYPE_NAME = "defaultEucaNodeType";

	private static final String EUCALYPTUS_EVENT_LOG = "eucalyptus-events.log";

	// TODO: pull out as configuration property
	private static final boolean FALLBACK_IF_NO_ARCH2IMPL_APPNAME_MAPPING = true;

	private volatile PrintWriter eucalyptusEventWriter = null;
	private volatile EucalyptusCloudNodeType euDefaultNodeType;

	/**
	 * The actual reconfigurations are performed by this {@link EucalyptusApplicationCloudingService}.
	 */
	private volatile EucalyptusApplicationCloudingService eucalyptusApplicationCloudingService;

	@Override
	public void doReconfiguration(final SLAsticReconfigurationPlan arg0) throws ReconfigurationException {
		throw new UnsupportedOperationException("doReconfiguration(final SLAsticReconfigurationPlan arg0) not supported");
	}

	/**
	 * Initializes the {@link AbstractEucalyptusApplicationCloudingService} based on an {@link IEucalyptusApplicationCloudingServiceConfiguration} provided by a
	 * {@link Properties} file.
	 */
	@Override
	public boolean init() {
		EucalyptusApplicationCloudingService eucalyptusApplicationCloudingService;
		try {
			{ /* Initialize the Eucalyptus service */
				/* Determine the path of the Eucalyptus configuration file. */
				final String configurationFile = super.getInitProperty(PROPERTY_CONFIGURATIONFN_NAME);
				if ((configurationFile == null) || (configurationFile.length() == 0)) {
					final String errorMsg = "Invalid or missing value for property '" + PROPERTY_CONFIGURATIONFN_NAME + "'";
					LOG.error(errorMsg);
					return false;
				}

				/* Create an instance of the Eucalyptus service */
				eucalyptusApplicationCloudingService = EucalyptusApplicationCloudingService.createService(configurationFile);
			}

			{/* Lookup the default node type */
				final String defaultNodeTypeName = super.getInitProperty(PROPERTY_DEFAULT_NODE_TYPE_NAME);
				if (defaultNodeTypeName == null) {
					LOG.error("Invalid or missing value for property '" + PROPERTY_DEFAULT_NODE_TYPE_NAME + "'");
					return false;
				}

				this.euDefaultNodeType = (EucalyptusCloudNodeType) eucalyptusApplicationCloudingService.lookupCloudNodeType(defaultNodeTypeName);
				if (this.euDefaultNodeType == null) {
					LOG.error("Failed to lookup default node type '" + defaultNodeTypeName + "'");
					return false;
				}
			}
		} catch (final Exception exc) {
			LOG.error("Error initializing :" + exc.getMessage(), exc);
			eucalyptusApplicationCloudingService = null;
		}

		this.eucalyptusApplicationCloudingService = eucalyptusApplicationCloudingService;
		return this.eucalyptusApplicationCloudingService != null;
	}

	private volatile ModelManager modelManager;

	@Override
	public boolean execute() {
		try {
			this.modelManager = (ModelManager) this.getControlComponent().getModelManager();

			/* Create print writer for eucalyptus events */
			final File euEventsFile = this.getComponentContext().createFileInContextDir(EUCALYPTUS_EVENT_LOG);
			this.eucalyptusEventWriter = new PrintWriter(euEventsFile);
			if (this.eucalyptusEventWriter == null) {
				LOG.error("Failed to create event logger");
				return false;
			}

			/* Register logger */
			// final ICurrentTimeProvider currentTimeProvider =
			// new SLAsticCurrentTimeProvider();
			final EucalyptusEventLogger euEventLogger = new EucalyptusEventLogger(this.eucalyptusEventWriter);
			this.eucalyptusApplicationCloudingService.addEventListener(euEventLogger);
		} catch (final Exception exc) {
			LOG.error("Failed to create application: " + exc.getMessage());
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
			LOG.error("Failed to remove application: " + exc.getMessage());
			return;
		} finally {
			if (this.eucalyptusEventWriter != null) {
				this.eucalyptusEventWriter.close();
			}
		}
	}

	/**
	 * 
	 * @param assemblyComponent
	 * @param fallBackIfNoArch2ImplNameMapping
	 * @return
	 */
	private EucalyptusCloudedApplication eucaApplicationForAssemblyComponet(final AssemblyComponent assemblyComponent,
			final boolean fallBackIfNoArch2ImplNameMapping) {
		final String fqAssemblyComponentName = NameUtils.createFQName(assemblyComponent.getPackageName(), assemblyComponent.getName());
		String eucaApplicationName =
				this.modelManager.getArch2ImplNameMappingManager().lookupImplName4ArchName(EntityType.ASSEMBLY_COMPONENT, fqAssemblyComponentName);
		if ((eucaApplicationName == null) && fallBackIfNoArch2ImplNameMapping) {
			/* Fall-back: Use assembly component name as euca applicaion name */
			eucaApplicationName = fqAssemblyComponentName;
		}

		final EucalyptusCloudedApplication eucalyptusCloudedApplication =
				(EucalyptusCloudedApplication) this.eucalyptusApplicationCloudingService.lookupCloudedApplication(eucaApplicationName);

		if (eucalyptusCloudedApplication == null) {
			LOG.warn(String.format("Failed to lookup euca app for name %s (fall-back mode: %s)", eucaApplicationName, fallBackIfNoArch2ImplNameMapping));
		}

		return eucalyptusCloudedApplication;
	}

	/**
	 * 
	 * @param executionContainer
	 * @return
	 */
	private EucalyptusCloudNode eucaNodeForExecutionContainer(final ExecutionContainer executionContainer) {
		final String fqContainerName =
				NameUtils.createFQName(executionContainer.getPackageName(), executionContainer.getName());
		final String eucaNodeName =
				this.modelManager.getArch2ImplNameMappingManager().lookupImplName4ArchName(EntityType.EXECUTION_CONTAINER, fqContainerName);

		final EucalyptusCloudNode cloudNode = (EucalyptusCloudNode) this.eucalyptusApplicationCloudingService.lookupNode(eucaNodeName);

		return cloudNode;
	}

	@Override
	protected boolean concreteReplicateComponent(final AssemblyComponent assemblyComponent,
			final ExecutionContainer toExecutionContainer, final DeploymentComponent resDeploymentComponent) {
		boolean success = false;

		try {
			final EucalyptusApplicationInstanceConfiguration config = new EucalyptusApplicationInstanceConfiguration();

			final EucalyptusCloudNode dstNode = this.eucaNodeForExecutionContainer(toExecutionContainer);
			if (dstNode == null) {
				throw new ApplicationCloudingServiceException("dstNode is null for execution container: " + toExecutionContainer);
			}

			final EucalyptusCloudedApplication eucaApplication = this
					.eucaApplicationForAssemblyComponet(assemblyComponent, FALLBACK_IF_NO_ARCH2IMPL_APPNAME_MAPPING);
			if (eucaApplication == null) {
				final String errorMsg =
						String.format("eucaApplication is null for assembly component: %s\n"
								+ "Most likely, no initial mapping was defined in the eucalyptus configuration",
								assemblyComponent);
				throw new ApplicationCloudingServiceException(errorMsg);
			}

			final EucalyptusApplicationInstance appInstance =
					(EucalyptusApplicationInstance) this.eucalyptusApplicationCloudingService.deployApplicationInstance(eucaApplication, config, dstNode);
			if (appInstance != null) {
				success = true;
			} else {
				LOG.error("appInstance is null");
				success = false;
			}
		} catch (final ApplicationCloudingServiceException e) {
			LOG.error(e.getMessage(), e);
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
				this.concreteReplicateComponent(deploymentComponent.getAssemblyComponent(), destination, resDeploymentComponent);
		if (!successReplication) {
			LOG.error("concreteReplicateComponent(..) failed");
		} else {
			successDereplication = this.concreteDereplicateComponent(deploymentComponent);
		}

		if (!successDereplication) {
			LOG.error("concreteDereplicateComponent(..) failed");
		}

		return successReplication && successDereplication;
	}

	@Override
	protected boolean concreteDereplicateComponent(final DeploymentComponent deploymentComponent) {
		boolean success = false;

		try {
			LOG.info("concreteDereplicateComponent");

			final AssemblyComponent assemblyComponent = deploymentComponent.getAssemblyComponent();
			final ExecutionContainer executionContainer = deploymentComponent.getExecutionContainer();

			final EucalyptusCloudedApplication euCloudedApplication =
					this.eucaApplicationForAssemblyComponet(assemblyComponent, FALLBACK_IF_NO_ARCH2IMPL_APPNAME_MAPPING);
			if (euCloudedApplication == null) {
				LOG.error("Failed to lookup eucalyptus application for assembly component: " + assemblyComponent);
				return false;
			}

			final EucalyptusCloudNode euCloudNode = this.eucaNodeForExecutionContainer(executionContainer);
			if (euCloudNode == null) {
				LOG.error("Failed to lookup eucalyptus node for execution container" + executionContainer);
				return false;
			}

			final EucalyptusApplicationInstance appInstance =
					(EucalyptusApplicationInstance) this.eucalyptusApplicationCloudingService
							.lookupApplicationInstance(euCloudedApplication, euCloudNode);

			if (appInstance == null) {
				final String errorMsg = "appInstance for " + deploymentComponent + " is null";
				LOG.error(errorMsg);
				return false;
			}

			// TODO: add boolean return value for exit status?
			this.eucalyptusApplicationCloudingService.undeployApplicationInstance(appInstance);

			success = true;
		} catch (final ApplicationCloudingServiceException e) {
			LOG.error(e.getMessage(), e);
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

			final EucalyptusCloudNodeType euNodeType = this.euDefaultNodeType;

			final EucalyptusCloudNode euNode =
					(EucalyptusCloudNode) this.eucalyptusApplicationCloudingService.allocateNode(fqExecutionContainerName, euNodeType);

			if (euNode == null) {
				LOG.error("allocateNode(..) failed");
				success = false;
			} else {
				success = true;

				// TODO: does this make any sense?:
				// this.modelManager.getArch2ImplNameMappingManager().registerArch2implNameMapping(
				// EntityType.EXECUTION_CONTAINER, fqExecutionContainerName,
				// fqExecutionContainerName);

				this.modelManager.getArch2ImplNameMappingManager()
						.registerArch2implNameMapping(EntityType.EXECUTION_CONTAINER, fqExecutionContainerName, euNode.getName());
			}
		} catch (final ApplicationCloudingServiceException e) {
			LOG.error(e.getMessage(), e);
			success = false;
		}
		return success;
	}

	@Override
	protected boolean concreteDeallocateExecutionContainer(final ExecutionContainer executionContainer) {
		boolean success = false;

		try {
			LOG.info("concreteDeallocateExecutionContainer");

			final EucalyptusCloudNode euNode = this.eucaNodeForExecutionContainer(executionContainer);

			if (euNode == null) {
				LOG.error("Failed to lookup node");
				success = false;
			} else {
				// TODO: return value?
				this.eucalyptusApplicationCloudingService.deallocateNode(euNode);

				success = true;
			}
		} catch (final ApplicationCloudingServiceException e) {
			LOG.error(e.getMessage(), e);
			success = false;
		}
		return success;
	}

	private class EucalyptusEventLogger extends AbstractEucalyptusServiceEventLogger {

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
			return EucalyptusReconfigurationManager.this.getControlComponent().getCurrentTimeMillis();
		}
	}

	@Override
	protected DeploymentComponent createPreliminaryDeploymentComponentInModel(final AssemblyComponent assemblyComponent, final ExecutionContainer executionContainer) {
		// TODO: set preliminary flag or alike? (could adopt the procedure for execution containers)
		return ((ModelManager) this.getControlComponent().getModelManager()).getComponentDeploymentModelManager()
				.createAndRegisterDeploymentComponent(assemblyComponent, executionContainer);
	}

	@Override
	protected ExecutionContainer createPreliminaryExecutionContainerInModel(final String fullyQualifiedName, final ExecutionContainerType executionContainerType) {
		return ((ModelManager) this.getControlComponent().getModelManager()).getExecutionEnvironmentModelManager().
				createAndRegisterExecutionContainer(fullyQualifiedName, executionContainerType,/* do not mark allocated */false);
	}

	@Override
	protected boolean deletePreliminaryDeploymentComponentFromModel(final DeploymentComponent deploymentComponent) {
		return ((ModelManager) this.getControlComponent().getModelManager()).getComponentDeploymentModelManager().deleteDeploymentComponent(deploymentComponent);
	}

	@Override
	protected boolean deletePreliminaryExecutionContainerFromModel(final ExecutionContainer executionContainer) {
		return ((ModelManager) this.getControlComponent().getModelManager()).getExecutionEnvironmentModelManager().deallocateExecutionContainer(executionContainer);
	}

	@Override
	protected boolean deleteDeploymentComponentFromModel(final DeploymentComponent deploymentComponent) {
		LOG.info("deleteDeploymentComponentFromModel(...)");
		return ((ModelManager) this.getControlComponent().getModelManager()).getComponentDeploymentModelManager().deleteDeploymentComponent(deploymentComponent);
	}

	@Override
	protected boolean deleteExecutionContainerFromModel(final ExecutionContainer executionContainer) {
		LOG.info("deleteExecutionContainerFromModel");
		return ((ModelManager) this.getControlComponent().getModelManager()).getExecutionEnvironmentModelManager().deallocateExecutionContainer(executionContainer);
	}
}
