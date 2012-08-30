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

package kieker.tools.slastic.simulation;


import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import kieker.tools.slastic.simulation.model.hardware.controller.cpu.UtilizationProbeEventGenerator;
import kieker.tools.slastic.simulation.model.mapping.AllocationController;
import kieker.tools.slastic.simulation.software.controller.controlflow.ExternalCallEnterEvent;
import kieker.tools.slastic.simulation.software.controller.controlflow.ExternalCallReturnEvent;
import kieker.tools.slastic.simulation.software.statistics.DummyStats;
import kieker.tools.slastic.simulation.software.statistics.ISystemStats;
import kieker.tools.slastic.simulation.software.statistics.SystemStats;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class SimulationModule extends AbstractModule {

	@Override
	protected void configure() {
		this.bind(ISystemStats.class).annotatedWith(Names.named("ComponentUsers")).to(DummyStats.class);

		this.bind(ISystemStats.class).annotatedWith(Names.named("Execution")).to(SystemStats.class);

		this.bind(ISystemStats.class).annotatedWith(Names.named("SystemUsersOnCall")).to(SystemStats.class);

		this.bind(ISystemStats.class).annotatedWith(Names.named("SystemUsersOnReturn")).to(SystemStats.class);

		this.bind(ISystemStats.class).annotatedWith(Names.named("CPUUsage")).to(SystemStats.class);

		this.requestStaticInjection(ExternalCallEnterEvent.class,
				ExternalCallReturnEvent.class, AllocationController.class,
				UtilizationProbeEventGenerator.class);

		this.bind(ISystemStats.class).to(SystemStats.class);
	}

}
