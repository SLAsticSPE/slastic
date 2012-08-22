package org.trustsoft.slastic.simulation.software.controller.controlflow;

import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.model.ModelManager;

import desmoj.core.simulator.ExternalEvent;

public abstract class AbstractControlFlowEvent extends ExternalEvent {

	private final String traceId;

	public AbstractControlFlowEvent(final String name, final String traceId) {
		super(ModelManager.getInstance().getModel(), name, Constants.DEBUG);
		this.traceId = traceId;
	}

	/**
	 * @return the traceId
	 */
	public final String getTraceId() {
		return this.traceId;
	}

	@Override
	public abstract void eventRoutine();

}
