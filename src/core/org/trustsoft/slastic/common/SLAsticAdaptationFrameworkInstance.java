package org.trustsoft.slastic.common;

import java.util.HashMap;
import java.util.Map;

import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.openarchitectureware.workflow.WorkflowRunner;
import org.openarchitectureware.workflow.monitor.ProgressMonitor;
import org.trustsoft.slastic.control.AbstractSLAsticControl;
import org.trustsoft.slastic.control.components.analysis.AbstractAdaptationPlanner;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformanceEvaluator;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformancePredictor;
import org.trustsoft.slastic.control.components.analysis.AbstractSLAsticAnalysis;
import org.trustsoft.slastic.control.components.analysis.AbstractWorkloadForecaster;
import org.trustsoft.slastic.control.components.modelManager.AbstractSLAsticModelManager;
import org.trustsoft.slastic.control.components.modelUpdater.AbstractSLAsticModelUpdater;
import org.trustsoft.slastic.monitoring.AbstractSLAsticMonitoringManager;
import org.trustsoft.slastic.reconfiguration.AbstractSLAsticReconfigurationManager;

import org.trustsoft.slastic.slasticqosannotations.ExternalServiceResponseTimeObjective;
import org.trustsoft.slastic.slasticqosannotations.InternalServiceResponseTimeObjective;
import org.trustsoft.slastic.slasticqosannotations.QoSObjective;
import org.trustsoft.slastic.slasticqosannotations.SLAsticQoSAnnotations;
import org.trustsoft.slastic.slasticresourceenvironment.SLAsticResourceEnvironment;
import org.trustsoft.slastic.slasticresourceenvironment.ServerNode;

/**
 * @author Andre van Hoorn, Lena Stoever
 */
public class SLAsticAdaptationFrameworkInstance {

	private static final Log log = LogFactory.getLog(SLAsticAdaptationFrameworkInstance.class);
	private AbstractSLAsticControl controller;
	private AbstractSLAsticMonitoringManager monitoringManager;
	private AbstractSLAsticReconfigurationManager reconfigurationMgr;
	private final Properties controllerProps = new Properties();
	private final Properties monitoringProps = new Properties();
	private final Properties reconfigurationProps = new Properties();
	private final Properties modelManagerProps = new Properties();
	private final Properties modelUpdaterProps = new Properties();
	private final Properties analysisProps = new Properties();
	private final Properties performanceEvaluatorProps = new Properties();
	private final Properties workloadForecasterProps = new Properties();
	private final Properties performancePredictorProps = new Properties();
	private final Properties adaptationPlannerProps = new Properties();
	private final static String COMPONENT_CLASSNAME_PROPNAME = "classname";

	/* Avoid construction via default constructor */
	private SLAsticAdaptationFrameworkInstance() {
		this.controller = null;
		this.monitoringManager = null;
		this.reconfigurationMgr = null;
	}

	public SLAsticAdaptationFrameworkInstance(Properties prop) {
		loadProperties(prop);
	}

	private void initProperties(final Properties prop)
			throws IllegalArgumentException {
		for (String curPropName : prop.stringPropertyNames()) {
			boolean storedProp = false;
			if (curPropName.startsWith(AbstractPerformanceEvaluator.PROP_PREFIX
					+ ".")) {
				storedProp = true;
				this.performanceEvaluatorProps.setProperty(curPropName
						.replaceFirst(AbstractPerformanceEvaluator.PROP_PREFIX
								+ ".", ""), prop.getProperty(curPropName));
			}
			if (curPropName.startsWith(AbstractWorkloadForecaster.PROP_PREFIX)) {
				storedProp = true;
				this.workloadForecasterProps.setProperty(curPropName
						.replaceFirst(AbstractWorkloadForecaster.PROP_PREFIX
								+ ".", ""), prop.getProperty(curPropName));
			}
			if (curPropName
					.startsWith(AbstractPerformancePredictor.PROP_PREFIX)) {
				storedProp = true;
				this.performancePredictorProps.setProperty(curPropName
						.replaceFirst(AbstractPerformancePredictor.PROP_PREFIX
								+ ".", ""), prop.getProperty(curPropName));
			}
			if (curPropName.startsWith(AbstractAdaptationPlanner.PROP_PREFIX)) {
				storedProp = true;
				this.adaptationPlannerProps.setProperty(curPropName
						.replaceFirst(AbstractAdaptationPlanner.PROP_PREFIX
								+ ".", ""), prop.getProperty(curPropName));
			}
			if (curPropName.startsWith(AbstractSLAsticAnalysis.PROP_PREFIX)) {
				storedProp = true;
				this.analysisProps.setProperty(curPropName.replaceFirst(
						AbstractSLAsticAnalysis.PROP_PREFIX + ".", ""), prop
						.getProperty(curPropName));
			}
			if (curPropName.startsWith(AbstractSLAsticModelManager.PROP_PREFIX)) {
				storedProp = true;
				this.modelManagerProps.setProperty(curPropName.replaceFirst(
						AbstractSLAsticModelManager.PROP_PREFIX + ".", ""),
						prop.getProperty(curPropName));
			}
			if (curPropName.startsWith(AbstractSLAsticModelUpdater.PROP_PREFIX)) {
				storedProp = true;
				this.modelUpdaterProps.setProperty(curPropName.replaceFirst(
						AbstractSLAsticModelUpdater.PROP_PREFIX + ".", ""),
						prop.getProperty(curPropName));
			}
			if (curPropName.startsWith(AbstractSLAsticControl.PROP_PREFIX)) {
				storedProp = true;
				this.controllerProps.setProperty(curPropName.replaceFirst(
						AbstractSLAsticControl.PROP_PREFIX + ".", ""), prop
						.getProperty(curPropName));
			}
			if (curPropName
					.startsWith(AbstractSLAsticMonitoringManager.PROP_PREFIX)) {
				storedProp = true;
				this.monitoringProps.setProperty(
						curPropName.replaceFirst(
								AbstractSLAsticMonitoringManager.PROP_PREFIX
										+ ".", ""), prop
								.getProperty(curPropName));
			}
			if (curPropName
					.startsWith(AbstractSLAsticReconfigurationManager.PROP_PREFIX)) {
				storedProp = true;
				this.reconfigurationProps
						.setProperty(
								curPropName
										.replaceFirst(
												AbstractSLAsticReconfigurationManager.PROP_PREFIX
														+ ".", ""), prop
										.getProperty(curPropName));
			}
			if (!storedProp) {
				log.warn("Unknown property name '" + curPropName + "");
			}
		}
	}

	private void loadProperties(Properties prop)
			throws IllegalArgumentException {
		try {
			this.initProperties(prop);

			/* Load all components */
			String monitoringComponentClassnameProperty = this.monitoringProps
					.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
			if (monitoringComponentClassnameProperty == null
					|| monitoringComponentClassnameProperty.length() <= 0) {
				log.error("Missing configuration property value for '"
						+ AbstractSLAsticMonitoringManager.PROP_PREFIX + "."
						+ SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME + "'");
			}
			this.monitoringManager = (AbstractSLAsticMonitoringManager) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
					monitoringComponentClassnameProperty, this.monitoringProps);

			String controlComponentClassnameProperty = this.controllerProps
					.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
			if (controlComponentClassnameProperty == null
					|| controlComponentClassnameProperty.length() <= 0) {
				log.error("Missing configuration property value for '"
						+ AbstractSLAsticControl.PROP_PREFIX + "."
						+ SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME + "'");
			} else {
				this.controller = (AbstractSLAsticControl) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
						controlComponentClassnameProperty, this.controllerProps);
			}

			AbstractSLAsticModelManager modelManagerComponent = null;
			String modelManagerComponentClassnameProperty = this.modelManagerProps
					.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
			if (modelManagerComponentClassnameProperty == null
					|| modelManagerComponentClassnameProperty.length() <= 0) {
				log.error("Missing configuration property value for '"
						+ AbstractSLAsticModelManager.PROP_PREFIX + "."
						+ SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME + "'");
			} else {
				modelManagerComponent = (AbstractSLAsticModelManager) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
						modelManagerComponentClassnameProperty,
						this.modelManagerProps);
			}

			AbstractSLAsticModelUpdater modelUpdaterComponent = null;
			String modelUpdaterComponentClassnameProperty = this.modelUpdaterProps
					.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
			if (modelUpdaterComponentClassnameProperty == null
					|| modelUpdaterComponentClassnameProperty.length() <= 0) {
				log.error("Missing configuration property value for '"
						+ AbstractSLAsticModelUpdater.PROP_PREFIX + "."
						+ SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME + "'");
			} else {
				modelUpdaterComponent = (AbstractSLAsticModelUpdater) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
						modelUpdaterComponentClassnameProperty,
						this.modelUpdaterProps);
			}

			AbstractSLAsticAnalysis analysisComponent = null;
			String analysisComponentClassnameProperty = this.analysisProps
					.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
			if (analysisComponentClassnameProperty == null
					|| analysisComponentClassnameProperty.length() <= 0) {
				log.error("Missing configuration property value for '"
						+ AbstractSLAsticAnalysis.PROP_PREFIX + "."
						+ SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME + "'");
			} else {
				analysisComponent = (AbstractSLAsticAnalysis) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
						analysisComponentClassnameProperty, this.analysisProps);
			}

			String performanceEvaluatorComponentClassnameProperty = this.performanceEvaluatorProps
					.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
			AbstractPerformanceEvaluator performanceEvaluatorComponent = null;
			// Note: a performance evaluator component is not mandatory
			if (performanceEvaluatorComponentClassnameProperty != null
					&& performanceEvaluatorComponentClassnameProperty.length() > 0) {
				performanceEvaluatorComponent = (AbstractPerformanceEvaluator) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
						performanceEvaluatorComponentClassnameProperty,
						this.performanceEvaluatorProps);
			}

			String workloadForecasterComponentClassnameProperty = this.workloadForecasterProps
					.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
			AbstractWorkloadForecaster workloadForecasterComponent = null;
			// Note: a workload forecaster component is not mandatory
			if (workloadForecasterComponentClassnameProperty != null
					&& workloadForecasterComponentClassnameProperty.length() > 0) {
				workloadForecasterComponent = (AbstractWorkloadForecaster) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
						workloadForecasterComponentClassnameProperty,
						this.workloadForecasterProps);
			}

			String performancePredictorComponentClassnameProperty = this.performancePredictorProps
					.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
			AbstractPerformancePredictor performancePredictorComponent = null;
			// Note: a performance predictor component is not mandatory
			if (performancePredictorComponentClassnameProperty != null
					&& performancePredictorComponentClassnameProperty.length() > 0) {
				performancePredictorComponent = (AbstractPerformancePredictor) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
						performancePredictorComponentClassnameProperty,
						this.performancePredictorProps);
			}

			String adaptationPlannerComponentClassnameProperty = this.adaptationPlannerProps
					.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
			AbstractAdaptationPlanner adaptationPlannerComponent = null;
			// Note: a performance predictor component is not mandatory
			if (adaptationPlannerComponentClassnameProperty != null
					&& adaptationPlannerComponentClassnameProperty.length() > 0) {
				adaptationPlannerComponent = (AbstractAdaptationPlanner) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
						adaptationPlannerComponentClassnameProperty,
						this.adaptationPlannerProps);
			}

			String reconfigurationManagerComponentClassnameProperty = this.reconfigurationProps
					.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
			if (reconfigurationManagerComponentClassnameProperty == null
					|| reconfigurationManagerComponentClassnameProperty
							.length() <= 0) {
				log.error("Missing configuration property value for "
						+ AbstractSLAsticReconfigurationManager.PROP_PREFIX
						+ "." + SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME
						+ "'");
			}
			this.reconfigurationMgr = (AbstractSLAsticReconfigurationManager) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
					reconfigurationManagerComponentClassnameProperty,
					this.reconfigurationProps);

			/* Assert that mandatory components are available */
			boolean success = true;
			if (this.reconfigurationMgr == null) {
				log.error("reconfigurationManagerComponent is null");
				success = false;
			}
			if (this.controller == null) {
				log.error("slasticCtrlComponent is null");
				success = false;
			}
			if (modelManagerComponent == null) {
				log.error("modelManagerComponent is null");
				success = false;
			}
			if (modelUpdaterComponent == null) {
				log.error("modelUpdaterComponent is null");
				success = false;
			}
			if (analysisComponent == null) {
				log.error("analysisComponent is null");
				success = false;
			}
			if (!success) {
				throw new IllegalArgumentException(
						"Failed to load at least one component");
			}

			/* "wire" the components */
                        this.monitoringManager.setController(this.controller);
			this.reconfigurationMgr.setControlComponent(this.controller);

			this.controller.setReconfigurationManager(this.reconfigurationMgr); // TODO:
																				// fix!
			this.controller.setAnalysis(analysisComponent);
			this.controller.setModelManager(modelManagerComponent);
			this.controller.setModelUpdater(modelUpdaterComponent);

			modelManagerComponent.setParentControlComponent(this.controller);
			modelUpdaterComponent.setParentControlComponent(this.controller);
			modelUpdaterComponent.setModelManager(modelManagerComponent);

			analysisComponent.setParentControlComponent(this.controller);
			analysisComponent
					.setPerformanceEvaluator(performanceEvaluatorComponent);
			analysisComponent
					.setWorkloadForecaster(workloadForecasterComponent);
			analysisComponent
					.setPerformancePredictor(performancePredictorComponent);
			analysisComponent.setAdaptationPlanner(adaptationPlannerComponent);

			if (performanceEvaluatorComponent != null) {
				performanceEvaluatorComponent
						.setParentAnalysisComponent(analysisComponent);
			}
			if (workloadForecasterComponent != null) {
				workloadForecasterComponent
						.setParentAnalysisComponent(analysisComponent);
			}
			if (performancePredictorComponent != null) {
				performancePredictorComponent
						.setParentAnalysisComponent(analysisComponent);
			}
			if (adaptationPlannerComponent != null) {
				adaptationPlannerComponent
						.setParentAnalysisComponent(analysisComponent);
			}

			/* Initialize event handling */
			this.controller.addListener(modelManagerComponent);
			this.controller.addListener(modelUpdaterComponent);
			if (performanceEvaluatorComponent != null) {
				this.controller.addListener(performanceEvaluatorComponent);
				performanceEvaluatorComponent
						.setSimpleSLAsticEventService(this.controller);
			}
			if (workloadForecasterComponent != null) {
				this.controller.addListener(workloadForecasterComponent);
				workloadForecasterComponent
						.setSimpleSLAsticEventService(this.controller);
			}
			if (performancePredictorComponent != null) {
				this.controller.addListener(performancePredictorComponent);
				performancePredictorComponent
						.setSimpleSLAsticEventService(this.controller);
			}
			if (adaptationPlannerComponent != null) {
				this.controller.addListener(adaptationPlannerComponent);
				adaptationPlannerComponent
						.setSimpleSLAsticEventService(this.controller);
			}

		} catch (Exception exc) {
			log.error("An error occured", exc);
			throw new IllegalArgumentException("An error occured", exc);
		}
	}

	/** Start instance. The method returns immediately. */
	public void run() {
		this.controller.execute();
		this.reconfigurationMgr.execute();
		this.monitoringManager.execute();
	}

	/** Terminate instance */
	public void terminate() {
		this.controller.terminate();
		this.monitoringManager.terminate();
		this.reconfigurationMgr.terminate();
	}

	private static void testSLAsticMetaModel() {
		// String wfFile
		// ="C:/workspace/slastic/src/org/trustsoft/slastic/control/InitModels.oaw";
		// String wfFile =
		// "../../../workspace2/SLAstic-Framework/trunk/src/org/trustsoft/slastic/control/InitModelsMac.oaw";
		final String INIT_WORKFLOW_FN = "/home/voorn/svn_work/sw_DALenaRobert/SLAstic-Framework/branches/20091022-Andre/META-INF/oaw/InitModels-Andre.oaw";
		Map<String, String> properties = new HashMap<String, String>();
		Map<String, String> slotContents = new HashMap<String, String>();

		// workflow runner of the oAW-framework
		WorkflowRunner runner = new WorkflowRunner();
		// Note, I had to add the (ProgressMonitor) cast in order to remove
		// netbeans-reported errors
		runner.run(INIT_WORKFLOW_FN,
				(ProgressMonitor) new NullProgressMonitor(), properties,
				slotContents);
		slal.Model slas = (slal.Model) runner.getContext().get("theModel");
		reconfMM.ReconfigurationModel reconfigurationModel = (reconfMM.ReconfigurationModel) runner
				.getContext().get("reconfigurationModel");

		// OLD (from Andrés branch):
		// //Das sollte nun immer gemacht werden:
		// ModelManager.getInstance().initModel(reconfigurationModel);
		//
		// //SLAChecker slaChecker = new SLAChecker(slas);
		// ModelUpdater modelUpdater = new
		// ModelUpdater(reconfigurationModel.getMaxResponseTimes());
		// //IKiekerMonitoringLogReader logReader = new
		// FSReaderRealtime(inputDir, 7);
		// IKiekerMonitoringLogReader logReader = new
		// JMSReader("tcp://127.0.0.1:3035/", "queue1");
		// MonitoringRecordTypeLogger recordTypeLogger = new
		// MonitoringRecordTypeLogger();
		// //slaChecker.start();

		/* Dumps the record type ID */
		// tpanInstance.addConsumer(new MonitoringRecordTypeLogger());
		/* Collects all executions */
		// ExecutionSequenceRepositoryFiller seqRepConsumer = new
		// ExecutionSequenceRepositoryFiller();
		// tpanInstance.addConsumer(seqRepConsumer);
		/* Dumps response times */
		// ResponseTimePlotter rtPlotter = new ResponseTimePlotter();
		// tpanInstance.addConsumer(rtPlotter);
		// String wfFile = "../../SLALproject/src/SLALproject.oaw";
		// ISLAsticReconfigurationManager reconfigurationManager =
		// new ReconfigurationManagerWget();
		// final int NUM_RECONFIGURATIONS = 5;
		// for (int i = 1; i <= NUM_RECONFIGURATIONS; i++) {
		// try {
		// log.info("Requesting reconfiguration " + i + "/" +
		// NUM_RECONFIGURATIONS);
		// reconfigurationManager.doReconfiguration(null);
		// Thread.sleep(5000);
		// } catch (Exception ex) {
		// Logger.getLogger(ControlComponent.class.getName()).log(Level.SEVERE,
		// null, ex);
		// }
		// }
		// System.exit(0);
		/* SLAsticResourceEnvironment */
		SLAsticResourceEnvironment resourceEnvironment = (SLAsticResourceEnvironment) runner
				.getContext().get("resourceEnvironmentModel");
		log.info("\n*SLAsticResourceEnvironment: "
				+ resourceEnvironment.getId() + "*\nServers in pool:");
		for (ServerNode n : resourceEnvironment
				.getResourceEnvironment_ServerPool()
				.getServerPool_serverNodes()) {
			log.info(n.getServerNode_PCMResourceContainer().getEntityName());
		}

		/* SLAsticQoSAnnotations */
		SLAsticQoSAnnotations qosAnnotations = (SLAsticQoSAnnotations) runner
				.getContext().get("qosAnnotationsModel");
		log.info("\n*SLAsticQoSAnnotations*");
		for (QoSObjective o : qosAnnotations.getQosAnnotations_qosObjectives()) {
			if (o instanceof ExternalServiceResponseTimeObjective) {
				ExternalServiceResponseTimeObjective eo = (ExternalServiceResponseTimeObjective) o;
				log.info("System: " + eo.getSystem().getEntityName());
				log.info("Value: " + eo.getValue());
				log.info("Time Window: " + eo.getTimeWindow());
				log.info("Service: " + eo.getService());
				log.info("Providing Role: " + eo.getProvidingRole());
			}

			if (o instanceof InternalServiceResponseTimeObjective) {
				InternalServiceResponseTimeObjective io = (InternalServiceResponseTimeObjective) o;
				log.info("Component: " + io.getComponent().getEntityName());
				log.info("Value: " + io.getValue());
				log.info("Time Window: " + io.getTimeWindow());
				log.info("Service: " + io.getService());
				log.info("Providing Role: " + io.getProvidingRole());
			}
		}
	}
}
