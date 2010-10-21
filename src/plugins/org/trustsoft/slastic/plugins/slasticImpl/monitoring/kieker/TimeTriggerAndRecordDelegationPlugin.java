package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker;

import java.util.Collection;

import kieker.analysis.plugin.IMonitoringRecordConsumerPlugin;
import kieker.analysis.plugin.configuration.AbstractInputPort;
import kieker.common.record.IMonitoringRecord;
import kieker.tools.currentTimeEventGenerator.CurrentTimeEventGenerator;
import kieker.tools.currentTimeEventGenerator.TimestampEvent;

import org.trustsoft.slastic.control.AbstractControlComponent;

/**
 * On {@link IMonitoringRecord}s received via
 * {@link #newMonitoringRecord(IMonitoringRecord)} the
 * {@link TimeTriggerAndRecordDelegationPlugin} (1) triggers
 * {@link AbstractControlComponent#setCurrentTimeNanos(long)} with
 * {@link AbstractKiekerMonitoringManager#TIMER_EVENTS_RESOLUTION_NANOS}
 * resolution and subsequently delegates the record to the
 * {@link IMonitoringRecordConsumerPlugin} returned by
 * {@link AbstractKiekerMonitoringManager#getMonitoringRecordConsumer()}.
 * 
 * @author Andre van Hoorn
 * 
 */
class TimeTriggerAndRecordDelegationPlugin implements
		IMonitoringRecordConsumerPlugin {

	private final AbstractControlComponent controlComponent;
	private final IMonitoringRecordConsumerPlugin monitoringRecordConsumerPlugin;
	private final Collection<Class<? extends IMonitoringRecord>> recordTypeSubscriptionList;

	/**
	 * Must not be used for construction
	 */
	@SuppressWarnings("unused")
	private TimeTriggerAndRecordDelegationPlugin() {
		this(null, null);
	}

	public TimeTriggerAndRecordDelegationPlugin(
			final AbstractControlComponent controlComponent,
			final IMonitoringRecordConsumerPlugin monitoringRecordConsumerPlugin) {
		this.monitoringRecordConsumerPlugin = monitoringRecordConsumerPlugin;
		this.recordTypeSubscriptionList =
				this.monitoringRecordConsumerPlugin
						.getRecordTypeSubscriptionList();
		this.controlComponent = controlComponent;
	}

	private final CurrentTimeEventGenerator timeEventGenerator =
			new CurrentTimeEventGenerator(
					AbstractKiekerMonitoringManager.TIMER_EVENTS_RESOLUTION_NANOS);
	{
		/*
		 * Initialize CurrentTimeEventGeneratorFilter and register handler that
		 * sends time events to the event processing engine.
		 */
		this.timeEventGenerator.getCurrentTimeOutputPort().subscribe(
				new AbstractInputPort<TimestampEvent>(
						"Passes timer events to the event processing service") {
					@Override
					public void newEvent(final TimestampEvent event) {
						final long currentTimeNanos = event.getTimestamp();
						TimeTriggerAndRecordDelegationPlugin.this.controlComponent
								.setCurrentTimeNanos(currentTimeNanos);
					}

				});
	}

	/**
	 * It is important that the timer event is sent before the incoming record
	 * is processed.
	 */
	@Override
	public boolean newMonitoringRecord(final IMonitoringRecord record) {
		final long timestamp = record.getLoggingTimestamp();
		/*
		 * Pass each record to timeEventGenerator which might result in time
		 * event trigger
		 */
		this.timeEventGenerator.newTimestamp(timestamp);

		boolean success = true;

		/* Delegate record to actual consumer */
		if ((this.recordTypeSubscriptionList == null)
				|| this.recordTypeSubscriptionList.contains(record.getClass())) {
			success =
					this.monitoringRecordConsumerPlugin
							.newMonitoringRecord(record);
		}

		return success;
	}

	@Override
	public boolean execute() {
		return this.monitoringRecordConsumerPlugin.execute();
	}

	@Override
	public void terminate(final boolean error) {
		this.monitoringRecordConsumerPlugin.terminate(error);

	}

	@Override
	public Collection<Class<? extends IMonitoringRecord>> getRecordTypeSubscriptionList() {
		return null; // receive records of any type and not only those of
		// getMonitoringRecordConsumer()
	}

}
