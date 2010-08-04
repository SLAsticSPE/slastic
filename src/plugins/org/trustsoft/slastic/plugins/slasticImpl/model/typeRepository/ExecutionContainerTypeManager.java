package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;
import java.util.List;

/**
 *
 * @author Andre van Hoorn
 */
public class ExecutionContainerTypeManager extends AbstractFQNamedEntityManager<ExecutionContainerType> implements IExecutionContainerTypeManager {
    public ExecutionContainerTypeManager(final List<ExecutionContainerType> componentTypes){
        super(componentTypes);
    }

    @Override
    public ExecutionContainerType lookupExecutionContainerType(
            final String fullyQualifiedName) {
        return this.lookup(fullyQualifiedName);
    }

    @Override
    public ExecutionContainerType lookupExecutionContainerType(final long id){
        return this.lookup(id);
    }

    @Override
    public ExecutionContainerType createAndRegisterExecutionContainerType(
            final String fullyQualifiedName) {
        return this.createAndRegister(fullyQualifiedName);
    }

    @Override
    protected ExecutionContainerType createEntity() {
        return TypeRepositoryFactory.eINSTANCE.createExecutionContainerType();
    }
}
