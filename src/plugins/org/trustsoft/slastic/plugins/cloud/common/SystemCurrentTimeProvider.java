package org.trustsoft.slastic.plugins.cloud.common;


/**
 * 
 * @author Andre van Hoorn
 *
 */
public class SystemCurrentTimeProvider implements ICurrentTimeProvider {

	@Override
	public long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

}
