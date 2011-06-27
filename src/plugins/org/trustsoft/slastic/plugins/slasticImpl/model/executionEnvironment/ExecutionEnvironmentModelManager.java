package org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment;

import java.util.Collection;

import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel;
import de.cau.se.slastic.metamodel.executionEnvironment.NetworkLink;
import de.cau.se.slastic.metamodel.executionEnvironment.Resource;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;
import de.cau.se.slastic.metamodel.typeRepository.NetworkLinkType;

/**
 * 
 * @author Andre van Hoorn
 */
public class ExecutionEnvironmentModelManager extends AbstractModelManager<ExecutionEnvironmentModel> implements
		IExecutionContainersManager, INetworkLinksManager// ,
// IExecutionContainersAllocationManager
{

	/**
	 * TODO: HACK: This information should be stored in an intermediate layer
	 * between Monitoring and Reconfiguration (#10, #11).
	 * 
	 * Maps a technical hostname to the corresponding architectural container
	 * name;
	 */
	// public final ConcurrentHashMap<String, ExecutionContainer>
	// containerNameMapping =
	// new ConcurrentHashMap<String, ExecutionContainer>();

	private final ExecutionContainersManager executionContainersManager;
	private final NetworkLinksManager networkLinksManager;

	// TODO: remove?
	// private final ExecutionContainersAllocationManager
	// executionContainersAllocationManager;

	private ExecutionEnvironmentModelManager() {
		super(null);
		this.executionContainersManager = null;
		this.networkLinksManager = null;
		// TODO: remove?
		// this.executionContainersAllocationManager = null;
	}

	public ExecutionEnvironmentModelManager(final ExecutionEnvironmentModel executionEnvironmentModel) {
		super(executionEnvironmentModel);
		this.executionContainersManager =
				new ExecutionContainersManager(executionEnvironmentModel.getExecutionContainers());
		this.networkLinksManager = new NetworkLinksManager(executionEnvironmentModel.getNetworkLinks());
		// TODO: remove?
		// this.executionContainersAllocationManager =new
		// ExecutionContainersAllocationManager(executionEnvironmentModel.getAllocatedExecutionContainers());
	}

	@Override
	public ExecutionContainer lookupExecutionContainer(final String fullyQualifiedName) {
		return this.executionContainersManager.lookup(fullyQualifiedName);
	}

	@Override
	public ExecutionContainer lookupExecutionContainer(final long id) {
		return this.executionContainersManager.lookupExecutionContainer(id);
	}

	@Override
	public ExecutionContainer createAndRegisterExecutionContainer(final String fullyQualifiedName,
			final ExecutionContainerType executionContainerType, final boolean markAllocated) {
		return this.executionContainersManager.createAndRegisterExecutionContainer(fullyQualifiedName,
				executionContainerType, markAllocated);
	}

	@Override
	public boolean allocateExecutionContainer(final ExecutionContainer executionContainer) {
		return this.executionContainersManager.allocateExecutionContainer(executionContainer);
	}

	@Override
	public boolean deallocateExecutionContainer(final ExecutionContainer executionContainer) {
		return this.executionContainersManager.deallocateExecutionContainer(executionContainer);
	}

	// @Override
	// public boolean deleteExecutionContainer(final ExecutionContainer
	// executionContainer) {
	// return
	// this.executionContainersManager.deleteExecutionContainer(executionContainer);
	// }

	@Override
	public Resource lookupExecutionContainerResource(final ExecutionContainer executionContainer,
			final String resourceSpecificationName) {
		return this.executionContainersManager.lookupExecutionContainerResource(executionContainer,
				resourceSpecificationName);
	}

	@Override
	public NetworkLink lookupNetworkLink(final String fullyQualifiedName) {
		return this.networkLinksManager.lookupNetworkLink(fullyQualifiedName);
	}

	@Override
	public NetworkLink lookupNetworkLink(final long id) {
		return this.networkLinksManager.lookupNetworkLink(id);
	}

	@Override
	public NetworkLink createAndRegisterNetworkLink(final String fullyQualifiedName,
			final NetworkLinkType networkLinkType) {
		return this.networkLinksManager.createAndRegisterNetworkLink(fullyQualifiedName, networkLinkType);
	}

	@Override
	public Collection<ExecutionContainer> executionContainersForType(
			final ExecutionContainerType executionContainerType, final boolean includeInactive) {
		return this.executionContainersManager.executionContainersForType(executionContainerType, includeInactive);
	}
}
