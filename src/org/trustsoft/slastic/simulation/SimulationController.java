package org.trustsoft.slastic.simulation;

import java.util.Comparator;
import java.util.TreeSet;

import kieker.common.logReader.IKiekerRecordConsumer;
import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;
import kieker.tpmon.monitoringRecord.executions.KiekerExecutionRecord;

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

	public SimulationController(final String name, final Repository repos,
			final System struct, final ResourceEnvironment resourceEnv,
			final Allocation initAllocation,
			final ReconfigurationModel reconfModel) {
		model = new DynamicSimulationModel(name, repos, struct, resourceEnv,
				initAllocation, reconfModel, buffer);
		exp = new Experiment(name);
	}

	public synchronized void init() {
		if (!init) {
			model.connectToExperiment(exp);
			exp.setShowProgressBar(false);
		}
		init = true;
	}

	public void start() {
		exp.stop(stopCond = new StopCondition(model, model.getName(),
				Constants.DEBUG));
		exp.start();
	}

	@Override
	public void consumeMonitoringRecord(
			final AbstractKiekerMonitoringRecord monitoringRecord)
			throws RecordConsumerExecutionException {
		if (monitoringRecord instanceof KiekerExecutionRecord) {
			final KiekerExecutionRecord ker = (KiekerExecutionRecord) monitoringRecord;
			if (ker.isEntryPoint) {
				synchronized (buffer) {
					// we buffer entry calls until the last call will return
					// BEFORE the next one starts
					while (buffer.size() > Constants.PRE_BUFFER
							&& buffer.first().getTout() < ker.tin) {
						try {
							// we need to schedule next calls on return
							buffer.wait();
						} catch (final InterruptedException e) {
							e.printStackTrace();
						}
					}
					buffer.add(new EntryCall(ker.componentName, ker.opname,
							ker.traceId, ker.tin, ker.tout));

				}
			}
		}
	}

	@Override
	public boolean execute() throws RecordConsumerExecutionException {
		if (!init) {
			init();
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
