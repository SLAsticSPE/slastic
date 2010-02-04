package org.trustsoft.slastic.simulation.user.model;

public final class ScenarioSpec {

	private boolean open;

	private int numUsers;

	private double time;

	public boolean isOpen() {
		return open;
	}

	public int getNumUsers() {
		return numUsers;
	}

	public double getTime() {
		return time;
	}

	public int incUsers() {
		if (open) {
			return ++numUsers;
		}
		return numUsers;
	}

	public int decUsers() {
		if (open) {
			return --numUsers;
		}
		return numUsers;
	}
}
