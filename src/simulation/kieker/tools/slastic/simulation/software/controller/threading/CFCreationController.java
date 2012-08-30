/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package kieker.tools.slastic.simulation.software.controller.threading;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import kieker.tools.slastic.simulation.software.controller.ProgressingFlow;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class CFCreationController {
	private static final CFCreationController INSTANCE = new CFCreationController();

	private final PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();

	private final ThreadPoolExecutor executor =
			new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() - 1,
					Runtime.getRuntime().availableProcessors() * 2, 1000,
					TimeUnit.MILLISECONDS, this.queue);

	private CFCreationController() {}

	public static final CFCreationController getInstance() {
		return CFCreationController.INSTANCE;
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
