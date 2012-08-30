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

package kieker.tools.slastic.simulation.software.controller.controlflow;

/**
 * 
 * @author Robert von Massow
 * 
 * @param <T>
 */
public class Demand<T extends Number> {

	private final String demand;
	private volatile boolean done;
	@SuppressWarnings("unused")
	private final Class<T> clazzToCast;

	public Demand(final String demand, final Class<T> clazzToCast) {
		this.demand = demand;
		this.clazzToCast = clazzToCast;
	}

	public final boolean isDone() {
		return this.done;
	}

	public final void setDone(final boolean done) {
		this.done = done;
	}

	/**
	 * This is Hackfrickel! The Evaluator of SimuCom is not casting properly so
	 * we have to do this job using reflection AND generics.
	 * 
	 * @return
	 */
	public final Integer getDemand() {
		// final Object ret = EvaluationProxy.evaluate(demand, clazzToCast,
		// null);
		// final T evaluate = clazzToCast.cast(ret);
		return Integer.parseInt(this.demand.replaceAll("\\s", ""));
	}

}
