package org.trustsoft.slastic.simulation;

import java.util.Comparator;
import java.util.TreeSet;

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

@SuppressWarnings("unused")
public class SimulationController {
	private static final Log LOG = LogFactory.getLog(SimulationController.class);

	private final DynamicSimulationModel model;
	private final Experiment exp;
	private StopCondition stopCond;
	private SimulationController instance;

	private volatile boolean init;
	private final TreeSet<EntryCall> buffer = new TreeSet<EntryCall>(
			new Comparator<EntryCall>() {

				@Override
				public int compare(final EntryCall t, final EntryCall t1) {
					return t.getTin() < t1.getTin() ? -1 : 1;
				}
			});
	private final ExternalCallQueue queue = new ExternalCallQueue();

	public final static IMonitoringRecord TERMINATION_RECORD = new EmptyRecord();

	public static final String INPUT_PORT_NAME_RECORDS = "records";

	@Plugin
	public class MonitoringRecordConsumerFilter extends AbstractFilterPlugin {

		public MonitoringRecordConsumerFilter(final Configuration configuration) {
			super(configuration);
		}

		// TODO: Is this method called at all? It seems that this is a duplicate of SimulationController.start()
		public void start() {
			SimulationController.this.exp.stop(SimulationController.this.stopCond = new StopCondition(SimulationController.this.model,
					SimulationController.this.model.getName(), Constants.DEBUG));
			CallHandler.getInstance().setStopCond(SimulationController.this.stopCond);
			ModelManager.markStart();
			SimulationController.this.exp.start();
		}

		@InputPort(name = INPUT_PORT_NAME_RECORDS, eventTypes = { OperationExecutionRecord.class })
		public boolean newMonitoringRecord(final OperationExecutionRecord record) {
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

		@Override
		protected Configuration getDefaultConfiguration() {
			return new Configuration();
		}
	}

	// TODO: we should clean this up ...
	private final AbstractFilterPlugin monitoingRecordConsumerFilter = new MonitoringRecordConsumerFilter(new Configuration());
	private Injector createInjector;

	public AbstractFilterPlugin getMoitoringRecordConsumerFilter() {
		return this.monitoingRecordConsumerFilter;
	}

	public SimulationController(final String name, final Repository repos, final System struct, final ResourceEnvironment resourceEnv,
			final Allocation initAllocation, final ReconfigurationModel reconfModel) {
		this.exp = new Experiment(name);
		this.model = new DynamicSimulationModel(name, repos, struct, resourceEnv, initAllocation, reconfModel, this.queue, this.exp);
	}

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

	private final IReconfPlanReceiver reconfigurationPlanReceiverPort = new IReconfPlanReceiver() {

		@Override
		public void addReconfigurationEventListener(
				final ReconfEventListener listener) {
			ModelManager.getInstance().addReconfEventListener(listener);
		}

		@Override
		public void reconfigure(final SLAsticReconfigurationPlan plan) {
			SimulationController.LOG.info("Received reconfiguration plan " + plan);
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

	public final SimulationController getInstance() {
		return this.instance;
	}
}
