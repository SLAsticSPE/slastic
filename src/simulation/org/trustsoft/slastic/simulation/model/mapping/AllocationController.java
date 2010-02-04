package org.trustsoft.slastic.simulation.model.mapping;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.model.hardware.controller.engine.Server;
import org.trustsoft.slastic.simulation.model.mapping.loadbalancer.RandomBalancer;
import org.trustsoft.slastic.simulation.model.reconfiguration.ReconfigurationController;

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

	private final LoadBalancer loadBalancer;

	// TODO: block components!
	// TODO: active process count would probably best places here

	public AllocationController(final Allocation allocation, final Model model) {
		genAllocationModel(allocation);
		loadBalancer = new RandomBalancer();
		this.model = model;
	}

	public AllocationController(final Allocation allocation,
			final LoadBalancer lb, final Model model) {
		genAllocationModel(allocation);
		loadBalancer = lb;
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
			serverToAllocationContextsMapping.put(server.getId(),
					new HashSet<String>());
		}
		final Collection<AssemblyContext> asmContexts = ModelManager
				.getInstance().getAssemblyCont().getAllASMContexts();
		for (final AssemblyContext assemblyContext : asmContexts) {
			assemblyContextToServerMapping.put(assemblyContext.getId(),
					new HashSet<String>());
		}
		for (final AllocationContext allocContext : contexts) {
			assemblyContextToServerMapping
					.get(
							allocContext.getAssemblyContext_AllocationContext()
									.getId()).add(
							allocContext
									.getResourceContainer_AllocationContext()
									.getId());
			serverToAllocationContextsMapping.get(
					allocContext.getResourceContainer_AllocationContext()
							.getId())
					.add(
							allocContext.getAssemblyContext_AllocationContext()
									.getId());
			ModelManager.getInstance().getHwCont().allocate(
					allocContext.getResourceContainer_AllocationContext()
							.getId());
		}

	}

	/**
	 * Determines if a given server has components mapped onto it
	 * 
	 * @param id
	 * @return true if a server is used (i.e. asm contexts are mapped to it)
	 */
	public boolean serverIsUsed(final String id) {
		return !serverToAllocationContextsMapping.get(id).isEmpty();
	}

	public String getServer(final AssemblyContext asmContext) {
		return loadBalancer.getServerMapping(asmContext.getId(),
				assemblyContextToServerMapping.get(asmContext.getId()));
	}

	public String getServer(final String asmContext) {
		return loadBalancer.getServerMapping(asmContext,
				assemblyContextToServerMapping.get(asmContext));
	}

	/**
	 * block a component on a given server node
	 * 
	 * @param asmContext
	 * @param server
	 * @return true on success
	 */
	public boolean blockInstance(final String asmContext, final String server) {
		final Hashtable<String, Boolean> blockState = serverToAsmToBlockState
				.get(server);
		if (blockState != null) {
			Boolean blocked = blockState.get(asmContext);
			if (blocked != null) {
				blocked = Boolean.TRUE;
				return true;
			}
		}
		return false;
	}

	/**
	 * unblock a component on a given server node
	 * 
	 * @param asmContext
	 * @param server
	 * @return
	 */
	public boolean unblockInstance(final String asmContext, final String server) {
		final Hashtable<String, Boolean> blockState = serverToAsmToBlockState
				.get(server);
		if (blockState != null) {
			Boolean blocked = blockState.get(asmContext);
			if (blocked != null) {
				blocked = Boolean.FALSE;
				return true;
			}
		}
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
		final Hashtable<String, Integer> users = serverToAsmToUserCount
				.get(server);
		if (users != null) {
			final Integer cUser = users.get(asmContext);
			if (cUser != null) {
				return cUser > 0;
			}
		}
		return false;
	}

	public int addUser(final String asmContext, final String server) {
		final Hashtable<String, Integer> users = serverToAsmToUserCount
				.get(server);
		if (users != null) {
			final Integer cUser = users.get(asmContext);
			if (cUser != null) {
				users.put(asmContext, cUser + 1);
				return cUser + 1;
			}
		}
		return -1;
	}

	public int remUser(final String asmContext, final String server) {
		final Hashtable<String, Integer> users = serverToAsmToUserCount
				.get(server);
		if (users != null) {
			final Integer cUser = users.get(asmContext);
			if (cUser != null) {
				final int nextCUserCount = cUser - 1;
				users.put(asmContext, nextCUserCount);
				if (nextCUserCount == 0) {
					notifyReconfController(asmContext, server);
				}
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
	 * TODO emit event for reconf that component is empty
	 * 
	 * @param asmContext
	 * @param server
	 */
	private final void notifyReconfController(final String asmContext,
			final String server) {
		// TODO Auto-generated method stub
		if (serverToAsmToBlockState.get(server).get(asmContext)) {
			ReconfigurationController.getInstrance().markUnusedAndBlocked(
					server, asmContext);
		}
	}
}
