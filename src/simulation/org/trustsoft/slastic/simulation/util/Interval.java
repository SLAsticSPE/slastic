package org.trustsoft.slastic.simulation.util;

/**
 * 
 * @author Robert von Massow
 * 
 * @param <T>
 */
public class Interval<T> {
	private double lower, upper;

	private T abt;

	public double getLower() {
		return this.lower;
	}

	public void setLower(final double lower) {
		this.lower = lower;
	}

	public double getUpper() {
		return this.upper;
	}

	public void setUpper(final double upper) {
		this.upper = upper;
	}

	public T getAbt() {
		return this.abt;
	}

	public void setAbt(final T abt) {
		this.abt = abt;
	}

	public boolean contains(final double val) {
		return (this.lower <= val) && (this.upper > val);
	}
}
