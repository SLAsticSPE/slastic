package org.trustsoft.slastic.common;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import java.util.Properties;
import kieker.common.logReader.IKiekerMonitoringLogReader;
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
public class SLAsticInstance {

    private static final Log log = LogFactory.getLog(SLAsticInstance.class);
    private final AbstractSLAsticControl controller;
    private final AbstractSLAsticMonitoringManager monitoringManager;
    private final AbstractSLAsticReconfigurationManager reconfigurationMgr;
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
    private SLAsticInstance() {
        this.controller = null;
        this.monitoringManager = null;
        this.reconfigurationMgr = null;
    }

    public SLAsticInstance(final AbstractSLAsticControl controller, final AbstractSLAsticMonitoringManager monitoringManager, final AbstractSLAsticReconfigurationManager reconfigurationMgr) {
        this.controller = controller;
        this.monitoringManager = monitoringManager;
        this.reconfigurationMgr = reconfigurationMgr;
    }

    public SLAsticInstance(Properties prop) {
        this.controller = null;
        this.monitoringManager = null;
        this.reconfigurationMgr = null;
        loadProperties(prop);
    }

    private void initProperties(Properties prop) throws IllegalArgumentException {
        for (String curPropName : prop.stringPropertyNames()) {
            boolean storedProp = false;
            if (curPropName.startsWith(AbstractPerformanceEvaluator.PROP_PREFIX)) {
                storedProp = true;
                this.performanceEvaluatorProps.setProperty(curPropName, prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractWorkloadForecaster.PROP_PREFIX)) {
                storedProp = true;
                this.workloadForecasterProps.setProperty(curPropName, prop.getProperty(curPropName));
            } 
            if (curPropName.startsWith(AbstractPerformancePredictor.PROP_PREFIX)) {
                storedProp = true;
                this.performancePredictorProps.setProperty(curPropName, prop.getProperty(curPropName));
            } 
            if (curPropName.startsWith(AbstractAdaptationPlanner.PROP_PREFIX)) {
                storedProp = true;
                this.adaptationPlannerProps.setProperty(curPropName, prop.getProperty(curPropName));
            } 
            if (curPropName.startsWith(AbstractSLAsticAnalysis.PROP_PREFIX)) {
                storedProp = true;
                this.analysisProps.setProperty(curPropName, prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractSLAsticModelManager.PROP_PREFIX)) {
                storedProp = true;
                this.modelManagerProps.setProperty(curPropName, prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractSLAsticModelUpdater.PROP_PREFIX)) {
                storedProp = true;
                this.modelUpdaterProps.setProperty(curPropName, prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractSLAsticControl.PROP_PREFIX)) {
                storedProp = true;
                this.controllerProps.setProperty(curPropName, prop.getProperty(curPropName));
            } 
            if (curPropName.startsWith(AbstractSLAsticMonitoringManager.PROP_PREFIX)) {
                storedProp = true;
                this.monitoringProps.setProperty(curPropName, prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractSLAsticReconfigurationManager.PROP_PREFIX)) {
                storedProp = true;
                this.reconfigurationProps.setProperty(curPropName, prop.getProperty(curPropName));
            }
            if (!storedProp) {
                log.error("Unknown property name '" + curPropName + "");
            }
        }
    }

    private void loadProperties(Properties prop) throws IllegalArgumentException {
        this.initProperties(prop);

        /* Load all components */
        String controlComponentClassnameProperty = this.controllerProps.getProperty(AbstractSLAsticControl.PROP_PREFIX+"."+SLAsticInstance.COMPONENT_CLASSNAME_PROPNAME);
        if (controlComponentClassnameProperty == null || controlComponentClassnameProperty.length() <= 0) {
            log.error("Missing configuration property value for 'controlComponent'");
        }
        AbstractSLAsticControl slasticCtrlComponent = (AbstractSLAsticControl) loadAndInitInstanceFromClassname(controlComponentClassnameProperty, this.controllerProps);

        String modelManagerComponentClassnameProperty = this.modelManagerProps.getProperty(AbstractSLAsticModelManager.PROP_PREFIX+"."+SLAsticInstance.COMPONENT_CLASSNAME_PROPNAME);
        if (modelManagerComponentClassnameProperty == null || modelManagerComponentClassnameProperty.length() <= 0) {
            log.error("Missing configuration property value for 'modelManagerComponent'");
        }
        AbstractSLAsticModelManager modelManagerComponent = (AbstractSLAsticModelManager) loadAndInitInstanceFromClassname(modelManagerComponentClassnameProperty, this.modelManagerProps);

        String modelUpdaterComponentClassnameProperty = this.modelUpdaterProps.getProperty(AbstractSLAsticModelUpdater.PROP_PREFIX+"."+SLAsticInstance.COMPONENT_CLASSNAME_PROPNAME);
        if (modelUpdaterComponentClassnameProperty == null || modelUpdaterComponentClassnameProperty.length() <= 0) {
            log.error("Missing configuration property value for 'modelUpdaterComponent'");
        }
        AbstractSLAsticModelUpdater modelUpdaterComponent = (AbstractSLAsticModelUpdater) loadAndInitInstanceFromClassname(modelUpdaterComponentClassnameProperty, this.modelUpdaterProps);

        String analysisComponentClassnameProperty = this.analysisProps.getProperty(AbstractSLAsticAnalysis.PROP_PREFIX+"."+SLAsticInstance.COMPONENT_CLASSNAME_PROPNAME);
        if (analysisComponentClassnameProperty == null || analysisComponentClassnameProperty.length() <= 0) {
            log.error("Missing configuration property value for 'analysisComponent'");
        }
        AbstractSLAsticAnalysis analysisComponent = (AbstractSLAsticAnalysis) loadAndInitInstanceFromClassname(analysisComponentClassnameProperty, this.analysisProps);

        String performanceEvaluatorComponentClassnameProperty = this.performanceEvaluatorProps.getProperty(AbstractPerformanceEvaluator.PROP_PREFIX+"."+SLAsticInstance.COMPONENT_CLASSNAME_PROPNAME);
        AbstractPerformanceEvaluator performanceEvaluatorComponent = null;
        // Note: a performance evaluator component is not mandatory
        if (performanceEvaluatorComponentClassnameProperty != null && performanceEvaluatorComponentClassnameProperty.length() > 0) {
            performanceEvaluatorComponent = (AbstractPerformanceEvaluator) loadAndInitInstanceFromClassname(performanceEvaluatorComponentClassnameProperty, this.performanceEvaluatorProps);
        }

        String workloadForecasterComponentClassnameProperty = this.workloadForecasterProps.getProperty(AbstractWorkloadForecaster.PROP_PREFIX+"."+SLAsticInstance.COMPONENT_CLASSNAME_PROPNAME);
        AbstractWorkloadForecaster workloadForecasterComponent = null;
        // Note: a workload forecaster component is not mandatory
        if (workloadForecasterComponentClassnameProperty != null && workloadForecasterComponentClassnameProperty.length() > 0) {
            workloadForecasterComponent = (AbstractWorkloadForecaster) loadAndInitInstanceFromClassname(workloadForecasterComponentClassnameProperty, this.workloadForecasterProps);
        }

        String performancePredictorComponentClassnameProperty = this.performancePredictorProps.getProperty(AbstractPerformancePredictor.PROP_PREFIX+"."+SLAsticInstance.COMPONENT_CLASSNAME_PROPNAME);
        AbstractPerformancePredictor performancePredictorComponent = null;
        // Note: a performance predictor component is not mandatory
        if (performancePredictorComponentClassnameProperty != null && performancePredictorComponentClassnameProperty.length() > 0) {
            performancePredictorComponent = (AbstractPerformancePredictor) loadAndInitInstanceFromClassname(performancePredictorComponentClassnameProperty, this.performancePredictorProps);
        }

        String adaptationPlannerComponentClassnameProperty = this.adaptationPlannerProps.getProperty(AbstractAdaptationPlanner.PROP_PREFIX+"."+SLAsticInstance.COMPONENT_CLASSNAME_PROPNAME);
        AbstractAdaptationPlanner adaptationPlannerComponent = null;
        // Note: a performance predictor component is not mandatory
        if (adaptationPlannerComponentClassnameProperty != null && adaptationPlannerComponentClassnameProperty.length() > 0) {
            adaptationPlannerComponent = (AbstractAdaptationPlanner) loadAndInitInstanceFromClassname(adaptationPlannerComponentClassnameProperty, this.adaptationPlannerProps);
        }

        String reconfigurationManagerComponentClassnameProperty = this.reconfigurationProps.getProperty(AbstractSLAsticReconfigurationManager.PROP_PREFIX+"."+SLAsticInstance.COMPONENT_CLASSNAME_PROPNAME);
        if (reconfigurationManagerComponentClassnameProperty == null || reconfigurationManagerComponentClassnameProperty.length() <= 0) {
            log.error("Missing configuration property value for 'reconfigurationManagerComponent'");
        }
        AbstractSLAsticReconfigurationManager reconfigurationManagerComponent = (AbstractSLAsticReconfigurationManager) loadAndInitInstanceFromClassname(reconfigurationManagerComponentClassnameProperty, this.reconfigurationProps);

        /* Assert that mandatory components are available */
        boolean success = true;
        if (reconfigurationManagerComponent == null) {
            log.error("reconfigurationManagerComponent is null");
            success = false;
        }
        if (slasticCtrlComponent == null) {
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
            throw new IllegalArgumentException("Failed to load at least one component");
        }

        /* "wire" the components */
        reconfigurationManagerComponent.setControlComponent(slasticCtrlComponent);

        slasticCtrlComponent.setReconfigurationManager(reconfigurationManagerComponent); // TODO: fix!
        slasticCtrlComponent.setAnalysis(analysisComponent);
        slasticCtrlComponent.setModelManager(modelManagerComponent);
        slasticCtrlComponent.setModelUpdater(modelUpdaterComponent);

        modelManagerComponent.setParentControlComponent(slasticCtrlComponent);
        modelUpdaterComponent.setParentControlComponent(slasticCtrlComponent);
        modelUpdaterComponent.setModelManager(modelManagerComponent);

        analysisComponent.setParentControlComponent(slasticCtrlComponent);
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
        slasticCtrlComponent.addListener(modelManagerComponent);
        slasticCtrlComponent.addListener(modelUpdaterComponent);
        if (performanceEvaluatorComponent != null) {
            slasticCtrlComponent.addListener(performanceEvaluatorComponent);
            performanceEvaluatorComponent.setSimpleSLAsticEventService(slasticCtrlComponent);
        }
        if (workloadForecasterComponent != null) {
            slasticCtrlComponent.addListener(workloadForecasterComponent);
            workloadForecasterComponent.setSimpleSLAsticEventService(slasticCtrlComponent);
        }
        if (performancePredictorComponent != null) {
            slasticCtrlComponent.addListener(performancePredictorComponent);
            performancePredictorComponent.setSimpleSLAsticEventService(slasticCtrlComponent);
        }
        if (adaptationPlannerComponent != null) {
            slasticCtrlComponent.addListener(adaptationPlannerComponent);
            adaptationPlannerComponent.setSimpleSLAsticEventService(slasticCtrlComponent);
        }

        // TODO: This should also be configurable from the command-line!
        String logReaderClassnameProperty = prop.getProperty("tpmon.monitoringDataReader");
        String logReaderInitStringProperty = prop.getProperty("tpmon.monitoringDataReaderInitString", ""); // empty String is default
        if (logReaderClassnameProperty == null || logReaderClassnameProperty.length() <= 0) {
            log.error("Missing configuration property value for 'tpmon.monitoringDataReader'");
        }
        IKiekerMonitoringLogReader logReader =
                (IKiekerMonitoringLogReader) loadAndInitInstanceFromClassname(logReaderClassnameProperty, logReaderInitStringProperty);

    //tpanInstance = new TpanInstance();
    //tpanInstance.setLogReader(logReader);
    //tpanInstance.addRecordConsumer(slasticCtrlComponent);

    // TODO: to be removed
    //tpanInstance = legacyInstance();
    //return tpanInstance;
    }

    /** An object of the class with name @classname is instantiated, its
     *  method init(String initString) is called with parameter @a initString
     *  and the object is returned. This implies, that the class for @a classname
     *  provide the method init(String initString).
     *
     *  @return the instance; null in case an error occured.
     */
    private Object loadAndInitInstanceFromClassname(String classname, String initString) {
        Object inst = null;
        try {
            Class cl = Class.forName(classname);
            inst = cl.newInstance();
            Method m = cl.getMethod("init", String.class);
            m.invoke(inst, initString);
            log.info("Loaded and instantiated component ('" + classname + "') with init string '" + initString + "'");
        } catch (Exception ex) {
            inst = null;
            log.fatal("Failed to instantiate component of class '" + classname + "'", ex);
        }
        return inst;
    }

    /** An object of the class with name @classname is instantiated, its
     *  method init(String initString) is called with parameter @a initString
     *  and the object is returned. This implies, that the class for @a classname
     *  provide the method init(String initString).
     *
     *  @return the instance; null in case an error occured.
     */
    private Object loadAndInitInstanceFromClassname(String classname, Properties props) {
        Object inst = null;
        try {
            Class cl = Class.forName(classname);
            inst = cl.newInstance();
            Method m = cl.getMethod("init", String.class);
            m.invoke(inst, props);
            log.info("Loaded and instantiated component ('" + classname + "') with init string '" + props + "'");
        } catch (Exception ex) {
            inst = null;
            log.fatal("Failed to instantiate component of class '" + classname + "'", ex);
        }
        return inst;
    }
    
    /** Start instance. The method returns immediately. */
    public void run() {
        this.controller.execute();
        this.monitoringManager.execute();
        this.reconfigurationMgr.execute();
    }

    /** Terminate instance */
    public void terminate() {
        this.controller.terminate();
        this.monitoringManager.terminate();
        this.reconfigurationMgr.terminate();
    }

    private static void testSLAsticMetaModel() {
        //String wfFile ="C:/workspace/slastic/src/org/trustsoft/slastic/control/InitModels.oaw";
        //String wfFile = "../../../workspace2/SLAstic-Framework/trunk/src/org/trustsoft/slastic/control/InitModelsMac.oaw";
        final String INIT_WORKFLOW_FN = "/home/voorn/svn_work/sw_DALenaRobert/SLAstic-Framework/branches/20091022-Andre/META-INF/oaw/InitModels-Andre.oaw";
        Map<String, String> properties = new HashMap<String, String>();
        Map<String, String> slotContents = new HashMap<String, String>();

        //workflow runner of the oAW-framework
        WorkflowRunner runner = new WorkflowRunner();
        // Note, I had to add the (ProgressMonitor) cast in order to remove netbeans-reported errors
        runner.run(INIT_WORKFLOW_FN, (ProgressMonitor) new NullProgressMonitor(), properties, slotContents);
        slal.Model slas = (slal.Model) runner.getContext().get("theModel");
        reconfMM.ReconfigurationModel reconfigurationModel = (reconfMM.ReconfigurationModel) runner.getContext().get("reconfigurationModel");

        // OLD (from Andrés branch):
//          //Das sollte nun immer gemacht werden:
//            ModelManager.getInstance().initModel(reconfigurationModel);
//
//            //SLAChecker slaChecker = new SLAChecker(slas);
//            ModelUpdater modelUpdater = new ModelUpdater(reconfigurationModel.getMaxResponseTimes());
//            //IKiekerMonitoringLogReader logReader = new FSReaderRealtime(inputDir, 7);
//            IKiekerMonitoringLogReader logReader = new JMSReader("tcp://127.0.0.1:3035/", "queue1");
//            MonitoringRecordTypeLogger recordTypeLogger = new MonitoringRecordTypeLogger();
//            //slaChecker.start();

        /* Dumps the record type ID */
//        tpanInstance.addConsumer(new MonitoringRecordTypeLogger());

        /* Collects all executions */
//        ExecutionSequenceRepositoryFiller seqRepConsumer = new ExecutionSequenceRepositoryFiller();
//        tpanInstance.addConsumer(seqRepConsumer);

        /* Dumps response times */
//        ResponseTimePlotter rtPlotter = new ResponseTimePlotter();
//        tpanInstance.addConsumer(rtPlotter);
        //String wfFile = "../../SLALproject/src/SLALproject.oaw";

//            ISLAsticReconfigurationManager reconfigurationManager =
//                    new ReconfigurationManagerWget();
//        final int NUM_RECONFIGURATIONS = 5;
//        for (int i = 1; i <= NUM_RECONFIGURATIONS; i++) {
//            try {
//                log.info("Requesting reconfiguration " + i + "/" + NUM_RECONFIGURATIONS);
//                reconfigurationManager.doReconfiguration(null);
//                Thread.sleep(5000);
//            } catch (Exception ex) {
//                Logger.getLogger(ControlComponent.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

        //System.exit(0);

        /* SLAsticResourceEnvironment */
        SLAsticResourceEnvironment resourceEnvironment = (SLAsticResourceEnvironment) runner.getContext().get("resourceEnvironmentModel");
        log.info("\n*SLAsticResourceEnvironment: " + resourceEnvironment.getId() + "*\nServers in pool:");
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