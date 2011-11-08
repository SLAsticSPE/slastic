package org.trustsoft.slastic.plugins.slasticImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.event.IObservationEvent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.control.components.modelUpdater.AbstractModelUpdaterComponent;
import org.trustsoft.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction.TraceReconstructor;
import org.trustsoft.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction.UsageAndAssemblyModelUpdater;

/**
 * 
 * @author Andre van Hoorn
 */
public class ModelUpdater extends AbstractModelUpdaterComponent {

	private static final Log log = LogFactory.getLog(ModelUpdater.class);

	// TODO: turn into property
	private static final long TRACE_DETECTION_TIMEOUT_MILLIS = 2000;

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
		this.traceReceiver = new TraceReconstructor(this.getParentControlComponent().getEPServiceProvider(),
					ModelUpdater.TRACE_DETECTION_TIMEOUT_MILLIS);

		/*
		 * Constructs a {@link UsageAndAssemblyModelUpdater} which registers
		 * itself as a subscriber to the {@link EPServiceProvider}.
		 */
		this.usageAndAssemblyModelUpdater =
				new UsageAndAssemblyModelUpdater(this.getParentControlComponent().getEPServiceProvider(),
						(ModelManager) this.getModelManager());

		return true;
	}

	@Override
	public void terminate(final boolean error) {
		// No need to do something
	}
}
