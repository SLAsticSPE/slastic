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

import java.util.Arrays;
import java.util.List;

import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.util.SignatureUtils;

import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.Signature;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;

/**
 * 
 * @author Andre van Hoorn
 */
public class InterfacesManager extends AbstractFQNamedEntityManager<Interface> implements IInterfacesManager {

	/**
	 * 
	 * @param interfaces
	 * @param componentTypeManager
	 */
	public InterfacesManager(final List<Interface> interfaces) {
		super(interfaces);
	}

	@Override
	public Interface lookupInterface(final String fullyQualifiedName) {
		return this.lookup(fullyQualifiedName);
	}

	@Override
	public Interface lookupInterface(final long id) {
		return this.lookupEntityById(id);
	}

	@Override
	public Interface createAndRegisterInterface(final String fullyQualifiedName) {
		return this.createAndRegister(fullyQualifiedName);
	}

	@Override
	protected Interface createEntity() {
		return TypeRepositoryFactory.eINSTANCE.createInterface();
	}

	@Override
	public Signature lookupSignature(final Interface iface, final String signatureName, final String returnType, final String[] argTypes) {
		for (final Signature signature : iface.getSignatures()) {
			// compare operation name
			if (!signature.getName().equals(signatureName)) {
				continue;
			}
			// compare return type
			if (!signature.getReturnType().equals(returnType)) {
				continue;
			}
			// compare argument types
			if (!Arrays.equals(signature.getParamTypes().toArray(), argTypes)) {
				continue;
			}
			/*
			 * if we reached this position, the current signature matches the
			 * requested signature
			 */
			return signature;
		}

		// no matching signature
		return null;
	}

	@Override
	public Signature createAndRegisterSignature(final Interface iface, final String signatureName, final String returnType, final String[] argTypes) {
		Signature res = this.lookupSignature(iface, signatureName, returnType, argTypes);

		if (res != null) {
			throw new IllegalArgumentException("Signature with given properties already registered: " + res);
		}

		// Create and register operation
		res = SignatureUtils.createSignature(signatureName, argTypes, returnType);
		iface.getSignatures().add(res);

		return res;
	}
}
