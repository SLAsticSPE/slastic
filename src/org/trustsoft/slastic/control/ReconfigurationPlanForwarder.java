package org.trustsoft.slastic.control;

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.systemModel.ModelManager;
import org.trustsoft.slastic.control.systemModel.exceptions.AllocationContextNotInModelException;
import org.trustsoft.slastic.control.systemModel.exceptions.IllegalReconfigurationOperationException;
import org.trustsoft.slastic.control.systemModel.exceptions.ServerNotAllocatedException;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

/**
 * This Class tries to replace the simulator with forwarding the ReconfigurationPlan back to the ModelManager.
 * @author Lena Stoever
 *
 */
public class ReconfigurationPlanForwarder extends Thread {
		private static final Log log = LogFactory.getLog(ReconfigurationPlanForwarder.class);
		private ArrayBlockingQueue<SLAsticReconfigurationPlan> reconfigurationPlans;
		private static ReconfigurationPlanForwarder instance; 
		private static int maxPlans = 20;
		
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
			while(this.reconfigurationPlans.size()!=0){
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
		
		public synchronized void addPlan(SLAsticReconfigurationPlan plan){
			try {
				this.reconfigurationPlans.put(plan);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
}
