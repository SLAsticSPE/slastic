package org.trustsoft.slastic.tests.junit.framework.esper.quickStart;

import junit.framework.TestCase;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
 * Esper test based on the quick start presented in
 * http://esper.codehaus.org/tutorials/tutorial/quickstart.html
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestEsperQuickStart extends TestCase {
	public void testCreateSendReceiveEvent() {

		/* Creating a statement */
		EPServiceProvider epService = EPServiceProviderManager
				.getDefaultProvider();
		String expression = "select avg(price) from "
				+ OrderEvent.class.getName() + ".win:time(30 sec)";
		EPStatement statement = epService.getEPAdministrator().createEPL(
				expression);

		/* Adding a listener */
		MyListener listener = new MyListener();
		statement.addListener(listener);

		epService.getEPRuntime().sendEvent(new OrderEvent("shirt1", 80.00));
		epService.getEPRuntime().sendEvent(new OrderEvent("shirt2", 60.00));
		epService.getEPRuntime().sendEvent(new OrderEvent("shirt3", 40.00));
		epService.getEPRuntime().sendEvent(new OrderEvent("shirt4", 20.00));
	}
}

class MyListener implements UpdateListener {
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		EventBean event = newEvents[0];
		System.out.println("avg=" + event.get("avg(price)"));
	}
}
