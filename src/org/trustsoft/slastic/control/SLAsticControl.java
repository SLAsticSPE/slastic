package org.trustsoft.slastic.control;

import java.io.File;
import java.util.Collection;
import kieker.common.logReader.IMonitoringRecordConsumer;
import kieker.common.logReader.filesystemReader.FilesystemReader;
import kieker.common.tools.logReplayer.ReplayDistributor;
import kieker.loganalysis.LogAnalysisInstance;
import kieker.loganalysis.datamodel.ExecutionSequence;
import kieker.loganalysis.plugins.DependencyGraphPlugin;
import kieker.loganalysis.recordConsumer.ExecutionSequenceRepositoryFiller;
import kieker.loganalysis.recordConsumer.MonitoringRecordTypeLogger;
import kieker.tpmon.core.TpmonController;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.recordConsumer.ResponseTimeAverageCalculator;
import org.trustsoft.slastic.control.recordConsumer.ResponseTimePlotter;

/**
 * @author Andre van Hoorn
 */
public class SLAsticControl {

    private static final Log log = LogFactory.getLog(SLAsticControl.class);

    private static final TpmonController ctrlInst = TpmonController.getInstance();

    private static final IMonitoringRecordConsumer logCons = new IMonitoringRecordConsumer() {

                /** Anonymous consumer class that simply passes all records to the
                 *  controller */
                public String[] getRecordTypeSubscriptionList() {
                    return null; // consume all types
                }

                public void consumeMonitoringRecord(final AbstractKiekerMonitoringRecord monitoringRecord) {
                    ctrlInst.logMonitoringRecord(monitoringRecord);
                }

                public boolean execute() {
                    // do nothing, we are synchronous
                    return true;
                }

                public void terminate() {
                    ctrlInst.terminateMonitoring();
                }
            };

    public static void main(String[] args){
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

        LogAnalysisInstance analysisInstance = new LogAnalysisInstance();
        analysisInstance.addLogReader(new FilesystemReader(inputDir));

        /* Dumps the record type ID */
        analysisInstance.addConsumer(new MonitoringRecordTypeLogger());

        /* Collects all executions */
//        ExecutionSequenceRepositoryFiller seqRepConsumer = new ExecutionSequenceRepositoryFiller();
//        analysisInstance.addConsumer(seqRepConsumer);

        /* Dumps response times */
//        ResponseTimePlotter rtPlotter = new ResponseTimePlotter();
//        analysisInstance.addConsumer(rtPlotter);
        ResponseTimeAverageCalculator rtac = new ResponseTimeAverageCalculator();
       //IMonitoringRecordConsumer rtDistributorCons = new ReplayDistributor(7, rtac);
        //analysisInstance.addConsumer(rtDistributorCons);

        
        
        analysisInstance.addConsumer(rtac);

        /* Replays traces in real time */
       
        analysisInstance.run();
        
        

        /* Example that plots a dependency graph */
        /* generate dependency diagram */


//        Collection<ExecutionSequence> seqEnum = seqRepConsumer.getExecutionSequenceRepository().repository.values();
//        DependencyGraphPlugin.writeDotFromExecutionTraces(seqEnum, inputDir+File.separator+"/dependencyGraph.dot");
//        log.info("Wrote dependency graph to file " + inputDir+File.separator+"/dependencyGraph.dot");



        log.info("Bye, this was SLAsticControl");
        System.exit(0);
    }
}
