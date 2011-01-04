package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters;

import kieker.common.record.IMonitoringRecord;
import de.cau.se.slastic.metamodel.core.IEvent;

public interface ISynchronousTransformationFilter {
	/**
	 * Transform the given {@link IMonitoringRecord} to an {@link IEvent}.
	 * Implementing methods may return null if the consumed
	 * {@link IMonitoringRecord} did not result in a new {@link IEvent}.
	 * 
	 * @param record
	 * @return
	 */
	public IEvent transform(final IMonitoringRecord record);
}
