package org.trustsoft.slastic.simulation.model.hardware.controller;

import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.InternalEObject;
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
	private final Log log = LogFactory.getLog(this.getClass());

	public HardwareController(final ResourceEnvironment resources,
			final Model model) {
		super(model, "Resource Controller", Constants.DEBUG, Constants.DEBUG);
		this.model = model;
		this.generateEnvironment(resources, model);
	}

	private void generateEnvironment(final ResourceEnvironment re, final Model m) {

		for (final ResourceContainer rc : re
				.getResourceContainer_ResourceEnvironment()) {
			final Server server = this.createServer(rc.getId(), m);
			final List<ProcessingResourceSpecification> prslist = rc
					.getActiveResourceSpecifications_ResourceContainer();
			for (final ProcessingResourceSpecification prs : prslist) {
				this.log.info("Adding Processing Resource "
						+ ((InternalEObject) prs
								.getActiveResourceType_ActiveResourceSpecification())
								.eProxyURI() + " for " + rc.getId());
				final ProcessingResourceType prt = prs
						.getActiveResourceType_ActiveResourceSpecification();
				if (((InternalEObject) prs
						.getActiveResourceType_ActiveResourceSpecification())
						.eProxyURI()
						.toString()
						.equals("pathmap://PCM_MODELS/Palladio.resourcetype#_oro4gG3fEdy4YaaT-RYrLQ")) {
					this.genCPU(m, rc, server, prs);

				} else if (prt.getEntityName().equals("HDD")) {
					this.genHDD(m, rc, server, prs);
				}

			}
			this.serversById.put(rc.getId().toString(), server);
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
		final int prate = Integer.parseInt(prs
				.getProcessingRate_ProcessingResourceSpecification()
				.getSpecification().replaceAll("\\s*", ""));
		final CPU cpu = new CPU(m, rc.getEntityName() + "CPU", Constants.DEBUG,
				new CPURRScheduler(m, rc.getEntityName() + "CPUScheduler"),
				prate);
		cpu.getScheduler().setTickRate(Constants.PS_SLICE);
		server.addCPU(cpu);
	}

	private Server createServer(final String name, final Model m) {
		final Server s = new Server(m, name, Constants.DEBUG);
		return s;
	}

	public boolean isAllocated(final String id) {
		return this.serversById.get(id).isAllocated();
	}

	public boolean allocate(final String id) {
		if (!this.serversById.get(id).isAllocated()) {
			this.serversById.get(id).setAllocated(true);
			this.allocatedServers++;
			return true;
		}
		this.log.warn("Failed to allocate server"
				+ id
				+ " , allocation status: "
				+ (this.serversById.get(id).isAllocated() ? "allocated"
						: "unallocated"));
		return false;
	}

	public boolean delocate(final String id) {
		if (this.allocatedServers > 1
				&& !ModelManager.getInstance().getAllocCont().serverIsUsed(id)) {
			this.serversById.get(id).setAllocated(false);
			this.allocatedServers--;
			return true;
		}
		this.log.warn("Delocation failed, Servers left: "
				+ this.allocatedServers
				+ ", Usage status: "
				+ (ModelManager.getInstance().getAllocCont().serverIsUsed(id) ? "used"
						: "unused") + ", Allocation status: "
				+ this.serversById.get(id).isAllocated());
		return false;
	}

	public Collection<Server> getServers() {
		return this.serversById.values();
	}

	public Server getServer(final String server) {
		return this.serversById.get(server);
	}

}
