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

package kieker.tools.slastic.simulation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import reconfMM.ReconfigurationModel;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.system.System;
import desmoj.core.simulator.Experiment;

import kieker.analysis.plugin.annotation.InputPort;
import kieker.analysis.plugin.annotation.Plugin;
import kieker.analysis.plugin.filter.AbstractFilterPlugin;
import kieker.common.configuration.Configuration;
import kieker.common.record.IMonitoringRecord;
import kieker.common.record.controlflow.OperationExecutionRecord;
import kieker.common.record.misc.EmptyRecord;
import kieker.common.util.ClassOperationSignaturePair;
import kieker.tools.slastic.simulation.config.Constants;
import kieker.tools.slastic.simulation.listeners.IReconfigurationEventListener;
import kieker.tools.slastic.simulation.model.ModelManager;
import kieker.tools.slastic.simulation.model.interfaces.IReconfigurationPlanReceiver;
import kieker.tools.slastic.simulation.software.controller.CallHandler;
import kieker.tools.slastic.simulation.software.controller.EntryCall;
import kieker.tools.slastic.simulation.util.ExternalCallQueue;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class SimulationController {
	private static final Log LOG = LogFactory.getLog(SimulationController.class);

	public static final String INPUT_PORT_NAME_RECORDS = "records";

	public final static IMonitoringRecord TERMINATION_RECORD = new EmptyRecord();

	private final DynamicSimulationModel model;
	private final Experiment exp;
	private StopCondition stopCond;

	private volatile boolean init;

	// TODO: (Re-)introduce the buffer?
	// private final TreeSet<EntryCall> buffer = new TreeSet<EntryCall>(
	// new Comparator<EntryCall>() {
	//
	// @Override
	// public int compare(final EntryCall t, final EntryCall t1) {
	// return t.getTin() < t1.getTin() ? -1 : 1;
	// }
	// });
	private final ExternalCallQueue queue = new ExternalCallQueue();

	public SimulationController(final String name, final Repository repos, final System struct, final ResourceEnvironment resourceEnv,
			final Allocation initAllocation, final ReconfigurationModel reconfModel) {
		this.exp = new Experiment(name);
		this.model = new DynamicSimulationModel(name, repos, struct, resourceEnv, initAllocation, reconfModel, this.queue, this.exp);
	}

	private Injector createInjector;

	public synchronized void init() {
		this.createInjector = Guice.createInjector(new SimulationModule());
		CallHandler.getInstance().setInjector(this.createInjector);
		if (!this.init) {
			this.exp.setShowProgressBar(false);
		}
		this.init = true;
	}

	public void start() {
		java.lang.System.out.println(java.lang.System.currentTimeMillis());
		this.exp.stop(this.stopCond = new StopCondition(this.model, this.model.getName(), Constants.DEBUG));
		CallHandler.getInstance().setStopCond(this.stopCond);
		ModelManager.markStart();
		this.exp.start();
	}

	@Plugin
	public class MonitoringRecordConsumerFilter extends AbstractFilterPlugin {

		public MonitoringRecordConsumerFilter(final Configuration configuration) {
			super(configuration);
		}

		// TODO: Is this method called at all? It seems that this is a duplicate of SimulationController.start()
		// public void start() {
		// SimulationController.this.exp.stop(SimulationController.this.stopCond = new StopCondition(SimulationController.this.model,
		// SimulationController.this.model.getName(), Constants.DEBUG));
		// CallHandler.getInstance().setStopCond(SimulationController.this.stopCond);
		// ModelManager.markStart();
		// SimulationController.this.exp.start();
		// }

		@InputPort(name = INPUT_PORT_NAME_RECORDS, eventTypes = { OperationExecutionRecord.class })
		public boolean inputMonitoringRecord(final OperationExecutionRecord record) {
			// TODO: handle TERMINATION_RECORD (isn't this the case below?)
			final OperationExecutionRecord ker = record;
			if (ker.getEoi() == 0) {
				// this.log.info("Received record " + ker.componentName);
				// we buffer entry calls until the last call will return
				// BEFORE the next one starts
				final ClassOperationSignaturePair cosp = ClassOperationSignaturePair.splitOperationSignatureStr(ker.getOperationSignature());
				final EntryCall ec = new EntryCall(cosp.getFqClassname(), cosp.getSignature().getName(), ker.getTraceId(), ker.getTin(), ker.getTout());
				SimulationController.this.queue.add(ec);
				// while (this.buffer.size() > Constants.PRE_BUFFER
				// && this.buffer.first().getTout() < ker.tin) {
				// try {
				// // we need to schedule next calls on return
				// synchronized (this.buffer) {
				// this.log.info("Queue full, waiting");
				// this.buffer.wait();
				// }
				// } catch (final InterruptedException e) {
				// e.printStackTrace();
				// }
				// }
				// this.buffer.add(ec);
			}
			return true;
		}

		@Override
		public boolean init() {
			if (!SimulationController.this.init) {
				SimulationController.this.init();
			}
			return true;
		};

		@Override
		public void terminate(final boolean error) {
			SimulationController.LOG.info("Last record read, marking termination");
			SimulationController.this.queue.finish();
			CallHandler.getInstance().setTerminating(true);
		}

		@Override
		public Configuration getCurrentConfiguration() {
			return new Configuration();
		}
	}

	private final AbstractFilterPlugin monitoringRecordConsumerFilter = new MonitoringRecordConsumerFilter(new Configuration());

	public AbstractFilterPlugin getMonitoringRecordConsumerFilter() {
		return this.monitoringRecordConsumerFilter;
	}

	private final IReconfigurationPlanReceiver reconfigurationPlanReceiverPort = new IReconfigurationPlanReceiver() {

		@Override
		public void addReconfigurationEventListener(final IReconfigurationEventListener listener) {
			ModelManager.getInstance().addReconfEventListener(listener);
		}

		@Override
		public void reconfigure(final SLAsticReconfigurationPlan plan) {
			LOG.info("Received reconfiguration plan " + plan);
			ModelManager.getInstance().reconfigure(plan);

		}

		@Override
		public void removeReconfigurationEventListener(final IReconfigurationEventListener listener) {
			ModelManager.getInstance().removeReconfigurationEventListener(listener);
		}
	};

	public IReconfigurationPlanReceiver getReconfigurationPlanReceiverPort() {
		return this.reconfigurationPlanReceiverPort;
	}
}
