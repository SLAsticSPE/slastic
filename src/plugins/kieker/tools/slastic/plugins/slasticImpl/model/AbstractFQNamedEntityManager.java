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

package kieker.tools.slastic.plugins.slasticImpl.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kieker.tools.slastic.metamodel.core.FQNamedEntity;

/**
 * 
 * @author Andre van Hoorn
 */
public abstract class AbstractFQNamedEntityManager<T extends FQNamedEntity>
		extends AbstractEntityManager<T> {

	private final Map<String, T> entitiesByFullyQualifiedName = new HashMap<String, T>();

	public AbstractFQNamedEntityManager(final List<T> entities) {
		super(entities);
		for (final T entity : entities) {
			this.addEntitiyToNameMap(entity);
		}
	}

	public T lookup(final String fullyQualifiedName) {
		return this.entitiesByFullyQualifiedName.get(fullyQualifiedName);
	}

	@Override
	protected abstract T createEntity();

	public T createAndRegister(final String fullyQualifiedName) {
		if (this.entitiesByFullyQualifiedName.containsKey(fullyQualifiedName)) {
			throw new IllegalArgumentException("Element with name "
					+ fullyQualifiedName + " exists already");
		}
		final T newEntity = super.createAndRegisterEntity();
		final String[] fqnSplit =
				NameUtils.splitFullyQualifiedName(fullyQualifiedName);
		newEntity.setPackageName(fqnSplit[0]);
		newEntity.setName(fqnSplit[1]);
		this.addEntitiyToNameMap(newEntity);
		return newEntity;
	}

	protected void assignNameIdAndRegister(final T entity,
			final String fullyQualifiedName) {
		if (this.entitiesByFullyQualifiedName.containsKey(fullyQualifiedName)) {
			throw new IllegalArgumentException("Element with name "
					+ fullyQualifiedName + "exists already");
		}
		super.assignIdAndRegister(entity);
		final String[] fqnSplit = NameUtils.splitFullyQualifiedName(fullyQualifiedName);
		entity.setPackageName(fqnSplit[0]);
		entity.setName(fqnSplit[1]);
		this.addEntitiyToNameMap(entity);
	}

	/**
	 * Adds a newly created component to the local tables.
	 */
	private void addEntitiyToNameMap(final T entity) {
		if (entity.getPackageName().isEmpty()) {
			/* no package name */
			this.entitiesByFullyQualifiedName.put(entity.getName(), entity);
		} else {
			/* separate package name and type name by '.' */
			this.entitiesByFullyQualifiedName.put(entity.getPackageName() + "."
					+ entity.getName(), entity);
		}
	}
}
