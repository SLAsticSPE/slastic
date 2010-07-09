package org.trustsoft.slastic.plugins.slachecker.control.analysis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.components.events.IEvent;

public class SLAViolationEvent implements IEvent {
	private static final Log log = LogFactory.getLog(SLAViolationEvent.class);
	public int serviceID;
	
	SLAViolationEvent(int serviceID){
		this.serviceID = serviceID;
	}
	
	public int getServiceID(){
		return this.serviceID;
	}
	
}
