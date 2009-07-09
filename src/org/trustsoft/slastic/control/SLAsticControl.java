package org.trustsoft.slastic.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.trustsoft.slastic.control.recordConsumer.ResponseTimeCalculator;

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
        final TreeMap<Integer,TreeMap<Float,Long>> map = new TreeMap<Integer, TreeMap<Float,Long>>();
        TreeMap<Float, Long> slo = new TreeMap<Float, Long>();
        slo.put(new Float(0.90f),new Long(1850000000));
        slo.put(new Float(0.95), new Long(1950000000));
        slo.put(new Float(0.99), new Long(1970000000));
        map.put(new Integer(77), slo);
        map.put(new Integer(12), slo);
        
        ScheduledThreadPoolExecutor ex = new ScheduledThreadPoolExecutor(map.size());
        
        
        final ResponseTimeCalculator rtac = new ResponseTimeCalculator(map.keySet().toArray(new Integer[map.size()]));
        analysisInstance.addRecordConsumer(rtac);
        final DateFormat m_ISO8601Local = new SimpleDateFormat("yyyyMMdd'-'HHmmss");
        
        for(int i = 0; i< map.size(); i++){
        	final int ID = map.keySet().toArray(new Integer[map.size()])[i];
        	final Float[] quantile = map.get(ID).keySet().toArray(new Float[map.get(ID).size()]);
        	ex.scheduleAtFixedRate(new Runnable() {
                public void run() {
                	long[] responseTimes = rtac.getQuantilResponseTime(quantile, ID);
                	for(int j = 0; j<responseTimes.length; j++){
                		if(responseTimes[j]> map.get(ID).get(quantile[j])){
                			System.out.println("SLA for service "+ID+" for quantile: "+quantile[j]+" NOT satisfied : "+responseTimes[j]+" > "+map.get(ID).get(quantile[j]));
                		}else
                			System.out.println("SLA for service "+ID+" for quantile: "+quantile[j]+" SATISFIED: "+responseTimes[j]+" <= "+map.get(ID).get(quantile[j]));
                			
                	}
                    //System.out.println(m_ISO8601Local.format(new java.util.Date()) + ": QUANTIL:::::::::" + rtac.getQuantilResponseTime(quantile, ID)[0]);
                }
            }, 1+i, 1, TimeUnit.SECONDS);
        }
        

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
