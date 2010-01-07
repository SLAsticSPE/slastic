package org.trustsoft.slastic.control.plugins.daLena;

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.plugins.daLena.modelManager.ModelManager;
import org.trustsoft.slastic.control.exceptions.AllocationContextNotInModelException;
import org.trustsoft.slastic.control.exceptions.IllegalReconfigurationOperationException;
import org.trustsoft.slastic.control.exceptions.ServerNotAllocatedException;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import org.trustsoft.slastic.reconfigurationManager.AbstractSLAsticReconfigurationManager;

/**
 * This Class tries to replace the simulator with forwarding the ReconfigurationPlan back to the ModelManager.
 * @author Lena Stoever
 *
 */
public class ReconfigurationPlanForwarder extends AbstractSLAsticReconfigurationManager {

    private static final Log log = LogFactory.getLog(ReconfigurationPlanForwarder.class);
    private final ArrayBlockingQueue<SLAsticReconfigurationPlan> reconfigurationPlans;
    private boolean terminated = false;
    //Maximum number of plans that ca be hold
    private final static int maxPlans = 20;

    public ReconfigurationPlanForwarder() {
        this.reconfigurationPlans = new ArrayBlockingQueue<SLAsticReconfigurationPlan>(maxPlans);
    }

    public void init(String initString) throws IllegalArgumentException {
        super.initVarsFromInitString(initString);
        // we don't expect init properties so far.
    }

    public void forwardPlans() {
        while (this.reconfigurationPlans.size() != 0 && !this.terminated) {
            try {
                //forward reconfiguration plan to the Model Manager. "true" for storing the result
                ((ModelManager) this.getControlComponent().getModelManager()).doReconfiguration(this.reconfigurationPlans.take(), true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (AllocationContextNotInModelException e) {
                e.printStackTrace();
            } catch (ServerNotAllocatedException e) {
                e.printStackTrace();
            } catch (IllegalReconfigurationOperationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void doReconfiguration(SLAsticReconfigurationPlan plan) {
        try {
            this.reconfigurationPlans.put(plan);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void terminate() {
        this.terminated = true;
    }

    @Override
    public boolean execute() {
        this.forwardPlans();
        return true;
    }
}
