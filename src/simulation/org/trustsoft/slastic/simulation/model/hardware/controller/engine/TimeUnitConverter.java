package org.trustsoft.slastic.simulation.model.hardware.controller.engine;

/**
 * 
 * @author Robert von Massow
 * 
 */
// TODO: For what reason aren't we using the functionality provided by Java's TimeUnit class?
public class TimeUnitConverter {
	public static long toNS(final long in, final TimeUnit u) {
		switch (u) {
		case NS:
			return in;
		case MuS:
			return in * 1000;
		case MS:
			return in * 1000000;
		case S:
			return in * 1000000000;
		default:
			return -1;
		}
	}

}
