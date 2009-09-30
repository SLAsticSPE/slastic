package org.trustsoft.slastic.control;

import java.util.HashMap;
import java.util.Map;
import kieker.common.logReader.LogReaderExecutionException;
import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.common.logReader.filesystemReader.realtime.FSReaderRealtime;
import kieker.tpan.TpanInstance;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.org.eclipse.jdt.internal.core.ModelUpdater;
import org.openarchitectureware.workflow.WorkflowRunner;
import org.openarchitectureware.workflow.monitor.NullProgressMonitor;
import org.trustsoft.slastic.control.analysis.SLAChecker;
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
        //String wfFile ="../../../workspace/SLALproject/src/SLALproject.oaw";
        String wfFile = "../../../workspace2/SLAstic-Framework/trunk/src/org/trustsoft/slastic/control/InitModelsMac.oaw";
        //String wfFile = "/home/voorn/svn_work/sw_DALenaRobert/SLAstic-Framework/trunk/src/org/trustsoft/slastic/control/InitModels-Andre.oaw";
        Map<String, String> properties = new HashMap<String, String>();
        Map<String, String> slotContents = new HashMap<String, String>();
        WorkflowRunner runner = new WorkflowRunner();
        runner.run(wfFile, new NullProgressMonitor(), properties, slotContents);
        slal.Model slas = (slal.Model) runner.getContext().get("theModel");
        reconfMM.ReconfigurationModel reconfigurationModel = (reconfMM.ReconfigurationModel) runner.getContext().get("reconfigurationModel");
        
        //Das sollte nun immer gemacht werden:
        ModelManager.getInstance().initModel(reconfigurationModel);
       
//        for(int i = 0; i<reconfigurationModel.getComponents().size(); i++){
//        	System.out.println(reconfigurationModel.getComponents().get(i).getComponent().getEntityName());        }
//        for(int i = 0; i<slas.getObligations().getSlo().size(); i++){
//        	System.out.println(slas.getObligations().getSlo().get(i).getServiceID());        }

        SLAChecker rtac = new SLAChecker(slas);
        org.trustsoft.slastic.control.recordConsumer.ModelUpdater updater = new org.trustsoft.slastic.control.recordConsumer.ModelUpdater(reconfigurationModel.getMaxResponseTimes());
        FSReaderRealtime fsReaderRealtime = new FSReaderRealtime(inputDir, 7);

        TpanInstance analysisInstance = new TpanInstance();
        analysisInstance.setLogReader(fsReaderRealtime);
        analysisInstance.addRecordConsumer(updater);
        
        rtac.start();

        try {
        	log.info("run sollte ausgef�hrtwerden");
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

        log.info("Bye, this was SLAsticControl");
        //System.exit(0);
    }
}
