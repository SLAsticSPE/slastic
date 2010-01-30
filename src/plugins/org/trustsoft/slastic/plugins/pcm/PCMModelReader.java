package org.trustsoft.slastic.plugins.pcm;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;

/**
 *
 * @author Andre van Hoorn
 */
public class PCMModelReader {
    private static final PCMModelReader INSTANCE = new PCMModelReader();

    public static final PCMModelReader getInstance(){
        return INSTANCE;
    }

    public Repository readRepository (final String model_fn){
        return null;
    }

    public System readSystem (final String model_fn){
        return null;
    }

    public Allocation readAllocation (final String model_fn){
        return null;
    }

    public ResourceEnvironment readResourceEnvironment (final String model_fn){
        return null;
    }
}
