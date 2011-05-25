package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import java.util.Arrays;
import java.util.List;

import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;

import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.Operation;
import de.cau.se.slastic.metamodel.typeRepository.Signature;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;

/**
 * 
 * @author Andre van Hoorn
 */
public class ComponentTypesManager extends
		AbstractFQNamedEntityManager<ComponentType> implements
		IComponentTypesManager {
	public ComponentTypesManager(final List<ComponentType> componentTypes) {
		super(componentTypes);
	}

	// TODO: I'm pretty sure we'll have problems with this when starting with a
	// deserialized model
	private volatile long nextId = 1;

	@Override
	public ComponentType lookupComponentType(final String fullyQualifiedName) {
		return this.lookup(fullyQualifiedName);
	}

	@Override
	public ComponentType lookupComponentType(final long id) {
		return this.lookupEntityById(id);
	}

	@Override
	public ComponentType createAndRegisterComponentType(
			final String fullyQualifiedName) {
		return this.createAndRegister(fullyQualifiedName);
	}

	@Override
	protected ComponentType createEntity() {
		return TypeRepositoryFactory.eINSTANCE.createComponentType();
	}

	@Override
	public Operation createAndRegisterOperation(
			final ComponentType componentType, final String operationName,
			final String returnType, final String[] argTypes) {
		Operation res =
				this.lookupOperation(componentType, operationName, returnType,
						argTypes);

		if (res != null) {
			throw new IllegalArgumentException(
					"Operation with given properties already registered: "
							+ res);
		}

		// Create and register operation
		res = TypeRepositoryFactory.eINSTANCE.createOperation();
		res.setActive(true); // TODO: required?
		res.setComponentType(componentType);
		res.setId(this.nextId++);
		final Signature signature =
				TypeRepositoryFactory.eINSTANCE.createSignature();
		signature.setName(operationName);
		signature.setReturnType(returnType);

		for (final String paramType : argTypes) {
			signature.getParamTypes().add(paramType);
		}

		res.setSignature(signature);

		return res;
	}

	@Override
	public Operation lookupOperation(final ComponentType componentType,
			final String operationName, final String returnType,
			final String[] argTypes) {
		for (final Operation op : componentType.getOperations()) {
			final Signature signature = op.getSignature();
			// compare operation name
			if (!signature.getName().equals(operationName)) {
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
			 * if we reached this position, the current operation matches the
			 * requested operation
			 */
			return op;
		}

		// no matching operation
		return null;
	}
}