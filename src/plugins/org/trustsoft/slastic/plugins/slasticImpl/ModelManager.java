package org.trustsoft.slastic.plugins.slasticImpl;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.event.IObservationEvent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.control.components.modelManager.AbstractModelManagerComponent;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentDeployment.ComponentDeploymentModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment.ExecutionEnvironmentModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.reconfiguration.IReconfigurationManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.reconfiguration.ReconfigurationManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;
import org.trustsoft.slastic.reconfiguration.ReconfigurationException;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyFactory;
import de.cau.se.slastic.metamodel.componentDeployment.ComponentDeploymentFactory;
import de.cau.se.slastic.metamodel.core.CoreFactory;
import de.cau.se.slastic.metamodel.core.SystemModel;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentFactory;
import de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;

/**
 * 
 * @author Andre van Hoorn
 */
public class ModelManager extends AbstractModelManagerComponent {

	private static final Log log = LogFactory.getLog(ModelManager.class);

	/* fields related to the type repository */
	private static final String PROP_NAME_SYSTEM_MODEL__INPUT_FN = "systemModel-inputfn";
	private static final String PROP_NAME_SYSTEM_MODEL__OUTPUT_FN = "systemModel-outputputfn";
	// private volatile String systemModel_inputFile;
	private volatile String systemModel_outputFile = "";
	private volatile SystemModel systemModel;

	/* Managers for the submodels */
	private volatile TypeRepositoryModelManager typeRepositoryManager;
	private volatile ComponentAssemblyModelManager componentAssemblyModelManager;
	private volatile ExecutionEnvironmentModelManager executionEnvironmentModelManager;
	private volatile ComponentDeploymentModelManager componentDeploymentModelManager;
	private volatile IReconfigurationManager reconfigurationManager;

	public ModelManager() {
		this.systemModel = ModelManager.createInitializedSystemModel();
		this.initManagers();
	}

	public ModelManager(final SystemModel systemModel) {
		this.systemModel = systemModel;
		this.initManagers();
	}

	public ModelManager(final String systemModel_inputFile) throws IOException {
		this.systemModel = this.loadModel(systemModel_inputFile);
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
	
	/**
	 * Initialized the managers for the submodels type repository, component
	 * assembly, execution environment, and component deployment.
	 * 
	 * The system model ${@link #systemModel} must not be null since the
	 * submodels are extracted from it.
	 * 
	 * @return true on success, false otherwise
	 * @throws IllegalStateException
	 *             if ${@link #systemModel} is null.
	 */
	private boolean initManagers() {
		if (this.systemModel == null) {
			ModelManager.log.error("this.systemModel is null");
			throw new IllegalStateException("this.systemModel is null");
		}

		try {
			this.typeRepositoryManager = new TypeRepositoryModelManager(
					this.systemModel.getTypeRepositoryModel());
			this.componentAssemblyModelManager = new ComponentAssemblyModelManager(
					this.systemModel.getComponentAssemblyModel());
			this.executionEnvironmentModelManager = new ExecutionEnvironmentModelManager(
					this.systemModel.getExecutionEnvironmentModel());
			this.componentDeploymentModelManager = new ComponentDeploymentModelManager(
					this.systemModel.getComponentDeploymentModel());
			this.reconfigurationManager = new ReconfigurationManager(
					this.typeRepositoryManager,
					this.componentAssemblyModelManager,
					this.executionEnvironmentModelManager,
					this.componentDeploymentModelManager);
		} catch (final Exception exc) {
			ModelManager.log.error("Error initializing managers", exc);
			return false;
		}
		return true;
	}

	@Override
	public boolean init() {
		final String systemModel_inputFile = super.getInitProperty(
				ModelManager.PROP_NAME_SYSTEM_MODEL__INPUT_FN, "");
		this.systemModel_outputFile = super.getInitProperty(
				ModelManager.PROP_NAME_SYSTEM_MODEL__OUTPUT_FN, "");

		if (systemModel_inputFile.isEmpty()) {
			ModelManager.log
					.info("No input filename for system model given --- creating new model");
			this.systemModel = ModelManager.createInitializedSystemModel();
		} else {
			ModelManager.log.info("Loading system model from file "
					+ systemModel_inputFile);
			try {
				this.systemModel = this.loadModel(systemModel_inputFile);
			} catch (final IOException ex) {
				ModelManager.log.error("Failed to load system model from "
						+ systemModel_inputFile, ex);
				return false;
			}
		}
		return this.initManagers();
	}

	private SystemModel loadModel(final String systemModel_inputFile)
			throws IOException {
		ModelManager.log.info("Loading system model from file "
				+ systemModel_inputFile);
		return ModelIOUtils.loadSystemModel(systemModel_inputFile);
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
		this.saveModel();
	}

	public void saveModel(final String outputFn) throws IOException {
		ModelIOUtils.saveSystemModel(this.systemModel, outputFn);
	}

	private void saveModel() {
		try {
			if (!this.systemModel_outputFile.isEmpty()) {
				this.saveModel(this.systemModel_outputFile);
			}
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
}
