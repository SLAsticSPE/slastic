package org.trustsoft.slastic.plugins.starter;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.ParseException;

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
        cmdlOpts.addOption(OptionBuilder.withArgName("file").hasArg().withLongOpt(CMD_LONG_OPT_START_FRAMEWORK).isRequired(true).withDescription("SLAstic.Framework configuration file").withValueSeparator('=').create("f"));
        cmdlOpts.addOption(OptionBuilder.withArgName("file").hasArg().withLongOpt(CMD_LONG_OPT_START_SIMULATION).isRequired(false).withDescription("SLAstic.Simulator configuration file").withValueSeparator('=').create("s"));
    }

    public static void main(String[] args) {
        log.info("Hi, this is SLAsticControl");

        if (!parseArgs(args)) {
            System.exit(1);
        }

        // start framework?
        // TODO: wait until framework ist initialized
        if (cmdl.hasOption(CMD_LONG_OPT_START_FRAMEWORK)) {
            SLAsticAdaptationFrameworkInstance frameworkInst = initSLAsticInstanceFromArgs();
            if (frameworkInst == null) {
                log.error("Failed to init SLAstic framework instance");
                System.exit(1);
            }
            frameworkInst.run();
            log.info("SLAstic framework instance started");
        }

        // start simulation?
        if (cmdl.hasOption(CMD_LONG_OPT_START_SIMULATION)) {
            SLAsticSimulatorInstance simInst = initSLAsticSimulationInstanceFromArgs();
            if (simInst == null) {
                log.error("Failed to init SLAstic simulator instance");
                System.exit(1);
            }
            simInst.run();
            log.info("SLAstic simulator instance started");
        }

        log.info("Bye, this was SLAsticControl");
    }

    /**
     * Initializes and returns a SLAstic simulation instance.
     *
     * @return the initialized instance; null on error
     */
    private static SLAsticSimulatorInstance initSLAsticSimulationInstanceFromArgs() throws IllegalArgumentException {
        String simulationConfigurationFile = cmdl.getOptionValue(CMD_LONG_OPT_START_SIMULATION);

        if (simulationConfigurationFile == null) {
            log.fatal("Missing value for command line option '" + CMD_LONG_OPT_START_SIMULATION + "'");
            throw new IllegalArgumentException("Missing value for command line option '" + CMD_LONG_OPT_START_SIMULATION + "'");
        }

        SLAsticSimulatorInstance inst = null;
        try {
            inst = new SLAsticSimulatorInstance(loadProperties(simulationConfigurationFile));
        } catch (Exception exc) {
            log.error("Error creating SLAsticInstance", exc);
            throw new IllegalArgumentException("Error creating SLAsticInstance", exc);
        }
        return inst;
    }

    /**
     * Initializes and returns a SLAstic instance.
     *
     * @return the initialized instance; null on error
     */
    private static SLAsticAdaptationFrameworkInstance initSLAsticInstanceFromArgs() throws IllegalArgumentException {
        String configurationFile = cmdl.getOptionValue(CMD_LONG_OPT_START_FRAMEWORK);

        if (configurationFile == null) {
            log.fatal("Missing value for command line option '" + CMD_LONG_OPT_START_FRAMEWORK + "'");
            throw new IllegalArgumentException("Missing value for command line option '" + CMD_LONG_OPT_START_FRAMEWORK + "'");
        }

        SLAsticAdaptationFrameworkInstance inst = null;
        try {
            inst = new SLAsticAdaptationFrameworkInstance(loadProperties(configurationFile));
        } catch (Exception exc) {
            log.error("Error creating SLAsticInstance", exc);
            throw new IllegalArgumentException("Error creating SLAsticInstance", exc);
        }
        return inst;
    }

    private static Properties loadProperties(String fn) throws IllegalArgumentException {
        // Load configuration file
        InputStream is = null;
        Properties prop = new Properties();

        try {
            is = new FileInputStream(fn);
            log.info("Loading properties from file '" + fn + "'");
            prop.load(is);
        } catch (Exception ex) {
            log.error("Failed to load properties from file '" + fn + "'", ex);
            throw new IllegalArgumentException("Failed to load properties from file '" + fn + "'", ex);
        } finally {
            try {
                is.close();
            } catch (Exception ex) {
                log.error("Failed to close property input stream", ex);
            }
        }
        return prop;
    }

    static boolean parseArgs(String[] args) {
        try {
            cmdl = cmdlParser.parse(cmdlOpts, args);


        } catch (ParseException e) {
            System.err.println("Error parsing arguments: " + e.getMessage());
            printUsage();
            return false;
        }

        return true;
    }

    private static void printUsage() {
        cmdHelpFormatter.printHelp(SLAsticAdaptationFrameworkInstance.class.getName(), cmdlOpts);
    }
}
