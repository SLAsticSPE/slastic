package org.trustsoft.slastic.control;

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.analysis.AdaptationAnalyzer;
import org.trustsoft.slastic.control.recordConsumer.QuantileCalculator;
import org.trustsoft.slastic.control.systemModel.ModelManager;

import ReconfigurationPlanModel.ComponentMigrationOP;
import ReconfigurationPlanModel.ReconfigurationPlanModelFactory;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import ReconfigurationPlanModel.impl.ReconfigurationPlanModelFactoryImpl;

public class ReconfigurationPlanForwarder extends Thread {
		private static final Log log = LogFactory.getLog(QuantileCalculator.class);
		private ArrayBlockingQueue<SLAsticReconfigurationPlan> reconfigurationPlans;
		private static ReconfigurationPlanForwarder instance; 
		private static int maxPlans = 20;
		
		private ReconfigurationPlanForwarder(){
			this.reconfigurationPlans = new ArrayBlockingQueue<SLAsticReconfigurationPlan>(maxPlans);
		}
		
		public static ReconfigurationPlanForwarder getInstance(){
			if(instance == null){
				instance = new ReconfigurationPlanForwarder();
			}
			return instance;
		}
		
		public void run(){
			while(this.reconfigurationPlans.size()!=0){
				try {
					log.info("Ob ich hier wohl zu lange warte?");
					ModelManager.getInstance().doReconfiguration(this.reconfigurationPlans.take(),true);
					log.info("Nicht zu lange gewartet");
					//Thread.sleep(500);
				} catch (InterruptedException e) {
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
