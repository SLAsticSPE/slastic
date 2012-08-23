package org.trustsoft.slastic.simulation.software.controller.threading;

import org.trustsoft.slastic.simulation.software.controller.ProgressingFlow;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class CFGenerationTask implements Runnable, Comparable<CFGenerationTask> {

	private final ProgressingFlow pf;
	private final int evaluations;

	public CFGenerationTask(final ProgressingFlow pf, final int evaluations) {
		this.pf = pf;
		this.evaluations = evaluations > 0 ? evaluations : 1;
	}

	@Override
	public void run() {
		for (int i = 0; (i < this.evaluations) && (this.pf.getStatus() == CFCreationStatus.PAUSED); i++) {
			this.pf.next();
		}
		CFCreationController.getInstance().doSomethingWithMe(this.pf);
	}

	@Override
	public int compareTo(final CFGenerationTask o) {
		return o.getPf().getNodes().size() > this.pf.getNodes().size() ? -1 : 1;
	}

	/**
	 * @return the pf
	 */
	public final ProgressingFlow getPf() {
		return this.pf;
	}
}
