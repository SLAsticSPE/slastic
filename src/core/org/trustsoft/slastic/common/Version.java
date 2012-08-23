package org.trustsoft.slastic.common;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class Version {

	/*
	 * The VERSION string is updated by the Ant build file, which looks for the
	 * pattern: VERSION = <quote>.*<quote>
	 */
	private static final String VERSION = "0.01a-SNAPSHOT-20120626";

	static final String COPYRIGHT = "Copyright (c) 2012 SLAstic Project";

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
		return Version.VERSION;
	}
}
