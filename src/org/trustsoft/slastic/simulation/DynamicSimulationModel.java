package org.trustsoft.slastic.simulation;

import java.util.TreeSet;

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
import desmoj.core.simulator.Model;

public class DynamicSimulationModel extends Model {

	private final ModelManager manager;
	private final CallHandler callHandler;
	private final TreeSet<EntryCall> buffer;

	public DynamicSimulationModel(final String name, final Repository repos,
			final System struct, final ResourceEnvironment resourceEnv,
			final Allocation initAllocation,
			final ReconfigurationModel reconfModel,
			final TreeSet<EntryCall> buffer) {
		super(null, name, Constants.DEBUG, Constants.DEBUG);
		manager = new ModelManager(repos, struct, resourceEnv, initAllocation,
				reconfModel, this);
		this.buffer = buffer;
		callHandler = new CallHandler();
	}

	@Override
	public String description() {
		return "This is SLAstic.SIM, the dynamic software architecture simulator.\nWe currently simulate "
				+ super.getName();
	}

	@Override
	public void doInitialSchedules() {
		synchronized (buffer) {
			for (final EntryCall call : buffer) {
				try {
					callHandler.call(call.getOpname(), call.getTraceId() + "",
							call.getComponentName(), call.getTin());
				} catch (final NoSuchSeffException e) {
					e.printStackTrace();
				} catch (final BranchException e) {
					e.printStackTrace();
				} catch (final SumGreaterXException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void init() {

	}

	public void callReturns(final String traceId) {
		EntryCall remove = null;
		for (final EntryCall ec : buffer) {
			if (traceId.equals(ec.getTraceId())) {
				remove = ec;
				break;
			}
		}
		buffer.remove(remove);
		synchronized (buffer) {
			buffer.notify();
		}
	}

}
