package org.trustsoft.slastic.plugins.cloud.eucalyptus.service;

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
