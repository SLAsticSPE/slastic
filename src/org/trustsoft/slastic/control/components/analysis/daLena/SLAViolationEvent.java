package org.trustsoft.slastic.control.components.analysis.daLena;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.components.analysis.ISLAsticAnalysisEvent;

public class SLAViolationEvent implements ISLAsticAnalysisEvent {
	private static final Log log = LogFactory.getLog(SLAViolationEvent.class);
	public int serviceID;
	
	SLAViolationEvent(int serviceID){
		this.serviceID = serviceID;
	}
	
	public int getServiceID(){
		return this.serviceID;
	}
	
}
