package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;
import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;
import java.util.List;

/**
 *
 * @author Andre van Hoorn
 */
public class InterfacesManager extends AbstractFQNamedEntityManager<Interface> implements IInterfacesManager {
    public InterfacesManager(final List<Interface> interfaces){
        super(interfaces);
    }

    @Override
    public Interface lookupInterface(
            final String fullyQualifiedName) {
        return this.lookup(fullyQualifiedName);
    }

    @Override
    public Interface lookupInterface(final long id){
        return this.lookup(id);
    }

    @Override
    public Interface createAndRegisterInterface(
            final String fullyQualifiedName) {
        return this.createAndRegister(fullyQualifiedName);
    }

    @Override
    protected Interface createEntity() {
        return TypeRepositoryFactory.eINSTANCE.createInterface();
    }
}
