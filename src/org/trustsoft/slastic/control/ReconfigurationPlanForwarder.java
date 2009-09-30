package org.trustsoft.slastic.control;

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.recordConsumer.QuantileCalculator;
import org.trustsoft.slastic.control.systemModel.ModelManager;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

public class ReconfigurationPlanForwarder extends Thread {
		private static final Log log = LogFactory.getLog(QuantileCalculator.class);
		private ArrayBlockingQueue<SLAsticReconfigurationPlan> reconfigurationPlans;
		
		public ReconfigurationPlanForwarder(int maxPlans){
			this.reconfigurationPlans = new ArrayBlockingQueue<SLAsticReconfigurationPlan>(maxPlans);
		}
		
		public void run(){
			while(this.reconfigurationPlans.size()!=0){
				try {
					ModelManager.getInstance().doReconfiguration(this.reconfigurationPlans.take());
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void addPlan(SLAsticReconfigurationPlan plan){
			try {
				this.reconfigurationPlans.put(plan);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
}
