package org.trustsoft.slastic.plugins.pcm;

import java.io.IOException;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.trustsoft.slastic.plugins.slasticImpl.ModelIOUtils;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.system.System;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.trustsoft.slastic.plugins.pcm.control.PCMModelSet;
import org.trustsoft.slastic.plugins.starter.MyURIConverterImpl;

/**
 *
 * @author Andre van Hoorn
 */
public class PCMModelReader extends ModelIOUtils {

    private static final Log log = LogFactory.getLog(PCMModelReader.class);

    public static Repository readRepository(final String model_fn, final ResourceSet resourceSet) throws IOException {
        //return (Repository) this.readXMIModel(model_fn, de.uka.ipd.sdq.pcm.repository.RepositoryPackage.class.getName());
        return (Repository) ModelIOUtils.loadModel(de.uka.ipd.sdq.pcm.repository.RepositoryPackage.eINSTANCE, model_fn, resourceSet);
    }

    public static void storeRepository(final Repository repos, final String model_fn, final ResourceSet resourceSet) throws IOException {
        ModelIOUtils.saveModel(repos, model_fn);
    }

    public static System readSystem(final String model_fn, final ResourceSet resourceSet) throws IOException {
        //return (System) this.readXMIModel(model_fn, de.uka.ipd.sdq.pcm.system.SystemPackage.class.getName());
        return (System) ModelIOUtils.loadModel(de.uka.ipd.sdq.pcm.system.SystemPackage.eINSTANCE, model_fn, resourceSet);
    }

    public static void storeSystem(final System system, final String model_fn, final ResourceSet resourceSet) throws IOException {
        ModelIOUtils.saveModel(system, model_fn);
    }

    public static Allocation readAllocation(final String model_fn, final ResourceSet resourceSet) throws IOException {
        //return (Allocation) this.readXMIModel(model_fn, de.uka.ipd.sdq.pcm.allocation.AllocationPackage.class.getName());
        return (Allocation) ModelIOUtils.loadModel(de.uka.ipd.sdq.pcm.allocation.AllocationPackage.eINSTANCE, model_fn, resourceSet);
    }

    public static void storeAllocation(final Allocation allocation, final String model_fn, final ResourceSet resourceSet) throws IOException {
        ModelIOUtils.saveModel(allocation, model_fn);
    }

    public static ResourceEnvironment readResourceEnvironment(final String model_fn, final ResourceSet resourceSet) throws IOException {
        //return (ResourceEnvironment) this.readXMIModel(model_fn, de.uka.ipd.sdq.pcm.resourceenvironment.ResourceenvironmentPackage.class.getName());
        return (ResourceEnvironment) ModelIOUtils.loadModel(de.uka.ipd.sdq.pcm.resourceenvironment.ResourceenvironmentPackage.eINSTANCE, model_fn, resourceSet);
    }

    public static void storeResourceEnvironment(final ResourceEnvironment resEnv, final String model_fn) throws IOException {
        ModelIOUtils.saveModel(resEnv, model_fn);
    }

    public static PCMModelSet readPCMModel(
            final String pcmRespositoryModel_fn,
            final String pcmSystemModel_fn,
            final String pcmResourceEnvironmentModel_fn,
            final String pcmAllocationModel_fn) throws IOException {
        final PCMModelSet pcmModel = new PCMModelSet();
        final ResourceSet set = new ResourceSetImpl();
        set.setURIConverter(new MyURIConverterImpl(new String[]{pcmRespositoryModel_fn, pcmSystemModel_fn, pcmAllocationModel_fn,
                    pcmResourceEnvironmentModel_fn}));
        final Repository pcmRepository = PCMModelReader.readRepository(
                pcmRespositoryModel_fn, set);
        if (pcmRepository == null) {
            PCMModelReader.log.error("Failed to read PCM repository from file '"
                    + pcmRespositoryModel_fn + "'");
            throw new IllegalArgumentException(
                    "Failed to read PCM repository from file '"
                    + pcmRespositoryModel_fn + "'");
        } else {
            pcmModel.setPCMRepository(pcmRepository);
            PCMModelReader.log.info("Loaded PCM repository model: "
                    + pcmRepository.getEntityName());
        }
         System pcmSystem = PCMModelReader.readSystem(
                pcmSystemModel_fn, set);
        if (pcmSystem == null) {
            PCMModelReader.log.error("Failed to read PCM system from file '"
                    + pcmSystemModel_fn + "'");
            throw new IllegalArgumentException(
                    "Failed to read PCM system from file '"
                    + pcmSystemModel_fn + "'");
        } else {
            pcmModel.setPCMSystem(pcmSystem);
            PCMModelReader.log.info("Loaded PCM system model: "
                    + pcmSystem.getEntityName());
        }
        Allocation pcmAllocation = PCMModelReader.readAllocation(
                pcmAllocationModel_fn, set);
        if (pcmAllocation == null) {
            PCMModelReader.log.error("Failed to read PCM allocation from file '"
                    + pcmAllocationModel_fn + "'");
            throw new IllegalArgumentException(
                    "Failed to read PCM allocation from file '"
                    + pcmAllocationModel_fn + "'");
        } else {
            pcmModel.setPCMAllocation(pcmAllocation);
            PCMModelReader.log.info("Loaded PCM allocation model: "
                    + pcmAllocation.getEntityName());
        }
        ResourceEnvironment pcmResourceEnvironment = PCMModelReader.readResourceEnvironment(
                pcmResourceEnvironmentModel_fn, set);
        if (pcmResourceEnvironment == null) {
            PCMModelReader.log.error("Failed to read PCM resource environment from file '"
                    + pcmResourceEnvironmentModel_fn + "'");
            throw new IllegalArgumentException(
                    "Failed to read PCM resource environment from file '"
                    + pcmResourceEnvironmentModel_fn + "'");
        } else {
            pcmModel.setPCMResourceEnvironment(pcmResourceEnvironment);
            PCMModelReader.log.info("Loaded PCM resource environment model: "
                    + pcmAllocation.getEntityName());
        }

            final Map<URI, URI> ma = set.getURIConverter().getURIMap();
            for (final URI uri : ma.keySet()) {
                PCMModelReader.log.info(uri + " => " + ma.get(uri));
            }
            EcoreUtil.resolveAll(set);
            final Map<EObject, Collection<Setting>> m = EcoreUtil.UnresolvedProxyCrossReferencer.find(set);
            for (final EObject o : m.keySet()) {
                PCMModelReader.log.warn(o + " " + m.get(o));
            }

        return pcmModel;
    }
}
