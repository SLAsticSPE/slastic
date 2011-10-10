package org.trustsoft.slastic.plugins.slasticImpl;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EObject;
import org.trustsoft.slastic.common.event.IObservationEvent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.control.components.modelManager.AbstractModelManagerComponent;
import org.trustsoft.slastic.plugins.slasticImpl.model.arch2implMapping.Arch2ImplNameMappingManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentDeployment.ComponentDeploymentModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment.ExecutionEnvironmentModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.reconfiguration.IReconfigurationManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.reconfiguration.ReconfigurationManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.usage.UsageModelManager;
import org.trustsoft.slastic.reconfiguration.ReconfigurationException;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyFactory;
import de.cau.se.slastic.metamodel.componentDeployment.ComponentDeploymentFactory;
import de.cau.se.slastic.metamodel.core.CoreFactory;
import de.cau.se.slastic.metamodel.core.SystemModel;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentFactory;
import de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;
import de.cau.se.slastic.metamodel.usage.UsageFactory;
import de.cau.se.slastic.metamodel.usage.UsageModel;

/**
 * 
 * @author Andre van Hoorn
 */
public class ModelManager extends AbstractModelManagerComponent {

	private static final Log log = LogFactory.getLog(ModelManager.class);

	// fields related to the type repository
	private static final String PROP_NAME_SYSTEM_MODEL__INPUT_FN =
			"systemModel-inputfn";
	/** Will be set in {@link #init()} */
	private volatile String systemModel_outputFile = "";
	/** Will be created in {@link #init()} */
	private volatile SystemModel systemModel;

	// fields related to the type repository
	private static final String PROP_NAME_USAGE_MODEL__INPUT_FN =
			"usageModel-inputfn";
	/** Will be set in {@link #init()} */
	private volatile String usageModel_outputFile = "";
	/** Will be created in {@link #init()} */
	private volatile UsageModel usageModel;

	// Managers for the system sub-models
	private volatile TypeRepositoryModelManager typeRepositoryManager;
	private volatile ComponentAssemblyModelManager componentAssemblyModelManager;
	private volatile ExecutionEnvironmentModelManager executionEnvironmentModelManager;
	private volatile ComponentDeploymentModelManager componentDeploymentModelManager;

	/** Manager handling the reconfigurations */
	private volatile IReconfigurationManager reconfigurationManager;

	/** Manager for the usage model */
	private volatile UsageModelManager usageModelManager;

	/**
	 * Manager for maintaining mappings between architectural and
	 * implementation-level among monitoring and reconfiguration managers
	 */
	private final Arch2ImplNameMappingManager arch2ImplNameMappingManager =
			new Arch2ImplNameMappingManager();

	/**
	 * @return the arch2ImplNameMappingManager
	 */
	public final Arch2ImplNameMappingManager getArch2ImplNameMappingManager() {
		return this.arch2ImplNameMappingManager;
	}

	public ModelManager() {
		// Model Manager with empty system and usage model
		this(ModelManager.createInitializedSystemModel());
	}

	public ModelManager(final String systemModel_inputFile) throws IOException {
		this(ModelManager.loadSystemModel(systemModel_inputFile));
	}

	public ModelManager(final SystemModel systemModel) {
		// Model Manager with given system model but empty usage model
		this(systemModel, ModelManager.createInitializedUsageModel());
	}

	public ModelManager(final SystemModel systemModel, final UsageModel usageModel) {
		this.systemModel = systemModel;
		this.usageModel = usageModel;
		this.initManagers();
	}

	public ModelManager(final String systemModel_inputFile, final String usageModel_inputFile) throws IOException {
		final EObject[] models = ModelManager.loadSystemAndUsageModel(systemModel_inputFile, usageModel_inputFile);
		this.systemModel = (SystemModel) models[0];
		this.usageModel = (UsageModel) models[1];
		this.initManagers();
	}

	public TypeRepositoryModelManager getTypeRepositoryManager() {
		return this.typeRepositoryManager;
	}

	public ComponentAssemblyModelManager getComponentAssemblyModelManager() {
		return this.componentAssemblyModelManager;
	}

	public ComponentDeploymentModelManager getComponentDeploymentModelManager() {
		return this.componentDeploymentModelManager;
	}

	public ExecutionEnvironmentModelManager getExecutionEnvironmentModelManager() {
		return this.executionEnvironmentModelManager;
	}

	public IReconfigurationManager getReconfigurationManager() {
		return this.reconfigurationManager;
	}

	public UsageModelManager getUsageModelManager() {
		return this.usageModelManager;
	}

	/**
	 * Initialized the managers for the sub-models type repository, component
	 * assembly, execution environment, and component deployment.
	 * 
	 * The system model ${@link #systemModel} and the {@link #usageModel} must
	 * not be null since the sub-models are extracted from it.
	 * 
	 * @return true on success, false otherwise
	 * @throws IllegalStateException
	 *             if ${@link #systemModel} or {@link #usageModel} is null.
	 */
	private boolean initManagers() {
		if (this.systemModel == null) {
			ModelManager.log.error("this.systemModel is null");
			throw new IllegalStateException("this.systemModel is null");
		}

		if (this.usageModel == null) {
			ModelManager.log.error("this.usageModel is null");
			throw new IllegalStateException("this.usageModel is null");
		}

		try {
			this.typeRepositoryManager = new TypeRepositoryModelManager(
					this.systemModel.getTypeRepositoryModel());
			this.componentAssemblyModelManager =
					new ComponentAssemblyModelManager(
							this.systemModel.getComponentAssemblyModel(),
							this.typeRepositoryManager);
			this.executionEnvironmentModelManager =
					new ExecutionEnvironmentModelManager(
							this.systemModel.getExecutionEnvironmentModel(),
							this.typeRepositoryManager);
			this.componentDeploymentModelManager =
					new ComponentDeploymentModelManager(
							this.systemModel.getComponentDeploymentModel(),
							this.typeRepositoryManager,
							this.componentAssemblyModelManager,
							this.executionEnvironmentModelManager);
			this.reconfigurationManager = new ReconfigurationManager(
					this.typeRepositoryManager,
					this.componentAssemblyModelManager,
					this.executionEnvironmentModelManager,
					this.componentDeploymentModelManager);
			this.usageModelManager = new UsageModelManager(this.usageModel);
		} catch (final Exception exc) {
			ModelManager.log.error("Error initializing managers", exc);
			return false;
		}
		return true;
	}

	@Override
	public boolean init() {
		final String systemModel_inputFile =
				super.getInitProperty(ModelManager.PROP_NAME_SYSTEM_MODEL__INPUT_FN, "");
		final String usageModel_inputFile =
				super.getInitProperty(ModelManager.PROP_NAME_USAGE_MODEL__INPUT_FN, "");

		if (systemModel_inputFile.isEmpty()) {
			// In this case, we create new system and usage models
			ModelManager.log.info("No input filename for system model given --- creating new models");
			this.systemModel = ModelManager.createInitializedSystemModel();
			this.usageModel = ModelManager.createInitializedUsageModel();
		} else {
			if (usageModel_inputFile.isEmpty()) {
				// Load system model from file but create empty usage model

				try {
					this.systemModel = ModelManager.loadSystemModel(systemModel_inputFile);
				} catch (final IOException ex) {
					ModelManager.log.error("Failed to load system model from "
								+ systemModel_inputFile, ex);
					return false;
				}

				ModelManager.log
						.info("No input filename for usage model given --- creating new model");
				this.usageModel = ModelManager.createInitializedUsageModel();
			} else {
				// Load both system model and usage from files
				try {
					final EObject[] models =
							ModelManager.loadSystemAndUsageModel(systemModel_inputFile, usageModel_inputFile);
					this.systemModel = (SystemModel) models[0];
					this.usageModel = (UsageModel) models[1];
				} catch (final IOException ex) {
					ModelManager.log.error("Failed to load usage model from "
								+ usageModel_inputFile, ex);
					return false;
				}
			}
		}

		this.systemModel_outputFile =
				this.getComponentContext().createFileInContextDir("output.slastic").getAbsolutePath();
		this.usageModel_outputFile =
				this.getComponentContext().createFileInContextDir("output.slasticusage").getAbsolutePath();

		return this.initManagers();
	}

	private static SystemModel loadSystemModel(final String systemModel_inputFile)
			throws IOException {
		ModelManager.log.info("Loading system model from file "
				+ systemModel_inputFile);
		return ModelIOUtils.loadSystemModel(systemModel_inputFile);
	}

	private static EObject[] loadSystemAndUsageModel(final String systemModel_inputFile,
			final String usageModel_inputFile)
			throws IOException {
		ModelManager.log.info("Loading system model from file " + systemModel_inputFile
				+ " and usage model from file " + usageModel_inputFile);
		return ModelIOUtils.loadSystemAndUsageModel(systemModel_inputFile, usageModel_inputFile);
	}

	@Override
	public void doReconfiguration(final SLAsticReconfigurationPlan plan) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void doReconfiguration(final ReconfigurationPlan plan)
			throws ReconfigurationException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void handleEvent(final IEvent ev) {
	}

	@Override
	public void newObservation(final IObservationEvent ioe) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void terminate(final boolean error) {
		ModelManager.log.info("Terminating model manager");
		this.saveModels();
	}

	public void saveModels(final String systemModelOutputFn, final String usageModelOutputFn) throws IOException {
		ModelIOUtils.saveModels(this.systemModel, systemModelOutputFn, this.usageModel, usageModelOutputFn);
	}

	private void saveModels() {
		try {
			this.saveModels(this.systemModel_outputFile, this.usageModel_outputFile);
		} catch (final IOException exc) {
			ModelManager.log.error(
					"An IOException occured while saving models", exc);
		}
	}

	@Override
	public boolean execute() {
		return true;
	}

	/**
	 * Creates a new system model with initialized (but empty) sub-models type
	 * repository model, component assembly model, execution environment model,
	 * and component deployment model.
	 * 
	 * This method was created because the EMF factory method does not create
	 * instances of the contained objects (in this case the sub-models).
	 * 
	 * @return the new system model
	 */
	public static SystemModel createInitializedSystemModel() {
		final SystemModel systemModel = CoreFactory.eINSTANCE
				.createSystemModel();
		systemModel.setTypeRepositoryModel(TypeRepositoryFactory.eINSTANCE
				.createTypeRepositoryModel());
		systemModel
				.setComponentAssemblyModel(ComponentAssemblyFactory.eINSTANCE
						.createComponentAssemblyModel());
		systemModel
				.setExecutionEnvironmentModel(ExecutionEnvironmentFactory.eINSTANCE
						.createExecutionEnvironmentModel());
		systemModel
				.setComponentDeploymentModel(ComponentDeploymentFactory.eINSTANCE
						.createComponentDeploymentModel());
		return systemModel;
	}
<<<<<<< HEAD
	
	public SystemModel getSystemModel(){
		return this.systemModel;
=======

	/**
	 * Creates a new and empty usage model.
	 * 
	 * @return
	 */
	public static UsageModel createInitializedUsageModel() {
		return UsageFactory.eINSTANCE.createUsageModel();
>>>>>>> a8d5080a7364ab65efb43922c690b1a0eba90d8a
	}
}
