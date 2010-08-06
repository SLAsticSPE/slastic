package org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyFactory;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;
import java.util.List;

/**
 *
 * @author Andre van Hoorn
 */
public class AssemblyComponentsManager extends AbstractFQNamedEntityManager<AssemblyComponent> implements IAssemblyComponentsManager {
    public AssemblyComponentsManager(final List<AssemblyComponent> AssemblyComponents){
        super(AssemblyComponents);
    }

    @Override
    public AssemblyComponent lookupAssemblyComponent(
            final String fullyQualifiedName) {
        return this.lookup(fullyQualifiedName);
    }

    @Override
    public AssemblyComponent lookupAssemblyComponent(final long id){
        return this.lookup(id);
    }

    @Override
    public AssemblyComponent createAndRegisterAssemblyComponent(
            final String fullyQualifiedName,
            final ComponentType componentType) {
        AssemblyComponent assemblyComponent =
                this.createAndRegister(fullyQualifiedName);
        assemblyComponent.setComponentType(componentType);
        return assemblyComponent;
    }

    @Override
    protected AssemblyComponent createEntity() {
        return ComponentAssemblyFactory.eINSTANCE.createAssemblyComponent();
    }
}
