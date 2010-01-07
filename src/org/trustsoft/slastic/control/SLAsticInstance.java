package org.trustsoft.slastic.control;

import org.trustsoft.slastic.control.plugins.daLena.ReconfigurationPlanForwarder;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import kieker.common.logReader.IKiekerMonitoringLogReader;
import kieker.common.logReader.LogReaderExecutionException;
import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.common.logReader.filesystemReader.realtime.FSReaderRealtime;
import kieker.tpan.TpanInstance;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.ParseException;

import kieker.tpan.logReader.JMSReader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.openarchitectureware.workflow.WorkflowRunner;
import org.openarchitectureware.workflow.monitor.ProgressMonitor;
import org.trustsoft.slastic.control.components.AbstractSLAsticControl;
import org.trustsoft.slastic.control.plugins.daLena.analysis.AdaptationPlannerBookstoreSamplePlan;
import org.trustsoft.slastic.control.plugins.daLena.analysis.Analysis;
import org.trustsoft.slastic.control.plugins.daLena.analysis.JPetStoreAdaptationPlanner;
import org.trustsoft.slastic.control.plugins.daLena.analysis.SLAChecker;
import org.trustsoft.slastic.control.components.analysis.AbstractAdaptationPlanner;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformanceEvaluator;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformancePredictor;
import org.trustsoft.slastic.control.components.analysis.AbstractSLAsticAnalysis;
import org.trustsoft.slastic.control.components.analysis.AbstractWorkloadForecaster;
import org.trustsoft.slastic.control.components.modelManager.AbstractSLAsticModelManager;
import org.trustsoft.slastic.control.components.modelUpdater.AbstractSLAsticModelUpdater;
import org.trustsoft.slastic.control.plugins.daLena.ControlComponent;
import org.trustsoft.slastic.control.plugins.daLena.modelManager.ModelManager;
import org.trustsoft.slastic.control.plugins.daLena.modelUpdater.ModelUpdater;

import org.trustsoft.slastic.reconfigurationManager.AbstractSLAsticReconfigurationManager;
import org.trustsoft.slastic.control.plugins.daLena.ReconfigurationManagerWget;
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
    private static CommandLine cmdl = null;
    private static final CommandLineParser cmdlParser = new BasicParser();
    private static final HelpFormatter cmdHelpFormatter = new HelpFormatter();
    private static final Options cmdlOpts = new Options();

    static {
        cmdlOpts.addOption(OptionBuilder.withArgName("file").hasArg().withLongOpt("configuration").isRequired(true).withDescription("Configuration file").withValueSeparator('=').create("f"));
    }

    public static void main(String[] args) {
        log.info("Hi, this is SLAsticControl");

        if (!parseArgs(args)) {
            System.exit(1);
        }

        TpanInstance tpanInstance = initInstanceFromArgs();
        if (tpanInstance == null) {
            log.error("init() returned null");
            System.exit(1);
        }

        try {
            //starting Tpan object that starts the other objects internally
            tpanInstance.run();
        } catch (LogReaderExecutionException e) {
            log.error("LogReaderExecutionException:", e);
        } catch (RecordConsumerExecutionException e) {
            log.error("RecordConsumerExecutionException:", e);
        }

        log.info("Bye, this was SLAsticControl");
    }

    /**
     * Initializes and returns a ControlComponent analysis instance.
     *
     * @return the initialized instance; null on error
     */
    private static TpanInstance initInstanceFromArgs() throws IllegalArgumentException {
        TpanInstance tpanInstance = new TpanInstance();

        String configurationFile = cmdl.getOptionValue("configuration");
        if (configurationFile == null) {
            log.fatal("Configuration file parameter is null");
            return null;
        }

        // Load configuration file
        InputStream is = null;
        Properties prop = new Properties();
        try {
            is = new FileInputStream(configurationFile);
            log.info("Loading configuration from file '" + configurationFile + "'");
            prop.load(is);
        } catch (Exception ex) {
            log.error("Error loading tpmon.properties file '" + configurationFile + "'", ex);
            // TODO: introduce static variable 'terminated' or alike
        } finally {
            try {
                is.close();
            } catch (Exception ex) { /* nothing we can do */ }
        }

        /* now, we'll load the properties: */

        /* Load all components */
        String controlComponentClassnameProperty = prop.getProperty("controlComponent");
        String controlComponentInitStringProperty = prop.getProperty("controlComponentInitString", ""); // empty String is default
        if (controlComponentClassnameProperty == null || controlComponentClassnameProperty.length() <= 0) {
            log.error("Missing configuration property value for 'controlComponent'");
        }
        AbstractSLAsticControl slasticCtrlComponent = (AbstractSLAsticControl) loadAndInitInstanceFromClassname(controlComponentClassnameProperty, controlComponentInitStringProperty);

        String modelManagerComponentClassnameProperty = prop.getProperty("modelManagerComponent");
        String modelManagerComponentInitStringProperty = prop.getProperty("modelManagerComponentInitString", ""); // empty String is default
        if (modelManagerComponentClassnameProperty == null || modelManagerComponentClassnameProperty.length() <= 0) {
            log.error("Missing configuration property value for 'modelManagerComponent'");
        }
        AbstractSLAsticModelManager modelManagerComponent = (AbstractSLAsticModelManager) loadAndInitInstanceFromClassname(modelManagerComponentClassnameProperty, modelManagerComponentInitStringProperty);

        String modelUpdaterComponentClassnameProperty = prop.getProperty("modelUpdaterComponent");
        String modelUpdaterComponentInitStringProperty = prop.getProperty("modelUpdaterComponentInitString", ""); // empty String is default
        if (modelUpdaterComponentClassnameProperty == null || modelUpdaterComponentClassnameProperty.length() <= 0) {
            log.error("Missing configuration property value for 'modelUpdaterComponent'");
        }
        AbstractSLAsticModelUpdater modelUpdaterComponent = (AbstractSLAsticModelUpdater) loadAndInitInstanceFromClassname(modelUpdaterComponentClassnameProperty, modelUpdaterComponentInitStringProperty);

        String analysisComponentClassnameProperty = prop.getProperty("analysisComponent");
        String analysisComponentInitStringProperty = prop.getProperty("analysisComponentInitString", ""); // empty String is default
        if (analysisComponentClassnameProperty == null || analysisComponentClassnameProperty.length() <= 0) {
            log.error("Missing configuration property value for 'analysisComponent'");
        }
        AbstractSLAsticAnalysis analysisComponent = (AbstractSLAsticAnalysis) loadAndInitInstanceFromClassname(analysisComponentClassnameProperty, analysisComponentInitStringProperty);

        String performanceEvaluatorComponentClassnameProperty = prop.getProperty("performanceEvaluatorComponent");
        String performanceEvaluatorComponentInitStringProperty = prop.getProperty("performanceEvaluatorComponentInitString", ""); // empty String is default
        AbstractPerformanceEvaluator performanceEvaluatorComponent = null;
        // Note: a performance evaluator component is not mandatory
        if (performanceEvaluatorComponentClassnameProperty != null && performanceEvaluatorComponentClassnameProperty.length() > 0) {
            performanceEvaluatorComponent = (AbstractPerformanceEvaluator) loadAndInitInstanceFromClassname(performanceEvaluatorComponentClassnameProperty, performanceEvaluatorComponentInitStringProperty);
        }

        String workloadForecasterComponentClassnameProperty = prop.getProperty("workloadForecasterComponent");
        String workloadForecasterComponentInitStringProperty = prop.getProperty("workloadForecasterComponentInitString", ""); // empty String is default
        AbstractWorkloadForecaster workloadForecasterComponent = null;
        // Note: a workload forecaster component is not mandatory
        if (workloadForecasterComponentClassnameProperty != null && workloadForecasterComponentClassnameProperty.length() > 0) {
            workloadForecasterComponent = (AbstractWorkloadForecaster) loadAndInitInstanceFromClassname(workloadForecasterComponentClassnameProperty, workloadForecasterComponentInitStringProperty);
        }

        String performancePredictorComponentClassnameProperty = prop.getProperty("performancePredictorComponent");
        String performancePredictorComponentInitStringProperty = prop.getProperty("performancePredictorComponentInitString", ""); // empty String is default
        AbstractPerformancePredictor performancePredictorComponent = null;
        // Note: a performance predictor component is not mandatory
        if (performancePredictorComponentClassnameProperty != null && performancePredictorComponentClassnameProperty.length() > 0) {
            performancePredictorComponent = (AbstractPerformancePredictor) loadAndInitInstanceFromClassname(performancePredictorComponentClassnameProperty, performancePredictorComponentInitStringProperty);
        }

        String adaptationPlannerComponentClassnameProperty = prop.getProperty("adaptationPlannerComponent");
        String adaptationPlannerComponentInitStringProperty = prop.getProperty("adaptationPlannerComponentInitString", ""); // empty String is default
        AbstractAdaptationPlanner adaptationPlannerComponent = null;
        // Note: a performance predictor component is not mandatory
        if (adaptationPlannerComponentClassnameProperty != null && adaptationPlannerComponentClassnameProperty.length() > 0) {
            adaptationPlannerComponent = (AbstractAdaptationPlanner) loadAndInitInstanceFromClassname(adaptationPlannerComponentClassnameProperty, adaptationPlannerComponentInitStringProperty);
        }

        String reconfigurationManagerComponentClassnameProperty = prop.getProperty("reconfigurationManagerComponent");
        String reconfigurationManagerComponentInitStringProperty = prop.getProperty("reconfigurationManagerComponentInitString", ""); // empty String is default
        if (reconfigurationManagerComponentClassnameProperty == null || reconfigurationManagerComponentClassnameProperty.length() <= 0) {
            log.error("Missing configuration property value for 'reconfigurationManagerComponent'");
        }
        AbstractSLAsticReconfigurationManager reconfigurationManagerComponent = (AbstractSLAsticReconfigurationManager) loadAndInitInstanceFromClassname(reconfigurationManagerComponentClassnameProperty, reconfigurationManagerComponentInitStringProperty);

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
            return null;
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
        if (performanceEvaluatorComponent != null) {
            slasticCtrlComponent.addListener(performanceEvaluatorComponent);
        }
        if (workloadForecasterComponent != null) {
            slasticCtrlComponent.addListener(workloadForecasterComponent);
        }
        if (performancePredictorComponent != null) {
            slasticCtrlComponent.addListener(performancePredictorComponent);
        }
        if (adaptationPlannerComponent != null) {
            slasticCtrlComponent.addListener(adaptationPlannerComponent);
        }

        // TODO: This should also be configurable from the command-line!
        String logReaderClassnameProperty = prop.getProperty("tpmon.monitoringDataReader");
        String logReaderInitStringProperty = prop.getProperty("tpmon.monitoringDataReaderInitString", ""); // empty String is default
        if (logReaderClassnameProperty == null || logReaderClassnameProperty.length() <= 0) {
            log.error("Missing configuration property value for 'tpmon.monitoringDataReader'");
        }
        IKiekerMonitoringLogReader logReader =
                (IKiekerMonitoringLogReader) loadAndInitInstanceFromClassname(logReaderClassnameProperty, logReaderInitStringProperty);

        tpanInstance = new TpanInstance();
        tpanInstance.setLogReader(logReader);
        tpanInstance.addRecordConsumer(slasticCtrlComponent);

        // TODO: to be removed
        //tpanInstance = legacyInstance();

        return tpanInstance;
    }

    /** An object of the class with name @classname is instantiated, its
     *  method init(String initString) is called with parameter @a initString
     *  and the object is returned. This implies, that the class for @a classname
     *  provide the method init(String initString).
     *
     *  @return the instance; null in case an error occured.
     */
    private static Object loadAndInitInstanceFromClassname(String classname, String initString) {
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

    private static TpanInstance legacyInstance() {
        TpanInstance analysisInstance = null;
        IKiekerMonitoringLogReader logReader = null;
        AbstractSLAsticControl slasticCtrlComponent = null;
        AbstractSLAsticReconfigurationManager reconfigurationManager = null;
        AbstractSLAsticModelUpdater modelUpdater = null;
        AbstractSLAsticModelManager modelManager = null;
        AbstractSLAsticAnalysis analysisComponent = null;
        AbstractPerformanceEvaluator performanceEvaluator = null;
        AbstractWorkloadForecaster workloadForecaster = null;
        AbstractPerformancePredictor performancePredictor = null;
        AbstractAdaptationPlanner adaptationPlanner = null;

        String readerType = System.getProperty("reader");
        if (readerType == null || readerType.length() == 0) {
            log.error("No reader-type found");
            log.error("Please Provide reader-type via: ant -Dreader=<readerType> -Dexample=<exampleType> -DinitWorkflow=<filename> run-Example");
            log.error("Supported reader types: FSRealtime, JMS");
        }

        String exampleType = System.getProperty("example");
        if (exampleType == null || exampleType.length() == 0) {
            log.error("No Example-Type found");
            log.error("please provide an example-type via: ant -Dreader=<readerType> -Dexample=<exampleType> -DinitWorkflow=<filename> run-Example");
            log.error("Supported example-type: Bookstore, JPetStore");
        }

        String initWorkflow_fn = System.getProperty("initWorkflow");
        if (initWorkflow_fn == null || initWorkflow_fn.length() == 0) {
            log.error("No initWorkflow found");
            log.error("please provide a workflow via: ant -Dreader=<readerType> -Dexample=<exampleType> -DinitWorkflow=<filename> run-Example");
            log.error("Supported example-type: Bookstore, JPetStore");
        }

        String inputDir = System.getProperty("inputDir");
        if (readerType.equals("FSRealtime") || readerType.equals("FSReader")) {
            if (inputDir == null || inputDir.length() == 0 || inputDir.equals("${inputDir}")) {
                log.error("No input dir found!");
                log.error("Provide an input dir as system property.");
                log.error("Example to read all tpmon-* files from /tmp:\n" +
                        "                    ant -DinputDir=/tmp/ run-SLAsticControl    ");
                System.exit(1);
            } else {
                log.info("Reading all tpmon-* files from " + inputDir);
            }

            //Controller object, the main object of the SLAstic.CONTROL-Framework
            slasticCtrlComponent = new ControlComponent();
            slasticCtrlComponent.setAnalysis(analysisComponent);
            slasticCtrlComponent.setModelManager(modelManager);
            slasticCtrlComponent.setModelUpdater(modelUpdater);
            slasticCtrlComponent.setReconfigurationManager(reconfigurationManager);
            slasticCtrlComponent.init("initWorkflow_fn=" + initWorkflow_fn);

            //Performance Analyzer, part of the Analysis-Object
            performanceEvaluator = new SLAChecker();
//            analysisComponent.setPerformanceEvaluator(performanceEvaluator);

            //Adaptation Analyzer, different implementations for JPetStore-Example and other examples
            if (exampleType.equals("JPetStore")) {
                //JPetStore Adaptation Analyzer for handling SLAViolation-Events by sending a Component-Redeployment-OP to the Reconfiguration Manager
                adaptationPlanner = new JPetStoreAdaptationPlanner();
                //Reconfiguration Manager that executes plan via network
                reconfigurationManager = new ReconfigurationManagerWget();
                reconfigurationManager.execute();
            } else {
                //Adaptation Analyzer that produces different Test-Plans
                adaptationPlanner = new AdaptationPlannerBookstoreSamplePlan();
                //Reconfiguration Manager that sends the Plan back to the Model Manager
                reconfigurationManager = new ReconfigurationPlanForwarder();
            }

            reconfigurationManager.setControlComponent(slasticCtrlComponent);

            //set different Analyzer-Objects, set null for not implemented ones.
//            analysisComponent.setAdaptationPlanner(adaptationPlanner);
//            analysisComponent.setPerformancePredictor(performancePredictor);
//            analysisComponent.setWorkloadForecaster(workloadForecaster);

            //Analysis object, which belongs to the Controller-Object. It is responsible for the analysis of the Monitoring-Data
            analysisComponent = new Analysis();
            analysisComponent.setAdaptationPlanner(adaptationPlanner);
            analysisComponent.setParentControlComponent(slasticCtrlComponent);
            analysisComponent.setPerformanceEvaluator(performanceEvaluator);
            analysisComponent.setPerformancePredictor(performancePredictor);
            analysisComponent.setWorkloadForecaster(workloadForecaster);

            //Instantiate ModelUpdater that is responsible for distributing incoming monitoring data
            modelUpdater = new ModelUpdater();
            modelManager = new ModelManager();

            //Initalizing Controller object
            slasticCtrlComponent.setAnalysis(analysisComponent);
            slasticCtrlComponent.setModelManager(modelManager);
            slasticCtrlComponent.setModelUpdater(modelUpdater);
            slasticCtrlComponent.setReconfigurationManager(reconfigurationManager);

            //Tpan Instace for monitoring data
            analysisInstance = new TpanInstance();
            if (readerType.equals("FSRealtime")) {
                //log reader to replay data in realtime
                logReader = new FSReaderRealtime(inputDir, 7);

            } else if (readerType.equals("JMS")) {
                //JMS reader for reading via network in realtime
                //logReader= new JMSReader("tcp://pc-vanhoorn.informatik.uni-oldenburg.de:3035/","queue1");
                logReader = new JMSReader("tcp://134.106.27.209:3035/", "queue1");
            } else {
                log.error("ReaderType: " + readerType + " not found");
                return null;
            }

            //initializing Tpan object
            analysisInstance.setLogReader(logReader);
            analysisInstance.addRecordConsumer(slasticCtrlComponent);
        }

        return analysisInstance;
    }

    static boolean parseArgs(String[] args) {
        try {
            cmdl = cmdlParser.parse(cmdlOpts, args);
        } catch (ParseException e) {
            System.err.println("Error parsing arguments: " + e.getMessage());
            printUsage();

            return false;
        }

        return true;
    }

    private static void printUsage() {
        cmdHelpFormatter.printHelp(SLAsticInstance.class.getName(), cmdlOpts);
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
