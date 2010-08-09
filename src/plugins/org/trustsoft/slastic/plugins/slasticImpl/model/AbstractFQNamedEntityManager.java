/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.plugins.slasticImpl.model;

import de.cau.se.slastic.metamodel.core.FQNamedEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractFQNamedEntityManager<T extends FQNamedEntity> extends AbstractEntityManager<T> {
    private final Map<String, T> entitiesByFullQualifiedName =
            new HashMap<String, T>();

    public AbstractFQNamedEntityManager(final List<T> entities){
        super(entities);
        for (T entity : entities){
            this.addEntitiyToNameMap(entity);
        }
    }

    public T lookup(
            final String fullyQualifiedName) {
        return this.entitiesByFullQualifiedName.get(fullyQualifiedName);
    }

    protected abstract T createEntity();

    public T createAndRegister(
            final String fullyQualifiedName) {
        if (this.entitiesByFullQualifiedName.containsKey(fullyQualifiedName)) {
            throw new IllegalArgumentException("Element with name " + fullyQualifiedName + "exists already");
        }
        final T newEntity = super.createAndRegisterEntity();
        final String[] fqnSplit = NameUtils.splitFullyQualifiedName(fullyQualifiedName);
        newEntity.setPackageName(fqnSplit[0]);
        newEntity.setName(fqnSplit[1]);
        this.addEntitiyToNameMap(newEntity);
        return newEntity;
    }

    /**
     * Adds a newly created component to the local tables.
     */
    private void addEntitiyToNameMap(T componentType){
        this.entitiesByFullQualifiedName.put(
                componentType.getPackageName()+"."+componentType.getName(),
                componentType);
    }
}
