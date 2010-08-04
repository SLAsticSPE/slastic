package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;
import java.util.List;

/**
 *
 * @author Andre van Hoorn
 */
public class ComponentTypeManager extends AbstractFQNamedEntityManager<ComponentType> implements IComponentTypeManager {
    public ComponentTypeManager(final List<ComponentType> componentTypes){
        super(componentTypes);
    }

    @Override
    public ComponentType lookupComponentType(
            final String fullyQualifiedName) {
        return this.lookup(fullyQualifiedName);
    }

    @Override
    public ComponentType lookupComponentType(final long id){
        return this.lookup(id);
    }

    @Override
    public ComponentType createAndRegisterComponentType(
            final String fullyQualifiedName) {
        return this.createAndRegister(fullyQualifiedName);
    }

    @Override
    protected ComponentType createEntity() {
        return TypeRepositoryFactory.eINSTANCE.createComponentType();
    }
}
