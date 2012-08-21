package org.trustsoft.slastic.simulation.model.software.repository;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.trustsoft.slastic.simulation.model.ModelManager;

import reconfMM.ReconfigurationModel;
import de.uka.ipd.sdq.pcm.repository.BasicComponent;
import de.uka.ipd.sdq.pcm.repository.DataType;
import de.uka.ipd.sdq.pcm.repository.PassiveResource;
import de.uka.ipd.sdq.pcm.repository.ProvidesComponentType;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.repository.Signature;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingBehaviour;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingSEFF;
import de.uka.ipd.sdq.pcm.seff.ServiceEffectSpecification;
import desmoj.core.simulator.Model;

/**
 * 
 * @author Robert von Massow
 * 
 */
public final class ComponentTypeController {

	private static volatile ComponentTypeController instance;
	// = new
	// ComponentController();
	private final Hashtable<String, ProvidesComponentType> components = new Hashtable<String, ProvidesComponentType>();
	private final Hashtable<BasicComponent, Hashtable<Signature, ResourceDemandingSEFF>> seffsByComponent = new Hashtable<BasicComponent, Hashtable<Signature, ResourceDemandingSEFF>>();
	private final Hashtable<BasicComponent, Hashtable<String, PassiveResource>> passiveResByComponent = new Hashtable<BasicComponent, Hashtable<String, PassiveResource>>();
	private final Hashtable<String, ResourceDemandingSEFF> seffsByServiceName = new Hashtable<String, ResourceDemandingSEFF>();
	private List<DataType> types;
	private final Log log;

	public ComponentTypeController(final Repository repository,
			final ReconfigurationModel reconfModel, final Model model) {
		this.log = ModelManager.getInstance().getLogger();
		for (final ProvidesComponentType component : repository.getComponents__Repository()) {
			this.log.info("Adding Component " + component + " to simulation model");
			this.put(component, reconfModel);
			if (component instanceof BasicComponent) {
				final BasicComponent bc = (BasicComponent) component;
				this.passiveResByComponent.put(bc,
						new Hashtable<String, PassiveResource>());
				for (final PassiveResource res : bc
						.getPassiveResource_BasicComponent()) {
					this.passiveResByComponent.get(bc).put(res.getEntityName(), res);
				}
			}
		}
		for (final DataType dataType : repository.getDatatypes_Repository()) {
			this.put(dataType);
		}
		ComponentTypeController.instance = this;
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
		this.components.put(pct.getEntityName(), pct);
		if (pct instanceof BasicComponent) {
			final BasicComponent bc = (BasicComponent) pct;
			for (final ServiceEffectSpecification seff : bc.getServiceEffectSpecifications__BasicComponent()) {
				if (seff instanceof ResourceDemandingSEFF) {
					final ResourceDemandingSEFF rdseff = (ResourceDemandingSEFF) seff;
					this.log.info("Adding RDSEFF " + rdseff + " for service " + rdseff.getDescribedService__SEFF().getServiceName());
					if (this.seffsByComponent.get(bc) == null) {
						this.seffsByComponent.put(bc, new Hashtable<Signature, ResourceDemandingSEFF>());
					}
					this.seffsByComponent.get(bc).put(rdseff.getDescribedService__SEFF(), rdseff);
					this.seffsByServiceName.put(rdseff.getDescribedService__SEFF().getServiceName(), rdseff);
				}
			}

		}
	}

	public void put(final DataType datatype) {
		this.types.add(datatype);
	}

	public static ComponentTypeController getInstance() {
		return ComponentTypeController.instance;
	}

	public ResourceDemandingBehaviour getSeffById(final String serviceName) {
		return this.seffsByServiceName.get(serviceName);
	}

	public Set<String> getSeffs() {
		return this.seffsByServiceName.keySet();
	}

	public String getComponentNameById(final String id) {
		return this.components.get(id).getEntityName();
	}

	public final Hashtable<String, PassiveResource> getPassiveResByComponent(final BasicComponent bc) {
		return this.passiveResByComponent.get(bc);
	}

	public final PassiveResource getPassiveResByName(final BasicComponent bc, final String name) {
		final Hashtable<String, PassiveResource> comp = this.passiveResByComponent.get(bc);
		if (comp != null) {
			return comp.get(name);
		}
		return null;
	}
}
