package org.trustsoft.slastic.simulation;

import java.util.Comparator;
import java.util.TreeSet;

import kieker.common.logReader.IKiekerRecordConsumer;
import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;
import kieker.tpmon.monitoringRecord.executions.KiekerExecutionRecord;

import org.apache.commons.logging.Log;
import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.software.controller.EntryCall;

import reconfMM.ReconfigurationModel;
import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.system.System;
import desmoj.core.simulator.Experiment;

public class SimulationController implements IKiekerRecordConsumer {

	private final DynamicSimulationModel model;
	private final Experiment exp;
	private StopCondition stopCond;
	private boolean init;
	private final TreeSet<EntryCall> buffer = new TreeSet<EntryCall>(
			new Comparator<EntryCall>() {

				public int compare(EntryCall t, EntryCall t1) {
					return t.getTin() < t1.getTin() ? -1 : 1;
				}

			});
	private final Log log;

	public SimulationController(final String name, final Repository repos,
			final System struct, final ResourceEnvironment resourceEnv,
			final Allocation initAllocation,
			final ReconfigurationModel reconfModel, final Log log) {
		this.log = log;
		this.exp = new Experiment(name);
		this.model = new DynamicSimulationModel(name, repos, struct,
				resourceEnv, initAllocation, reconfModel, this.buffer, log,
				this.exp);
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
		this.exp.start();
	}

	@Override
	public void consumeMonitoringRecord(
			final AbstractKiekerMonitoringRecord monitoringRecord)
			throws RecordConsumerExecutionException {
		if (monitoringRecord instanceof KiekerExecutionRecord) {
			final KiekerExecutionRecord ker = (KiekerExecutionRecord) monitoringRecord;
			if (ker.isEntryPoint) {
				synchronized (this.buffer) {
					// we buffer entry calls until the last call will return
					// BEFORE the next one starts
					while (this.buffer.size() > Constants.PRE_BUFFER
							&& this.buffer.first().getTout() < ker.tin) {
						try {
							// we need to schedule next calls on return
							this.buffer.wait();
						} catch (final InterruptedException e) {
							e.printStackTrace();
						}
					}
					this.buffer.add(new EntryCall(ker.componentName,
							ker.opname, ker.traceId, ker.tin, ker.tout));

				}
			}
		}
	}

	@Override
	public boolean execute() throws RecordConsumerExecutionException {
		if (!this.init) {
			this.init();
		}
		return true;
	}

	@Override
	public String[] getRecordTypeSubscriptionList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void terminate() {

	}
}
