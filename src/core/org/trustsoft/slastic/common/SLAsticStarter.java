package org.trustsoft.slastic.common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.common.logReader.IKiekerMonitoringLogReader;
import kieker.common.logReader.LogReaderExecutionException;
import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.tpan.TpanInstance;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.ParseException;

import org.trustsoft.slastic.control.AbstractSLAsticControl;
import org.trustsoft.slastic.control.components.analysis.AbstractAdaptationPlanner;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformanceEvaluator;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformancePredictor;
import org.trustsoft.slastic.control.components.analysis.AbstractSLAsticAnalysis;
import org.trustsoft.slastic.control.components.analysis.AbstractWorkloadForecaster;
import org.trustsoft.slastic.control.components.modelManager.AbstractSLAsticModelManager;
import org.trustsoft.slastic.control.components.modelUpdater.AbstractSLAsticModelUpdater;

import org.trustsoft.slastic.reconfiguration.AbstractSLAsticReconfigurationManager;

/**
 *
 * @author Andre van Hoorn
 */
public class SLAsticStarter {

    private static final Log log = LogFactory.getLog(SLAsticStarter.class);
    private static CommandLine cmdl = null;
    private static final CommandLineParser cmdlParser = new BasicParser();
    private static final HelpFormatter cmdHelpFormatter = new HelpFormatter();
    private static final Options cmdlOpts = new Options();

    static {
        cmdlOpts.addOption(OptionBuilder.withArgName("file").hasArg().withLongOpt("configuration").isRequired(true).withDescription("Configuration file").withValueSeparator('=').create("f"));
        cmdlOpts.addOption(OptionBuilder.withArgName("isnewconfig").hasArg().withLongOpt("isnewconfig").isRequired(false).withDescription("Is new configuration type (true|false)").withValueSeparator('=').create("n"));
    }

    public static void main(String[] args) {
        log.info("Hi, this is SLAsticControl");

        if (!parseArgs(args)) {
            System.exit(1);
        }

        String isnewconfigStrg = cmdl.getOptionValue("isnewconfig");
        if (isnewconfigStrg != null && isnewconfigStrg.trim().toLowerCase().equals("true")) {
            log.info("New configuration file format");
            SLAsticInstance inst = initNewInstanceFromArgs();
            if (inst == null) {
                log.error("initNewInstanceFromArgs() returned null");
                System.exit(1);
            }
            inst.run();
            log.info("SLAsticInstance started");
        } else { // TODO: remove
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
        }

        log.info("Bye, this was SLAsticControl");
    }

    /**
     * Initializes and returns a ControlComponent analysis instance.
     *
     * @return the initialized instance; null on error
     */
    private static SLAsticInstance initNewInstanceFromArgs() throws IllegalArgumentException {
        String configurationFile = cmdl.getOptionValue("configuration");
        if (configurationFile == null) {
            log.fatal("Configuration file parameter is null");
            throw new IllegalArgumentException("Configuration file parameter is null");
        }

        SLAsticInstance inst = null;

        // Load configuration file
        InputStream is = null;
        Properties prop = new Properties();
        try {
            is = new FileInputStream(configurationFile);
            log.info("Loading configuration from file '" + configurationFile + "'");
            prop.load(is);
            inst = new SLAsticInstance(prop);
        } catch (Exception ex) {
            log.error("Error creating SLAsticInstance", ex);
            throw new IllegalArgumentException("Error creating SLAsticInstance", ex);
        // TODO: introduce static variable 'terminated' or alike
        } finally {
            try {
                is.close();
            } catch (Exception ex) { /* nothing we can do */ }
        }
        return inst;
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

//    private static TpanInstance legacyInstance() {
//        TpanInstance analysisInstance = null;
//        IKiekerMonitoringLogReader logReader = null;
//        AbstractSLAsticControl slasticCtrlComponent = null;
//        AbstractSLAsticReconfigurationManager reconfigurationManager = null;
//        AbstractSLAsticModelUpdater modelUpdater = null;
//        AbstractSLAsticModelManager modelManager = null;
//        AbstractSLAsticAnalysis analysisComponent = null;
//        AbstractPerformanceEvaluator performanceEvaluator = null;
//        AbstractWorkloadForecaster workloadForecaster = null;
//        AbstractPerformancePredictor performancePredictor = null;
//        AbstractAdaptationPlanner adaptationPlanner = null;
//
//        String readerType = System.getProperty("reader");
//        if (readerType == null || readerType.length() == 0) {
//            log.error("No reader-type found");
//            log.error("Please Provide reader-type via: ant -Dreader=<readerType> -Dexample=<exampleType> -DinitWorkflow=<filename> run-Example");
//            log.error("Supported reader types: FSRealtime, JMS");
//        }
//
//        String exampleType = System.getProperty("example");
//        if (exampleType == null || exampleType.length() == 0) {
//            log.error("No Example-Type found");
//            log.error("please provide an example-type via: ant -Dreader=<readerType> -Dexample=<exampleType> -DinitWorkflow=<filename> run-Example");
//            log.error("Supported example-type: Bookstore, JPetStore");
//        }
//
//        String initWorkflow_fn = System.getProperty("initWorkflow");
//        if (initWorkflow_fn == null || initWorkflow_fn.length() == 0) {
//            log.error("No initWorkflow found");
//            log.error("please provide a workflow via: ant -Dreader=<readerType> -Dexample=<exampleType> -DinitWorkflow=<filename> run-Example");
//            log.error("Supported example-type: Bookstore, JPetStore");
//        }
//
//        String inputDir = System.getProperty("inputDir");
//        if (readerType.equals("FSRealtime") || readerType.equals("FSReader")) {
//            if (inputDir == null || inputDir.length() == 0 || inputDir.equals("${inputDir}")) {
//                log.error("No input dir found!");
//                log.error("Provide an input dir as system property.");
//                log.error("Example to read all tpmon-* files from /tmp:\n" +
//                        "                    ant -DinputDir=/tmp/ run-SLAsticControl    ");
//                System.exit(1);
//            } else {
//                log.info("Reading all tpmon-* files from " + inputDir);
//            }
//
//            //Controller object, the main object of the SLAstic.CONTROL-Framework
//            slasticCtrlComponent = new ControlComponent();
//            slasticCtrlComponent.setAnalysis(analysisComponent);
//            slasticCtrlComponent.setModelManager(modelManager);
//            slasticCtrlComponent.setModelUpdater(modelUpdater);
//            slasticCtrlComponent.setReconfigurationManager(reconfigurationManager);
//            slasticCtrlComponent.init("initWorkflow_fn=" + initWorkflow_fn);
//
//            //Performance Analyzer, part of the Analysis-Object
//            performanceEvaluator = new SLAChecker();
////            analysisComponent.setPerformanceEvaluator(performanceEvaluator);
//
//            //Adaptation Analyzer, different implementations for JPetStore-Example and other examples
//            if (exampleType.equals("JPetStore")) {
//                //JPetStore Adaptation Analyzer for handling SLAViolation-Events by sending a Component-Redeployment-OP to the Reconfiguration Manager
//                adaptationPlanner = new JPetStoreAdaptationPlanner();
//                //Reconfiguration Manager that executes plan via network
//                reconfigurationManager = new ReconfigurationManagerWget();
//                reconfigurationManager.execute();
//            } else {
//                //Adaptation Analyzer that produces different Test-Plans
//                adaptationPlanner = new AdaptationPlannerBookstoreSamplePlan();
//                //Reconfiguration Manager that sends the Plan back to the Model Manager
//                reconfigurationManager = new ReconfigurationPlanForwarder();
//            }
//
//            reconfigurationManager.setControlComponent(slasticCtrlComponent);
//
//            //set different Analyzer-Objects, set null for not implemented ones.
////            analysisComponent.setAdaptationPlanner(adaptationPlanner);
////            analysisComponent.setPerformancePredictor(performancePredictor);
////            analysisComponent.setWorkloadForecaster(workloadForecaster);
//
//            //Analysis object, which belongs to the Controller-Object. It is responsible for the analysis of the Monitoring-Data
//            analysisComponent = new Analysis();
//            analysisComponent.setAdaptationPlanner(adaptationPlanner);
//            analysisComponent.setParentControlComponent(slasticCtrlComponent);
//            analysisComponent.setPerformanceEvaluator(performanceEvaluator);
//            analysisComponent.setPerformancePredictor(performancePredictor);
//            analysisComponent.setWorkloadForecaster(workloadForecaster);
//
//            //Instantiate ModelUpdater that is responsible for distributing incoming monitoring data
//            modelUpdater = new ModelUpdater();
//            modelManager = new ModelManager();
//
//            //Initalizing Controller object
//            slasticCtrlComponent.setAnalysis(analysisComponent);
//            slasticCtrlComponent.setModelManager(modelManager);
//            slasticCtrlComponent.setModelUpdater(modelUpdater);
//            slasticCtrlComponent.setReconfigurationManager(reconfigurationManager);
//
//            //Tpan Instace for monitoring data
//            analysisInstance = new TpanInstance();
//            if (readerType.equals("FSRealtime")) {
//                //log reader to replay data in realtime
//                logReader = new FSReaderRealtime(inputDir, 7);
//
//            } else if (readerType.equals("JMS")) {
//                //JMS reader for reading via network in realtime
//                //logReader= new JMSReader("tcp://pc-vanhoorn.informatik.uni-oldenburg.de:3035/","queue1");
//                logReader = new JMSReader("tcp://134.106.27.209:3035/", "queue1");
//            } else {
//                log.error("ReaderType: " + readerType + " not found");
//                return null;
//            }
//
//            //initializing Tpan object
//            analysisInstance.setLogReader(logReader);
//            analysisInstance.addRecordConsumer(slasticCtrlComponent);
//        }
//
//        return analysisInstance;
//    }
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
}
