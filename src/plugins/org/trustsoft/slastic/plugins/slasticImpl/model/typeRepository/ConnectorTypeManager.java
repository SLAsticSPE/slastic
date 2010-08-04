package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;

/**
 *
 * @author Andre van Hoorn
 */
public class ConnectorTypeManager implements IConnectorTypeManager {
    private volatile long nextConnectorTypeId = 1;
    private final Map<String, ConnectorType> connectorTypesByFullQualifiedName =
            new HashMap<String, ConnectorType>();
    private final Map<Long, ConnectorType> connectorTypesById =
            new HashMap<Long, ConnectorType>();
    private final List<ConnectorType> connectorTypes;

    private ConnectorTypeManager(){
        this.connectorTypes = null;
    }

    public ConnectorTypeManager(final List<ConnectorType> connectorTypes){
        this.connectorTypes = connectorTypes;
        for (ConnectorType connectorType : connectorTypes){
            this.addConnectorTypeToTables(connectorType);
        }
    }

    @Override
    public ConnectorType lookupConnectorType(
            final String fullyQualifiedName) {
        return this.connectorTypesByFullQualifiedName.get(fullyQualifiedName);
    }

    @Override
    public ConnectorType lookupConnectorType(final long id){
        return this.connectorTypesById.get(id);
    }

    @Override
    public ConnectorType createAndRegisterConnectorType(
            final String fullyQualifiedName) {
        if (this.connectorTypesByFullQualifiedName.containsKey(fullyQualifiedName)) {
            throw new IllegalArgumentException("Element with name " + fullyQualifiedName + "exists already");
        }
        final ConnectorType newConnectorType =
                TypeRepositoryFactory.eINSTANCE.createConnectorType();
        newConnectorType.setId(nextConnectorTypeId++);
        final String[] fqnSplit = NameUtils.splitFullyQualifiedName(fullyQualifiedName);
        newConnectorType.setPackageName(fqnSplit[0]);
        newConnectorType.setName(fqnSplit[1]);
        this.connectorTypes.add(newConnectorType);
        this.addConnectorTypeToTables(newConnectorType);
        return newConnectorType;
    }

    /**
     * Adds a newly created connector to the local tables.
     */
    private void addConnectorTypeToTables(ConnectorType connectorType){
        this.connectorTypesByFullQualifiedName.put(
                connectorType.getPackageName()+"."+connectorType.getName(),
                connectorType);
        this.connectorTypesById.put(connectorType.getId(), connectorType);
    }
}
