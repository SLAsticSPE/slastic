package org.trustsoft.slastic.control.recordConsumer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TestAskThread extends Thread {
	ResponseTimeAverageCalculator calc;

    private static final DateFormat m_ISO8601Local =
                new SimpleDateFormat("yyyyMMdd'-'HHmmss");

	public TestAskThread(ResponseTimeAverageCalculator calc){
		this.calc=calc;
	}
	
	public void run(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(m_ISO8601Local.format(new java.util.Date())+": AVERAGE:::::::::"+this.calc.getAverageResponseTime()/(1000*1000));
		}
	}

}
