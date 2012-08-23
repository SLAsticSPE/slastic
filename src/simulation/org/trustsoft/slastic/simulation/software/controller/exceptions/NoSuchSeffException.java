package org.trustsoft.slastic.simulation.software.controller.exceptions;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class NoSuchSeffException extends Exception {

	private static final long serialVersionUID = 4155896770149929148L;

	public NoSuchSeffException(final String seff) {
		super(seff);
	}

}
