package org.trustsoft.slastic.simulation.model.hardware.controller.hd;

import org.trustsoft.slastic.simulation.model.hardware.controller.engine.AbstractScheduler;
import org.trustsoft.slastic.simulation.model.hardware.controller.engine.ProcessingResource;

import desmoj.core.advancedModellingFeatures.Res;
import desmoj.core.simulator.Model;

public class HardDrive extends ProcessingResource<IOOperation> {

	@SuppressWarnings("unused")
	private Res res;

	public HardDrive(final Model owner, final String name,
			final boolean showInTrace,
			final AbstractScheduler<HardDrive, IOOperation> scheduler,
			final int capacity) {
		super(owner, name, showInTrace, scheduler, capacity);
		scheduler.setOwner(this);
	}

	@Override
	public double getBusiness() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
