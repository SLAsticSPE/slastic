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

package kieker.tools.slastic.plugins.pcm.control;

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
