package org.trustsoft.slastic.simulation.model.hardware.controller;

import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.model.hardware.controller.cpu.CPU;
import org.trustsoft.slastic.simulation.model.hardware.controller.cpu.CPURRScheduler;
import org.trustsoft.slastic.simulation.model.hardware.controller.engine.Server;
import org.trustsoft.slastic.simulation.model.hardware.controller.hd.HDScheduler;
import org.trustsoft.slastic.simulation.model.hardware.controller.hd.HardDrive;
import org.trustsoft.slastic.simulation.stoexeval.EvaluationProxy;

import de.uka.ipd.sdq.pcm.resourceenvironment.ProcessingResourceSpecification;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.resourcetype.ProcessingResourceType;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Reportable;

public class HardwareController extends Reportable {

	// FIXME desmoj api!!!

	private final Model model;
	private final Hashtable<String, Server> serversById = new Hashtable<String, Server>();
	private int allocatedServers = 0;

	public HardwareController(final ResourceEnvironment ressources,
			final Model model) {
		super(model, "Resource Controler", Constants.DEBUG, Constants.DEBUG);
		this.model = model;
		generateEnvironment(ressources, model);
	}

	private void generateEnvironment(final ResourceEnvironment re, final Model m) {
		for (final ResourceContainer rc : re
				.getResourceContainer_ResourceEnvironment()) {
			final Server server = createServer(rc.getId(), m);
			final List<ProcessingResourceSpecification> prslist = rc
					.getActiveResourceSpecifications_ResourceContainer();
			for (final ProcessingResourceSpecification prs : prslist) {
				final ProcessingResourceType prt = prs
						.getActiveResourceType_ActiveResourceSpecification();
				if (prt.getEntityName().equals("CPU")) {
					genCPU(m, rc, server, prs);
				} else if (prt.getEntityName().equals("HDD")) {
					genHDD(m, rc, server, prs);
				}

			}
			serversById.put(rc.getId().toString(), server);
		}
	}

	private void genHDD(final Model m, final ResourceContainer rc,
			final Server server, final ProcessingResourceSpecification prs) {
		final int prate = (Integer) EvaluationProxy.evaluate(prs
				.getProcessingRate_ProcessingResourceSpecification()
				.getSpecification(), Integer.class, null);
		final HardDrive hd = new HardDrive(m, rc.getEntityName() + "HDD",
				Constants.DEBUG, new HDScheduler(m, rc.getEntityName()
						+ "HDDScheduler"), prate);
		server.addHDD(hd);
	}

	private void genCPU(final Model m, final ResourceContainer rc,
			final Server server, final ProcessingResourceSpecification prs) {
		final int prate = (Integer) EvaluationProxy.evaluate(prs
				.getProcessingRate_ProcessingResourceSpecification()
				.getSpecification(), Integer.class, null);
		final CPU cpu = new CPU(m, rc.getEntityName() + "CPU", Constants.DEBUG,
				new CPURRScheduler(m, rc.getEntityName() + "CPUScheduler"),
				prate);
		server.addCPU(cpu);
	}

	private Server createServer(final String name, final Model m) {
		final Server s = new Server(m, name, Constants.DEBUG);
		return s;
	}

	public boolean isUsed(final String id) {
		return ModelManager.getInstance().getAllocCont().serverIsUsed(id);
	}

	public boolean isAllocated(final String id) {
		return serversById.get(id).isAllocated();
	}

	public boolean allocate(final String id) {
		if (!serversById.get(id).isAllocated()) {
			serversById.get(id).setAllocated(true);
			allocatedServers++;
			return true;
		}
		return false;
	}

	public boolean delocate(final String id) {
		if (allocatedServers > 1
				&& !ModelManager.getInstance().getAllocCont().serverIsUsed(id)) {
			serversById.get(id).setAllocated(false);
			allocatedServers--;
			return true;
		}
		return false;
	}

	public Collection<Server> getServers() {
		return serversById.values();
	}

	public Server getServer(final String server) {
		return serversById.get(server);
	}

}
