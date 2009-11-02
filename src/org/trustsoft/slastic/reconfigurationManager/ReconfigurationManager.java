/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trustsoft.slastic.reconfigurationManager;

import ReconfigurationPlanModel.ComponentDeReplicationOP;
import ReconfigurationPlanModel.ComponentMigrationOP;
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
//        EList<SLAsticReconfigurationOpType> operations = plan.getOperations();
//        for (int i = 0; i < operations.size(); i++) {
//            SLAsticReconfigurationOpType op = operations.get(i);
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
                log.warn("Ignoring operation plan -- performing redeployment");
                ArrayList argList = new ArrayList<String>();
                argList.add("-c");
                argList.add("touch /tmp/reconfiguration");
                ShellExecutor.invoke(
                        "/bin/bash", /* command */
                        argList, /* arg list */
                        true);
//            }
//        }
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
