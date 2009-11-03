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
import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;
import org.trustsoft.slastic.reconfigurationManager.ReconfigurationManager;


/**
 * @author Andre van Hoorn
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
        if(exampleType == null || exampleType.length() == 0){
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
        
        org.trustsoft.slastic.control.recordConsumer.SLAsticControl slactrl =
                new org.trustsoft.slastic.control.recordConsumer.SLAsticControl(initWorkflow_fn);
        Analysis ana = new Analysis();
        SLAChecker slaChecker = new SLAChecker();
        ana.setPerformanceAnalyzer(slaChecker);
        IAdaptationAnalyzer adapt;
        IReconfigurationManager mng;
        if(exampleType.equals("JPetStore")){
        	adapt = new JPetStoreAdaptationAnalyzer();
        	mng = new ReconfigurationManager();
        }else{
        	adapt = new AdaptationAnalyzer();
        	mng = ReconfigurationPlanForwarder.getInstance();
        }
        
        ana.setAdaptationAnalyzer(adapt);
        ana.setPerformancePredictor(null);
        ana.setWorkloadAnalyzer(null);;
        IModelUpdater updater = new org.trustsoft.slastic.control.systemModel.ModelUpdater();
        slactrl.setAnalysis(ana);
        slactrl.setModelManager(ModelManager.getInstance());
        slactrl.setModelUpdater(updater);
        slactrl.setReconfigurationManager(mng);
        
        try {
			slactrl.execute();
		} catch (RecordConsumerExecutionException e1) {
			e1.printStackTrace();
		}
		TpanInstance analysisInstance = new TpanInstance();
		IKiekerMonitoringLogReader logReader;
		if(readerType.equals("FSRealtime")){
			logReader = new FSReaderRealtime(inputDir, 7);
			
		}else if(readerType.equals("JMS")){
			 logReader= new JMSReader("tcp://127.0.0.1:3035/","queue1");
		}else{
			log.error("ReaderType: "+readerType+" not found");
			return;
		}
		analysisInstance.setLogReader(logReader);
        analysisInstance.addRecordConsumer(slactrl);
        

        try {
            analysisInstance.run();
        } catch (LogReaderExecutionException e) {
            log.error("LogReaderExecutionException:", e);
        } catch (RecordConsumerExecutionException e) {
            log.error("RecordConsumerExecutionException:", e);
        }
        /* Example that plots a dependency graph */
        /* generate dependency diagram */


//        Collection<ExecutionSequence> seqEnum = seqRepConsumer.getExecutionSequenceRepository().repository.values();
//        DependencyGraphPlugin.writeDotFromExecutionTraces(seqEnum, inputDir+File.separator+"/dependencyGraph.dot");
//        log.info("Wrote dependency graph to file " + inputDir+File.separator+"/dependencyGraph.dot");
        ReconfigurationPlanForwarder.getInstance().terminate();
        log.info("Bye, this was SLAsticControl");
        //System.exit(0);
    }
}
