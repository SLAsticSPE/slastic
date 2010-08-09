/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.plugins.slasticImpl.model;

import de.cau.se.slastic.metamodel.core.Entity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractEntityManager<T extends Entity> {
    private volatile long nextId = 1;
    private final Map<Long, T> entitiesById =
            new HashMap<Long, T>();
    private final List<T> entities;

    private AbstractEntityManager(){
        this.entities = null;
    }

    public AbstractEntityManager(final List<T> entities){
        this.entities = entities;
        for (T entity : entities){
            this.addEntityToIdMap(entity);
        }
    }

    public T lookup(final long id){
        return this.entitiesById.get(id);
    }

    protected abstract T createEntity();

    public T createAndRegister() {
        final T newEntity = this.createEntity();
        newEntity.setId(nextId++);
        this.entities.add(newEntity);
        this.addEntityToIdMap(newEntity);
        return newEntity;
    }

    /**
     * Adds a newly created component to the local tables.
     */
    private final void addEntityToIdMap(T componentType){
        this.entitiesById.put(componentType.getId(), componentType);
    }
}
