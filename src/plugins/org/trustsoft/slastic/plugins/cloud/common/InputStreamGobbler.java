/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package org.trustsoft.slastic.plugins.cloud.common;

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
