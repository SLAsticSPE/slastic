package org.trustsoft.slastic.simulation.events.reconfiguration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.trustsoft.slastic.simulation.config.Constants;
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
	public void eventRoutine() {
		// TODO Auto-generated method stub
		PrintWriter pw = null;
		try {
			final File f = File.createTempFile("comprep", ".foo");
			pw = new PrintWriter(new FileWriter(f));
		} catch (final IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pw.println("starting op @" + this.currentTime().getTimeValue());
		final ComponentReplicationOP repOp = (ComponentReplicationOP) super
				.getReconfOp();
		final AssemblyContext ac = repOp.getComponent();
		final ResourceContainer rc = repOp.getDestination();
		if (ModelManager.getInstance().getHwCont().isAllocated(rc.getId())
				&& !ModelManager.getInstance().getAllocCont().hasAllocation(
						rc.getId(), ac.getId())) {
			ModelManager.getInstance().getAllocCont().add(rc.getId(),
					ac.getId());
			ModelManager.getInstance().getReconfController().operationFinished(
					repOp);
			pw.println(this.currentTime().getTimeValue()
					* Constants.SIM_TIME_TO_MON_TIME);
			// TODO Auto-generated catch block
		} else {
			ModelManager.getInstance().getReconfController().operationFailed(
					repOp);
			pw.println("fail\ndest alloc: "
					+ ModelManager.getInstance().getHwCont().isAllocated(
							rc.getId())
					+ "\n"
					+ "already spawned: "
					+ ModelManager.getInstance().getAllocCont().hasAllocation(
							rc.getId(), ac.getId()));

		}
		pw.flush();
		pw.close();

	}

}
