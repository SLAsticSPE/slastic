package org.trustsoft.slastic.simulation.software.controller.controlflow;

public class Demand<T extends Number> {

	private final String demand;
	private boolean done;
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
