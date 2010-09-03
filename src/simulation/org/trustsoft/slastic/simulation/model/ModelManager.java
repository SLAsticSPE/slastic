package org.trustsoft.slastic.simulation.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.trustsoft.slastic.simulation.listeners.ReconfEventListener;
import org.trustsoft.slastic.simulation.model.hardware.controller.HardwareController;
import org.trustsoft.slastic.simulation.model.interfaces.IReconfPlanReceiver;
import org.trustsoft.slastic.simulation.model.mapping.AllocationController;
import org.trustsoft.slastic.simulation.model.reconfiguration.ReconfigurationController;
import org.trustsoft.slastic.simulation.model.software.repository.ComponentController;
import org.trustsoft.slastic.simulation.model.software.system.AssemblyController;

import reconfMM.ReconfigurationModel;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.system.System;
import desmoj.core.simulator.Model;

public class ModelManager implements IReconfPlanReceiver {

	private final AllocationController allocCont;
	private final HardwareController hwCont;
	private final ComponentController compCont;
	private final AssemblyController assemblyCont;
	private final ReconfigurationController reconfCont;
	private static ModelManager instance;
	private final List<SLAsticReconfigurationPlan> plans = new LinkedList<SLAsticReconfigurationPlan>();
	private final Model model;
	private final Log log;
	private final ResourceEnvironment re;

	private static File f;
	private static long time;

	public ModelManager(final Repository repository, final System system,
			final ResourceEnvironment resources, final Allocation allocation,
			final ReconfigurationModel reconfModel, final Model model,
			final Log log) {
		ModelManager.instance = this;
		this.model = model;
		this.log = log;
		log.info("Model is " + model);
		this.compCont = new ComponentController(repository, reconfModel, model);
		this.assemblyCont = new AssemblyController(system, model);
		this.hwCont = new HardwareController(resources, model);
		this.allocCont = new AllocationController(allocation, model);
		this.reconfCont = new ReconfigurationController(reconfModel, model);
		this.re = resources;

	}

	public AllocationController getAllocCont() {
		return this.allocCont;
	}

	public HardwareController getHwCont() {
		return this.hwCont;
	}

	public ComponentController getCompCont() {
		return this.compCont;
	}

	public AssemblyController getAssemblyCont() {
		return this.assemblyCont;
	}

	public static ModelManager getInstance() {
		return ModelManager.instance;
	}

	@Override
	public void reconfigure(final SLAsticReconfigurationPlan plan) {
		this.reconfCont.schedulePlan(plan);
	}

	public ReconfigurationController getReconfController() {
		return this.reconfCont;
	}

	public void addReconfEventListener(final ReconfEventListener listener) {
		this.reconfCont.addReconfEventListener(listener);
	}

	public Model getModel() {
		return this.model;
	}

	@Override
	public void addReconfigurationEventListener(
			final ReconfEventListener listener) {
		this.reconfCont.addReconfEventListener(listener);
	}

	@Override
	public void removeReconfigurationEventListener(
			final ReconfEventListener listener) {
		this.reconfCont.removeReconfEventListener(listener);
	}

	public void reconfigure(final SLAsticReconfigurationPlan plan,
			final ReconfEventListener listener) {
		// Von Andre: Hi Robert, ich schlage vor, an einen abgeschickten Plan
		// immer genau einen listener zu binden. Dann brauchen wir die
		// add/remove-Listener-Teile auch nicht im Interface
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public Log getLogger() {
		return this.log;
	}

	public ResourceEnvironment getResources() {
		return this.re;
	}

	public static void markStart() {
		ModelManager.time = java.lang.System.nanoTime();
	}

	public static void markEnd(final long ltime) {
		PrintWriter pw;
		try {
			final File f = File.createTempFile("sim", ".len");
			pw = new PrintWriter(new FileWriter(f));
			pw.println(ltime - ModelManager.time);
			pw.flush();
			pw.close();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the reconfCont
	 */
	public final ReconfigurationController getReconfCont() {
		return this.reconfCont;
	}
}
