package org.trustsoft.slastic.simulation;

import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.software.controller.CallHandler;
import org.trustsoft.slastic.simulation.software.controller.EntryCall;
import org.trustsoft.slastic.simulation.software.controller.exceptions.BranchException;
import org.trustsoft.slastic.simulation.software.controller.exceptions.NoSuchSeffException;
import org.trustsoft.slastic.simulation.software.controller.exceptions.SumGreaterXException;

import reconfMM.ReconfigurationModel;
import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.system.System;
import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.Model;

public class DynamicSimulationModel extends Model {

	private final ModelManager manager;
	private final CallHandler callHandler;
	private final TreeSet<EntryCall> buffer;
	private final Log log = LogFactory.getLog(this.getClass());
	private boolean terminating;

	public DynamicSimulationModel(final String name, final Repository repos,
			final System struct, final ResourceEnvironment resourceEnv,
			final Allocation initAllocation,
			final ReconfigurationModel reconfModel,
			final TreeSet<EntryCall> buffer, final Experiment experiment) {
		super(null, name, Constants.DEBUG, Constants.DEBUG);
		this.connectToExperiment(experiment);
		this.manager = new ModelManager(repos, struct, resourceEnv,
				initAllocation, reconfModel, this, this.log);
		this.buffer = buffer;
		this.callHandler = new CallHandler(this);
	}

	@Override
	public String description() {
		return "This is SLAstic.SIM, the dynamic software architecture simulator.\nWe currently simulate "
				+ super.getName();
	}

	@Override
	public void doInitialSchedules() {
		synchronized (this.buffer) {
			for (final EntryCall call : this.buffer) {
				try {
					this.log.info("Calling " + call.getComponentName() + "."
							+ call.getOpname());
					this.callHandler.call(call.getOpname(), call.getTraceId()
							+ "", call.getComponentName(), call.getTin());
				} catch (final NoSuchSeffException e) {
					e.printStackTrace();
				} catch (final BranchException e) {
					e.printStackTrace();
				} catch (final SumGreaterXException e) {
					e.printStackTrace();
				}
				if (Constants.SINGLE_TRACE) {
					break;
				}
			}
			this.buffer.clear();
			this.buffer.notify();
		}
	}

	@Override
	public void init() {

	}

	/*
	 * call returns algo<br/> let the next entry call be scheduled
	 * 
	 */
	public void callReturns(final String traceId) {
		final EntryCall call;
		synchronized (this.buffer) {
			this.log.info("Attempting to fetch next call");
			call = this.buffer.first();
			if (call != null) {
				this.buffer.remove(call);
			}
			this.buffer.notify();

		}
		if (call == null) {
			this.log.info("No call in queue");
			return;
		}
		this.log.info("Calling " + call.getComponentName() + "."
				+ call.getOpname());
		try {
			this.callHandler.call(call.getOpname(), call.getTraceId() + "",
					call.getComponentName(), call.getTin());
		} catch (final NoSuchSeffException e) {
			e.printStackTrace();
		} catch (final BranchException e) {
			e.printStackTrace();
		} catch (final SumGreaterXException e) {
			e.printStackTrace();
		}

	}

	public void setTerminating(final boolean b) {
		this.terminating = b;
	}

}
