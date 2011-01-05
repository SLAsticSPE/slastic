package org.trustsoft.slastic.plugins.pcm.control.modelManager;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.event.IObservationEvent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.control.components.modelManager.AbstractModelManagerComponent;
import org.trustsoft.slastic.plugins.pcm.PCMModelReader;
import org.trustsoft.slastic.plugins.pcm.control.PCMModelSet;
import org.trustsoft.slastic.reconfiguration.ReconfigurationException;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan;
import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.allocation.AllocationFactory;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class PCMModelManager extends AbstractModelManagerComponent {

	private static final Log log = LogFactory.getLog(PCMModelManager.class);
	private static final String PROP_NAME_PCM_REPOSITORY_FN =
			"pcmrepository_fn";
	private static final String PROP_NAME_PCM_SYSTEM_FN = "pcmsystem_fn";
	private static final String PROP_NAME_PCM_RESOURCEENV_FN =
			"pcmresourceenv_fn";
	private static final String PROP_NAME_PCM_ALLOCATION_FN =
			"pcmallocation_fn";
	protected volatile PCMModelSet pcmModel;

	public PCMModelManager() {
	}

	@Override
	public boolean init() {
		try {
			this.loadPCMModel();
			return true;
		} catch (final IOException ex) {
			PCMModelManager.log.error("Failed to load reconfiguration model",
					ex);
			return false;
		}
	}

	private void loadPCMModel() throws IOException {
		final String pcmRespositoryModel_fn =
				this.getInitProperty(PCMModelManager.PROP_NAME_PCM_REPOSITORY_FN);
		final String pcmSystemModel_fn =
				this.getInitProperty(PCMModelManager.PROP_NAME_PCM_SYSTEM_FN);
		final String pcmResourceEnvironmentModel_fn =
				this.getInitProperty(PCMModelManager.PROP_NAME_PCM_RESOURCEENV_FN);
		final String pcmAllocationModel_fn =
				this.getInitProperty(PCMModelManager.PROP_NAME_PCM_ALLOCATION_FN);
		this.pcmModel =
				PCMModelReader.readPCMModel(pcmRespositoryModel_fn,
						pcmSystemModel_fn, pcmResourceEnvironmentModel_fn,
						pcmAllocationModel_fn);
	}

	AllocationFactory pcmAllocationFactory = AllocationFactory.eINSTANCE;

	/**
	 * Migrates a component deployed as allCtx to the resource container resCont
	 * and returns the new allocation context.
	 * 
	 * @param allCtx
	 * @param resCont
	 * @return
	 */
	public synchronized AllocationContext componentMigration(
			final AllocationContext allCtx, final ResourceContainer resCont) {
		PCMModelManager.log.debug("Model reconfiguration: componentMigration ("
				+ allCtx + ", " + resCont + ")");

		/* Create new allocation context */
		final AllocationContext newAllCtx =
				this.pcmAllocationFactory.createAllocationContext();
		newAllCtx.setAssemblyContext_AllocationContext(allCtx
				.getAssemblyContext_AllocationContext());
		newAllCtx.setResourceContainer_AllocationContext(resCont);

		// TODO: start transaction
		/* 1. Remove old allocation context */
		if (!this.pcmModel.getPCMAllocation()
				.getAllocationContexts_Allocation().remove(allCtx)) {
			PCMModelManager.log.warn("Failed to remove allocation context"
					+ allCtx);
		} else {
			PCMModelManager.log.debug("Removed allocation context" + allCtx);
		}

		/* 2. Add new allocation context */
		if (!this.pcmModel.getPCMAllocation()
				.getAllocationContexts_Allocation().add(newAllCtx)) {
			PCMModelManager.log.warn("Failed to add new allocation context"
					+ newAllCtx);
		} else {
			PCMModelManager.log.debug("Removed allocation context" + newAllCtx);
		}

		// TODO: stop transaction

		return newAllCtx;
	}

	/**
	 * Replicates the assembly context asmCtx to the resource container resCont
	 * and returns the new allocation context.
	 * 
	 * @param asmCtx
	 * @param resCont
	 * @return
	 */
	public synchronized AllocationContext componentReplication(
			final AssemblyContext asmCtx, final ResourceContainer resCont) {
		PCMModelManager.log
				.debug("Model reconfiguration: componentReplication (" + asmCtx
						+ ", " + resCont + ")");

		/* Create new allocation context */
		final AllocationContext newAllCtx =
				this.pcmAllocationFactory.createAllocationContext();
		newAllCtx.setAssemblyContext_AllocationContext(asmCtx);
		newAllCtx.setResourceContainer_AllocationContext(resCont);

		// TODO: start transaction

		/* 1. Add new allocation context */
		if (!this.pcmModel.getPCMAllocation()
				.getAllocationContexts_Allocation().add(newAllCtx)) {
			PCMModelManager.log.warn("Failed to add new allocation context"
					+ newAllCtx);
		} else {
			PCMModelManager.log.debug("Removed allocation context" + newAllCtx);
		}

		// TODO: stop transaction

		return newAllCtx;
	}

	/**
	 * Replicates the allocation context allCtx.
	 * 
	 * @param allCtx
	 * @return true on success, false otherwise
	 */
	public synchronized boolean componentDeReplication(
			final AllocationContext allCtx) {
		boolean success;

		PCMModelManager.log
				.debug("Model reconfiguration: componentDeReplication ("
						+ allCtx + ")");

		// TODO: start transaction

		/* 1. Remove old allocation context */
		success =
				this.pcmModel.getPCMAllocation()
						.getAllocationContexts_Allocation().remove(allCtx);
		if (!success) {
			PCMModelManager.log.warn("Failed to remove allocation context"
					+ allCtx);
		} else {
			PCMModelManager.log.debug("Removed allocation context" + allCtx);
		}

		// TODO: stop transaction

		return success;
	}

	@Override
	public boolean execute() {
		return true;
	}

	@Override
	public void doReconfiguration(final SLAsticReconfigurationPlan plan) {
		// TODO Auto-generated method stub
	}

	@Override
	public void handleEvent(final IEvent ev) {
	}

	@Override
	public void terminate(final boolean error) {
		// do nothing
	}

	@Override
	public void newObservation(final IObservationEvent event) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public PCMModelSet getPcmModel() {
		return this.pcmModel;
	}

	@Override
	public void doReconfiguration(final ReconfigurationPlan plan)
			throws ReconfigurationException {
		throw new UnsupportedOperationException();
	}
}
