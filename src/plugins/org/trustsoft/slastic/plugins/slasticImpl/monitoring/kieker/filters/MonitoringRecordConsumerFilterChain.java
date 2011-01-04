package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import kieker.analysis.plugin.IMonitoringRecordConsumerPlugin;
import kieker.common.record.IMonitoringRecord;
import kieker.common.record.IMonitoringRecordReceiver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.AbstractControlComponent;

import de.cau.se.slastic.metamodel.core.IEvent;

/**
 * TODO: Move to SLastic Framework
 * 
 * @author Andre van Hoorn
 * 
 */
public class MonitoringRecordConsumerFilterChain implements
		IMonitoringRecordConsumerPlugin {

	private static final Log log = LogFactory
			.getLog(MonitoringRecordConsumerFilterChain.class);

	private volatile AbstractControlComponent controlComponent;

	public void setControlComponent(
			final AbstractControlComponent controlComponent) {
		this.controlComponent = controlComponent;
	}

	private final List<ISynchronousTransformationFilter> synchronousFilters =
			new ArrayList<ISynchronousTransformationFilter>();

	private final List<IMonitoringRecordReceiver> synchronousReceivers =
			new ArrayList<IMonitoringRecordReceiver>();

	// TODO: add additional filter types, e.g., asynchronous

	/**
	 * 
	 * @param filter
	 */
	public void addSynchronousFilter(
			final ISynchronousTransformationFilter filter) {
		this.synchronousFilters.add(filter);
	}

	/**
	 * 
	 * @param monitoringRecordReceiver
	 */
	public void addSynchronousReceiver(
			final IMonitoringRecordReceiver monitoringRecordReceiver) {
		this.synchronousReceivers.add(monitoringRecordReceiver);
	}

	@Override
	public void terminate(final boolean error) {
		MonitoringRecordConsumerFilterChain.log.info("Summary: "
				+ this.incomingRecordCount.get() + " records received; "
				+ this.outgoingRecordCount.get() + " records delivered");
	}

	private final AtomicInteger incomingRecordCount = new AtomicInteger(0);
	private final AtomicInteger outgoingRecordCount = new AtomicInteger(0);

	@Override
	public boolean execute() {
		// nothing to do
		return true;
	}

	@Override
	public boolean newMonitoringRecord(final IMonitoringRecord record) {
		final boolean success = true;

		this.incomingRecordCount.incrementAndGet();
		/* Transform record */

		/* Pass records to synchronous filters */
		for (final ISynchronousTransformationFilter filter : this.synchronousFilters) {
			/* Send transformation result to event processing service */
			final IEvent event = filter.transform(record);
			if (event != null) {
				MonitoringRecordConsumerFilterChain.this.controlComponent
						.getEPServiceProvider().getEPRuntime().sendEvent(event);
			}
		}

		/* Pass records to synchronous receivers */
		for (final IMonitoringRecordReceiver receiver : this.synchronousReceivers) {
			/* Send transformation result to event processing service */
			receiver.newMonitoringRecord(record);
		}

		this.outgoingRecordCount.incrementAndGet();
		return success;
	}

	public final static Collection<Class<? extends IMonitoringRecord>> RECORD_TYPE_SUBSCR_LIST =
			null; // any type; filters take care that they process only the
					// right types

	@Override
	public Collection<Class<? extends IMonitoringRecord>> getRecordTypeSubscriptionList() {
		return MonitoringRecordConsumerFilterChain.RECORD_TYPE_SUBSCR_LIST;
	}
};
