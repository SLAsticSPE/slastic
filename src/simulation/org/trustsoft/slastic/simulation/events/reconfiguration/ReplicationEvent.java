package org.trustsoft.slastic.simulation.events.reconfiguration;

import org.trustsoft.slastic.simulation.model.ModelManager;

import ReconfigurationPlanModel.ComponentReplicationOP;
import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import desmoj.core.simulator.Model;

public final class ReplicationEvent extends ReconfigurationEvent {

	public ReplicationEvent(final Model owner, final String name,
			final boolean showInTrace,
			final SLAsticReconfigurationOpType reconfOp) {
		super(owner, name, showInTrace, reconfOp);
	}

	@Override
	public boolean eventRoutine2() {
		// TODO Auto-generated method stub
		final ComponentReplicationOP repOp = (ComponentReplicationOP) super
				.getReconfOp();
		final AssemblyContext ac = repOp.getComponent();
		final ResourceContainer rc = repOp.getDestination();
		if (ModelManager.getInstance().getHwCont().isAllocated(rc.getId())
				&& !ModelManager.getInstance().getAllocCont().hasAllocation(
						rc.getId(), ac.getId())) {
			ModelManager.getInstance().getAllocCont().add(rc.getId(),
					ac.getId());
			return true;
			// TODO Auto-generated catch block
		} else {
			return false;
		}

	}

}
