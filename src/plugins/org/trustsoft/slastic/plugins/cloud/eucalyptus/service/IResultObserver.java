package org.trustsoft.slastic.plugins.cloud.eucalyptus.service;

import java.util.List;

/**
 * Observer used for the different handlers. When the results of the external
 * program are ready, the resultsReady procedure is called
 * 
 * @author ffi
 * 
 */
public interface IResultObserver {
	/**
	 * called when the results are complete and available for further use
	 * 
	 * @param results
	 */
	void resultsReady(List<String> results);
}
