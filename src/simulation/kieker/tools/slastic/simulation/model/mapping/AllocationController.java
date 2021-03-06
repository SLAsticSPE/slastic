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

package kieker.tools.slastic.simulation.model.mapping;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import kieker.tools.slastic.simulation.model.ModelManager;
import kieker.tools.slastic.simulation.model.hardware.controller.engine.Server;
import kieker.tools.slastic.simulation.model.mapping.loadbalancer.RandomBalancer;
import kieker.tools.slastic.simulation.model.reconfiguration.ReconfigurationController;
import kieker.tools.slastic.simulation.model.software.repository.ComponentTypeController;
import kieker.tools.slastic.simulation.software.controller.controlflow.AbstractControlFlowEvent;
import kieker.tools.slastic.simulation.software.statistics.ISystemStats;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.repository.BasicComponent;
import de.uka.ipd.sdq.pcm.repository.PassiveResource;
import de.uka.ipd.sdq.pcm.repository.ProvidesComponentType;
import desmoj.core.simulator.Model;

/**
 * @author Robert von Massow
 * 
 */
public final class AllocationController {
	private static final Log LOG = LogFactory.getLog(AllocationController.class);

	@SuppressWarnings("unused")
	private final Model model;
	private final Hashtable<String, Collection<String>> assemblyContextToServerMappingById = new Hashtable<String, Collection<String>>();
	private final Hashtable<String, Collection<String>> serverToAssemblyContextsMappingById = new Hashtable<String, Collection<String>>();
	private final Hashtable<String, Hashtable<String, Boolean>> serverToAsmToBlockState = new Hashtable<String, Hashtable<String, Boolean>>();
	private final Hashtable<String, Hashtable<String, Integer>> serverToAsmToUserCount = new Hashtable<String, Hashtable<String, Integer>>();

	private final Hashtable<String, Hashtable<String, Hashtable<String, PassiveSimResource>>> serverToAsmToPassiveResource =
			new Hashtable<String, Hashtable<String, Hashtable<String, PassiveSimResource>>>();

	@Inject
	@Named("ComponentUsers")
	private static ISystemStats stats;

	private final ILoadBalancer loadBalancer;

	public AllocationController(final Allocation allocation, final Model model) {
		this.genAllocationModel(allocation);
		this.loadBalancer = new RandomBalancer();
		this.model = model;
	}

	public AllocationController(final Allocation allocation, final ILoadBalancer lb, final Model model) {
		this.genAllocationModel(allocation);
		this.loadBalancer = lb;
		this.model = model;

	}

	/**
	 * Initialize SimulationModel allocation
	 * 
	 * @param allocation
	 */
	private void genAllocationModel(final Allocation allocation) {
		final List<AllocationContext> contexts = allocation.getAllocationContexts_Allocation();
		final Collection<Server> servers = ModelManager.getInstance().getHardwareController().getServers();
		for (final Server server : servers) {
			this.serverToAssemblyContextsMappingById.put(server.getId(), new HashSet<String>());
			this.serverToAsmToBlockState.put(server.getId(), new Hashtable<String, Boolean>());
		}
		final Collection<AssemblyContext> asmContexts = ModelManager.getInstance().getAssemblyController().getAssemblyContexts();
		for (final AssemblyContext assemblyContext : asmContexts) {
			this.assemblyContextToServerMappingById.put(assemblyContext.getId(), new HashSet<String>());
		}
		for (final AllocationContext allocContext : contexts) {
			LOG.info("Adding initial Allocation: " + allocContext.getAssemblyContext_AllocationContext()
					+ " to " + allocContext.getResourceContainer_AllocationContext());
			final String asmId = allocContext.getAssemblyContext_AllocationContext().getId();
			final String serverId = allocContext.getResourceContainer_AllocationContext().getId();
			this.assemblyContextToServerMappingById.get(asmId).add(serverId);
			this.serverToAssemblyContextsMappingById.get(serverId).add(asmId);
			ModelManager.getInstance().getHardwareController().bpallocate(serverId);
			this.initPassiveResource(serverId, asmId);
		}

	}

	/**
	 * Determines if a given server has components mapped onto it
	 * 
	 * @param serverId
	 * @return true if a server is used (i.e. asm contexts are mapped to it)
	 */
	public final boolean serverIsUsed(final String serverId) {
		for (final Collection<String> servers : this.assemblyContextToServerMappingById.values()) {
			if (servers.contains(serverId)) {
				return true;
			}
		}
		return false;
	}

	// TODO: Not used; why?
	public final String getServerLoadBalanced(final AssemblyContext asmContextId) {
		return this.loadBalancer.getServerMapping(asmContextId.getId(), this.assemblyContextToServerMappingById.get(asmContextId.getId()));
	}

	/**
	 * Returns a server ID
	 * 
	 * @param asmContextId
	 * @return
	 */
	public final String getServerLoadBalanced(final String asmContextId) {
		LOG.info("Getting Ressource Container for ASM Context: " + asmContextId);
		return this.loadBalancer.getServerMapping(asmContextId, this.assemblyContextToServerMappingById.get(asmContextId));
	}

	/**
	 * unblock a component on a given server node
	 * 
	 * @param asmContextId
	 * @param serverId
	 * @return
	 */
	public boolean unblockInstance(final String asmContextId, final String serverId) {
		Hashtable<String, Boolean> blockState = this.serverToAsmToBlockState.get(serverId);
		if (blockState == null) {
			blockState = new Hashtable<String, Boolean>();
			this.serverToAsmToBlockState.put(serverId, blockState);
		}
		blockState.put(asmContextId, Boolean.FALSE);
		return false;
	}

	/**
	 * Checks if a component on a server has any users left
	 * 
	 * @param asmContextId
	 * @param serverId
	 * @return true if component has users left
	 */
	public final boolean hasUsers(final String asmContextId, final String serverId) {
		final Hashtable<String, Integer> users = this.serverToAsmToUserCount.get(serverId);
		if (users != null) {
			final Integer cUser = users.get(asmContextId);
			if (cUser != null) {
				return cUser > 0;
			} else {
				return true;
			}
		}
		return false;
	}

	public final int addUser(final String asmContextId, final String serverId) {
		Hashtable<String, Integer> users = this.serverToAsmToUserCount.get(serverId);
		if (users != null) {
			Integer cUser = users.get(asmContextId);
			if (cUser == null) {
				cUser = 0;
			}
			users.put(asmContextId, cUser + 1);
			stats.logComponentUsers(asmContextId, serverId, cUser + 1);
			return cUser + 1;
		} else {
			users = new Hashtable<String, Integer>();
			this.serverToAsmToUserCount.put(serverId, users);
			users.put(asmContextId, 1);
			stats.logComponentUsers(asmContextId, serverId, 1);
			return 1;
		}
	}

	public final int remUser(final String asmContextId, final String serverId) {
		final Hashtable<String, Integer> users = this.serverToAsmToUserCount
				.get(serverId);
		if (users != null) {
			final Integer cUser = users.get(asmContextId);
			if (cUser != null) {
				final int nextCUserCount = cUser - 1;
				users.put(asmContextId, nextCUserCount);
				if (nextCUserCount == 0) {
					this.notifyReconfController(asmContextId, serverId);
				}
				stats.logComponentUsers(asmContextId, serverId, nextCUserCount);
				return nextCUserCount;
			}
		}
		/*
		 * maybe notify if component is not used and blocked as there's probably
		 * a reconf operation ready to execute
		 */
		return -1;
	}

	/**
	 * block a component on a given server node
	 * 
	 * @param asmContextId
	 * @param serverId
	 * @return true on success
	 */
	public final boolean blockInstance(final String asmContextId, final String serverId) {
		Hashtable<String, Boolean> blockState = this.serverToAsmToBlockState.get(serverId);
		LOG.info("blocking " + asmContextId + " on server " + serverId);
		if (blockState == null) {
			blockState = new Hashtable<String, Boolean>();
			this.serverToAsmToBlockState.put(serverId, blockState);
		}
		blockState.put(asmContextId, Boolean.TRUE);
		return false;
	}

	/**
	 * TODO emit event for reconf that component is empty
	 * 
	 * @param asmContextId
	 * @param serverId
	 */
	private final void notifyReconfController(final String asmContextId, final String serverId) {
		try {
			if (this.serverToAsmToBlockState.get(serverId).get(asmContextId)) {
				ReconfigurationController.getInstrance().markUnusedAndBlocked(serverId, asmContextId);
				this.unblockInstance(asmContextId, serverId);
			}
		} catch (final Exception e) {
		}

	}

	public final boolean hasAllocation(final String serverId, final String asmContextId) {
		return this.assemblyContextToServerMappingById.get(asmContextId).contains(serverId);
	}

	/**
	 * Adds the asmContext to the server (used for replication)
	 * 
	 * @param serverId
	 * @param asmContextId
	 */
	public final void add(final String serverId, final String asmContextId) {
		this.assemblyContextToServerMappingById.get(asmContextId).add(serverId);
		this.initPassiveResource(serverId, asmContextId);
	}

	private void initPassiveResource(final String serverId, final String asmContextId) {
		Hashtable<String, Hashtable<String, PassiveSimResource>> asmCtxToPRes = this.serverToAsmToPassiveResource.get(serverId);
		if (asmCtxToPRes == null) {
			asmCtxToPRes = new Hashtable<String, Hashtable<String, PassiveSimResource>>();
			this.serverToAsmToPassiveResource.put(serverId, asmCtxToPRes);
		}

		Hashtable<String, PassiveSimResource> pres = asmCtxToPRes.get(asmContextId);
		if (pres == null) {
			pres = new Hashtable<String, PassiveSimResource>();
			asmCtxToPRes.put(asmContextId, pres);
		}

		final ProvidesComponentType component =
				ModelManager.getInstance().getAssemblyController().getAssemblyContextById(asmContextId).getEncapsulatedComponent_ChildComponentContext();
		if (component instanceof BasicComponent) {
			final BasicComponent bc = (BasicComponent) component;
			final Hashtable<String, PassiveResource> passiveRes = ComponentTypeController.getInstance().getPassiveResByComponent(bc);
			for (final String pResName : passiveRes.keySet()) {
				final int capacity = Integer.parseInt(passiveRes.get(pResName).getCapacity_PassiveResource().getSpecification().replaceAll("\\s", ""));
				final PassiveSimResource psr = new PassiveSimResource(pResName, capacity);
				pres.put(pResName, psr);
			}
		}
	}

	/**
	 * remove a components instance mapping. this will not remove users. the
	 * reconfcontroller will be notified if when all pending requests to the
	 * removed instance have been computed.
	 * 
	 * @param component
	 * @return
	 */
	// TODO: unify signature to have final String serverId, final String asmContextId here as well?
	public final boolean del(final AllocationContext component) {
		final String asmContext = component.getAssemblyContext_AllocationContext().getId();
		final Collection<String> servers = this.assemblyContextToServerMappingById.get(asmContext);
		if (servers.size() > 1) {
			final String server = component.getResourceContainer_AllocationContext().getId();
			servers.remove(server);
			this.blockInstance(asmContext, server);

			LOG.info("Deleted " + component.getAssemblyContext_AllocationContext().getId()
					+ " from container " + component.getResourceContainer_AllocationContext().getId());
			if (!this.hasUsers(asmContext, server)) {
				this.notifyReconfController(asmContext, server);
			}
			return true;
		} else {
			LOG.warn("Not allowing less than one allocation (tried to remove " + component.getAssemblyContext_AllocationContext()
					+ " from " + component.getResourceContainer_AllocationContext() + ")");
			return false;
		}
	}

	public final int acquirePassiveResource(final String serverId, final String asmContextId, final String passiveResourceName, final AbstractControlFlowEvent next) {
		return this.serverToAsmToPassiveResource.get(serverId).get(asmContextId).get(passiveResourceName).acquire(next);
	}

	public final int releasePassiveResource(final String serverId, final String asmContextId, final String passiveResourceName) {
		return this.serverToAsmToPassiveResource.get(serverId).get(asmContextId).get(passiveResourceName).release();
	}
}
