package org.trustsoft.slastic.plugins.starter.kieker;

import kieker.monitoring.core.configuration.Configuration;
import kieker.monitoring.core.controller.IMonitoringController;
import kieker.monitoring.core.controller.MonitoringController;
import kieker.monitoring.writer.namedRecordPipe.PipeWriter;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
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
public abstract class AbstractAnalysisStarter {

	private static final Log log = LogFactory
			.getLog(AbstractAnalysisStarter.class);
	protected volatile CommandLine cmdl = null;
	private final CommandLineParser cmdlParser = new BasicParser();
	private final HelpFormatter cmdHelpFormatter = new HelpFormatter();
	private final Options cmdlOpts = new Options();

	/**
	 * Must not be used for construction
	 */
	@SuppressWarnings("unused")
	private AbstractAnalysisStarter() {
		this.args = null;
	}

	/** Command line arguments */
	private final String[] args;

	private final static Option[] CMDL_OPTIONS =
	{ CmdlOptions.CMDL_OPT_START_FRAMEWORK,
			CmdlOptions.CMDL_OPT_KIEKER_PIPENAME };

	/**
	 * Creates a new {@link AbstractAnalysisStarter} based on the given argument
	 * list.
	 * 
	 * @param args
	 */
	public AbstractAnalysisStarter(final String[] args,
			final Option[] additionalCmdlOptions) {
		this.args = args;
		this.addCmdlOpt(AbstractAnalysisStarter.CMDL_OPTIONS);
		this.addCmdlOpt(additionalCmdlOptions);
	}

	/**
	 * Adds the given options to the list of command-line options.
	 * 
	 * @param options
	 */
	private void addCmdlOpt(final Option[] options) {
		for (final Option opt : options) {
			this.cmdlOpts.addOption(opt);
		}
	}

	protected abstract boolean init();

	/**
	 * Parses the command-line argument and start the SLAstic framework instance
	 * as well as the Kieker monitoring log reader. This method is syncronous
	 * and returns after the analysis has terminated.
	 * 
	 * @return
	 */
	public boolean start() {
		/**
		 * Parse command-line arguments and initialize variable #cmdl.
		 */
		if (!this.parseArgs()) {
			AbstractAnalysisStarter.log.error("Failed to parse arguments");
			return false;
		}

		/**
		 * Force implementing classes to initialize
		 */
		if (!this.init()) {
			AbstractAnalysisStarter.log.error("init failed");
			return false;
		}

		/**
		 * Initialize the framework based on the configuration file passed as
		 * command-line argument and start the instance. Using the named pipe
		 * reader, the framework instance executes concurrently and the
		 * following method returns immediately.
		 */
		final FrameworkInstance frameworkInst =
				this.initAndStartSLAsticFramework();
		if (frameworkInst == null) {
			AbstractAnalysisStarter.log
					.error("Error while initializing/starting the SLAstic framework");
			System.exit(1);
		}

	
		/**
		 * Starts the Kieker JMS reader. The records are passed to the SLAstic
		 * monitoring manager via a named pipe. The following method is blocking
		 * in that it returns after the reader has terminated.
		 */
		final boolean logReplayRes = this.startReader();
		AbstractAnalysisStarter.log.debug("initAndStartJMS terminated with"
				+ logReplayRes);

		/**
		 * Send a termination event to the SLAstic framework.
		 */
		frameworkInst.terminate(logReplayRes);
		AbstractAnalysisStarter.log
				.debug("SLAstic framework instance terminated");

		return logReplayRes;
	}

	private boolean startReader() {
		final Configuration configuration =
				Configuration.createDefaultConfiguration();

		/* Configuring name pipe writer */
		configuration.setProperty(Configuration.WRITER_CLASSNAME,
				PipeWriter.class.getName());
		configuration.setProperty(PipeWriter.CONFIG_PIPENAME,
				"monitoringPipe0");
		// TODO: Is this correct?? Enable "replay mode", i.e., the logging
		// timestamps in the records are kept as-is
		configuration.setProperty(Configuration.AUTO_SET_LOGGINGTSTAMP,
				Boolean.toString(false));
		// Set controller name
		configuration
				.setProperty(Configuration.CONTROLLER_NAME, "ReplayToPipe");

		final IMonitoringController ctrl =
				MonitoringController.createInstance(configuration);

		return this.startReader(ctrl);
	}

	protected abstract boolean startReader(
			IMonitoringController monitoringController);

	/**
	 * Creates and starts a {@link FrameworkInstance} and returns immediately.
	 * 
	 * @return the started instance; null on error
	 */
	private FrameworkInstance initAndStartSLAsticFramework() {
		final FrameworkInstance frameworkInst =
				this.initSLAsticInstanceFromArgs();
		if (frameworkInst == null) {
			AbstractAnalysisStarter.log
					.error("Failed to init SLAstic framework instance");
			return null;
		}
		AbstractAnalysisStarter.log
				.debug("Starting SLAstic framework instance ...");
		if (!frameworkInst.run()) {
			AbstractAnalysisStarter.log
					.fatal("SLAstic framework instance returned with error");
			return null;
		}
		return frameworkInst;
	}

	/**
	 * Initializes and returns a SLAstic instance.
	 * 
	 * @return the initialized instance; null on error
	 */
	private FrameworkInstance initSLAsticInstanceFromArgs()
			throws IllegalArgumentException {
		final String configurationFile =
				this.cmdl.getOptionValue(CmdlOptions.CMDL_OPT_START_FRAMEWORK
						.getLongOpt());

		if (configurationFile == null) {
			AbstractAnalysisStarter.log
					.fatal("Missing value for command line option '"
							+ CmdlOptions.CMDL_OPT_START_FRAMEWORK.getLongOpt()
							+ "'");
			throw new IllegalArgumentException(
					"Missing value for command line option '"
							+ CmdlOptions.CMDL_OPT_START_FRAMEWORK.getLongOpt()
							+ "'");
		}

		FrameworkInstance inst = null;

		try {
			inst =
					new FrameworkInstance(
							PropertiesFileUtils
									.loadPropertiesFile(configurationFile));
		} catch (final Exception exc) {
			AbstractAnalysisStarter.log.error("Error creating SLAsticInstance",
					exc);
			throw new IllegalArgumentException(
					"Error creating SLAsticInstance", exc);
		}

		return inst;
	}

	/**
	 * Parses the command-line arguments the value of which are then accessible
	 * via the static field {@link #cmdl}.
	 * 
	 * @param args
	 * @return true on success; false otherwise
	 */
	public boolean parseArgs() {
		try {
			this.cmdl = this.cmdlParser.parse(this.cmdlOpts, this.args);

		} catch (final ParseException e) {
			System.err.println("Error parsing arguments: " + e.getMessage());
			this.printUsage();
			return false;
		}

		return true;
	}

	/**
	 * Prints usage information to stout.
	 * 
	 */
	private void printUsage() {
		this.cmdHelpFormatter.printHelp(FrameworkInstance.class.getName(),
				this.cmdlOpts);
	}

	/**
	 * 
	 * @param optName
	 * @return
	 */
	public String getStringOptionValue(
			final String optName) {
		return this.cmdl.getOptionValue(optName);
	}

	/**
	 * 
	 * @param optName
	 * @return
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public String getStringOptionValueNotEmpty(
			final String optName) throws NullPointerException,
			IllegalArgumentException {
		return CmdlOptions.stringOptionValueNotEmpty(this.cmdl, optName);
	}

	/**
	 * 
	 * @param optName
	 * @return
	 */
	public String[] getStringOptionValues(
			final String optName) {
		return this.cmdl.getOptionValues(optName);
	}

	/**
	 * 
	 * @param optName
	 * @return
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public String[] stringOptionValuesNotNullNotEmpty(
			final String optName) throws NullPointerException,
			IllegalArgumentException {
		return CmdlOptions
				.stringOptionValuesNotNullNotEmpty(this.cmdl, optName);
	}

	/**
	 * 
	 * @param optName
	 * @return
	 * @throws NullPointerException
	 */
	public boolean getBooleanOptionValue(
			final String optName) throws NullPointerException {
		return CmdlOptions.booleanOptionValue(this.cmdl, optName);
	}

	/**
	 * 
	 * @param optName
	 * @param defaultValue
	 * @return
	 */
	public boolean getBooleanOptionValue(final String optName,
			final boolean defaultValue) {
		return CmdlOptions.booleanOptionValue(this.cmdl, optName, defaultValue);
	}

	/**
	 * 
	 * @param optName
	 * @param defaultValue
	 * @return
	 * @throws NumberFormatException
	 */
	public int getIntOptionValue(final String optName, final int defaultValue)
			throws NumberFormatException {
		return CmdlOptions.intOptionValue(this.cmdl, optName, defaultValue);
	}

	/**
	 * 
	 * @param optName
	 * @return
	 * @throws NullPointerException
	 */
	public int intOptionValue(final String optName) throws NullPointerException {
		return CmdlOptions.intOptionValue(this.cmdl, optName);
	}
}
