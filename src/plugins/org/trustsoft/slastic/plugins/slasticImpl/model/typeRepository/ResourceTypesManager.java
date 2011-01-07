package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import java.util.List;

import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;

import de.cau.se.slastic.metamodel.typeRepository.ResourceType;
import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.CPUType;
import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.GenericResourceType;
import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.MemSwapType;
import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.ResourceTypesFactory;

/**
 * 
 * @author Andre van Hoorn
 */
public class ResourceTypesManager extends
		AbstractFQNamedEntityManager<ResourceType> implements
		IResourceTypesManager {
	public ResourceTypesManager(final List<ResourceType> resourceTypes) {
		super(resourceTypes);
	}

	@Override
	public ResourceType lookupResourceType(final String fullyQualifiedName) {
		return this.lookup(fullyQualifiedName);
	}

	@Override
	public ResourceType lookupResourceType(final long id) {
		return this.lookupEntityById(id);
	}

	@Override
	public GenericResourceType createAndRegisterGenericResourceType(
			final String fullyQualifiedName) {
		final GenericResourceType resT = this.createGenericResourceType();
		this.assignNameIdAndRegister(resT, fullyQualifiedName);
		return resT;
	}

	@Override
	public MemSwapType createAndRegisterMemSwapResourceType(
			final String fullyQualifiedName) {
		final MemSwapType resT = this.createMemSwapResourceType();
		this.assignNameIdAndRegister(resT, fullyQualifiedName);
		return resT;
	}

	@Override
	public CPUType createAndRegisterCPUResourceType(
			final String fullyQualifiedName) {
		final CPUType resT = this.createCPUResourceType();
		this.assignNameIdAndRegister(resT, fullyQualifiedName);
		return resT;
	}

	/**
	 * Returns a {@link GenericResourceType}
	 */
	@Override
	protected ResourceType createEntity() {
		return this.createGenericResourceType();
	}

	private GenericResourceType createGenericResourceType() {
		return ResourceTypesFactory.eINSTANCE.createGenericResourceType();
	}

	private MemSwapType createMemSwapResourceType() {
		return ResourceTypesFactory.eINSTANCE.createMemSwapType();
	}

	private CPUType createCPUResourceType() {
		return ResourceTypesFactory.eINSTANCE.createCPUType();
	}
}
