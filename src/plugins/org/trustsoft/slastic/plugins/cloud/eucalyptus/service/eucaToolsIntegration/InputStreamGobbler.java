package org.trustsoft.slastic.plugins.cloud.eucalyptus.service.eucaToolsIntegration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * The listener for input of an external program da
 * 
 * @author Florian Fittkau
 * 
 */
public class InputStreamGobbler extends Thread {
	// references
	private final InputStream is;
	private final IResultObserver resultObs;

	public InputStreamGobbler(final InputStream is, final IResultObserver resultObs) {
		this.is = is;
		this.resultObs = resultObs;
	}

	@Override
	public void run() {
		final List<String> results = new LinkedList<String>();
		try {
			final InputStreamReader isr = new InputStreamReader(this.is);
			final BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				results.add(line);
			}

			this.resultObs.resultsReady(results);
			br.close();
		} catch (final IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
