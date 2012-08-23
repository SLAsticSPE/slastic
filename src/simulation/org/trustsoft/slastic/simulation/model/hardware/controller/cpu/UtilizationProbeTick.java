package org.trustsoft.slastic.simulation.model.hardware.controller.cpu;

import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class UtilizationProbeTick extends ExternalEvent {

	private final UtilizationProbeEventGenerator utilizationProbeEventGenerator;

	public UtilizationProbeTick(final Model owner, final String name, final boolean showInTrace, final UtilizationProbeEventGenerator utilizationProbeEventGenerator) {
		super(owner, name, showInTrace);
		this.utilizationProbeEventGenerator = utilizationProbeEventGenerator;
	}

	@Override
	public void eventRoutine() {
		this.utilizationProbeEventGenerator.tick();
	}

}
