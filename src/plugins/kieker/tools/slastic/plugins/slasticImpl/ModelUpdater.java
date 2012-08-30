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

	// private static final Log LOG = LogFactory.getLog(ModelUpdater.class);

	// TODO: turn into property
	private static final long TRACE_DETECTION_TIMEOUT_MILLIS = 1000;

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
		return true;
	}

	@Override
	public boolean execute() {
		/*
		 * Constructs a {@link TraceReconstructor} which registers itself as a
		 * subscriber to the {@link EPServiceProvider}.
		 */
		this.traceReceiver = new TraceReconstructor(this.getParentControlComponent().getEPServiceProvider(), TRACE_DETECTION_TIMEOUT_MILLIS);

		/*
		 * Constructs a {@link UsageAndAssemblyModelUpdater} which registers
		 * itself as a subscriber to the {@link EPServiceProvider}.
		 */
		this.usageAndAssemblyModelUpdater =
				new UsageAndAssemblyModelUpdater(this.getParentControlComponent().getEPServiceProvider(), (ModelManager) this.getModelManager());

		return true;
	}

	@Override
	public void terminate(final boolean error) {
		// No need to do something
	}
}
