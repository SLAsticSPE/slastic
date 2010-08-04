/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trustsoft.slastic.plugins.slasticImpl.model;

import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepository;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author avanhoorn
 */
public class RepositoryManager {

    private volatile long nextComponentId = 1;
    private final TypeRepository typeRepository;
    private final Map<String, ComponentType> componentTypesByFullQualifiedName =
            new HashMap<String, ComponentType>();
    private final Map<Long, ComponentType> componentTypesById =
            new HashMap<Long, ComponentType>();

    public RepositoryManager() {
        this.typeRepository =
                TypeRepositoryFactory.eINSTANCE.createTypeRepository();
    }

    public ComponentType lookupComponentType(
            final String fullyQualifiedName) {
        return this.componentTypesByFullQualifiedName.get(fullyQualifiedName);
    }

    public ComponentType lookupComponentType(final long id){
        return this.componentTypesById.get(id);
    }

    /**
     *
     * @param fullyQualifiedName
     * @return
     * @throws IllegalArgumentException if a component type with the given
     * namedIdentifier has already been registered
     */
    public ComponentType createAndRegisterComponentType(
            final String fullyQualifiedName) {
        if (this.componentTypesByFullQualifiedName.containsKey(fullyQualifiedName)) {
            throw new IllegalArgumentException("Element with name " + fullyQualifiedName + "exists already");
        }
        final ComponentType newComponentType =
                TypeRepositoryFactory.eINSTANCE.createComponentType();
        newComponentType.setId(nextComponentId++);
        final String[] fqnSplit = splitFullyQualifiedName(fullyQualifiedName);
        newComponentType.setPackageName(fqnSplit[0]);
        newComponentType.setName(fqnSplit[1]);
        typeRepository.getComponentTypes().add(newComponentType);
        this.componentTypesByFullQualifiedName.put(fullyQualifiedName, newComponentType);
        this.componentTypesById.put(newComponentType.getId(), newComponentType);
        return newComponentType;
    }

    /**
     * Returns an array containing the package name and identifier components
     * of a fully-qualified name string.
     *
     * @param fullyQualifiedName the fully-qualified name to split
     * @return the array field with index 0 contains the package name,
     *         the array field with index 1 contains the identifier
     *
     */
    private String[] splitFullyQualifiedName(final String fullyQualifiedName) {
        final int idx = fullyQualifiedName.lastIndexOf(".");
        final String identifier;
        final String packageName;
        if (idx == -1) { // no parameter list
            packageName = "";
            identifier = fullyQualifiedName;
        } else {
            packageName = fullyQualifiedName.substring(0, idx);
            identifier = fullyQualifiedName.substring(idx + 1, fullyQualifiedName.length() - 1);
        }
        return new String[]{packageName, identifier};
    }
}
