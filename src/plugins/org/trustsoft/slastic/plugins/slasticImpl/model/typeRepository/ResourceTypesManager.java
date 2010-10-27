package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import java.util.List;

import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;

import de.cau.se.slastic.metamodel.typeRepository.ResourceType;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class ResourceTypesManager extends AbstractFQNamedEntityManager<ResourceType> implements IResourceTypesManager {
    public ResourceTypesManager(final List<ResourceType> resourceTypes){
        super(resourceTypes);
    }

    @Override
    public ResourceType lookupResourceType(
            final String fullyQualifiedName) {
        return this.lookup(fullyQualifiedName);
    }

    @Override
    public ResourceType lookupResourceType(final long id){
        return this.lookupEntityById(id);
    }

    @Override
    public ResourceType createAndRegisterResourceType(
            final String fullyQualifiedName) {
        return this.createAndRegister(fullyQualifiedName);
    }

    @Override
    protected ResourceType createEntity() {
        return TypeRepositoryFactory.eINSTANCE.createResourceType();
    }
}
