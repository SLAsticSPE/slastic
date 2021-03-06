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

package kieker.tools.slastic.plugins.cloud.common;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Starts the different tools that are needed.
 * 
 * @author Florian Fittkau
 * 
 */
public class ExternalCommandExecuter implements IResultObserver {

	private static final Log log = LogFactory.getLog(ExternalCommandExecuter.class);

	private final boolean isDummyMode;

	private String result;

	public ExternalCommandExecuter() {
		this(false);
	}

	/**
	 * Constructs a new {@link ExternalCommandExecuter} with the opportunity to
	 * specify whether it should run in a so-called dummy mode or not. In the
	 * dummy mode, the {@link ExternalCommandExecuter} simply dumps the commands
	 * to be issued into the log rather actually executing them.
	 * 
	 * @param enableDummyMode
	 */
	public ExternalCommandExecuter(final boolean enableDummyMode) {
		this.isDummyMode = enableDummyMode;
	}

	/**
	 * executes the command in a given environment directory/path
	 * 
	 * @param commands
	 * @param enviro
	 */
	public String executeCommandWithEnv(final AbstractCommand command, final String enviro) {
		Process proc = null;
		this.result = "";

		try {
			final Runtime rt = Runtime.getRuntime();

			ExternalCommandExecuter.log.info(enviro + "$ "
					+ command.getCommandString().replaceAll(" && ", " "));
			if (this.isDummyMode) {
				return "DUMMY MODE OUTPUT";
			}

			final String[] commandsDummy =
					command.getCommandString().split(" && ");

			proc = rt.exec(commandsDummy, null, new File(enviro));

			final InputStreamGobbler errorGobbler =
					new InputStreamGobbler(proc.getErrorStream(), this);

			final InputStreamGobbler inputGobbler =
					new InputStreamGobbler(proc.getInputStream(), this);

			errorGobbler.start();
			inputGobbler.start();

			proc.waitFor();
			errorGobbler.join(10000);
			inputGobbler.join(10000);
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}

		return this.result;
	}

	/**
	 * Spawn a thread that will execute the command in the given environment
	 * directory/path after waiting the given time period in milliseconds. Note,
	 * that this method returns immediately.
	 * 
	 */
	// TODO: allow the registration of an observer to be notified when the
	// asynchronous command has been executed. It should then be
	// possible to fetch the result string from this object
	public void executeCommandWithEnvAndDelayAsync(final AbstractCommand command,
			final String enviro, final long delayMillis) {
		final Runnable r = new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(delayMillis);
					ExternalCommandExecuter.this.executeCommandWithEnv(command, enviro);
				} catch (final InterruptedException e) {
					ExternalCommandExecuter.log.error("Delayed executor thread (command: " + command.getCommandString() + ") was interrupted: "
							+ e.getMessage(), e);
				}
			}
		};

		final Thread thread = new Thread(r);
		thread.start();
	}

	@Override
	public synchronized void resultsReady(final List<String> results) {
		if (results.size() > 0) {
			for (final String string : results) {
				this.result += string;
			}
		}
	}
}
