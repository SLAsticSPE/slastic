/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

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
