package org.trustsoft.slastic.plugins.starter;

import java.util.HashMap;
import java.util.Map;

import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.openarchitectureware.workflow.WorkflowRunner;
import org.openarchitectureware.workflow.monitor.ProgressMonitor;
import org.trustsoft.slastic.common.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.AbstractControlComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractAdaptationPlannerComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractAnalysisComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformanceEvaluatorComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformancePredictorComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractWorkloadForecasterComponent;
import org.trustsoft.slastic.control.components.modelManager.AbstractModelManagerComponent;
import org.trustsoft.slastic.control.components.modelUpdater.AbstractModelUpdaterComponent;
import org.trustsoft.slastic.monitoring.AbstractMonitoringManagerComponent;
import org.trustsoft.slastic.reconfiguration.AbstractReconfigurationManagerComponent;

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
    private AbstractControlComponent controller;
    private AbstractMonitoringManagerComponent monitoringManager;
    private AbstractReconfigurationManagerComponent reconfigurationMgr;
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
            if (curPropName.startsWith(AbstractPerformanceEvaluatorComponent.PROP_PREFIX
                    + ".")) {
                storedProp = true;
                this.performanceEvaluatorProps.setProperty(curPropName.replaceFirst(AbstractPerformanceEvaluatorComponent.PROP_PREFIX
                        + ".", ""), prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractWorkloadForecasterComponent.PROP_PREFIX)) {
                storedProp = true;
                this.workloadForecasterProps.setProperty(curPropName.replaceFirst(AbstractWorkloadForecasterComponent.PROP_PREFIX
                        + ".", ""), prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractPerformancePredictorComponent.PROP_PREFIX)) {
                storedProp = true;
                this.performancePredictorProps.setProperty(curPropName.replaceFirst(AbstractPerformancePredictorComponent.PROP_PREFIX
                        + ".", ""), prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractAdaptationPlannerComponent.PROP_PREFIX)) {
                storedProp = true;
                this.adaptationPlannerProps.setProperty(curPropName.replaceFirst(AbstractAdaptationPlannerComponent.PROP_PREFIX
                        + ".", ""), prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractAnalysisComponent.PROP_PREFIX)) {
                storedProp = true;
                this.analysisProps.setProperty(curPropName.replaceFirst(
                        AbstractAnalysisComponent.PROP_PREFIX + ".", ""), prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractModelManagerComponent.PROP_PREFIX)) {
                storedProp = true;
                this.modelManagerProps.setProperty(curPropName.replaceFirst(
                        AbstractModelManagerComponent.PROP_PREFIX + ".", ""),
                        prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractModelUpdaterComponent.PROP_PREFIX)) {
                storedProp = true;
                this.modelUpdaterProps.setProperty(curPropName.replaceFirst(
                        AbstractModelUpdaterComponent.PROP_PREFIX + ".", ""),
                        prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractControlComponent.PROP_PREFIX)) {
                storedProp = true;
                this.controllerProps.setProperty(curPropName.replaceFirst(
                        AbstractControlComponent.PROP_PREFIX + ".", ""), prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractMonitoringManagerComponent.PROP_PREFIX)) {
                storedProp = true;
                this.monitoringProps.setProperty(
                        curPropName.replaceFirst(
                        AbstractMonitoringManagerComponent.PROP_PREFIX
                        + ".", ""), prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractReconfigurationManagerComponent.PROP_PREFIX)) {
                storedProp = true;
                this.reconfigurationProps.setProperty(
                        curPropName.replaceFirst(
                        AbstractReconfigurationManagerComponent.PROP_PREFIX
                        + ".", ""), prop.getProperty(curPropName));
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
            String monitoringComponentClassnameProperty = this.monitoringProps.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
            if (monitoringComponentClassnameProperty == null
                    || monitoringComponentClassnameProperty.length() <= 0) {
                log.error("Missing configuration property value for '"
                        + AbstractMonitoringManagerComponent.PROP_PREFIX + "."
                        + SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME + "'");
            }
            this.monitoringManager = (AbstractMonitoringManagerComponent) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
                    monitoringComponentClassnameProperty, this.monitoringProps);

            String controlComponentClassnameProperty = this.controllerProps.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
            if (controlComponentClassnameProperty == null
                    || controlComponentClassnameProperty.length() <= 0) {
                log.error("Missing configuration property value for '"
                        + AbstractControlComponent.PROP_PREFIX + "."
                        + SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME + "'");
            } else {
                this.controller = (AbstractControlComponent) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
                        controlComponentClassnameProperty, this.controllerProps);
            }

            AbstractModelManagerComponent modelManagerComponent = null;
            String modelManagerComponentClassnameProperty = this.modelManagerProps.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
            if (modelManagerComponentClassnameProperty == null
                    || modelManagerComponentClassnameProperty.length() <= 0) {
                log.error("Missing configuration property value for '"
                        + AbstractModelManagerComponent.PROP_PREFIX + "."
                        + SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME + "'");
            } else {
                modelManagerComponent = (AbstractModelManagerComponent) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
                        modelManagerComponentClassnameProperty,
                        this.modelManagerProps);
            }

            AbstractModelUpdaterComponent modelUpdaterComponent = null;
            String modelUpdaterComponentClassnameProperty = this.modelUpdaterProps.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
            if (modelUpdaterComponentClassnameProperty == null
                    || modelUpdaterComponentClassnameProperty.length() <= 0) {
                log.error("Missing configuration property value for '"
                        + AbstractModelUpdaterComponent.PROP_PREFIX + "."
                        + SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME + "'");
            } else {
                modelUpdaterComponent = (AbstractModelUpdaterComponent) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
                        modelUpdaterComponentClassnameProperty,
                        this.modelUpdaterProps);
            }

            AbstractAnalysisComponent analysisComponent = null;
            String analysisComponentClassnameProperty = this.analysisProps.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
            if (analysisComponentClassnameProperty == null
                    || analysisComponentClassnameProperty.length() <= 0) {
                log.error("Missing configuration property value for '"
                        + AbstractAnalysisComponent.PROP_PREFIX + "."
                        + SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME + "'");
            } else {
                analysisComponent = (AbstractAnalysisComponent) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
                        analysisComponentClassnameProperty, this.analysisProps);
            }

            String performanceEvaluatorComponentClassnameProperty = this.performanceEvaluatorProps.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
            AbstractPerformanceEvaluatorComponent performanceEvaluatorComponent = null;
            // Note: a performance evaluator component is not mandatory
            if (performanceEvaluatorComponentClassnameProperty != null
                    && performanceEvaluatorComponentClassnameProperty.length() > 0) {
                performanceEvaluatorComponent = (AbstractPerformanceEvaluatorComponent) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
                        performanceEvaluatorComponentClassnameProperty,
                        this.performanceEvaluatorProps);
            }

            String workloadForecasterComponentClassnameProperty = this.workloadForecasterProps.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
            AbstractWorkloadForecasterComponent workloadForecasterComponent = null;
            // Note: a workload forecaster component is not mandatory
            if (workloadForecasterComponentClassnameProperty != null
                    && workloadForecasterComponentClassnameProperty.length() > 0) {
                workloadForecasterComponent = (AbstractWorkloadForecasterComponent) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
                        workloadForecasterComponentClassnameProperty,
                        this.workloadForecasterProps);
            }

            String performancePredictorComponentClassnameProperty = this.performancePredictorProps.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
            AbstractPerformancePredictorComponent performancePredictorComponent = null;
            // Note: a performance predictor component is not mandatory
            if (performancePredictorComponentClassnameProperty != null
                    && performancePredictorComponentClassnameProperty.length() > 0) {
                performancePredictorComponent = (AbstractPerformancePredictorComponent) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
                        performancePredictorComponentClassnameProperty,
                        this.performancePredictorProps);
            }

            String adaptationPlannerComponentClassnameProperty = this.adaptationPlannerProps.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
            AbstractAdaptationPlannerComponent adaptationPlannerComponent = null;
            // Note: a performance predictor component is not mandatory
            if (adaptationPlannerComponentClassnameProperty != null
                    && adaptationPlannerComponentClassnameProperty.length() > 0) {
                adaptationPlannerComponent = (AbstractAdaptationPlannerComponent) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
                        adaptationPlannerComponentClassnameProperty,
                        this.adaptationPlannerProps);
            }

            String reconfigurationManagerComponentClassnameProperty = this.reconfigurationProps.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
            if (reconfigurationManagerComponentClassnameProperty == null
                    || reconfigurationManagerComponentClassnameProperty.length() <= 0) {
                log.error("Missing configuration property value for "
                        + AbstractReconfigurationManagerComponent.PROP_PREFIX
                        + "." + SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME
                        + "'");
            }
            this.reconfigurationMgr = (AbstractReconfigurationManagerComponent) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
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
            analysisComponent.setPerformanceEvaluator(performanceEvaluatorComponent);
            analysisComponent.setWorkloadForecaster(workloadForecasterComponent);
            analysisComponent.setPerformancePredictor(performancePredictorComponent);
            analysisComponent.setAdaptationPlanner(adaptationPlannerComponent);

            if (performanceEvaluatorComponent != null) {
                performanceEvaluatorComponent.setParentAnalysisComponent(analysisComponent);
            }
            if (workloadForecasterComponent != null) {
                workloadForecasterComponent.setParentAnalysisComponent(analysisComponent);
            }
            if (performancePredictorComponent != null) {
                performancePredictorComponent.setParentAnalysisComponent(analysisComponent);
            }
            if (adaptationPlannerComponent != null) {
                adaptationPlannerComponent.setParentAnalysisComponent(analysisComponent);
            }

            /* Initialize event handling */
            this.controller.addListener(modelManagerComponent);
            this.controller.addListener(modelUpdaterComponent);
            if (performanceEvaluatorComponent != null) {
                this.controller.addListener(performanceEvaluatorComponent);
                performanceEvaluatorComponent.setSimpleSLAsticEventService(this.controller);
            }
            if (workloadForecasterComponent != null) {
                this.controller.addListener(workloadForecasterComponent);
                workloadForecasterComponent.setSimpleSLAsticEventService(this.controller);
            }
            if (performancePredictorComponent != null) {
                this.controller.addListener(performancePredictorComponent);
                performancePredictorComponent.setSimpleSLAsticEventService(this.controller);
            }
            if (adaptationPlannerComponent != null) {
                this.controller.addListener(adaptationPlannerComponent);
                adaptationPlannerComponent.setSimpleSLAsticEventService(this.controller);
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
    public void terminate(final boolean error) {
        this.controller.terminate(error);
        this.monitoringManager.terminate(error);
        this.reconfigurationMgr.terminate(error);
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
        reconfMM.ReconfigurationModel reconfigurationModel = (reconfMM.ReconfigurationModel) runner.getContext().get("reconfigurationModel");

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
        SLAsticResourceEnvironment resourceEnvironment = (SLAsticResourceEnvironment) runner.getContext().get("resourceEnvironmentModel");
        log.info("\n*SLAsticResourceEnvironment: "
                + resourceEnvironment.getId() + "*\nServers in pool:");
        for (ServerNode n : resourceEnvironment.getResourceEnvironment_ServerPool().getServerPool_serverNodes()) {
            log.info(n.getServerNode_PCMResourceContainer().getEntityName());
        }

        /* SLAsticQoSAnnotations */
        SLAsticQoSAnnotations qosAnnotations = (SLAsticQoSAnnotations) runner.getContext().get("qosAnnotationsModel");
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
