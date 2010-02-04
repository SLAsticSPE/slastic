package org.trustsoft.slastic.simulation.software.controller.controlflow;

import org.trustsoft.slastic.simulation.stoexeval.EvaluationProxy;

public class Demand<T extends Number> {

	private final String demand;
	private boolean done;
	private final Class<T> clazzToCast;

	public Demand(final String demand, final Class<T> clazzToCast) {
		this.demand = demand;
		this.clazzToCast = clazzToCast;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(final boolean done) {
		this.done = done;
	}

	/**
	 * This is Hackfrickel! The Evaluator of SimuCom is not casting properly so
	 * we have to do this job using reflection AND generics.
	 * 
	 * @return
	 */
	public T getDemand() {
		final Object ret = EvaluationProxy.evaluate(demand, clazzToCast, null);
		final T evaluate = clazzToCast.cast(ret);
		return evaluate;
	}

}
