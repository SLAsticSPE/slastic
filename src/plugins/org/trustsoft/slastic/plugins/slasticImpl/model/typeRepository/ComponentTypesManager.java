package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

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

	private final InterfacesManager interfacesManager;
	
	/**
	 * 
	 * @param componentTypes
	 * @param interfaceManager 
	 */
	public ComponentTypesManager(final List<ComponentType> componentTypes, final InterfacesManager interfaceManager) {
		super(componentTypes);
		this.interfacesManager = interfaceManager;
		// TODO: To fix the problem below, we should now determine the
		// highest operation id existent in the model.
	}

	// TODO: I'm pretty sure we'll have problems with this when starting with a
	// deserialized model
	private volatile long nextOperationId = 1;

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
	public ComponentType lookupComponentType(final String fullyQualifiedName) {
		return this.lookup(fullyQualifiedName);
	}

	@Override
	public ComponentType lookupComponentType(final long id) {
		return this.lookupEntityById(id);
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
		final Signature argSignature = SignatureUtils.createSignature(operationName, argTypes, returnType);
		
		for (final Operation op : componentType.getOperations()) {
			if (SignatureUtils.signaturesEqual(argSignature, op.getSignature())) {
				return op;
			}
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
	
	// TODO: need a test for this method
	@Override
	public Interface lookupProvidedInterfaceForSignature(final ComponentType componentType, final Signature signature) {
		final List<Interface> providedInterfaces = componentType.getProvidedInterfaces();
		for (final Interface iface : providedInterfaces) {
			final Signature lookedupSignature = this.interfacesManager.lookupSignature(iface, signature.getName(), signature.getReturnType(), signature.getParamTypes().toArray(new String[]{}));
			if (lookedupSignature != null) {
				return iface;
			}
		}
		// no matching interface found
		return null;
	}

	// TODO: need a test for this method
	@Override
	public Interface lookupRequiredInterfaceForSignature(final ComponentType componentType, final Signature signature) {
		final List<Interface> requiredInterfaces = componentType.getProvidedInterfaces();
		for (final Interface iface : requiredInterfaces) {
			final Signature lookedupSignature = this.interfacesManager.lookupSignature(iface, signature.getName(), signature.getReturnType(), signature.getParamTypes().toArray(new String[]{}));
			if (lookedupSignature != null) {
				return iface;
			}
		}
		// no matching interface found
		return null;
	}
}