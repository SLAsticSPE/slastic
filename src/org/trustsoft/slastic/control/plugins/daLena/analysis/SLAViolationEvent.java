package org.trustsoft.slastic.control.plugins.daLena.analysis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.components.events.ISLAsticEvent;

public class SLAViolationEvent implements ISLAsticEvent {
	private static final Log log = LogFactory.getLog(SLAViolationEvent.class);
	public int serviceID;
	
	SLAViolationEvent(int serviceID){
		this.serviceID = serviceID;
	}
	
	public int getServiceID(){
		return this.serviceID;
	}
	
}
