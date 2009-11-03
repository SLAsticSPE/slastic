/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trustsoft.slastic.reconfigurationManager;

import ReconfigurationPlanModel.ComponentDeReplicationOP;
import ReconfigurationPlanModel.ComponentMigrationOP;
import ReconfigurationPlanModel.ComponentRedeploymentOP;
import ReconfigurationPlanModel.ComponentReplicationOP;
import ReconfigurationPlanModel.NodeAllocationOP;
import ReconfigurationPlanModel.NodeDeAllocationOP;
import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import ReconfigurationPlanModel.impl.ComponentDeReplicationOPImpl;
import ReconfigurationPlanModel.impl.ComponentMigrationOPImpl;
import ReconfigurationPlanModel.impl.ComponentReplicationOPImpl;
import ReconfigurationPlanModel.impl.NodeAllocationOPImpl;
import ReconfigurationPlanModel.impl.NodeDeAllocationOPImpl;
import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.trustsoft.slastic.control.exceptions.AllocationContextNotInModelException;
import org.trustsoft.slastic.control.exceptions.IllegalReconfigurationOperationException;
import org.trustsoft.slastic.control.exceptions.ServerNotAllocatedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class ReconfigurationManager implements IReconfigurationManager {

    private final Log log = LogFactory.getLog(ReconfigurationManager.class);

    public synchronized void doReconfiguration(
            ReconfigurationPlanModel.SLAsticReconfigurationPlan plan)
            throws ReconfigurationException {
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
                argList.add("wget http://pc-vanhoorn:8080/catalogComplexityManagerServlet/index?action=setComplexity&complexity=200");
                ShellExecutor.invoke(
                        "/bin/bash", /* command */
                        argList, /* arg list */
                        true);
            } else {
                throw new UnsupportedOperationException();
            }
        }
    }

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void terminate() {
		// TODO Auto-generated method stub
		
	}
}
