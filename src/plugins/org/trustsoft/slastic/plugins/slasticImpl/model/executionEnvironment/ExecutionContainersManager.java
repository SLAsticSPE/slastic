package org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentFactory;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;
import java.util.List;

/**
 *
 * @author Andre van Hoorn
 */
public class ExecutionContainersManager extends AbstractFQNamedEntityManager<ExecutionContainer> implements IExecutionContainersManager {
    public ExecutionContainersManager(final List<ExecutionContainer> ExecutionContainers){
        super(ExecutionContainers);
    }

    @Override
    public ExecutionContainer lookupExecutionContainer(
            final String fullyQualifiedName) {
        return this.lookup(fullyQualifiedName);
    }

    @Override
    public ExecutionContainer lookupExecutionContainer(final long id){
        return this.lookup(id);
    }

    @Override
    public ExecutionContainer createAndRegisterExecutionContainer(
            final String fullyQualifiedName, final ExecutionContainerType executionContainerType) {
        ExecutionContainer executionContainer = 
                this.createAndRegister(fullyQualifiedName);
        executionContainer.setExecutionContainerType(executionContainerType);
        return executionContainer;
    }

    @Override
    protected ExecutionContainer createEntity() {
        return ExecutionEnvironmentFactory.eINSTANCE.createExecutionContainer();
    }
}
