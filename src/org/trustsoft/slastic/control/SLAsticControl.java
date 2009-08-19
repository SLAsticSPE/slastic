package org.trustsoft.slastic.control;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kieker.common.logReader.IKiekerRecordConsumer;
import kieker.common.logReader.LogReaderExecutionException;
import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.common.logReader.filesystemReader.FSReader;
import kieker.common.tools.logReplayer.ReplayDistributor;
import kieker.tpan.TpanInstance;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openarchitectureware.workflow.WorkflowRunner;
import org.openarchitectureware.workflow.monitor.NullProgressMonitor;
import org.trustsoft.slastic.control.recordConsumer.SLAChecker;

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

        TpanInstance analysisInstance = new TpanInstance();
        analysisInstance.setLogReader(new FSReader(inputDir));
//        new JMSReader( );

        /* Dumps the record type ID */
//        analysisInstance.addConsumer(new MonitoringRecordTypeLogger());

        /* Collects all executions */
//        ExecutionSequenceRepositoryFiller seqRepConsumer = new ExecutionSequenceRepositoryFiller();
//        analysisInstance.addConsumer(seqRepConsumer);

        /* Dumps response times */
//        ResponseTimePlotter rtPlotter = new ResponseTimePlotter();
//        analysisInstance.addConsumer(rtPlotter);
        String wfFile = "/Users/Lena/Documents/workspace/SLALproject/src/SLALproject.oaw";
        Map properties = new HashMap();
        Map slotContents = new HashMap();
        WorkflowRunner runner = new WorkflowRunner(); 
        runner.run(wfFile, new NullProgressMonitor(), properties, slotContents);
        slal.Model slas = (slal.Model)runner.getContext().get("theModel");
        
        
        final SLAChecker rtac = new SLAChecker(slas);
        analysisInstance.addRecordConsumer(rtac);
      

        IKiekerRecordConsumer rtDistributorCons = new ReplayDistributor(7, rtac);
        analysisInstance.addRecordConsumer(rtDistributorCons);

        /* Replays traces in real time */

        try {
        analysisInstance.run();
        } catch (LogReaderExecutionException e){
            log.error("LogReaderExecutionException:", e);
        } catch (RecordConsumerExecutionException e){
            log.error("RecordConsumerExecutionException:", e);
        }

        


        /* Example that plots a dependency graph */
        /* generate dependency diagram */


//        Collection<ExecutionSequence> seqEnum = seqRepConsumer.getExecutionSequenceRepository().repository.values();
//        DependencyGraphPlugin.writeDotFromExecutionTraces(seqEnum, inputDir+File.separator+"/dependencyGraph.dot");
//        log.info("Wrote dependency graph to file " + inputDir+File.separator+"/dependencyGraph.dot");



        log.info("Bye, this was SLAsticControl");
    //System.exit(0);
    }
    }
