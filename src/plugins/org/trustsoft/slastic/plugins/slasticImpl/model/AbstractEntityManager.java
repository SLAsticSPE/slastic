/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.plugins.slasticImpl.model;

import de.cau.se.slastic.metamodel.core.Entity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractEntityManager<T extends Entity> {
    private static final Log log = LogFactory.getLog(AbstractEntityManager.class);

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

    public T lookupEntityById(final long id){
        return this.entitiesById.get(id);
    }

    protected abstract T createEntity();

    public T createAndRegisterEntity() {
        final T newEntity = this.createEntity();
        newEntity.setId(nextId++);
        this.entities.add(newEntity);
        this.addEntityToIdMap(newEntity);
        return newEntity;
    }

    /**
     * Removes the entity from the list of registered entities.
     *
     * @param entity
     * @return true iff the model contained the element, false otherwise
     * @throws IllegalStateException if an inconsistent state is detected
     */
    public boolean removeEntity(T entity){
        T removedEntity = this.entitiesById.remove(entity.getId());
        if (removedEntity != entity){
            IllegalStateException ise =
                    new IllegalStateException("Unexpected element removed with id" +
                    entity.getId()+". Expected: " + entity + " ; removed: " + removedEntity);
            log.error("IllegalStateException: " + ise.getMessage(), ise);
            throw ise;
        }

        return this.entities.remove(entity);
    }

    /**
     * Adds a newly created component to the local tables.
     */
    private void addEntityToIdMap(T componentType){
        this.entitiesById.put(componentType.getId(), componentType);
    }
}
