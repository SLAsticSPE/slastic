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

package kieker.tools.slastic.plugins.slasticImpl.monitoring.kieker.filters;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.analysis.plugin.annotation.InputPort;
import kieker.analysis.plugin.filter.AbstractFilterPlugin;
import kieker.common.configuration.Configuration;
import kieker.common.record.IMonitoringRecord;
import kieker.monitoring.core.IMonitoringRecordReceiver;
import kieker.tools.slastic.control.AbstractControlComponent;
import kieker.tools.slastic.metamodel.core.IEvent;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class MonitoringRecordConsumerFilterChain extends AbstractFilterPlugin {

	private static final Log LOG = LogFactory.getLog(MonitoringRecordConsumerFilterChain.class);

	private volatile AbstractControlComponent controlComponent;

	public void setControlComponent(final AbstractControlComponent controlComponent) {
		this.controlComponent = controlComponent;
	}

	private final List<ISynchronousTransformationFilter> synchronousFilters = new ArrayList<ISynchronousTransformationFilter>();
	private final List<IMonitoringRecordReceiver> synchronousReceivers = new ArrayList<IMonitoringRecordReceiver>();

	// TODO: add additional filter types, e.g., asynchronous

	public MonitoringRecordConsumerFilterChain(final Configuration configuration) {
		super(configuration);
	}

	/**
	 * 
	 * @param filter
	 */
	public void addSynchronousFilter(final ISynchronousTransformationFilter filter) {
		this.synchronousFilters.add(filter);
	}

	/**
	 * 
	 * @param monitoringRecordReceiver
	 */
	public void addSynchronousReceiver(final IMonitoringRecordReceiver monitoringRecordReceiver) {
		this.synchronousReceivers.add(monitoringRecordReceiver);
	}

	@Override
	public void terminate(final boolean error) {
		LOG.info("Summary: "
				+ this.incomingRecordCount.get() + " records received; "
				+ this.outgoingRecordCount.get() + " records delivered");
	}

	private final AtomicInteger incomingRecordCount = new AtomicInteger(0);
	private final AtomicInteger outgoingRecordCount = new AtomicInteger(0);

	public static final String INPUT_PORT_NAME_RECORDS = "records";

	@InputPort(name = INPUT_PORT_NAME_RECORDS, eventTypes = IMonitoringRecord.class)
	public boolean newMonitoringRecord(final IMonitoringRecord record) {
		final boolean success = true;

		this.incomingRecordCount.incrementAndGet();
		/* Transform record */

		/* Pass records to synchronous filters */
		for (final ISynchronousTransformationFilter filter : this.synchronousFilters) {
			/* Send transformation result to event processing service */
			final IEvent event = filter.transform(record);
			if (event != null) {
				MonitoringRecordConsumerFilterChain.this.controlComponent.getEPServiceProvider().getEPRuntime().sendEvent(event);
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

	@Override
	public Configuration getCurrentConfiguration() {
		return new Configuration();
	}
};
