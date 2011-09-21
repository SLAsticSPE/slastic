package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.util.SignatureUtils;

import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.Interface;
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

	private static final Log log = LogFactory.getLog(ComponentTypesManager.class);

	/**
	 * 
	 * @param componentTypes
	 */
	public ComponentTypesManager(final List<ComponentType> componentTypes) {
		super(componentTypes);
		// TODO: To fix the problem below, we should now determine the
		// highest operation id existent in the model.
	}

	// TODO: I'm pretty sure we'll have problems with this when starting with a
	// deserialized model
	private volatile long nextOperationId = 1;

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
		res.setId(this.nextOperationId++);

		final Signature signature = SignatureUtils.createSignature(operationName, argTypes, returnType);
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

	@Override
	public void registerProvidedInterface(final ComponentType componentType, final Interface providedInterface) {
		final EList<Interface> providedInterfaces = componentType.getProvidedInterfaces();

		if (providedInterfaces.contains(providedInterface)) {
			ComponentTypesManager.log.warn("Interface " + providedInterface
					+ " already contained in list of interfaces provided by " + componentType);
		} else {
			providedInterfaces.add(providedInterface);
		}
	}

	@Override
	public void registerRequiredInterface(final ComponentType componentType, final Interface requiredInterface) {
		final EList<Interface> requiredInterfaces = componentType.getRequiredInterfaces();

		if (requiredInterfaces.contains(requiredInterface)) {
			ComponentTypesManager.log.warn("Interface " + requiredInterface
					+ " already contained in list of interfaces required by " + componentType);
		} else {
			requiredInterfaces.add(requiredInterface);
		}
	}
}