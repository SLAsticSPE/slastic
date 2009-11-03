package org.trustsoft.slastic.control;

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.systemModel.ModelManager;
import org.trustsoft.slastic.control.exceptions.AllocationContextNotInModelException;
import org.trustsoft.slastic.control.exceptions.IllegalReconfigurationOperationException;
import org.trustsoft.slastic.control.exceptions.ServerNotAllocatedException;
import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

/**
 * This Class tries to replace the simulator with forwarding the ReconfigurationPlan back to the ModelManager.
 * @author Lena Stoever
 *
 */
public class ReconfigurationPlanForwarder extends Thread implements IReconfigurationManager  {
		private static final Log log = LogFactory.getLog(ReconfigurationPlanForwarder.class);
		private ArrayBlockingQueue<SLAsticReconfigurationPlan> reconfigurationPlans;
		private boolean terminated = false;
		private static ReconfigurationPlanForwarder instance; 
		private final static int maxPlans = 20;
		
		private ReconfigurationPlanForwarder(){
			this.reconfigurationPlans = new ArrayBlockingQueue<SLAsticReconfigurationPlan>(maxPlans);
		}
		
		/**
		 * Implementation of the Singleton-Pattern.
		 * @return
		 */
		public static ReconfigurationPlanForwarder getInstance(){
			if(instance == null){
				instance = new ReconfigurationPlanForwarder();
			}
			return instance;
		}
		
		public void run(){
			while(this.reconfigurationPlans.size()!=0 && !this.terminated){
				try {
					ModelManager.getInstance().doReconfiguration(this.reconfigurationPlans.take(),true);
					//Thread.sleep(500);
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
		public synchronized void doReconfiguration(SLAsticReconfigurationPlan plan){
			try {
				this.reconfigurationPlans.put(plan);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void terminate(){
			this.terminated = true;
		}

		@Override
		public void execute() {
			this.run();
			
		}
		
}
