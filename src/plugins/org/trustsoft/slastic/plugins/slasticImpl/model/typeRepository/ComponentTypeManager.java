/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;

/**
 *
 * @author Andre van Hoorn
 */
public class ComponentTypeManager implements IComponentTypeManager {
    private volatile long nextComponentId = 1;
    private final Map<String, ComponentType> componentTypesByFullQualifiedName =
            new HashMap<String, ComponentType>();
    private final Map<Long, ComponentType> componentTypesById =
            new HashMap<Long, ComponentType>();
    private final List<ComponentType> componentTypes;

    private ComponentTypeManager(){
        this.componentTypes = null;
    }

    public ComponentTypeManager(final List<ComponentType> componentTypes){
        this.componentTypes = componentTypes;
        for (ComponentType componentType : componentTypes){
            this.addComponentTypeToTables(componentType);
        }
    }

    @Override
    public ComponentType lookupComponentType(
            final String fullyQualifiedName) {
        return this.componentTypesByFullQualifiedName.get(fullyQualifiedName);
    }

    @Override
    public ComponentType lookupComponentType(final long id){
        return this.componentTypesById.get(id);
    }

    @Override
    public ComponentType createAndRegisterComponentType(
            final String fullyQualifiedName) {
        if (this.componentTypesByFullQualifiedName.containsKey(fullyQualifiedName)) {
            throw new IllegalArgumentException("Element with name " + fullyQualifiedName + "exists already");
        }
        final ComponentType newComponentType =
                TypeRepositoryFactory.eINSTANCE.createComponentType();
        newComponentType.setId(nextComponentId++);
        final String[] fqnSplit = NameUtils.splitFullyQualifiedName(fullyQualifiedName);
        newComponentType.setPackageName(fqnSplit[0]);
        newComponentType.setName(fqnSplit[1]);
        this.componentTypes.add(newComponentType);
        this.addComponentTypeToTables(newComponentType);
        return newComponentType;
    }

    /**
     * Adds a newly created component to the local tables.
     */
    private void addComponentTypeToTables(ComponentType componentType){
        this.componentTypesByFullQualifiedName.put(
                componentType.getPackageName()+"."+componentType.getName(),
                componentType);
        this.componentTypesById.put(componentType.getId(), componentType);
    }
}
