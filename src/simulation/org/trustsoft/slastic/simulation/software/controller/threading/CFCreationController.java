package org.trustsoft.slastic.simulation.software.controller.threading;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.trustsoft.slastic.simulation.software.controller.ProgressingFlow;

public class CFCreationController {

	private final PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();

	private final ThreadPoolExecutor executor = new ThreadPoolExecutor(Runtime
			.getRuntime().availableProcessors() - 1, Runtime.getRuntime()
			.availableProcessors() * 2, 1000, TimeUnit.MILLISECONDS, this.queue);

	private static final CFCreationController instance = new CFCreationController();

	private CFCreationController() {
	}

	public static final CFCreationController getInstance() {
		return CFCreationController.instance;
	}

	public final void add(final ProgressingFlow pf) {
		this.executor.execute(new CFGenerationTask(pf, 5));
	}

	protected final void doSomethingWithMe(final ProgressingFlow pf) {
		if (pf.getStatus() == CFCreationStatus.PAUSED) {
			this.reAdd(pf);
		}
	}

	private void reAdd(final ProgressingFlow pf) {
		this.executor.execute(new CFGenerationTask(pf, 5));
	}
}
