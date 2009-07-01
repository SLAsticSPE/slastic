package org.trustsoft.slastic.control.recordConsumer;

public class TestAskThread extends Thread {
	ResponseTimeAverageCalculator calc;
	
	public TestAskThread(ResponseTimeAverageCalculator calc){
		this.calc=calc;
	}
	
	public void run(){
		try {
			Thread.sleep(30);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true){
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("AVERAGE:::::::::"+this.calc.getAverageResponseTime());
		}
	}

}
