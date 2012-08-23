package org.trustsoft.slastic.simulation;

/**
 * 
 * @author Andre van Hoorn
 */
// TODO: Why is this class in the simulation package?
public class Version {

	/*
	 * The VERSION string is updated by the Ant build file, which looks for the
	 * pattern: VERSION = <quote>.*<quote>
	 */
	private static final String VERSION = "0.95-20090624";

	static final String COPYRIGHT = "2012 SLAstic Project";

	/**
	 * Not instantiable.
	 */
	private Version()
	{
		super();
	}

	/**
	 * Returns the version String.
	 * 
	 * @return the version String.
	 */
	public static final String getVERSION() {
		return VERSION;
	}
}
