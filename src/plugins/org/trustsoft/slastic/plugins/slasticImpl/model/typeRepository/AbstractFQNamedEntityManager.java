/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import de.cau.se.slastic.metamodel.core.FQNamedEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractFQNamedEntityManager<T extends FQNamedEntity> {
    private volatile long nextId = 1;
    private final Map<String, T> entitiesByFullQualifiedName =
            new HashMap<String, T>();
    private final Map<Long, T> entitiesById =
            new HashMap<Long, T>();
    private final List<T> entities;

    private AbstractFQNamedEntityManager(){
        this.entities = null;
    }

    public AbstractFQNamedEntityManager(final List<T> entities){
        this.entities = entities;
        for (T entity : entities){
            this.addEntitiyToTables(entity);
        }
    }

    public T lookup(
            final String fullyQualifiedName) {
        return this.entitiesByFullQualifiedName.get(fullyQualifiedName);
    }

    public T lookup(final long id){
        return this.entitiesById.get(id);
    }

    protected abstract T createEntity();

    public T createAndRegister(
            final String fullyQualifiedName) {
        if (this.entitiesByFullQualifiedName.containsKey(fullyQualifiedName)) {
            throw new IllegalArgumentException("Element with name " + fullyQualifiedName + "exists already");
        }
        final T newEntity = this.createEntity();
        newEntity.setId(nextId++);
        final String[] fqnSplit = NameUtils.splitFullyQualifiedName(fullyQualifiedName);
        newEntity.setPackageName(fqnSplit[0]);
        newEntity.setName(fqnSplit[1]);
        this.entities.add(newEntity);
        this.addEntitiyToTables(newEntity);
        return newEntity;
    }

    /**
     * Adds a newly created component to the local tables.
     */
    private void addEntitiyToTables(T componentType){
        this.entitiesByFullQualifiedName.put(
                componentType.getPackageName()+"."+componentType.getName(),
                componentType);
        this.entitiesById.put(componentType.getId(), componentType);
    }
}
