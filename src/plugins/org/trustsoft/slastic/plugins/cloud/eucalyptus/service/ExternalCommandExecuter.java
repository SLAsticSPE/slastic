package org.trustsoft.slastic.plugins.cloud.eucalyptus.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Starts the different tools that are needed.
 * 
 * @author ffi
 * 
 */
public class ExternalCommandExecuter implements IResultObserver {

	private static final Log log = LogFactory
			.getLog(ExternalCommandExecuter.class);

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
	public String executeCommandWithEnv(final EucalyptusCommand command,
			final String enviro) {
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

	@Override
	public synchronized void resultsReady(final List<String> results) {
		if (results.size() > 0) {
			for (final String string : results) {
				this.result += string;
			}
		}
	}
}