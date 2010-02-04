package org.trustsoft.slastic.simulation.model.hardware.controller.engine;

import java.util.HashSet;
import java.util.Set;

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

	public Server(final Model owner, final String name,
			final boolean showInTrace) {
		super(owner, name, showInTrace);
		hdds = new HashSet<HardDrive>();
		cpus = new HashSet<CPU>();
		id = name;
	}

	public void addCPU(final CPU cpu) {
		cpus.add(cpu);
	}

	public void addHDD(final HardDrive hdd) {
		hdds.add(hdd);
	}

	public boolean isAllocated() {
		return allocated;
	}

	public void setAllocated(final boolean allocated) {
		this.allocated = allocated;
	}

	public Set<CPU> getCpus() {
		return cpus;
	}

	public Set<HardDrive> getHdds() {
		return hdds;
	}

	public String getId() {
		return id;
	}

	public void addCPUTask(final CPUSchedulableProcess process) {
		// Maybe do smp balancing later here
		cpus.iterator().next().schedule(process);
	}

}
