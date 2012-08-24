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

package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import java.util.List;

import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;

import de.cau.se.slastic.metamodel.typeRepository.NetworkLinkType;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;

/**
 * 
 * @author Andre van Hoorn
 */
public class NetworkLinkTypesManager extends AbstractFQNamedEntityManager<NetworkLinkType> implements INetworkLinkTypesManager {
	public NetworkLinkTypesManager(final List<NetworkLinkType> componentTypes) {
		super(componentTypes);
	}

	@Override
	public NetworkLinkType lookupNetworkLinkType(final String fullyQualifiedName) {
		return this.lookup(fullyQualifiedName);
	}

	@Override
	public NetworkLinkType lookupNetworkLinkType(final long id) {
		return this.lookupEntityById(id);
	}

	@Override
	public NetworkLinkType createAndRegisterNetworkLinkType(final String fullyQualifiedName) {
		return this.createAndRegister(fullyQualifiedName);
	}

	@Override
	protected NetworkLinkType createEntity() {
		return TypeRepositoryFactory.eINSTANCE.createNetworkLinkType();
	}
}
