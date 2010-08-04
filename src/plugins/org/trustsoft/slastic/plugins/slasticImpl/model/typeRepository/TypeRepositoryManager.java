package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepository;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class TypeRepositoryManager implements IComponentTypeManager, IConnectorTypeManager {

    private final TypeRepository typeRepositoryModel;
    private final ComponentTypeManager componentTypeManager;
    private final ConnectorTypeManager connectorTypeManager;

    public TypeRepositoryManager() {
        this.typeRepositoryModel =
                TypeRepositoryFactory.eINSTANCE.createTypeRepository();
        this.componentTypeManager =
                new ComponentTypeManager(this.typeRepositoryModel.getComponentTypes());
        this.connectorTypeManager =
                new ConnectorTypeManager(this.typeRepositoryModel.getConnectorTypes());
    }

    public TypeRepositoryManager(final TypeRepository typeRepositoryModel) {
        this.typeRepositoryModel = typeRepositoryModel;
        this.componentTypeManager =
                new ComponentTypeManager(this.typeRepositoryModel.getComponentTypes());
        this.connectorTypeManager =
                new ConnectorTypeManager(this.typeRepositoryModel.getConnectorTypes());
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
}
