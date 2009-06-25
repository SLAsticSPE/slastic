package org.trustsoft.slastic.control;

import java.util.Hashtable;
import kieker.loganalysis.LogAnalysisController;
import kieker.loganalysis.consumer.ExecutionSequenceRepositoryFiller;
import kieker.loganalysis.consumer.MonitoringRecordLogger;
import kieker.loganalysis.datamodel.ExecutionSequence;
import kieker.loganalysis.logReader.FSReader;
import kieker.loganalysis.plugins.DependencyGraphPlugin;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Andre van Hoorn
 */
public class SLAsticControl {
    private static final Log log = LogFactory.getLog(SLAsticControl.class);
    
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

        LogAnalysisController analysisInstance = new LogAnalysisController();
        analysisInstance.setLogReader(new FSReader(inputDir));
        analysisInstance.addConsumer(new MonitoringRecordLogger());
        ExecutionSequenceRepositoryFiller seqRepConsumer = new ExecutionSequenceRepositoryFiller();
        analysisInstance.addConsumer(seqRepConsumer);
        analysisInstance.run();

        /* Example that plots a dependency graph */
        /* generate dependency diagram */
        Hashtable<Long, ExecutionSequence> sequenceTable = seqRepConsumer.getExecutionSequenceRepository().getRepositoryAsHashTable();
        DependencyGraphPlugin depGraphTool = new DependencyGraphPlugin();
        depGraphTool.processExecutionTraces(sequenceTable.values());

        log.info("Bye, this was SLAsticControl");
    }
}
