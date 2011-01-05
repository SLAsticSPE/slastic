package org.trustsoft.slastic.plugins.pcm.reconfiguration;

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.exceptions.IllegalReconfigurationOperationException;
import org.trustsoft.slastic.plugins.pcm.control.modelManager.AllocationContextNotInModelException;
import org.trustsoft.slastic.plugins.pcm.control.modelManager.ModelManager;
import org.trustsoft.slastic.plugins.pcm.control.modelManager.ServerNotAllocatedException;
import org.trustsoft.slastic.reconfiguration.AbstractReconfigurationManagerComponent;
import org.trustsoft.slastic.reconfiguration.ReconfigurationException;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan;

/**
 * This Class tries to replace the simulator with forwarding the ReconfigurationPlan back to the ModelManager.
 * @author Lena Stoever
 *
 */
public class ReconfigurationPlanForwarder extends AbstractReconfigurationManagerComponent {

    private static final Log log = LogFactory.getLog(ReconfigurationPlanForwarder.class);
    private final ArrayBlockingQueue<SLAsticReconfigurationPlan> reconfigurationPlans;
    private volatile boolean terminated = false;
    //Maximum number of plans that ca be hold
    private final static int maxPlans = 20;

    public ReconfigurationPlanForwarder() {
        this.reconfigurationPlans = new ArrayBlockingQueue<SLAsticReconfigurationPlan>(ReconfigurationPlanForwarder.maxPlans);
    }

    public void forwardPlans() {
        while ((this.reconfigurationPlans.size() != 0) && !this.terminated) {
            try {
                //forward reconfiguration plan to the Model Manager. "true" for storing the result
                ((ModelManager) this.getControlComponent().getModelManager()).doReconfiguration(this.reconfigurationPlans.take(), true);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            } catch (final AllocationContextNotInModelException e) {
                e.printStackTrace();
            } catch (final ServerNotAllocatedException e) {
                e.printStackTrace();
            } catch (final IllegalReconfigurationOperationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void doReconfiguration(final SLAsticReconfigurationPlan plan) {
        try {
            this.reconfigurationPlans.put(plan);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void terminate(final boolean error) {
        this.terminated = true;
    }

    @Override
    public boolean execute() {
        this.forwardPlans();
        return true;
    }

    @Override
    public boolean init() {
        return true;
    }

	@Override
	public void doReconfiguration(final ReconfigurationPlan plan)
			throws ReconfigurationException {
		throw new UnsupportedOperationException();
	}
}
