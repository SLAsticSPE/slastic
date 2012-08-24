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

package org.trustsoft.slastic.plugins.slasticImpl.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.cau.se.slastic.metamodel.core.Entity;

/**
 * 
 * @author Andre van Hoorn
 */
// TODO: requires synchronization / thread-safety
public abstract class AbstractEntityManager<T extends Entity> {
	private static final Log LOG = LogFactory.getLog(AbstractEntityManager.class);

	/**
	 * On initialization, this value will be incremented according to the
	 * current maximum value
	 */
	private volatile long nextId = -1;

	private final Map<Long, T> entitiesById = new HashMap<Long, T>();
	private final List<T> entities;

	/**
	 * Must not be used for construction.
	 */
	@SuppressWarnings("unused")
	private AbstractEntityManager() {
		this.entities = null;
		this.nextId = 0;
	}

	public AbstractEntityManager(final List<T> entities) {
		this.entities = entities;
		long maxId = this.nextId;
		for (final T entity : entities) {
			if (entity.getId() > maxId) {
				maxId = entity.getId();
			}
			this.addEntityToIdMap(entity);
		}
		this.nextId = maxId + 1; // 0 if entities is empty
	}

	public T lookupEntityById(final long id) {
		return this.entitiesById.get(id);
	}

	protected abstract T createEntity();

	/**
	 * By default, entities have the status active.
	 * 
	 * @return
	 */
	public T createAndRegisterEntity() {
		final T newEntity = this.createEntity();
		this.assignIdAndRegister(newEntity);
		this.assignStatus(newEntity, true);
		return newEntity;
	}

	public void assignStatus(final T entity, final boolean active) {
		entity.setActive(true);
	}

	protected void assignIdAndRegister(final T entity) {
		entity.setId(this.nextId++);
		this.entities.add(entity);
		this.addEntityToIdMap(entity);
	}

	/**
	 * Removes the entity from the list of registered entities.
	 * 
	 * TODO: currently, we only set {@link Entity#setActive(boolean)} to true
	 * rather than permanently removing the entity.
	 * 
	 * @param entity
	 * @return true iff the model contained the element, false otherwise
	 * @throws IllegalStateException
	 *             if an inconsistent state is detected
	 */
	public boolean removeEntity(final T entity) {
		// final T removedEntity = this.entitiesById.remove(entity.getId());
		// if (removedEntity != entity) {
		// final IllegalStateException ise =
		// new IllegalStateException(
		// "Unexpected element removed with id"
		// + entity.getId() + ". Expected: " + entity
		// + " ; removed: " + removedEntity);
		// AbstractEntityManager.log.error(
		// "IllegalStateException: " + ise.getMessage(), ise);
		// throw ise;
		// }
		//
		// return this.entities.remove(entity);
		AbstractEntityManager.LOG.info("Setting entity's activity to false: " + entity);
		entity.setActive(false);
		return true;
	}

	/**
	 * Returns the list of entities. The returned collection is a newly created
	 * list and may be modified; the contained object, however, are the original
	 * ones.
	 * 
	 * @return
	 */
	public Collection<T> getEntities() {
		final Collection<T> retList = new ArrayList<T>();
		retList.addAll(this.entities);
		return retList;
	}

	/**
	 * Adds a newly created component to the local tables.
	 */
	private void addEntityToIdMap(final T componentType) {
		this.entitiesById.put(componentType.getId(), componentType);
	}
}
