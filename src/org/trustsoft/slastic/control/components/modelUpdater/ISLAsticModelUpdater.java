package org.trustsoft.slastic.control.components.modelUpdater;

import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;
import org.trustsoft.slastic.control.components.ISLAsticComponent;

public interface ISLAsticModelUpdater extends ISLAsticComponent {

	/**
	 * This method identifies the type of the given record and forwards it to the Model Manager
	 * @param record given monitoring record
	 */
	public void update(AbstractKiekerMonitoringRecord record);
}
