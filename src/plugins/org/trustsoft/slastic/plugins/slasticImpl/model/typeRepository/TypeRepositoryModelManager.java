package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import de.cau.se.slastic.metamodel.core.SLAsticModel;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryModel;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;

/**
 * TODO: interfaces, network link types
 *
 * @author Andre van Hoorn
 */
public class TypeRepositoryModelManager extends AbstractModelManager<TypeRepositoryModel> implements IComponentTypesManager, IConnectorTypesManager, IExecutionContainerTypesManager {

    private final ComponentTypesManager componentTypeManager;
    private final ConnectorTypesManager connectorTypeManager;
    private final ExecutionContainerTypesManager executionContainerTypeManager;

    private TypeRepositoryModelManager() {
        super(null);
        this.componentTypeManager = null;
        this.connectorTypeManager = null;
        this.executionContainerTypeManager = null;
    }

    public TypeRepositoryModelManager(final TypeRepositoryModel typeRepositoryModel) {
        super(typeRepositoryModel);
        this.componentTypeManager =
                new ComponentTypesManager(typeRepositoryModel.getComponentTypes());
        this.connectorTypeManager =
                new ConnectorTypesManager(typeRepositoryModel.getConnectorTypes());
       this.executionContainerTypeManager =
                new ExecutionContainerTypesManager(typeRepositoryModel.getExecutionContainerTypes());
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