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

package kieker.tools.slastic.plugins.slasticImpl.model.typeRepository;

import java.util.List;

import kieker.tools.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;
import kieker.tools.slastic.plugins.slasticImpl.model.NameUtils;

import kieker.tools.slastic.metamodel.typeRepository.ConnectorType;
import kieker.tools.slastic.metamodel.typeRepository.Interface;
import kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class ConnectorTypesManager extends AbstractFQNamedEntityManager<ConnectorType> implements IConnectorTypesManager {
	public final static String CONNECTOR_RND_NAME_PREFIX = "CONNECTOR_T_";
	
    public ConnectorTypesManager(final List<ConnectorType> connectorTypes){
        super(connectorTypes);
    }

    @Override
    public ConnectorType createAndRegisterConnectorType(
            final String fullyQualifiedName, final Interface iface) {
    	final ConnectorType ct = this.createAndRegister(fullyQualifiedName);
    	ct.setInterface(iface);
    	return ct;
    }


	@Override
	public ConnectorType createAndRegisterConnectorType(final Interface iface) {
		final String rndName = NameUtils.createUniqueName(ConnectorTypesManager.CONNECTOR_RND_NAME_PREFIX);

		return this.createAndRegisterConnectorType(rndName, iface);
	}
    
    @Override
    protected ConnectorType createEntity() {
        return TypeRepositoryFactory.eINSTANCE.createConnectorType();
    }
    
    @Override
    public ConnectorType lookupConnectorType(
            final String fullyQualifiedName) {
        return this.lookup(fullyQualifiedName);
    }

    @Override
    public ConnectorType lookupConnectorType(final long id){
        return this.lookupEntityById(id);
    }
}
