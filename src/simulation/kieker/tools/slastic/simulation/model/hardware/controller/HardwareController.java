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

package kieker.tools.slastic.simulation.model.hardware.controller;

import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.InternalEObject;

import kieker.tools.slastic.simulation.config.Constants;
import kieker.tools.slastic.simulation.model.ModelManager;
import kieker.tools.slastic.simulation.model.hardware.controller.cpu.CPU;
import kieker.tools.slastic.simulation.model.hardware.controller.cpu.CPURRScheduler;
import kieker.tools.slastic.simulation.model.hardware.controller.engine.Server;
import kieker.tools.slastic.simulation.model.hardware.controller.hd.HDScheduler;
import kieker.tools.slastic.simulation.model.hardware.controller.hd.HardDrive;
import kieker.tools.slastic.simulation.stoexeval.EvaluationProxy;

import de.uka.ipd.sdq.pcm.resourceenvironment.ProcessingResourceSpecification;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.resourcetype.ProcessingResourceType;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Reportable;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class HardwareController extends Reportable {

	// FIXME desmoj api!!!

	private static final Log LOG = LogFactory.getLog(HardwareController.class);

	private final Model model;
	private final Hashtable<String, Server> serversById = new Hashtable<String, Server>();
	private int numAllocatedServers = 0;

	public HardwareController(final ResourceEnvironment resources, final Model model) {
		super(model, "Resource Controller", Constants.DEBUG, Constants.DEBUG);
		this.model = model;
		this.generateEnvironment(resources, model);
	}

	private void generateEnvironment(final ResourceEnvironment re, final Model m) {

		for (final ResourceContainer rc : re.getResourceContainer_ResourceEnvironment()) {
			final Server server = this.createServer(rc.getId(), m);
			final List<ProcessingResourceSpecification> prslist = rc.getActiveResourceSpecifications_ResourceContainer();
			for (final ProcessingResourceSpecification prs : prslist) {
				LOG.info("Adding Processing Resource "
						+ ((InternalEObject) prs.getActiveResourceType_ActiveResourceSpecification()).eProxyURI() + " for " + rc.getId());
				final ProcessingResourceType prt = prs.getActiveResourceType_ActiveResourceSpecification();
				if (((InternalEObject) prs.getActiveResourceType_ActiveResourceSpecification()).eProxyURI().toString()
						.equals("pathmap://PCM_MODELS/Palladio.resourcetype#_oro4gG3fEdy4YaaT-RYrLQ")) {
					this.genCPU(m, rc, server, prs);
				} else if (prt.getEntityName().equals("HDD")) {
					this.genHDD(m, rc, server, prs);
				}
			}
			this.serversById.put(rc.getId().toString(), server);
		}
	}

	private void genHDD(final Model m, final ResourceContainer rc, final Server server, final ProcessingResourceSpecification prs) {
		final int prate = (Integer) EvaluationProxy.evaluate(prs.getProcessingRate_ProcessingResourceSpecification().getSpecification(), Integer.class, null);
		final HardDrive hd = new HardDrive(m, rc.getEntityName() + "HDD", Constants.DEBUG, new HDScheduler(m, rc.getEntityName() + "HDDScheduler"), prate);
		server.addHDD(hd);
	}

	private void genCPU(final Model m, final ResourceContainer rc, final Server server, final ProcessingResourceSpecification prs) {
		final int prate = Integer.parseInt(prs.getProcessingRate_ProcessingResourceSpecification().getSpecification().replaceAll("\\s*", ""));
		final CPU cpu = new CPU(m, rc.getEntityName() + "CPU", Constants.DEBUG, new CPURRScheduler(m, rc.getEntityName() + "CPU"), prate);
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
		final Server server = this.serversById.get(id);
		if (!server.isAllocated()) {
			server.setAllocated(true);
			for (final CPU cpu : server.getCpus()) {
				cpu.resumeMonitoringAt(this.model.currentTime());
			}
			this.numAllocatedServers++;
			return true;
		}
		LOG.warn("Failed to allocate server" + id + " , allocation status: " + (server.isAllocated() ? "allocated" : "unallocated"));
		return false;
	}

	// TODO: What's the semantics of this method?
	public boolean bpallocate(final String id) {
		final Server server = this.serversById.get(id);
		if (!server.isAllocated()) {
			server.setAllocated(true);
			this.numAllocatedServers++;
			return true;
		}
		return false;
	}

	public boolean delocate(final String id) {
		final Server server = this.serversById.get(id);
		// this.log.warn("Delocating server " + server.getName());
		if ((this.numAllocatedServers > 1) && !ModelManager.getInstance().getAllocationController().serverIsUsed(id)) {
			server.setAllocated(false);
			for (final CPU cpu : server.getCpus()) {
				cpu.pauseMonitoring();
			}
			this.numAllocatedServers--;
			return true;
		}
		LOG.warn("Delocation failed, Servers left: " + this.numAllocatedServers + ", Usage status: "
				+ (ModelManager.getInstance().getAllocationController().serverIsUsed(id) ? "used" : "unused") + ", Allocation status: "
				+ server.isAllocated());
		return false;
	}

	public Collection<Server> getServers() {
		return this.serversById.values();
	}

	public Server getServer(final String server) {
		return this.serversById.get(server);
	}

}
