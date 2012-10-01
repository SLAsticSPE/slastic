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

package kieker.tools.slastic.plugins.slasticImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.tools.slastic.common.event.IObservationEvent;
import kieker.tools.slastic.control.components.events.IEvent;
import kieker.tools.slastic.control.components.modelUpdater.AbstractModelUpdaterComponent;
import kieker.tools.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction.TraceReconstructor;
import kieker.tools.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction.UsageAndAssemblyModelUpdater;

/**
 * 
 * @author Andre van Hoorn
 */
public class ModelUpdater extends AbstractModelUpdaterComponent {

	private static final Log LOG = LogFactory.getLog(ModelUpdater.class);

	private static final String PROP_NAME_TRACE_USAGE_RECONSTR_ENABLED = "traceUsageReconstructionEnabled";
	private static final String PROP_NAME_TRACE_DETECTION_TIMEOUT_MILLIS = "traceDetectionTimeoutMillis";

	private volatile boolean traceUsageReconstructionEnabled = true;
	private volatile long traceDetectionTimeOutMillis = 1000;

	/**
	 * Will be initialized in {@link #execute()}.
	 */
	@SuppressWarnings("unused")
	private volatile TraceReconstructor traceReceiver;

	/**
	 * Will be initialized in {@link #execute()}.
	 */
	@SuppressWarnings("unused")
	private volatile UsageAndAssemblyModelUpdater usageAndAssemblyModelUpdater;

	@Override
	public void handleEvent(final IEvent ev) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void newObservation(final IObservationEvent ime) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean init() {
		this.traceUsageReconstructionEnabled =
				Boolean.parseBoolean(super.getInitProperty(PROP_NAME_TRACE_USAGE_RECONSTR_ENABLED, Boolean.toString(this.traceUsageReconstructionEnabled)));
		if (this.traceUsageReconstructionEnabled) {
			this.traceDetectionTimeOutMillis =
					Long.parseLong(super.getInitProperty(PROP_NAME_TRACE_DETECTION_TIMEOUT_MILLIS, Long.toString(this.traceDetectionTimeOutMillis)));
		}
		return true;
	}

	@Override
	public boolean execute() {
		/*
		 * Constructs a {@link TraceReconstructor} which registers itself as a
		 * subscriber to the {@link EPServiceProvider}.
		 */
		if (this.traceUsageReconstructionEnabled) {
			LOG.info("Trace and usage model reconstruction enabled with timeout (millis): " + this.traceDetectionTimeOutMillis);

			this.traceReceiver = new TraceReconstructor(this.getParentControlComponent().getEPServiceProvider(), this.traceDetectionTimeOutMillis);

			/*
			 * Constructs a {@link UsageAndAssemblyModelUpdater} which registers
			 * itself as a subscriber to the {@link EPServiceProvider}.
			 */
			this.usageAndAssemblyModelUpdater =
					new UsageAndAssemblyModelUpdater(this.getParentControlComponent().getEPServiceProvider(), (ModelManager) this.getModelManager());
		} else {
			LOG.info("Trace and usage model reconstruction disabled");
		}
		return true;
	}

	@Override
	public void terminate(final boolean error) {
		if (this.traceReceiver != null) {
			LOG.info(this.traceReceiver.getClass().getSimpleName() + " reconstructed " + this.traceReceiver.getNumTracesReconstructed() + " traces");
		}
	}
}
