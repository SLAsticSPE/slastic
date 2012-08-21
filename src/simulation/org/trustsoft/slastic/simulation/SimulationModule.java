package org.trustsoft.slastic.simulation;

import org.trustsoft.slastic.simulation.model.hardware.controller.cpu.UtilizationProbeEventGenerator;
import org.trustsoft.slastic.simulation.model.mapping.AllocationController;
import org.trustsoft.slastic.simulation.software.controller.controlflow.ExternalCallEnterNode;
import org.trustsoft.slastic.simulation.software.controller.controlflow.ExternalCallReturnNode;
import org.trustsoft.slastic.simulation.software.statistics.DummyStats;
import org.trustsoft.slastic.simulation.software.statistics.ISystemStats;
import org.trustsoft.slastic.simulation.software.statistics.SystemStats;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class SimulationModule extends AbstractModule {

	@Override
	protected void configure() {
		this.bind(ISystemStats.class).annotatedWith(Names.named("ComponentUsers")).to(DummyStats.class);

		this.bind(ISystemStats.class).annotatedWith(Names.named("Execution")).to(SystemStats.class);

		this.bind(ISystemStats.class).annotatedWith(Names.named("SystemUsersOnCall")).to(SystemStats.class);

		this.bind(ISystemStats.class).annotatedWith(Names.named("SystemUsersOnReturn")).to(SystemStats.class);

		this.bind(ISystemStats.class).annotatedWith(Names.named("CPUUsage")).to(SystemStats.class);

		this.requestStaticInjection(ExternalCallEnterNode.class,
				ExternalCallReturnNode.class, AllocationController.class,
				UtilizationProbeEventGenerator.class);

		this.bind(ISystemStats.class).to(SystemStats.class);
	}

}
