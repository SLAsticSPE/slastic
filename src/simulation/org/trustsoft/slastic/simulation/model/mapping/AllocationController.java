package org.trustsoft.slastic.simulation.model.mapping;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.model.hardware.controller.engine.Server;
import org.trustsoft.slastic.simulation.model.mapping.loadbalancer.RandomBalancer;
import org.trustsoft.slastic.simulation.model.reconfiguration.ReconfigurationController;
import org.trustsoft.slastic.simulation.software.statistics.ISystemStats;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import desmoj.core.simulator.Model;

/**
 * @author skomp
 * 
 */
public final class AllocationController {

	@SuppressWarnings("unused")
	private final Model model;
	private final Hashtable<String, Collection<String>> assemblyContextToServerMapping = new Hashtable<String, Collection<String>>();
	private final Hashtable<String, Collection<String>> serverToAllocationContextsMapping = new Hashtable<String, Collection<String>>();
	private final Hashtable<String, Hashtable<String, Boolean>> serverToAsmToBlockState = new Hashtable<String, Hashtable<String, Boolean>>();
	private final Hashtable<String, Hashtable<String, Integer>> serverToAsmToUserCount = new Hashtable<String, Hashtable<String, Integer>>();
	private final Log log = LogFactory.getLog(AllocationController.class);

	@Inject
	@Named("ComponentUsers")
	private static ISystemStats stats;

	private final LoadBalancer loadBalancer;

	public AllocationController(final Allocation allocation, final Model model) {
		this.genAllocationModel(allocation);
		this.loadBalancer = new RandomBalancer();
		this.model = model;
	}

	public AllocationController(final Allocation allocation,
			final LoadBalancer lb, final Model model) {
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
		final List<AllocationContext> contexts = allocation
				.getAllocationContexts_Allocation();
		final Collection<Server> servers = ModelManager.getInstance()
				.getHwCont().getServers();
		for (final Server server : servers) {
			this.serverToAllocationContextsMapping.put(server.getId(),
					new HashSet<String>());
			this.serverToAsmToBlockState.put(server.getId(),
					new Hashtable<String, Boolean>());
		}
		final Collection<AssemblyContext> asmContexts = ModelManager
				.getInstance().getAssemblyCont().getAllASMContexts();
		for (final AssemblyContext assemblyContext : asmContexts) {
			this.assemblyContextToServerMapping.put(assemblyContext.getId(),
					new HashSet<String>());
		}
		for (final AllocationContext allocContext : contexts) {
			this.log.info("Adding initial Allocation: "
					+ allocContext.getAssemblyContext_AllocationContext()
					+ " to "
					+ allocContext.getResourceContainer_AllocationContext());
			this.assemblyContextToServerMapping
					.get(allocContext.getAssemblyContext_AllocationContext()
							.getId()).add(
							allocContext
									.getResourceContainer_AllocationContext()
									.getId());
			this.serverToAllocationContextsMapping.get(
					allocContext.getResourceContainer_AllocationContext()
							.getId())
					.add(allocContext.getAssemblyContext_AllocationContext()
							.getId());
			ModelManager
					.getInstance()
					.getHwCont()
					.bpallocate(
							allocContext
									.getResourceContainer_AllocationContext()
									.getId());
		}

	}

	/**
	 * Determines if a given server has components mapped onto it
	 * 
	 * @param serverId
	 * @return true if a server is used (i.e. asm contexts are mapped to it)
	 */
	public boolean serverIsUsed(final String serverId) {
		for (final Collection<String> servers : this.assemblyContextToServerMapping
				.values()) {
			if (servers.contains(serverId)) {
				return true;
			}
		}
		return false;
	}

	public String getServer(final AssemblyContext asmContext) {
		return this.loadBalancer.getServerMapping(asmContext.getId(),
				this.assemblyContextToServerMapping.get(asmContext.getId()));
	}

	public String getServer(final String asmContext) {
		this.log.info("Getting Ressource Container for ASM Context: "
				+ asmContext);
		return this.loadBalancer.getServerMapping(asmContext,
				this.assemblyContextToServerMapping.get(asmContext));
	}

	/**
	 * unblock a component on a given server node
	 * 
	 * @param asmContext
	 * @param server
	 * @return
	 */
	public boolean unblockInstance(final String asmContext, final String server) {
		Hashtable<String, Boolean> blockState = this.serverToAsmToBlockState
				.get(server);
		if (blockState == null) {
			blockState = new Hashtable<String, Boolean>();
			this.serverToAsmToBlockState.put(server, blockState);
		}
		blockState.put(asmContext, Boolean.FALSE);
		return false;
	}

	/**
	 * Checks if a component on a server has any users left
	 * 
	 * @param asmContext
	 * @param server
	 * @return true if component has users left
	 */
	public boolean hasUsers(final String asmContext, final String server) {
		final Hashtable<String, Integer> users = this.serverToAsmToUserCount
				.get(server);
		if (users != null) {
			final Integer cUser = users.get(asmContext);
			if (cUser != null) {
				return cUser > 0;
			} else {
				return true;
			}
		}
		return false;
	}

	public int addUser(final String asmContext, final String server) {
		Hashtable<String, Integer> users = this.serverToAsmToUserCount
				.get(server);
		if (users != null) {
			Integer cUser = users.get(asmContext);
			if (cUser == null) {
				cUser = 0;
			}
			users.put(asmContext, cUser + 1);
			this.log.info("Server " + server + " Component " + asmContext
					+ " has " + (cUser + 1) + " users");
			AllocationController.stats.logComponentUsers(asmContext, server,
					cUser + 1);
			return cUser + 1;
		} else {
			users = new Hashtable<String, Integer>();
			this.serverToAsmToUserCount.put(server, users);
			users.put(asmContext, 1);
			this.log.info("Server " + server + " Component " + asmContext
					+ " has " + 1 + " users");
			AllocationController.stats.logComponentUsers(asmContext, server, 1);
			return 1;
		}
	}

	public int remUser(final String asmContext, final String server) {
		final Hashtable<String, Integer> users = this.serverToAsmToUserCount
				.get(server);
		if (users != null) {
			final Integer cUser = users.get(asmContext);
			if (cUser != null) {
				final int nextCUserCount = cUser - 1;
				users.put(asmContext, nextCUserCount);
				if (nextCUserCount == 0) {
					this.notifyReconfController(asmContext, server);
				}
				this.log.info("Server " + server + " Component " + asmContext
						+ " has " + nextCUserCount + " users");
				AllocationController.stats.logComponentUsers(asmContext,
						server, nextCUserCount);
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
	 * @param asmContext
	 * @param server
	 * @return true on success
	 */
	public boolean blockInstance(final String asmContext, final String server) {
		Hashtable<String, Boolean> blockState = this.serverToAsmToBlockState
				.get(server);
		this.log.info("blocking " + asmContext + " on server " + server);
		if (blockState == null) {
			blockState = new Hashtable<String, Boolean>();
			this.serverToAsmToBlockState.put(server, blockState);
		}
		blockState.put(asmContext, Boolean.TRUE);
		return false;
	}

	/**
	 * TODO emit event for reconf that component is empty
	 * 
	 * @param asmContext
	 * @param server
	 */
	private final void notifyReconfController(final String asmContext,
			final String server) {
		try {
			if (this.serverToAsmToBlockState.get(server).get(asmContext)) {
				ReconfigurationController.getInstrance().markUnusedAndBlocked(
						server, asmContext);
				this.unblockInstance(asmContext, server);
			}
		} catch (final Exception e) {
			// e.printStackTrace();
		}

	}

	public boolean hasAllocation(final String server, final String asmContext) {
		return this.assemblyContextToServerMapping.get(asmContext).contains(
				server);
	}

	/**
	 * Adds the asmContext to the server (used for replication)
	 * 
	 * @param server
	 * @param asmContext
	 */
	public void add(final String server, final String asmContext) {
		this.assemblyContextToServerMapping.get(asmContext).add(server);
	}

	/**
	 * remove a components instance mapping. this will not remove users. the
	 * reconfcontroller will be notified if when all pending requests to the
	 * removed instance have been computed.
	 * 
	 * @param component
	 * @return
	 */
	public boolean del(final AllocationContext component) {
		final String asmContext = component
				.getAssemblyContext_AllocationContext().getId();
		final Collection<String> servers = this.assemblyContextToServerMapping
				.get(asmContext);
		if (servers.size() > 1) {
			final String server = component
					.getResourceContainer_AllocationContext().getId();
			servers.remove(server);
			this.blockInstance(asmContext, server);

			this.log.info("Deleted "
					+ component.getAssemblyContext_AllocationContext().getId()
					+ " from container "
					+ component.getResourceContainer_AllocationContext()
							.getId());
			if (!this.hasUsers(asmContext, server)) {
				this.notifyReconfController(asmContext, server);
			}
			return true;
		} else {
			this.log.warn("Not allowing less than one allocation (tried to remove "
					+ component.getAssemblyContext_AllocationContext()
					+ " from "
					+ component.getResourceContainer_AllocationContext() + ")");
			return false;
		}
	}
}
