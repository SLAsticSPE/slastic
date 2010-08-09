package org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel;
import de.cau.se.slastic.metamodel.executionEnvironment.NetworkLink;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;
import de.cau.se.slastic.metamodel.typeRepository.NetworkLinkType;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;

/**
 *
 * @author Andre van Hoorn
 */
public class ExecutionEnvironmentModelManager extends AbstractModelManager<ExecutionEnvironmentModel> implements IExecutionContainersManager, INetworkLinksManager, IExecutionContainersAllocationManager {

    private final ExecutionContainersManager executionContainersManager;
    private final NetworkLinksManager networkLinksManager;
    private final ExecutionContainersAllocationManager executionContainersAllocationManager;

    private ExecutionEnvironmentModelManager() {
        super(null);
        this.executionContainersManager = null;
        this.networkLinksManager = null;
        this.executionContainersAllocationManager = null;
    }

    public ExecutionEnvironmentModelManager(final ExecutionEnvironmentModel executionEnvironmentModel){
        super(executionEnvironmentModel);
        this.executionContainersManager = new ExecutionContainersManager(executionEnvironmentModel.getExecutionContainers());
        this.networkLinksManager = new NetworkLinksManager(executionEnvironmentModel.getNetworkLinks());
        this.executionContainersAllocationManager = new ExecutionContainersAllocationManager(executionEnvironmentModel.getAllocatedExecutionContainers());
    }

    @Override
    public ExecutionContainer lookupExecutionContainer(String fullyQualifiedName) {
        return this.executionContainersManager.lookup(fullyQualifiedName);
    }

    @Override
    public ExecutionContainer lookupExecutionContainer(long id) {
        return this.executionContainersManager.lookupExecutionContainer(id);
    }

    @Override
    public ExecutionContainer createAndRegisterExecutionContainer(String fullyQualifiedName, ExecutionContainerType executionContainerType) {
        return this.executionContainersManager.createAndRegisterExecutionContainer(fullyQualifiedName, executionContainerType);
    }

    @Override
    public NetworkLink lookupNetworkLink(String fullyQualifiedName) {
        return this.networkLinksManager.lookupNetworkLink(fullyQualifiedName);
    }

    @Override
    public NetworkLink lookupNetworkLink(long id) {
        return this.networkLinksManager.lookupNetworkLink(id);
    }

    @Override
    public NetworkLink createAndRegisterNetworkLink(String fullyQualifiedName, NetworkLinkType networkLinkType) {
        return this.networkLinksManager.createAndRegisterNetworkLink(fullyQualifiedName, networkLinkType);
    }

    @Override
    public boolean allocateExecutionContainer(ExecutionContainer executionContainer) {
        return this.executionContainersAllocationManager.allocateExecutionContainer(executionContainer);
    }

    @Override
    public boolean deallocateExecutionContainer(ExecutionContainer executionContainer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
