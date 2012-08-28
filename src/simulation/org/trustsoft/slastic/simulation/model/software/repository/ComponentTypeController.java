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

package org.trustsoft.slastic.simulation.model.software.repository;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

	private static final Log LOG = LogFactory.getLog(ComponentTypeController.class);

	private static volatile ComponentTypeController INSTANCE;

	private final Hashtable<String, ProvidesComponentType> componentsByName = new Hashtable<String, ProvidesComponentType>();

	private final Hashtable<BasicComponent, Hashtable<Signature, ResourceDemandingSEFF>> seffsByComponent = new Hashtable<BasicComponent, Hashtable<Signature, ResourceDemandingSEFF>>();

	// TODO: This mainly concerns access to this map:
	// Should we use more sophisticated service IDs than just the opname? I expect we might
	// run into trouble having multiple services/operations with the same name.
	// For example, we might want to include the component (type) name in the id
	//
	// TODO: Note also that we also have the map 'seffsByComponent'
	private final Hashtable<String, ResourceDemandingSEFF> seffsByServiceName = new Hashtable<String, ResourceDemandingSEFF>();

	private final Hashtable<BasicComponent, Hashtable<String, PassiveResource>> passiveResByComponent = new Hashtable<BasicComponent, Hashtable<String, PassiveResource>>();

	private List<DataType> types;

	public ComponentTypeController(final Repository repository, final ReconfigurationModel reconfModel, final Model model) {
		for (final ProvidesComponentType component : repository.getComponents__Repository()) {
			LOG.info("Adding Component " + component + " to simulation model");
			this.put(component, reconfModel);
			if (component instanceof BasicComponent) {
				final BasicComponent bc = (BasicComponent) component;
				this.passiveResByComponent.put(bc, new Hashtable<String, PassiveResource>());
				for (final PassiveResource res : bc.getPassiveResource_BasicComponent()) {
					this.passiveResByComponent.get(bc).put(res.getEntityName(), res);
				}
			}
		}
		for (final DataType dataType : repository.getDatatypes_Repository()) {
			this.put(dataType);
		}
		ComponentTypeController.INSTANCE = this;
	}

	/**
	 * get some data from a component and put it in a dictionary for later use
	 * 
	 * @param entityName
	 * @param pct
	 * @param reconfModel
	 */
	private void put(final ProvidesComponentType pct, final ReconfigurationModel reconfModel) {
		this.componentsByName.put(pct.getEntityName(), pct);
		if (pct instanceof BasicComponent) {
			final BasicComponent bc = (BasicComponent) pct;
			for (final ServiceEffectSpecification seff : bc.getServiceEffectSpecifications__BasicComponent()) {
				if (seff instanceof ResourceDemandingSEFF) {
					final ResourceDemandingSEFF rdseff = (ResourceDemandingSEFF) seff;
					LOG.info("Adding RDSEFF " + rdseff + " for service " + rdseff.getDescribedService__SEFF().getServiceName());
					if (this.seffsByComponent.get(bc) == null) {
						this.seffsByComponent.put(bc, new Hashtable<Signature, ResourceDemandingSEFF>());
					}
					this.seffsByComponent.get(bc).put(rdseff.getDescribedService__SEFF(), rdseff);
					this.seffsByServiceName.put(rdseff.getDescribedService__SEFF().getServiceName(), rdseff);
				}
			}

		} else {
			// TODO: support other specializations of ProvidesComponentType?
			LOG.error("Unsupported component (meta-)type" + pct.getClass());
		}
	}

	public void put(final DataType datatype) {
		this.types.add(datatype);
	}

	public static ComponentTypeController getInstance() {
		return ComponentTypeController.INSTANCE;
	}

	// TODO: This mainly concerns callers of this method:
	// Should we use more sophisticated service IDs than just the opname? I expect we might
	// run into trouble having multiple services/operations with the same name.
	// For example, we might want to include the component (type) name in the id
	//
	// TODO: Note also that we also have the map 'seffsByComponent'
	public ResourceDemandingBehaviour getSeffById(final String serviceName) {
		return this.seffsByServiceName.get(serviceName);
	}

	public Set<String> getSeffs() {
		return this.seffsByServiceName.keySet();
	}

	public String getComponentNameById(final String id) {
		return this.componentsByName.get(id).getEntityName();
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
