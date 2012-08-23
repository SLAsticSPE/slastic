/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

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
        return this.lookupEntityById(id);
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
