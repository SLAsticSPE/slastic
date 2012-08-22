package org.trustsoft.slastic.simulation.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.logging.Log;
import org.trustsoft.slastic.simulation.listeners.IReconfigurationEventListener;
import org.trustsoft.slastic.simulation.model.hardware.controller.HardwareController;
import org.trustsoft.slastic.simulation.model.interfaces.IReconfigurationPlanReceiver;
import org.trustsoft.slastic.simulation.model.mapping.AllocationController;
import org.trustsoft.slastic.simulation.model.reconfiguration.ReconfigurationController;
import org.trustsoft.slastic.simulation.model.software.repository.ComponentTypeController;
import org.trustsoft.slastic.simulation.model.software.system.AssemblyController;

import reconfMM.ReconfigurationModel;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.system.System;
import desmoj.core.simulator.Model;

public final class ModelManager implements IReconfigurationPlanReceiver {
	private static volatile ModelManager INSTANCE;

	// TODO: why static?
	private static volatile long time;

	private final Log log;

	/**
	 * The simulation model
	 */
	private final Model model;

	private final AllocationController allocationController;
	private final HardwareController hardwareController;
	private final ComponentTypeController componentTypeController;
	private final AssemblyController assemblyController;
	private final ReconfigurationController reconfigurationController;
	private final ResourceEnvironment resourceEnvironment;

	// TODO: why do we pass the Log?
	public ModelManager(final Repository repository, final System system,
			final ResourceEnvironment resources, final Allocation allocation,
			final ReconfigurationModel reconfModel, final Model model,
			final Log log) {
		ModelManager.INSTANCE = this;
		this.model = model;
		this.log = log;
		log.info("Model is " + model);
		this.componentTypeController = new ComponentTypeController(repository, reconfModel, model);
		this.assemblyController = new AssemblyController(system, model);
		this.hardwareController = new HardwareController(resources, model);
		this.allocationController = new AllocationController(allocation, model);
		this.reconfigurationController = new ReconfigurationController(reconfModel, model);
		this.resourceEnvironment = resources;

	}

	public AllocationController getAllocationController() {
		return this.allocationController;
	}

	public final HardwareController getHardwareController() {
		return this.hardwareController;
	}

	public final ComponentTypeController getComponentTypeController() {
		return this.componentTypeController;
	}

	public AssemblyController getAssemblyController() {
		return this.assemblyController;
	}

	public ResourceEnvironment getResourceEnvironment() {
		return this.resourceEnvironment;
	}

	public static final ModelManager getInstance() {
		return ModelManager.INSTANCE;
	}

	@Override
	public void reconfigure(final SLAsticReconfigurationPlan plan) {
		this.reconfigurationController.schedulePlan(plan);
	}

	public final ReconfigurationController getReconfigurationController() {
		return this.reconfigurationController;
	}

	public void addReconfEventListener(final IReconfigurationEventListener listener) {
		this.reconfigurationController.addReconfEventListener(listener);
	}

	public Model getModel() {
		return this.model;
	}

	@Override
	public void addReconfigurationEventListener(final IReconfigurationEventListener listener) {
		this.reconfigurationController.addReconfEventListener(listener);
	}

	@Override
	public void removeReconfigurationEventListener(
			final IReconfigurationEventListener listener) {
		this.reconfigurationController.removeReconfEventListener(listener);
	}

	public void reconfigure(final SLAsticReconfigurationPlan plan, final IReconfigurationEventListener listener) {
		// Von Andre: Hi Robert, ich schlage vor, an einen abgeschickten Plan
		// immer genau einen listener zu binden. Dann brauchen wir die
		// add/remove-Listener-Teile auch nicht im Interface
		throw new UnsupportedOperationException("Not supported yet.");
	}

	// TODO: why pass the logger to external objects?
	public Log getLogger() {
		return this.log;
	}

	// TODO: why static?
	public static void markStart() {
		ModelManager.time = java.lang.System.currentTimeMillis();
	}

	// TODO: why static?
	public static void markEnd() {
		PrintWriter pw;
		try {
			final File f = File.createTempFile("sim", ".len");
			pw = new PrintWriter(new FileWriter(f));
			pw.println(java.lang.System.currentTimeMillis() - ModelManager.time);
			pw.flush();
			pw.close();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
