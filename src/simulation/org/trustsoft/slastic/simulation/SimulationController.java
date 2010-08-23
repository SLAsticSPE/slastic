package org.trustsoft.slastic.simulation;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

import kieker.analysis.plugin.IMonitoringRecordConsumerPlugin;
import kieker.common.record.DummyMonitoringRecord;
import kieker.common.record.IMonitoringRecord;
import kieker.common.record.OperationExecutionRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.listeners.ReconfEventListener;
import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.model.interfaces.IReconfPlanReceiver;
import org.trustsoft.slastic.simulation.software.controller.CallHandler;
import org.trustsoft.slastic.simulation.software.controller.EntryCall;
import org.trustsoft.slastic.simulation.util.ExternalCallQueue;

import reconfMM.ReconfigurationModel;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.system.System;
import desmoj.core.simulator.Experiment;

public class SimulationController {

	private final DynamicSimulationModel model;
	private final Experiment exp;
	private StopCondition stopCond;
	private volatile boolean init;
	private final TreeSet<EntryCall> buffer = new TreeSet<EntryCall>(
			new Comparator<EntryCall>() {

				public int compare(EntryCall t, EntryCall t1) {
					return t.getTin() < t1.getTin() ? -1 : 1;
				}
			});
	private final ExternalCallQueue queue = new ExternalCallQueue();
	private final Log log = LogFactory.getLog(this.getClass());
	public final static IMonitoringRecord TERMINATION_RECORD = new DummyMonitoringRecord();
	private final IMonitoringRecordConsumerPlugin recordConsumerPluginPort = new IMonitoringRecordConsumerPlugin() {

		public Collection<Class<? extends IMonitoringRecord>> getRecordTypeSubscriptionList() {
			return null; // consume all records
		}

		public void start() {
			SimulationController.this.exp
					.stop(SimulationController.this.stopCond = new StopCondition(
							SimulationController.this.model,
							SimulationController.this.model.getName(),
							Constants.DEBUG));
			CallHandler.getInstance().setStopCond(
					SimulationController.this.stopCond);
			ModelManager.markStart();
			SimulationController.this.exp.start();
		}

		@Override
		public boolean newMonitoringRecord(IMonitoringRecord record) {
			// TODO: handle TERMINATION_RECORD

			if (record instanceof OperationExecutionRecord) {
				final OperationExecutionRecord ker = (OperationExecutionRecord) record;
				if (ker.eoi == 0) {
					// this.log.info("Received record " + ker.componentName);
					// we buffer entry calls until the last call will return
					// BEFORE the next one starts
					final EntryCall ec = new EntryCall(ker.componentName,
							ker.opname, ker.traceId, ker.tin, ker.tout);
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
			} else if (record == SimulationController.TERMINATION_RECORD) {
				SimulationController.this.log
						.info("Last record read, marking termination");
				SimulationController.this.queue.finish();
				CallHandler.getInstance().setTerminating(true);
			}
			return true;
		}

		public boolean execute() {
			if (!SimulationController.this.init) {
				SimulationController.this.init();
			}
			return true;
		}

		public void terminate(boolean error) {
			// TODO: we might send the termination record from here
		}
	};

	public IMonitoringRecordConsumerPlugin getRecordConsumerPluginPort() {
		return this.recordConsumerPluginPort;
	}

	public SimulationController(final String name, final Repository repos,
			final System struct, final ResourceEnvironment resourceEnv,
			final Allocation initAllocation,
			final ReconfigurationModel reconfModel) {
		this.exp = new Experiment(name);
		this.model = new DynamicSimulationModel(name, repos, struct,
				resourceEnv, initAllocation, reconfModel, this.queue, this.exp);
	}

	public synchronized void init() {
		if (!this.init) {
			this.exp.setShowProgressBar(false);
		}
		this.init = true;
	}

	public void start() {
		this.exp.stop(this.stopCond = new StopCondition(this.model, this.model
				.getName(), Constants.DEBUG));
		CallHandler.getInstance().setStopCond(this.stopCond);
		this.exp.start();
	}

	private final IReconfPlanReceiver reconfigurationPlanReceiverPort = new IReconfPlanReceiver() {

		@Override
		public void addReconfigurationEventListener(
				final ReconfEventListener listener) {
			ModelManager.getInstance().addReconfEventListener(listener);
		}

		@Override
		public void reconfigure(final SLAsticReconfigurationPlan plan) {
			ModelManager.getInstance().reconfigure(plan);

		}

		@Override
		public void removeReconfigurationEventListener(
				final ReconfEventListener listener) {
			ModelManager.getInstance().removeReconfigurationEventListener(
					listener);
		}
	};

	public IReconfPlanReceiver getReconfigurationPlanReceiverPort() {
		return this.reconfigurationPlanReceiverPort;
	}
}
