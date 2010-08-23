package org.trustsoft.slastic.plugins.pcm;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.system.System;
import java.io.IOException;
import org.trustsoft.slastic.plugins.slasticImpl.ModelIOUtils;

/**
 *
 * @author Andre van Hoorn
 */
public class PCMModelReader extends ModelIOUtils {

    public static Repository readRepository(final String model_fn) throws IOException {
        //return (Repository) this.readXMIModel(model_fn, de.uka.ipd.sdq.pcm.repository.RepositoryPackage.class.getName());
        return (Repository) loadModel(de.uka.ipd.sdq.pcm.repository.RepositoryPackage.eINSTANCE, model_fn);
    }

    public static void storeRepository(final Repository repos, final String model_fn) throws IOException {
        saveModel(repos, model_fn);
    }

    public static System readSystem(final String model_fn) throws IOException {
        //return (System) this.readXMIModel(model_fn, de.uka.ipd.sdq.pcm.system.SystemPackage.class.getName());
        return (System) loadModel(de.uka.ipd.sdq.pcm.system.SystemPackage.eINSTANCE, model_fn);
    }

    public static void storeSystem(final System system, final String model_fn) throws IOException {
        saveModel(system, model_fn);
    }

    public static Allocation readAllocation(final String model_fn) throws IOException {
        //return (Allocation) this.readXMIModel(model_fn, de.uka.ipd.sdq.pcm.allocation.AllocationPackage.class.getName());
        return (Allocation) loadModel(de.uka.ipd.sdq.pcm.allocation.AllocationPackage.eINSTANCE, model_fn);
    }

    public static void storeAllocation(final Allocation allocation, final String model_fn) throws IOException {
        saveModel(allocation, model_fn);
    }

    public static ResourceEnvironment readResourceEnvironment(final String model_fn) throws IOException {
        //return (ResourceEnvironment) this.readXMIModel(model_fn, de.uka.ipd.sdq.pcm.resourceenvironment.ResourceenvironmentPackage.class.getName());
        return (ResourceEnvironment) loadModel(de.uka.ipd.sdq.pcm.resourceenvironment.ResourceenvironmentPackage.eINSTANCE, model_fn);
    }

    public static void storeResourceEnvironment(final ResourceEnvironment resEnv, final String model_fn) throws IOException {
        saveModel(resEnv, model_fn);
    }
}
