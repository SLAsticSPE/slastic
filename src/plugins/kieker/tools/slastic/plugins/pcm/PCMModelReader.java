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

package kieker.tools.slastic.plugins.pcm;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import kieker.tools.slastic.plugins.pcm.control.PCMModelSet;
import kieker.tools.slastic.plugins.slasticImpl.ModelIOUtils;
import kieker.tools.slastic.plugins.starter.MyURIConverterImpl;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.system.System;

/**
 * 
 * @author Andre van Hoorn
 */
public class PCMModelReader extends ModelIOUtils {

	private static final Log LOG = LogFactory.getLog(PCMModelReader.class);

	public static Repository readRepository(final String model_fn, final ResourceSet resourceSet) throws IOException {
		// return (Repository) this.readXMIModel(model_fn, de.uka.ipd.sdq.pcm.repository.RepositoryPackage.class.getName());
		return (Repository) ModelIOUtils.loadModel(de.uka.ipd.sdq.pcm.repository.RepositoryPackage.eINSTANCE, model_fn, resourceSet);
	}

	public static void storeRepository(final Repository repos, final String model_fn, final ResourceSet resourceSet) throws IOException {
		ModelIOUtils.saveModels(new EObject[] { repos }, new String[] { model_fn });
	}

	public static System readSystem(final String model_fn, final ResourceSet resourceSet) throws IOException {
		// return (System) this.readXMIModel(model_fn, de.uka.ipd.sdq.pcm.system.SystemPackage.class.getName());
		return (System) ModelIOUtils.loadModel(de.uka.ipd.sdq.pcm.system.SystemPackage.eINSTANCE, model_fn, resourceSet);
	}

	public static void storeSystem(final System system, final String model_fn, final ResourceSet resourceSet) throws IOException {
		ModelIOUtils.saveModels(new EObject[] { system }, new String[] { model_fn });
	}

	public static Allocation readAllocation(final String model_fn, final ResourceSet resourceSet) throws IOException {
		// return (Allocation) this.readXMIModel(model_fn, de.uka.ipd.sdq.pcm.allocation.AllocationPackage.class.getName());
		return (Allocation) ModelIOUtils.loadModel(de.uka.ipd.sdq.pcm.allocation.AllocationPackage.eINSTANCE, model_fn, resourceSet);
	}

	public static void storeAllocation(final Allocation allocation, final String model_fn, final ResourceSet resourceSet) throws IOException {
		ModelIOUtils.saveModels(new EObject[] { allocation }, new String[] { model_fn });
	}

	public static ResourceEnvironment readResourceEnvironment(final String model_fn, final ResourceSet resourceSet) throws IOException {
		// return (ResourceEnvironment) this.readXMIModel(model_fn, de.uka.ipd.sdq.pcm.resourceenvironment.ResourceenvironmentPackage.class.getName());
		return (ResourceEnvironment) ModelIOUtils.loadModel(de.uka.ipd.sdq.pcm.resourceenvironment.ResourceenvironmentPackage.eINSTANCE, model_fn, resourceSet);
	}

	public static void storeResourceEnvironment(final ResourceEnvironment resEnv, final String model_fn) throws IOException {
		ModelIOUtils.saveModels(new EObject[] { resEnv }, new String[] { model_fn });
	}

	public static PCMModelSet readPCMModel(
			final String pcmRespositoryModel_fn,
			final String pcmSystemModel_fn,
			final String pcmResourceEnvironmentModel_fn,
			final String pcmAllocationModel_fn) throws IOException {
		final PCMModelSet pcmModel = new PCMModelSet();
		final ResourceSet set = new ResourceSetImpl();
		set.setURIConverter(new MyURIConverterImpl(new String[] { pcmRespositoryModel_fn, pcmSystemModel_fn, pcmAllocationModel_fn, pcmResourceEnvironmentModel_fn }));
		final Repository pcmRepository = PCMModelReader.readRepository(pcmRespositoryModel_fn, set);
		if (pcmRepository == null) {
			LOG.error("Failed to read PCM repository from file '" + pcmRespositoryModel_fn + "'");
			throw new IllegalArgumentException("Failed to read PCM repository from file '" + pcmRespositoryModel_fn + "'");
		} else {
			pcmModel.setPCMRepository(pcmRepository);
			LOG.info("Loaded PCM repository model: " + pcmRepository.getEntityName());
		}
		final System pcmSystem = PCMModelReader.readSystem(
				pcmSystemModel_fn, set);
		if (pcmSystem == null) {
			LOG.error("Failed to read PCM system from file '" + pcmSystemModel_fn + "'");
			throw new IllegalArgumentException("Failed to read PCM system from file '" + pcmSystemModel_fn + "'");
		} else {
			pcmModel.setPCMSystem(pcmSystem);
			LOG.info("Loaded PCM system model: " + pcmSystem.getEntityName());
		}
		final Allocation pcmAllocation = PCMModelReader.readAllocation(pcmAllocationModel_fn, set);
		if (pcmAllocation == null) {
			LOG.error("Failed to read PCM allocation from file '" + pcmAllocationModel_fn + "'");
			throw new IllegalArgumentException("Failed to read PCM allocation from file '" + pcmAllocationModel_fn + "'");
		} else {
			pcmModel.setPCMAllocation(pcmAllocation);
			LOG.info("Loaded PCM allocation model: " + pcmAllocation.getEntityName());
		}
		final ResourceEnvironment pcmResourceEnvironment = PCMModelReader.readResourceEnvironment(pcmResourceEnvironmentModel_fn, set);
		if (pcmResourceEnvironment == null) {
			LOG.error("Failed to read PCM resource environment from file '" + pcmResourceEnvironmentModel_fn + "'");
			throw new IllegalArgumentException("Failed to read PCM resource environment from file '" + pcmResourceEnvironmentModel_fn + "'");
		} else {
			pcmModel.setPCMResourceEnvironment(pcmResourceEnvironment);
			LOG.info("Loaded PCM resource environment model: " + pcmAllocation.getEntityName());
		}

		final Map<URI, URI> ma = set.getURIConverter().getURIMap();
		for (final URI uri : ma.keySet()) {
			LOG.info(uri + " => " + ma.get(uri));
		}
		EcoreUtil.resolveAll(set);
		final Map<EObject, Collection<Setting>> m = EcoreUtil.UnresolvedProxyCrossReferencer.find(set);
		for (final EObject o : m.keySet()) {
			LOG.warn(o + " " + m.get(o));
		}

		return pcmModel;
	}
}
