package org.trustsoft.slastic.plugins.cloud.common;


/**
 * 
 * @author Andre van Hoorn
 * 
 */
public interface ICurrentTimeProvider {
	public static ICurrentTimeProvider SYSTEM_CURRENT_TIME_PROVIDER =
			new SystemCurrentTimeProvider();

	/**
	 * Returns the current time in milliseconds since January 1, 1970 (UTC).
	 */
	public long getCurrentTimeMillis();
}
