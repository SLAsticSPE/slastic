package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;
import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.NetworkLinkType;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryModel;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;

/**
 * TODO: interfaces, network link types
 *
 * @author Andre van Hoorn
 */
public class TypeRepositoryModelManager extends AbstractModelManager<TypeRepositoryModel> implements IComponentTypesManager, IInterfacesManager, IConnectorTypesManager, IExecutionContainerTypesManager, INetworkLinkTypesManager {

    private final ComponentTypesManager componentTypeManager;
    private final ConnectorTypesManager connectorTypeManager;
    private final InterfacesManager interfaceManager;
    private final ExecutionContainerTypesManager executionContainerTypeManager;
    private final NetworkLinkTypesManager networkLinkTypeManager;


    private TypeRepositoryModelManager() {
        super(null);
        this.componentTypeManager = null;
        this.connectorTypeManager = null;
        this.interfaceManager = null;
        this.executionContainerTypeManager = null;
        this.networkLinkTypeManager = null;
    }

    public TypeRepositoryModelManager(final TypeRepositoryModel typeRepositoryModel) {
        super(typeRepositoryModel);
        this.componentTypeManager =
                new ComponentTypesManager(typeRepositoryModel.getComponentTypes());
        this.connectorTypeManager =
                new ConnectorTypesManager(typeRepositoryModel.getConnectorTypes());
        this.interfaceManager =
                new InterfacesManager(typeRepositoryModel.getInterfaces());
        this.executionContainerTypeManager =
                new ExecutionContainerTypesManager(typeRepositoryModel.getExecutionContainerTypes());
        this.networkLinkTypeManager =
                new NetworkLinkTypesManager(typeRepositoryModel.getNetworkLinkType());
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
    public Interface lookupInterface(String fullyQualifiedName) {
        return this.interfaceManager.lookupInterface(fullyQualifiedName);
    }

    @Override
    public Interface lookupInterface(long id) {
        return this.interfaceManager.lookupInterface(id);
    }

    @Override
    public Interface createAndRegisterInterface(String fullyQualifiedName) {
        return this.interfaceManager.createAndRegisterInterface(fullyQualifiedName);
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

   @Override
    public NetworkLinkType lookupNetworkLinkType(String fullyQualifiedName) {
        return this.networkLinkTypeManager.lookupNetworkLinkType(fullyQualifiedName);
    }

    @Override
    public NetworkLinkType lookupNetworkLinkType(long id) {
        return this.networkLinkTypeManager.lookupNetworkLinkType(id);
    }

    @Override
    public NetworkLinkType createAndRegisterNetworkLinkType(String fullyQualifiedName) {
        return this.networkLinkTypeManager.createAndRegisterNetworkLinkType(fullyQualifiedName);
    }
}
