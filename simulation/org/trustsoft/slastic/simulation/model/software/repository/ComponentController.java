package org.trustsoft.slastic.simulation.model.software.repository;

import java.util.Hashtable;
import java.util.List;

import reconfMM.ReconfigurationModel;
import de.uka.ipd.sdq.pcm.repository.BasicComponent;
import de.uka.ipd.sdq.pcm.repository.DataType;
import de.uka.ipd.sdq.pcm.repository.ProvidesComponentType;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.repository.Signature;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingBehaviour;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingSEFF;
import de.uka.ipd.sdq.pcm.seff.ServiceEffectSpecification;
import desmoj.core.simulator.Model;

public class ComponentController {

	private static ComponentController instance;
	// = new
	// ComponentController();
	private final Hashtable<String, ProvidesComponentType> components = new Hashtable<String, ProvidesComponentType>();
	private final Hashtable<BasicComponent, Hashtable<Signature, ResourceDemandingSEFF>> seffsByComponent = new Hashtable<BasicComponent, Hashtable<Signature, ResourceDemandingSEFF>>();
	private final Hashtable<String, ResourceDemandingSEFF> seffsByServiceName = new Hashtable<String, ResourceDemandingSEFF>();
	private List<DataType> types;
	private final Model model;

	public ComponentController(final Repository repository,
			final ReconfigurationModel reconfModel, final Model model) {
		for (final ProvidesComponentType component : repository
				.getComponents__Repository()) {
			this.put(component, reconfModel);
		}
		for (final DataType dataType : repository.getDatatypes_Repository()) {
			this.put(dataType);
		}
		this.model = model;
		instance = this;
	}

	/**
	 * get some data from a component and put it in a dictionary for later use
	 * 
	 * @param entityName
	 * @param pct
	 * @param reconfModel
	 */
	private void put(final ProvidesComponentType pct,
			final ReconfigurationModel reconfModel) {
		components.put(pct.getEntityName(), pct);
		if (components instanceof BasicComponent) {
			final BasicComponent bc = (BasicComponent) components;
			for (final ServiceEffectSpecification seff : bc
					.getServiceEffectSpecifications__BasicComponent()) {
				if (seff instanceof ResourceDemandingSEFF) {
					final ResourceDemandingSEFF rdseff = (ResourceDemandingSEFF) seff;
					if (seffsByComponent.get(bc) == null) {
						seffsByComponent
								.put(
										bc,
										new Hashtable<Signature, ResourceDemandingSEFF>());
					}
					seffsByComponent.get(bc).put(
							rdseff.getDescribedService__SEFF(), rdseff);
					seffsByServiceName.put(rdseff.getDescribedService__SEFF()
							.getServiceName(), rdseff);
				}
			}

		}
	}

	public void put(final DataType datatype) {
		types.add(datatype);
	}

	public static ComponentController getInstance() {
		return instance;
	}

	public ResourceDemandingBehaviour getSeffById(final String seff) {
		return seffsByServiceName.get(seff);
	}

}
