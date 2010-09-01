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

public class DynamicSimulationModel extends Model {

	private final ModelManager manager;
	private final CallHandler callHandler;
	private final ExternalCallQueue buffer;
	private final Log log = LogFactory.getLog(this.getClass());
	private boolean terminating;
	private boolean written;

	public DynamicSimulationModel(final String name, final Repository repos,
			final System struct, final ResourceEnvironment resourceEnv,
			final Allocation initAllocation,
			final ReconfigurationModel reconfModel,
			final ExternalCallQueue simulatedThreadQueue,
			final Experiment experiment) {
		super(null, name, Constants.DEBUG, Constants.DEBUG);
		this.connectToExperiment(experiment);
		this.manager = new ModelManager(repos, struct, resourceEnv,
				initAllocation, reconfModel, this, this.log);
		this.buffer = simulatedThreadQueue;
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

		// // for testing only
/*
		final ReconfigurationPlanModelFactory pf = new ReconfigurationPlanModelFactoryImpl();

		final NodeAllocationOP na = pf.createNodeAllocationOP();
		ResourceContainer dest0 = null, dest1 = null, dest2 = null;
		for (final ResourceContainer rc : ModelManager.getInstance()
				.getResources().getResourceContainer_ResourceEnvironment()) {
			if (rc.getId().equals("_e76KIaKiEd6HO68P--FvMQ")) {
				dest0 = rc;
			} else if (rc.getId().equals("_dlANwTmhEd-U4u9Bc9bBPA")) {
				dest1 = rc;
			} else if (rc.getId().equals("_fr9y4TmkEd-U4u9Bc9bBPA")) {
				dest2 = rc;
			}
		}
		na.setNode(dest0);
		AllocationEvent alloc = new AllocationEvent(this, "bla",
				Constants.DEBUG, na, dest0.getId());
		alloc.schedule(SimTime.NOW);
		alloc = new AllocationEvent(this, "bla", Constants.DEBUG, na, dest1
				.getId());
		alloc.schedule(SimTime.NOW);
		alloc = new AllocationEvent(this, "bla", Constants.DEBUG, na, dest2
				.getId());
		alloc.schedule(SimTime.NOW);
		final ComponentReplicationOP op = pf.createComponentReplicationOP();
		op.setComponent(ModelManager.getInstance().getAssemblyCont()
				.getASMContextById("_HWAaYKKjEd6HO68P--FvMQ"));
		op.setDestination(dest0);
		ReplicationEvent re = new ReplicationEvent(this, "foo",
				Constants.DEBUG, op);
		re = new ReplicationEvent(this, "foo", Constants.DEBUG, op);
		re.schedule(new SimTime(600));
		final ComponentReplicationOP op1 = pf.createComponentReplicationOP();
		op1.setComponent(ModelManager.getInstance().getAssemblyCont()
				.getASMContextById("_HWAaYKKjEd6HO68P--FvMQ"));
		op1.setDestination(dest1);
		re = new ReplicationEvent(this, "foo", Constants.DEBUG, op1);
		re.schedule(new SimTime(800));
		final ComponentReplicationOP op2 = pf.createComponentReplicationOP();
		op2.setComponent(ModelManager.getInstance().getAssemblyCont()
				.getASMContextById("_HWAaYKKjEd6HO68P--FvMQ"));
		op2.setDestination(dest2);
		re = new ReplicationEvent(this, "foo", Constants.DEBUG, op2);
		re.schedule(new SimTime(1000));

		// re.schedule(new SimTime(4500));
		// re = new ReplicationEvent(this, "foo", Constants.DEBUG, op);
		// re.schedule(new SimTime(7500));
		// re = new ReplicationEvent(this, "foo", Constants.DEBUG, op);
		// re.schedule(new SimTime(10500));
		final ComponentDeReplicationOP dr = pf.createComponentDeReplicationOP();
		final AllocationFactory af = new AllocationFactoryImpl();
		final AllocationContext ac = af.createAllocationContext();
		ac
				.setAssemblyContext_AllocationContext(ModelManager
						.getInstance().getAssemblyCont().getASMContextById(
								"_HWAaYKKjEd6HO68P--FvMQ"));
		ac.setResourceContainer_AllocationContext(dest0);
		dr.setComponent(ac);
		final DelComponent dc = new DelComponent(this, "bar", Constants.DEBUG,
				dr);	*/
		// dc.schedule(new SimTime(3000));
		//
		// dc = new DelComponent(this, "bar", Constants.DEBUG, dr);
		// dc.schedule(new SimTime(6000));
		// dc = new DelComponent(this, "bar", Constants.DEBUG, dr);
		// dc.schedule(new SimTime(9000));
		// dc = new DelComponent(this, "bar", Constants.DEBUG, dr);
		// dc.schedule(new SimTime(9500));

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
		this.log.info("Attempting to fetch next call");
		call = this.buffer.removeFirstBlocking();
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
