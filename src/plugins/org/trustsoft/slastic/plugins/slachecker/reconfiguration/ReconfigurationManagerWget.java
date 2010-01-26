package org.trustsoft.slastic.plugins.slachecker.reconfiguration;


import org.trustsoft.slastic.reconfiguration.*;
import ReconfigurationPlanModel.ComponentRedeploymentOP;
import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import java.util.ArrayList;

import org.eclipse.emf.common.util.EList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class ReconfigurationManagerWget extends AbstractSLAsticReconfigurationManager {

    private final Log log = LogFactory.getLog(ReconfigurationManagerWget.class);

    public synchronized void doReconfiguration(
            ReconfigurationPlanModel.SLAsticReconfigurationPlan plan)
            throws SLAsticReconfigurationException {
        EList<SLAsticReconfigurationOpType> operations = plan.getOperations();
        for (SLAsticReconfigurationOpType op : operations) {
            // Check of which type the Operation is
//            if (op instanceof ComponentDeReplicationOPImpl) {
//                throw new UnsupportedOperationException();
//            } else if (op instanceof ComponentMigrationOPImpl) {
//                throw new UnsupportedOperationException();
//            } else if (op instanceof ComponentReplicationOPImpl) {
//                throw new UnsupportedOperationException();
//            } else if (op instanceof NodeAllocationOPImpl) {
//                throw new UnsupportedOperationException();
//            } else if (op instanceof NodeDeAllocationOPImpl) {
//                throw new UnsupportedOperationException();
//            } else {
            if (op instanceof ComponentRedeploymentOP) {
                log.info("Initiating Redeployment");
                ArrayList<String> argList = new ArrayList<String>();
                argList.add("-c");
                if(System.getProperty("os.name").contains("Mac")){
                	argList.add("/usr/local/bin/wget 'http://127.0.0.1:8080/catalogComplexityManagerServlet/index?action=setComplexity&complexity=200'");
                }else{
                	argList.add("wget 'http://127.0.0.1:8080/catalogComplexityManagerServlet/index?action=setComplexity&complexity=200'");
                }  
                ShellExecutor.invoke(
                        "/bin/bash", /* command */
                        argList, /* arg list */
                        true);
            } else {
                throw new UnsupportedOperationException();
            }
        }
    }

    public void init(String initString) throws IllegalArgumentException {
        super.initVarsFromInitString(initString);
        // we don't expect init properties so far.
    }

    public boolean execute() {
        return true;
    }

    public void terminate() {  }
}
