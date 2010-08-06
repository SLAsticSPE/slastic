/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironment;
import de.cau.se.slastic.metamodel.executionEnvironment.NetworkLink;

/**
 *
 * @author Andre van Hoorn
 */
public class ExecutionEnvironmentManager implements IExecutionContainersManager, INetworkLinksManager {

    private final ExecutionContainersManager executionContainersManager;
    private final NetworkLinksManager networkLinksManager;

    private ExecutionEnvironmentManager() {
        this.executionContainersManager = null;
        this.networkLinksManager = null;
    }

    public ExecutionEnvironmentManager(final ExecutionEnvironment executionEnvironment){
        this.executionContainersManager = new ExecutionContainersManager(executionEnvironment.getExecutionContainers());
        this.networkLinksManager = new NetworkLinksManager(executionEnvironment.getNetworkLinks());
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
    public ExecutionContainer createAndRegisterExecutionContainer(String fullyQualifiedName) {
        return this.executionContainersManager.createAndRegisterExecutionContainer(fullyQualifiedName);
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
    public NetworkLink createAndRegisterNetworkLink(String fullyQualifiedName) {
        return this.networkLinksManager.createAndRegisterNetworkLink(fullyQualifiedName);
    }
}
