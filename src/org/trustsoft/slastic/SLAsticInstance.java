package org.trustsoft.slastic;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.openarchitectureware.workflow.WorkflowRunner;
import org.openarchitectureware.workflow.monitor.ProgressMonitor;

import org.trustsoft.slastic.slasticqosannotations.ExternalServiceResponseTimeObjective;
import org.trustsoft.slastic.slasticqosannotations.InternalServiceResponseTimeObjective;
import org.trustsoft.slastic.slasticqosannotations.QoSObjective;
import org.trustsoft.slastic.slasticqosannotations.SLAsticQoSAnnotations;
import org.trustsoft.slastic.slasticresourceenvironment.SLAsticResourceEnvironment;
import org.trustsoft.slastic.slasticresourceenvironment.ServerNode;

/**
 * @author Andre van Hoorn, Lena Stoever
 */
public class SLAsticInstance {
    private static final Log log = LogFactory.getLog(SLAsticInstance.class);

    private 

    private static void testSLAsticMetaModel() {
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

        /* Dumps the record type ID */
//        tpanInstance.addConsumer(new MonitoringRecordTypeLogger());

        /* Collects all executions */
//        ExecutionSequenceRepositoryFiller seqRepConsumer = new ExecutionSequenceRepositoryFiller();
//        tpanInstance.addConsumer(seqRepConsumer);

        /* Dumps response times */
//        ResponseTimePlotter rtPlotter = new ResponseTimePlotter();
//        tpanInstance.addConsumer(rtPlotter);
        //String wfFile = "../../SLALproject/src/SLALproject.oaw";

//            ISLAsticReconfigurationManager reconfigurationManager =
//                    new ReconfigurationManagerWget();
//        final int NUM_RECONFIGURATIONS = 5;
//        for (int i = 1; i <= NUM_RECONFIGURATIONS; i++) {
//            try {
//                log.info("Requesting reconfiguration " + i + "/" + NUM_RECONFIGURATIONS);
//                reconfigurationManager.doReconfiguration(null);
//                Thread.sleep(5000);
//            } catch (Exception ex) {
//                Logger.getLogger(ControlComponent.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

        //System.exit(0);

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
