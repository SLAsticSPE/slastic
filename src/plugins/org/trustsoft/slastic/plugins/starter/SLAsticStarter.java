package org.trustsoft.slastic.plugins.starter;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.FrameworkInstance;
import org.trustsoft.slastic.common.util.PropertiesFileUtils;

/**
 * 
 * @author Andre van Hoorn
 */
public class SLAsticStarter {

	private static final Log log = LogFactory.getLog(SLAsticStarter.class);
	private static CommandLine cmdl = null;
	private static final CommandLineParser cmdlParser = new BasicParser();
	private static final HelpFormatter cmdHelpFormatter = new HelpFormatter();
	private static final Options cmdlOpts = new Options();
	private static final String CMD_LONG_OPT_START_FRAMEWORK = "start-framework";
	private static final String CMD_LONG_OPT_START_SIMULATION = "start-simulation";

	static {
		SLAsticStarter.cmdlOpts.addOption(OptionBuilder.withArgName("file").hasArg()
				.withLongOpt(SLAsticStarter.CMD_LONG_OPT_START_FRAMEWORK).isRequired(true)
				.withDescription("SLAstic.Framework configuration file")
				.withValueSeparator('=').create("f"));
		SLAsticStarter.cmdlOpts.addOption(OptionBuilder.withArgName("file").hasArg()
				.withLongOpt(SLAsticStarter.CMD_LONG_OPT_START_SIMULATION).isRequired(false)
				.withDescription("SLAstic.Simulator configuration file")
				.withValueSeparator('=').create("s"));
	}

	public static void main(final String[] args) {
		SLAsticStarter.log.info("Hi, this is SLAsticControl");

		if (!SLAsticStarter.parseArgs(args)) {
			System.exit(1);
		}

		// start framework?
		// TODO: wait until framework ist initialized
		if (SLAsticStarter.cmdl.hasOption(SLAsticStarter.CMD_LONG_OPT_START_FRAMEWORK)) {
			final FrameworkInstance frameworkInst = SLAsticStarter.initSLAsticInstanceFromArgs();
			if (frameworkInst == null) {
				SLAsticStarter.log.error("Failed to init SLAstic framework instance");
				System.exit(1);
			}
			if (!frameworkInst.run()) {
				SLAsticStarter.log.fatal("SLAstic framework instance return with error");
				System.exit(1);
			}
			SLAsticStarter.log.info("SLAstic framework instance started");
		}

		// start simulation?
		if (SLAsticStarter.cmdl.hasOption(SLAsticStarter.CMD_LONG_OPT_START_SIMULATION)) {
			final SLAsticSimulatorInstance simInst = SLAsticStarter.initSLAsticSimulationInstanceFromArgs();
			if (simInst == null) {
				SLAsticStarter.log.error("Failed to init SLAstic simulator instance");
				System.exit(1);
			}
			simInst.run();
			SLAsticStarter.log.info("SLAstic simulator instance started");
		}

		SLAsticStarter.log.info("Bye, this was SLAsticControl");
	}

	/**
	 * Initializes and returns a SLAstic simulation instance.
	 * 
	 * @return the initialized instance; null on error
	 */
	private static SLAsticSimulatorInstance initSLAsticSimulationInstanceFromArgs()
			throws IllegalArgumentException {
		final String simulationConfigurationFile = SLAsticStarter.cmdl
				.getOptionValue(SLAsticStarter.CMD_LONG_OPT_START_SIMULATION);

		if (simulationConfigurationFile == null) {
			SLAsticStarter.log.fatal("Missing value for command line option '"
					+ SLAsticStarter.CMD_LONG_OPT_START_SIMULATION + "'");
			throw new IllegalArgumentException(
					"Missing value for command line option '"
							+ SLAsticStarter.CMD_LONG_OPT_START_SIMULATION + "'");
		}

		SLAsticSimulatorInstance inst = null;
		try {
			inst = new SLAsticSimulatorInstance(
					PropertiesFileUtils
							.loadPropertiesFile(simulationConfigurationFile));
		} catch (final Exception exc) {
			SLAsticStarter.log.error("Error creating SLAsticInstance", exc);
			throw new IllegalArgumentException(
					"Error creating SLAsticInstance", exc);
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
		final String configurationFile = SLAsticStarter.cmdl
				.getOptionValue(SLAsticStarter.CMD_LONG_OPT_START_FRAMEWORK);

		if (configurationFile == null) {
			SLAsticStarter.log.fatal("Missing value for command line option '"
					+ SLAsticStarter.CMD_LONG_OPT_START_FRAMEWORK + "'");
			throw new IllegalArgumentException(
					"Missing value for command line option '"
							+ SLAsticStarter.CMD_LONG_OPT_START_FRAMEWORK + "'");
		}

		FrameworkInstance inst = null;
		try {
			inst = new FrameworkInstance(
					PropertiesFileUtils.loadPropertiesFile(configurationFile));
		} catch (final Exception exc) {
			SLAsticStarter.log.error("Error creating SLAsticInstance", exc);
			throw new IllegalArgumentException(
					"Error creating SLAsticInstance", exc);
		}
		return inst;
	}

	static boolean parseArgs(final String[] args) {
		try {
			SLAsticStarter.cmdl = SLAsticStarter.cmdlParser.parse(SLAsticStarter.cmdlOpts, args);

		} catch (final ParseException e) {
			System.err.println("Error parsing arguments: " + e.getMessage());
			SLAsticStarter.printUsage();
			return false;
		}
		// org.trustsoft.slastic.control.sla.parser.ParserComponent p;
		// p.

		return true;
	}

	private static void printUsage() {
		SLAsticStarter.cmdHelpFormatter.printHelp(
				FrameworkInstance.class.getName(), SLAsticStarter.cmdlOpts);
	}
}
