package org.trustsoft.slastic.simulation.util;

public class Interval<T> {
	private double lower, upper;

	private T abt;

	public double getLower() {
		return lower;
	}

	public void setLower(final double lower) {
		this.lower = lower;
	}

	public double getUpper() {
		return upper;
	}

	public void setUpper(final double upper) {
		this.upper = upper;
	}

	public T getAbt() {
		return abt;
	}

	public void setAbt(final T abt) {
		this.abt = abt;
	}

	public boolean contains(final double val) {
		return lower <= val && upper > val;
	}
}
