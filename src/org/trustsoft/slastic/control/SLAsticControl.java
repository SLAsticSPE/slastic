package org.trustsoft.slastic.control;

import kieker.common.logReader.LogReaderExecutionException;
import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.common.logReader.filesystemReader.realtime.FSReaderRealtime;
import kieker.tpan.TpanInstance;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.analysis.AdaptationAnalyzer;
import org.trustsoft.slastic.control.analysis.Analysis;
import org.trustsoft.slastic.control.analysis.SLAChecker;
import org.trustsoft.slastic.control.systemModel.IModelUpdater;
import org.trustsoft.slastic.control.systemModel.ModelManager;


/**
 * @author Andre van Hoorn
 */
public class SLAsticControl {

    private static final Log log = LogFactory.getLog(SLAsticControl.class);

    public static void main(String[] args) {
        log.info("Hi, this is SLAsticControl");

        String inputDir = System.getProperty("inputDir");
        if (inputDir == null || inputDir.length() == 0 || inputDir.equals("${inputDir}")) {
            log.error("No input dir found!");
            log.error("Provide an input dir as system property.");
            log.error("Example to read all tpmon-* files from /tmp:\n" +
                    "                    ant -DinputDir=/tmp/ run-SLAsticControl    ");
            System.exit(1);
        } else {
            log.info("Reading all tpmon-* files from " + inputDir);
        }
        
        org.trustsoft.slastic.control.recordConsumer.SLAsticControl slactrl = new org.trustsoft.slastic.control.recordConsumer.SLAsticControl();
        Analysis ana = new Analysis();
        SLAChecker slaChecker = new SLAChecker();
        ana.setPerformanceAnalyzer(slaChecker);
        AdaptationAnalyzer adapt = new AdaptationAnalyzer();
        ana.setAdaptationAnalyzer(adapt);
        ana.setPerformancePredictor(null);
        ana.setWorkloadAnalyzer(null);
        IModelUpdater updater = new org.trustsoft.slastic.control.systemModel.ModelUpdater();
        slactrl.setAnalysis(ana);
        slactrl.setModelManager(ModelManager.getInstance());
        slactrl.setModelUpdater(updater);
        slactrl.setReconfigurationManager(ReconfigurationPlanForwarder.getInstance());
        
        try {
			slactrl.execute();
		} catch (RecordConsumerExecutionException e1) {
			e1.printStackTrace();
		}
        
        FSReaderRealtime fsReaderRealtime = new FSReaderRealtime(inputDir, 7);
        TpanInstance analysisInstance = new TpanInstance();
        analysisInstance.setLogReader(fsReaderRealtime);
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
