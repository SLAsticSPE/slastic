package org.trustsoft.slastic.tests.junit.framework.esper.externalTime.outputLimiting;

import java.util.ArrayList;
import java.util.List;

import com.espertech.esper.client.EPServiceProvider;

/**
 * Subscriber that records sequence of events observed on the insert and remove
 * stream.
 * 
 * @author Andre van Hoorn
 * 
 */
class IRStreamCollector {
	private final List<StreamRecord> iStream =
			new ArrayList<StreamRecord>();
	
	/**
	 * Returns the recorded insert stream.
	 * 
	 * @return
	 */
	public List<StreamRecord> getiStream() {
		return this.iStream;
	}

	/**
	 * Returns the recorded remove stream.
	 * 
	 * @return
	 */
	public List<StreamRecord> getrStream() {
		return this.rStream;
	}

	private final List<StreamRecord> rStream =
			new ArrayList<StreamRecord>();
	private final EPServiceProvider epService;

	public IRStreamCollector(final EPServiceProvider epService) {
		this.epService = epService;
	}

	/**
	 * Stores the received row in the sequence of records observed on the insert
	 * stream.
	 * 
	 * @param row
	 */
	public void update(final Object[] row) {
		this.iStream.add(new StreamRecord(this.epService.getEPRuntime()
				.getCurrentTime(), row));
	}

	/**
	 * Stores the received row in the sequence of records observed on the remove
	 * stream.
	 * 
	 * @param row
	 */
	public void updateRStream(final Object[] row) {
		this.rStream.add(new StreamRecord(this.epService.getEPRuntime()
				.getCurrentTime(), row));
	}
}