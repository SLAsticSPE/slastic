package org.trustsoft.slastic.simulation.user;

import org.trustsoft.slastic.simulation.config.Constants;

import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;

public class UserEvents extends ExternalEvent {

	public UserEvents(final Model owner, final String name) {
		super(owner, name, Constants.DEBUG);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void eventRoutine() {
		// TODO Auto-generated method stub

	}

}
