package org.trustsoft.slastic.plugins.pcm.control;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.system.System;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;

/**
 *
 * @author Andre van Hoorn
 */
public class PCMModelSet {
    private Repository pcmRepository;
    private System pcmSystem;
    private Allocation pcmAllocation;
    private ResourceEnvironment pcmResourceEnvironment;

    public PCMModelSet (){}

    public PCMModelSet(Repository pcmRepository, System pcmSystem, Allocation pcmAllocation, ResourceEnvironment pcmResourceEnvironment) {
        this.pcmRepository = pcmRepository;
        this.pcmSystem = pcmSystem;
        this.pcmAllocation = pcmAllocation;
        this.pcmResourceEnvironment = pcmResourceEnvironment;
    }

    public Allocation getPCMAllocation() {
        return pcmAllocation;
    }

    public void setPCMAllocation(Allocation pcmAllocation) {
        this.pcmAllocation = pcmAllocation;
    }

    public Repository getPCMRepository() {
        return pcmRepository;
    }

    public void setPCMRepository(Repository pcmRepository) {
        this.pcmRepository = pcmRepository;
    }

    public ResourceEnvironment getPCMResourceEnvironment() {
        return pcmResourceEnvironment;
    }

    public void setPCMResourceEnvironment(ResourceEnvironment pcmResourceEnvironment) {
        this.pcmResourceEnvironment = pcmResourceEnvironment;
    }

    public System getPCMSystem() {
        return pcmSystem;
    }

    public void setPCMSystem(System pcmSystem) {
        this.pcmSystem = pcmSystem;
    }
}
