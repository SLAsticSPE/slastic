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

package kieker.tools.slastic.plugins.starter;

import java.io.File;
import java.io.IOException;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.tools.slastic.common.FrameworkInstance;
import kieker.tools.slastic.common.util.FileUtils;
import kieker.tools.slastic.common.util.PropertiesFileUtils;

/**
 * Starts a {@link FrameworkInstance} and (if requested) a connected {@link SLAsticSimulatorInstance}.
 * 
 * @author Andre van Hoorn
 */
@SuppressWarnings("static-access")
public class SLAsticStarter {

	private static final Log LOG = LogFactory.getLog(SLAsticStarter.class);

	private static CommandLine cmdl = null;
	private static final CommandLineParser cmdlParser = new BasicParser();
	private static final HelpFormatter cmdHelpFormatter = new HelpFormatter();
	private static final Options cmdlOpts = new Options();
	private static final String CMD_LONG_OPT_START_FRAMEWORK = "start-framework";
	private static final String CMD_LONG_OPT_START_SIMULATION = "start-simulation";

	/**
	 * Will be initialized in {@link #initSLAsticInstanceFromArgs()}
	 */
	private static String frameworkConfigurationFile;

	/**
	 * Will be initialized in {@link #initSLAsticSimulationInstanceFromArgs()}
	 */
	private static String simulationConfigurationFile;

	static {
		cmdlOpts.addOption(OptionBuilder.withArgName("file").hasArg().withLongOpt(CMD_LONG_OPT_START_FRAMEWORK).isRequired(true)
				.withDescription("SLAstic.Framework configuration file").withValueSeparator('=').create("f"));
		cmdlOpts.addOption(OptionBuilder.withArgName("file").hasArg().withLongOpt(CMD_LONG_OPT_START_SIMULATION).isRequired(false)
				.withDescription("SLAstic.Simulator configuration file").withValueSeparator('=').create("s"));
	}

	public static void main(final String[] args) {
		LOG.info("Hi, this is SLAsticControl");

		if (!SLAsticStarter.parseArgs(args)) {
			System.exit(1);
		}

		// start framework?
		// TODO: wait until framework is initialized
		if (cmdl.hasOption(CMD_LONG_OPT_START_FRAMEWORK)) {
			final FrameworkInstance frameworkInst = SLAsticStarter.initSLAsticInstanceFromArgs();
			if (frameworkInst == null) {
				LOG.error("Failed to init SLAstic framework instance");
				System.exit(1);
			}
			// copy configuration file to the project directory
			try {
				FileUtils.copyFileToDir(new File(frameworkConfigurationFile), new File(frameworkInst.getConfiguration().getRootContext().getDirectoryLocation()));
			} catch (final IOException e) {
				LOG.fatal("Failed to copy the framework configuration file", e);
			}
			if (!frameworkInst.run()) {
				LOG.fatal("SLAstic framework instance return with error");
				System.exit(1);
			}
			LOG.info("SLAstic framework instance started");
		}

		// start simulation?
		if (cmdl.hasOption(CMD_LONG_OPT_START_SIMULATION)) {
			final SLAsticSimulatorInstance simInst = SLAsticStarter.initSLAsticSimulationInstanceFromArgs();
			if (simInst == null) {
				LOG.error("Failed to init SLAstic simulator instance");
				System.exit(1);
			}
			simInst.run();
			LOG.info("SLAstic simulator instance started");
		}

		// wait until framework is terminated?

		LOG.info("Bye, this was SLAsticControl");
	}

	/**
	 * Initializes and returns a SLAstic simulation instance.
	 * 
	 * @return the initialized instance; null on error
	 */
	private static SLAsticSimulatorInstance initSLAsticSimulationInstanceFromArgs()
			throws IllegalArgumentException {
		simulationConfigurationFile = cmdl.getOptionValue(CMD_LONG_OPT_START_SIMULATION);

		if (simulationConfigurationFile == null) {
			LOG.fatal("Missing value for command line option '" + CMD_LONG_OPT_START_SIMULATION + "'");
			throw new IllegalArgumentException("Missing value for command line option '" + CMD_LONG_OPT_START_SIMULATION + "'");
		}

		SLAsticSimulatorInstance inst = null;
		try {
			inst = new SLAsticSimulatorInstance(PropertiesFileUtils.loadPropertiesFile(simulationConfigurationFile));
		} catch (final Exception exc) {
			LOG.error("Error creating SLAsticInstance", exc);
			throw new IllegalArgumentException("Error creating SLAsticInstance", exc);
		}
		return inst;
	}

	/**
	 * Initializes and returns a SLAstic instance.
	 * 
	 * @return the initialized instance; null on error
	 */
	private static FrameworkInstance initSLAsticInstanceFromArgs()
			throws IllegalArgumentException {
		frameworkConfigurationFile = cmdl.getOptionValue(CMD_LONG_OPT_START_FRAMEWORK);

		if (frameworkConfigurationFile == null) {
			LOG.fatal("Missing value for command line option '" + CMD_LONG_OPT_START_FRAMEWORK + "'");
			throw new IllegalArgumentException("Missing value for command line option '" + CMD_LONG_OPT_START_FRAMEWORK + "'");
		}

		FrameworkInstance inst = null;
		try {
			inst = new FrameworkInstance(PropertiesFileUtils.loadPropertiesFile(frameworkConfigurationFile));
		} catch (final Exception exc) {
			LOG.error("Error creating SLAsticInstance", exc);
			throw new IllegalArgumentException("Error creating SLAsticInstance", exc);
		}
		return inst;
	}

	static boolean parseArgs(final String[] args) {
		try {
			cmdl = cmdlParser.parse(cmdlOpts, args);
		} catch (final ParseException e) {
			System.err.println("Error parsing arguments: " + e.getMessage());
			SLAsticStarter.printUsage();
			return false;
		}

		return true;
	}

	private static void printUsage() {
		cmdHelpFormatter.printHelp(FrameworkInstance.class.getName(), cmdlOpts);
	}
}
