package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker;

import org.trustsoft.slastic.control.AbstractControlComponent;

import kieker.analysis.plugin.annotation.InputPort;
import kieker.analysis.plugin.filter.AbstractFilterPlugin;
import kieker.common.configuration.Configuration;

/**
 * Passes current time events in nanos, received via its {@link #INPUT_PORT_NAME_TIMER_EVENTS_NANOS}, to the
 * configured {@link AbstractControlComponent} via the {@link AbstractControlComponent#setCurrentTimeNanos(long)}.
 * 
 * @author Andre van Hoorn
 * 
 */
public class CurrentTimeSetter extends AbstractFilterPlugin {
	public static final String INPUT_PORT_NAME_TIMER_EVENTS_NANOS = "timerEventNanos";

	private final AbstractControlComponent controlComponent;

	public CurrentTimeSetter(final Configuration configuration, final AbstractControlComponent controlComponent) {
		super(configuration);
		this.controlComponent = controlComponent;
	}

	@InputPort(name = INPUT_PORT_NAME_TIMER_EVENTS_NANOS, eventTypes = { Long.class })
	public void inputTimerEventNanos(final long currentTimeNanos) {
		this.controlComponent.setCurrentTimeNanos(currentTimeNanos);
	}

	@Override
	public Configuration getCurrentConfiguration() {
		return new Configuration();
	}

	@Override
	protected Configuration getDefaultConfiguration() {
		return new Configuration();
	}
}
