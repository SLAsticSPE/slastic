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

import kieker.tools.slastic.simulation.software.controller.ProgressingFlow;

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
