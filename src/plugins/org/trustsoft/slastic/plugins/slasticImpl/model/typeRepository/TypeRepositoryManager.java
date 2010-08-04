package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepository;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class TypeRepositoryManager implements IComponentTypeManager, IConnectorTypeManager, IExecutionContainerTypeManager {

    private final TypeRepository typeRepositoryModel;
    private final ComponentTypeManager componentTypeManager;
    private final ConnectorTypeManager connectorTypeManager;
    private final ExecutionContainerTypeManager executionContainerTypeManager;

    public TypeRepositoryManager() {
        this.typeRepositoryModel =
                TypeRepositoryFactory.eINSTANCE.createTypeRepository();
        this.componentTypeManager =
                new ComponentTypeManager(this.typeRepositoryModel.getComponentTypes());
        this.connectorTypeManager =
                new ConnectorTypeManager(this.typeRepositoryModel.getConnectorTypes());
        this.executionContainerTypeManager =
                new ExecutionContainerTypeManager(this.typeRepositoryModel.getExecutionContainerTypes());
    }

    public TypeRepositoryManager(final TypeRepository typeRepositoryModel) {
        this.typeRepositoryModel = typeRepositoryModel;
        this.componentTypeManager =
                new ComponentTypeManager(this.typeRepositoryModel.getComponentTypes());
        this.connectorTypeManager =
                new ConnectorTypeManager(this.typeRepositoryModel.getConnectorTypes());
       this.executionContainerTypeManager =
                new ExecutionContainerTypeManager(this.typeRepositoryModel.getExecutionContainerTypes());
    }

    @Override
    public ComponentType lookupComponentType(String fullyQualifiedName) {
        return this.componentTypeManager.lookupComponentType(fullyQualifiedName);
    }

    @Override
    public ComponentType lookupComponentType(long id) {
        return this.componentTypeManager.lookupComponentType(id);
    }

    @Override
    public ComponentType createAndRegisterComponentType(String fullyQualifiedName) {
        return this.componentTypeManager.createAndRegisterComponentType(fullyQualifiedName);
    }

    @Override
    public ConnectorType lookupConnectorType(String fullyQualifiedName) {
        return this.connectorTypeManager.lookupConnectorType(fullyQualifiedName);
    }

    @Override
    public ConnectorType lookupConnectorType(long id) {
        return this.connectorTypeManager.lookupConnectorType(id);
    }

    @Override
    public ConnectorType createAndRegisterConnectorType(String fullyQualifiedName) {
        return this.connectorTypeManager.createAndRegisterConnectorType(fullyQualifiedName);
    }

    @Override
    public ExecutionContainerType lookupExecutionContainerType(String fullyQualifiedName) {
        return this.executionContainerTypeManager.lookupExecutionContainerType(fullyQualifiedName);
    }

    @Override
    public ExecutionContainerType lookupExecutionContainerType(long id) {
        return this.executionContainerTypeManager.lookupExecutionContainerType(id);
    }

    @Override
    public ExecutionContainerType createAndRegisterExecutionContainerType(String fullyQualifiedName) {
        return this.executionContainerTypeManager.createAndRegisterExecutionContainerType(fullyQualifiedName);
    }
}
