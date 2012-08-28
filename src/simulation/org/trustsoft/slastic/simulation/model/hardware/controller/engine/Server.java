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

package org.trustsoft.slastic.simulation.model.hardware.controller.engine;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.model.hardware.controller.cpu.CPU;
import org.trustsoft.slastic.simulation.model.hardware.controller.cpu.CPUSchedulableProcess;
import org.trustsoft.slastic.simulation.model.hardware.controller.hd.HardDrive;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class Server extends Entity {
	private static final Log LOG = LogFactory.getLog(Server.class);

	private final Set<CPU> cpus;
	private final Set<HardDrive> hdds;
	private boolean allocated = false;
	private final String id;

	public Server(final Model owner, final String name, final boolean showInTrace) {
		super(owner, name, showInTrace);
		this.hdds = new HashSet<HardDrive>();
		this.cpus = new HashSet<CPU>();
		this.id = name;
	}

	public void addCPU(final CPU cpu) {
		LOG.info("Added CPU " + cpu);
		this.cpus.add(cpu);
	}

	public void addHDD(final HardDrive hdd) {
		this.hdds.add(hdd);
	}

	public boolean isAllocated() {
		return this.allocated;
	}

	public void setAllocated(final boolean allocated) {
		this.allocated = allocated;
	}

	public Set<CPU> getCpus() {
		return this.cpus;
	}

	public Set<HardDrive> getHdds() {
		return this.hdds;
	}

	public String getId() {
		return this.id;
	}

	public void addCPUTask(final CPUSchedulableProcess process) {
		// Maybe do smp balancing later here
		LOG.info("Added CPU Task");
		this.cpus.iterator().next().schedule(process);
	}

}
