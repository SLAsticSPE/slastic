package org.trustsoft.slastic.control;

import kieker.common.logReader.IKiekerMonitoringLogReader;
import kieker.common.logReader.LogReaderExecutionException;
import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.common.logReader.filesystemReader.realtime.FSReaderRealtime;
import kieker.tpan.TpanInstance;
import kieker.tpan.logReader.JMSReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.analysis.AdaptationAnalyzer;
import org.trustsoft.slastic.control.analysis.Analysis;
import org.trustsoft.slastic.control.analysis.IAdaptationAnalyzer;
import org.trustsoft.slastic.control.analysis.JPetStoreAdaptationAnalyzer;
import org.trustsoft.slastic.control.analysis.SLAChecker;
import org.trustsoft.slastic.control.systemModel.IModelUpdater;
import org.trustsoft.slastic.control.systemModel.ModelManager;
import org.trustsoft.slastic.control.systemModel.ModelUpdater;
import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;
import org.trustsoft.slastic.reconfigurationManager.ReconfigurationManager;
import org.trustsoft.slastic.control.ReconfigurationPlanForwarder;


/**
 * @author Andre van Hoorn, Lena Stoever
 */
public class SLAsticControl {

    private static final Log log = LogFactory.getLog(SLAsticControl.class);

    public static void main(String[] args) {
        log.info("Hi, this is SLAsticControl");
        
        String readerType = System.getProperty("reader");
        if(readerType == null || readerType.length()==0){
        	log.error("No reader-type found");
        	log.error("Please Provide reader-type via: ant -Dreader=<readerType> -Dexample=<exampleType> -DinitWorkflow=<filename> run-Example");
        	log.error("Supported reader types: FSRealtime, JMS");
        }
        
        String exampleType = System.getProperty("example");
        if(exampleType == null || exampleType.length() == 0){
        	log.error("No Example-Type found");
        	log.error("please provide an example-type via: ant -Dreader=<readerType> -Dexample=<exampleType> -DinitWorkflow=<filename> run-Example");
        	log.error("Supported example-type: Bookstore, JPetStore");
        }

        String initWorkflow_fn = System.getProperty("initWorkflow");
        if(initWorkflow_fn == null || initWorkflow_fn.length() == 0){
        	log.error("No initWorkflow found");
        	log.error("please provide a workflow via: ant -Dreader=<readerType> -Dexample=<exampleType> -DinitWorkflow=<filename> run-Example");
        	log.error("Supported example-type: Bookstore, JPetStore");
        }

        String inputDir = System.getProperty("inputDir");
        if(readerType.equals("FSRealtime") || readerType.equals("FSReader")){
	        if (inputDir == null || inputDir.length() == 0 || inputDir.equals("${inputDir}")) {
	            log.error("No input dir found!");
	            log.error("Provide an input dir as system property.");
	            log.error("Example to read all tpmon-* files from /tmp:\n" +
	                    "                    ant -DinputDir=/tmp/ run-SLAsticControl    ");
	            System.exit(1);
	        } else {
	            log.info("Reading all tpmon-* files from " + inputDir);
        }
        }
        
        //Controller object, the main object of the SLAstic.CONTROL-Framework
        org.trustsoft.slastic.control.recordConsumer.SLAsticControl slactrl =
                new org.trustsoft.slastic.control.recordConsumer.SLAsticControl(initWorkflow_fn);
        //Analysis object, which belongs to the Controller-Object. It is responsible for the analysis of the Monitoring-Data
        Analysis ana = new Analysis();
        //Performance Analyzer, part of the Analysis-Object
        SLAChecker slaChecker = new SLAChecker();
        ana.setPerformanceAnalyzer(slaChecker);
        
        //Adaptation Analyzer, different implementations for JPetStore-Example and other examples
        IAdaptationAnalyzer adapt;
        IReconfigurationManager mng;
        if(exampleType.equals("JPetStore")){
        	//JPetStore Adaptation Analyzer for handling SLAViolation-Events by sending a Component-Redeployment-OP to the Reconfiguration Manager
        	adapt = new JPetStoreAdaptationAnalyzer();
        	//Reconfiguration Manager that executes plan via network
        	mng = new ReconfigurationManager();
        }else{
        	//Adaptation Analyzer that produces different Test-Plans
        	adapt = new AdaptationAnalyzer();
        	//Reconfiguration Manager that sends the Plan back to the Model Manager
        	mng = ReconfigurationPlanForwarder.getInstance();
        }
        
        
        //set different Analyzer-Objects, set null for not implemented ones.
        ana.setAdaptationAnalyzer(adapt);
        ana.setPerformancePredictor(null);
        ana.setWorkloadAnalyzer(null);
        
        //Instantiate ModelUpdater that is responsible for distributing incoming monitoring data
        IModelUpdater updater = new ModelUpdater();
        
        //Initalizing Controller obhect
        slactrl.setAnalysis(ana);
        slactrl.setModelManager(ModelManager.getInstance());
        slactrl.setModelUpdater(updater);
        slactrl.setReconfigurationManager(mng);
        
		//Tpan Instace for monitoring data
		TpanInstance analysisInstance = new TpanInstance();
		IKiekerMonitoringLogReader logReader;
		if(readerType.equals("FSRealtime")){
			//log reader to replay data in realtime
			logReader = new FSReaderRealtime(inputDir, 7);
			
		}else if(readerType.equals("JMS")){
			//JMS reader for reading via network in realtime
			 //logReader= new JMSReader("tcp://pc-vanhoorn.informatik.uni-oldenburg.de:3035/","queue1");
                        logReader= new JMSReader("tcp://134.106.27.209:3035/","queue1");
		}else{
			log.error("ReaderType: "+readerType+" not found");
			return;
		}
		
		//initializing Tpan object
		analysisInstance.setLogReader(logReader);
        analysisInstance.addRecordConsumer(slactrl);
        

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
