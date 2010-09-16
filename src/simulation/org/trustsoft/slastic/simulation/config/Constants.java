package org.trustsoft.slastic.simulation.config;

/**
 * Hold the constants needed for simulation
 * 
 * @author skomp
 * 
 */
public class Constants {
	/**
	 * More detailed output
	 */
	public static final boolean DEBUG = true;

	/**
	 * How to calculate monitoring time <br />
	 * MonTime = (SimTime / SIM_TIME_TO_MON_TIME)
	 */
	public static final long SIM_TIME_TO_MON_TIME = 1000000000;

	/**
	 * How many calls are scheduled before the simulation is started
	 */
	public static final int PRE_BUFFER = 20000;

	/**
	 * If true, simulate a single call and exit
	 */
	public static final boolean SINGLE_TRACE = false;

	/**
	 * The time slice of the processor sharing scheduler
	 */
	public static final long PS_SLICE = 50;

	public static final int CPU_SCALE = 1;
}
