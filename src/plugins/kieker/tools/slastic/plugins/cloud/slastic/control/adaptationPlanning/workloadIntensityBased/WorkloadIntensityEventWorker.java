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

package kieker.tools.slastic.plugins.cloud.slastic.control.adaptationPlanning.workloadIntensityBased;

import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.tools.slastic.plugins.cloud.slastic.control.adaptationPlanning.ConfigurationManager;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * 
 * 
 * @author Andre van Hoorn
 * 
 */
class WorkloadIntensityEventWorker implements Runnable {
	private static final Log LOG = LogFactory.getLog(WorkloadIntensityEventWorker.class);

	private final WorkloadIntensityRuleEngine ruleEngine;
	private final ConfigurationManager configurationManager;

	private final AssemblyComponent assemblyComponent;
	private final ExecutionContainerType executionContainerType;

	private final AtomicReference<WorkloadIntensityEvent> workloadIntensityEventRef;

	public WorkloadIntensityEventWorker(final AssemblyComponent assemblyComponent,
			final ExecutionContainerType executionContainerType, final WorkloadIntensityRuleEngine ruleEngine,
			final ConfigurationManager configurationManager,
			final AtomicReference<WorkloadIntensityEvent> workloadIntensityEventRef) {
		this.assemblyComponent = assemblyComponent;
		this.executionContainerType = executionContainerType;
		this.ruleEngine = ruleEngine;
		this.configurationManager = configurationManager;
		this.workloadIntensityEventRef = workloadIntensityEventRef;
	}

	/**
	 * Note that this method doesn't need to be thread-safe, as there's only one
	 * worker thread executing the {@link WorkloadIntensityEventWorker}s. Also
	 * the {@link ConfigurationManager#reconfigure(int)} is only called by this
	 * method.
	 */
	@Override
	public void run() {
		// consume (get and reset) most recent workload intensity event
		final WorkloadIntensityEvent curWorkloadIntensity = this.workloadIntensityEventRef.getAndSet(null);

		if (curWorkloadIntensity == null) {
			// this may happen if workload intensity events have been dropped in
			// the meantime
			return;
		}

		final Baseline requestedBaseline = this.ruleEngine.nextBaseline(this.assemblyComponent, curWorkloadIntensity);
		if (requestedBaseline == null) {
			LOG.error("failed to request nextBaseline for assembly component '" + this.assemblyComponent + "' and workload intensity '" + curWorkloadIntensity + "'");
		}

		final boolean success =
				this.configurationManager.reconfigure(this.assemblyComponent, this.executionContainerType, requestedBaseline.getNumNodes());
		if (success) {
			this.ruleEngine.commitBaseline(this.assemblyComponent, requestedBaseline);
		} else {
			LOG.error(String.format("Reconfiguration failed for assembly component %s, execution contain type %s, and baseline %s ",
					this.assemblyComponent, this.executionContainerType, requestedBaseline));
		}
	}
}
