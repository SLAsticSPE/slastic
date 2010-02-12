package org.trustsoft.slastic.simulation;

import java.util.Comparator;
import java.util.TreeSet;

import kieker.common.logReader.IKiekerRecordConsumer;
import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;
import kieker.tpmon.monitoringRecord.KiekerDummyMonitoringRecord;
import kieker.tpmon.monitoringRecord.executions.KiekerExecutionRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.listeners.ReconfEventListener;
import org.trustsoft.slastic.simulation.model.interfaces.IReconfPlanReceiver;
import org.trustsoft.slastic.simulation.software.controller.CallHandler;
import org.trustsoft.slastic.simulation.software.controller.EntryCall;

import reconfMM.ReconfigurationModel;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.system.System;
import desmoj.core.simulator.Experiment;

public class SimulationController implements IKiekerRecordConsumer,
		IReconfPlanReceiver {
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
	private final Log log = LogFactory.getLog(this.getClass());

	public final static AbstractKiekerMonitoringRecord TERMINATION_RECORD = new KiekerDummyMonitoringRecord();

	public SimulationController(final String name, final Repository repos,
			final System struct, final ResourceEnvironment resourceEnv,
			final Allocation initAllocation,
			final ReconfigurationModel reconfModel) {
		this.exp = new Experiment(name);
		this.model = new DynamicSimulationModel(name, repos, struct,
				resourceEnv, initAllocation, reconfModel, this.buffer, this.exp);
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

	@Override
	public void consumeMonitoringRecord(
			final AbstractKiekerMonitoringRecord monitoringRecord)
			throws RecordConsumerExecutionException {

		// TODO: handle TERMINATION_RECORD

		if (monitoringRecord instanceof KiekerExecutionRecord) {
			final KiekerExecutionRecord ker = (KiekerExecutionRecord) monitoringRecord;
			if (ker.eoi == 0) {
				// this.log.info("Received record " + ker.componentName);
				// we buffer entry calls until the last call will return
				// BEFORE the next one starts
				while (this.buffer.size() > Constants.PRE_BUFFER
						&& this.buffer.first().getTout() < ker.tin) {
					try {
						// we need to schedule next calls on return
						synchronized (this.buffer) {
							this.log.info("Queue full, waiting");
							this.buffer.wait();
						}
					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
				}
				this.buffer.add(new EntryCall(ker.componentName, ker.opname,
						ker.traceId, ker.tin, ker.tout));
			}
		} else if (monitoringRecord instanceof KiekerDummyMonitoringRecord) {
			final KiekerDummyMonitoringRecord kdr = (KiekerDummyMonitoringRecord) monitoringRecord;
			this.log.info("Last record read, marking termination");
			CallHandler.getInstance().setTerminating(true);
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

	@Override
	public void addReconfigurationEventListener(
			final ReconfEventListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reconfigure(final SLAsticReconfigurationPlan plan) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeReconfigurationEventListener(
			final ReconfEventListener listener) {
		// TODO Auto-generated method stub

	}
}
