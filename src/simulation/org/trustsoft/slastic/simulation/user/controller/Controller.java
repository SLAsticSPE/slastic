package org.trustsoft.slastic.simulation.user.controller;

import java.util.Hashtable;

import org.trustsoft.slastic.simulation.user.model.Scenario;

import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import desmoj.core.simulator.Model;

public class Controller {
	private static Controller instance = new Controller();

	private final Hashtable<String, Scenario> activeUsers = new Hashtable<String, Scenario>();

	public static final Controller getInstance() {
		return instance;
	}

	public void addUsage(final UsageModel um, final Model m) {

	}

	public void start() {

	}

	public void finish(final String string) {

	}
}
