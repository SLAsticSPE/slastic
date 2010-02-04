package org.trustsoft.slastic.simulation.user.controller;

import java.util.List;

import org.trustsoft.slastic.simulation.config.Constants;

import de.uka.ipd.sdq.pcm.usagemodel.AbstractUserAction;
import de.uka.ipd.sdq.pcm.usagemodel.ScenarioBehaviour;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.pcm.usagemodel.Workload;
import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;

public class Generator extends ExternalEvent {

	public Generator(final UsageScenario usageScenario, final Model m,
			final String name) {
		super(m, usageScenario.getEntityName() + " Generator", Constants.DEBUG);
	}

	public void genUseCase(final UsageModel usageModel, final Model m,
			final String string) {
		final List<UsageScenario> scenarios = usageModel
				.getUsageScenario_UsageModel();
		for (final UsageScenario scenario : scenarios) {
			final Workload workload = scenario.getWorkload_UsageScenario();
			final ScenarioBehaviour behaviour = scenario
					.getScenarioBehaviour_UsageScenario();
			final List<AbstractUserAction> actions = behaviour
					.getActions_ScenarioBehaviour();
			for (final AbstractUserAction abstractUserAction : actions) {

			}
		}
	}

	public void start() {

	}

	@Override
	public void eventRoutine() {
	}

}
