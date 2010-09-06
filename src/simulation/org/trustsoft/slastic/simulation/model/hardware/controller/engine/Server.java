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

public class Server extends Entity {

	private final Set<CPU> cpus;
	private final Set<HardDrive> hdds;
	private boolean allocated = false;
	private final String id;
	private final Log log;

	public Server(final Model owner, final String name,
			final boolean showInTrace) {
		super(owner, name, showInTrace);
		this.hdds = new HashSet<HardDrive>();
		this.cpus = new HashSet<CPU>();
		this.id = name;
                // avh: removed 'name' from logger name, because otherwise,
                //      this logger cannot be configured based on the packagename
		this.log = LogFactory.getLog(Server.class);
	}

	public void addCPU(final CPU cpu) {
		this.log.info("Added CPU " + cpu);
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
		this.log.info("Added CPU Task");
		this.cpus.iterator().next().schedule(process);
	}

}
