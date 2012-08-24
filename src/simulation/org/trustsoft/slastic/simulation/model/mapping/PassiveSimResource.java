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

package org.trustsoft.slastic.simulation.model.mapping;

import java.util.LinkedList;
import java.util.List;

import org.trustsoft.slastic.simulation.software.controller.controlflow.AbstractControlFlowEvent;

import desmoj.core.simulator.SimTime;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class PassiveSimResource {

	private final String name;
	private final int maxCapacity;
	private volatile int capacity;
	private final List<AbstractControlFlowEvent> queue = new LinkedList<AbstractControlFlowEvent>();

	public PassiveSimResource(final String name, final int maxCapacity) {
		this.name = name;
		this.maxCapacity = this.capacity = maxCapacity;
	}

	public int acquire(final AbstractControlFlowEvent node) {
		return this.capacity > 0 ? --this.capacity + this.sched(node) : this.enqueue(node);
	}

	public int release() {
		return this.queue.size() > 0 ? -this.queue.size()
				+ this.sched(this.queue.remove(0))
				: ++this.capacity <= this.maxCapacity ? this.capacity : null;
	}

	private int sched(final AbstractControlFlowEvent node) {
		node.schedule(SimTime.NOW);
		return 0;
	}

	private int enqueue(final AbstractControlFlowEvent node) {
		this.queue.add(node);
		return -this.queue.size(); // TODO: why '-'?
	}

	public final String getName() {
		return this.name;
	}
}
