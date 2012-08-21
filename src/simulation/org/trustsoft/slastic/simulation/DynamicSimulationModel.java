package org.trustsoft.slastic.simulation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.config.Constants;
import org.trustsoft.slastic.simulation.model.ModelManager;
import org.trustsoft.slastic.simulation.software.controller.CallHandler;
import org.trustsoft.slastic.simulation.software.controller.EntryCall;
import org.trustsoft.slastic.simulation.software.controller.exceptions.BranchException;
import org.trustsoft.slastic.simulation.software.controller.exceptions.NoSuchSeffException;
import org.trustsoft.slastic.simulation.software.controller.exceptions.SumGreaterXException;
import org.trustsoft.slastic.simulation.util.ExternalCallQueue;

import reconfMM.ReconfigurationModel;
import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.system.System;
import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.Model;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class DynamicSimulationModel extends Model {
	private static final Log LOG = LogFactory.getLog(DynamicSimulationModel.class);

	@SuppressWarnings("unused")
	private final ModelManager manager; // used to create (and hold) the singleton instance

	private final CallHandler callHandler;
	private final ExternalCallQueue buffer;

	// TODO: It seems that these variables aren't used at all
	// private volatile boolean terminating;
	// private volatile boolean written;

	public DynamicSimulationModel(final String name, final Repository repos,
			final System struct, final ResourceEnvironment resourceEnv,
			final Allocation initAllocation,
			final ReconfigurationModel reconfModel,
			final ExternalCallQueue simulatedThreadQueue,
			final Experiment experiment) {
		super(null, name, Constants.DEBUG, Constants.DEBUG);
		this.connectToExperiment(experiment);
		this.manager = new ModelManager(repos, struct, resourceEnv, initAllocation, reconfModel, this, LOG);
		this.buffer = simulatedThreadQueue;
		this.callHandler = new CallHandler(this);
	}

	@Override
	public String description() {
		return "This is SLAstic.SIM, the dynamic software architecture simulator.\nWe currently simulate " + super.getName();
	}

	@Override
	public void doInitialSchedules() {
		synchronized (this.buffer) {
			for (final EntryCall call : this.buffer) {
				try {
					LOG.info("Calling " + call.getComponentName() + "." + call.getOpname());
					this.callHandler.call(call.getOpname(), call.getTraceId() + "", call.getComponentName(), call.getTin());
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
	 */
	public void callReturns(final String traceId) {
		final EntryCall call;
		LOG.info("Attempting to fetch next call");
		call = this.buffer.removeFirstBlocking();
		if (call == null) {
			LOG.info("No call in queue");
			return;
		}
		LOG.warn("Calling " + call.getComponentName() + "." + call.getOpname());
		try {
			this.callHandler.call(call.getOpname(), call.getTraceId() + "", call.getComponentName(), call.getTin());
		} catch (final NoSuchSeffException e) {
			e.printStackTrace();
		} catch (final BranchException e) {
			e.printStackTrace();
		} catch (final SumGreaterXException e) {
			e.printStackTrace();
		}

	}

	// TODO: Not used at all
	// public void setTerminating(final boolean b) {
	// this.terminating = b;
	// }
}
