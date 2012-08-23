package org.trustsoft.slastic.simulation.events.reconfiguration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.model.ModelManager;

import ReconfigurationPlanModel.ComponentReplicationOP;
import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import desmoj.core.simulator.Model;

/**
 * 
 * @author Robert von Massow
 * 
 */
public final class ReplicationEvent extends AbstractReconfigurationEvent {

	private static final Log LOG = LogFactory.getLog(ReplicationEvent.class);

	public ReplicationEvent(final Model owner, final String name,
			final boolean showInTrace,
			final SLAsticReconfigurationOpType reconfOp) {
		super(owner, name, showInTrace, reconfOp);
	}

	@Override
	public boolean concreteEventRoutine() {
		// TODO Auto-generated method stub
		final ComponentReplicationOP repOp = (ComponentReplicationOP) super.getReconfOp();
		final AssemblyContext ac = repOp.getComponent();
		final ResourceContainer rc = repOp.getDestination();
		if (ModelManager.getInstance().getHardwareController().isAllocated(rc.getId())
				&& !ModelManager.getInstance().getAllocationController().hasAllocation(rc.getId(), ac.getId())) {
			ModelManager.getInstance().getAllocationController().add(rc.getId(), ac.getId());
			return true;
			// TODO Auto-generated catch block
		} else {
			ReplicationEvent.LOG
					.warn("Replication failed, target machine allocated: " + ModelManager.getInstance().getHardwareController().isAllocated(rc.getId())
							+ ", component already instantiated: " + ModelManager.getInstance().getAllocationController().hasAllocation(rc.getId(), ac.getId()));
			return false;
		}

	}

}
