package org.trustsoft.slastic.control;

import java.util.HashMap;
import java.util.Map;
import kieker.common.logReader.IKiekerMonitoringLogReader;
import kieker.common.logReader.LogReaderExecutionException;
import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.common.logReader.filesystemReader.realtime.FSReaderRealtime;
import kieker.tpan.TpanInstance;

import kieker.tpan.logReader.JMSReader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.openarchitectureware.workflow.WorkflowRunner;
import org.openarchitectureware.workflow.monitor.ProgressMonitor;
import org.trustsoft.slastic.control.analysis.AdaptationPlanner;
import org.trustsoft.slastic.control.analysis.Analysis;
import org.trustsoft.slastic.control.analysis.IAdaptationPlanner;
import org.trustsoft.slastic.control.analysis.IAnalysis;
import org.trustsoft.slastic.control.analysis.JPetStoreAdaptationPlanner;
import org.trustsoft.slastic.control.analysis.SLAChecker;
import org.trustsoft.slastic.control.systemModel.IModelManager;
import org.trustsoft.slastic.control.systemModel.IModelUpdater;
import org.trustsoft.slastic.control.systemModel.ModelManager;
import org.trustsoft.slastic.control.systemModel.ModelUpdater;

import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;
import org.trustsoft.slastic.reconfigurationManager.ReconfigurationManager;
import org.trustsoft.slastic.slasticqosannotations.ExternalServiceResponseTimeObjective;
import org.trustsoft.slastic.slasticqosannotations.InternalServiceResponseTimeObjective;
import org.trustsoft.slastic.slasticqosannotations.QoSObjective;
import org.trustsoft.slastic.slasticqosannotations.SLAsticQoSAnnotations;
import org.trustsoft.slastic.slasticresourceenvironment.SLAsticResourceEnvironment;
import org.trustsoft.slastic.slasticresourceenvironment.ServerNode;

/**
 * @author Andre van Hoorn, Lena Stoever
 */
public class SLAsticControl {
    private static final Log log = LogFactory.getLog(SLAsticControl.class);

    private static IReconfigurationManager mng = null;
    private static IKiekerMonitoringLogReader logReader = null;

    private static IModelUpdater modelUpdater = null;
    private static IModelManager modelManager = null;

    private static IAnalysis analysisComponent = null;
    private static IAdaptationPlanner adaptationPlanner = null;

    public static void main(String[] args) {
        log.info("Hi, this is SLAsticControl");

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

            //        new JMSReader( );

            /* Dumps the record type ID */
//        analysisInstance.addConsumer(new MonitoringRecordTypeLogger());

            /* Collects all executions */
//        ExecutionSequenceRepositoryFiller seqRepConsumer = new ExecutionSequenceRepositoryFiller();
//        analysisInstance.addConsumer(seqRepConsumer);

            /* Dumps response times */
//        ResponseTimePlotter rtPlotter = new ResponseTimePlotter();
//        analysisInstance.addConsumer(rtPlotter);
            //String wfFile = "../../SLALproject/src/SLALproject.oaw";

//            IReconfigurationManager reconfigurationManager =
//                    new ReconfigurationManager();
//        final int NUM_RECONFIGURATIONS = 5;
//        for (int i = 1; i <= NUM_RECONFIGURATIONS; i++) {
//            try {
//                log.info("Requesting reconfiguration " + i + "/" + NUM_RECONFIGURATIONS);
//                reconfigurationManager.doReconfiguration(null);
//                Thread.sleep(5000);
//            } catch (Exception ex) {
//                Logger.getLogger(SLAsticControl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

            //System.exit(0);

            //Controller object, the main object of the SLAstic.CONTROL-Framework
            org.trustsoft.slastic.control.recordConsumer.SLAsticControl slactrl =
                    new org.trustsoft.slastic.control.recordConsumer.SLAsticControl(initWorkflow_fn);
            //Analysis object, which belongs to the Controller-Object. It is responsible for the analysis of the Monitoring-Data
            analysisComponent = new Analysis();
            //Performance Analyzer, part of the Analysis-Object
            SLAChecker slaChecker = new SLAChecker();
            analysisComponent.setPerformanceAnalyzer(slaChecker);

            //Adaptation Analyzer, different implementations for JPetStore-Example and other examples
            if (exampleType.equals("JPetStore")) {
                //JPetStore Adaptation Analyzer for handling SLAViolation-Events by sending a Component-Redeployment-OP to the Reconfiguration Manager
                adaptationPlanner = new JPetStoreAdaptationPlanner();
                //Reconfiguration Manager that executes plan via network
                mng = new ReconfigurationManager();
            } else {
                //Adaptation Analyzer that produces different Test-Plans
                adaptationPlanner = new AdaptationPlanner();
                //Reconfiguration Manager that sends the Plan back to the Model Manager
                mng = ReconfigurationPlanForwarder.getInstance();
            }

            //set different Analyzer-Objects, set null for not implemented ones.
            analysisComponent.setAdaptationAnalyzer(adaptationPlanner);
            analysisComponent.setPerformancePredictor(null);
            analysisComponent.setWorkloadAnalyzer(null);

            //Instantiate ModelUpdater that is responsible for distributing incoming monitoring data
            modelUpdater = new ModelUpdater();
            modelManager = ModelManager.getInstance();

            //Initalizing Controller object
            slactrl.setAnalysis(analysisComponent);
            slactrl.setModelManager(modelManager);
            slactrl.setModelUpdater(modelUpdater);
            slactrl.setReconfigurationManager(mng);

            //Tpan Instace for monitoring data
            TpanInstance analysisInstance = new TpanInstance();
            if (readerType.equals("FSRealtime")) {
                //log reader to replay data in realtime
                logReader = new FSReaderRealtime(inputDir, 7);

            } else if (readerType.equals("JMS")) {
                //JMS reader for reading via network in realtime
                //logReader= new JMSReader("tcp://pc-vanhoorn.informatik.uni-oldenburg.de:3035/","queue1");
                logReader = new JMSReader("tcp://134.106.27.209:3035/", "queue1");
            } else {
                log.error("ReaderType: " + readerType + " not found");
                return;
            }

            //initializing Tpan object
            analysisInstance.setLogReader(logReader);
            analysisInstance.addRecordConsumer(slactrl);


            //AdaptationPlanner analyzer = new AdaptationPlanner();
            //analyzer.analyze();
            //ReconfigurationPlanForwarder.getInstance().run();
            //log.info("Analyzer und alles gestartet");


            try {
                //starting Tpan object that starts the other objects internally
                analysisInstance.run();
            } catch (LogReaderExecutionException e) {
                log.error("LogReaderExecutionException:", e);
            } catch (RecordConsumerExecutionException e) {
                log.error("RecordConsumerExecutionException:", e);
            }

            log.info("Bye, this was SLAsticControl");
        }
    }

    public static void testSLAsticMetaModel() {
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
